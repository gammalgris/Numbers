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

package jmul.patterns;


import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A utility class for pattern matching.
 *
 * @author Kristian Kutin
 */
public final class PatternHelper {

    /**
     * The static constructor.
     */
    private PatternHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Compiles the specified regular expression and matches it against the specified string.
     *
     * @param aRegex
     *        a regular expression
     * @param aString
     *        a string
     *
     * @return a matcher (i.e. matching results)
     */
    public static Matcher match(CharSequence aRegex, CharSequence aString) {

        Pattern pattern = Pattern.compile(String.valueOf(aRegex));
        Matcher matcher = pattern.matcher(String.valueOf(aString));

        return matcher;
    }

}
