/*
 * SPDX-License-Identifier: GPL-3.0
 * 
 * 
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2017  Kristian Kutin
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
 * $Id: df21650a7d570c7fbc3862c16f415f5c027b6bc8 $
 */

package jmul.math.prime;


/**
 * This interface describes a list which associates a number
 * with certain properties.<br />
 * <br />
 * <i>Note:<br />
 * Only one property is supported right now (i.e. is prime number).</i>
 *
 * @author Kristian Kutin
 */
interface NumberPropertiesList {

    /**
     * Checks if the specified number is a prime number.
     *
     * @param n
     *
     * @return <code>true</code> if the specified number is
     *         a prime number, else <code>false</code>
     */
    boolean isPrimeNumber(int n);

}
