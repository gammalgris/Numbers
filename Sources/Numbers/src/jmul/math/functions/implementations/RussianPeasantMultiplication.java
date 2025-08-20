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

package jmul.math.functions.implementations;


import java.util.HashMap;
import java.util.Map;

import jmul.math.numbers.Number;
import jmul.math.numbers.NumberHelper;
import static jmul.math.numbers.NumberHelper.createInfinity;
import static jmul.math.numbers.NumberHelper.createNegativeInfinity;
import static jmul.math.numbers.NumberHelper.createNumber;
import static jmul.math.numbers.creation.CreationParameters.CLONE;
import jmul.math.numbers.exceptions.UndefinedOperationException;
import jmul.math.numbers.nodes.DigitNode;
import jmul.math.numbers.nodes.NodesHelper;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.Result;
import jmul.math.signs.Sign;
import jmul.math.signs.Signs;


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
 * @deprecated There are issues with odd number bases and shifting of the decimal separator.
 *
 * @author Kristian Kutin
 */
@Deprecated
public class RussianPeasantMultiplication implements BinaryOperation<Number, Result<Number>> {

    /**
     * The default constructor.
     */
    public RussianPeasantMultiplication() {

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

            String operation = String.format("%s * %s", operand1, operand2);
            throw new UndefinedOperationException(operation, operand1, operand2);

        } else if (operand1.isZero() && operand2.isInfinity()) {

            String operation = String.format("%s * %s", operand1, operand2);
            throw new UndefinedOperationException(operation, operand1, operand2);

        } else if (operand1.isInfinity() || operand2.isInfinity()) {

            if (operand1.isNegative() && !operand2.isNegative()) {

                Number result = createNegativeInfinity(base);
                return new Result<Number>(result);

            } else if (!operand1.isNegative() && operand2.isNegative()) {

                Number result = createNegativeInfinity(base);
                return new Result<Number>(result);

            } else {

                Number result = createInfinity(base);
                return new Result<Number>(result);
            }

        } else if (operand1.isZero() || operand2.isZero()) {

            Number result = createNumber(base, Signs.POSITIVE, 0);
            return new Result<Number>(result);

        } else {

            final Number ONE = createNumber(base, Signs.POSITIVE, 1);
            final Number MINUS_ONE = createNumber(base, Signs.NEGATIVE, 1);

            if (operand1.equals(ONE)) {

                Number result = NumberHelper.createNumber(CLONE, operand2);
                return new Result<Number>(result);

            } else if (operand2.equals(ONE)) {

                Number result = NumberHelper.createNumber(CLONE, operand1);
                return new Result<Number>(result);

            } else if (operand1.equals(MINUS_ONE)) {

                Sign newSign = Signs.negate(Signs.xor(operand1.sign(), operand2.sign()));
                Number result = NumberHelper.createNumber(CLONE, operand2);
                if (newSign != result.sign()) {

                    result = result.negate();
                }
                return new Result<Number>(result);

            } else if (operand2.equals(MINUS_ONE)) {

                Sign newSign = Signs.negate(Signs.xor(operand1.sign(), operand2.sign()));
                Number result = NumberHelper.createNumber(CLONE, operand1);
                if (newSign != result.sign()) {

                    result = result.negate();
                }
                return new Result<Number>(result);
            }
        }

        // Determine the sign of the result.
        Sign newSign = Signs.negate(Signs.xor(operand1.sign(), operand2.sign()));

        // Remove the sign on both operands.
        Number clone1 = operand1.absoluteValue();
        Number clone2 = operand2.absoluteValue();

        // Determine how many shifts are requiremed to transform both operands into
        // integers.
        Number shifts = createNumber(base, Signs.NEGATIVE, 1);

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
        clone1 = clone1.shiftRight(shifts);
        clone2 = clone2.shiftRight(shifts);

        // Multiply the operands, i.e. write the second operand to the left and the first operand to the right.
        // Keep halving the number on the left and doubling the number on the right until the number on the
        // left is down to one. Add all numbers on the right where the corresponding number on the left is odd.
        // The result is the product of the multiplication.
        final Number ONE = createNumber(base, Signs.POSITIVE, 1);
        Map<Number, Number> remainderSummandMap = new HashMap<>();

        while (true) {

            if (clone2.isOdd()) {

                remainderSummandMap.put(clone2, clone1);
            }

            if (clone2.equals(ONE)) {

                break;
            }

            clone2 = clone2.halving();
            clone2 = clone2.removeFractionPart();
            clone1 = clone1.doubling();
        }

        Number sum = createNumber(base, Signs.POSITIVE, 0);

        for (Map.Entry<Number, Number> entry : remainderSummandMap.entrySet()) {

            Number value = entry.getValue();
            sum = sum.add(value);
        }

        // Reverse the shifts done before. The number of reverse shifts has to be doubled to get the
        // right result.
        shifts = shifts.doubling();
        Number result = sum.shiftLeft(shifts);

        // Apply the sign to the result.
        if (Signs.isNegative(newSign)) {

            result = result.negate();
        }

        // Trim leading and trailing zeroes.
        NodesHelper.trimLeft(result.centerNode());
        NodesHelper.trimRight(result.centerNode());

        return new Result<Number>(result);
    }

}
