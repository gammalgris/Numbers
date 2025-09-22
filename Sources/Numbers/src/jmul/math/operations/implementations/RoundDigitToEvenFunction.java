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


import java.util.Set;

import jmul.math.digits.Digit;
import jmul.math.digits.DigitHelper;
import jmul.math.digits.PositionalNumeralSystems;
import jmul.math.operations.OperationSingletons;
import jmul.math.operations.repository.OperationIdentifiers;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.ResultWithCarry;


/**
 * A function for rounding digits (see <a href="https://en.wikipedia.org/wiki/Rounding">rounding to even</a>).
 *
 * @author Kristian Kutin
 */
public class RoundDigitToEvenFunction implements BinaryOperation<Digit, ResultWithCarry<Digit>> {

    /**
     * The default constructor.
     */
    public RoundDigitToEvenFunction() {

        super();
    }

    /**
     * Round the first specified number according to the second specified number.
     *
     * @param digit1
     *        a digit
     * @param digit2
     *        the digit to the right of the first digit
     *
     * @return the rounding result including a carry for the digit left of the first specified digit
     */
    @Override
    public ResultWithCarry<Digit> calculate(Digit digit1, Digit digit2) {

        ParameterCheckHelper.checkParameter(digit1);
        ParameterCheckHelper.checkParameter(digit2);

        final int base = digit1.base();

        final Digit ONE = PositionalNumeralSystems.ordinalToDigit(base, 1);
        final Digit ZERO = PositionalNumeralSystems.ordinalToDigit(base, 0);

        Set<Digit> roundDown = DigitHelper.determineRoundDownDigits(base);
        Set<Digit> middle = DigitHelper.determineMiddleDigit(base);

        if (digit1.isEven()) {

            if (digit2.isZero() || roundDown.contains(digit2) || middle.contains(digit2)) {

                return new ResultWithCarry<Digit>(digit1, ZERO);
            }

        } else {

            if (digit2.isZero() || roundDown.contains(digit2)) {

                return new ResultWithCarry<Digit>(digit1, ZERO);
            }
        }

        BinaryOperation<Digit, ResultWithCarry<Digit>> function =
            (BinaryOperation<Digit, ResultWithCarry<Digit>>) OperationSingletons.getFunction(OperationIdentifiers.ADD_DIGITS_FUNCTION);
        ResultWithCarry<Digit> result = function.calculate(digit1, ONE);

        return result;
    }

}
