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

package jmul.math.operations.implementations;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import jmul.math.numbers.Number;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.Result;
import jmul.math.vectors.Vector;
import jmul.math.vectors.VectorImpl;


/**
 * This function implementation adds two vectors.
 *
 * @author Kristian Kutin
 */
public class AddVectors implements BinaryOperation<Vector, Result<Vector>> {

    /**
     * The default constructor.
     */
    public AddVectors() {

        super();
    }

    /**
     * Adds the specified vectors.
     *
     * @param vector1
     *        a vector
     * @param vector2
     *        a vector
     *
     * @return the result
     */
    @Override
    public Result<Vector> calculate(Vector vector1, Vector vector2) {

        ParameterCheckHelper.checkParameters(vector1, vector2);

        int base = vector1.base();

        Iterator<Number> iterator1 = vector1.iterator();
        Iterator<Number> iterator2 = vector2.iterator();

        Queue<Number> results = new LinkedList<Number>();

        while (iterator1.hasNext() && iterator2.hasNext()) {

            Number component1 = iterator1.next();
            Number component2 = iterator2.next();

            Number result = component1.add(component2);
            results.add(result);
        }

        Vector sum = new VectorImpl(base, results.stream());

        return new Result<Vector>(sum);
    }

}
