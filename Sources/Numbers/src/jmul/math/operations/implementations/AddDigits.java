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

package jmul.math.operations.implementations;


import jmul.math.digits.Digit;
import jmul.math.digits.PositionalNumeralSystems;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.ResultWithCarry;
import static jmul.math.operations.implementations.ParameterCheckHelper.checkParameter;
import static jmul.math.operations.implementations.ParameterCheckHelper.checkParameterBase;


/**
 * This function adds two digits.
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
     * @param digit1
     *        an operand
     * @param digit2
     *        an operand
     *
     * @return the result of the addition
     */
    @Override
    public ResultWithCarry<Digit> calculate(Digit digit1, Digit digit2) {

        checkParameter(digit1);
        checkParameter(digit2);
        checkParameterBase(digit1, digit2);

        int base = digit1.base();

        int sum = digit1.ordinal() + digit2.ordinal();

        int result = sum % base;
        int carry = sum / base;

        Digit resultDigit = PositionalNumeralSystems.ordinalToDigit(base, result);
        Digit carryDigit = PositionalNumeralSystems.ordinalToDigit(base, carry);

        return new ResultWithCarry<Digit>(resultDigit, carryDigit);
    }

}
