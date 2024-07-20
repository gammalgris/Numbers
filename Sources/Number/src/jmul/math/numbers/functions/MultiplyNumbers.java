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


import jmul.math.numbers.Number;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.Result;


/**
 * Implements the multiplication according to the russian peasant multiplication.
 * The implementation checks the operands regarding absolute values and signs and
 * compares the numbers. Depending on the actual case additional multiplications
 * or divisions are performed.<br>
 * <br>
 * <i>Note:<br>
 * For large numbers a different algorithm may be required. Make a statistical
 * analysis with different algorithms.</i>
 *
 * @author Kristian Kutin
 */
public class MultiplyNumbers implements BinaryOperation<Number, Result<Number>> {

    public MultiplyNumbers() {

        super();
    }

    @Override
    public Result<Number> calculate(Number operand1, Number operand2) {
        // TODO Implement this method
        return null;
    }

}
