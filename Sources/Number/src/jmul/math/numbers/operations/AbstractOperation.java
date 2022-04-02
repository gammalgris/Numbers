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


import jmul.math.numbers.DigitSequence;
import jmul.math.numbers.digits.Digit;


/**
 *
 *
 * @param <T>
 *        The actual implementation of a digit sequence (i,.e. a number)
 *
 * @deprecated when all operations are implemented check what can be moved to this parent class. Consider deleting
 *             this class if it cannot be used.
 */
@Deprecated
abstract class AbstractOperation<T extends DigitSequence<? extends Digit>> {

    /**
     * The default constructor.
     */
    protected AbstractOperation() {

        super();
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

}
