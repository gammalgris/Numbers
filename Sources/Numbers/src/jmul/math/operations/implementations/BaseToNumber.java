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
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.Result;
import jmul.math.signs.Sign;
import jmul.math.signs.Signs;


/**
 * This class implements a function which translates a number base (decimal) to a number of a specified base.
 *
 * @author Kristian Kutin
 */
public class BaseToNumber implements BinaryOperation<Integer, Result<Number>> {

    /**
     * The default constructor.
     */
    public BaseToNumber() {

        super();
    }

    /**
     * Determines and returns the sign of the specified integer.
     *
     * @param i
     *        an integer
     *
     * @return a sign
     */
    private Sign determineSign(int i) {

        if (i < 0) {

            return Signs.NEGATIVE;
        }

        return Signs.POSITIVE;
    }

    /**
     * Translates the specified number base (decimal) to a number of the specified destination number base (decimal).
     *
     * @param base
     *        the number base (decimal)
     * @param destinationBase
     *        the destination number base (decimal)
     *
     * @return the number base as number
     */
    @Override
    public Result<Number> calculate(Integer base, Integer destinationBase) {

        int absoluteBase = java.lang.Math.abs(base);

        Number result = Math.ZERO.value(destinationBase);

        for (int a = 0; a < absoluteBase; a++) {

            result = result.inc();
        }

        if (base < 0) {

            result = result.negate();
        }

        return new Result<Number>(result);
    }

}
