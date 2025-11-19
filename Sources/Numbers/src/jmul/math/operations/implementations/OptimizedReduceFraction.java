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
import jmul.math.operations.OperationSingletons;
import jmul.math.operations.Result;
import jmul.math.operations.UnaryOperation;
import jmul.math.operations.repository.OperationIdentifiers;


/**
 * Implements a function that reduces a fraction by evaluating the last two digits.
 *
 * @author Kristian Kutin
 */
public class OptimizedReduceFraction implements UnaryOperation<Fraction, Result<Fraction>> {

    /**
     * The default constructor.
     */
    public OptimizedReduceFraction() {

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

        Number newNumerator = reducedFraction.numerator();
        Number newDenominator = reducedFraction.denominator();

        do {

            if (lastDigitIsZero(newNumerator) && lastDigitIsZero(newDenominator)) {

                newNumerator = newNumerator.shiftLeft();
                newDenominator = newDenominator.shiftLeft();

            } else if (newNumerator.isEven() && newDenominator.isEven()) {

                newNumerator = newNumerator.halving();
                newDenominator = newDenominator.halving();

            } else {

                break;
            }

        } while (true);

        reducedFraction = FractionHelper.createFraction(DONT_CLONE, newNumerator, newDenominator);

        if (newDenominator.isOne() || newNumerator.isOne()) {

            return new Result<Fraction>(reducedFraction);
        }

        UnaryOperation<Fraction, Result<Fraction>> function =
            (UnaryOperation<Fraction, Result<Fraction>>) OperationSingletons.getFunction(OperationIdentifiers.REDUCE_FRACTION_BY_COMMON_PRIME_FACTORS);
        Result<Fraction> result = function.calculate(reducedFraction);

        return result;
    }

    /**
     * Checks if the last digit (i.e. most right right digit) is zero.
     *
     * @param n
     *        a number
     *
     * @return <code>true</code> if the last digit is zero, else <code>false</code>
     */
    private boolean lastDigitIsZero(Number n) {

        return n.centerNode().digit().isZero();
    }

}
