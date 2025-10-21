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
import static jmul.math.fractions.FractionHelper.createFraction;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;
import static jmul.math.numbers.creation.CreationParameters.DONT_CLONE;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.Result;


/**
 * This operation caclulates Pi according to Archimedes (see
 * <a href="https://en.wikipedia.org/wiki/Measurement_of_a_Circle">Measurement of a Circle</a>).
 *
 * @author Kristian Kutin
 */
public class ArchimedesPiApproximation implements BinaryOperation<Number, Result<Number>> {

    /**
     * The default constructor.
     */
    public ArchimedesPiApproximation() {

        super();
    }

    /**
     * Calculates an approximation of Pi accordign to the specified parameters.
     *
     * @param iterations
     *        an iteration depths
     * @param decimalPlaces
     *        a precision
     *
     * @return an approximation of Pi
     */
    @Override
    public Result<Number> calculate(Number iterations, Number decimalPlaces) {

        ParameterCheckHelper.checkParameters(iterations, decimalPlaces);
        ParameterCheckHelper.checkPositiveIntegerGreaterZero(iterations);
        ParameterCheckHelper.checkPositiveInteger(iterations);

        int base = iterations.base();

        Number numerator = createNumber(10, "22");
        numerator = numerator.rebase(base);

        Number denominator = createNumber(10, "7");
        denominator = denominator.rebase(base);

        Fraction fraction = createFraction(DONT_CLONE, numerator, denominator);

        Number result = fraction.evaluate(decimalPlaces);
        return new Result<Number>(result);
    }

}
