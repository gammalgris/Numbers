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

package jmul.math.fractions;


import java.util.SortedSet;

import jmul.math.numbers.AbstractNumber;
import jmul.math.numbers.Number;
import jmul.math.operations.ArithmeticFractionOperations;
import jmul.math.operations.FractionComparisons;
import jmul.math.operations.NumberComparisons;


/**
 * This interface represents a fraction. A fraction knows several notations (i.e. fraction or mixed
 * fraction) or can be translated into an integer value.
 *
 * @author Kristian Kutin
 */
public interface Fraction extends Comparable, NumberComparisons, FractionComparisons, ArithmeticFractionOperations,
                                  AbstractNumber {

    /**
     * Evaluates this fraction and returns a number which is equivalent to the fraction.
     *
     * @return a number
     */
    Number evaluate();

    /**
     * Evaluates this fraction and returns a number which is equivalent to the fraction.
     *
     * @param decimalPlaces
     *        the number of decimal places retained after cutting the fraction part
     *
     * @return a number
     */
    Number evaluate(Number decimalPlaces);

    /**
     * Normalizes this fraction (i.e. integrates an integer part into the numerator).
     *
     * @return a fraction
     */
    Fraction normalizedFraction();

    /**
     * Normalizes this fraction (i.e. extracts the integer part from the numerator).
     *
     * @return a fraction
     */
    Fraction normalizedMixedFraction();

    /**
     * Checks if this fraction has an integer part.
     *
     * @return <code>true</code> if this fraction has an integer part, else <code>false</code>
     */
    boolean hasIntegerPart();

    /**
     * Returns the integer part of this fraction.
     *
     * @return a number
     */
    Number integerPart();

    /**
     * Checks if this fraction has a numerator.
     *
     * @return <code>true</code> if this fraction has a numerator, else <code>false</code>
     */
    boolean hasNumerator();

    /**
     * Returns the numerator of this fraction.
     *
     * @return a number
     */
    Number numerator();

    /**
     * Checks if this fraction has a denominator.
     *
     * @return <code>true</code> if this fraction has a denominator, else <code>false</code>
     */
    boolean hasDenominator();

    /**
     * Returns the denominator of this fraction.
     *
     * @return a number
     */
    Number denominator();

    /**
     * Reduce fraction.
     *
     * @return a fraction
     */
    Fraction reduce();

    /**
     * Translates this fraction into a number of the specified base.
     *
     * @param base
     *        the new base
     *
     * @return a fraction
     */
    Fraction rebase(int base);

    /**
     * Returns the absolute value of this fraction.
     *
     * @return the absolute value
     */
    Fraction absoluteValue();

    /**
     * Determines the common divisors for this fraction (i.e. numerator and denominator). The result set contains
     * divisors greater than one.
     *
     * @return a set of divisors or an empty set if there are no common divisors
     */
    SortedSet<Number> commonDivisorSet();

    /**
     * Determines the common prime factors for this fraction (i.e. numerator and denominator). The result set contains
     * the prime factors.
     *
     * @return a set of prime factors or an empty set if there are no common prime factors
     */
    SortedSet<Number> commonPrimeFactors();

}
