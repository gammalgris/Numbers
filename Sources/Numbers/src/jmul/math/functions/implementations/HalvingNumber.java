/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2024  Kristian Kutin
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


import jmul.math.functions.FunctionSingletons;
import jmul.math.functions.repository.FunctionIdentifiers;
import jmul.math.numbers.Number;
import jmul.math.numbers.NumberImpl;
import jmul.math.signs.Sign;
import jmul.math.digits.Digit;
import jmul.math.numbers.nodes.DigitNode;
import jmul.math.numbers.nodes.NodesHelper;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.Result;
import jmul.math.operations.ResultWithCarry;
import jmul.math.operations.ResultWithRemainder;
import jmul.math.operations.UnaryOperation;


/**
 * An implementation of a function that halves numbers.
 *
 * @author Kristian Kutin
 */
public class HalvingNumber implements UnaryOperation<Number, Result<Number>> {

    /**
     * The default constructor.
     */
    public HalvingNumber() {

        super();
    }

    /**
     * Returns a number which is half the specified number.
     *
     * @param number
     *        a number
     *
     * @return a number
     */
    @Override
    public Result<Number> calculate(Number number) {

        ParameterCheckHelper.checkParameter(number);

        if (number.isZero()) {

            Number clone = new NumberImpl(number);
            if (clone.isNegative()) {

                clone = clone.negate();
            }
            return new Result<Number>(clone);
        }

        if (number.isInfinity()) {

            Number clone = new NumberImpl(number);
            return new Result<Number>(clone);
        }

        int base = number.base();
        Sign sign = number.sign();

        DigitNode centerNode = number.centerNode();
        DigitNode currentNode = NodesHelper.moveLeft(centerNode);

        DigitNode clonedCenterNode = null;
        DigitNode clonedCurrentNode = null;
        DigitNode leftTail = null;

        BinaryOperation<Digit, ResultWithCarry<Digit>> addDigitsFunction =
            (BinaryOperation<Digit, ResultWithCarry<Digit>>) FunctionSingletons.getFunction(FunctionIdentifiers.ADD_DIGITS_FUNCTION);

        UnaryOperation<Digit, ResultWithRemainder<Digit>> halveDigitFunction =
            (UnaryOperation<Digit, ResultWithRemainder<Digit>>) FunctionSingletons.getFunction(FunctionIdentifiers.HALVING_DIGIT_FUNCTION);

        ResultWithRemainder<Digit> previousResult = null;

        while (true) {

            if (currentNode == null) {

                break;
            }

            Digit currentDigit = currentNode.digit();
            ResultWithRemainder<Digit> result = halveDigitFunction.calculate(currentDigit);

            if (previousResult == null) {

                Digit newDigit = result.result();
                clonedCurrentNode = NodesHelper.createNode(newDigit);
                previousResult = result;

            } else {

                ResultWithCarry<Digit> sum = addDigitsFunction.calculate(result.result(), previousResult.remainder());
                Digit newDigit = sum.result();
                clonedCurrentNode = NodesHelper.createNode(newDigit);
                previousResult = result;
            }

            if (currentNode == centerNode) {

                clonedCenterNode = clonedCurrentNode;
            }

            NodesHelper.linkNodes(leftTail, clonedCurrentNode);
            leftTail = clonedCurrentNode;

            currentNode = currentNode.rightNode();
        }

        if (previousResult != null) {

            Digit digit = previousResult.remainder();
            if (!digit.isZero()) {

                clonedCurrentNode = NodesHelper.createNode(digit);
                NodesHelper.linkNodes(leftTail, clonedCurrentNode);
            }
        }

        NodesHelper.trimLeft(clonedCenterNode);
        NodesHelper.trimRight(clonedCenterNode);

        Number halvedNumber = new NumberImpl(base, sign, clonedCenterNode);
        return new Result<Number>(halvedNumber);
    }

}
