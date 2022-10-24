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

package jmul.math.numbers.digits;


import java.util.Comparator;

import jmul.math.hash.HashHelper;
import jmul.math.numbers.FunctionIdentifiers;
import static jmul.math.numbers.ParameterHelper.checkBase;
import static jmul.math.numbers.ParameterHelper.checkOrdinal;
import jmul.math.numbers.functions.EqualityFunction;

import jmul.singletons.FunctionSingletons;


/**
 * A simple implementation of a digit for any numeral system.<br>
 * <br>
 * <i>Note:<br>
 * There is no need to implement a custom version of the {@link #equals} and
 * {@link #hashCode} methods. Each digit instance is unique and is guaranteed to exist
 * only once (see {@link NumeralSystems}). When creating new number objects the existing
 * digits are referenced. Thus the default {@link #equals} method and comparison by <code>==</code>
 * should both work for comparison.</i>
 *
 * @author Kristian Kutin
 *
 * @see NumeralSystems
 */
class DigitImpl implements Digit {

    /**
     * The symbol associated with this digit.
     */
    private final char symbol;

    /**
     * The base for this digit. Allowed values are <code>0 &lt; x &lt;= 64</code>.<br>
     * <br>
     * <i>Note:<br>
     * This limitation is introduced because currently only digits and letters (lower and upper case)
     * are used as symbols (10 digits plus 26 lower case letters plus 26 upper case letters).</i>
     */
    private final int base;

    /**
     * The corresponding ordinal number for this digit. Allowed values are <code>0 &lt;= x &lt;= 64</code>.
     * The ordinal number should represent tha natural ordering of the digits within a numeral system.
     */
    private final int ordinal;

    /**
     * Creates a new digit according to the specified parameters.
     *
     * @param symbol
     *        the symbol for this digit
     * @param base
     *        the base for this digit
     * @param ordinal
     *        the corresponding ordinal number
     */
    public DigitImpl(char symbol, int base, int ordinal) {

        super();

        this.symbol = symbol;
        this.base = checkBase(base);
        this.ordinal = checkOrdinal(ordinal);
    }

    /**
     * Returns the symbol associated with this digit.
     *
     * @return a symbol
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

        return ordinal == 0;
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

        if (o == null) {

            return false;
        }

        if (o instanceof Digit) {

            EqualityFunction<Digit> function =
                (EqualityFunction<Digit>) FunctionSingletons.getFunction(FunctionIdentifiers.DIGIT_EQUALITY_FUNCTION);
            boolean result = function.equals(this, (Digit) o);

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
