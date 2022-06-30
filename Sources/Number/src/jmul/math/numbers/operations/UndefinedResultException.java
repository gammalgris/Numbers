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

package jmul.math.numbers.operations;


/**
 * This exception is used in cases where an arithmetic calculation would lead to an undefined result.
 *
 * @author Kristian Kutin
 */
public class UndefinedResultException extends RuntimeException {

    /**
     * The default constructor.
     */
    public UndefinedResultException() {

        super();
    }

    /**
     * Creates a new exception according to the specified parameters.
     *
     * @param message
     *        an exception message
     */
    public UndefinedResultException(String message) {

        super(message);
    }

    /**
     * Creates a new exception according to the specified parameters.
     *
     * @param cause
     *        an exception which was catched and is rethrown
     */
    public UndefinedResultException(Throwable cause) {

        super(cause);
    }

    /**
     * Creates a new exception according to the specified parameters.
     *
     * @param message
     *        an exception message
     * @param cause
     *        an exception which was catched and is rethrown
     */
    public UndefinedResultException(String message, Throwable cause) {

        super(message, cause);
    }

}
