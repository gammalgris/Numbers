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

package jmul.math;


/**
 * This class contains various constants.
 *
 * @author Kristian Kutin
 * 
 * @deprecated Conflicts with jmul.math.Constants from library jmul
 */
@Deprecated
public final class Constants {

    /**
     * The smallest base which is currently supported.
     */
    public static final int MIN_BASE;

    /**
     * The greatest base which is currently supported.
     */
    public static final int MAX_BASE;

    /*
     * The static initializer.
     */
    static {

        MIN_BASE = 2;
        MAX_BASE = 62;
    }

    /**
     * The default constructor.
     */
    private Constants() {

        throw new UnsupportedOperationException();
    }

}
