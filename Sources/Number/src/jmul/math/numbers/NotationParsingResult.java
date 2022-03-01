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


import java.util.regex.Matcher;

import jmul.math.numbers.notations.Notations;


/**
 * A custom return type which includes a notation information and a matcher for regular expressions.
 *
 * @author Kristian Kutin
 */
public class NotationParsingResult {

    /**
     * A notation information.
     */
    private final Notations notation;

    /**
     * A matcher for regular expressions.
     */
    private final Matcher matcher;

    /**
     * Creates a new instance of this custom return type.
     *
     * @param notation
     *        a notation information
     * @param matcher
     *        a matcher for regular expressions
     */
    public NotationParsingResult(Notations notation, Matcher matcher) {

        this.notation = notation;
        this.matcher = matcher;
    }

    /**
     * Returns notation informations.
     *
     * @return a notation information
     */
    public Notations notation() {

        return notation;
    }

    /**
     * Returns a matcher for regular expressions.
     *
     * @return a matcher for regular expressions.
     */
    public Matcher matcher() {

        return matcher;
    }

}
