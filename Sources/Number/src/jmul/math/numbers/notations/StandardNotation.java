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

package jmul.math.numbers.notations;


import jmul.math.numbers.Constants;
import jmul.math.numbers.digits.Digit;
import jmul.math.numbers.DigitSequence;


/**
 * An implementation of a function that creates a standard notation representation for the specified number.
 *
 * @param <T>
 *        the set of digits which comprises the digit sequence
 *
 * @author Kristian Kutin
 */
public class StandardNotation<T extends DigitSequence<? extends Digit>> implements Notation<T> {

    /**
     * The default constructor.
     *
     */
    public StandardNotation() {

        super();
    }

    /**
     * Creates a standard notation representation for the specified number.
     *
     * @param n
     *        a number
     *
     * @return a standard notation representation
     */
    @Override
    public String toString(T n) {

        StringBuilder buffer = new StringBuilder();

        if (n.isNegative()) {

            buffer.append(n.sign().symbol());
        }

        if (n.digits() == 0) {

            buffer.append(Constants.INFINITY_REPRESENTATION);

        } else {

            for (int a = n.leftDigits(); a > 0; a--) {

                Digit digit = n.digitAt(a);
                buffer.append(digit.symbol());
            }

            if (n.rightDigits() > 0) {

                buffer.append(Constants.DECIMAL_SEPARATOR);

                for (int a = 1; a <= n.rightDigits(); a++) {

                    Digit digit = n.digitAt(-a);
                    buffer.append(digit.symbol());
                }
            }
        }

        return String.valueOf(buffer);
    }

}
