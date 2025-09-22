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
import jmul.math.operations.OperationSingletons;
import jmul.math.operations.repository.OperationIdentifiers;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.numbers.nodes.DigitNode;
import jmul.math.numbers.nodes.NodesHelper;
import jmul.math.operations.MixedBinaryOperation;
import jmul.math.operations.Result;
import jmul.math.operations.UnaryOperation;
import jmul.math.signs.Signs;


/**
 * This function generates a random number.<br>
 * <br>
 * <i>Note:<br>
 * The performance needs to be improved.</i>
 *
 * @author Kristian Kutin
 */
public class RandomNumberFunction implements MixedBinaryOperation<Integer, Number, Result<Number>> {

    /**
     * The default constructor.
     */
    public RandomNumberFunction() {

        super();
    }

    /**
     * Generates a random number between zero and one with the specified number of digits to the right of the decimal
     * separator (0 =&lt; n &lt; 1).
     *
     * @param base
     *        a number base
     * @param digits
     *        the number of digits to the right of the decimal separator
     *
     * @return a number greater or equal to zero and lesser than one
     */
    @Override
    public Result<Number> calculate(Integer base, Number digits) {

        ParameterCheckHelper.checkNumberBase(base);
        ParameterCheckHelper.checkPositiveIntegerGreaterZero(digits);

        UnaryOperation<Integer, Result<Digit>> randomDigitFunction =
            (UnaryOperation<Integer, Result<Digit>>) OperationSingletons.getFunction(OperationIdentifiers.RANDOM_DIGIT_FUNCTION);

        DigitNode digitNode = NodesHelper.createNode(base, 0);
        DigitNode centerNode = digitNode;

        Number remainingDigits = digits;
        while (!remainingDigits.isZero()) {

            Result<Digit> result = randomDigitFunction.calculate(base);
            Digit randomDigit = result.result();

            DigitNode newDigitNode = NodesHelper.createNode(randomDigit);
            NodesHelper.linkNodes(digitNode, newDigitNode);

            digitNode = digitNode.rightNode();

            remainingDigits = remainingDigits.dec();
        }

        NodesHelper.trimRight(centerNode);
        Number randomNumber = createNumber(base, Signs.POSITIVE, centerNode);

        return new Result<Number>(randomNumber);
    }

}
