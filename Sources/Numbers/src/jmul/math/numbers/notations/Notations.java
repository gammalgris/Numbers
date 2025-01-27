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

package jmul.math.numbers.notations;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * This enumeration contains various number notations.
 *
 * @author Kristian Kutin
 *
 * @deprecated Replace the digits with placeholders. The caller should resolve the placeholders.
 */
@Deprecated
public enum Notations implements Notation {


    STANDARD_NOTATION("^(?<SIGN>[+-]{0,1})[0]*(?<LEFT>(([1-9a-zA-Z][0-9a-zA-Z]*)|([0]))){1}([,.](?<RIGHT>[0]|[0-9a-zA-Z]*[1-9a-zA-Z])([0]+){0,1}){0,1}$",
                      "SIGN", "LEFT", "RIGHT"),
    SCIENTIFIC_NOATATION("^(?<SIGN>[+-]){0,1}(?<MANTISSA>[0-9a-zA-Z][.,][0-9a-zA-Z]*[1-9a-zA-Z]){1}[0]*[eE](?<SIGNEXPONENT>[+-]){0,1}(?<EXPONENT>[0-9a-zA-Z][0-9a-zA-Z]*){1}$",
                         "SIGN", "MANTISSA", "SIGNEXPONENT", "EXPONENT"), ;


    /**
     * A regex string.
     */
    private final String regex;

    /**
     * The names of all named capturing groups which are defined within the regex string.
     */
    private final List<String> namedCapturingGroups;

    /**
     * Creates a new enumeration element according to the specified parameters.
     *
     * @param regex
     *        a regex string
     * @param namedCapturingGroups
     *        the names of all named capturing groups which are defined within the regex string
     */
    private Notations(String regex, String... namedCapturingGroups) {

        this.regex = regex;
        this.namedCapturingGroups = Collections.unmodifiableList(Arrays.asList(namedCapturingGroups));
    }

    /**
     * Returns the regex string which describes this notation.
     *
     * @return a regex string
     */
    @Override
    public String regex() {

        return regex;
    }

    /**
     * Returns a list of named capturing groups which are defined within the regex string.
     */
    public List<String> namedCapturingGroups() {

        return namedCapturingGroups;
    }

}
