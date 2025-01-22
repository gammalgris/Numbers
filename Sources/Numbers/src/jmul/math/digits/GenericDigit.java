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

package jmul.math.digits;


import java.util.Comparator;

import jmul.math.functions.FunctionSingletons;
import jmul.math.hash.HashHelper;
import jmul.math.functions.repository.FunctionIdentifiers;
import jmul.math.operations.EqualityFunction;


/**
 * An implementation of a digit for a positional numeral system. This implementation
 * is immutable.
 *
 * @author Kristian Kutin
 *
 * @see Digit
 * @see PositionalNumeralSystem
 * @see PositionalNumeralSystems
 */
class GenericDigit implements Digit {

    /**
     * A constant value.
     */
    private static final int ZERO;

    /*
     * The static initializer.
     */
    static {

        ZERO = 0;
    }

    /**
     * The symbol associated with this digit.
     */
    private final char symbol;

    /**
     * The base of a positional numeral system.
     */
    private final int base;

    /**
     * The corresponding ordinal number for this digit.
     */
    private final int ordinal;

    /**
     * Creates a new digit according to the specified parameters.
     *
     * @param base
     *        the base of a positional numeral system
     * @param ordinal
     *        the corresponding ordinal number
     * @param symbol
     *        the symbol for this digit
     */
    public GenericDigit(int base, int ordinal, char symbol) {

        super();

        this.base = base;
        this.ordinal = ordinal;
        this.symbol = symbol;
    }

    /**
     * Returns the symbol associated with this digit.
     *
     * @return a symbol, i.e. a single character
     */
    @Override
    public char symbol() {

        return symbol;
    }

    /**
     * Returns the base of the positional numeral system.
     *
     * @return a base
     */
    @Override
    public int base() {

        return base;
    }

    /**
     * Returns the ordinal number for this digit,
     *
     * @return an ordinal number
     */
    @Override
    public int ordinal() {

        return ordinal;
    }

    /**
     * Checks if this digit is zero.
     *
     * @return <code>true</code> if this digit is zero, else <code>false</code>
     */
    @Override
    public boolean isZero() {

        return ordinal == ZERO;
    }

    /**
     * Returns a string representation for this digit.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return String.valueOf(symbol);
    }

    /**
     * Checks the equality of this number and the specified object (i.e. number).
     *
     * @param o
     *        another object (i.e. number)
     *
     * @return <code>true</code> if this number is euqal to the specified number, else <code>false</code>
     */
    @Override
    public boolean equals(Object o) {

        if (o instanceof Digit) {

            Digit other = (Digit) o;
            EqualityFunction<Digit> function =
                (EqualityFunction<Digit>) FunctionSingletons.getFunction(FunctionIdentifiers.DIGIT_EQUALITY_FUNCTION);
            boolean result = function.equals(this, other);

            return result;
        }

        return false;
    }

    /**
     * Calculates a hash code for this digit.
     *
     * @return a hash code
     */
    @Override
    public int hashCode() {

        return HashHelper.calculateHashCode(Digit.class, base, ordinal);
    }

    /**
     * Compares this digit with the specified digit regardign the natural order.
     *
     * @param d
     *        a digit
     *
     * @return <code>1</code>, <code>0</code> or <code>-1</code> if this digit is greater, equal
     *         or lesser than the specified digit.
     */
    @Override
    public int compareTo(Digit d) {

        Comparator<Digit> function =
            (Comparator<Digit>) FunctionSingletons.getFunction(FunctionIdentifiers.DIGIT_COMPARATOR_FUNCTION);
        int result = function.compare(this, d);

        return result;
    }

}
