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

package jmul.math.functions.implementations;


import jmul.math.digits.Digit;
import jmul.math.functions.FunctionSingletons;
import jmul.math.functions.repository.FunctionIdentifiers;
import jmul.math.numbers.Number;
import jmul.math.numbers.NumberImpl;
import jmul.math.signs.Sign;
import static jmul.math.signs.Signs.POSITIVE;
import jmul.math.numbers.exceptions.UndefinedOperationException;
import jmul.math.numbers.nodes.DigitNode;
import jmul.math.numbers.nodes.NodesHelper;
import jmul.math.operations.Result;
import jmul.math.operations.UnaryOperation;


/**
 * This function implementation calculates nines' complement of a number.
 *
 * @author Kristian Kutin
 */
public class NumberComplement implements UnaryOperation<Number, Result<Number>> {

    /**
     * The default constructor.
     */
    public NumberComplement() {

        super();
    }

    /**
     * Calculates and returns the complement of the specified number.
     *
     * @param n
     *        a number
     *
     * @return the complement of the specified number
     */
    @Override
    public Result<Number> calculate(Number n) {

        if (n == null) {

            throw new IllegalArgumentException("The specified number is null!");
        }

        if (n.isInfinity()) {

            String message = String.format("Cannot calculate the complement for %s!", n.toString());
            throw new UndefinedOperationException(message);
        }

        int base = n.base();
        Sign sign = POSITIVE;

        UnaryOperation<Digit, Result<Digit>> function =
            (UnaryOperation<Digit, Result<Digit>>) FunctionSingletons.getFunction(FunctionIdentifiers.DIGIT_COMPLEMENT_FUNCTION);


        // Handle the center node first
        DigitNode centerNode = n.centerNode();
        DigitNode resultCenterNode;

        {
            Result<Digit> result = function.calculate(centerNode.digit());
            Digit complement = result.result();

            resultCenterNode = NodesHelper.createNode(complement);
        }


        // go left
        DigitNode leftNode = centerNode.leftNode();
        DigitNode resultLeftNode = resultCenterNode;

        while (true) {

            if (leftNode == null) {

                break;
            }

            Result<Digit> result = function.calculate(leftNode.digit());
            Digit complement = result.result();

            DigitNode newNode = NodesHelper.createNode(complement);
            NodesHelper.linkNodes(newNode, resultLeftNode);
            resultLeftNode = resultLeftNode.leftNode();

            leftNode = leftNode.leftNode();
        }


        // go right
        DigitNode rightNode = centerNode.rightNode();
        DigitNode resultRightNode = resultCenterNode;

        while (true) {

            if (rightNode == null) {

                break;
            }

            Result<Digit> result = function.calculate(rightNode.digit());
            Digit complement = result.result();

            DigitNode newNode = NodesHelper.createNode(complement);
            NodesHelper.linkNodes(resultRightNode, newNode);
            resultRightNode = resultRightNode.rightNode();

            rightNode = rightNode.rightNode();
        }


        Number result = new NumberImpl(base, sign, resultCenterNode);

        return new Result<Number>(result);
    }

}
