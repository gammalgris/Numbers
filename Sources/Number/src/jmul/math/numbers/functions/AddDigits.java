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


import jmul.math.numbers.digits.Digit;
import jmul.math.numbers.digits.PositionalNumeralSystems;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.ResultWithCarry;
import static jmul.math.numbers.functions.ParameterCheckHelper.checkParameter;
import static jmul.math.numbers.functions.ParameterCheckHelper.checkParameterBase;


/**
 * This binary function adds two digits.
 *
 * @author Kristian Kutin
 */
public class AddDigits implements BinaryOperation<Digit, ResultWithCarry<Digit>> {

    /**
     * The default constructor.
     */
    public AddDigits() {

        super();
    }

    /**
     * Adds the specified digits.
     *
     * @param operand1
     *        an operand
     * @param operand2
     *        an operand
     *
     * @return the result of the addition
     */
    @Override
    public ResultWithCarry<Digit> calculate(Digit operand1, Digit operand2) {

        checkParameter(operand1);
        checkParameter(operand2);
        checkParameterBase(operand1, operand2);

        int base = operand1.base();

        int carry = 0;
        int result = operand1.ordinal() + operand2.ordinal();

        if (result >= base) {

            result = result - base;
            carry = 1;
        }

        Digit resultDigit = PositionalNumeralSystems.ordinalToDigit(base, result);
        Digit carryDigit = PositionalNumeralSystems.ordinalToDigit(base, carry);

        return new ResultWithCarry<Digit>(resultDigit, carryDigit);
    }

}
