/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2022  Kristian Kutin
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
import jmul.math.numbers.digits.Digit;
import jmul.math.numbers.exceptions.UndefinedOperationException;
import jmul.math.numbers.nodes.DigitNode;
import jmul.math.numbers.nodes.NodesHelper;
import jmul.math.numbers.nodes.NodesResult;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.Result;
import jmul.math.operations.ResultWithCarry;
import jmul.math.operations.UnaryOperation;

import jmul.metainfo.annotations.Modified;


/**
 * This class implements a function for adding two numbers. The implementation checks the operands
 * regarding absolute values and signs and compares the numbers. Depending on the actual case an
 * addition or subtraction is performed.<br>
 * Addition is straightforward by adding all corresponding digits from right to left and carry over
 * the carry. Subtractions are delegated to the corresponding function. Special cases (e.g. infinity
 * or zero operands) are handled seperately as they don't require much computation.
 *
 * @author Kristian Kutin
 */
public class AddNumbers implements BinaryOperation<Number, Result<Number>> {

    /**
     * The default constructor.
     */
    public AddNumbers() {

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
     * Adds the specified operands. One or both operands are infinity.
     *
     * @param operand1
     *        an operand
     * @param operand2
     *        an operand
     *
     * @return the result
     */
    private Result<Number> addInfinity(Number operand1, Number operand2) {

        int base = operand1.base();
        Sign sign = operand1.sign();
        boolean equalSigns = operand1.sign() == operand2.sign();

        if (equalSigns) {

            /*
             * cases handled:
             *
             * inifinity + infinity = infinity
             * -infinity + -infinity = -infinity
             * infinity + n = infinity
             * -infinity + -n = -infinity
             * n + infinity = infinity
             * -n + -infinity = -infinity
             */
            Number result = new NumberImpl(base, sign);
            return new Result<Number>(result);

        } else {

            if (operand1.isInfinity() && !operand2.isInfinity()) {

                /*
                 * cases handled:
                 *
                 * infinity + -n = infinity
                 * -infinity + n = -infinity
                 */
                Number result = new NumberImpl(operand1);
                return new Result<Number>(result);

            } else if (!operand1.isInfinity() && operand2.isInfinity()) {

                /*
                 * cases handled:
                 *
                 * n + -infinity = -infinity
                 * -n + infinity = infinity
                 */
                Number result = new NumberImpl(operand2);
                return new Result<Number>(result);

            } else {

                /*
                 * cases handled:
                 *
                 * infinity + -infinity
                 * -infinity + infinity
                 */
                throw new UndefinedOperationException("+", operand1, operand2);
            }
        }
    }

    /**
     * Adds the specified operands. One or both operands are zero.
     *
     * @param operand1
     *        an operand
     * @param operand2
     *        an operand
     *
     * @return the result
     */
    private Result<Number> addZero(Number operand1, Number operand2) {

        int base = operand1.base();

        if (operand1.isZero() && !operand2.isZero()) {

            /*
             * cases handled:
             *
             * 0 + n = n
             * -0 + -n = -n
             * 0 + -n = -n
             * -0 + n = n
             */

            Number result = new NumberImpl(operand2);
            return new Result<Number>(result);

        } else if (!operand1.isZero() && operand2.isZero()) {

            /*
             * cases handled:
             *
             * n + 0 = n
             * -n + -0 = -n
             * n + -0 = n
             * -n + 0 = -n
             */

            Number result = new NumberImpl(operand1);
            return new Result<Number>(result);

        } else {

            /*
             * cases handled:
             *
             * 0 + 0 = 0
             * -0 + -0 = 0
             * 0 + -0 = 0
             * -0 + 0 = 0
             */

            Number result = new NumberImpl(base, "0");
            return new Result<Number>(result);
        }
    }

    /**
     * Adds the specified operands. Both operands have the same sign.
     *
     * @param operand1
     *        an operand
     * @param operand2
     *        an operand
     *
     * @return the sum of the two operands
     */
    private Result<Number> addOperandsWithSameSign(Number operand1, Number operand2) {

        int base = operand1.base();
        Sign sign = operand1.sign();

        NodesResult moveResult = NodesHelper.moveRightSynchronously(operand1.centerNode(), operand2.centerNode());
        DigitNode node1 = moveResult.firstNode;
        DigitNode node2 = moveResult.secondNode;


        // Check which number has more digits to the right. These digits just have to be cloned and appended to
        // the result.
        DigitNode rightTail = null;
        if (node1.rightNode() != null) {

            rightTail = node1.rightNode();

        } else if (node2.rightNode() != null) {

            rightTail = node2.rightNode();
        }


        // Add the relevant digits and create a new linked list with the results.
        BinaryOperation<Digit, ResultWithCarry<Digit>> addDigitsFunction =
            (BinaryOperation<Digit, ResultWithCarry<Digit>>) FunctionSingletons.getFunction(FunctionIdentifiers.ADD_DIGITS_FUNCTION);

        DigitNode resultNode = null;
        DigitNode previousResultNode = null;
        DigitNode resultCenter = null;
        DigitNode resultRightEndWithoutTail = null;
        Digit previousCarry = null;

        while (true) {

            if ((node1 == null) || (node2 == null)) {

                break;
            }

            ResultWithCarry<Digit> resultWrapper = addDigitsFunction.calculate(node1.digit(), node2.digit());
            Digit result = resultWrapper.result();
            Digit carry = resultWrapper.carry();

            if ((previousCarry != null) && !previousCarry.isZero()) {

                ResultWithCarry<Digit> resultWrapperWithAddedCarry = addDigitsFunction.calculate(result, previousCarry);
                result = resultWrapperWithAddedCarry.result();

                if ((resultWrapperWithAddedCarry.carry() != null) && !resultWrapperWithAddedCarry.carry().isZero()) {

                    // In this case the first addition produced no carry (i.e. zero) and we have to keep the new carry.
                    carry = resultWrapperWithAddedCarry.carry();
                }
            }

            if (resultNode == null) {

                resultNode = NodesHelper.createNode(result);
                resultRightEndWithoutTail = resultNode;

            } else {

                resultNode = NodesHelper.createNode(result);
                NodesHelper.linkNodes(resultNode, previousResultNode);
            }

            if (node1 == operand1.centerNode()) {

                resultCenter = resultNode;
            }

            previousCarry = carry;
            previousResultNode = resultNode;
            node1 = node1.leftNode();
            node2 = node2.leftNode();
        }


        // Check which number has more digits to the left.
        DigitNode leftTail = null;
        if (node1 != null) {

            leftTail = node1;

        } else if (node2 != null) {

            leftTail = node2;
        }


        if (leftTail == null) {

            if (!previousCarry.isZero()) {

                resultNode = NodesHelper.createNode(previousCarry);
                NodesHelper.linkNodes(resultNode, previousResultNode);
            }

        } else {

            while (true) {

                if (leftTail == null) {

                    break;
                }

                ResultWithCarry<Digit> resultWrapper = addDigitsFunction.calculate(leftTail.digit(), previousCarry);
                Digit result = resultWrapper.result();
                Digit carry = resultWrapper.carry();

                resultNode = NodesHelper.createNode(result);
                NodesHelper.linkNodes(resultNode, previousResultNode);

                previousCarry = carry;
                previousResultNode = resultNode;
                leftTail = leftTail.leftNode();
            }
        }


        // clone and append the right tail if there is any
        if (rightTail != null) {

            DigitNode clonedRightTail = NodesHelper.cloneRightTail(rightTail);
            NodesHelper.linkNodes(resultRightEndWithoutTail, clonedRightTail);
        }


        Number result = new NumberImpl(base, sign, resultCenter);

        return new Result<Number>(result);
    }

    /**
     * Adds the specified operands. The operands have different signs.
     *
     * @param operand1
     *        an operand
     * @param operand2
     *        an operand
     *
     * @return the difference of the two operands
     */
    private Result<Number> addOperandsWithDifferentSigns(Number operand1, Number operand2) {

        int base = operand1.base();

        Number absolute1 = operand1.absoluteValue();
        Number absolute2 = operand2.absoluteValue();

        int comparisonResult = absolute1.compareTo(absolute2);

        if (comparisonResult == 0) {

            /*
             * abs(n) = abs(m)
             *
             * cases handled:
             *
             * n + -m -> 0 (if abs(n) = abs(m))
             * -n + m -> 0 (if abs(n) = abs(m))
             */

            Number result = new NumberImpl(base, "0");
            return new Result<Number>(result);

        } else if (comparisonResult > 0) {

            /*
             * abs(n) > abs(m)
             *
             * cases handled:
             *
             * n + -m -> n - m (if abs(n) > abs(m))
             * -n + m -> -(n - m) (if abs(n) > abs(m))
             */

            if (operand1.isNegative()) {

                return subtractNumber(Signs.NEGATIVE, absolute1, absolute2);

            } else {

                return subtractNumber(Signs.POSITIVE, absolute1, absolute2);
            }

        } else {

            /*
             * abs(n) < abs(m)
             *
             * cases handled:
             *
             * n + -m -> -m + n -> -(m - n) (if abs(m) > abs(n))
             * -n + m -> m - n (if abs(m) > abs(n))
             */

            if (operand1.isNegative()) {

                return subtractNumber(Signs.POSITIVE, absolute2, absolute1);

            } else {

                return subtractNumber(Signs.NEGATIVE, absolute2, absolute1);
            }
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

    /**
     * Takes the two operands and calculates the sum. Depending on the respective values and
     * signs the required operation is an addition or subtraction. In some cases (infinity and
     * zero) no calculation is necessary.
     *
     * @param operand1
     *        an operand
     * @param operand2
     *        an operand
     *
     * @return a sum
     */
    @Override
    public Result<Number> calculate(Number operand1, Number operand2) {

        checkParameters(operand1, operand2);

        boolean sameSigns = operand1.sign() == operand2.sign();

        Result<Number> result;
        if (operand1.isInfinity() || operand2.isInfinity()) {

            return addInfinity(operand1, operand2);

        } else if (operand1.isZero() || operand2.isZero()) {

            return addZero(operand1, operand2);

        } else if (sameSigns) {

            /*
             * cases handled:
             *
             * n + m
             * -n + -m
             */
            result = addOperandsWithSameSign(operand1, operand2);

        } else {

            /*
             * cases handled:
             *
             * -n + m
             * n + -m
             */
            result = addOperandsWithDifferentSigns(operand1, operand2);
        }

        DigitNode resultCenter = result.result().centerNode();
        NodesHelper.trimLeft(resultCenter);
        NodesHelper.trimRight(resultCenter);

        return result;
    }

}
