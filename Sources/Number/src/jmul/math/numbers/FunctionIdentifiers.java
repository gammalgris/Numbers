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

package jmul.math.numbers;


import jmul.math.functions.FunctionIdentifier;


/**
 * This enumeration class contains identifiers for various functions.
 *
 * @author Kristian Kutin
 */
public enum FunctionIdentifiers implements FunctionIdentifier {


    STANDARD_NOTATION_FUNCTION,
    SCIENTIFIC_NOTATION_FUNCTION,

    STANDARD_NOTATION_PARSER,
    SCIENTIFIC_NOTATION_PARSER,

    ADD_DIGITS_FUNCTION,
    DIGIT_COMPLEMENT_FUNCTION,
    HALVING_DIGIT_FUNCTION,

    SHIFT_LEFT_FUNCTION,
    SHIFT_RIGHT_FUNCTION,

    MAX_FUNCTION,
    MIN_FUNCTION,

    DOUBLING_NUMBER_FUNCTION,
    HALVING_NUMBER_FUNCTION,
    TRUNCATE_NUMBER_FUNCTION,

    DIGIT_EQUALITY_FUNCTION,
    DIGIT_COMPARATOR_FUNCTION,

    NEGATE_NUMBER_FUNCTION,
    NUMBER_COMPLEMENT_FUNCTION,

    NUMBER_COMPARATOR_FUNCTION,
    NUMBER_EQUALITY_FUNCTION,

    ADDITION_FUNCTION,
    SUBTRACTION_FUNCTION,
    MULTIPLICATION_FUNCTION,
    DIVISION_FUNCTION,
    MODULO_FUNCTION,
    DIVISO_FUNCTION,

    INCREMENT_FUNCTION,
    DECREMENT_FUNCTION,

    EVEN_NUMBER_FUNCTION,
    ODD_NUMBER_FUNCTION,

    REBASE_FUNCTION;


    /**
     * The default constructor.
     */
    private FunctionIdentifiers() {
    }

    /**
     * Returns the length this char sequence.
     *
     * @return a length
     */
    @Override
    public int length() {

        return toString().length();
    }

    /**
     * Returns the character at the specified position.
     *
     * @param index
     *        an index (i.e. zero or positive number up to the length of the string)
     *
     * @return a character
     */
    @Override
    public char charAt(int index) {

        return toString().charAt(index);
    }

    /**
     * Returns a subsequence at the specified position.
     *
     * @param start
     *        a start index
     * @param end
     *        an end index
     *
     * @return a substring
     */
    @Override
    public CharSequence subSequence(int start, int end) {

        return toString().subSequence(start, end);
    }

}
