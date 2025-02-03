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

package jmul.math.functions.implementations;


import jmul.math.digits.Digit;
import jmul.math.fractions.Fraction;
import jmul.math.numbers.Number;
import jmul.math.numbers.exceptions.DigitBaseMismatchException;


/**
 * A helper class for checking parameters.
 *
 * @author Kristian Kutin
 */
public final class ParameterCheckHelper {

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

    /**
     * Checks the specifiecd parameters.
     *
     * @param operand1
     *        an operand
     * @param operand2
     *        an operand
     */
    public static void checkParameters(Number operand1, Number operand2) {

        if (operand1 == null) {

            String message = "The first operand is null!";
            throw new IllegalArgumentException(message);
        }

        if (operand2 == null) {

            String message = "The second operand is null!";
            throw new IllegalArgumentException(message);
        }

        if (operand1.base() != operand2.base()) {

            String message = "The specified numbers are of different bases!";
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Checks the specifiecd parameters.
     *
     * @param integer
     *        an integer
     */
    public static void checkIntegerIgnoreNull(Number integer) {

        if (integer.isFraction()) {

            String message = "An integer is requred!";
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Checks the specifiecd parameters.
     *
     * @param operand1
     *        an operand
     * @param operand2
     *        an operand
     * @param operand3
     *        an operand
     */
    public static void checkParameters(Number operand1, Number operand2, Number operand3) {

        if (operand1 == null) {

            String message = "The first operand is null!";
            throw new IllegalArgumentException(message);
        }

        if (operand2 == null) {

            String message = "The second operand is null!";
            throw new IllegalArgumentException(message);
        }

        if (operand3 == null) {

            String message = "The third operand is null!";
            throw new IllegalArgumentException(message);
        }

        if (operand1.base() != operand2.base()) {

            String message = "The specified numbers are of different bases!";
            throw new IllegalArgumentException(message);
        }

        if (operand1.base() != operand3.base()) {

            String message = "The specified numbers are of different bases!";
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Checks the specified parameter.
     *
     * @param number
     *        a number
     *
     * @return the specified number
     */
    public static Number checkParameter(Number number) {

        if (number == null) {

            String message = "No number (null) was specified!";
            throw new IllegalArgumentException(message);
        }

        return number;
    }

    /**
     * Checks the specified parameter.
     *
     * @param fraction
     *        a fraction
     *
     * @return the specified fraction
     */
    public static Fraction checkParameter(Fraction fraction) {

        if (fraction == null) {

            String message = "No fraction (null) was specified!";
            throw new IllegalArgumentException(message);
        }

        return fraction;
    }

    /**
     * Checks the specified parameter.
     *
     * @param number
     *        a number
     */
    public static void checkInteger(Number number) {

        if (number == null) {

            String message = "No number (null) was specified!";
            throw new IllegalArgumentException(message);
        }

        if (!number.isInteger()) {

            String message = "The specified number is not an integer!";
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Checks the specified parameters.
     *
     * @param operand1
     *        an operand
     * @param operand2
     *        an operand
     */
    public static void checkParameters(Fraction operand1, Fraction operand2) {

        if (operand1 == null) {

            throw new IllegalArgumentException("No expression (null) was specified!");
        }

        if (operand2 == null) {

            throw new IllegalArgumentException("No expression (null) was specified!");
        }

        if (operand1.base() != operand2.base()) {

            throw new DigitBaseMismatchException(operand1, operand2);
        }
    }

    /**
     * Checks the specified parameters.
     *
     * @param operand1
     *        an operand
     * @param operand2
     *        an operand
     */
    public static void checkParameters(Fraction operand1, Number operand2) {

        if (operand1 == null) {

            throw new IllegalArgumentException("No expression (null) was specified!");
        }

        if (operand2 == null) {

            throw new IllegalArgumentException("No number (null) was specified!");
        }

        if (operand1.base() != operand2.base()) {

            throw new DigitBaseMismatchException(operand1, operand2);
        }
    }

}
