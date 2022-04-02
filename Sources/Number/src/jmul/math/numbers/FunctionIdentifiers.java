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


import jmul.singletons.Identifier;


/**
 * This enumeration contains various function identifiers.
 *
 * @author Kristian Kutin
 */
public enum FunctionIdentifiers implements Identifier {


    DIGIT_ADDITION(),
    DIGIT_COMPLEMENT(),
    
    STANDARD_NOTATION(),
    SCIENTIFIC_NOTATION(),
    
    COMPARATOR(),;


    /**
     * Returns the length of this identifier string.
     *
     * @return a length
     */
    @Override
    public int length() {

        return toString().length();
    }

    /**
     * Returns the character at the specified index position.
     *
     * @param index
     *        an index position
     *
     * @return a character
     */
    @Override
    public char charAt(int index) {

        return toString().charAt(index);
    }

    /**
     * Returns a subsequence within this identifier string according to the specified index positions.
     *
     * @param start
     *        a start index
     * @param end
     *        a end index (not included)
     *
     * @return a substring
     */
    @Override
    public CharSequence subSequence(int start, int end) {

        return toString().subSequence(start, end);
    }

}
