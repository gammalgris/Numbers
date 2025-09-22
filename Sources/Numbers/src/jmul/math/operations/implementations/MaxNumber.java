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


import java.util.Comparator;

import jmul.math.operations.OperationSingletons;
import jmul.math.operations.repository.OperationIdentifiers;
import jmul.math.numbers.Number;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.Result;


/**
 * This function implementation compares two numbers and returns the greater number.
 *
 * @author Kristian Kutin
 */
public class MaxNumber implements BinaryOperation<Number, Result<Number>> {

    /**
     * The default constructor.
     */
    public MaxNumber() {

        super();
    }

    /**
     * Compares the specified numbers and returns the greater number.
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

        ParameterCheckHelper.checkParameters(operand1, operand2);

        Comparator<Number> function =
            (Comparator<Number>) OperationSingletons.getFunction(OperationIdentifiers.NUMBER_COMPARATOR_FUNCTION);
        int result = function.compare(operand1, operand2);

        if (result >= 0) {

            return new Result<Number>(operand1);

        } else {

            return new Result<Number>(operand2);
        }
    }

}
