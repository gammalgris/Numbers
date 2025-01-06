/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2023  Kristian Kutin
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

package jmul.math.numbers.functions;


import jmul.math.functions.FunctionSingletons;
import jmul.math.numbers.FunctionIdentifiers;
import jmul.math.numbers.Number;
import jmul.math.numbers.NumberImpl;
import jmul.math.numbers.Sign;
import jmul.math.numbers.Signs;
import jmul.math.numbers.exceptions.UndefinedOperationException;
import jmul.math.numbers.nodes.DigitNode;
import jmul.math.numbers.nodes.NodesHelper;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.Result;
import jmul.math.operations.UnaryOperation;

import jmul.metainfo.annotations.Modified;


/**
 * This class implements a function for subtracting two numbers. The implementation checks the
 * operands regarding absolute values and signs and compares the numbers. Depending on the actual
 * case an addition or subtraction is performed.<br>
 * Subtraction is done by calculating the complement of the subtrahend and add it to minuend
 * (minuend &gt; subtrahend). Additions are delegated to the corresponding function. Special cases
 * (e.g. infinity or zero operands) are handled seperately as they don't require much computation.
 *
 * @author Kristian Kutin
 */
public class SubtractNumbers implements BinaryOperation<Number, Result<Number>> {

    /**
     * The default constructor.
     */
    public SubtractNumbers() {

        super();
    }

