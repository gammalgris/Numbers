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


import jmul.math.numbers.Number;
import jmul.math.digits.Digit;
import jmul.math.numbers.exceptions.UndefinedOperationException;
import jmul.math.numbers.nodes.DigitNode;
import jmul.math.operations.Result;
import jmul.math.operations.UnaryOperation;


/**
 * This function implementation tests if a number is odd.
 *
 * @author Kristian Kutin
 */
public class OddNumberFunction implements UnaryOperation<Number, Result<Boolean>> {

    /**
     * The default constuctor.
     */
    public OddNumberFunction() {

        super();
    }

    /**
     * Tests if the specified number is odd.
     *
     * @param operand
     *        a number
     *
     * @return <code>true</code> if the specified number is odd, else <code>false</code>
     */
    @Override
    public Result<Boolean> calculate(Number operand) {

        ParameterCheckHelper.checkInteger(operand);

        if (operand.isInfinity()) {

            throw new UndefinedOperationException();
        }

        DigitNode centerNode = operand.centerNode();
        Digit digit = centerNode.digit();
        int ordinal = digit.ordinal();

        int remainder = ordinal % 2;

        boolean result;
        if (remainder == 0) {

            result = false;

        } else {

            result = true;
        }

        return new Result<Boolean>(result);
    }

}
