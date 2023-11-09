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


import java.util.HashMap;
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
    private static SymbolSets SYMBOL_SETS;

    /**
     * A singleton containing all currently used positional numeral systems.
     */
    private static Map<Integer, PositionalNumeralSystem> POSITIONAL_NUMERAL_SYSTEMS_MAP;

    /*
     * The static initializer.
     */
    static {

        SYMBOL_SETS = new SymbolSets();
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

            char[] symbols = SYMBOL_SETS.subset(base);
            system = new GenericPositionalNumeralSystem(symbols);
            POSITIONAL_NUMERAL_SYSTEMS_MAP.put(base, system);
        }

        return system;
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

}
