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

package jmul.math.units;


/**
 * This enumeration contains various angle units.<br>
 * <br>
 * <i>Note:<br>
 * This enumeration is not complete. Add missing entries.</i>
 *
 * @author Kristian Kutin
 */
public enum AngleUnits implements Angle {

    /**
     * See <a href="https://en.wikipedia.org/wiki/Radian">Radian</a>.
     */
    RADIAN,

    /**
     * See <a href="https://en.wikipedia.org/wiki/Degree_(angle)">Degree</a>.
     */
    DEGREE,

    /**
     * See <a href="https://en.wikipedia.org/wiki/Gradian">Gradian</a>.
     */
    GRADIAN, ;

}
