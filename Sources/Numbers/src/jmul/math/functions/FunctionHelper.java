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

package jmul.math.functions;


import jmul.math.Math;
import jmul.math.functions.conditions.ConditionFunctionEntry;
import jmul.math.functions.conditions.GreaterOrEqualCondition;
import jmul.math.functions.conditions.LesserThanCondition;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;


/**
 * A utility class for creating functions.
 *
 * @author Kristian Kutin
 */
public final class FunctionHelper {

    /**
     * The default constructor.
     */
    private FunctionHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Creates a new monomial according to the specified parameters.
     *
     * @param base
     *        a number base
     * @param coefficientString
     *        a number string representing a coefficient
     * @param exponentString
     *        a number string representing an exponent
     *
     * @return a monomial function
     */
    public static Function createMonomialFunction(int base, String coefficientString, String exponentString) {

        return createMonomialFunction(createNumber(base, coefficientString), createNumber(base, exponentString));
    }

    /**
     * Creates a new monomial according to the specified parameters.
     *
     * @param coefficient
     *        a coefficient
     * @param exponent
     *        an exponent
     *
     * @return a monomial function
     */
    public static Function createMonomialFunction(Number coefficient, Number exponent) {

        return new MonomialFunctionImpl(coefficient, exponent);
    }

    /**
     * Creates a random monomial function with random coefficient and exponent.
     *
     * @param base
     *        a number base
     *
     * @return a monomial function
     */
    public static Function createRandomMonomialFunction(int base) {

        Number coefficient = Math.random(base);
        coefficient = coefficient.shiftRight();
        if (removeFractionPart()) {

            coefficient = coefficient.removeFractionPart();
        }

        Number exponent = Math.random(base);
        exponent = exponent.shiftRight();
        exponent = exponent.removeFractionPart();

        return createMonomialFunction(coefficient, exponent);
    }

    /**
     * Randomly determines whether to remove the fraction part of a number or not.
     *
     * @return <code>true</code> or <code>false</code>
     */
    private static boolean removeFractionPart() {

        final boolean[] values = { true, false };

        int randomIndex = (int) (java.lang.Math.random() * 2D);

        return values[randomIndex];
    }

    /**
     * Creates a new polynomial function according to the specified parameters.
     *
     * @param base
     *        a number base
     * @param coefficientStrings
     *        all coefficient number strings (in ascending order c<sub>0</sub>, c<sub>1</sub>, c<sub>2</sub>, ..., c<sub>n</sub>)
     *
     * @return a function
     */
    public static Function createPolynomialFunction(int base, String... coefficientStrings) {

        if (coefficientStrings == null) {

            throw new IllegalArgumentException("No coefficients (null) were specified!");
        }

        int length = coefficientStrings.length;
        Number[] coefficients = new Number[length];

        for (int index = 0; index < length; index++) {

            Number n = createNumber(base, coefficientStrings[index]);
            coefficients[index] = n;
        }

        return new PolynomialFunctionImpl(coefficients);
    }

    /**
     * Creates a random polynomial function with random coefficients.
     *
     * @param base
     *        a number base
     *
     * @return a function
     */
    public static Function createRandomPolynomialFunction(int base) {

        int maxNumbers = randomInteger(4);

        Number[] coefficients = new Number[maxNumbers];
        for (int index = 0; index < maxNumbers; index++) {

            Number coefficient = Math.random(base);
            if (removeFractionPart()) {

                coefficient = coefficient.removeFractionPart();
            }

            coefficients[index] = coefficient;
        }

        return new PolynomialFunctionImpl(coefficients);
    }

    /**
     * Returns a random integer (i.e. 1 &lt;= random number &lt;= max)
     *
     * @param max
     *        a maximum value
     *
     * @return a random integer
     */
    private static int randomInteger(int max) {

        if (max < 2) {

            throw new IllegalArgumentException("The maximum number must be greater than 1!");
        }

        return ((int) (java.lang.Math.random() * ((double) (max - 1)) + 1D));
    }

    /**
     * Creates a partial function according to the specified parameters.
     *
     * @param entries
     *        condition function entries
     *
     * @return a partial function
     */
    public static Function createPartialFunction(ConditionFunctionEntry... entries) {

        if (entries == null) {

            throw new IllegalArgumentException("No entries (null) were specified!");
        }

        return new PartialFunctionImpl(entries);
    }

    /**
     * Creates a partial function with two functions.
     *
     * @param base
     *        a number base
     *
     * @return a random partial function
     */
    public static Function createRandomPartialFunction(int base) {

        Number threshold = Math.random(base);

        ConditionFunctionEntry[] entries = {
            new ConditionFunctionEntry(new LesserThanCondition(threshold), createRandomPolynomialFunction(base)),
            new ConditionFunctionEntry(new GreaterOrEqualCondition(threshold), createRandomPolynomialFunction(base))
        };

        return new PartialFunctionImpl(entries);
    }

    /**
     * Creates a random function.
     *
     * @param base
     *        a number base
     *
     * @return a random function
     */
    public static Function createRandomFunction(int base) {

        int index = randomIndex(3);

        switch (index) {

        case 0:
            return createRandomMonomialFunction(base);
        case 1:
            return createRandomPolynomialFunction(base);
        case 2:
            return createRandomPartialFunction(base);
        }

        throw new UnsupportedOperationException("There is no corresponding rule for a case!");
    }

    /**
     * Randomly determines an index (i.e. 0 &lt;= random index &lt; max).
     *
     * @param max
     *        the maximum threshold for an index
     *
     * @return a random idnex
     */
    private static int randomIndex(int max) {

        return (int) (java.lang.Math.random() * ((double) max));
    }

}
