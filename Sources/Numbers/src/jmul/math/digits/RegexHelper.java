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


import static jmul.math.numbers.Constants.BASE_MAX_LIMIT;
import static jmul.math.numbers.Constants.BASE_MIN_LIMIT;


/**
 * A utility class for regex expressions regarding numbers of different number bases.
 *
 * @author Kristian Kutin
 */
public final class RegexHelper {

    /**
     * The default constructor.
     */
    private RegexHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Returns a regex expression for the ordinal zero digit.
     *
     * @param symbols
     *        a string containing all digits in ascending order
     *
     * @return a regex expression
     */
    public static String getOrdinalZeroSymbolRegex(String symbols) {

        checkSymbols(symbols);

        String ordinalZeroSymbol = String.format("%c", symbols.charAt(0));
        String regex = String.format("[%s]", ordinalZeroSymbol);

        return regex;
    }

    /**
     * Checks the specified symbols.
     *
     * @param symbols
     *        a string containing all digits in ascending order
     *
     * @return the specified symbols
     */
    private static String checkSymbols(String symbols) {

        if (symbols == null) {

            throw new IllegalArgumentException("No symbols (null) were specified!");
        }

        if ((symbols.length() < BASE_MIN_LIMIT) || (symbols.length() > BASE_MAX_LIMIT)) {

            throw new UnsupportedOperationException("Unsupported number base!");
        }

        return symbols;
    }

    /**
     * Retuns a regex expression for a number without fraction.
     *
     * @param symbols
     *        a string containing all digits in ascending order
     *
     * @return a regex expression
     */
    public static String getNumberRegex(String symbols) {

        checkSymbols(symbols);

        String symbolsWithoutOrdinalZeroSymbol = symbols.substring(1);
        String regex = String.format("[%s][%s]*", symbolsWithoutOrdinalZeroSymbol, symbols);

        return regex;
    }

    /**
     * Returns a regex expression for the fraction part of a number.
     *
     * @param symbols
     *        a string containing all digits in ascending order
     *
     * @return a regex expression
     */
    public static String getFractionRegex(String symbols) {

        checkSymbols(symbols);

        String symbolsWithoutOrdinalZeroSymbol = symbols.substring(1);
        String regex = String.format("[%s]*[%s]", symbols, symbolsWithoutOrdinalZeroSymbol);

        return regex;
    }

}
