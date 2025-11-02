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
import jmul.math.operations.Result;


/**
 * An implementation of a function that determines the common prime factors of two specified number.
 *
 * @author Kristian Kutin
 */
public class DetermineCommonPrimeFactorsInNumbers implements BinaryOperation<Number, Result<Sequence<Number>>> {

    /**
     * The default constructor.
     */
    public DetermineCommonPrimeFactorsInNumbers() {

        super();
    }

    /**
     * Determine the common prime factors.
     *
     * @param number1
     *        a number
     * @param number2
     *        a number
     *
     * @return a sequence with all common prime factors
     */
    @Override
    public Result<Sequence<Number>> calculate(Number number1, Number number2) {

        ParameterCheckHelper.checkParameters(number1, number2);

        int base = number1.base();

        Sequence<Number> sequence1 = number1.primeFactors();
        Sequence<Number> sequence2 = number2.primeFactors();

        final Number ZERO = Math.ZERO.value(base);
        Number ordinal1 = ZERO;
        Number ordinal2 = ZERO;

        List<Number> list1 = new ArrayList<>();
        List<Number> list2 = new ArrayList<>();

        Number element1 = null;
        Number element2 = null;

        // Expand the lists in order to make it easier to identify common prime factors. Examples:
        // 9 & 3 -> [ 3, 3 } & { 3 } -> { 3, 3 } & { 3, null }
        // 9 & 6 -> [ 3, 3 } & { 2, 3 } -> { null, 3, 3 } & { 2, 3, null }

        while (true) {

            if (ordinal1.isLesser(sequence1.elements())) {

                element1 = sequence1.ordinal(ordinal1);

            } else {

                element1 = null;
            }

            if (ordinal2.isLesser(sequence2.elements())) {

                element2 = sequence2.ordinal(ordinal2);

            } else {

                element2 = null;
            }

            if ((element1 == null) && (element2 == null)) {

                break;
            }

            if (element1 == null) {

                for (; ordinal2.isLesser(sequence2.elements()); ordinal2 = ordinal2.inc()) {

                    element2 = sequence2.ordinal(ordinal2);
                    list1.add(null);
                    list2.add(element2);
                }


            } else if (element2 == null) {

                for (; ordinal1.isLesser(sequence1.elements()); ordinal1 = ordinal1.inc()) {

                    element1 = sequence1.ordinal(ordinal1);
                    list1.add(element1);
                    list2.add(null);
                }

            } else if (element1.equals(element2)) {

                list1.add(element1);
                list2.add(element2);

                ordinal1 = ordinal1.inc();
                ordinal2 = ordinal2.inc();

            } else {

                if (element1.isLesser(element2)) {

                    list1.add(element1);
                    list2.add(null);

                    ordinal1 = ordinal1.inc();

                } else {

                    list1.add(null);
                    list2.add(element2);

                    ordinal2 = ordinal2.inc();
                }
            }

        }

        // Collect the common prime factors.

        List<Number> common = new ArrayList<>();

        for (int index = 0; index < list1.size(); index++) {

            element1 = list1.get(index);
            element2 = list2.get(index);

            if ((element1 == null) || (element2 == null)) {

                continue;

            } else {

                common.add(element1);
            }
        }

        Sequence<Number> result = new SequenceImpl<>(base, common);
        return new Result<Sequence<Number>>(result);
    }

}
