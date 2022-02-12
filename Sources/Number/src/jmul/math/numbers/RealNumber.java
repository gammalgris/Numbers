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

package jmul.math.numbers;


import java.math.BigDecimal;
import java.math.BigInteger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import jmul.math.numbers.digits.DecimalDigits;
import jmul.math.numbers.digits.Digit;


/**
 * An implementation of a real number on a base 10 numeral system.
 *
 * @author Kristian Kutin
 */
public final class RealNumber extends AbstractNumber<DecimalDigits> {

    /**
     * A constant that doesn't contain any digits.
     */
    private static final DecimalDigits[] NO_DIGITS;

    /**
     * A constant number representing positive infinity.
     */
    public static final RealNumber POSITIVE_INFINITY;

    /**
     * A constant number representing negative infinity.
     */
    public static final RealNumber NEGATIVE_INFINITY;

    /*
     * The static initializer.
     */
    static {

        NO_DIGITS = new DecimalDigits[] { };

        POSITIVE_INFINITY = new RealNumber(Signs.POSITIVE);
        NEGATIVE_INFINITY = new RealNumber(Signs.NEGATIVE);
    }

    /**
     * The default constructor. Creates a real number with a zero value.
     */
    public RealNumber() {

        this(0);
    }

    /**
     * Creates a real number according to the specified integer.
     *
     * @param n
     *        an integer
     */
    public RealNumber(int n) {

        super(toSign(n), parseString(String.valueOf(n)), determineSeparatorIndex(String.valueOf(n)));
    }

    /**
     * Creates a real number according to the specified long value.
     *
     * @param l
     *        a long value
     */
    public RealNumber(long l) {

        super(toSign(l), parseString(String.valueOf(l)), determineSeparatorIndex(String.valueOf(l)));
    }

    /**
     * Creates a real number according to the specified float value.
     *
     * @param f
     *        a float value
     */
    public RealNumber(float f) {

        super(toSign(f), parseString(String.valueOf(f)), determineSeparatorIndex(String.valueOf(f)));
    }

    /**
     * Creates a real number according to the specified double value.
     *
     * @param d
     *        a double value
     */
    public RealNumber(double d) {

        super(toSign(d), parseString(String.valueOf(d)), determineSeparatorIndex(String.valueOf(d)));
    }

    /**
     * Creates a real number according to the specified string.
     *
     * @param s
     *        a string
     */
    public RealNumber(CharSequence s) {

        super(toSign(s), parseString(String.valueOf(s)), determineSeparatorIndex(String.valueOf(s)));
    }

    /**
     * Creates a real number according to the specified big integer.
     *
     * @param i
     *        a big integer
     */
    public RealNumber(BigInteger i) {

        super(toSign(i), parseString(String.valueOf(i)), determineSeparatorIndex(String.valueOf(i)));
    }

    /**
     * Creates a real number according to the specified big decimal.
     *
     * @param d
     *        a big decimal
     */
    public RealNumber(BigDecimal d) {

        super(toSign(d), parseString(String.valueOf(d)), determineSeparatorIndex(String.valueOf(d)));
    }

    /**
     * Creates a real number according to the specified parameters.
     *
     * @param aSign
     *        a string containing the sign of the number
     */
    private RealNumber(Sign aSign) {

        super(aSign, NO_DIGITS, null);
    }

    /**
     * Creates a real number according to the specified parameters.
     *
     * @param aSign
     *        the sign for this number (i.e. digit sequence)
     * @param allDigits
     *        all relevant digits of the number without a separator (i.e. point or comma)
     * @param aRelativeSeparatorIndex
     *        the index of a separator (i.e. point or comma) relative to the first digit
     */
    private RealNumber(Sign aSign, DecimalDigits[] allDigits, Integer aRelativeSeparatorIndex) {

        super(aSign, allDigits, aRelativeSeparatorIndex);
    }

    /**
     * Determines and returns the sign of the specified integer.
     *
     * @param n
     *        an integer
     *
     * @return a sign
     */
    private static Sign toSign(int n) {

        if (n < 0) {

            return Signs.NEGATIVE;
        }

        return Signs.POSITIVE;
    }

