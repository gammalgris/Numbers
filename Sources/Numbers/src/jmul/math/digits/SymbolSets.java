/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2024  Kristian Kutin
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
import java.util.List;

import static jmul.math.numbers.Constants.BASE_MAX_LIMIT;
import static jmul.math.numbers.Constants.BASE_MIN_LIMIT;


/**
 * This class contains all allowed symbols for digits and provides
 * subsets for various positional numeral systems.<br>
 * <br>
 * <i>Note:</i><br>
 * <ol>
 * <li><i>Base 1 is not supported. Integers would be represented by zeroes and
 * the zero count would define the integer. It's unclear how to interpret
 * a fraction of zeroes.</i></li>
 * <li><i>Minimum is base 2 which represents binary.</i></li>
 * <li><i>Maximum base is currently 62. If you allow greater bases then check which symbols
 * should be used for digits. These characters should not be conflict with operators (e.g. +, -, etc.).
 * Accordingly the regular expressions for parsing numbers should be expanded.</i></li>
 * </ol>
 *
 * @author Kristian Kutin
 */
public class SymbolSets {

    /**
     * A set which contains all allowed symbols.
     */
    private final List<Character> symbols;

    /**
     * The default constructor.
     */
    public SymbolSets() {

        super();

        List<Character> symbols = new ArrayList<>();

        for (int a = 0; a <= BASE_MAX_LIMIT; a++) {

            if (a < 10) {

                int code = 48 + a;
                symbols.add((char) code);

            } else if (a < 36) {

                int code = 55 + a;
                symbols.add((char) code);

            } else {

                int code = 61 + a;
                symbols.add((char) code);
            }
        }

        this.symbols = Collections.unmodifiableList(symbols);
    }

    /**
     * Returns a subset of symbols for a positional numeral system with the specified base.
     *
     * @param base
     *        the base for a positional numeral system
     *
     * @return a subset of symbols
     */
    public char[] subset(int base) {

        if ((base < BASE_MIN_LIMIT) || (base > BASE_MAX_LIMIT)) {

            String message = String.format("Unsuported base %d!", base);
            throw new IllegalArgumentException(message);
        }

        char[] subset = new char[base];
        for (int ordinalNumber = 0; ordinalNumber < base; ordinalNumber++) {

            char symbol = symbols.get(ordinalNumber);
            subset[ordinalNumber] = symbol;
        }

        return subset;
    }

}
