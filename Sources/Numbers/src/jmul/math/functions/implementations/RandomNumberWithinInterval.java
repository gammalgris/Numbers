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


import jmul.math.functions.FunctionSingletons;
import jmul.math.functions.repository.FunctionIdentifiers;
import jmul.math.numbers.Number;
import jmul.math.operations.MixedBinaryOperation;
import jmul.math.operations.Result;
import jmul.math.operations.TernaryOperation;


/**
 * An implementation of a function which generates a random number within a specified interval.
 *
 * @author Kristian Kutin
 */
public class RandomNumberWithinInterval implements TernaryOperation<Number, Result<Number>> {

    /**
     * The default constructor.
     */
    public RandomNumberWithinInterval() {

        super();
    }

    /**
     * Returns a random value between the specified boundaries (including  the boundaries). Generates a random number
     * between zero and one with the specified number of digits to the right of the decimal separator. Determines and
     * returns a corresponding number within the specified interval (min =&lt; n =&lt; max).
     *
     * @param min
     *        the lower boundary of the interval
     * @param max
     *        the upper boundary of the interval
     * @param digits
     *        the number of digits to the right of the decimal separator. This applies to the random
     *        number generated.
     *
     * @return a random number
     */
    @Override
    public Result<Number> calculate(Number min, Number max, Number digits) {

        ParameterCheckHelper.checkParameters(min, max, digits);

        if (min.isGreater(max)) {

            throw new IllegalArgumentException("Invalid interval boundaries!");
        }

        int base = min.base();
        boolean integer = min.isInteger() && max.isInteger();
        Number newMax = max.inc();

        MixedBinaryOperation<Integer, Number, Result<Number>> randomNumberFunction =
            (MixedBinaryOperation<Integer, Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.RANDOM_NUMBER_FUNCTION);
        Result<Number> result = randomNumberFunction.calculate(base, digits);

        Number randomNumber = result.result();
        Number delta = newMax.subtract(min);

        Number newRandomNumber = randomNumber.multiply(delta);
        newRandomNumber = newRandomNumber.add(min);

        if (integer) {

            newRandomNumber = newRandomNumber.removeFractionPart();
        }

        return new Result<Number>(newRandomNumber);
    }

}
