/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2024  Kristian Kutin
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


import jmul.math.fractions.Fraction;
import static jmul.math.fractions.FractionHelper.DONT_CLONE;
import static jmul.math.fractions.FractionHelper.createFraction;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.numbers.Number;
import jmul.math.numbers.NumberImpl;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.Result;
import jmul.math.signs.Sign;
import jmul.math.signs.Signs;


/**
 * An implementation of a division (i.e. division by subtraction).
 *
 * @author Kristian Kutin
 */
public class DivisionBySubtraction implements BinaryOperation<Number, Result<Fraction>> {

    /**
     * The default constructor.
     */
    public DivisionBySubtraction() {

        super();
    }

    /**
     * Performs a division with the specified dividend and divisor. The result is an expression (i.e.
     * a quotient, a mixed fraction or integer).
     *
     * @param dividend
     * @param divisor
     *
     * @return
     */
    @Override
    public Result<Fraction> calculate(Number dividend, Number divisor) {

        ParameterCheckHelper.checkParameters(dividend, divisor);
        ParameterCheckHelper.checkIntegerIgnoreNull(dividend);
        ParameterCheckHelper.checkIntegerIgnoreNull(divisor);

        int base = dividend.base();
        Sign sign = Signs.negate(Signs.xor(dividend.sign(), divisor.sign()));

        Number absoluteDividend = dividend.absoluteValue();
        Number absoluteDivisor = divisor.absoluteValue();

        if (absoluteDivisor.isOne()) {

            Number newDividend = absoluteDividend;
            if (Signs.isNegative(sign)) {
                
                newDividend = newDividend.negate();
            }

            Fraction result = createFraction(DONT_CLONE, newDividend);
            return new Result<Fraction>(result);
            
        } else if (absoluteDividend.isInfinity() || absoluteDivisor.isZero() || absoluteDivisor.isInfinity()) {
            
            Number newDividend = absoluteDividend;
            Number newDivisor = absoluteDivisor;
            if (Signs.isNegative(sign)) {
                
                newDividend = newDividend.negate();
            }

            Fraction result = createFraction(DONT_CLONE, newDividend, newDivisor);
            return new Result<Fraction>(result);
        } 

        Number remainder = absoluteDividend;
        Number subtrahend = absoluteDivisor;
        Number multiple = createNumber(base, "0");

        while (true) {

            if (remainder.isLesser(subtrahend)) {

                break;
            }

            remainder = remainder.subtract(subtrahend);
            multiple = multiple.inc();
        }


        if (remainder.isZero()) {

            Fraction integer;
            if (Signs.isPositive(sign)) {

                integer = createFraction(DONT_CLONE, multiple);

            } else {

                integer = createFraction(DONT_CLONE, multiple.negate());
            }

            return new Result<Fraction>(integer);

        } else if (!multiple.isZero()) {

            Fraction mixedNumber;
            if (Signs.isPositive(sign)) {

                mixedNumber = createFraction(DONT_CLONE, multiple, remainder, subtrahend);

            } else {

                mixedNumber = createFraction(DONT_CLONE, multiple.negate(), remainder, subtrahend);
            }

            return new Result<Fraction>(mixedNumber);

        } else {

            Fraction quotient;
            if (Signs.isPositive(sign)) {

                quotient = createFraction(DONT_CLONE, remainder, divisor);

            } else {

                quotient = createFraction(DONT_CLONE, remainder.negate(), subtrahend);
            }

            return new Result<Fraction>(quotient);
        }
    }

}
