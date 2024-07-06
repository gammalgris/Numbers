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


import jmul.math.numbers.FunctionIdentifiers;
import jmul.math.numbers.Number;
import jmul.math.numbers.NumberImpl;
import jmul.math.numbers.Sign;
import jmul.math.numbers.digits.Digit;
import jmul.math.numbers.exceptions.UndefinedResultException;
import jmul.math.numbers.nodes.DigitNode;
import jmul.math.numbers.nodes.Nodes;
import jmul.math.numbers.nodes.NodesResult;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.Result;
import jmul.math.operations.ResultWithCarry;

import jmul.math.functions.FunctionSingletons;


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
     *        an oeprand
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
                throw new UndefinedResultException(operand1, operand2);
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
     * @return a sum
     */
    private Result<Number> addEqualSigns(Number operand1, Number operand2) {

        int base = operand1.base();
        Sign sign = operand1.sign();

        NodesResult moveResult = Nodes.moveRightSynchronously(operand1.centerNode(), operand2.centerNode());
        DigitNode node1 = moveResult.firstNode;
        DigitNode node2 = moveResult.secondNode;

        DigitNode leftTail;
        DigitNode rightTail;
        if (node1.rightNode() != null) {

            rightTail = Nodes.cloneRightTail(node1.rightNode());

        } else {

            rightTail = Nodes.cloneRightTail(node2.rightNode());
        }

        DigitNode resultNode = null;
        DigitNode previousResultNode = null;
        DigitNode resultCenter = null;
        DigitNode resultWithoutRightTail = null;

        BinaryOperation<Digit, ResultWithCarry<Digit>> function =
            (BinaryOperation<Digit, ResultWithCarry<Digit>>) FunctionSingletons.getFunction(FunctionIdentifiers.ADD_DIGITS_FUNCTION);
        Digit carry = null;
        ResultWithCarry<Digit> resultWrapper;
        while (true) {

            resultWrapper = function.calculate(node1.digit(), node2.digit());
            Digit result = resultWrapper.result();
            if ((carry == null) || carry.isZero()) {

                carry = resultWrapper.carry();

            } else {

                ResultWithCarry<Digit> resultWrapper2 = function.calculate(result, carry);
                result = resultWrapper2.result();
                carry = resultWrapper.carry();
            }

            previousResultNode = resultNode;
            resultNode = Nodes.createNode(result);
            Nodes.linkNodes(resultNode, previousResultNode);

            if (resultWithoutRightTail == null) {

                resultWithoutRightTail = resultNode;
            }

            if (node1 == operand1.centerNode()) {

                resultCenter = resultNode;
            }

            if ((node1.leftNode() != null) && (node2.leftNode() != null)) {

                node1 = node1.leftNode();
                node2 = node2.leftNode();

            } else {

                if (node1.leftNode() != null) {

                    leftTail = node1.leftNode();

                } else {

                    leftTail = node2.leftNode();
                }

                break;
            }
        }

        Nodes.linkNodes(resultWithoutRightTail, rightTail);

        while (leftTail != null) {

            resultWrapper = function.calculate(leftTail.digit(), carry);
            Digit result = resultWrapper.result();
            carry = resultWrapper.carry();

            DigitNode newLeft = Nodes.createNode(result);

            Nodes.linkNodes(newLeft, resultNode);

            resultNode = resultNode.leftNode();
            leftTail = leftTail.leftNode();
        }

        if (!carry.isZero()) {

            DigitNode newLeft = Nodes.createNode(carry);
            Nodes.linkNodes(newLeft, resultNode);
        }

        Nodes.trimLeft(resultCenter);
        Nodes.trimRight(resultCenter);

        Number result = new NumberImpl(base, sign, resultCenter);

        return new Result<Number>(result);
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
     *        the minuend
     * @param subtrahend
     *        the subtrahend
     *
     * @return a difference
     */
    private Result<Number> subtractNumber(Sign sign, Number minuend, Number subtrahend) {

        //TODO
        return null;
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

        int base = operand1.base();
        boolean equalSigns = operand1.sign() == operand2.sign();

        if (operand1.isInfinity() || operand2.isInfinity()) {

            return addInfinity(operand1, operand2);

        } else if (operand1.isZero() || operand2.isZero()) {

            return addZero(operand1, operand2);
        }

        if (equalSigns) {

            /*
             * cases handled:
             *
             * n + m
             * -n + -m
             */
            return addEqualSigns(operand1, operand2);
        }

        Number absolute1 = operand1.absoluteValue();
        Number absolute2 = operand2.absoluteValue();

        if (absolute1.compareTo(absolute2) == 0) {

            /*
             * cases handled:
             *
             * n + -m -> 0 (if abs(n) = abs(m))
             * -n + m -> 0 (if abs(n) = abs(m))
             */

            Number result = new NumberImpl(base, "0");
            return new Result<Number>(result);
        }

        /*
         * cases handled:
         *
         * n + -m -> n - m (if abs(n) > abs(m))
         * n + -m -> -m + n -> -(m - n) (if abs(m) > abs(n))
         * -n + m -> -n + m -> -(n - m) (if abs(n) > abs(m))
         * -n + m -> m - n (if abs(m) > abs(n))
         */
        
        if (absolute1.compareTo(absolute2) > 0) {

            /*
             * cases handled:
             *
             * n + -m -> n - m (if abs(n) > abs(m))
             * -n + m -> -n + m -> -(n - m) (if abs(n) > abs(m))
             */

            // TODO
            return null;

        } else {

            /*
             * cases handled:
             *
             * n + -m -> -m + n -> -(m - n) (if abs(m) > abs(n))
             * -n + m -> m - n (if abs(m) > abs(n))
             */

            // TODO
            return null;
        }
    }

}
