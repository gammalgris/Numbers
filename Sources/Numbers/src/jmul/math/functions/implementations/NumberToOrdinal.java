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
import jmul.math.operations.MixedBinaryOperation;
import jmul.math.operations.Result;


/**
 * Translates a number to an ordinal number.
 *
 * @author Kristian Kutin
 */
public class NumberToOrdinal implements MixedBinaryOperation<Number, Integer, Result<Integer>> {

    /**
     * The default constructor.
     */
    public NumberToOrdinal() {

        super();
    }

    /**
     * Translates the specified number to an ordinal number (an unsigned decimal). The ordinal number is checked
     * with the specified destination number base (i.e. the ordinal number must be zero or greater and lesser than the
     * number base).
     *
     * @param number
     *        a number
     * @param destinationBase
     *        a number base
     *
     * @return an ordinal number (decimal)
     */
    @Override
    public Result<Integer> calculate(Number number, Integer destinationBase) {

        if (number.isFraction()) {

            String message = String.format("The specified number has a fraction part!");
            throw new IllegalArgumentException(message);
        }

        if (number.isInfinity()) {

            String message = String.format("The specified number is infinity!");
            throw new IllegalArgumentException(message);
        }

        int absoluteBase = Math.abs(destinationBase);
        int result = 0;

        Number counter = number.absoluteValue();
        while (!counter.isZero()) {

            counter = counter.dec();
            result = Math.incrementExact(result);
        }

        if (result >= absoluteBase) {

            String message =
                String.format("The specified number (%s) cannot be represented with a single digit in the destination number base (%d)!",
                              number, destinationBase);
            throw new IllegalArgumentException(message);
        }

        return new Result<Integer>(result);
    }

}
