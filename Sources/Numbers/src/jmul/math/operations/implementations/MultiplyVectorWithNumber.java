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
import jmul.math.operations.MixedBinaryOperation;
import jmul.math.operations.Result;
import jmul.math.vectors.Vector;
import jmul.math.vectors.VectorImpl;


/**
 * This function implementation multiplies a vector with a number.
 *
 * @author Kristian Kutin
 */
public class MultiplyVectorWithNumber implements MixedBinaryOperation<Vector, Number, Result<Vector>> {

    /**
     * The default constructor.
     */
    public MultiplyVectorWithNumber() {

        super();
    }

    /**
     * Multiplies the specified vector with the specified number.
     *
     * @param vector
     *        a vector
     * @param number
     *        a number
     *
     * @return a vector
     */
    @Override
    public Result<Vector> calculate(Vector vector, Number number) {

        ParameterCheckHelper.checkParameters(vector, number);

        int base = vector.base();

        Iterator<Number> iterator = vector.iterator();

        Queue<Number> results = new LinkedList<Number>();

        while (iterator.hasNext()) {

            Number component = iterator.next();

            Number result = component.multiply(number);
            results.add(result);
        }

        Vector sum = new VectorImpl(base, results.stream());

        return new Result<Vector>(sum);
    }

}
