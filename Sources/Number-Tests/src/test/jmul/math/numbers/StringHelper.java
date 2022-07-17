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

package test.jmul.math.numbers;


/**
 * A utility class for manipulating strings.
 *
 * @author Kristian Kutin
 */
public final class StringHelper {

    /**
     * The default constructor.
     */
    private StringHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Returns a copy of the specified string where a leading plus character is removed.
     *
     * @param string
     *        a string containing a number
     *
     * @return a normalized string
     */
    public static String removeSign(String string) {

        String sign = "+";
        if (string.startsWith(string)) {

            return string.replace(sign, "");
        }

        return string;
    }

}
