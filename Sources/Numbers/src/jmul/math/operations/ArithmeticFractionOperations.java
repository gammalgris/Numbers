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
package jmul.math.operations;


import jmul.math.fractions.Fraction;
import jmul.math.numbers.Number;


/**
 * This interface describes various arithmetic operations.<br>
 * <br>
 * <i>Note:<br>
 * This interface describes a fluent interface for fraction implementation and
 * is intended to allow concatenation of arithmetic operations.</i>
 *
 * @author Kristian Kutin
 */
public interface ArithmeticFractionOperations {

    /*
     * Unary arithmetic operations.
     */

    /**
     * Negates this fraction (i.e. changes the sign).
     *
     * @return a fraction
     */
    Fraction negate();

    /**
     * Calculates the square root for this fraction.
     *
     * @return a fraction
     */
    Fraction squareRoot();

    /**
     * Halves this fraction.
     *
     * @return a fraction
     */
    Fraction halving();

    /**
     * Doubles this fraction.
     *
     * @return a fraction
     */
    Fraction doubling();

    /**
     * Increments this fraction by one.
     *
     * @return a fraction
     */
    Fraction inc();

    /**
     * Decrements this fraction by one.
     *
     * @return a fraction
     */
    Fraction dec();

    /*
     * Binary arithmetic operations.
     */

    /**
     * Adds this fraction and the specified number.
     *
     * @param n
     *        a number
     *
     * @return a fraction
     */
    Fraction add(Number n);

    /**
     * Adds this fraction and the specified fraction.
     *
     * @param f
     *        a number
     *
     * @return a fraction
     */
    Fraction add(Fraction f);

    /**
     * Subtracts the specified number from this fraction.
     *
     * @param n
     *        a number
     *
     * @return a fraction
     */
    Fraction subtract(Number n);

    /**
     * Subtracts the specified fractionr from this fraction.
     *
     * @param f
     *        a fraction
     *
     * @return a fraction
     */
    Fraction subtract(Fraction f);

    /**
     * Multiplies this fraction with the specified number.
     *
     * @param n
     *        a number
     *
     * @return a fraction
     */
    Fraction multiply(Number n);

    /**
     * Multiplies this fraction with the specified fraction.
     *
     * @param f
     *        a fraction
     *
     * @return a fraction
     */
    Fraction multiply(Fraction f);

    /**
     * Divides this fraction by the specified number.
     *
     * @param n
     *        a number
     *
     * @return a fraction
     */
    Fraction divide(Number n);

    /**
     * Divides this fraction by the specified fraction.
     *
     * @param f
     *        a fraction
     *
     * @return a fraction
     */
    Fraction divide(Fraction f);

    /**
     * Compares this fraction and the specified number and returns the greater number (as fraction).
     *
     * @param n
     *        a number
     *
     * @return a fraction
     */
    Fraction max(Number n);

    /**
     * Compares this fraction and the specified fraction and returns the greater number (as fraction).
     *
     * @param f
     *        a fraction
     *
     * @return a fraction
     */
    Fraction max(Fraction f);

    /**
     * Compares this fraction and the specified number and returns the smaller number (as fraction).
     *
     * @param n
     *        a number
     *
     * @return a fraction
     */
    Fraction min(Number n);

    /**
     * Compares this fraction and the specified fraction and returns the smaller number (as fraction).
     *
     * @param f
     *        a fraction
     *
     * @return a fraction
     */
    Fraction min(Fraction f);

    /**
     * Performs an exponentiation with this fraction as the base and the specified number as the exponent.
     *
     * @param n
     *        a number
     *
     * @return a fraction
     */
    Fraction exponentiate(Number n);

    /**
     * Takes the specified number as base and calculates the logarithm for this number.
     *
     * @param n
     *        a number
     *
     * @return a fraction
     */
    Fraction log(Number n);

    /**
     * Returns the reciprocal of this fraction.
     *
     * @return a fraction
     */
    Fraction reciprocal();

    /*
     * Ternary arithmetic operations.
     */

}
