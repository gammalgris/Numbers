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
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


/**
 * A simple implementation of a positional numeral system. This implementation
 * is immutable.
 *
 * @author Kristian Kutin
 */
class GenericPositionalNumeralSystem implements PositionalNumeralSystem {

    /**
     * The base of this numeral system.
     */
    private final int base;

    /**
     * A mapping character to digit.
     */
    private final Map<Character, Digit> charMap;

    /**
     * A mapping ordinal to digit.
     */
    private final Map<Integer, Digit> ordinalMap;

    /**
     * Creates a positional numeral system according to the specified parameters.
     *
     * @param symbols
     *        the symbols for all digits in ascending order. The array size corresponds with
     *        the base for this positional numeral system.
     */
    GenericPositionalNumeralSystem(char... symbols) {

        super();

        this.base = symbols.length;

        Set<Character> processedSymbols = new HashSet<>();
        Map<Character, Digit> charMap = new HashMap<>();
        Map<Integer, Digit> ordinalMap = new HashMap<>();

        for (int ordinalNumber = 0; ordinalNumber < symbols.length; ordinalNumber++) {

            char symbol = symbols[ordinalNumber];

            if (processedSymbols.contains(symbol)) {

                String message = String.format("Duplicate symbols (%c) are used! Symbols have to be unique.", symbol);
                throw new IllegalArgumentException(message);
            }
            processedSymbols.add(symbol);

            Digit digit = new GenericDigit(symbol, base, ordinalNumber);

            charMap.put(symbol, digit);
            ordinalMap.put(ordinalNumber, digit);
        }

        this.charMap = Collections.unmodifiableMap(charMap);
        this.ordinalMap = Collections.unmodifiableMap(ordinalMap);
    }

    /**
     * Returns the base of the positional numeral system.
     *
     * @return a base
     */
    @Override
    public int base() {

        return base;
    }

    /**
     * Find a digit that corresponds to the specified ordinal number.
     *
     * @param ordinal
     *        an ordinal number
     *
     * @return a digit
     */
    @Override
    public Digit ordinalToDigit(int ordinal) {

        if (!ordinalMap.containsKey(ordinal)) {

            String message =
                String.format("No digit exists which corresponds to the specified ordinal number (%d)!", ordinal);
            throw new IllegalArgumentException(message);
        }

        return ordinalMap.get(ordinal);
    }

    /**
     * Find a digit that corresponds to the specified symbol.
     *
     * @param symbol
     *        a character
     *
     * @return a digit
     */
    @Override
    public Digit charToDigit(char symbol) {

        if (!charMap.containsKey(symbol)) {

            String message = String.format("No digit exists which corresponds to the specified symbol ('%c')!", symbol);
            throw new IllegalArgumentException(message);
        }

        return charMap.get(symbol);
    }

    /**
     * Returns a string representation for this object.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        String message = String.format("positional numeral System : base %d", base());
        return message;
    }

}
