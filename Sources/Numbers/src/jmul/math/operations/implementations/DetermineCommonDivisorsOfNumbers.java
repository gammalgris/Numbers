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


import java.lang.reflect.Array;

import jmul.math.collections.Set;
import jmul.math.concurrent.CalculationPool;
import jmul.math.concurrent.ConcurrentCalculation;
import jmul.math.numbers.Number;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.Result;


/**
 * An operation that determines the common divisors of two numbers.
 *
 * @author Kristian Kutin
 */
public class DetermineCommonDivisorsOfNumbers implements BinaryOperation<Number, Result<Set<Number>>> {

    /**
     * The default constructor.
     */
    public DetermineCommonDivisorsOfNumbers() {

        super();
    }

    /**
     * Determines the common divisors of the specified numbers.
     *
     * @param number1
     *        a number
     * @param number2
     *        a number
     *
     * @return a set containing all common divisors or an empty set if there are no common divisors
     */
    @Override
    public Result<Set<Number>> calculate(Number number1, Number number2) {

        ParameterCheckHelper.checkParameters(number1, number2);
        ParameterCheckHelper.checkIndex(number1);
        ParameterCheckHelper.checkIndex(number2);

        /*
        // sequential calculations
        Set<Number> set1 = number1.divisors();
        Set<Number> set2 = number2.divisors();
        */

        // concurrent calculations

        CalculationPool<Number, Set<Number>> threadPool = new ConcurrentDetermineDivisorsPool();
        Set<Number>[] results = threadPool.calculateResultsAndWaitForThreads(number1, number2);
        Set<Number> set1 = results[0];
        Set<Number> set2 = results[1];

        Set<Number> resultSet = set1.intersection(set2);

        return new Result<Set<Number>>(resultSet);
    }

}


/**
 * A thread implementation for concurrent computing.
 *
 * @author Kristian Kutin
 */
class ConcurrentDetermineDivisors extends ConcurrentCalculation<Number, Set<Number>> {

    /**
     * Creates a new instance according to the specified parameter.
     *
     * @param number
     *        a number
     */
    ConcurrentDetermineDivisors(Number number) {

        super(number);
    }

    /**
     * The actual concurrent calculation.
     *
     * @param input
     *        a number
     *
     * @return a sequence of prime factors
     */
    @Override
    public Set<Number> calculate(Number input) {

        return input.divisors();
    }

}


/**
 * A pool for handling concurrent computations.
 *
 * @author Kristian Kutin
 */
class ConcurrentDetermineDivisorsPool extends CalculationPool<Number, Set<Number>> {

    /**
     * Creates a new empty array of the result type.
     *
     * @param length
     *        the array size
     *
     * @return a new empty array
     */
    @Override
    protected Set<Number>[] newArray(int length) {

        return (Set<Number>[]) Array.newInstance(Set.class, length);
    }

    /**
     * Creates all concurrent calculations (i.e. runnables).
     *
     * @param inputs
     *        numbers
     *
     * @return all concurrent calculations (i.e. runnables)
     */
    @Override
    protected ConcurrentCalculation<Number, Set<Number>>[] createConcurrentCalculations(Number... inputs) {

        int length = inputs.length;

        if (length != 2) {

            throw new IllegalArgumentException("A wrong numer of arguments was specified!");
        }

        ConcurrentDetermineDivisors[] calculations = new ConcurrentDetermineDivisors[length];

        for (int index = 0; index < length; index++) {

            Number number = inputs[index];
            ConcurrentDetermineDivisors runnable = new ConcurrentDetermineDivisors(number);
            calculations[index] = runnable;
        }

        return calculations;
    }

}
