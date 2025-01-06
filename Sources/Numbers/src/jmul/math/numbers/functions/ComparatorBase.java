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

package jmul.math.numbers.functions;


/**
 * A base implementation for a comparator implementation.
 *
 * @author Kristian Kutin
 */
public class ComparatorBase {

    /**
     * A constant value indicating that a number is greater than another number.
     */
    public static final int GREATER_THAN;

    /**
     * A constanjt indicating that a number is equal to another number.
     */
    public static final int EQUALS;

    /**
     * A constant indicating that a number is lesser than another number.
     */
    public static final int LESSER_THAN;

    /*
     * The static initializer.
     */
    static {

        GREATER_THAN = 1;
        EQUALS = 0;
        LESSER_THAN = -1;
    }

    /**
     * The default constructor.
     */
    public ComparatorBase() {

        super();
    }

}