    /**
     * Determines and returns the sign of the specified long value.
     *
     * @param l
     *        a long value
     *
     * @return a sign
     */
    private static Sign toSign(long l) {

        if (l < 0L) {

            return Signs.NEGATIVE;
        }

        return Signs.POSITIVE;
    }

    /**
     * Determines and returns the sign of the specified float value.
     *
     * @param f
     *        a float value
     *
     * @return a sign
     */
    private static Sign toSign(float f) {

        if (f < 0F) {

            return Signs.NEGATIVE;
        }

        return Signs.POSITIVE;
    }

    /**
     * Determines and returns the sign of the specified double value.
     *
     * @param d
     *        a double value
     *
     * @return a sign
     */
    private static Sign toSign(double d) {

        if (d < 0D) {

            return Signs.NEGATIVE;
        }

        return Signs.POSITIVE;
    }

    /**
     * Determines and returns the sign found in the specified string.
     *
     * @param c
     *        a character
     *
     * @return a sign
     */
    private static Sign toSign(char c) {

        return Signs.findBySymbol(c);
    }

    /**
     * Determines and returns the sign of the specified string.
     *
     * @param s
     *        a string
     *
     * @return a sign
     */
    private static Sign toSign(CharSequence s) {

        try {

            return determineSignFromStandardNotation(s);

        } catch (IllegalArgumentException e) {

            // The specified string doesn't match the standard notation. Ignore this exception and check if the
            // string matches the scientific notation.
            return determineSignFromScientificNotation(s);
        }
    }

    /**
     * Determines and returns the sign of the specified big integer.
     *
     * @param i
     *        a big integer
     *
     * @return a sign
     */
    private static Sign toSign(BigInteger i) {

        return toSign(i.signum());
    }

    /**
     * Determines and returns the sign of the specified big decimal.
     *
     * @param d
     *        a big decimal
     *
     * @return a sign
     */
    private static Sign toSign(BigDecimal d) {

        return toSign(d.signum());
    }

    /**
     * Determines and returns all digits whithin the specified string.
     *
     * @param s
     *        a string
     *
     * @return all digits as array
     */
    private static DecimalDigits[] parseString(CharSequence s) {

        try {

            return determineDigitsFromStandardNotation(s);

        } catch (IllegalArgumentException e) {

            return determineDigitsFromScientificNotation(s);
        }
    }

    /**
     * Determines and returns the index of the separator (i.e. comma or point) within the specified string.
     *
     * @param s
     *        a string
     *
     * @return an index
     */
    private static int determineSeparatorIndex(CharSequence s) {

        try {

            return determineSeparatorIndexFromStandardNotation(s);

        } catch (IllegalArgumentException e) {

            return determineSeparatorIndexFromScientificNotation(s);
        }
    }

    /**
     * Returns <code>0</code> for the given digit base.
     *
     * @return <code>0</code>
     */
    @Override
    protected DecimalDigits zero() {

        return DecimalDigits.ZERO;
    }

    /**
     * Returns an empty array for the given digit base.
     *
     * @return a new array
     */
    @Override
    protected DecimalDigits[] newArray() {

        return new DecimalDigits[] { };
    }

    /**
     * Determines and returns the sign of the specified string.
     *
     * @param s
     *        a string
     *
     * @return a sign
     */
    private static Sign determineSignFromStandardNotation(CharSequence s) {

        Matcher matcher = PatternHelper.match(Constants.DECIMAL_STANDARD_NOTATION_PATTERN_STRING, s);

        if (!matcher.find()) {

            throw new IllegalArgumentException("The specified string (\"" + s + "\") is not a valid number!");
        }

        String prefix = matcher.group(Constants.SIGN_GROUP);

        if ((prefix != null) && (prefix.length() > 0)) {

            char symbol = prefix.charAt(0);
            return toSign(symbol);
        }

        return Signs.POSITIVE;
    }

    /**
     * Determines and returns the sign of the specified string.
     *
     * @param s
     *        a string
     *
     * @return a sign
     */
    private static Sign determineSignFromScientificNotation(CharSequence s) {

        Matcher matcher = PatternHelper.match(Constants.DECIMAL_SCIENTIFIC_NOTATION_PATTERN_STRING, s);

        if (!matcher.find()) {

            throw new IllegalArgumentException("The specified string (\"" + s + "\") is not a valid number!");
        }

        String prefix = matcher.group(Constants.SIGN_GROUP);

        if ((prefix != null) && (prefix.length() > 0)) {

            char symbol = prefix.charAt(0);
            return toSign(symbol);
        }

        return Signs.POSITIVE;
    }

