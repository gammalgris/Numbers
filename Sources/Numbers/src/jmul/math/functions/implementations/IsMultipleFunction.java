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


import jmul.math.Math;
import jmul.math.functions.FunctionSingletons;
import jmul.math.functions.repository.FunctionIdentifiers;
import jmul.math.numbers.Number;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.Result;
import jmul.math.operations.TernaryOperation;


/**
 * This function tests if a number is the multiple of a specified number.
 *
 * @author Kristian Kutin
 */
public class IsMultipleFunction implements BinaryOperation<Number, Result<Boolean>> {

    /**
     * The defalt constructor.
     */
    public IsMultipleFunction() {

        super();
    }

    /**
     * Checks if the first specified number is a multiple of the second specified number.
     *
     * @param number1
     *        a number
     * @param number2
     *        a number
     *
     * @return <code>true</code> if the first specified number is a multiple of the second specified number,
     *         else <code>false</code>
     */
    @Override
    public Result<Boolean> calculate(Number number1, Number number2) {

        ParameterCheckHelper.checkParameters(number1, number2);

        TernaryOperation<Number, Result<Number>> divisionFunction =
            (TernaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.RUSSIAN_DIVISION_FUNCTION);
        Result<Number> divisionResult =
            divisionFunction.calculate(number1, number2, Math.DEFAULT_MAXIMUM_FRACTION_LENGTH);

        Number quotient = divisionResult.result();
        boolean result = quotient.isInteger();

        return new Result<Boolean>(result);
    }

}
