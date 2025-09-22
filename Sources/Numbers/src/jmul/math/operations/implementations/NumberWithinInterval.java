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


import jmul.math.numbers.Number;
import jmul.math.operations.Result;
import jmul.math.operations.TernaryOperation;


/**
 * An implementation of a function that checks if a number is within an interval.
 *
 * @author Kristian Kutin
 */
public class NumberWithinInterval implements TernaryOperation<Number, Result<Boolean>> {

    /**
     * The default constructor.
     */
    public NumberWithinInterval() {

        super();
    }

    /**
     * Checks if the specified number is within the specified bounds (i.e. min and max value).
     *
     * @param min
     *        a number (i.e. lower bound of an interval)
     * @param number
     *        a number
     * @param max
     *        a number (i.e. upper bound of an interval)
     *
     * @return <code>true</code> if the number is with the specified bounds, else <code>false</code>
     */
    @Override
    public Result<Boolean> calculate(Number min, Number number, Number max) {

        ParameterCheckHelper.checkParameters(min, number, max);

        boolean result = number.isGreaterOrEqual(min) && number.isLesserOrEqual(max);

        return new Result<Boolean>(result);
    }

}
