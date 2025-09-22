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


import jmul.math.numbers.Number;
import jmul.math.numbers.NumberHelper;
import static jmul.math.numbers.creation.CreationParameters.CLONE;
import jmul.math.operations.Result;
import jmul.math.operations.UnaryOperation;
import jmul.math.signs.Sign;
import jmul.math.signs.Signs;


/**
 * A function that rounds a number down (i.e. the nearest integer that doesn't exceed this number).
 *
 * @author Kristian Kutin
 */
public class RoundDownFunction implements UnaryOperation<Number, Result<Number>> {

    /**
     * The default constructor.
     */
    public RoundDownFunction() {

        super();
    }

    /**
     * Rounds the specified number down (i.e. the nearest integer that doesn't exceed this number).
     *
     * @param number
     *        a number
     *
     * @return the nearest integer that doesn't exceed the specified number
     */
    @Override
    public Result<Number> calculate(Number number) {

        ParameterCheckHelper.checkParameter(number);

        if (number.isInteger() || number.isInfinity()) {

            Number clone = NumberHelper.createNumber(CLONE, number);
            return new Result<Number>(clone);
        }

        Sign originalSign = number.sign();
        Number result = number.removeFractionPart();

        if (Signs.isNegative(originalSign)) {

            result = result.dec();
        }

        return new Result<Number>(result);
    }

}