    /**
     * Determines all digits within the specified string.
     *
     * @param s
     *        a string
     *
     * @return all digits as array
     */
    private static DecimalDigits[] determineDigitsFromStandardNotation(CharSequence s) {

        Matcher matcher = PatternHelper.match(Constants.DECIMAL_STANDARD_NOTATION_PATTERN_STRING, s);

        if (!matcher.find()) {

            throw new IllegalArgumentException("The specified string (\"" + s + "\") is not a valid number!");
        }

        String left = matcher.group(Constants.LEFT_GROUP);
        String right = matcher.group(Constants.RIGHT_GROUP);

        String normalizedString = left;
        if (right != null) {

            normalizedString = normalizedString + right;
        }

        List<DecimalDigits> list = new ArrayList<>();

        for (int a = 0; a < normalizedString.length(); a++) {

            char c = normalizedString.charAt(a);

            DecimalDigits d = DecimalDigits.charToDigit(c);
            list.add(d);
        }

        return list.toArray(new DecimalDigits[] { });
    }

    /**
     * Determines all digits within the specified string.
     *
     * @param s
     *        a string
     *
     * @return all digits as array
     */
    private static DecimalDigits[] determineDigitsFromScientificNotation(CharSequence s) {

        Matcher matcher = PatternHelper.match(Constants.DECIMAL_SCIENTIFIC_NOTATION_PATTERN_STRING, s);

        if (!matcher.find()) {

            throw new IllegalArgumentException("The specified string (\"" + s + "\") is not a valid number!");
        }

        String mantissa = matcher.group(Constants.MANTISSA_GROUP);

        String normalized = mantissa.replace(".", "");
        normalized = normalized.replace(",", "");

        List<DecimalDigits> list = new ArrayList<>();

        for (int a = 0; a < normalized.length(); a++) {

            char c = normalized.charAt(a);

            DecimalDigits d = DecimalDigits.charToDigit(c);
            list.add(d);
        }

        return list.toArray(new DecimalDigits[] { });
    }

    /**
     * Determines the index of the separator (i.e. point or comma) within the specified string.
     *
     * @param s
     *        a string
     *
     * @return an index
     */
    private static int determineSeparatorIndexFromStandardNotation(CharSequence s) {

        Matcher matcher = PatternHelper.match(Constants.DECIMAL_STANDARD_NOTATION_PATTERN_STRING, s);

        if (!matcher.find()) {

            throw new IllegalArgumentException("The specified string (\"" + s + "\") is not a valid number!");
        }

        String left = matcher.group(Constants.LEFT_GROUP);

        int index = left.length() - 1;

        return index;
    }

    /**
     * Determines the index of the separator (i.e. point or comma) within the specified string.
     *
     * @param s
     *        a string
     *
     * @return an index
     */
    private static int determineSeparatorIndexFromScientificNotation(CharSequence s) {

        Matcher matcher = PatternHelper.match(Constants.DECIMAL_SCIENTIFIC_NOTATION_PATTERN_STRING, s);

        if (!matcher.find()) {

            throw new IllegalArgumentException("The specified string (\"" + s + "\") is not a valid number!");
        }

        String signExponent = matcher.group(Constants.SIGN_EXPONENT_GROUP);
        String exponent = matcher.group(Constants.EXPONENT_GROUP);

        String normalized = "";
        if (signExponent != null) {

            normalized += signExponent;
        }
        normalized += exponent;

        int i = Integer.parseInt(normalized);

        int index = i;

        return index;
    }

    /**
     * Creates a new instance of a real number according to the specified parameters.
     *
     * @param aSign
     *        a sign
     * @param allDigits
     *        all digits in an array
     * @param aRelativeSeparatorIndex
     *        the index of a separator (i.e. comma or point) relative to the first digit
     *
     * @return a new number
     */
    public static RealNumber newInstance(Sign aSign, Digit[] allDigits, Integer aRelativeSeparatorIndex) {

        return new RealNumber(aSign, (DecimalDigits[]) allDigits.clone(), aRelativeSeparatorIndex);
    }

}
