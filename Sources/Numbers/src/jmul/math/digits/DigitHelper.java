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

package jmul.math.digits;


import java.util.HashSet;
import java.util.Set;


/**
 * A helper class wit various utilty functions regardign digits and number bases.
 *
 * @author Kristian Kutin
 */
public class DigitHelper {

    /**
     * The default constructor.
     */
    private DigitHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Determines all digits which are rounded down. Zero is ignored since it remains unchanged by rounding.
     *
     * @param base
     *        a number base
     *
     * @return a set of digits
     */
    public static Set<Digit> determineRoundDownDigits(int base) {

        Set<Digit> digitSet = new HashSet<>();

        int half = base / 2;
        boolean evenBase = (base % 2) == 0;

        if (evenBase) {

            for (int ordinal = 1; ordinal < half; ordinal++) {

                Digit digit = PositionalNumeralSystems.ordinalToDigit(base, ordinal);
                digitSet.add(digit);
            }

        } else {

            for (int ordinal = 1; ordinal <= half; ordinal++) {

                Digit digit = PositionalNumeralSystems.ordinalToDigit(base, ordinal);
                digitSet.add(digit);
            }
        }

        return digitSet;
    }

    /**
     * Determines all digits which are rounded up.
     *
     * @param base
     *        a number base
     *
     * @return a digit set
     */
    public static Set<Digit> determineRoundUpDigits(int base) {

        Set<Digit> roundUp = new HashSet<>();

        for (int ordinal = 1; ordinal < base; ordinal++) {

            Digit digit = PositionalNumeralSystems.ordinalToDigit(base, ordinal);
            roundUp.add(digit);
        }

        Set<Digit> roundDown = determineRoundDownDigits(base);
        Set<Digit> middle = determineMiddleDigit(base);

        roundUp.removeAll(roundDown);
        roundUp.removeAll(middle);

        return roundUp;
    }

    /**
     * Determines the middle digit. The middle digit may be subject to other rounding rules in order
     * to minimize rounding errors.
     *
     * @param base
     *        a number base
     *
     * @return a digit set
     */
    public static Set<Digit> determineMiddleDigit(int base) {

        Set<Digit> digitSet = new HashSet<>();
        int half = base / 2;
        boolean evenBase = (base % 2) == 0;
        if (evenBase) {

            Digit digit = PositionalNumeralSystems.ordinalToDigit(base, half);
            digitSet.add(digit);
        }

        return digitSet;
    }

}
