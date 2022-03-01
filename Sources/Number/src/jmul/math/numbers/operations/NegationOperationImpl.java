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
import jmul.math.numbers.builders.NegationOperation;
import jmul.math.numbers.digits.Digit;
import jmul.math.numbers.system.NumeralSystems;


/**
 * An implementation of a function for negating a number.
 *
 * @param <T>
 *        The actual implementation of a digit sequence (i,.e. a number)
 *
 * @author Kristian Kutin
 */
public class NegationOperationImpl<T extends DigitSequence<? extends Digit>> extends AbstractOperation<T> implements NegationOperation<T> {

    /**
     * The default constructor.
     */
    public NegationOperationImpl() {

        super();
    }

    /**
     * Negates the specified number.
     *
     * @param n
     *        a number
     *
     * @return the negated number
     */
    @Override
    public T negate(T n) {

        int base = n.base();
        Sign sign = Signs.negate(n.sign());
        List<Digit> digits = new ArrayList<>();

        if (n.isInfinity()) {

            return (T) NumeralSystems.toDigitSequence(base, sign, digits, null);
        }

        int left = n.leftDigits();
        int right = n.rightDigits();
        int index = Math.max(0, left - 1);

        for (int a = -right; a <= left; a++) {

            if (a == 0) {

                continue;
            }

            Digit d = n.digitAt(a);
            digits.add(0, d);
        }

        return (T) NumeralSystems.toDigitSequence(base, sign, Collections.unmodifiableList(digits), index);
    }

}
