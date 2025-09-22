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


import jmul.math.digits.Digit;
import static jmul.math.operations.implementations.ParameterCheckHelper.checkParameter;
import jmul.math.numbers.Number;
import jmul.math.numbers.NumberHelper;
import static jmul.math.numbers.NumberHelper.createNumber;
import static jmul.math.numbers.creation.CreationParameters.CLONE;
import jmul.math.numbers.nodes.DigitNode;
import jmul.math.numbers.nodes.NodesHelper;
import jmul.math.operations.Result;
import jmul.math.operations.UnaryOperation;
import jmul.math.signs.Sign;
import jmul.math.signs.Signs;


/**
 * This is an implementation of a function that removes the fraction part from a number.
 *
 * @author Kristian Kutin
 */
public class RemoveFractionPart implements UnaryOperation<Number, Result<Number>> {

    /**
     * The default constructor.
     */
    public RemoveFractionPart() {

        super();
    }

    /**
     * Returns a copy of the specified number which is truncated (i.e. the integer part is removed).
     *
     * @param operand
     *        a number
     *
     * @return a truncated number
     */
    @Override
    public Result<Number> calculate(Number operand) {

        checkParameter(operand);

        if (operand.isInfinity()) {

            Number clone = NumberHelper.createNumber(CLONE, operand);
            return new Result<Number>(clone);
        }

        int base = operand.base();
        Sign sign = operand.sign();

        DigitNode center = operand.centerNode();
        Digit digit = center.digit();

        DigitNode clonedCenter = NodesHelper.createNode(digit);
        DigitNode clonedLeftTail = NodesHelper.cloneLeftTail(center.leftNode());
        NodesHelper.linkNodes(clonedLeftTail, clonedCenter);

        if ((clonedLeftTail == null) && digit.isZero()) {

            sign = Signs.POSITIVE;
        }

        Number clone = createNumber(base, sign, clonedCenter);
        return new Result<Number>(clone);
    }

}
