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
import jmul.math.operations.Result;
import jmul.math.operations.ResultImpl;
import jmul.math.operations.UnaryOperation;


/**
 * An implementation of an operation that calculates the complement of the specified digit.
 *
 * @param <T>
 *        the actual parameter type
 *
 * @author Kristian Kutin
 */
public class DigitComplement<T extends Digit> implements UnaryOperation<T, Result<T>> {

    /**
     * The default constructor.
     */
    public DigitComplement() {

        super();
    }

    /**
     * Checks the specified parameters and throws an exception if invalid.
     *
     * @param p1
     *        a parameter
     */
    private void checkparameter(T p1) {

        if (p1 == null) {

            String message = "The first parameter is null!";
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Performs the unary operation for the specified parameter.
     *
     * @param p1
     *        a parameter
     *
     * @return a result
     */
    public Result<T> calculate(T p1) {

        checkparameter(p1);

        int base = p1.base();

        int result = base - p1.ordinal() - 1;

        T resultDigit = (T) NumeralSystems.ordinalToDigit(base, result);

        return new ResultImpl<T>(resultDigit);
    }

}
