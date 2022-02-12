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

package jmul.math.numbers.digits;


/**
 * This enumeration contains all binary digits.
 *
 * @author Kristian Kutin
 */
public enum BinaryDigits implements Digit {


    ZERO('0'),
    ONE('1'), ;


    /**
     * The symbol associated with the sign.
     */
    private final char symbol;

    /**
     * Creates a new enumeration element with the specified symbol.
     *
     * @param aSymbol
     *        a symbol to be associated with the enumeration element
     */
    private BinaryDigits(char aSymbol) {

        symbol = aSymbol;
    }

    /**
     * Returns the symbol associated with this digit.
     *
     * @return a symbol
     */
    @Override
    public char symbol() {

        return symbol;
    }

    /**
     * Returns the base of the positional numeral system.
     *
     * @return a base
     */
    @Override
    public int base() {

        return values().length;
    }

    /**
     * Returns a string representation of the enumeration element.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return String.valueOf(symbol());
    }

    /**
     * Find a digit that corresponds to the specified symbol.
     *
     * @param aSymbol
     *        a symbol (i.e. a character)
     *
     * @return a binary digit
     */
    public static BinaryDigits charToDigit(char aSymbol) {

        for (BinaryDigits digit : values()) {

            if (digit.symbol() == aSymbol) {

                return digit;
            }
        }

        String message = String.format("'%s' is not a valid symbol!", aSymbol);
        throw new IllegalArgumentException(message);
    }

}
