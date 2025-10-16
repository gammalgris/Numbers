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


import jmul.math.Math;
import jmul.math.digits.Digit;
import jmul.math.numbers.Number;
import jmul.math.numbers.NumberHelper;
import static jmul.math.numbers.creation.CreationParameters.CLONE;
import jmul.math.numbers.nodes.DigitNode;
import jmul.math.operations.OperationSingletons;
import jmul.math.operations.Result;
import jmul.math.operations.UnaryOperation;
import jmul.math.operations.repository.OperationIdentifiers;
import jmul.math.signs.Sign;
import jmul.math.signs.Signs;


/**
 * An implementation of a function that calculates the digit sum.
 *
 * @author Kristian Kutin
 */
public class NumberDigitSum implements UnaryOperation<Number, Result<Number>> {

    /**
     * The default constructor.
     */
    public NumberDigitSum() {

        super();
    }

    /**
     * Calculates the digit sum of the specified number.
     *
     * @param n
     *        a number (i.e. integer)
     *
     * @return the digit sum
     */
    @Override
    public Result<Number> calculate(Number n) {

        ParameterCheckHelper.checkInteger(n);

        if (n.isInfinity()) {

            Number clone = NumberHelper.createNumber(CLONE, n);
            return new Result<Number>(clone);
        }

        UnaryOperation<Digit, Result<Number>> function =
            (UnaryOperation<Digit, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.DIGIT_TO_NUMBER_FUNCTION);

        int base = n.base();
        Number sum = Math.ZERO.value(base);
        DigitNode currentNode = n.centerNode();

        while (currentNode != null) {

            Digit digit = currentNode.digit();

            Result<Number> result = function.calculate(digit);
            Number digitAsNumber = result.result();

            sum = sum.add(digitAsNumber);

            currentNode = currentNode.leftNode();
        }

        Sign sign = n.sign();
        if (Signs.isNegative(sign)) {

            sum = sum.negate();
        }

        return new Result<Number>(sum);
    }

}
