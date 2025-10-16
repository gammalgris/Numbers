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


import jmul.math.digits.Digit;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.numbers.nodes.DigitNode;
import jmul.math.numbers.nodes.NodesHelper;
import jmul.math.operations.Result;
import jmul.math.operations.UnaryOperation;
import jmul.math.signs.Signs;


/**
 * An implementation of a function that translates a digit to a number.
 *
 * @author Kristian Kutin
 */
public class DigitToNumberConversion implements UnaryOperation<Digit, Result<Number>> {

    /**
     * The default constructor.
     */
    public DigitToNumberConversion() {

        super();
    }

    /**
     * Translates the specified digit into a number.
     *
     * @param d
     *        a digit
     *
     * @return a number (i.e. an integer)
     */
    @Override
    public Result<Number> calculate(Digit d) {

        ParameterCheckHelper.checkParameter(d);

        int base = d.base();
        DigitNode centerNode = NodesHelper.createNode(d);
        Number result = createNumber(base, Signs.POSITIVE, centerNode);

        return new Result<Number>(result);
    }

}
