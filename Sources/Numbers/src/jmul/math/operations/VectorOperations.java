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

    Number length(Vector vector);

    Vector add(Vector vector);

    Vector subtract(Vector vector);

    Vector multiply(Number number);

    Number scalarProduct(Vector vector);

    Vector crossProduct(Vector vector);

    Vector tripleProduct(Vector vector1, Vector vector2);

    Matrix dyadicProduct(Vector vector);

}
