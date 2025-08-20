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


import jmul.math.digits.Digit;
import jmul.math.digits.PositionalNumeralSystems;
import jmul.math.functions.FunctionSingletons;
import jmul.math.functions.repository.FunctionIdentifiers;
import jmul.math.numbers.Number;
import jmul.math.numbers.NumberHelper;
import static jmul.math.numbers.NumberHelper.createNumber;
import static jmul.math.numbers.creation.CreationParameters.CLONE;
import jmul.math.numbers.nodes.DigitNode;
import jmul.math.numbers.nodes.NodesHelper;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.Result;
import jmul.math.operations.ResultWithRemainder;
import jmul.math.operations.UnaryOperation;
import jmul.math.signs.Sign;


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

            Number clone = NumberHelper.createNumber(CLONE, number);
            if (clone.isNegative()) {

                clone = clone.negate();
            }
            return new Result<Number>(clone);
        }

        if (number.isInfinity()) {

            Number clone = NumberHelper.createNumber(CLONE, number);
            return new Result<Number>(clone);
        }

        int base = number.base();
        Sign sign = number.sign();

        DigitNode centerNode = number.centerNode();
        DigitNode currentNode = NodesHelper.moveLeft(centerNode);

        DigitNode clonedCenterNode = null;
        DigitNode clonedCurrentNode = null;
        DigitNode leftTail = null;

        BinaryOperation<Digit, ResultWithRemainder<Digit>> halveDigitFunction =
            (BinaryOperation<Digit, ResultWithRemainder<Digit>>) FunctionSingletons.getFunction(FunctionIdentifiers.HALVING_DIGIT_FUNCTION);

        final Digit zeroDigit = PositionalNumeralSystems.ordinalToDigit(base, 0);
        Digit previousCarry = zeroDigit;

        while (currentNode != null) {

            Digit currentDigit = currentNode.digit();
            ResultWithRemainder<Digit> result = halveDigitFunction.calculate(currentDigit, previousCarry);

            Digit newDigit = result.result();
            previousCarry = result.remainder();

            clonedCurrentNode = NodesHelper.createNode(newDigit);

            if (currentNode == centerNode) {

                clonedCenterNode = clonedCurrentNode;
            }

            NodesHelper.linkNodes(leftTail, clonedCurrentNode);
            leftTail = clonedCurrentNode;

            currentNode = currentNode.rightNode();
        }

        if (!previousCarry.isZero()) {

            ResultWithRemainder<Digit> result = halveDigitFunction.calculate(zeroDigit, previousCarry);
            Digit digit = result.result();
            clonedCurrentNode = NodesHelper.createNode(digit);
            NodesHelper.linkNodes(leftTail, clonedCurrentNode);
        }

        NodesHelper.trimLeft(clonedCenterNode);
        NodesHelper.trimRight(clonedCenterNode);

        Number halvedNumber = createNumber(base, sign, clonedCenterNode);
        return new Result<Number>(halvedNumber);
    }

}
