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

package jmul.math.numbers.notations;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jmul.math.functions.FunctionSingletons;
import jmul.math.functions.repository.FunctionIdentifier;
import jmul.math.functions.repository.FunctionIdentifiers;
import static jmul.math.numbers.Constants.DEFAULT_NUMBER_BASE;
import jmul.math.numbers.exceptions.NumberParsingException;


/**
 * A utility class for parsing number string.
 *
 * @author Kristian Kutin
 */
public final class ParserHelper {

    /**
     * A list of parser functions.
     */
    private static final List<FunctionIdentifier> PARSER_FUNCTION_LIST;

    /*
     * The static initializer.
     */
    static {

        List<FunctionIdentifiers> tmpList = new ArrayList<>();

        /*
         * Because of ambiguities we first test if the input matches the scientific notation, i.e. the letter
         * E or e is used as separator between mantissa and exponent. This letter can also represent a digit.
         * Thus the order of the parser functions is essential.
         */
        tmpList.add(FunctionIdentifiers.SCIENTIFIC_NOTATION_PARSER);
        tmpList.add(FunctionIdentifiers.STANDARD_NOTATION_PARSER);

        PARSER_FUNCTION_LIST = Collections.unmodifiableList(tmpList);
    }

    /**
     * The default constructor.
     */
    private ParserHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * This function wraps parsing a string.
     *
     * @param base
     *        the base for this number
     * @param s
     *        a string which represetns a number
     *
     * @return the parsing result
     */
    public static ParsingResult parseString(int base, CharSequence s) {

        List<Throwable> exceptions = new ArrayList<>();

        for (FunctionIdentifier identifier : PARSER_FUNCTION_LIST) {

            NotationParser parser = (NotationParser) FunctionSingletons.getFunction(identifier);

            try {

                return parser.parseNotation(base, s.toString());

            } catch (IllegalArgumentException e) {

                // Ignore the exception for now because it might not be the matching notation.
                // Remember the exception for later if needed.
                exceptions.add(e);
            }
        }

        throw new NumberParsingException(base, s, exceptions);
    }

    /**
     * Parses the specified byte value.
     *
     * @param b
     *        a byte value
     *
     * @return the parsing result
     */
    public static ParsingResult parseByte(byte b) {

        String numberString = String.valueOf(b);

        return parseString(DEFAULT_NUMBER_BASE, numberString);
    }

    /**
     * Parses the specified short value.
     *
     * @param s
     *        a short value
     *
     * @return the parsing result
     */
    public static ParsingResult parseShort(short s) {

        String numberString = String.valueOf(s);

        return parseString(DEFAULT_NUMBER_BASE, numberString);
    }

    /**
     * Parses the specified integer value.
     *
     * @param i
     *        a integer value
     *
     * @return the parsing result
     */
    public static ParsingResult parseInteger(int i) {

        String numberString = String.valueOf(i);

        return parseString(DEFAULT_NUMBER_BASE, numberString);
    }

    /**
     * Parses the specified long value.
     *
     * @param l
     *        a long value
     *
     * @return the parsing result
     */
    public static ParsingResult parseLong(long l) {

        String numberString = String.valueOf(l);

        return parseString(DEFAULT_NUMBER_BASE, numberString);
    }

    /**
     * Parses the specified float value.
     *
     * @param f
     *        a float value
     *
     * @return the parsing result
     */
    public static ParsingResult parseFloat(float f) {

        String numberString = String.valueOf(f);

        return parseString(DEFAULT_NUMBER_BASE, numberString);
    }

    /**
     * Parses the specified double value.
     *
     * @param d
     *        a double value
     *
     * @return the parsing result
     */
    public static ParsingResult parseDouble(double d) {

        String numberString = String.valueOf(d);

        return parseString(DEFAULT_NUMBER_BASE, numberString);
    }

    /**
     * Parses the specified number value.
     *
     * @param n
     *        a double value
     *
     * @return the parsing result
     */
    public static ParsingResult parseNumber(java.lang.Number n) {

        checkNumberParameter(n);
        String numberString = String.valueOf(n);

        return parseString(DEFAULT_NUMBER_BASE, numberString);
    }

    /**
     * Checks the specified parameter.
     *
     * @param n
     *        a number wrapper
     *
     * @return the specified parameter
     */
    private static java.lang.Number checkNumberParameter(java.lang.Number n) {

        if (n == null) {

            throw new NullPointerException();
        }

        return n;
    }

}
