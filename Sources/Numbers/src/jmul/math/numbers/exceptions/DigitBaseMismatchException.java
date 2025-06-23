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

package jmul.math.numbers.exceptions;


import jmul.math.digits.Digit;
import jmul.math.fractions.Fraction;
import jmul.math.numbers.Number;


/**
 * A custom exception for cases when two numbers cannot be cpmared.
 *
 * @author Kristian Kutin
 */
public class DigitBaseMismatchException extends IllegalArgumentException {

    /**
     * Creates a new exception with the specified parameters.
     *
     * @param e1
     *        an expression
     * @param e2
     *        an expression
     */
    public DigitBaseMismatchException(Fraction e1, Fraction e2) {

        super(createErrorMessage(e1, e2));
    }

    /**
     * Creates a new exception with the specified parameters.
     *
     * @param e1
     *        an expression
     * @param n2
     *        a number
     */
    public DigitBaseMismatchException(Fraction e1, Number n2) {

        super(createErrorMessage(e1, n2));
    }

    /**
     * Creates a new exception with the specified parameters.
     *
     * @param n1
     *        a number
     * @param n2
     *        a number
     */
    public DigitBaseMismatchException(Number n1, Number n2) {

        super(createErrorMessage(n1, n2));
    }

    /**
     * Creates a new exception with the specified parameters.
     *
     * @param d1
     *        a digit
     * @param d2
     *        a digit
     */
    public DigitBaseMismatchException(Digit d1, Digit d2) {

        super(createErrorMessage(d1, d2));
    }

    /**
     * Creates an error message according to the specified parameters.
     *
     * @param e1
     *        an expression
     * @param e2
     *        an expression
     *
     * @return an error message
     */
    private static String createErrorMessage(Fraction e1, Fraction e2) {

        return String.format("Unable to compare expression %s (base %d) and %s (base %d)!", e1.toString(), e1.base(),
                             e2.toString(), e2.base());
    }

    /**
     * Creates an error message according to the specified parameters.
     *
     * @param e1
     *        an expression
     * @param n2
     *        a number
     *
     * @return an error message
     */
    private static String createErrorMessage(Fraction e1, Number n2) {

        return String.format("Unable to compare expression %s (base %d) and %s (base %d)!", e1.toString(), e1.base(),
                             n2.toString(), n2.base());
    }

    /**
     * Creates an error message according to the specified parameters.
     *
     * @param n1
     *        a number
     * @param n2
     *        a number
     *
     * @return an error message
     */
    private static String createErrorMessage(Number n1, Number n2) {

        return String.format("Unable to compare numbers %s (base %d) and %s (base %d)!", n1.toString(), n1.base(),
                             n2.toString(), n2.base());
    }

    /**
     * Creates an error message according to the specified parameters.
     *
     * @param d1
     *        a digit
     * @param d2
     *        a digit
     *
     * @return an error message
     */
    private static String createErrorMessage(Digit d1, Digit d2) {

        return String.format("Unable to compare digits %s (base %d) and %s (base %d)!", d1.toString(), d1.base(),
                             d2.toString(), d2.base());
    }

}
