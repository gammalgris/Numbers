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

package test.jmul.math.numbers;


import java.util.regex.Matcher;

import jmul.math.numbers.Constants;
import jmul.patterns.PatternHelper;
import jmul.math.numbers.Sign;
import jmul.math.numbers.Signs;


/**
 * This class contains utility functions for evaluating number strings.
 *
 * @author Kristian Kutin
 */
public final class StringHelper {

    /**
     * The default constructor.
     */
    private StringHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Checks if the specified strign represents a negative number.
     *
     * @param s
     *        a string
     *
     * @return <code>true</code> if the number is negative, else <code>false</code>
     */
    public static boolean isNegative(CharSequence s) {

        Matcher matcher;

        matcher = PatternHelper.match(Constants.DECIMAL_STANDARD_NOTATION_PATTERN_STRING, s);
        if (matcher.find()) {

            String match = matcher.group(Constants.SIGN_GROUP);
            if ((match != null) && match.contains("-")) {

                return true;
            }
        }

        matcher = PatternHelper.match(Constants.DECIMAL_SCIENTIFIC_NOTATION_PATTERN_STRING, s);
        if (matcher.find()) {

            String match = matcher.group(Constants.SIGN_GROUP);
            if ((match != null) && match.contains("-")) {

                return true;
            }
        }

        return false;
    }

    /**
     * Returns the number of digits for the specified string.
     *
     * @param s
     *        a string
     *
     * @return a digit count
     */
    public static int digits(CharSequence s) {

        return digitsLeft(s) + digitsRight(s);
    }

    /**
     * Determines the digits left of a separator.
     *
     * @param s
     *        a string
     *
     * @return a digit count
     */
    public static int digitsLeft(CharSequence s) {

        try {

            return digitsLeftStandardNotation(s);

        } catch (IllegalArgumentException e) {

            // The specified string doesn't match the standard notation. Ignore this exception and check if the
            // string matches the scientific notation.
            return digitsLeftScientificNotation(s);
        }
    }

    /**
     * Determines the digits left of a separator.
     *
     * @param s
     *        a string
     *
     * @return a digit count
     */
    public static int digitsLeftStandardNotation(CharSequence s) {

        Matcher matcher = PatternHelper.match(Constants.DECIMAL_STANDARD_NOTATION_PATTERN_STRING, s);

        if (!matcher.find()) {

            throw new IllegalArgumentException("The specified string (\"" + s + "\") is not a valid number!");
        }

        String left = matcher.group(Constants.LEFT_GROUP);

        int digits = left.length();

        return digits;
    }

    /**
     * Determines the digits left of a separator.
     *
     * @param s
     *        a string
     *
     * @return a digit count
     */
    public static int digitsLeftScientificNotation(CharSequence s) {

        Matcher matcher = PatternHelper.match(Constants.DECIMAL_SCIENTIFIC_NOTATION_PATTERN_STRING, s);

        if (!matcher.find()) {

            throw new IllegalArgumentException("The specified string (\"" + s + "\") is not a valid number!");
        }

        String mantissa = matcher.group(Constants.MANTISSA_GROUP);
        String signExponent = matcher.group(Constants.SIGN_EXPONENT_GROUP);
        String exponent = matcher.group(Constants.EXPONENT_GROUP);

        int left = 1;
        int right = mantissa.length() - left - 1;

        Sign sign;
        if (signExponent != null) {

            char symbol = signExponent.charAt(0);
            sign = Signs.findBySymbol(symbol);

        } else {

            sign = Signs.POSITIVE;
        }

        String exponentString = "";
        if (signExponent != null) {

            exponentString = signExponent;
        }
        exponentString += exponent;

        int i = Integer.parseInt(exponentString);

        if (i < 0) {

            return 1;

        } else {

            return i + 1;
        }
    }

    /**
     * Determines the digits right of a separator.
     *
     * @param s
     *        a string
     *
     * @return a digit count
     */
    public static int digitsRight(CharSequence s) {

        try {

            return digitsRightStandardNotation(s);

        } catch (IllegalArgumentException e) {

            // The specified string doesn't match the standard notation. Ignore this exception and check if the
            // string matches the scientific notation.
            return digitsRightScientificNotation(s);
        }
    }

    /**
     * Determines the digits right of a separator.
     *
     * @param s
     *        a string
     *
     * @return a digit count
     */
    public static int digitsRightStandardNotation(CharSequence s) {

        Matcher matcher = PatternHelper.match(Constants.DECIMAL_STANDARD_NOTATION_PATTERN_STRING, s);

        if (!matcher.find()) {

            throw new IllegalArgumentException("The specified string (\"" + s + "\") is not a valid number!");
        }

        String right = matcher.group(Constants.RIGHT_GROUP);

        int digits = 0;
        if (right != null) {

            digits += right.length();
        }

        return digits;
    }

    /**
     * Determines the digits right of a separator.
     *
     * @param s
     *        a string
     *
     * @return a digit count
     */
    public static int digitsRightScientificNotation(CharSequence s) {

        Matcher matcher = PatternHelper.match(Constants.DECIMAL_SCIENTIFIC_NOTATION_PATTERN_STRING, s);

        if (!matcher.find()) {

            throw new IllegalArgumentException("The specified string (\"" + s + "\") is not a valid number!");
        }

        String mantissa = matcher.group(Constants.MANTISSA_GROUP);
        String signExponent = matcher.group(Constants.SIGN_EXPONENT_GROUP);
        String exponent = matcher.group(Constants.EXPONENT_GROUP);

        int left = 1;
        int right = mantissa.length() - left - 1;

        Sign sign;
        if (signExponent != null) {

            char symbol = signExponent.charAt(0);
            sign = Signs.findBySymbol(symbol);

        } else {

            sign = Signs.POSITIVE;
        }

        String exponentString = "";
        if (signExponent != null) {

            exponentString = signExponent;
        }
        exponentString += exponent;

        int i = Integer.parseInt(exponentString);

        return Math.max(0, right - i);
    }

}
