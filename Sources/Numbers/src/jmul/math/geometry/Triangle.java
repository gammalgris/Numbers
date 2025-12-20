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

package jmul.math.geometry;


import jmul.math.numbers.Number;


/**
 * This interface describes a triangle.
 *
 * <pre>
 * {@code
 *                C
 *              gamma
 *                .
 *              .     .
 *         b  .           .   a
 *         .                  .
 *       .                        .
 *     ...............................
 *   A alpha         c                beta B
 * }
 * </pre>
 *
 * @author Kristian Kutin
 */
public interface Triangle {

    /**
     * The length of side a of the triangle.
     *
     * @return the length of the side a
     */
    Number a();

    /**
     * The length of side b of the triangle.
     *
     * @return the length of the side b
     */
    Number b();

    /**
     * The length of side b of the triangle.
     *
     * @return  the length of the side c
     */
    Number c();

    /**
     * Returns the angle alpha of the triangle.
     *
     * @return an angle
     */
    Number alpha();

    /**
     * Returns the angle beta of the triangle.
     *
     * @return an angle
     */
    Number beta();

    /**
     * Returns the angle gamma of the triangle.
     *
     * @return an angle
     */
    Number gamma();

    /**
     * Checks if this triangle matches the soecified type.
     *
     * @param triangleType
     *        a triangle type
     *
     * @return <code>true</code> if this triangle matches the specified type, else <code>false</code>
     */
    boolean is(TriangleType triangleType);

}
