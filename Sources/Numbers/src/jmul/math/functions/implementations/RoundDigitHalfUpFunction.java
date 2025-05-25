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
import jmul.math.operations.ResultWithCarry;
import jmul.math.operations.UnaryOperation;


/**
 * A function for rounding digits.
 *
 * @author Kristian Kutin
 */
public class RoundDigitHalfUpFunction implements UnaryOperation<Digit, ResultWithCarry<Digit>> {

    /**
     * The default constructor.
     */
    public RoundDigitHalfUpFunction() {

        super();
    }

    /**
     * Rounds the specified digit and returns the result of the rounding and the carry for the next digit.
     *
     * @param digit
     *        a digit
     *
     * @return a rounded digit and the carry for the next digit
     */
    @Override
    public ResultWithCarry<Digit> calculate(Digit digit) {

        ParameterCheckHelper.checkParameter(digit);

        int base = digit.base();
        int diviso = base / 2;
        int remainder = base % 2;
        int ordinal = digit.ordinal();
        boolean oddBase = remainder > 0;

        int result = 0;
        int carry = 0;
        if (oddBase) {

            if (ordinal > diviso) {

                carry = 1;
            }

        } else {

            if (ordinal >= diviso) {

                carry = 1;
            }
        }

        Digit resultDigit = PositionalNumeralSystems.ordinalToDigit(base, result);
        Digit carryDigit = PositionalNumeralSystems.ordinalToDigit(base, carry);

        return new ResultWithCarry<Digit>(resultDigit, carryDigit);
    }

}
