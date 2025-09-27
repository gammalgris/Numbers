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


import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import jmul.math.numbers.Number;
import jmul.math.operations.Result;
import jmul.math.operations.UnaryOperation;


/**
 * An implementation of a function that determines the prime factors of a number.
 *
 * @author Kristian Kutin
 */
public class DeterminePrimeFactors implements UnaryOperation<Number, Result<SortedSet<Number>>> {

    /**
     * The default constructor.
     */
    public DeterminePrimeFactors() {

        super();
    }

    /**
     * Determines the prime factors of the specified number. The result set contains all prime factors.
     *
     * @param operand
     *        a number
     *
     * @return a set of divisors
     */
    @Override
    public Result<SortedSet<Number>> calculate(Number operand) {

        ParameterCheckHelper.checkInteger(operand);

        SortedSet<Number> divisors = operand.divisorSet();
        SortedSet<Number> remainingDivisors = new TreeSet<>();

        Iterator<Number> outerIterator = divisors.iterator();
        while (outerIterator.hasNext()) {

            Number number1 = outerIterator.next();

            boolean isNotMultiple = true;
            Iterator<Number> innerIterator = divisors.iterator();
            while (innerIterator.hasNext()) {

                Number number2 = innerIterator.next();

                if (!number1.equals(number2)) {

                    isNotMultiple = !number1.isMultipleOf(number2) && isNotMultiple;
                }
            }

            if (isNotMultiple) {

                remainingDivisors.add(number1);
            }
        }

        return new Result<SortedSet<Number>>(remainingDivisors);
    }

}
