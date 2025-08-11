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


import jmul.math.digits.Digit;
import jmul.math.digits.PositionalNumeralSystems;
import jmul.math.operations.Result;
import jmul.math.operations.UnaryOperation;


/**
 * This function generates a random digit.<br>
 * <br>
 * <i>Note:<br>
 * The performance needs to be improved.</i>
 *
 * @author Kristian Kutin
 */
public class RandomDigitFunction implements UnaryOperation<Integer, Result<Digit>> {

    /**
     * The default constructor.
     */
    public RandomDigitFunction() {

        super();
    }

    /**
     * Generates a random digit.
     *
     * @param base
     *        a number base
     *
     * @return a digit
     */
    @Override
    public Result<Digit> calculate(Integer base) {

        ParameterCheckHelper.checkNumberBase(base);

        int randomOrdinal = (int) (Math.random() * base);
        Digit randomDigit = PositionalNumeralSystems.ordinalToDigit(base, randomOrdinal);

        return new Result<Digit>(randomDigit);
    }

}
