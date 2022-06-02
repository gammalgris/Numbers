/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2022  Kristian Kutin
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

package jmul.math.numbers.operations;


import jmul.math.numbers.Number;
import jmul.math.numbers.NumberImpl;
import jmul.math.numbers.Sign;
import jmul.math.numbers.Signs;
import jmul.math.numbers.nodes.DigitNode;
import jmul.math.numbers.nodes.Nodes;
import jmul.math.operations.Result;
import jmul.math.operations.UnaryOperation;


/**
 * This function implementation negates a number.
 *
 * @author Kristian Kutin
 */
public class NegateNumberFunctionImpl implements UnaryOperation<Number> {

    /**
     * The default constructor.
     */
    public NegateNumberFunctionImpl() {

        super();
    }

    /**
     * Negates the specified number.
     *
     * @param n
     *        a number
     *
     * @return a number
     */
    @Override
    public Result<Number> calculate(Number n) {

        Sign sign = Signs.negate(n.sign());
        int base = n.base();
        DigitNode centerNode = Nodes.cloneLinkedList(n.centerNode());

        Number newNumber = new NumberImpl(sign, base, centerNode);

        return new Result<Number>(newNumber);
    }

}
