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


import jmul.math.digits.Digit;
import jmul.math.digits.PositionalNumeralSystems;
import jmul.math.functions.FunctionSingletons;
import jmul.math.functions.repository.FunctionIdentifiers;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.numbers.nodes.DigitNode;
import jmul.math.numbers.nodes.NodesHelper;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.Result;
import jmul.math.operations.ResultWithCarry;
import jmul.math.signs.Sign;


/**
 * This function shortens the precision of a number according to the specified decimal places
 * (see <a href="https://en.wikipedia.org/wiki/Rounding">Banker's rounding</a>).
 *
 * @author Kristian Kutin
 */
public class RoundNumberToOddFunction implements BinaryOperation<Number, Result<Number>> {

    /**
     * The default constructor.
     */
    public RoundNumberToOddFunction() {

        super();
    }

    /**
     * Shorten the precision of this number according to the specified decimal places.
     *
     * @param number
     *        a number
     * @param decimalPlaces
     *        the number of decimal places remaining after rounding
     *
     * @return a shortened number according to the specified precision
     */
    @Override
    public Result<Number> calculate(Number number, Number decimalPlaces) {

        ParameterCheckHelper.checkParameter(number);
        ParameterCheckHelper.checkPositiveInteger(decimalPlaces);

        Number clone = createNumber(number);

        if (clone.isInfinity() || clone.isInteger()) {

            return new Result<Number>(clone);
        }

        // Move from the center node to the node which represents the specified precision and remeber the node.

        DigitNode centerNode = clone.centerNode();
        Number counter = decimalPlaces;
        DigitNode newRightEnd = centerNode;

        while (true) {

            if (counter.isZero()) {

                break;
            }

            if (newRightEnd.rightNode() == null) {

                break;
            }

            newRightEnd = newRightEnd.rightNode();
            counter = counter.dec();
        }

        // If the fraction part is shorter than the specified precision there is nothing else to do. Adding nodes
        // to the right with zeroes is superfluous.

        if (!counter.isZero()) {

            return new Result<Number>(clone);
        }

        BinaryOperation<Digit, ResultWithCarry<Digit>> roundDigit =
            (BinaryOperation<Digit, ResultWithCarry<Digit>>) FunctionSingletons.getFunction(FunctionIdentifiers.ROUND_DIGIT_TO_ODD_FUNCTION);
        BinaryOperation<Digit, ResultWithCarry<Digit>> addDigits =
            (BinaryOperation<Digit, ResultWithCarry<Digit>>) FunctionSingletons.getFunction(FunctionIdentifiers.ADD_DIGITS_FUNCTION);

        // Move to the right end of the linked list. Go from right to left and round each digit. Stop when reaching
        // the specified precision.

        int base = clone.base();
        Sign sign = clone.sign();

        final Digit ZERO = PositionalNumeralSystems.ordinalToDigit(base, 0);

        // round this number

        DigitNode currentNode = NodesHelper.moveRight(centerNode);
        Digit carryDigit = ZERO;
        boolean stop = false;

        while (true) {

            // exit loop conditions

            if (currentNode == null) {

                break;
            }

            if (currentNode == newRightEnd) {

                stop = true;
            }

            if (stop && carryDigit.isZero()) {

                break;
            }

            Digit currentDigit = currentNode.digit();
            Digit oldCurrentDigit = currentDigit;

            if (!carryDigit.isZero()) { // add carry to current digit

                ResultWithCarry<Digit> resultAddition = addDigits.calculate(currentDigit, carryDigit);
                currentDigit = resultAddition.result();
                carryDigit = resultAddition.carry();
            }

            if (currentDigit != oldCurrentDigit) { // replace current digit node

                DigitNode newCurrentNode = NodesHelper.createNode(currentDigit);

                if (currentNode == centerNode) {

                    centerNode = newCurrentNode;
                    clone = createNumber(base, sign, centerNode);
                }

                if (currentNode == newRightEnd) {

                    newRightEnd = newCurrentNode;
                }

                NodesHelper.replaceNode(currentNode, newCurrentNode);
                oldCurrentDigit = currentDigit;
                currentNode = newCurrentNode;
            }

            { // round current digit

                DigitNode nodeToRight = currentNode.rightNode();
                Digit digitToRight;
                if (nodeToRight == null) {

                    digitToRight = ZERO;

                } else {

                    digitToRight = nodeToRight.digit();
                }

                ResultWithCarry<Digit> result = roundDigit.calculate(currentDigit, digitToRight);
                currentDigit = result.result();
                Digit roundingCarry = result.carry();

                if (!roundingCarry.isZero()) {

                    carryDigit = roundingCarry;
                }
            }

            if (currentDigit != oldCurrentDigit) { // replace current digit node

                DigitNode newCurrentNode = NodesHelper.createNode(currentDigit);

                if (currentNode == centerNode) {

                    centerNode = newCurrentNode;
                    clone = createNumber(base, sign, centerNode);
                }

                if (currentNode == newRightEnd) {

                    newRightEnd = newCurrentNode;
                }

                NodesHelper.replaceNode(currentNode, newCurrentNode);
                oldCurrentDigit = currentDigit;
                currentNode = newCurrentNode;
            }

            currentNode = currentNode.leftNode();
        }

        if (!carryDigit.isZero()) {

            DigitNode leftNode = NodesHelper.createNode(carryDigit);
            currentNode = NodesHelper.moveLeft(centerNode);
            NodesHelper.linkNodes(leftNode, currentNode);
        }

        // Trim the new number.

        NodesHelper.removeRightTail(newRightEnd);
        NodesHelper.trimRight(centerNode);

        if (clone.isZero()) {

            clone = clone.absoluteValue();
        }

        return new Result<Number>(clone);
    }

}
