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


import jmul.math.numbers.nodes.DigitNode;


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
     * Counts digits left of a decimal separator.<br>
     * <br>
     * <i>Note:<br>
     * The function may not work on too large numbers</i>
     *
     * @param number
     *        a number
     *
     * @return a digit count
     */
    public static int countLeftDigits(Number number) {

        int count = 0;

        if (number.isInfinity()) {

            return count;
        }

        DigitNode node = number.centerNode();

        while (node != null) {

            count++;
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
     */
    public static int countRightDigit(Number number) {

        int count = -1;

        if (number.isInfinity()) {

            return count;
        }

        DigitNode node = number.centerNode();

        while (node != null) {

            count++;
            node = node.rightNode();
        }

        return count;
    }

}
