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
import jmul.math.operations.Result;
import jmul.math.operations.UnaryOperation;
import jmul.math.signs.Signs;


/**
 * An implementation of a function that calculates the factorial.
 *
 * @author Kristian Kutin
 */
public class Factorial implements UnaryOperation<Number, Result<Number>> {

    /**
     * The default constructor.
     */
    public Factorial() {

        super();
    }

    /**
     * Claculates the factorial of the specified number.
     *
     * @param operand
     *        an integer
     *
     * @return the factorial of the specified integer
     */
    @Override
    public Result<Number> calculate(Number operand) {

        ParameterCheckHelper.checkPositiveInteger(operand);

        if (operand.isZero()) {

            Number result = createNumber(Signs.POSITIVE, operand.base(), 1);
            return new Result<Number>(result);
        }

        if (operand.isInfinity()) {

            Number result = createNumber(operand);
            return new Result<Number>(result);
        }

        Number factor = operand.dec();
        Number product = operand;

        while (true) {

            if (factor.isZero()) {

                break;
            }

            product = product.multiply(factor);
            factor = factor.dec();
        }

        return new Result<Number>(product);
    }

}
