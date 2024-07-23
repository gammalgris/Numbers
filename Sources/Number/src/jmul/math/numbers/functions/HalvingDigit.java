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

package jmul.math.numbers.functions;


import jmul.math.numbers.digits.Digit;
import jmul.math.numbers.digits.PositionalNumeralSystems;
import static jmul.math.numbers.functions.ParameterCheckHelper.checkParameter;
import jmul.math.operations.ResultWithRemainder;
import jmul.math.operations.UnaryOperation;


/**
 * Implements a function that halves a digit (i.e. divides it by two).
 *
 * @author Kristian Kutin
 */
public class HalvingDigit implements UnaryOperation<Digit, ResultWithRemainder<Digit>> {

    /**
     * The default constructor.
     */
    public HalvingDigit() {

        super();
    }

    /**
     * Takes the specified digit and halves it (i.e. divides it by 2 in the corresponding number base).
     *
     * @param digit
     *        a digit
     *
     * @return the result with the remainder
     */
    @Override
    public ResultWithRemainder<Digit> calculate(Digit digit) {

        checkParameter(digit);

        int base = digit.base();

        int ordinal = digit.ordinal();
        int result = ordinal / 2;
        int modulo = ordinal % 2;

        int remainder;
        if (modulo == 1) {

            remainder = base / 2;

        } else {

            remainder = 0;
        }

        Digit resultDigit = PositionalNumeralSystems.ordinalToDigit(base, result);
        Digit remainderDigit = PositionalNumeralSystems.ordinalToDigit(base, remainder);

        return new ResultWithRemainder<Digit>(resultDigit, remainderDigit);
    }

}
