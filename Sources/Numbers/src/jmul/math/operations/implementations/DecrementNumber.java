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


import jmul.math.Math;
import jmul.math.numbers.Number;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.OperationSingletons;
import jmul.math.operations.Result;
import jmul.math.operations.UnaryOperation;
import jmul.math.operations.repository.OperationIdentifiers;


/**
 * This function implements the decrement function.
 *
 * @author Kristian Kutin
 */
public class DecrementNumber implements UnaryOperation<Number, Result<Number>> {

    /**
     * The default constructor.
     */
    public DecrementNumber() {

        super();
    }

    /**
     * Decrements the specified operand by one.
     *
     * @param operand
     *        a number
     *
     * @return a number
     */
    @Override
    public Result<Number> calculate(Number operand) {

        ParameterCheckHelper.checkParameter(operand);

        int base = operand.base();
        final Number ONE = Math.ONE.value(base);

        BinaryOperation<Number, Result<Number>> function =
            (BinaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.SUBTRACT_NUMBERS_FUNCTION);
        Result<Number> result = function.calculate(operand, ONE);

        return result;
    }

}
