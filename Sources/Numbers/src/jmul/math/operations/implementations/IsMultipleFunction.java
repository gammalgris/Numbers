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


import java.util.Collections;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.Result;
import jmul.math.signs.Sign;
import jmul.math.signs.Signs;


/**
 * This function tests if a number is the multiple of a specified number.<br>
 * <br>
 * <i>Note:</i><br>
 * <i>This implementation doesn't need a full division. A full division entails calculating the fraction part which is
 * superfluous compuation in this scenario. It's enough to see if the first part of the division results in a remaidner
 * or not. The fraction part and the default precision might in some cases be interpreted wrongly and lead to a wrong
 * result.</i><br>
 * <i> The russian division is used instead of division by subtraction.</i>
 *
 * @author Kristian Kutin
 */
public class IsMultipleFunction implements BinaryOperation<Number, Result<Boolean>> {

    /**
     * The defalt constructor.
     */
    public IsMultipleFunction() {

        super();
    }

    /**
     * Checks if the first specified number is a multiple of the second specified number.
     *
     * @param operand1
     *        a number
     * @param operand2
     *        a number
     *
     * @return <code>true</code> if the first specified number is a multiple of the second specified number,
     *         else <code>false</code>
     */
    @Override
    public Result<Boolean> calculate(Number operand1, Number operand2) {

        ParameterCheckHelper.checkParameters(operand1, operand2);

        int base = operand1.base();
        Sign sign = Signs.divideAndDetermineResultSign(operand1.sign(), operand2.sign());

        final Number ZERO = createNumber(base, Signs.POSITIVE, 0);


        // Handle special cases which can be resolved without computation.

        if (operand1.isZero() || operand2.isZero()) {

            return new Result<Boolean>(false);
        }

        if (operand1.isInfinity() || operand2.isInfinity()) {

            return new Result<Boolean>(false);
        }

        if (operand1.isOne() && !operand2.isOne()) {

            return new Result<Boolean>(false);
        }

        if (operand2.isOne()) {

            return new Result<Boolean>(true);
        }


        // Determine the integer parts (result and remainder)

        final Number ONE = createNumber(base, Signs.POSITIVE, 1);

        SortedMap<Number, Number> multiples = new TreeMap<Number, Number>(Collections.reverseOrder());

        Number absoluteDividend = operand1.absoluteValue();
        Number absoluteDivisor = operand2.absoluteValue();

        Number integerPart = ZERO;
        Number remainder = absoluteDividend;

        {
            Number factor = ONE;
            Number multiple = absoluteDivisor;

            Number max = absoluteDividend;

            while (max.isGreater(multiple)) {

                multiples.put(factor, multiple);

                factor = factor.doubling();
                multiple = multiple.doubling();
            }
        }

        {
            while (true) {

                if (remainder.isLesser(absoluteDivisor)) {

                    break;
                }

                for (Map.Entry<Number, Number> entry : multiples.entrySet()) {

                    Number key = entry.getKey();
                    Number value = entry.getValue();

                    if (remainder.isGreaterOrEqual(value)) {

                        integerPart = integerPart.add(key);
                        remainder = remainder.subtract(value);
                        break;
                    }
                }
            }
        }

        if (remainder.isZero()) {

            return new Result<Boolean>(true);
        }

        return new Result<Boolean>(false);
    }

}
