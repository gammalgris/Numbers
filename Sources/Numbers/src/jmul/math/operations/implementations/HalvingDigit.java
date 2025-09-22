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

package jmul.math.operations.implementations;


import jmul.math.digits.Digit;
import jmul.math.digits.PositionalNumeralSystems;
import static jmul.math.operations.implementations.ParameterCheckHelper.checkParameter;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.ResultWithRemainder;


/**
 * Implements a function that halves a digit (i.e. divides it by two).
 *
 * @author Kristian Kutin
 */
public class HalvingDigit implements BinaryOperation<Digit, ResultWithRemainder<Digit>> {

    /**
     * The default constructor.
     */
    public HalvingDigit() {

        super();
    }

    /**
     * Takes the specified digit and halves it (i.e. divides it by 2 in the corresponding number base).<br>
     * <br>
     * <i>Note:<br>
     * With odd number bases it's not always possible to hit the exact half. In this case this algorithm will give you
     * a number slightly less than the half. Adding the halves will result in a number lesser than the original
     * number.</i>
     *
     * @param digit
     *        a digit
     * @param carry
     *        the carry of a previous calculation. The carry can be zero or one.
     *
     * @return the result with the remainder
     */
    @Override
    public ResultWithRemainder<Digit> calculate(Digit digit, Digit carry) {

        checkParameter(digit);

        int base = digit.base();

        int total = carry.ordinal() * base + digit.ordinal();
        int result = total / 2;
        int remainder = total % 2;

        Digit resultDigit = PositionalNumeralSystems.ordinalToDigit(base, result);
        Digit remainderDigit = PositionalNumeralSystems.ordinalToDigit(base, remainder);

        return new ResultWithRemainder<Digit>(resultDigit, remainderDigit);
    }

}
