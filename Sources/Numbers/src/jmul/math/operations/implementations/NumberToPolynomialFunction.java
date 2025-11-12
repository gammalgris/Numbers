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


import java.util.ArrayList;
import java.util.List;

import jmul.math.digits.Digit;
import jmul.math.functions.PolynomialFunction;
import jmul.math.functions.PolynomialFunctionImpl;
import jmul.math.numbers.Number;
import jmul.math.numbers.nodes.DigitNode;
import jmul.math.operations.OperationSingletons;
import jmul.math.operations.Result;
import jmul.math.operations.UnaryOperation;
import jmul.math.operations.repository.OperationIdentifiers;


/**
 * Translates a number to a polynomial function.
 *
 * @author Kristian Kutin
 */
public class NumberToPolynomialFunction implements UnaryOperation<Number, Result<PolynomialFunction>> {

    /**
     * The default constructor.
     */
    public NumberToPolynomialFunction() {

        super();
    }

    /**
     * Translates the specified number to a polynomial function.
     *
     * @param number
     *        a number
     *
     * @return a polynomial function
     */
    @Override
    public Result<PolynomialFunction> calculate(Number number) {

        ParameterCheckHelper.checkPositiveIntegerGreaterZero(number);

        UnaryOperation<Digit, Result<Number>> function =
            (UnaryOperation<Digit, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.DIGIT_TO_NUMBER_FUNCTION);

        List<Number> operands = new ArrayList<>();

        DigitNode currentNode = number.centerNode();
        while (currentNode != null) {

            Digit digit = currentNode.digit();

            Result<Number> conversionResult = function.calculate(digit);
            Number operand = conversionResult.result();

            operands.add(operand);

            currentNode = currentNode.leftNode();
        }

        Number[] emptyArray = { };
        Number[] array = operands.toArray(emptyArray);
        PolynomialFunction polynomialFunction = new PolynomialFunctionImpl(array);

        return new Result<PolynomialFunction>(polynomialFunction);
    }

}
