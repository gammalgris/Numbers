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

package jmul.math.numbers.digits.operations;


import jmul.math.numbers.digits.Digit;
import jmul.math.numbers.system.NumeralSystems;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.ResultWithCarry;
import jmul.math.operations.ResultWithCarryImpl;


/**
 * An implementation of an operation that performs the addition on two digits.
 *
 * @param <T>
 *        the actual parameter type
 *
 * @author Kristian Kutin
 */
public class DigitAddition<T extends Digit> implements BinaryOperation<T, ResultWithCarry<T>> {

    /**
     * The default constructor.
     */
    public DigitAddition() {

        super();
    }

    /**
     * Checks the specified parameters and throws an exception if invalid.
     *
     * @param p1
     *        a parameter
     * @param p2
     *        a parameter
     */
    private void checkparameters(T p1, T p2) {

        if (p1 == null) {

            String message = "The first parameter is null!";
            throw new IllegalArgumentException(message);
        }

        if (p2 == null) {

            String message = "The second parameter is null!";
            throw new IllegalArgumentException(message);
        }

        if (p1.base() != p2.base()) {

            String message =
                String.format("The specified digits are from different numeral systems (base=%d; base=%d)!", p1.base(),
                              p2.base());
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Performs the binary operation for the specified parameters.
     *
     * @param p1
     *        a parameter
     * @param p2
     *        a parameter
     *
     * @return a result
     */
    @Override
    public ResultWithCarry<T> calculate(T p1, T p2) {

        checkparameters(p1, p2);

        int base = p1.base();

        int carry = 0;
        int result = p1.ordinal() + p2.ordinal();

        if (result >= base) {

            carry = result % base;
            result = result / base;
        }

        T resultDigit = (T) NumeralSystems.ordinalToDigit(base, result);
        T carryDigit = (T) NumeralSystems.ordinalToDigit(base, carry);

        return new ResultWithCarryImpl<T>(resultDigit, carryDigit);
    }

}
