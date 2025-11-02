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
import jmul.math.operations.Result;
import jmul.math.operations.UnaryOperation;


/**
 * This function checks if a number is  a prime number or not.
 *
 * @author Kristian Kutin
 */
public class IsPrimeCheck implements UnaryOperation<Number, Result<Boolean>> {

    /**
     * The default constructor.
     */
    public IsPrimeCheck() {

        super();
    }

    /**
     * Checks if the specified number is a prime number.
     *
     * @param number
     *        a number
     *
     * @return <code>true</code> if the specified number is a prime number, else <code>false</code>
     */
    @Override
    public Result<Boolean> calculate(Number number) {

        ParameterCheckHelper.checkInteger(number);

        if (number.isZero() || number.isNegative()) {

            String message = String.format("An invalid number (%s) was specified!", number);
            throw new IllegalArgumentException(message);
        }

        if (number.isOne()) {

            return new Result<Boolean>(false);
        }

        int base = number.base();
        Number divisor = Math.TWO.value(base);
        int divisors = 1; // 1 is a divisor but we don't need to test it

        while ((divisor.doubling()).isLesserOrEqual(number)) {

            Number remainder = number.modulo(divisor);
            if (remainder.isZero()) {

                divisors++;

                if (divisors > 2) { // We don't need to test all possible divisors. If there are more than 2 divisors
                    // we stop and save computing cycles.

                    break;
                }
            }

            divisor = divisor.inc();
        }

        divisors++; // The loop doesn't test the spüecified number itself as divisor. There is also no need to test it.

        boolean isPrime = divisors == 2;

        return new Result<Boolean>(isPrime);
    }

}
