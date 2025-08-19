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
import java.util.TreeSet;

import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.operations.Result;
import jmul.math.operations.UnaryOperation;
import jmul.math.signs.Signs;


/**
 * An implementation of a function that determines the divisors of a number.
 *
 * @author Kristian Kutin
 */
public class DetermineDivisorsFunction implements UnaryOperation<Number, Result<SortedSet<Number>>> {

    /**
     * The default constructor.
     */
    public DetermineDivisorsFunction() {

        super();
    }

    /**
     * Determines the divisors of the specified number and returns a set with all divisors greater than one.
     *
     * @param operand
     *        a number
     *
     * @return a set of divisors
     */
    @Override
    public Result<SortedSet<Number>> calculate(Number operand) {

        ParameterCheckHelper.checkInteger(operand);

        int base = operand.base();

        final Number TWO;
        {
            final Number ONE = createNumber(base, Signs.POSITIVE, 1);
            TWO = ONE.inc();
        }

        SortedSet<Number> divisors = new TreeSet<>();

        for (Number number = TWO; number.isLesser(operand); number = number.inc()) {

            Number doubled = number.doubling();

            if (doubled.isLesserOrEqual(operand)) {

                if (operand.isMultipleOf(number)) {

                    divisors.add(number);
                }

            } else {

                break;
            }
        }

        return new Result<SortedSet<Number>>(divisors);
    }

}
