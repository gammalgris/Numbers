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
import jmul.math.functions.PolynomialFunction;
import jmul.math.numbers.Number;
import jmul.math.numbers.NumberHelper;
import jmul.math.numbers.nodes.DigitNode;
import jmul.math.numbers.nodes.NodesHelper;
import jmul.math.operations.MixedBinaryOperation;
import jmul.math.operations.OperationSingletons;
import jmul.math.operations.Result;
import jmul.math.operations.UnaryOperation;
import jmul.math.operations.repository.OperationIdentifiers;
import jmul.math.signs.Sign;
import jmul.math.signs.Signs;


/**
 * Translates a polynomial function to a number.
 *
 * TODO This implementation currently ignores the coefficients. Depending on the base the conversion (number -&gt; digit)
 * might throw an exception.
 * TODO This implementation doesn't evaluate the coefficients to determine a sign.
 *
 * @author Kristian Kutin
 */
public class PolynomialFunctionToNumber implements UnaryOperation<PolynomialFunction, Result<Number>> {

    /**
     * The default constructor.
     */
    public PolynomialFunctionToNumber() {

        super();
    }

    /**
     * Translates the specified polynomial function to a number.
     *
     * @param function
     *        a polynomial function
     *
     * @return a number
     */
    @Override
    public Result<Number> calculate(PolynomialFunction function) {

        ParameterCheckHelper.checkParameter(function);

        MixedBinaryOperation<Number, Integer, Result<Digit>> conversionFunction =
            (MixedBinaryOperation<Number, Integer, Result<Digit>>) OperationSingletons.getFunction(OperationIdentifiers.NUMBER_TO_DIGIT_FUNCTION);

        int base = function.base();
        Sign sign = Signs.POSITIVE;

        DigitNode currentNode = null;
        DigitNode centerNode = null;

        for (int index = 0; index < function.coefficients(); index++) {

            Number coefficient = function.coefficient(index);

            Result<Digit> result = conversionFunction.calculate(coefficient, base);
            Digit digit = result.result();

            DigitNode newNode = NodesHelper.createNode(digit);
            NodesHelper.linkNodes(newNode, currentNode);

            if (index == 0) {

                centerNode = newNode;
            }

            currentNode = newNode;
        }

        Number newNumber = NumberHelper.createNumber(base, sign, centerNode);

        return new Result<Number>(newNumber);
    }

}
