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


import java.util.ArrayList;
import java.util.List;

import jmul.math.Math;
import jmul.math.collections.Sequence;
import jmul.math.collections.SequenceImpl;
import jmul.math.numbers.Number;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.OperationSingletons;
import jmul.math.operations.Result;
import jmul.math.operations.ResultWithRemainder;
import jmul.math.operations.UnaryOperation;
import jmul.math.operations.repository.OperationIdentifiers;


/**
 * An implementation of a function that determines the prime factors of a number.
 *
 * TODO Consider caching the results
 *
 * @author Kristian Kutin
 */
public class DeterminePrimeFactors implements UnaryOperation<Number, Result<Sequence<Number>>> {

    /**
     * The default constructor.
     */
    public DeterminePrimeFactors() {

        super();
    }

    /**
     * Determines the prime factors of the specified number. The result set contains all prime factors.
     *
     * @param number
     *        a number
     *
     * @return a set of divisors
     */
    @Override
    public Result<Sequence<Number>> calculate(Number number) {

        ParameterCheckHelper.checkInteger(number);

        int base = number.base();

        List<Number> divisors = determinePrimeFactors(number);
        Sequence<Number> sequence = new SequenceImpl<>(base, divisors);

        return new Result<Sequence<Number>>(sequence);
    }

    /**
     * Returns a list of prime factors which represent the specified number.
     *
     * @param number
     *        a number (i.e. positive integer greater than zero)
     *
     * @return a list of prime factors
     */
    private List<Number> determinePrimeFactors(Number number) {

        List<Number> allDivisors = new ArrayList<>();
        if (number.isOne()) {

            return allDivisors;
        }

        int base = number.base();

        BinaryOperation<Number, ResultWithRemainder<Number>> function =
            (BinaryOperation<Number, ResultWithRemainder<Number>>) OperationSingletons.getFunction(OperationIdentifiers.DIVIDE_NUMBERS_RETURN_RESULT_AND_REMAINDER_FUNCTION);

        Number ordinal = Math.ZERO.value(base);

        Number normalizedNumber = number.absoluteValue();
        Number divisor;
        Number result;
        Number remainder;
        while (true) {

            if (normalizedNumber.isZero() || normalizedNumber.isOne()) {

                break;
            }

            while (true) {

                divisor = Math.nextPrimeNumber(ordinal);

                ResultWithRemainder<Number> resultWrapper = function.calculate(normalizedNumber, divisor);

                result = resultWrapper.result();
                remainder = resultWrapper.remainder();

                if (remainder.isZero()) {

                    break;
                }

                if (divisor.equals(normalizedNumber)) {

                    break;
                }

                ordinal = ordinal.inc();
            }

            if (remainder.isZero()) {

                allDivisors.add(divisor);
            }

            normalizedNumber = result;
        }

        return allDivisors;
    }

}
