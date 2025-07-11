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

package jmul.math.operations;


import jmul.math.fractions.Fraction;
import jmul.math.functions.repository.FunctionIdentifier;
import jmul.math.numbers.Number;


/**
 * This interface describes various arithmetic operations.<br>
 * <br>
 * <i>Note:<br>
 * This interface describes something like a fluent interface for this custom number implementation and
 * is intended to allow concatenation of arithmetic operations.</i>
 *
 * @author Kristian Kutin
 */
public interface ArithmeticNumberOperations {

    /*
     * Unary arithmetic operations.
     */

    /**
     * Negates this number (i.e. changes the sign).
     *
     * @return a number
     */
    Number negate();

    /**
     * Calculates and returns the complement of this number.
     *
     * @return the complement of this number
     */
    Number complement();

    /**
     * Calculates the faculty for this number.
     *
     * @return the factorial for this number
     */
    Number factorial();

    /**
     * Calculates the square for this number.
     *
     * @return a number
     */
    Number square();

    /**
     * Calculates the square root for this number.
     *
     * @return a number
     */
    Number squareRoot();

    /**
     * Halves this number.
     *
     * @return a number
     */
    Number halving();

    /**
     * Doubles this number.
     *
     * @return a number
     */
    Number doubling();

    /**
     * Increments this number by one.
     *
     * @return a number
     */
    Number inc();

    /**
     * Decrements this number by one.
     *
     * @return a number
     */
    Number dec();

    /*
     * Binary arithmetic operations.
     */

    /**
     * Adds this number and the specified number.
     *
     * @param n
     *        a number
     *
     * @return a number
     */
    Number add(Number n);

    /**
     * Adds this number and the specified fraction.
     *
     * @param f
     *        a fraction
     *
     * @return a fraction
     */
    Fraction add(Fraction f);

    /**
     * Substracts the specified number from this number.
     *
     * @param n
     *        a number
     *
     * @return a number
     */
    Number subtract(Number n);

    /**
     * Substracts the specified fraction from this number.
     *
     * @param f
     *        a fraction
     *
     * @return a fraction
     */
    Fraction subtract(Fraction f);

    /**
     * Multiplies this number with the specified number.
     *
     * @param n
     *        a number
     *
     * @return a number
     */
    Number multiply(Number n);

    /**
     * Multiplies this number with the specified fraction.
     *
     * @param f
     *        a fraction
     *
     * @return a number
     */
    Fraction multiply(Fraction f);

    /**
     * Divides this number by the specified number.
     *
     * @param n
     *        a number
     *
     * @return a fraction (e.g. a quotient, a mixed fraction or integer)
     */
    Fraction divide(Number n);

    /**
     * Divides this number by the specified number.
     *
     * @param algorithm
     *        the identifier for an algorithm
     * @param n
     *        a number
     *
     * @return the quotient
     */
    Number divide(FunctionIdentifier algorithm, Number n);

    /**
     * Divides this number by the specified number.
     *
     * @param algorithm
     *        the identifier for an algorithm
     * @param n
     *        a number
     * @param decimalPlaces
     *        the number of decimal places retained after cutting the fraction part
     *
     * @return the quotient
     */
    Number divide(FunctionIdentifier algorithm, Number n, Number decimalPlaces);

    /**
     * Divides this number by the specified fraction.
     *
     * @param f
     *        a fraction
     *
     * @return a fraction (e.g. a quotient, a mixed fraction or integer)
     */
    Fraction divide(Fraction f);

    /**
     * Divides this number by the specified number and returns the remainder of the division.
     *
     * @param n
     *        an integer
     *
     * @return the remainder of the division
     */
    Number modulo(Number n);

    /**
     * Divides this number by the specified number and returns the result of the division.
     *
     * @param n
     *        an integer
     *
     * @return the result of the division
     */
    Number diviso(Number n);

    /**
     * Compares this number and the specified fraction and returns the greater number (as fraction).
     *
     * @param f
     *        a number
     *
     * @return a fraction
     */
    Fraction max(Fraction f);

    /**
     * Compares this number and the specified number and returns the greater number.
     *
     * @param n
     *        a number
     *
     * @return a number
     */
    Number max(Number n);

    /**
     * Compares this number and the specified fraction and returns the smaller number (as fraction).
     *
     * @param f
     *        a fraction
     *
     * @return a fraction
     */
    Fraction min(Fraction f);

    /**
     * Compares this number and the specified number and returns the smaller number.
     *
     * @param n
     *        a number
     *
     * @return a number
     */
    Number min(Number n);

    /**
     * Takes this number as base and exponentiates it with the specified number.
     *
     * @param n
     *        a number
     *
     * @return a number
     */
    Number exponentiate(Number n);

    /**
     * Takes the specified number as base and claculates the logarithm for this number.
     *
     * @param n
     *        a number
     *
     * @return a number
     */
    Number log(Number n);

    /**
     * Returns the reciprocal of this number.
     *
     * @return a fraction
     */
    Fraction reciprocal();

    /**
     * Round this number down to the nearest integer that doesn't exceed this number.
     *
     * @return the nearest integer that doesn't exceed this number
     */
    Number roundDown();

    /**
     * Round this number up to the nearest integer that is not less than this number.
     *
     * @return the nearest integer that is not less than this number
     */
    Number roundUp();

    /**
     * Shorten the precision of this number according to the specified decimal places.
     *
     * @param decimalPlaces
     *        the number of decimal places remainign after rounding
     *
     * @return a rounded number
     */
    Number round(Number decimalPlaces);

    /**
     * Shorten the precision of this number according to the specified decimal places.
     *
     * @param decimalPlaces
     *        the number of decimal places remaining after rounding
     *
     * @return a shortened number according to the specified precision
     */
    Number round(int decimalPlaces);

    /**
     * Shorten the precision of this number according to the specified decimal places.
     *
     * @param algorithm
     *        the identifier for an algorithm
     * @param decimalPlaces
     *        the number of decimal places remaining after rounding
     *
     * @return a shortened number according to the specified precision
     */
    Number round(FunctionIdentifier algorithm, int decimalPlaces);

    /**
     * Shorten the precision of this number according to the specified decimal places.
     *
     * @param algorithm
     *        the identifier for an algorithm
     * @param decimalPlaces
     *        the number of decimal places remaining after rounding
     *
     * @return a shortened number according to the specified precision
     */
    Number round(FunctionIdentifier algorithm, Number decimalPlaces);

    /*
     * Ternary arithmetic operations.
     */

}
