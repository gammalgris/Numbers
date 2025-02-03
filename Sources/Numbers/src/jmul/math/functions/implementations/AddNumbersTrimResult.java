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


import jmul.math.functions.FunctionSingletons;
import jmul.math.functions.repository.FunctionIdentifiers;
import jmul.math.numbers.Number;
import jmul.math.numbers.nodes.DigitNode;
import jmul.math.numbers.nodes.NodesHelper;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.Result;


/**
 * This implementation of an addition calls the default algorithm. In some cases the result
 * has to be trimmed. In other cases the result should not be trimmed (i.e. when calculating
 * with complements). A caller who needs the result to be trimmed calls this implementation.
 *
 * @author Kristian Kutin
 */
public class AddNumbersTrimResult implements BinaryOperation<Number, Result<Number>> {

    /**
     * The default constructor.
     */
    public AddNumbersTrimResult() {

        super();
    }

    /**
     * Calls the addition function and trims the result.
     *
     * @param operand1
     *        a number
     * @param operand2
     *        a number
     *
     * @return a number
     */
    @Override
    public Result<Number> calculate(Number operand1, Number operand2) {

        BinaryOperation<Number, Result<Number>> function =
            (BinaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.ADD_NUMBERS_FUNCTION);
        Result<Number> result = function.calculate(operand1, operand2);

        DigitNode resultCenter = result.result().centerNode();
        NodesHelper.trimLeft(resultCenter);
        NodesHelper.trimRight(resultCenter);

        return result;
    }

}
