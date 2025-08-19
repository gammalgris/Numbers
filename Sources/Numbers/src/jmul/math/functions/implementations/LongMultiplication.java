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


import java.util.ArrayList;
import java.util.List;

import jmul.math.digits.Digit;
import jmul.math.digits.PositionalNumeralSystems;
import jmul.math.functions.FunctionSingletons;
import jmul.math.functions.repository.FunctionIdentifiers;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createInfinity;
import static jmul.math.numbers.NumberHelper.createNegativeInfinity;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.numbers.exceptions.UndefinedOperationException;
import jmul.math.numbers.nodes.DigitNode;
import jmul.math.numbers.nodes.NodesHelper;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.Result;
import jmul.math.operations.ResultWithCarry;
import jmul.math.signs.Sign;
import jmul.math.signs.Signs;


/**
 * Implements the long multiplication.
 *
 * @author Kristian Kutin
 */
public class LongMultiplication implements BinaryOperation<Number, Result<Number>> {

    /**
     * The default constructor.
     */
    public LongMultiplication() {

        super();
    }

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


        final Number ZERO = createNumber(base, Signs.NEGATIVE, 0);

        Number shifts1 = ZERO;
        Number shifts2 = ZERO;

        DigitNode rightEndNode1 = operand1.centerNode();
        DigitNode rightEndNode2 = operand2.centerNode();

        BinaryOperation<Digit, ResultWithCarry<Digit>> multiplyDigitsFunction =
            (BinaryOperation<Digit, ResultWithCarry<Digit>>) FunctionSingletons.getFunction(FunctionIdentifiers.MULTIPLY_DIGITS_FUNCTION);
        BinaryOperation<Digit, ResultWithCarry<Digit>> addDigitsFunction =
            (BinaryOperation<Digit, ResultWithCarry<Digit>>) FunctionSingletons.getFunction(FunctionIdentifiers.ADD_DIGITS_FUNCTION);

        while (true) {

            if (rightEndNode1.rightNode() == null) {

                break;
            }

            rightEndNode1 = rightEndNode1.rightNode();
            shifts1 = shifts1.inc();
        }

        while (true) {

            if (rightEndNode2.rightNode() == null) {

                break;
            }

            rightEndNode2 = rightEndNode2.rightNode();
            shifts2 = shifts2.inc();
        }


        DigitNode currentNode1 = rightEndNode1;
        DigitNode currentNode2 = rightEndNode2;

        List<Number> rowResults = new ArrayList<>();
        final Digit ZERO_DIGIT = PositionalNumeralSystems.ordinalToDigit(base, 0);
        Number totalShifts = ZERO;

        while (currentNode2 != null) {

            Digit currentDigit2 = currentNode2.digit();

            DigitNode tempNode = null;
            Number shiftCounter = totalShifts;
            while (!shiftCounter.isZero()) {

                DigitNode newNode = NodesHelper.createNode(ZERO_DIGIT);
                NodesHelper.linkNodes(newNode, tempNode);
                tempNode = newNode;

                shiftCounter = shiftCounter.dec();
            }

            Digit previousCarry = ZERO_DIGIT;
            while (currentNode1 != null) {

                Digit currentDigit1 = currentNode1.digit();

                ResultWithCarry<Digit> result = multiplyDigitsFunction.calculate(currentDigit1, currentDigit2);
                Digit product = result.result();
                Digit carry = result.carry();

                ResultWithCarry<Digit> result2 = addDigitsFunction.calculate(product, previousCarry);
                Digit sum = result2.result();
                Digit carry2 = result2.carry();

                ResultWithCarry<Digit> result3 = addDigitsFunction.calculate(carry, carry2);
                Digit totalCarry = result3.result();
                Digit carry3 = result3.carry();

                if (!carry3.isZero()) {

                    throw new IllegalArgumentException("Something went wrong. No carry expected!");
                }

                DigitNode newNode = NodesHelper.createNode(sum);
                NodesHelper.linkNodes(newNode, tempNode);
                tempNode = newNode;

                previousCarry = totalCarry;

                currentNode1 = currentNode1.leftNode();
            }

            if (!previousCarry.isZero()) {

                DigitNode newNode = NodesHelper.createNode(previousCarry);
                NodesHelper.linkNodes(newNode, tempNode);
                tempNode = newNode;
            }

            tempNode = NodesHelper.moveRight(tempNode);

            Number tempNumber = createNumber(base, Signs.POSITIVE, tempNode);
            rowResults.add(tempNumber);

            currentNode1 = rightEndNode1;
            currentNode2 = currentNode2.leftNode();
            totalShifts = totalShifts.inc();
        }


        Number sum = ZERO;
        for (Number row : rowResults) {

            sum = sum.add(row);
        }


        sum = sum.shiftLeft(shifts1);
        sum = sum.shiftLeft(shifts2);


        if (Signs.isNegative(newSign)) {

            sum = sum.negate();
        }

        NodesHelper.trimRight(sum.centerNode());

        return new Result<Number>(sum);
    }

}
