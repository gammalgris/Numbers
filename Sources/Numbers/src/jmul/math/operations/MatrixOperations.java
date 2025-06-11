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


/**
 * This interface describes various matrix operations.<br>
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
public interface MatrixOperations {

    /**
     * Performs a matrix addition with this matrix and the specified matrix.
     *
     * @param matrix
     *        a matrix
     *
     * @return the result
     */
    Matrix add(Matrix matrix);

    /**
     * Performs a matrix subtraction with this matrix and the specified matrix.
     *
     * @param matrix
     *        a matrix
     *
     * @return the result
     */
    Matrix subtract(Matrix matrix);

    /**
     * Transposes this matrix.
     *
     * @return a transposed matrix
     */
    Matrix transpose();

    /**
     * Performs a matrix multiplication with this matrix and the specified matrix.
     *
     * @param matrix
     *        a matrix
     *
     * @return the result
     */
    Matrix multiply(Matrix matrix);

    /**
     * Performs a vetoriazation of this matrix (i.e. transforms the matrix to a vector).
     *
     * @return a vector
     */
    Vector toVector();

}
