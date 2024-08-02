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


import java.util.Comparator;

import jmul.math.functions.FunctionSingletons;
import jmul.math.numbers.FunctionIdentifiers;
import jmul.math.numbers.Number;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.Result;


/**
 * An implementation of a number comparison.
 *
 * @author Kristian Kutin
 */
public class LesserComparison implements BinaryOperation<Number, Result<Boolean>> {

    /**
     * The default constructor.
     */
    public LesserComparison() {

        super();
    }

    /**
     * Checks if the first operand number is lesser than the second operand.
     *
     * @param operand1
     *        a number
     * @param operand2
     *        a number
     *
     * @return <code>true</code> if the first operand is lesser than the second operand, else <code>false</code>
     */
    @Override
    public Result<Boolean> calculate(Number operand1, Number operand2) {

        Comparator<Number> function =
            (Comparator<Number>) FunctionSingletons.getFunction(FunctionIdentifiers.NUMBER_COMPARATOR_FUNCTION);
        int comparisonResult = function.compare(operand1, operand2);

        boolean result = comparisonResult < 0;

        return new Result<Boolean>(result);
    }

}
