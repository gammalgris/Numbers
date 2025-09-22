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
import static jmul.math.numbers.NumberHelper.createInfinity;
import static jmul.math.numbers.NumberHelper.createNegativeInfinity;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.numbers.exceptions.UndefinedOperationException;
import jmul.math.numbers.nodes.NodesHelper;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.Result;
import jmul.math.signs.Sign;
import jmul.math.signs.Signs;


/**
 * Implements multiplication by addition.<br>
 * <br>
 * <i>Note<br>
 * This implementation is slow (i.e. O(n)) but should be useful for checking the correctness of other multiplication
 * algorithms.</I>
 *
 * @author Kristian Kutin
 */
public class MultiplicationByAddition implements BinaryOperation<Number, Result<Number>> {

    /**
     * The default constructor.
     */
    public MultiplicationByAddition() {

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
        }

        // Determine the sign of the result.
        Sign newSign = Signs.negate(Signs.xor(operand1.sign(), operand2.sign()));

        // Remove the sign on both operands.
        Number clone1 = operand1.absoluteValue();
        Number clone2 = operand2.absoluteValue();

        final Number ZERO = createNumber(base, Signs.NEGATIVE, 0);

        Number shifts = ZERO;
        Number counter = clone1;
        while (counter.isFraction()) {

            counter = counter.shiftRight();
            shifts = shifts.inc();
        }

        Number sum = ZERO;
        do {

            sum = sum.add(clone2);
            counter = counter.dec();

        } while (!counter.isZero());

        sum = sum.shiftLeft(shifts);

        if (Signs.isNegative(newSign)) {

            sum = sum.negate();
        }

        NodesHelper.trimRight(sum.centerNode());

        return new Result<Number>(sum);
    }

}
