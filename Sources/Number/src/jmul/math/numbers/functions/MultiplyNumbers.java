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


import java.util.HashMap;
import java.util.Map;

import jmul.math.numbers.Number;
import jmul.math.numbers.NumberImpl;
import jmul.math.numbers.Sign;
import jmul.math.numbers.Signs;
import jmul.math.numbers.exceptions.UndefinedOperationException;
import jmul.math.numbers.nodes.DigitNode;
import jmul.math.numbers.nodes.NodesHelper;
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

    /**
     * The default constructor.
     */
    public MultiplyNumbers() {

        super();
    }

    /**
     * Multiplies the specified numbers and returns the product.
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

        int base = operand1.base();

        // Handle special cases which can be resolved without computation.
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

        // Determine the sign of the result.
        Sign sign;
        if (operand1.isNegative()) {

            sign = operand1.sign();

        } else if (operand2.isNegative()) {

            sign = operand2.sign();

        } else {

            sign = operand1.sign();
        }

        // Remove the sign on both operands.
        Number clone1 = operand1.absoluteValue();
        Number clone2 = operand2.absoluteValue();

        // Determine how many shifts are requiremed to transform both operands into
        // integers.
        Number shifts = new NumberImpl(base, "-1");

        DigitNode currentNode1 = operand1.centerNode();
        DigitNode currentNode2 = operand2.centerNode();

        while (true) {

            if ((currentNode1 == null) && (currentNode2 == null)) {

                break;
            }

            if (currentNode1 != null) {

                currentNode1 = currentNode1.rightNode();
            }

            if (currentNode2 != null) {

                currentNode2 = currentNode2.rightNode();
            }

            shifts = shifts.inc();
        }

        // Transform the operands into integers.
        clone1 = operand1.shiftRight(shifts);
        clone2 = operand2.shiftRight(shifts);

        // Multiply the operands, i.e. write the second operand to the left and the first operand to the right.
        // Keep halving the number on the left and doubling the number on the right until the number on the
        // left is down to one. Add all numbers on the right where the corresponding number on the left is odd.
        // The result is the product of the multiplication.
        final Number ONE = new NumberImpl(base, "1");
        Map<Number, Number> remainderSummandMap = new HashMap<>();

        while (true) {

            if (clone2.isOdd()) {

                remainderSummandMap.put(clone2, clone1);
            }

            if (clone2.equals(ONE)) {

                break;
            }

            clone2 = clone2.halving();
            clone2 = clone2.truncate();
            clone1 = clone1.doubling();
        }

        Number sum = new NumberImpl(base, "0");

        for (Map.Entry<Number, Number> entry : remainderSummandMap.entrySet()) {

            Number value = entry.getValue();
            sum = sum.add(value);
        }

        // Reverse the shifts done before. The number of reverse shifts has to be doubled to get the
        // right result.
        shifts = shifts.doubling();
        Number result = sum.shiftLeft(shifts);

        // Apply the sign to the result.
        if (Signs.isNegative(sign)) {

            result = result.negate();
        }

        // Trim leading and trailing zeroes.
        NodesHelper.trimLeft(result.centerNode());
        NodesHelper.trimRight(result.centerNode());

        return new Result<Number>(result);
    }

}
