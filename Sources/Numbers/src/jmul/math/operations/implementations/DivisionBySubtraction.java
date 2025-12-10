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
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.numbers.exceptions.NoResultButLimitException;
import jmul.math.numbers.exceptions.UndefinedOperationException;
import jmul.math.numbers.nodes.NodesHelper;
import jmul.math.operations.Result;
import jmul.math.operations.TernaryOperation;
import jmul.math.signs.Sign;
import jmul.math.signs.Signs;


/**
 * Implements division by subtraction.<br>
 * <br>
 * <i>Note<br>
 * This implementation is slow (i.e. O(n)) but should be useful for checking the correctness of other division
 * algorithms.</I>
 *
 * @author Kristian Kutin
 */
public class DivisionBySubtraction implements TernaryOperation<Number, Result<Number>> {

    /**
     * The default constructor.
     */
    public DivisionBySubtraction() {

        super();
    }

    /**
     * Divides the specified dividend by the specified divisor.
     *
     * @param dividend
     *        a number
     * @param divisor
     *        a number
     * @param decimalPlaces
     *        the number of decimal places retained after cutting the fraction part
     *
     * @return the quotient
     */
    @Override
    public Result<Number> calculate(Number dividend, Number divisor, Number decimalPlaces) {

        ParameterCheckHelper.checkParameters(dividend, divisor, decimalPlaces);
        ParameterCheckHelper.checkPositiveInteger(decimalPlaces);

        int base = dividend.base();
        Sign sign = Signs.negate(Signs.xor(dividend.sign(), divisor.sign()));

        Number absoluteDividend = dividend.absoluteValue();
        Number absoluteDivisor = divisor.absoluteValue();

        // Handle special cases which can be resolved without computation.

        if (absoluteDivisor.isZero()) {

            if (absoluteDividend.isInfinity()) {

                Number newDividend = absoluteDividend;
                if (Signs.isNegative(sign)) {

                    newDividend = newDividend.negate();
                }

                throw new NoResultButLimitException(newDividend);

            } else {

                throw new UndefinedOperationException("/", dividend, divisor);
            }

        } else if (absoluteDivisor.isOne()) {

            Number newDividend = absoluteDividend;
            if (Signs.isNegative(sign)) {

                newDividend = newDividend.negate();
            }

            return new Result<Number>(newDividend);
        }


        // detrmine the integer part

        Number remainder = absoluteDividend;
        Number subtrahend = absoluteDivisor;
        Number multiple = Math.ZERO.value(base);

        while (remainder.isGreaterOrEqual(subtrahend)) {

            remainder = remainder.subtract(subtrahend);
            multiple = multiple.inc();
        }

        Number integerPart = multiple;


        if (remainder.isZero()) { // If there is no remainder then the calculation is done.

            if (Signs.isNegative(sign)) {

                multiple = multiple.negate();
            }

            return new Result<Number>(multiple);
        }


        // Determine the fraction part

        final Number ZERO = createNumber(base, Signs.POSITIVE, 0);
        Number fractionPart = ZERO;
        Number digits = ZERO;

        {
            Number factor = ZERO;

            while (true) {

                if (digits.isGreaterOrEqual(decimalPlaces) && remainder.isLesser(absoluteDivisor)) {

                    break;
                }

                if (remainder.isZero()) {

                    break;
                }

                if (remainder.isLesser(absoluteDivisor)) {

                    remainder = remainder.shiftRight();
                    digits = digits.inc();

                    if (remainder.isLesser(absoluteDivisor)) {

                        fractionPart = fractionPart.add(factor);
                        fractionPart = fractionPart.shiftRight();

                        factor = ZERO;
                    }
                }

                while (remainder.isGreaterOrEqual(absoluteDivisor)) {

                    remainder = remainder.subtract(absoluteDivisor);
                    factor = factor.inc();

                    if (remainder.isLesser(absoluteDivisor)) {

                        fractionPart = fractionPart.add(factor);
                        fractionPart = fractionPart.shiftRight();

                        factor = ZERO;
                    }

                    break;
                }
            }
        }


        // Correct and trim the fraction part.

        while (!digits.isZero()) {

            fractionPart = fractionPart.shiftLeft();
            digits = digits.dec();
        }
        fractionPart = fractionPart.shiftLeft();

        NodesHelper.trimRight(fractionPart.centerNode());


        // Add the integer part and fraction part for the final result.

        Number result = integerPart.add(fractionPart);

        if (Signs.isNegative(sign)) {

            result = result.negate();
        }

        return new Result<Number>(result);
    }

}
