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
import jmul.math.operations.MixedBinaryOperation;
import jmul.math.operations.Result;


/**
 * Translates a number to a digit in a specified number base.
 *
 * @author Kristian Kutin
 */
public class NumberToDigit implements MixedBinaryOperation<Number, Integer, Result<Digit>> {

    /**
     * The default constructor.
     */
    public NumberToDigit() {

        super();
    }

    /**
     * Translates a number to a digit in a specified number base.
     *
     * @param number
     *        a number which represents a digit in the specified number base
     * @param destinationBase
     *        a number base
     *
     * @return a digit in the specified number base
     */
    @Override
    public Result<Digit> calculate(Number number, Integer destinationBase) {

        ParameterCheckHelper.checkParameter(number);
        ParameterCheckHelper.checkNumberBase(destinationBase);

        MixedBinaryOperation<Number, Integer, Result<Integer>> function =
            (MixedBinaryOperation<Number, Integer, Result<Integer>>) FunctionSingletons.getFunction(FunctionIdentifiers.NUMBER_TO_ORDINAL_FUNCTION);
        Result<Integer> result = function.calculate(number, destinationBase);
        int ordinal = result.result();

        Digit digit = PositionalNumeralSystems.ordinalToDigit(destinationBase, ordinal);

        return new Result<Digit>(digit);
    }

}
