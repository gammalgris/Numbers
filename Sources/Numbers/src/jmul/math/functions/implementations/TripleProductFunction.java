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

package jmul.math.functions.implementations;


import jmul.math.numbers.Number;
import jmul.math.operations.Result;
import jmul.math.operations.TernaryOperation;
import jmul.math.vectors.Vector;


/**
 * This function implementation calculates the triple product of three vectors (i.e. <code>(vector1 x vector2) * vector3</code>).
 *
 * @author Kristian Kutin
 */
public class TripleProductFunction implements TernaryOperation<Vector, Result<Number>> {

    /**
     * The default constructor.
     */
    public TripleProductFunction() {

        super();
    }

    /**
     * Calculates the triple product of the specified vectors (i.e. <code>(vector1 x vector2) * vector3</code>)
     *
     * @param vector1
     *        a vector
     * @param vector2
     *        a vector
     * @param vector3
     *        a vector
     *
     * @return the triple product
     */
    @Override
    public Result<Number> calculate(Vector vector1, Vector vector2, Vector vector3) {

        ParameterCheckHelper.checkParameters(vector1, vector2, vector3);

        Vector crossProduct = vector1.crossProduct(vector2);
        Number scalarProduct = crossProduct.scalarProduct(vector3);

        return new Result<Number>(scalarProduct);
    }

}
