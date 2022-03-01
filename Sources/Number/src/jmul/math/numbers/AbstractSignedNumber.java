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

package jmul.math.numbers;


import jmul.math.numbers.digits.Digit;


/**
 * An abstract base class for all implementations of a signed digit sequence.
 *
 * @param <T>
 *        the set of digits which comprises the digit sequence
 *
 * @author Kristian Kutin
 */
abstract class AbstractSignedNumber<T extends Digit> implements DigitSequence<T> {

    /**
     * The sign of this number (i.e. digit sequence).
     */
    protected final Sign sign;

    /**
     * Creates a new number (i.e. digit sequence) according to the specified parameters.
     *
     * @param aSign
     *        the sign for this number (i.e. digit sequence)
     */
    protected AbstractSignedNumber(Sign aSign) {

        super();

        sign = aSign;
    }

    /**
     * Returns the sign of this number (i.e. digit sequence).
     *
     * @return a sign
     */
    @Override
    public Sign sign() {

        return sign;
    }

    /**
     * Checks if this number is negative.
     *
     * @return <code>true</code> if this number is negative, else <code>false</code>
     */
    @Override
    public boolean isNegative() {

        return Signs.NEGATIVE.equals(sign());
    }

    /**
     * Checks if this number is positive.
     *
     * @return <code>true</code> if this number is positive, else <code>false</code>
     */
    @Override
    public boolean isPositive() {

        return Signs.POSITIVE.equals(sign());
    }

}
