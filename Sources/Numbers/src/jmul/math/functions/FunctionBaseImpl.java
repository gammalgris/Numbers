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

package jmul.math.functions;


import jmul.math.numbers.Number;
import jmul.math.operations.implementations.ParameterCheckHelper;
import jmul.math.operations.processing.ProcessingDetails;


/**
 * A common base implementation for functions.
 *
 * @author Kristian Kutin
 */
abstract class FunctionBaseImpl implements Function {

    /**
     * A number base.
     */
    private final int base;

    /**
     * Creates a new instance according to the specified parameters.
     */
    FunctionBaseImpl(int base) {

        super();

        ParameterCheckHelper.checkNumberBase(base);

        this.base = base;
    }

    /**
     * Returns the used number base.
     *
     * @return a number base
     */
    @Override
    public int base() {

        return base;
    }

    /**
     * Returns a string in a function notation.
     *
     * @return a string in a function notation
     */
    public String toFunctionNotation() {

        return String.format("f(x) = %s", toString());
    }

    /**
     * Calculate the function value for x.
     *
     * @param x
     *        the input value
     *
     * @return f(x)
     */
    @Override
    public Number calculate(Number x) {

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(ProcessingDetails.DEFAULT_ALGORITHM,
                                                   ProcessingDetails.DEFAULT_PRECISION,
                                                   ProcessingDetails.DEFAULT_ITERATION_DEPTH);

        return calculate(processingDetails, x);
    }

}
