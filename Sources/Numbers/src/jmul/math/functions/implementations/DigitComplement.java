/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2022  Kristian Kutin
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
import static jmul.math.functions.implementations.ParameterCheckHelper.checkParameter;
import jmul.math.operations.Result;
import jmul.math.operations.UnaryOperation;


/**
 * This unary function implementation calculates the coplement of a digit.
 *
 * @author Kristian Kutin
 */
public class DigitComplement implements UnaryOperation<Digit, Result<Digit>> {

    /**
     * The default constructor.
     */
    public DigitComplement() {

        super();
    }

    /**
     * Calculates the complement for the specified digit.
     *
     * @param digit
     *        a digit
     *
     * @return the result
     */
    @Override
    public Result<Digit> calculate(Digit digit) {

        checkParameter(digit);

        int base = digit.base();
        int result = base - digit.ordinal() - 1;

        Digit newDigit = PositionalNumeralSystems.ordinalToDigit(base, result);

        return new Result<Digit>(newDigit);
    }

}
