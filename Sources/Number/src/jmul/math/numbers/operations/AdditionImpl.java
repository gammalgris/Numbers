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
import jmul.math.numbers.Sign;
import jmul.math.numbers.Signs;
import jmul.math.numbers.digits.Digit;
import jmul.math.numbers.system.NumeralSystems;


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
public class AdditionImpl<T extends DigitSequence<? extends Digit>> extends AbstractOperation<T> implements Addition<T> {

    /**
     * The default constructor.
     */
    public AdditionImpl() {

        super();
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
    public T add(T n, T m) {

        super.checkParameters(n, m);

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

            return (T) NumeralSystems.toDigitSequence(base, sign, digits, null);
        }

        int left = Math.max(n.leftDigits(), m.leftDigits());
        int right = Math.max(n.rightDigits(), m.rightDigits());
        int index = Math.max(0, left - 1);

        int carry = 0;
        for (int a = -right; a <= left; a++) {

            if (a == 0) {

                continue;
            }

            Digit d1 = n.digitAt(a);
            Digit d2 = m.digitAt(a);

            int result = d1.ordinal() + d2.ordinal();

            result += carry;

            if (result >= base) {

                carry = result / base;
                result = result % base;

            } else {

                carry = 0;
            }

            Digit r = NumeralSystems.ordinalToDigit(base, result);
            digits.add(0, r);
        }

        if (carry > 0) {

            Digit r = NumeralSystems.ordinalToDigit(base, carry);
            digits.add(0, r);
            index++;
        }

        return (T) NumeralSystems.toDigitSequence(base, sign, Collections.unmodifiableList(digits), index);
    }

}
