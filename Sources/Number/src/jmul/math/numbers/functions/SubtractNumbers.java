/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2023  Kristian Kutin
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


import jmul.math.numbers.Number;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.Result;


/**
 * This class implements a function for subtracting two numbers. The implementation checks the
 * operands regarding absolute values and signs and compares the numbers. Depending on the actual
 * case an addition or subtraction is performed.<br>
 * Subtraction is done by calculating the complement of the subtrahend and add it to minuend
 * (minuend &gt; subtrahend). Additions are delegated to the corresponding function. Special cases
 * (e.g. infinity or zero operands) are handled seperately as they don't require much computation.
 *
 * @author Kristian Kutin
 */
public class SubtractNumbers implements BinaryOperation<Number, Result<Number>> {

    /**
     * The default constructor.
     */
    public SubtractNumbers() {

        super();
    }

    @Override
    public Result<Number> calculate(Number operand1, Number operand2) {

        // TODO Implement this method
        return null;
    }

}
