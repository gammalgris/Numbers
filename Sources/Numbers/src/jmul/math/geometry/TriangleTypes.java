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


/**
 * This interface defines a triangle type.
 * 
 * @author Kristian Kutin
 */
public enum TriangleTypes implements TriangleType {

    /*
     * By side lengths
     *
     * + Irregular triangle (synonym: scalene triangle)
     * + Isosceles triangle
     * + Equilateral triangle
     */

    IRREGULAR_TRIANGLE,
    ISOSCELES_TRIANGLE,
    EQUILATERAL_TRIANGLE,

    /*
     * By angles
     *
     * + Acute triangle
     * + Right triangle
     * + Obverted triangle
     */

    ACUTE_TRIANGLE,
    RIGHT_TRIANGLE,
    OBVERTED_TRIANGLE,
    OBTUSE_TRIANGLE;

}
