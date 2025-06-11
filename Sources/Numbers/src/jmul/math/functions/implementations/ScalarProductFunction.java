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


import java.util.Iterator;

import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.Result;
import jmul.math.signs.Signs;
import jmul.math.vectors.Vector;


/**
 * This function implementation calculates the scalar product of two vectors
 *
 * @author Kristian Kutin
 */
public class ScalarProductFunction implements BinaryOperation<Vector, Result<Number>> {

    /**
     * The default constructor.
     */
    public ScalarProductFunction() {

        super();
    }

    /**
     * Calculates the scalar product ogf the two specified vectors.
     *
     * @param vector1
     *        a vector
     * @param vector2
     *        a vector
     *
     * @return the scalar product
     */
    @Override
    public Result<Number> calculate(Vector vector1, Vector vector2) {

        ParameterCheckHelper.checkParameters(vector1, vector2);

        int base = vector1.base();

        Iterator<Number> iterator1 = vector1.iterator();
        Iterator<Number> iterator2 = vector2.iterator();

        Number sum = createNumber(Signs.POSITIVE, base, 0);

        while (iterator1.hasNext() && iterator2.hasNext()) {

            Number component1 = iterator1.next();
            Number component2 = iterator2.next();

            Number product = component1.multiply(component2);
            sum = sum.add(product);
        }

        return new Result<Number>(sum);
    }

}
