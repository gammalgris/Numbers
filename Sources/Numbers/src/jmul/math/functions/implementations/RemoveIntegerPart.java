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


import jmul.math.digits.Digit;
import jmul.math.digits.PositionalNumeralSystems;
import static jmul.math.functions.implementations.ParameterCheckHelper.checkParameter;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.numbers.NumberImpl;
import jmul.math.numbers.nodes.DigitNode;
import jmul.math.numbers.nodes.NodesHelper;
import jmul.math.operations.Result;
import jmul.math.operations.UnaryOperation;
import jmul.math.signs.Sign;
import jmul.math.signs.Signs;


/**
 * This is an implementation of a function that removes the integer part from a number.
 *
 * @author Kristian Kutin
 */
public class RemoveIntegerPart implements UnaryOperation<Number, Result<Number>> {

    /**
     * The default constructor.
     */
    public RemoveIntegerPart() {

        super();
    }

    /**
     * Returns a copy of the specified number which is truncated (i.e. the fraction part is removed).
     *
     * @param operand
     *        a number
     *
     * @return a truncated number
     */
    @Override
    public Result<Number> calculate(Number operand) {

        checkParameter(operand);

        int base = operand.base();

        if (operand.isInfinity()) {

            String symbol = PositionalNumeralSystems.toString(base, 0);
            Number clone = createNumber(base, symbol);
            return new Result<Number>(clone);
        }

        Sign sign = operand.sign();

        DigitNode center = operand.centerNode();
        Digit zeroDigit = PositionalNumeralSystems.ordinalToDigit(base, 0);

        DigitNode clonedCenter = NodesHelper.createNode(zeroDigit);

        DigitNode clonedRightTail = NodesHelper.cloneRightTail(center.rightNode());
        NodesHelper.linkNodes(clonedCenter, clonedRightTail);

        if ((clonedCenter.leftNode() == null) && (clonedCenter.rightNode() == null) && clonedCenter.digit().isZero()) {

            sign = Signs.POSITIVE;
        }

        Number clone = new NumberImpl(base, sign, clonedCenter);
        return new Result<Number>(clone);
    }

}
