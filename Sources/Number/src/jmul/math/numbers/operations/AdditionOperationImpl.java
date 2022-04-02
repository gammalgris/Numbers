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

package jmul.math.numbers.operations;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jmul.math.numbers.DigitSequence;
import jmul.math.numbers.FunctionIdentifiers;
import jmul.math.numbers.Sign;
import jmul.math.numbers.Signs;
import jmul.math.numbers.digits.Digit;
import jmul.math.numbers.digits.operations.DigitAddition;
import jmul.math.numbers.system.NumeralSystems;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.Result;
import jmul.math.operations.ResultImpl;
import jmul.math.operations.ResultWithCarry;

import jmul.singletons.FunctionSingletons;


/**
 * An implementation of a function that adds two numbers.<br>
 * <br>
 * <i>Note:<br>
 * Due to the generics the code looks a bit ugly and repretitive. Maybe there is a better way to implement this
 * class.</i>
 *
 * @param <T>
 *        The actual implementation of a digit sequence (i,.e. a number)
 *
 * @author Kristian Kutin
 */
public class AdditionOperationImpl<T extends DigitSequence<? extends Digit>> implements BinaryOperation<T, Result<T>> {

    /**
     * The default constructor.
     */
    public AdditionOperationImpl() {

        super();

        initFunctions();
    }

    /**
     * Initializes all required functions.
     */
    private void initFunctions() {

        if (!FunctionSingletons.existsFunction(FunctionIdentifiers.DIGIT_ADDITION)) {

            BinaryOperation<Digit, ResultWithCarry<Digit>> function = new DigitAddition<>();
            FunctionSingletons.putFunction(FunctionIdentifiers.DIGIT_ADDITION, function);
        }
    }

    /**
     * Returns a reference on a function.
     *
     * @return a function reference
     */
    private BinaryOperation<Digit, ResultWithCarry<Digit>> getDigitAdditionFunction() {

        return (BinaryOperation<Digit, ResultWithCarry<Digit>>) FunctionSingletons.getFunction(FunctionIdentifiers.DIGIT_ADDITION);
    }

    /**
     * Checks the two specified parameters.
     *
     * @param n
     *        a number
     * @param m
     *        a number
     */
    protected void checkParameters(T n, T m) {

        if (n.base() != m.base()) {

            String message =
                String.format("The specified numbers are from different numeral systems (base=%d; base=%d)!", n.base(),
                              m.base());
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Adds the specified numbers and returns a number containing the result.
     *
     * @param n
     *        a number
     * @param m
     *        a number
     *
     * @return a result
     */
    @Override
    public Result<T> calculate(T n, T m) {

        checkParameters(n, m);

        int base = n.base();

        Sign sign = Signs.POSITIVE;
        if (n.isPositive() && m.isNegative()) {

            //TODO substract n-m
            throw new UnsupportedOperationException();

        } else if (n.isNegative() && m.isPositive()) {

            //TODO substract m-n
            throw new UnsupportedOperationException();

        } else if (n.isNegative() && m.isNegative()) {

            sign = Signs.NEGATIVE;
        }

        List<Digit> digits = new ArrayList<>(); // List<? extends Digit> is a problem!

        if (n.isInfinity() || m.isInfinity()) {

            return new ResultImpl(NumeralSystems.toDigitSequence(base, sign, digits, null));
        }

        int left = Math.max(n.leftDigits(), m.leftDigits());
        int right = Math.max(n.rightDigits(), m.rightDigits());
        int index = Math.max(0, left - 1);

        Digit carry = null;
        for (int a = -right; a <= left; a++) {

            if (a == 0) {

                continue;
            }

            Digit d1 = n.digitAt(a);
            Digit d2 = m.digitAt(a);

            ResultWithCarry<Digit> result = getDigitAdditionFunction().calculate(d1, d2);

            Digit r = result.result();
            carry = result.carry();

            digits.add(0, r);
        }

        if ((carry != null) && !carry.isZero()) {

            digits.add(0, carry);
            index++;
        }

        return new ResultImpl(NumeralSystems.toDigitSequence(base, sign, Collections.unmodifiableList(digits), index));
    }

}
