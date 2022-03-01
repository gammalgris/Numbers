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
import java.util.List;

import jmul.math.numbers.DigitSequence;
import jmul.math.numbers.Sign;
import jmul.math.numbers.Signs;
import jmul.math.numbers.builders.SubtractionOperation;
import jmul.math.numbers.digits.Digit;
import jmul.math.numbers.system.NumeralSystems;


/**
 * An implementation of a function that subtracts two numbers.
 * 
 * @param <T>
 *        The actual implementation of a digit sequence (i,.e. a number)
 *
 * @author Kristian Kutin
 */
public class SubtractionOperationImpl<T extends DigitSequence<? extends Digit>> extends AbstractOperation<T> implements SubtractionOperation<T> {

    /**
     * The default constructor.
     */
    public SubtractionOperationImpl() {

        super();
    }

    /**
     * Subtracts the specified number m from the specified number n and returns a number containing the result.
     *
     * @param n
     *        a number
     * @param m
     *        a number
     *
     * @return a result
     */
    @Override
    public T subtract(T n, T m) {

        super.checkParameters(n, m);

        int base = n.base();

        Sign sign = Signs.POSITIVE;
        if (n.isPositive() && m.isNegative()) {

            //TODO add n + -m
            throw new UnsupportedOperationException();

        } else if (n.isNegative() && m.isPositive()) {

            //TODO add substract m + -n
            throw new UnsupportedOperationException();

        } else if (n.isNegative() && m.isNegative()) {

            //TODO add substract -n - -m
            throw new UnsupportedOperationException();
        }

        List<Digit> digits = new ArrayList<>(); // List<? extends Digit> is a problem!

        if (n.isInfinity() || m.isInfinity()) {

            return (T) NumeralSystems.toDigitSequence(base, sign, digits, null);
        }

        int left = Math.max(n.leftDigits(), m.leftDigits());
        int right = Math.max(n.rightDigits(), m.rightDigits());
        int index = Math.max(0, left - 1);


        return null;
    }

}
