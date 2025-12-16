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

package jmul.math.intervals;


/**
 * This interface desfines a boundary type for an interval.
 *
 * @author Kristian Kutin
 */
public interface BoundaryType {

    /**
     * Returns if this boundary is included in an interval or not.
     *
     * @return <code>true</code> if this boundary is included in an interval, else <code>false</code>
     */
    boolean includesBoundary();

    /**
     * Returns the symbol for this boundary according to the specified position.
     *
     * @param position
     *        the position of this boudnary regarding the interval.
     *
     * @return a symbol
     */
    String symbol(BoundaryPosition position);

}
