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
import jmul.math.operations.OperationSingletons;
import jmul.math.operations.repository.OperationIdentifiers;
import jmul.math.numbers.Number;
import jmul.math.operations.MixedBinaryOperation;
import jmul.math.operations.Result;
import jmul.math.operations.TernaryOperation;


/**
 * A function to evaluate a fraction (i.e. perform a division).
 *
 * @author Kristian Kutin
 */
public class EvaluateFraction implements MixedBinaryOperation<Fraction, Number, Result<Number>> {

    /**
     * The default constructor.
     */
    public EvaluateFraction() {

        super();
    }

    /**
     * Normalizes the fraction and performs a division.
     *
     * @param fraction
     *        a fraction
     * @param decimalPlaces
     *        the number of decimal places retained after cutting the fraction part
     *
     * @return a quotient
     */
    @Override
    public Result<Number> calculate(Fraction fraction, Number decimalPlaces) {

        ParameterCheckHelper.checkParameter(fraction);
        ParameterCheckHelper.checkInteger(decimalPlaces);

        Fraction normalizedFraction = fraction.normalizedFraction();
        Number numerator = normalizedFraction.numerator();
        Number denominator = normalizedFraction.denominator();

        TernaryOperation<Number, Result<Number>> function =
            (TernaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.RUSSIAN_DIVISION_FUNCTION);
        Result<Number> result = function.calculate(numerator, denominator, decimalPlaces);
        Number quotient = result.result();

        return new Result<Number>(quotient);
    }

}
