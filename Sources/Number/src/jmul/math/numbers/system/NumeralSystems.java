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

package jmul.math.numbers.system;


import java.util.List;

import jmul.math.numbers.DigitSequence;
import jmul.math.numbers.RealDecimalNumber;
import jmul.math.numbers.Sign;
import jmul.math.numbers.digits.BinaryDigits;
import jmul.math.numbers.digits.DecimalDigits;
import jmul.math.numbers.digits.Digit;
import jmul.math.numbers.digits.HexadecimalDigits;
import jmul.math.numbers.digits.OctalDigits;

import jmul.singletons.FunctionSingletons;


/**
 * This class provides various utility functions regarding numeral systems.
 *
 * @author Kristian Kutin
 * 
 * @deprecated This class may become superfluous or needs reworking when digit operations are implemented.
 */
@Deprecated
public final class NumeralSystems {

    /**
     * An array with several function names.
     */
    private static final String[] SUPPORTED_NUMERAL_SYSTEMS;

    /**
     * The name of a function.
     */
    private static final String BINARY_NUMERAL_SYSTEM_NAME;

    /**
     * The name of a function.
     */
    private static final String OCTAL_NUMERAL_SYSTEM_NAME;

    /**
     * The name of a function.
     */
    private static final String DECIMAL_NUMERAL_SYSTEM_NAME;

    /**
     * The name of a function.
     */
    private static final String HEXADECIMAL_NUMERAL_SYSTEM_NAME;

    /*
     * The static initializer.
     */
    static {

        BINARY_NUMERAL_SYSTEM_NAME = "binary numeral system";
        OCTAL_NUMERAL_SYSTEM_NAME = "octal numeral system";
        DECIMAL_NUMERAL_SYSTEM_NAME = "decimal numeral system";
        HEXADECIMAL_NUMERAL_SYSTEM_NAME = "hexadecimal numeral system";

        SUPPORTED_NUMERAL_SYSTEMS = new String[] {
            BINARY_NUMERAL_SYSTEM_NAME, OCTAL_NUMERAL_SYSTEM_NAME, DECIMAL_NUMERAL_SYSTEM_NAME,
            HEXADECIMAL_NUMERAL_SYSTEM_NAME
        };

        NumeralSystem a;

        a = new NumeralSystem<BinaryDigits>() {

            @Override
            public int base() {
                return BinaryDigits.ZERO.base();
            }

            @Override
            public BinaryDigits ordinalToDigit(int ordinal) {
                return BinaryDigits.values()[ordinal];
            }

            @Override
            public BinaryDigits charToDigit(char symbol) {
                return BinaryDigits.charToDigit(symbol);
            }

            @Override
            public DigitSequence<BinaryDigits> toDigitSequence(Sign sign, List<BinaryDigits> digits,
                                                               Integer relativeSeparatorIndex) {

                //TODO not implemented yet
                throw new UnsupportedOperationException();
            }
        };

        FunctionSingletons.putFunction(BINARY_NUMERAL_SYSTEM_NAME, a);

        a = new NumeralSystem<OctalDigits>() {

            @Override
            public int base() {
                return OctalDigits.ZERO.base();
            }

            @Override
            public OctalDigits ordinalToDigit(int ordinal) {
                return OctalDigits.values()[ordinal];
            }

            @Override
            public OctalDigits charToDigit(char symbol) {
                return OctalDigits.charToDigit(symbol);
            }

            @Override
            public DigitSequence<OctalDigits> toDigitSequence(Sign sign, List<OctalDigits> digits,
                                                              Integer relativeSeparatorIndex) {

                //TODO not implemented yet
                throw new UnsupportedOperationException();
            }
        };

        FunctionSingletons.putFunction(OCTAL_NUMERAL_SYSTEM_NAME, a);

        a = new NumeralSystem<DecimalDigits>() {

            @Override
            public int base() {
                return DecimalDigits.ZERO.base();
            }

            @Override
            public DecimalDigits ordinalToDigit(int ordinal) {
                return DecimalDigits.values()[ordinal];
            }

            @Override
            public DecimalDigits charToDigit(char symbol) {
                return DecimalDigits.charToDigit(symbol);
            }

            @Override
            public DigitSequence<DecimalDigits> toDigitSequence(Sign sign, List<DecimalDigits> digits,
                                                                Integer relativeSeparatorIndex) {
                DecimalDigits[] d = digits.toArray(new DecimalDigits[] { });
                return new RealDecimalNumber(sign, d, relativeSeparatorIndex);
            }
        };

        FunctionSingletons.putFunction(DECIMAL_NUMERAL_SYSTEM_NAME, a);

        a = new NumeralSystem<HexadecimalDigits>() {

            @Override
            public int base() {
                return HexadecimalDigits.ZERO.base();
            }

            @Override
            public HexadecimalDigits ordinalToDigit(int ordinal) {
                return HexadecimalDigits.values()[ordinal];
            }

            @Override
            public HexadecimalDigits charToDigit(char symbol) {
                return HexadecimalDigits.charToDigit(symbol);
            }

            @Override
            public DigitSequence<HexadecimalDigits> toDigitSequence(Sign sign, List<HexadecimalDigits> digits,
                                                                    Integer relativeSeparatorIndex) {

                //TODO not implemented yet
                throw new UnsupportedOperationException();
            }
        };

        FunctionSingletons.putFunction(HEXADECIMAL_NUMERAL_SYSTEM_NAME, a);
    }

    /**
     * The default constructor.
     */
    private NumeralSystems() {

        throw new UnsupportedOperationException();
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

        for (String name : SUPPORTED_NUMERAL_SYSTEMS) {

            NumeralSystem s = (NumeralSystem) FunctionSingletons.getFunction(name);

            if (s.base() != base) {

                continue;
            }

            return s.ordinalToDigit(ordinal);
        }

        String message = String.format("No numeral system with the base %d is supported!", base);
        throw new IllegalArgumentException(message);
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

        for (String name : SUPPORTED_NUMERAL_SYSTEMS) {

            NumeralSystem s = (NumeralSystem) FunctionSingletons.getFunction(name);

            if (s.base() != base) {

                continue;
            }

            return s.charToDigit(symbol);
        }

        String message = String.format("No numeral system with the base %d is supported!", base);
        throw new IllegalArgumentException(message);
    }

    /**
     * Create a new digit sequence according to the specified parameters.
     *
     * @param base
     *        the base of a numeral system
     * @param sign
     *        a sign
     * @param digits
     *        all digits of the digit sequence
     * @param relativeSeparatorIndex
     *        the index of a separator (i.e. comma or point) relative to the first digit
     *
     * @return a new number
     */
    public static DigitSequence<? extends Digit> toDigitSequence(int base, Sign sign, List<? extends Digit> digits,
                                                                 Integer relativeSeparatorIndex) {

        for (String name : SUPPORTED_NUMERAL_SYSTEMS) {

            NumeralSystem s = (NumeralSystem) FunctionSingletons.getFunction(name);

            if (s.base() != base) {

                continue;
            }

            return s.toDigitSequence(sign, digits, relativeSeparatorIndex);
        }

        String message = String.format("No numeral system with the base %d is supported!", base);
        throw new IllegalArgumentException(message);
    }

}
