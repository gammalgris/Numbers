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

package jmul.math.functions.implementations;


import java.util.Collections;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createInfinity;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.numbers.exceptions.NoResultButLimitException;
import jmul.math.numbers.exceptions.UndefinedOperationException;
import jmul.math.numbers.nodes.NodesHelper;
import jmul.math.operations.Result;
import jmul.math.operations.TernaryOperation;
import jmul.math.signs.Sign;
import jmul.math.signs.Signs;


/**
 * Implements the division using russian division.
 *
 * @author Kristian Kutin
 */
public class RussianDivisionFunction implements TernaryOperation<Number, Result<Number>> {

    /**
     * The default constructor.
     */
    public RussianDivisionFunction() {

        super();
    }

    /**
     * Divides the first number by the second number.
     *
     * @param operand1
     *        a number
     * @param operand2
     *        a number
     * @param decimalPlaces
     *        the number of decimal places retained after cutting fraction part
     *
     * @return the quotient
     */
    @Override
    public Result<Number> calculate(Number operand1, Number operand2, Number decimalPlaces) {

        ParameterCheckHelper.checkParameters(operand1, operand2, decimalPlaces);
        ParameterCheckHelper.checkInteger(decimalPlaces);

        int base = operand1.base();
        Sign sign = Signs.divideAndDetermineResultSign(operand1.sign(), operand2.sign());

        final Number NORMALIZED_ZERO = createNumber(base, Signs.POSITIVE, 0);
        final Number ZERO = createNumber(base, Signs.POSITIVE, 0);


        // Handle special cases which can be resolved without computation.

        if (operand1.isZero() && operand2.isInfinity()) {

            throw new NoResultButLimitException(ZERO);
        }

        if (operand1.isInfinity() && operand2.isInfinity()) {

            throw new NoResultButLimitException(ZERO);
        }

        if (operand1.isInfinity() && operand2.isZero()) {

            Number INFINITY = createInfinity(base, sign);
            throw new NoResultButLimitException(INFINITY);
        }

        if (operand2.isZero()) {

            throw new UndefinedOperationException();
        }

        if (operand1.isZero()) {

            return new Result<Number>(ZERO);
        }

        if (operand2.isOne()) {

            Number clone = createNumber(operand1);

            if (operand1.isPositive() && operand2.isNegative()) {

                clone = clone.negate();

            } else if (operand1.isNegative() && operand2.isNegative()) {

                clone = clone.negate();
            }

            return new Result<Number>(clone);
        }


        // Determine the integer parts (result and remainder)

        final Number ONE = createNumber(base, Signs.POSITIVE, 1);

        SortedMap<Number, Number> multiples = new TreeMap<Number, Number>(Collections.reverseOrder());

        Number absoluteDividend = operand1.absoluteValue();
        Number absoluteDivisor = operand2.absoluteValue();

        Number integerPart = ZERO;
        Number remainder = absoluteDividend;

        {
            // Fill an inversely sorted map with multiples. The hash map should not be empty and must have at least
            // one entry. Having too few entries might result in too many loops.

            Number factor = ONE;
            Number multiple = absoluteDivisor;

            Number max = absoluteDividend;

            while (max.isLesserOrEqual(multiple)) {

                max = max.shiftRight();
            }

            while (max.isGreater(multiple)) {

                multiples.put(factor, multiple);

                factor = factor.doubling();
                multiple = multiple.doubling();
            }
        }

        {
            while (true) {

                if (remainder.isLesser(absoluteDivisor)) {

                    break;
                }

                for (Map.Entry<Number, Number> entry : multiples.entrySet()) {

                    Number key = entry.getKey();
                    Number value = entry.getValue();

                    if (remainder.isGreaterOrEqual(value)) {

                        integerPart = integerPart.add(key);
                        remainder = remainder.subtract(value);
                        break;
                    }
                }
            }
        }

        if (remainder.isZero()) { // If there is no remainder then the calculation is done.

            if (Signs.isNegative(sign)) {

                integerPart = integerPart.negate();
            }

            return new Result<Number>(integerPart);
        }


        // Determine the fraction part

        Number fractionPart = ZERO;
        Number digits = NORMALIZED_ZERO;

        {
            Number factor = ZERO;

            while (true) {

                if (digits.isGreaterOrEqual(decimalPlaces) && remainder.isLesser(absoluteDivisor)) {

                    break;
                }

                if (remainder.isZero()) {

                    break;
                }

                if (remainder.isLesser(absoluteDivisor)) {

                    remainder = remainder.shiftRight();
                    digits = digits.inc();

                    if (remainder.isLesser(absoluteDivisor)) {

                        fractionPart = fractionPart.add(factor);
                        fractionPart = fractionPart.shiftRight();

                        factor = ZERO;
                    }
                }

                for (Map.Entry<Number, Number> entry : multiples.entrySet()) {

                    Number key = entry.getKey();
                    Number value = entry.getValue();

                    if (remainder.isGreaterOrEqual(value)) {

                        remainder = remainder.subtract(value);
                        factor = factor.add(key);

                        if (remainder.isLesser(absoluteDivisor)) {

                            fractionPart = fractionPart.add(factor);
                            fractionPart = fractionPart.shiftRight();

                            factor = ZERO;
                        }

                        break;
                    }
                }
            }
        }


        // Correct and trim the fraction part.

        while (!digits.isZero()) {

            fractionPart = fractionPart.shiftLeft();
            digits = digits.dec();
        }
        fractionPart = fractionPart.shiftLeft();

        NodesHelper.trimRight(fractionPart.centerNode());


        // Add the integer part and fraction part for the final result.

        Number result = integerPart.add(fractionPart);

        if (Signs.isNegative(sign)) {

            result = result.negate();
        }

        return new Result<Number>(result);
    }

}
