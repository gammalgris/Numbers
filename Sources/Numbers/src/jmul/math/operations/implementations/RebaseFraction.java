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
import jmul.math.fractions.FractionHelper;
import jmul.math.numbers.Number;
import static jmul.math.numbers.creation.CreationParameters.DONT_CLONE;
import jmul.math.operations.MixedBinaryOperation;
import jmul.math.operations.Result;


/**
 * An implementation of a function that rebases a fraction to a dfferent number base.
 *
 * @author Kristian Kutin
 */
public class RebaseFraction implements MixedBinaryOperation<Fraction, Integer, Result<Fraction>> {

    /**
     * The default constructor.
     */
    public RebaseFraction() {

        super();
    }

    /**
     * Rebases the specified fraction to the specified destination number base.
     *
     * @param fraction
     *        a fraction
     * @param destinationBase
     *        a destination number base
     *
     * @return a rebased fraction
     */
    @Override
    public Result<Fraction> calculate(Fraction fraction, Integer destinationBase) {

        ParameterCheckHelper.checkParameter(fraction);
        ParameterCheckHelper.checkNumberBase(destinationBase);

        Number integerPart = fraction.integerPart();
        Number numerator = fraction.numerator();
        Number denominator = fraction.denominator();

        integerPart = integerPart.rebase(destinationBase);
        numerator = numerator.rebase(destinationBase);
        denominator = denominator.rebase(destinationBase);

        Fraction translatedFraction = FractionHelper.createFraction(DONT_CLONE, integerPart, numerator, denominator);

        return new Result<Fraction>(translatedFraction);
    }

}
