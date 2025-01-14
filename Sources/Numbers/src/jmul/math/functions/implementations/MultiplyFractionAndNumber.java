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
import static jmul.math.functions.implementations.ParameterCheckHelper.checkParameters;
import jmul.math.numbers.Number;
import jmul.math.numbers.NumberImpl;
import jmul.math.operations.MixedBinaryOperation;
import jmul.math.operations.Result;


/**
 * Implements a multiplication of an expression with a number.
 *
 * @author Kristian Kutin
 */
public class MultiplyFractionAndNumber implements MixedBinaryOperation<Fraction, Number, Result<Fraction>> {

    /**
     * Default constructor.
     */
    public MultiplyFractionAndNumber() {

        super();
    }

    /**
     * Multiplies the specified expression with the specified number.
     *
     * @param operand1
     *        a number
     * @param operand2
     *        a number
     *
     * @return an expression
     */
    @Override
    public Result<Fraction> calculate(Fraction operand1, Number operand2) {

        /*checkParameters(operand1, operand2);

        int base = operand1.base();
        final Number ONE = new NumberImpl(base, "1");

        // normalize this expression (i.e. dividend and divisor)

        Number newDividend;
        if (operand1.hasDividend()) {

            newDividend = operand1.dividend();

        } else {

            newDividend = ONE;
        }

        Number newDivisor;
        if (operand1.hasDivisor()) {

            newDivisor = operand1.divisor();

        } else {

            newDivisor = ONE;
        }

        if (operand1.hasIntegerPart()) {

            Number newIntegerPart = operand1.integerPart();
            newIntegerPart = newIntegerPart.multiply(newDivisor);

            newDividend = newDividend.add(newIntegerPart);
        }

        // do the multiplication

        newDividend = newDividend.multiply(operand2);
        newDivisor = newDivisor.multiply(operand2);

        Fraction newExpression = createQuotient(DONT_CLONE, newDividend, newDivisor);

        return new Result<Fraction>(newExpression);*/
        
        throw new UnsupportedOperationException();
    }

}
