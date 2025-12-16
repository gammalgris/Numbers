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
 * This enumeration defines various boundary types.
 *
 * @author Kristian Kutin
 */
public enum BoundaryTypes implements BoundaryType {


    OPEN_BOUNDARY(false),
    CLOSED_BOUNDARY(true);


    /**
     * A flag indicating whether or not the boundary is included in an interval.
     */
    private final boolean boundaryIncluded;

    /**
     * Creates a new enumeration element.
     *
     * @param boundaryIncluded
     *        <code>true</code> if this boundary is included in an interval, else <code>false</code>
     */
    private BoundaryTypes(boolean boundaryIncluded) {

        this.boundaryIncluded = boundaryIncluded;
    }

    /**
     * Returns if this boundary is included in an interval or not.
     *
     * @return <code>true</code> if this boundary is included in an interval, else <code>false</code>
     */
    @Override
    public boolean includesBoundary() {

        return boundaryIncluded;
    }

    /**
     * Returns the symbol for this boundary according to the specified position.
     *
     * @param position
     *        the position of this boudnary regarding the interval.
     *
     * @return a symbol
     */
    @Override
    public String symbol(BoundaryPosition position) {

        return determineSymbol(this, position);
    }

    /**
     * Determines the symbol for the specified combination of boundary type and position.
     *
     * @param boundaryType
     *        a boundary type
     * @param position
     *        a position within an interval
     *
     * @return a symbol
     */
    private static String determineSymbol(BoundaryType boundaryType, BoundaryPosition position) {

        if (boundaryType == null) {

            throw new IllegalArgumentException("No boundary type (null) was specified!");
        }

        if (position == null) {

            throw new IllegalArgumentException("Np position (null) was specified!");
        }

        if (position == BoundaryPositions.LOWER_BOUNDARY) {

            if (boundaryType.includesBoundary()) {

                return "[";

            } else {

                return "(";
            }

        } else if (position == BoundaryPositions.UPPER_BOUNDARY) {

            if (boundaryType.includesBoundary()) {

                return "]";

            } else {

                return ")";
            }

        } else {

            throw new UnsupportedOperationException();
        }
    }

}
