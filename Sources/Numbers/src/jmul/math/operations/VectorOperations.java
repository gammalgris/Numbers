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


import jmul.math.matrices.Matrix;
import jmul.math.vectors.Vector;
import jmul.math.numbers.Number;


/**
 * This interface describes various vector operations.<br>
 * <br>
 * <i>Note:<br>
 * This interface describes something like a fluent interface for this custom vector implementation and
 * is intended to allow concatenation of vector operations.</i><br>
 * <br>
 * <i>Note:<br>
 * Requires more elaboration.</i>
 *
 * @author Kristian Kutin
 */
public interface VectorOperations {

    /**
     * Calculates the length of this vector.
     *
     * @return the length of this vector
     */
    Number length();

    /**
     * Adds this vector and the specified vector.
     *
     * @param vector
     *        another vector
     *
     * @return the result of the addition
     */
    Vector add(Vector vector);

    /**
     * Subtracts the specified vector form this vector.
     *
     * @param vector
     *        another vector
     *
     * @return the result of the subtraction
     */
    Vector subtract(Vector vector);

    /**
     * Multiply this vector with the specified number (i.e. perform a scalar multiplication).
     *
     * @param number
     *        a number
     *
     * @return the result of the multiplication
     */
    Vector multiply(Number number);

    /**
     * Multiplies this vector with the specified vector (i.e. calculates the inner product of two vectors).
     *
     * @param vector
     *        another vector
     *
     * @return the result of the multiplication
     */
    Number scalarProduct(Vector vector);

    /**
     * Calculates the cross product of this vector and the specified vector.
     *
     * @param vector
     *        another vector
     *
     * @return the cross product
     */
    Vector crossProduct(Vector vector);

    /**
     * Calculates the triple product of this vector and the two specified vectors (i.e. <code>(vector1 x vector2) * vector3</code>)
     *
     * @param vector1
     *        a vector
     * @param vector2
     *        a vector
     *
     * @return the triple product
     */
    Number tripleProduct(Vector vector1, Vector vector2);

    /**
     * Calculates the dyadic product of this vector and the specified vector. The underlying assumption ist that this
     * first vector is interpreted as a column vector and the specified vector is interpreted as a row vector.
     *
     * @param vector
     *        another vector
     *
     * @return the dyadic product
     */
    Matrix dyadicProduct(Vector vector);

}
