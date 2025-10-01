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


import jmul.math.fractions.Fraction;
import jmul.math.numbers.Number;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.Result;


/**
 * An operation that calculates an approximation of Euler's number.
 *
 * @author Kristian Kutin
 */
public class EulersNumberFunction implements BinaryOperation<Number, Result<Number>> {

    /**
     * The default constructor.
     */
    public EulersNumberFunction() {

        super();
    }

    /**
     * Calculates an approximation of Euler's number accordign to the specified parameters.
     *
     * @param iterations
     *        an iteration depths
     * @param decimalPlaces
     *        a precision
     *
     * @return an approximation of Euler's number
     */
    @Override
    public Result<Number> calculate(Number iterations, Number decimalPlaces) {

        ParameterCheckHelper.checkParameters(iterations, decimalPlaces);
        ParameterCheckHelper.checkPositiveIntegerGreaterZero(iterations);
        ParameterCheckHelper.checkPositiveInteger(iterations);

        Fraction approximation = f(iterations);
        Number eulersNumber = approximation.evaluate(decimalPlaces);

        return new Result<Number>(eulersNumber);
    }

    /**
     * The actual function that recursively calculates Euler's number.
     *
     * @param x
     *        a value
     *
     * @return the result
     */
    private static Fraction f(Number x) {

        if (x.isZero()) {

            return (x.factorial()).reciprocal();
        }

        return ((x.factorial()).reciprocal()).add(f(x.dec()));
    }

}
