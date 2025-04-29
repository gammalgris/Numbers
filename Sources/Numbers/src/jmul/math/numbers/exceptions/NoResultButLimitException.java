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

package jmul.math.numbers.exceptions;


import jmul.math.numbers.Number;


/**
 * A custom exception for cases when an illegal ordinal is used.
 *
 * @author Kristian Kutin
 */
public class NoResultButLimitException extends RuntimeException {

    /**
     * A number representing a limit.
     */
    private final Number limit;

    /**
     * Creates a new exception according to the specified parameters.
     *
     * @param limit
     *        a number representing a limit
     */
    public NoResultButLimitException(Number limit) {

        super(createMessage(checkParameter(limit)));

        this.limit = limit;
    }

    /**
     * Checks the specified parameter.
     *
     * @param limit
     *        a number representign a limit
     *
     * @return the specified parameter
     */
    private static Number checkParameter(Number limit) {

        if (limit == null) {

            throw new IllegalArgumentException("No parameter (null) was specified!");
        }

        return limit;
    }

    /**
     * Returns an exception message.
     *
     * @param limit
     *        a limit, which is part of the exception message
     *
     * @return an exception message
     */
    private static String createMessage(Number limit) {

        return String.format("The operation yields no result. A limit analysis yields the limit %s. ", limit);
    }

    /**
     * Returns the limit which represent the actual result.
     *
     * @return a limit
     */
    public Number limit() {

        return limit;
    }

}
