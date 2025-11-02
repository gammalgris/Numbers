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


import jmul.math.collections.Sequence;
import jmul.math.fractions.Fraction;
import jmul.math.numbers.Number;
import jmul.math.operations.Result;
import jmul.math.operations.UnaryOperation;


/**
 * An implementation of a function that determines the common prime factors of a fraction (i.e. numerator and
 * denominator).
 *
 * @author Kristian Kutin
 */
public class DetermineCommonPrimeFactorsInFraction implements UnaryOperation<Fraction, Result<Sequence<Number>>> {

    /**
     * The default constructor.
     */
    public DetermineCommonPrimeFactorsInFraction() {

        super();
    }

    /**
     * Determines the common prime factors of the specified fraction (i.e. numerator and denominator).
     *
     * @param operand
     *        a fraction
     *
     * @return all common divisors
     */
    @Override
    public Result<Sequence<Number>> calculate(Fraction operand) {

        ParameterCheckHelper.checkParameter(operand);

        Fraction normalizedFraction = operand.normalizedFraction();

        Number numerator = normalizedFraction.numerator();
        Number denominator = normalizedFraction.denominator();

        Sequence<Number> commonPrimeFactors = numerator.commonPrimeFactors(denominator);
        return new Result<Sequence<Number>>(commonPrimeFactors);
    }

}
