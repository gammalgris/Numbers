/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2024  Kristian Kutin
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

package jmul.math.operations;


/**
 * This is a wrapper class for results of arithmetic operations.<br>
 * <br>
 * <i>Note:<br>
 * This implmentation doesn't allow <code>null</code> values.</i>
 *
 * @author Kristian Kutin
 *
 * @param <T>
 *        the result type
 */
public class ResultWithRemainder<T> extends Result<T> {

    /**
     * The remainder (i.e. part of the result which is carried over for another calculation)
     */
    private final T remainder;

    /**
     * Creates a new result according to the specified parameters.
     *
     * @param result
     *        the result
     * @param remainder
     *        the remainder
     */
    public ResultWithRemainder(T result, T remainder) {

        super(result);

        checkRemainder(remainder);

        this.remainder = remainder;
    }

    /**
     * Checks the specified remainder.
     *
     * @param remainder
     *        a remainder
     */
    private void checkRemainder(T remainder) {

        if (remainder == null) {

            String message = "The remainder is null! Null values are not allowed.";
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Returns the remainder.
     *
     * @return the remainder
     */
    public T remainder() {

        return remainder;
    }

    /**
     * Returns a result summary.
     *
     * @return a summary
     */
    @Override
    public String toString() {

        String summary = String.format("result=%s; remainder=%s", result(), remainder());

        return summary;
    }

}
