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


import jmul.math.numbers.Number;
import jmul.math.numbers.NumberImpl;
import jmul.math.numbers.Sign;
import jmul.math.numbers.Signs;
import jmul.math.numbers.exceptions.UndefinedOperationException;
import jmul.math.numbers.nodes.DigitNode;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.Result;


/**
 * Implements the multiplication according to the russian peasant multiplication.
 * The implementation checks the operands regarding absolute values and signs and
 * compares the numbers. Depending on the actual case additional multiplications
 * or divisions are performed.<br>
 * <br>
 * <i>Note:<br>
 * For large numbers a different algorithm may be required. Make a statistical
 * analysis with different algorithms.</i>
 *
 * @author Kristian Kutin
 */
public class MultiplyNumbers implements BinaryOperation<Number, Result<Number>> {

    public MultiplyNumbers() {

        super();
    }

    @Override
    public Result<Number> calculate(Number operand1, Number operand2) {

        ParameterCheckHelper.checkParameters(operand1, operand2);

        int base = operand1.base();

        if (operand1.isInfinity() && operand2.isZero()) {

            throw new UndefinedOperationException("*", operand1, operand2);

        } else if (operand1.isZero() && operand2.isInfinity()) {

            throw new UndefinedOperationException("*", operand1, operand2);

        } else if (operand1.isInfinity() || operand2.isInfinity()) {

            if (operand1.isNegative() || operand2.isNegative()) {

                Number result = new NumberImpl(base, Signs.NEGATIVE);
                return new Result<Number>(result);

            } else {

                Number result = new NumberImpl(base);
                return new Result<Number>(result);
            }

        } else if (operand1.isZero() || operand2.isZero()) {

            Number result = new NumberImpl(base, "0");
            return new Result<Number>(result);
        }

        Sign sign;
        if (operand1.isNegative()) {

            sign = operand1.sign();

        } else if (operand2.isNegative()) {

            sign = operand2.sign();

        } else {

            sign = operand1.sign();
        }

        Number counter = new NumberImpl(base, "0");

        DigitNode currentNode1 = operand1.centerNode();
        DigitNode currentNode2 = operand2.centerNode();

        while (true) {

            if ((currentNode1 == null) && (currentNode2 == null)) {

                break;
            }

            counter = counter.inc();

            if (currentNode1.leftNode() != null) {

                currentNode1 = currentNode1.leftNode();
            }

            if (currentNode2.leftNode() != null) {

                currentNode2 = currentNode2.leftNode();
            }
        }

        Number clone1 = operand1.shiftRight(counter);
        Number clone2 = operand2.shiftRight(counter);


        // TODO Implement this method
        return null;
    }

}
