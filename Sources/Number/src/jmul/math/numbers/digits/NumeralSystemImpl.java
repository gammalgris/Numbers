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


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple implementation of a numeral system.
 *
 * @author Kristian Kutin
 */
class NumeralSystemImpl implements NumeralSystem {

    /**
     * A mapping of ordinal numbers to characters.
     */
    private static final Map<Integer, Character> ORDINAL_TO_CHARACTER_MAPPING;

    /*
     * The static initializer.
     */
    static {

        Map<Integer, Character> tmp = new HashMap<>();

        for (int a = 0; a <= 64; a++) {

            if (a < 10) {

                int code = 48 + a;
                tmp.put(a, (char) code);

            } else if (a < 36) {

                int code = 55 + a;
                tmp.put(a, (char) code);

            } else {

                int code = 61 + a;
                tmp.put(a, (char) code);
            }
        }

        ORDINAL_TO_CHARACTER_MAPPING = Collections.unmodifiableMap(tmp);
    }

    /**
     * The base of this numeral system.
     */
    private final int base;

    /**
     * All digits for this numeral system.
     */
    private final List<Digit> digits;

    /**
     * A mapping character to digit.
     */
    private final Map<Character, Digit> charMap;

    /**
     * A mapping ordinal to digit.
     */
    private final Map<Integer, Digit> ordinalMap;

    /**
     * Creates a new numeral system according to the specified base.
     *
     * @param base
     *        the base of a numeral system
     */
    NumeralSystemImpl(int base) {

        super();

        this.base = base;

        digits = initDigits(base);
        charMap = initCharMap(digits);
        ordinalMap = initOrdinalMap(digits);
    }

    private static char ordinal2char(int ordinal) {

        return ORDINAL_TO_CHARACTER_MAPPING.get(ordinal);
    }

    private static List<Digit> initDigits(int base) {

        List<Digit> tmp = new ArrayList<>();

        for (int a = 0; a < base; a++) {

            char symbol = ordinal2char(a);

            Digit digit = new DigitImpl(symbol, base, a);
            tmp.add(digit);
        }

        return Collections.unmodifiableList(tmp);
    }

    private static Map<Character, Digit> initCharMap(List<Digit> digits) {

        Map<Character, Digit> tmp = new HashMap<>();

        for (Digit digit : digits) {

            tmp.put(digit.symbol(), digit);
        }

        return Collections.unmodifiableMap(tmp);
    }

    private static Map<Integer, Digit> initOrdinalMap(List<Digit> digits) {

        Map<Integer, Digit> tmp = new HashMap<>();

        for (Digit digit : digits) {

            tmp.put(digit.ordinal(), digit);
        }

        return Collections.unmodifiableMap(tmp);
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

            String message = String.format("No digit exists which corresponds to the specified ordinal (%d)!", ordinal);
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

            String message = String.format("No digit exists which corresponds to the specified symbol (%d)!", symbol);
            throw new IllegalArgumentException(message);
        }

        return charMap.get(symbol);
    }

}
