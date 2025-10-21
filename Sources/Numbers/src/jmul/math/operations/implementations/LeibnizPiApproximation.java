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


import jmul.math.Math;
import jmul.math.numbers.Number;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.Result;
import jmul.math.operations.processing.ProcessingDetails;


/**
 * This operation caclulates Pi according to the Leibniz formula (see
 * <a href="https://en.wikipedia.org/wiki/Leibniz_formula_for_%CF%80">Leibniz formula</a>).
 *
 * @author Kristian Kutin
 */
public class LeibnizPiApproximation implements BinaryOperation<Number, Result<Number>> {

    /**
     * The default constructor.
     */
    public LeibnizPiApproximation() {

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

        final Number ZERO = Math.ZERO.value(base);
        final Number ONE = Math.ONE.value(base);
        final Number FOUR = ONE.doubling().doubling();

        Number sum = ZERO;
        Number counter = iterations;
        Number k = ZERO;

        while (!counter.isZero()) {

            Number next = calculate(k, iterations, decimalPlaces);
            sum = sum.add(next);

            k = k.inc();
            counter = counter.dec();
        }

        sum = FOUR.multiply(sum);
        return new Result<Number>(sum);
    }

    /**
     * Calulates the k<sub>th</sub> number of the series: ( -1 )^k / ( 2 * k + 1 )
     *
     * @param k
     *        a number (i.e. a positive integer)
     * @param iterations
     *        the number of iterations
     * @param decimalPlaces
     *        a precision
     *
     * @return the k<sub>th</sub> number of the series
     */
    private Number calculate(Number k, Number iterations, Number decimalPlaces) {

        int base = k.base();

        final Number MINUS_ONE = Math.MINUS_ONE.value(base);
        final Number ONE = Math.ONE.value(base);
        final Number TWO = ONE.inc();

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(ProcessingDetails.DEFAULT_ALGORITHM, decimalPlaces, iterations);

        Number term1 = MINUS_ONE.exponentiate(k);
        Number term2 = ONE.add(TWO.multiply(k));

        return term1.divide(processingDetails, term2);
    }

}
