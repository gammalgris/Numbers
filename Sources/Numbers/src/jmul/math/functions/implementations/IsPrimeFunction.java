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


import java.util.SortedSet;

import jmul.math.numbers.Number;
import jmul.math.operations.Result;
import jmul.math.operations.UnaryOperation;


/**
 * This function checks if a number is  a prime number or not.
 *
 * @author Kristian Kutin
 */
public class IsPrimeFunction implements UnaryOperation<Number, Result<Boolean>> {

    /**
     * The default constructor.
     */
    public IsPrimeFunction() {

        super();
    }

    /**
     * Checks if the specified number is a prime number.
     *
     * @param operand
     *        a number
     *
     * @return <code>true</code> if the specified number is a prime number, else <code>false</code>
     */
    @Override
    public Result<Boolean> calculate(Number operand) {

        ParameterCheckHelper.checkInteger(operand);

        if (operand.isZero() || operand.isNegative()) {

            String message = String.format("An invalid number (%s) was specified!", operand);
            throw new IllegalArgumentException(message);
        }

        if (operand.isOne()) {

            return new Result<Boolean>(false);
        }

        SortedSet<Number> primeFactors = operand.primeFactors();
        boolean isPrime = primeFactors.isEmpty();

        return new Result<Boolean>(isPrime);
    }

}
