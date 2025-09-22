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


import java.util.LinkedList;
import java.util.Queue;

import jmul.math.numbers.Number;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.Result;
import jmul.math.indices.IndexSingletons;
import jmul.math.vectors.Vector;
import jmul.math.vectors.VectorImpl;


/**
 * This function implementation calculates the cross product of two vectors.
 *
 * @author Kristian Kutin
 */
public class CrossProductFunction implements BinaryOperation<Vector, Result<Vector>> {

    /**
     * The default constructor.
     */
    public CrossProductFunction() {

        super();
    }

    /**
     * Calculates the specified vectors.
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

        Number firstIndex = IndexSingletons.firstIndex(base);
        Number lastIndex = vector1.dimensions();

        Number currentIndex = firstIndex;

        Queue<Number> results = new LinkedList<Number>();

        while (currentIndex.isLesserOrEqual(lastIndex)) {

            Number indexRotatedUp = rotateIndexUp(currentIndex, firstIndex, lastIndex);
            Number indexRotatedDown = rotateIndexDown(currentIndex, firstIndex, lastIndex);

            Number component1 = vector1.component(indexRotatedUp);
            Number component2 = vector2.component(indexRotatedDown);
            Number component3 = vector1.component(indexRotatedDown);
            Number component4 = vector2.component(indexRotatedUp);

            Number firstPart = component1.multiply(component2);
            Number secondPart = component3.multiply(component4);

            Number result = firstPart.subtract(secondPart);
            results.add(result);

            currentIndex = currentIndex.inc();
        }

        Vector crossProduct = new VectorImpl(base, results.stream());

        return new Result<Vector>(crossProduct);
    }

    /**
     * Rotates the specified index up.<br>
     * <br>
     * <i>Example:</i><br>
     * <br>
     * <code>
     * index sequence:         { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, ...}<br>
     * rotated index sequence: { 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, ... }
     * </code>
     * <br>
     * <br>
     * <i>If you specify an index of 1 the function returns an index of 2. For an index of 2 an index of 3
     * is returned. Etc.</i>
     *
     * @param currentIndex
     *        the current index
     * @param firstIndex
     *        the first index
     * @param lastIndex
     *        the last index
     *
     * @return a rotated index
     */
    private Number rotateIndexUp(Number currentIndex, Number firstIndex, Number lastIndex) {

        Number newIndex = currentIndex.inc();

        if (newIndex.isGreater(lastIndex)) {

            newIndex = firstIndex;
        }

        return newIndex;
    }

    /**
     * Rotates the specified index down.<br>
     * <br>
     * <i>Example:</i><br>
     * <br>
     * <code>
     * index sequence:         { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, ...}<br>
     * rotated index sequence: { 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, ... }
     * </code>
     * <br>
     * <br>
     * <i>If you specify an index of 1 the function returns an index of 10. For an index of 2 an index of 2
     * is returned. Etc.</i>
     *
     * @param currentIndex
     *        the current index
     * @param firstIndex
     *        the first index
     * @param lastIndex
     *        the last index
     *
     * @return a rotated index
     */
    private Number rotateIndexDown(Number currentIndex, Number firstIndex, Number lastIndex) {

        Number newIndex = currentIndex.dec();

        if (newIndex.isLesser(firstIndex)) {

            newIndex = lastIndex;
        }

        return newIndex;
    }

}
