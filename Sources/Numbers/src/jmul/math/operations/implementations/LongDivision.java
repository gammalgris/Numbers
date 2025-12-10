/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2025  Kristian Kutin
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * e-mail: kristian.kutin@arcor.de
 */

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package jmul.math.operations.implementations;


import jmul.math.digits.Digit;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.numbers.exceptions.NoResultButLimitException;
import jmul.math.numbers.exceptions.UndefinedOperationException;
import jmul.math.numbers.nodes.DigitNode;
import jmul.math.numbers.nodes.NodesHelper;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.OperationSingletons;
import jmul.math.operations.Result;
import jmul.math.operations.ResultWithRemainder;
import jmul.math.operations.TernaryOperation;
import jmul.math.operations.processing.ProcessingDetails;
import jmul.math.operations.repository.OperationIdentifiers;
import jmul.math.signs.Sign;
import jmul.math.signs.Signs;


/**
 * An implementation of long division (see <a href="https://en.wikipedia.org/wiki/Long_division">long division</a>).
 *
 * @author Kristian Kutin
 */
public class LongDivision implements TernaryOperation<Number, Result<Number>> {

    /**
     * The default constructor.
     */
    public LongDivision() {

        super();
    }

    /**
     * Divides the specified dividend by the specified divisor.
     *
     * @param dividend
     *        a number
     * @param divisor
     *        a number
     * @param decimalPlaces
     *        the number of decimal places retained after cutting the fraction part
     *
     * @return the quotient
     */
    @Override
    public Result<Number> calculate(Number dividend, Number divisor, Number decimalPlaces) {

        ParameterCheckHelper.checkParameters(dividend, divisor, decimalPlaces);
        ParameterCheckHelper.checkPositiveInteger(decimalPlaces);

        int base = dividend.base();
        Sign sign = Signs.negate(Signs.xor(dividend.sign(), divisor.sign()));

        Number absoluteDividend = dividend.absoluteValue();
        Number absoluteDivisor = divisor.absoluteValue();


        // Handle special cases which can be resolved without computation.

        final Number ZERO = createNumber(base, Signs.POSITIVE, 0);

        if (absoluteDivisor.isZero()) {

            if (absoluteDividend.isInfinity()) {

                Number newDividend = absoluteDividend;
                if (Signs.isNegative(sign)) {

                    newDividend = newDividend.negate();
                }

                throw new NoResultButLimitException(newDividend);

            } else {

                throw new UndefinedOperationException(sign.toString(), dividend, divisor);
            }

        } else if (absoluteDivisor.isOne()) {

            Number newDividend = absoluteDividend;
            if (Signs.isNegative(sign)) {

                newDividend = newDividend.negate();
            }

            return new Result<Number>(newDividend);

        } else if (absoluteDividend.isZero()) {

            return new Result<Number>(ZERO);
        }


        // Shift the divisor and dividend. We need integers. The ratio remains the same.

        while (absoluteDividend.isFraction() || absoluteDivisor.isFraction()) {

            absoluteDividend = absoluteDividend.shiftRight();
            absoluteDivisor = absoluteDivisor.shiftRight();
        }


        // Prepare the calculation

        BinaryOperation<Number, ResultWithRemainder<Number>> divisionFunction =
            (BinaryOperation<Number, ResultWithRemainder<Number>>) OperationSingletons.getFunction(OperationIdentifiers.DIVIDE_NUMBERS_RETURN_RESULT_AND_REMAINDER_FUNCTION);

        DigitNode firstCenterNode = absoluteDividend.centerNode();
        DigitNode firstNode = NodesHelper.moveLeft(firstCenterNode);
        DigitNode currentNode = firstNode; // This reference points to a node within the first operand.

        DigitNode previousNode = null; // This reference points to a node within the temporary number. See below.

        Number decimalCounter = ZERO.dec();
        Number resultNumber = null;

        boolean first = true;

        while (true) {

            // prepare the number for the intermediary step

            DigitNode newNode;
            if (currentNode == null) {

                newNode = NodesHelper.createNode(base, 0);

            } else {

                Digit digit = currentNode.digit();
                newNode = NodesHelper.createNode(digit);
            }

            NodesHelper.linkNodes(previousNode, newNode);
            NodesHelper.trimLeft(newNode);
            previousNode = newNode;
            Number temporaryNumber = createNumber(base, Signs.POSITIVE, newNode);

            if ((currentNode == firstCenterNode) || !decimalCounter.isNegative()) {

                decimalCounter = decimalCounter.inc();
            }

            if (currentNode != null) {

                currentNode = currentNode.rightNode();
            }

            if (first && !temporaryNumber.isZero() && temporaryNumber.isLesser(absoluteDivisor)) {

                continue;

            } else {

                first = false;
            }


            // now calculate this intermediary result

            ResultWithRemainder<Number> result = divisionFunction.calculate(temporaryNumber, absoluteDivisor);
            Number intermediaryResult = result.result();
            Number remainder = result.remainder();

            if (resultNumber == null) {

                resultNumber = intermediaryResult;

            } else {

                resultNumber = resultNumber.shiftRight();

                if (!intermediaryResult.isZero()) {

                    resultNumber = resultNumber.add(intermediaryResult);
                }
            }

            if (remainder.isZero() && (currentNode == null)) {

                break;
            }

            if (decimalCounter.isGreaterOrEqual(decimalPlaces)) {

                break;
            }

            if (remainder.isZero()) {

                previousNode = null;

            } else {

                previousNode = remainder.centerNode();
            }
        }


        // adjust the result

        resultNumber = resultNumber.shiftLeft(decimalCounter);
        if (resultNumber.isFraction()) {

            NodesHelper.trimRight(resultNumber.centerNode());

            ProcessingDetails processingDetails = ProcessingDetails.setPrecision(decimalPlaces);
            resultNumber = resultNumber.round(processingDetails);
        }

        if (Signs.isNegative(sign)) {

            resultNumber = resultNumber.negate();
        }

        return new Result<Number>(resultNumber);
    }

}
