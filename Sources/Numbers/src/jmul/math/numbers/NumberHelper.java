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


import jmul.math.digits.PositionalNumeralSystems;
import jmul.math.numbers.nodes.DigitNode;
import jmul.math.signs.Sign;
import jmul.math.signs.Signs;


/**
 * A class with utility functions for numbers.
 *
 * @author Kristian Kutin
 */
public final class NumberHelper {

    /**
     * The default constructor.
     */
    private NumberHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Creates a new number according to the specified parameters.
     *
     * @param sign
     *        the sign of the number
     * @param base
     *        a number base
     *
     * @return a number
     */
    public static Number createNumber(Sign sign, int base) {

        return new NumberImpl(base, sign);
    }

    /**
     * Creates a new number according to the specified parameters.
     *
     * @param base
     *        the number base
     *
     * @return a number
     */
    public static Number createNumber(int base) {

        return new NumberImpl(base);
    }

    /**
     * Creates a new number according to the specified parameters.
     *
     * @param base
     *        the number base
     * @param string
     *        a sequence of digits
     *
     * @return a number
     */
    public static Number createNumber(int base, String string) {

        if (string == null) {

            return new NumberImpl(base);
        }

        return new NumberImpl(base, string);
    }

    /**
     * Creates a new number according to the specified parameters.
     *
     * @param number
     *        a number
     *
     * @return a clone of the specified number
     */
    public static Number createNumber(Number number) {

        return new NumberImpl(number);
    }

    /**
     * Creates a new number according to the specified parameters. The number consists of a single digit.
     *
     * @param sign
     *        the sign of the number
     * @param base
     *        the number base
     * @param ordinal
     *        the ordinal value of the number (i.e. digit)
     *
     * @return a number
     */
    public static Number createNumber(Sign sign, int base, int ordinal) {

        String symbol = PositionalNumeralSystems.toString(base, ordinal);

        Number number;
        if (Signs.isNegative(sign)) {

            number = createNumber(base, Signs.NEGATIVE.toString() + symbol);

        } else {

            number = createNumber(base, symbol);
        }

        return number;
    }

    /**
     * Counts digits left of a decimal separator.<br>
     * <br>
     * <i>Note:<br>
     * The function may not work on too large numbers</i>
     *
     * @param number
     *        a number
     *
     * @return a digit count
     *
     * @deprecated integrate into number implementation and replace counter type.
     */
    @Deprecated
    public static int countLeftDigits(Number number) {

        int count = 0;

        if (number.isInfinity()) {

            return count;
        }

        DigitNode node = number.centerNode();

        while (node != null) {

            int previousCount = count;
            count++;

            if (count < previousCount) {

                throw new ArithmeticOverflowException();
            }

            node = node.leftNode();
        }

        return count;
    }

    /**
     * Counts digits right of the decimal separator.<br>
     * <br>
     * <i>Note:<br>
     * The function may not work on too large numbers</i>
     *
     * @param number
     *        a number
     *
     * @return a digit count
     *
     * @deprecated integrate into number implementation and replace counter type.
     */
    @Deprecated
    public static int countRightDigit(Number number) {

        int count = -1;

        if (number.isInfinity()) {

            return count;
        }

        DigitNode node = number.centerNode();

        while (node != null) {

            int previousCount = count;
            count++;

            if (count < previousCount) {

                throw new ArithmeticOverflowException();
            }

            node = node.rightNode();
        }

        return count;
    }

}


/**
 * A custom exception class.
 *
 * @author Kristian Kutin
 */
class ArithmeticOverflowException extends RuntimeException {

    /**
     * The default constructor.
     */
    public ArithmeticOverflowException() {

        super();
    }

}
