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

package test.jmul.math.intervals;


import jmul.math.intervals.BoundaryPosition;
import jmul.math.intervals.BoundaryPositions;
import jmul.math.intervals.BoundaryType;
import jmul.math.intervals.BoundaryTypes;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;


/**
 * This test suite tests the boundaries of an interval.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class BoundaryTest {

    /**
     * Tests a specific boundary configuration.
     */
    @Test
    public void testLowerOpenBoundary() {

        BoundaryPosition position = BoundaryPositions.LOWER_BOUNDARY;
        BoundaryType boundaryType = BoundaryTypes.OPEN_BOUNDARY;

        assertEquals("#position", BoundaryPositions.LOWER_BOUNDARY, position);
        assertNotEquals("#position", BoundaryPositions.UPPER_BOUNDARY, position);

        assertEquals("#type", false, boundaryType.includesBoundary());
        assertNotEquals("#type", true, boundaryType.includesBoundary());

        assertEquals("#symbol", "(", boundaryType.symbol(position));
        assertNotEquals("#symbol", "[", boundaryType.symbol(position));
        assertNotEquals("#symbol", ")", boundaryType.symbol(position));
        assertNotEquals("#symbol", "]", boundaryType.symbol(position));
    }

    /**
     * Tests a specific boundary configuration.
     */
    @Test
    public void testLowerClosedBoundary() {

        BoundaryPosition position = BoundaryPositions.LOWER_BOUNDARY;
        BoundaryType boundaryType = BoundaryTypes.CLOSED_BOUNDARY;

        assertEquals("#position", BoundaryPositions.LOWER_BOUNDARY, position);
        assertNotEquals("#position", BoundaryPositions.UPPER_BOUNDARY, position);

        assertEquals("#type", true, boundaryType.includesBoundary());
        assertNotEquals("#type", false, boundaryType.includesBoundary());

        assertNotEquals("#symbol", "(", boundaryType.symbol(position));
        assertEquals("#symbol", "[", boundaryType.symbol(position));
        assertNotEquals("#symbol", ")", boundaryType.symbol(position));
        assertNotEquals("#symbol", "]", boundaryType.symbol(position));
    }

    /**
     * Tests a specific boundary configuration.
     */
    @Test
    public void testUpperOpenBoundary() {

        BoundaryPosition position = BoundaryPositions.UPPER_BOUNDARY;
        BoundaryType boundaryType = BoundaryTypes.OPEN_BOUNDARY;

        assertNotEquals("#position", BoundaryPositions.LOWER_BOUNDARY, position);
        assertEquals("#position", BoundaryPositions.UPPER_BOUNDARY, position);

        assertEquals("#type", false, boundaryType.includesBoundary());
        assertNotEquals("#type", true, boundaryType.includesBoundary());

        assertNotEquals("#symbol", "(", boundaryType.symbol(position));
        assertNotEquals("#symbol", "[", boundaryType.symbol(position));
        assertEquals("#symbol", ")", boundaryType.symbol(position));
        assertNotEquals("#symbol", "]", boundaryType.symbol(position));
    }

    /**
     * Tests a specific boundary configuration.
     */
    @Test
    public void testUpperClosedBoundary() {

        BoundaryPosition position = BoundaryPositions.UPPER_BOUNDARY;
        BoundaryType boundaryType = BoundaryTypes.CLOSED_BOUNDARY;

        assertNotEquals("#position", BoundaryPositions.LOWER_BOUNDARY, position);
        assertEquals("#position", BoundaryPositions.UPPER_BOUNDARY, position);

        assertEquals("#type", true, boundaryType.includesBoundary());
        assertNotEquals("#type", false, boundaryType.includesBoundary());

        assertNotEquals("#symbol", "(", boundaryType.symbol(position));
        assertNotEquals("#symbol", "[", boundaryType.symbol(position));
        assertNotEquals("#symbol", ")", boundaryType.symbol(position));
        assertEquals("#symbol", "]", boundaryType.symbol(position));
    }

}
