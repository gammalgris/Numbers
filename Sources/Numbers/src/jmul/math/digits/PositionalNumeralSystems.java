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

package jmul.math.digits;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * This class grants access to various numeral systems.
 *
 * @author Kristian Kutin
 */
public class PositionalNumeralSystems {

    /**
     * A singleton containing all allowed symbols.
     */
    private static SymbolSets DEFAULT_SYMBOL_SETS;

    /**
     * A singleton containing all currently used positional numeral systems.
     */
    private static Map<Integer, PositionalNumeralSystem> POSITIONAL_NUMERAL_SYSTEMS_MAP;

    /*
     * The static initializer.
     */
    static {

        DEFAULT_SYMBOL_SETS = new SymbolSets();
        POSITIONAL_NUMERAL_SYSTEMS_MAP = new HashMap<>();
    }

    /**
     * Returns the numeral system with the specified base.
     *
     * @param base
     *        the base of a numeral system
     *
     * @return a numeral system
     */
    private static PositionalNumeralSystem getPositionalNumeralSystem(int base) {

        PositionalNumeralSystem system = null;
        if (POSITIONAL_NUMERAL_SYSTEMS_MAP.containsKey(base)) {

            system = POSITIONAL_NUMERAL_SYSTEMS_MAP.get(base);

        } else {

            char[] symbols = DEFAULT_SYMBOL_SETS.subset(base);
            system = new GenericPositionalNumeralSystem(symbols);
            POSITIONAL_NUMERAL_SYSTEMS_MAP.put(base, system);
        }

        return system;
    }

    /**
     * Adds a positional numeral system with the specified symbol set. A symbol set should not contain
     * duplicate symbols in order to avoid ambiguities.
     * The method doesn't override an already existing positional numeral system with the same number
     * base.
     *
     * @param symbols
     *        a symbol set. The size of the set defines the number base. The sequence of the symbols
     *        defines the natural order.
     */
    public static void addPositionalNumeralSystemWithCustomSymbols(char... symbols) {

        checkCustomSymbolSet(symbols);

        int base = symbols.length;

        if (!POSITIONAL_NUMERAL_SYSTEMS_MAP.containsKey(base)) {

            PositionalNumeralSystem system = new GenericPositionalNumeralSystem(symbols);
            POSITIONAL_NUMERAL_SYSTEMS_MAP.put(base, system);
        }
    }

    /**
     * Checks the specified symbol set.
     *
     * @param symbols
     *        a symbol set
     *
     * @return the specified symbol set
     */
    private static char[] checkCustomSymbolSet(char[] symbols) {

        if (symbols == null) {

            throw new IllegalArgumentException("No symbols (null) were specified!");
        }

        if (symbols.length == 0) {

            throw new IllegalArgumentException("No symbols (empty array) were specified!");
        }

        List<Character> uniqueSymbols = new ArrayList<>();
        List<Character> duplicateSymbols = new ArrayList<>();

        for (char symbol : symbols) {

            if (uniqueSymbols.contains(symbol)) {

                duplicateSymbols.add(symbol);

            } else {

                uniqueSymbols.add(symbol);
            }
        }

        if (!duplicateSymbols.isEmpty()) {

            String message = String.format("Duplicate symbols: %s", duplicateSymbols);
            throw new IllegalArgumentException(message);
        }

        return symbols;
    }

    /**
     * Translates the specified ordinal number to a digit with the specified base.
     *
     * @param base
     *        the base of a numeral system
     * @param ordinal
     *        an ordinal number representing the digit within the numeral system
     *
     * @return a digit
     */
    public static Digit ordinalToDigit(int base, int ordinal) {

        PositionalNumeralSystem numeralSystem = getPositionalNumeralSystem(base);
        return numeralSystem.ordinalToDigit(ordinal);
    }

    /**
     * Retrieves the digit for the specified ordinal number with the specified number
     * base and returns the associated symbol.
     *
     * @param base
     *        the base of a numeral system
     * @param ordinal
     *        an ordinal number representing the digit within the numeral system
     *
     * @return a symbol
     */
    public static char ordinalToSymbol(int base, int ordinal) {

        PositionalNumeralSystem numeralSystem = getPositionalNumeralSystem(base);

        return numeralSystem.ordinalToSymbol(ordinal);
    }

    /**
     * Translates the specified character to a digit with the specified base.
     *
     * @param base
     *        the base of a numeral system
     * @param symbol
     *        a character representing the digit within the numeral system
     *
     * @return a digit
     */
    public static Digit charToDigit(int base, char symbol) {

        PositionalNumeralSystem numeralSystem = getPositionalNumeralSystem(base);
        return numeralSystem.charToDigit(symbol);
    }

    /**
     * Returns a list of symbols which can be used within a regex. The symbols
     * are ordered from left to right in ascending order (see ordinal value).
     *
     * @param base
     *        the base of a numeral system
     *
     * @return a string containg all symbols in ascending order (see ordinal values)
     */
    public static String baseToDigitRegex(int base) {

        PositionalNumeralSystem numeralSystem = getPositionalNumeralSystem(base);

        StringBuilder buffer = new StringBuilder();
        for (int ordinal = 0; ordinal < base; ordinal++) {

            char symbol = numeralSystem.ordinalToSymbol(ordinal);
            buffer.append(symbol);
        }

        return buffer.toString();
    }

    /**
     * Returns a string which represents the ordinal value for the specified number base.
     *
     * @param base
     *        the base of a numeral system
     * @param ordinal
     *        the ordinal value of a digit
     *
     * @return a string representation of the specified ordinal number
     */
    public static String toString(int base, int ordinal) {

        char symbol = PositionalNumeralSystems.ordinalToSymbol(base, ordinal);

        return String.format("%c", symbol);
    }

    /**
     * Returns a regex snippet containing all allowed digits for the specified number base.
     *
     * @param base
     *        a number base
     *
     * @return a regex snippet
     */
    public static String allowedDigitsRegex(int base) {

        PositionalNumeralSystem numeralSystem = getPositionalNumeralSystem(base);

        return numeralSystem.allowedDigitsRegex();
    }

    /**
     * Returns a regex snippet containing a subset of allowed digits for the specified number base.
     *
     * @param base
     *        a number base
     *
     * @return a regex snippet
     */
    public static String allowedDigitsWithoutZeroRegex(int base) {

        PositionalNumeralSystem numeralSystem = getPositionalNumeralSystem(base);

        return numeralSystem.allowedDigitsWithoutZeroRegex();
    }

}
