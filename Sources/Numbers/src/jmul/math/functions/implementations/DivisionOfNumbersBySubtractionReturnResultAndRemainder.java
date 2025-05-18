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


import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.numbers.exceptions.NoResultButLimitException;
import jmul.math.numbers.exceptions.UndefinedOperationException;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.ResultWithRemainder;
import jmul.math.signs.Sign;
import jmul.math.signs.Signs;


/**
 * An implementation of a division (i.e. division by subtraction) for numbers.
 *
 * @author Kristian Kutin
 */
public class DivisionOfNumbersBySubtractionReturnResultAndRemainder implements BinaryOperation<Number, ResultWithRemainder<Number>> {

    /**
     * The default constructor.
     */
    public DivisionOfNumbersBySubtractionReturnResultAndRemainder() {

        super();
    }

    /**
     * Performs a division with the specified dividend and divisor. The result is an expression (i.e.
     * a quotient, a mixed fraction or integer).
     *
     * @param dividend
     *        a number
     * @param divisor
     *        a number
     *
     * @return the result with the remainder
     */
    @Override
    public ResultWithRemainder<Number> calculate(Number dividend, Number divisor) {

        ParameterCheckHelper.checkParameters(dividend, divisor);
        ParameterCheckHelper.checkIntegerIgnoreNull(dividend);
        ParameterCheckHelper.checkIntegerIgnoreNull(divisor);

        int base = dividend.base();
        Sign signResult = Signs.divideAndDetermineResultSign(dividend.sign(), divisor.sign());
        Sign signRemainder = Signs.divideAndDetermineRemainderSign(dividend.sign(), divisor.sign());

        Number absoluteDividend = dividend.absoluteValue();
        Number absoluteDivisor = divisor.absoluteValue();
        Number ZERO = createNumber(Signs.POSITIVE, base, 0);
        Number ONE = createNumber(Signs.POSITIVE, base, 1);

        if (absoluteDivisor.isOne()) {

            Number newDividend = absoluteDividend;
            if (Signs.isNegative(signResult)) {

                newDividend = newDividend.negate();
            }

            return new ResultWithRemainder<Number>(newDividend, ZERO);

        } else if (absoluteDividend.isZero() && absoluteDivisor.isZero()) {

            throw new NoResultButLimitException(ONE);

        } else if (absoluteDivisor.isZero()) {

            throw new UndefinedOperationException("Division by zero!");

        } else if (absoluteDividend.isInfinity() && absoluteDivisor.isInfinity()) {

            throw new NoResultButLimitException(ONE);

        } else if (absoluteDividend.isInfinity()) {

            Number newDividend = absoluteDividend;
            if (Signs.isNegative(signResult)) {

                newDividend = newDividend.negate();
            }

            return new ResultWithRemainder<Number>(newDividend, ZERO);

        } else if (absoluteDivisor.isInfinity()) {

            throw new NoResultButLimitException(ZERO);
        }

        Number remainder = absoluteDividend;
        Number subtrahend = absoluteDivisor;
        Number multiple = ZERO;

        while (true) {

            if (remainder.isLesser(subtrahend)) {

                break;
            }

            remainder = remainder.subtract(subtrahend);
            multiple = multiple.inc();
        }

        if (Signs.isNegative(signResult)) {

            multiple = multiple.negate();
        }

        if (Signs.isNegative(signRemainder)) {

            remainder = remainder.negate();
        }

        return new ResultWithRemainder<Number>(multiple, remainder);
    }

}
