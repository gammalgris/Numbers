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


import jmul.math.fractions.Fraction;
import static jmul.math.fractions.FractionHelper.createFraction;
import jmul.math.numbers.Number;
import static jmul.math.numbers.creation.CreationParameters.CLONE;
import jmul.math.operations.Result;
import jmul.math.operations.UnaryOperation;


/**
 * Implements a function that doubles a fraction.
 *
 * @author Kristian Kutin
 */
public class DoublingFraction implements UnaryOperation<Fraction, Result<Fraction>> {

    /**
     * The default constructor.
     */
    public DoublingFraction() {

        super();
    }

    /**
     * Doubles the specified fraction.
     *
     * @param fraction
     *        a fraction
     *
     * @return a doubled fraction
     */
    @Override
    public Result<Fraction> calculate(Fraction fraction) {

        ParameterCheckHelper.checkParameter(fraction);

        Fraction normalizedFraction = fraction.normalizedFraction();
        Number numerator = normalizedFraction.numerator();
        Number denominator = normalizedFraction.denominator();

        if (numerator.isInfinity()) {

            Fraction clone =
                createFraction(CLONE, fraction.integerPart(), fraction.numerator(), fraction.denominator());
            return new Result<Fraction>(clone);
        }

        numerator = numerator.doubling();
        Fraction result = createFraction(CLONE, numerator, denominator);

        return new Result<Fraction>(result);
    }

}
