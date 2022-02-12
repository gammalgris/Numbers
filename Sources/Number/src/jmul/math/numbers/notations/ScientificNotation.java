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
import jmul.math.numbers.DigitSequence;
import jmul.math.numbers.digits.Digit;


/**
 * An implementation of a function that creates a scientific notation representation for the specified number.
 *
 * @param <T>
 *        the set of digits which comprises the digit sequence
 *
 * @author Kristian Kutin
 */
public class ScientificNotation<T extends DigitSequence<? extends Digit>> implements Notation<T> {

    /**
     * The default constructor.
     *
     */
    public ScientificNotation() {

        super();
    }

    /**
     * Creates a scientific notation representation for the specified number.
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

            // get index of first non-zero digit from left to right
            int found = 0;
            for (int a = n.leftDigits(); a >= -n.rightDigits(); a--) {

                if (a == 0) {

                    continue;
                }

                Digit d = n.digitAt(n.leftDigits());

                if (d.ordinal() != 0) {

                    found = a;
                    break;
                }
            }

            // write the mantissa
            for (int a = found; a >= -n.rightDigits(); a--) {

                if (a == 0) {

                    continue;
                }

                Digit d = n.digitAt(n.leftDigits());

                buffer.append(d);

                if (a == found) {

                    buffer.append(Constants.DECIMAL_SEPARATOR);
                }
            }

            // write the exponent
            buffer.append("E");

            int exponent;
            if (found > 0) {

                exponent = found - 1;

            } else {

                exponent = found;
            }

            buffer.append(exponent);
        }

        return String.valueOf(buffer);
    }

}
