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


import java.util.SortedSet;

import jmul.math.fractions.Fraction;
import jmul.math.fractions.FractionHelper;
import jmul.math.numbers.Number;
import static jmul.math.numbers.creation.CreationParameters.DONT_CLONE;
import jmul.math.operations.Result;
import jmul.math.operations.UnaryOperation;
import jmul.math.operations.processing.ProcessingDetails;
import jmul.math.operations.repository.OperationIdentifiers;


/**
 * Implements a function that reduces a fraction.
 *
 * @author Kristian Kutin
 */
public class ReduceFraction implements UnaryOperation<Fraction, Result<Fraction>> {

    /**
     * Additional Processing details.
     */
    private static final ProcessingDetails PROCESSING_DETAILS;

    /*
     * The static initializer.
     */
    static {

        PROCESSING_DETAILS = ProcessingDetails.setAlgorithm(OperationIdentifiers.RUSSIAN_DIVISION_FUNCTION);
    }

    /**
     * The default constructor.
     */
    public ReduceFraction() {

        super();
    }

    /**
     * Reduces the specified fraction.
     *
     * @param operand
     *        a fraction
     *
     * @return a fraction
     */
    @Override
    public Result<Fraction> calculate(Fraction operand) {

        ParameterCheckHelper.checkParameter(operand);

        Fraction reducedFraction = operand.normalizedFraction();

        while (true) {

            SortedSet<Number> commonPrimeFactors = reducedFraction.commonPrimeFactors();

            if (commonPrimeFactors.isEmpty()) {

                break;
            }

            for (Number primeFactor : commonPrimeFactors) {

                Number numerator = reducedFraction.numerator();
                Number denominator = reducedFraction.denominator();

                Number newNumerator = numerator.divide(PROCESSING_DETAILS, primeFactor);
                Number newDenominator = denominator.divide(PROCESSING_DETAILS, primeFactor);

                reducedFraction = FractionHelper.createFraction(DONT_CLONE, newNumerator, newDenominator);
            }
        }

        return new Result<Fraction>(reducedFraction);
    }

}
