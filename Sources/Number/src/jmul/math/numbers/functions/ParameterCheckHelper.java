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
import jmul.math.numbers.exceptions.DigitBaseMismatchException;


/**
 * A helper class for checking parameters.
 *
 * @author Kristian Kutin
 */
final class ParameterCheckHelper {

    /**
     * The default constructor.
     */
    private ParameterCheckHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Checks the specified parameters and throws an exception if invalid.
     *
     * @param d
     *        a parameter
     */
    public static void checkParameter(Digit d) {

        if (d == null) {

            String message = "The specified digit is null!";
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Checks if the specified digits have the same base.
     *
     * @param d1
     *        a digit
     * @param d2
     *        a digit
     */
    public static void checkParameterBase(Digit d1, Digit d2) {

        if (d1.base() != d2.base()) {

            throw new DigitBaseMismatchException(d1, d2);
        }
    }

}