    /**
     * Checks the specifiecd parameters.
     *
     * @param operand1
     *        an operand
     * @param operand2
     *        an operand
     */
    private void checkParameters(Number operand1, Number operand2) {

        if (operand1 == null) {

            String message = "The first operand is null!";
            throw new IllegalArgumentException(message);
        }

        if (operand2 == null) {

            String message = "The second operand is null!";
            throw new IllegalArgumentException(message);
        }

        if (operand1.base() != operand2.base()) {

            String message = "The specified numbers are of different bases!";
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Subtracts the specified operands. One or both operands are infinity.
     *
     * @param operand1
     *        an operand
     * @param operand2
     *        an operand
     *
     * @return the result
     */
    private Result<Number> subtractInfinity(Number operand1, Number operand2) {

        int base = operand1.base();
        boolean equalSigns = operand1.sign() == operand2.sign();

        /*
         * all cases:
         *
         *  1) infinity - infinity = undefined operation
         *  2) infinity - n = infinity
         *  3) infinity - -infinity = infinity + infinity = infinity
         *  4) infinity - -n = infinity + n = infinity
         *  5) -infinity - infinity = -infinity
         *  6) -infinity - n = -infinity
         *  7) -infinity - -infinity = -infinity + infinity = undefined operation
         *  8) -infinity - -n = -infinity + n = -infinity
         *  9) n - infinity = -infinity
         * 10) n - -infinity = n + infinity = infinity
         * 11) -n - infinity = -infinity
         * 12) -n - -infinity = -n + infinity = infinity
         */

        if (!operand1.isInfinity() && operand2.isInfinity()) {

            /*
             * first operand is not infinity
             *
             *  9) n - infinity = -infinity
             * 10) n - -infinity = n + infinity = infinity
             * 11) -n - infinity = -infinity
             * 12) -n - -infinity = -n + infinity = infinity
             */
            Number result = new NumberImpl(operand2);
            result = result.negate();
            return new Result<Number>(result);

        } else if (operand1.isInfinity() && !operand2.isInfinity()) {

            /*
             * second operand is not infinity
             *
             *  2) infinity - n = infinity
             *  4) infinity - -n = infinity + n = infinity
             *  6) -infinity - n = -infinity
             *  8) -infinity - -n = -infinity + n = -infinity
             */
            Number result = new NumberImpl(operand1);
            return new Result<Number>(result);

        } else {

            if (equalSigns) {

                /*
                 * both operands are infinity and have equal signs
                 * => undefined operation
                 *
                 *  1) infinity - infinity = undefined operation
                 *  7) -infinity - -infinity = -infinity + infinity = undefined operation
                 */
                throw new UndefinedOperationException("-", operand1, operand2);

            } else {

                /*
                 * both operands are infinity and have different signs
                 *
                 *  3) infinity - -infinity = infinity + infinity = infinity
                 *  5) -infinity - infinity = -infinity
                 */
                Number result = new NumberImpl(base, operand1.sign());
                return new Result<Number>(result);
            }
        }
    }

    /**
     * Subtracts the specified operands. One or both operands are zero.
     *
     * @param operand1
     *        an operand
     * @param operand2
     *        an operand
     *
     * @return the result
     */
    private Result<Number> subtractZero(Number operand1, Number operand2) {

        int base = operand1.base();

        /*
         * all cases:
         *
         * 0 - n = -n
         * 0 - -n = 0 + n = n
         * -0 - n = -n
         * -0 - -n = 0 + n = n
         * n - 0 = n
         * n - -0 = n + 0 = n
         * -n - 0 = -n
         * -n - -0 = -n + 0 = -n
         * 0 - 0 = 0
         * 0 - -0 = 0
         * -0 - 0 = 0
         * -0 - -0 = 0
         */

        if (operand1.isZero() && !operand2.isZero()) {

            /*
             * cases handled:
             *
             * 0 - n = -n
             * 0 - -n = 0 + n = n
             * -0 - n = -n
             * -0 - -n = 0 + n = n
             */
            Number result = new NumberImpl(operand2);
            result = result.negate();
            return new Result<Number>(result);

        } else if (!operand1.isZero() && operand2.isZero()) {

            /*
             * cases handled:
             *
             * n - 0 = n
             * n - -0 = n + 0 = n
             * -n - 0 = -n
             * -n - -0 = -n + 0 = -n
             */
            Number result = new NumberImpl(operand1);
            return new Result<Number>(result);

        } else {

            /*
             * cases handled:
             *
             * 0 - 0 = 0
             * 0 - -0 = 0
             * -0 - 0 = 0
             * -0 - -0 = 0
             */
            Number result = new NumberImpl(base, "0");
            return new Result<Number>(result);
        }
    }

    /**
     * Subtracts the minuend from the subtrahend and returns the difference. The minuend and the subtrahend
     * are positive numbers.
     *
     * @param minuend
     *        the minuend of the subtraction
     * @param subtrahend
     *        the subtrahend of the subtraction
     *
     * @return the difference
     */
    private Result<Number> subtractOperandsWithSameSign(Number minuend, Number subtrahend) {

        /*
         * prequisites:
         *
         * 1) minuend > 0; subtrahend > 0
         *
         * cases:
         *
         * 1) minuend > subtrahend => n - m
         * 2) minuend < subtrahend => n - m => -(-n + m) => -(m - n)
         */

        int base = minuend.base();

        int comparisonResult = minuend.compareTo(subtrahend);
        if (comparisonResult > 0) {

            return subtractNumber(Signs.POSITIVE, minuend, subtrahend);

        } else if (comparisonResult < 0) {

            return subtractNumber(Signs.NEGATIVE, subtrahend, minuend);

        } else {

            Number result = new NumberImpl(base, "0");
            return new Result<Number>(result);
        }
    }

    /**
     * Adds the summands and returns the sum. The summands are positive numbers.
     *
     * @param firstSummand
     *        a summand in the addition
     * @param secondSummand
     *        a summand in the addition
     *
     * @return the sum
     */
    private Result<Number> addOperandsWithSameSign(Number firstSummand, Number secondSummand) {

        BinaryOperation<Number, Result<Number>> additionFunction =
            (BinaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.ADD_NUMBERS_FUNCTION);
        Result<Number> result = additionFunction.calculate(firstSummand, secondSummand);

        return result;
    }

    /**
     * Takes the two operands and calculates the difference. Depending on the respective values and
     * signs the required operation is an addition or subtraction. In some cases (infinity and
     * zero) no calculation is necessary.
     *
     * @param operand1
     *        an operand
     * @param operand2
     *        an operand
     *
     * @return a difference
     */
    @Override
    public Result<Number> calculate(Number operand1, Number operand2) {

        checkParameters(operand1, operand2);

        boolean sameSigns = operand1.sign() == operand2.sign();

        if (operand1.isInfinity() || operand2.isInfinity()) {

            return subtractInfinity(operand1, operand2);

        } else if (operand1.isZero() || operand2.isZero()) {

            return subtractZero(operand1, operand2);

        } else {

            Result<Number> result;
            Number absolute1 = operand1.absoluteValue();
            Number absolute2 = operand2.absoluteValue();
            Sign sign = operand1.sign();

            if (sameSigns) {

                /*
                 * cases handled:
                 *
                 * n - m
                 * -n - -m => -n + m => -1 * (n - m)
                 */

                result = subtractOperandsWithSameSign(absolute1, absolute2);

            } else {

                /*
                 * cases handled:
                 *
                 * -n - m => -1 * (n + m)
                 * n - -m => n + m
                 */
                result = addOperandsWithSameSign(absolute1, absolute2);
            }

            Number number = result.result();
            DigitNode resultCenter = number.centerNode();

            NodesHelper.trimLeft(resultCenter);
            NodesHelper.trimRight(resultCenter);

            if (Signs.isNegative(sign)) {

                number = number.negate();
            }

            return new Result<Number>(number);
        }
    }

    /**
     * Performs a subtraction using the complement method (i.e. turns the subtraction into an addition).
     * Calculate the complement of the minuend. Then add the subtrahend. Finally calculate complement of the
     * result. The specified sign is applied to the end result. The caller must check which of the specified
     * numbers has the greater absolute value and pass the oarameters accordingly (i.e. the minuend should
     * always have the greater absolute value).
     *
     * @param sign
     *        the sign of the result
     * @param minuend
     *        the minuend. The minuend may be modified.
     * @param subtrahend
     *        the subtrahend
     *
     * @return a difference
     */
    private Result<Number> subtractNumber(Sign sign, @Modified Number minuend, Number subtrahend) {

        /*
         * Prerequisites:
         *
         * 1) minuend > subtrahend
         *    If this is not the case then this function will return a wrong result.
         * 2) Signs should have been removed from the minuend and subtrahend. If not then
         *    ignore the signs.
         *
         * Notes:
         *
         * 1) A subtraction by complement is made thus the subtraction is replaced with an addition.
         *    The complement of the minuend is added to the subtrahend. The complement of the result is
         *    the result of the subtraction.
         * 2) The decimal part of the minuend can have less digits than the decimal part of the subtrahend.
         *    Accordingly the minuend may need additional trailing zeroes.
         * 3) The result can have leading and trailing zeroes. Accordingly the result has to be trimmed.
         */

        Result<Number> result;

        NodesHelper.fillUpWithZeroes(minuend.centerNode(), subtrahend.centerNode());

        UnaryOperation<Number, Result<Number>> complementFunction =
            (UnaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.NUMBER_COMPLEMENT_FUNCTION);
        result = complementFunction.calculate(minuend);
        Number complement = result.result();

        result = addOperandsWithSameSign(subtrahend, complement);
        Number sum = result.result();

        result = complementFunction.calculate(sum);
        Number unsignedSum = result.result();

        Number signedSum;
        if (Signs.POSITIVE == sign) {

            signedSum = unsignedSum;

        } else {

            signedSum = unsignedSum.negate();
        }

        return new Result<Number>(signedSum);
    }

}
