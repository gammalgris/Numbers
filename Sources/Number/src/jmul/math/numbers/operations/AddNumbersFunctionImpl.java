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

package jmul.math.numbers.operations;


import jmul.math.numbers.FunctionIdentifiers;
import jmul.math.numbers.Number;
import jmul.math.numbers.NumberImpl;
import jmul.math.numbers.Sign;
import jmul.math.numbers.digits.Digit;
import jmul.math.numbers.nodes.DigitNode;
import jmul.math.numbers.nodes.Nodes;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.Result;
import jmul.math.operations.ResultWithCarry;

import jmul.singletons.FunctionSingletons;


/**
 * This class implements a function for adding two numbers.
 *
 * @author Kristian Kutin
 */
public class AddNumbersFunctionImpl implements BinaryOperation<Number, Result<Number>> {

    /**
     * The default constructor.
     */
    public AddNumbersFunctionImpl() {

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
                String message =
                    String.format("Adding %s and %s is undefined!", operand1.toString(), operand2.toString());
                throw new UndefinedResultException(message);
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
        boolean equalSigns = operand1.sign() == operand2.sign();

        if (equalSigns) {

            if (operand1.isZero() && !operand2.isZero()) {

                /*
                 * cases handled:
                 *
                 * 0 + n = n
                 * -0 + -n = -n
                 */
                Number result = new NumberImpl(operand2);
                return new Result<Number>(result);

            } else if (!operand1.isZero() && operand2.isZero()) {

                /*
                 * cases handled:
                 *
                 * n + 0 = n
                 * -n + -0 = -n
                 */
                Number result = new NumberImpl(operand1);
                return new Result<Number>(result);

            } else {

                /*
                 * cases handled:
                 *
                 * 0 + 0 = 0
                 * -0 + -0 = 0
                 */
                Number result = new NumberImpl(base, "0");
                return new Result<Number>(result);
            }

        } else {

            if (operand1.isZero() && !operand2.isZero()) {

                /*
                 * cases handled:
                 *
                 * 0 + -n = -n
                 * -0 + n = n
                 */
                Number result = new NumberImpl(operand2);
                return new Result<Number>(result);

            } else if (!operand1.isZero() && operand2.isZero()) {

                /*
                 * cases handled:
                 *
                 * n + -0 = n
                 * -n + 0 = -n
                 */
                Number result = new NumberImpl(operand1);
                return new Result<Number>(result);

            } else {

                /*
                 * cases handled:
                 *
                 * 0 + -0 = 0
                 * -0 + 0 = 0
                 */
                Number result = new NumberImpl(base, "0");
                return new Result<Number>(result);
            }
        }
    }

    /**
     * Removes leading zeros in the linked lit.
     *
     * @param centerNode
     *        the center node of a linked list
     */
    private void trimLeft(DigitNode centerNode) {

        DigitNode left = centerNode;

        while (left.leftNode() != null) {

            left = left.leftNode();
        }

        DigitNode right;
        do {

            if (left == centerNode) {

                break;
            }

            right = left.rightNode();
            if (left.digit().isZero()) {

                Nodes.removeLeftTail(right);
                left = right;

            } else {

                break;
            }

        } while (right != null);
    }

    /**
     * Removes trailing zeros in the linked lit.
     *
     * @param centerNode
     *        the center node of a linked list
     */
    private void trimRight(DigitNode centerNode) {

        DigitNode right = centerNode;

        while (right.rightNode() != null) {

            right = right.rightNode();
        }

        DigitNode left;
        do {

            if (right == centerNode) {

                break;
            }

            left = right.leftNode();
            if (right.digit().isZero()) {

                Nodes.removeRightTail(left);
                right = left;

            } else {

                break;
            }

        } while (left != null);
    }

    private Result<Number> addEqualSigns(Number operand1, Number operand2) {

        int base = operand1.base();
        Sign sign = operand1.sign();

        DigitNode node1 = operand1.centerNode();
        DigitNode node2 = operand2.centerNode();

        while (true) {

            if ((node1.rightNode() != null) && (node2.rightNode() != null)) {

                node1 = node1.rightNode();
                node2 = node2.rightNode();

            } else {

                break;
            }
        }

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

        trimLeft(resultCenter);
        trimRight(resultCenter);

        Number result = new NumberImpl(base, sign, resultCenter);

        return new Result<Number>(result);
    }

    @Override
    public Result<Number> calculate(Number operand1, Number operand2) {

        checkParameters(operand1, operand2);

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

        } else {

            /*
             * cases handled:
             *
             * n + -m
             * -n + m
             */
            //TODO subtraction missing
        }

        // TODO Implement this method
        return null;
    }

}
