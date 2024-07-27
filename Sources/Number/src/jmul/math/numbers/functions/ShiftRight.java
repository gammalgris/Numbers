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

package jmul.math.numbers.functions;


import jmul.math.functions.FunctionSingletons;
import jmul.math.numbers.FunctionIdentifiers;
import jmul.math.numbers.Number;
import jmul.math.numbers.exceptions.UndefinedOperationException;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.Result;


/**
 * This is an implementation of a shift function which translates into multiplying a number
 * by a multiple of it's number base.
 *
 * @author Kristian Kutin
 */
public class ShiftRight implements BinaryOperation<Number, Result<Number>> {

    /**
     * The default constructor.
     */
    public ShiftRight() {

        super();
    }

    /**
     * Takes a copy of the specified number and shifts the decimal separator to the right
     * by the specified number of shifts (i.e. base^shifts).
     *
     * @param number
     *        a number
     * @param shifts
     *        the nuber of shifts to be performed. Zero will perform no shift. A positive number
     *        will perform shifts to the left. A negative number will perform shifts to the right.
     *
     * @return a copy of the specified number
     */
    @Override
    public Result<Number> calculate(Number number, Number shifts) {

        checkParameters(number, shifts);

        BinaryOperation<Number, Result<Number>> function =
            (BinaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.SHIFT_LEFT_FUNCTION);
        Result<Number> result = function.calculate(number, shifts.negate());

        return result;
    }

    /**
     * Checks the specifiecd parameters.
     *
     * @param number
     *        an oeprand
     * @param shifts
     *        the number of shifts
     */
    private void checkParameters(Number number, Number shifts) {

        if (number == null) {

            String message = "No number (null) was specified!";
            throw new IllegalArgumentException(message);
        }

        if (shifts == null) {

            String message = "No number of shifts (null) was specified!";
            throw new IllegalArgumentException(message);
        }

        if (number.base() != shifts.base()) {

            String message = "The specified numbers are of different bases!";
            throw new IllegalArgumentException(message);
        }

        if (shifts.isFraction()) {

            String message = "The operation is undefined if the number for shifts is a fraction!";
            throw new UndefinedOperationException(message);
        }
    }

}
