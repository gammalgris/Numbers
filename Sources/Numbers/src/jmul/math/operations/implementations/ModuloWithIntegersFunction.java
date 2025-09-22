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


import jmul.math.operations.OperationSingletons;
import jmul.math.operations.repository.OperationIdentifiers;
import jmul.math.numbers.Number;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.Result;
import jmul.math.operations.ResultWithRemainder;


/**
 * An implementation of a division (i.e. division of two integers) which returns the remainder.<br>
 * <br>
 * <i>Note:<br>
 * The underlying algorithm considers following equation:<br>
 * <br>
 * <code>a = b * c + r</code><br>
 * <br>
 * Where a, b, c, r are all integers. a is the first operand of this function implementation.
 * c is the second operand of this function implementation. r is the returned result.
 * </i>
 *
 * @author Kristian Kutin
 */
public class ModuloWithIntegersFunction implements BinaryOperation<Number, Result<Number>> {

    /**
     * The default constructor.
     */
    public ModuloWithIntegersFunction() {

        super();
    }

    /**
     * Divides the first operand by the second operand and returns the remainder.
     *
     * @param operand1
     *        an integer
     * @param operand2
     *        an integer
     *
     * @return the remainder of the division as integer
     */
    @Override
    public Result<Number> calculate(Number operand1, Number operand2) {

        BinaryOperation<Number, ResultWithRemainder<Number>> function =
            (BinaryOperation<Number, ResultWithRemainder<Number>>) OperationSingletons.getFunction(OperationIdentifiers.DIVIDE_NUMBERS_RETURN_RESULT_AND_REMAINDER_FUNCTION);
        ResultWithRemainder<Number> result = function.calculate(operand1, operand2);

        return new Result<Number>(result.remainder());
    }

}
