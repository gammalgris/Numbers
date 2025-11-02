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


import java.util.SortedMap;
import java.util.TreeMap;

import jmul.math.Math;
import jmul.math.numbers.Number;
import jmul.math.operations.Result;
import jmul.math.operations.UnaryOperation;


/**
 * Calculates the next prime number  (e.g. ordinal 0 -&gt; 2, ordinal 1 -&gt; 3, etc.)..
 *
 * @author Kristian Kutin
 */
public class NextPrimeNumber implements UnaryOperation<Number, Result<Number>> {

    /**
     * The actual element container.
     */
    final SortedMap<Integer, SortedMap<Number, Number>> primeNumbersByBase;

    /**
     * The default constructor.
     */
    public NextPrimeNumber() {

        super();

        this.primeNumbersByBase = new TreeMap<>();
    }

    /**
     * Returns the prime number according to the specified ordinal number.
     *
     * @param ordinal
     *        the ordinal number associated with a prime number
     *
     * @return a prime number
     */
    @Override
    public Result<Number> calculate(Number ordinal) {

        ParameterCheckHelper.checkParameter(ordinal);

        int base = ordinal.base();

        SortedMap<Number, Number> primeNumbers = primeNumbersByBase.get(base);
        if (primeNumbers == null) {

            primeNumbers = new TreeMap<>();
            primeNumbersByBase.put(base, primeNumbers);
        }

        Number primeNumber = primeNumbers.get(ordinal);
        if (primeNumber == null) {

            primeNumber = calculateNextPrimeNumber(ordinal);

        }

        return new Result<Number>(primeNumber);
    }

    /**
     * Calculates the next prime number.
     *
     * @param ordinal
     *        an ordinal number
     *
     * @return the prime number corresponding to the ordinal number
     */
    private Number calculateNextPrimeNumber(Number ordinal) {

        int base = ordinal.base();

        SortedMap<Number, Number> primeNumbers = primeNumbersByBase.get(base);

        Number highestOrdinal;
        Number highestNumber;
        if (primeNumbers.isEmpty()) {

            highestOrdinal = Math.MINUS_ONE.value(base);
            highestNumber = Math.ONE.value(base);

        } else {

            highestOrdinal = primeNumbers.lastKey();
            highestNumber = primeNumbers.get(highestOrdinal);
        }

        Number counter = highestOrdinal;
        Number number = highestNumber;

        while (counter.isLesser(ordinal)) {

            number = number.inc();

            if (number.isPrime()) {

                counter = counter.inc();
                primeNumbers.put(counter, number);
            }
        }

        return number;
    }

}
