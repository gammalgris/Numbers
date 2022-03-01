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
import jmul.math.numbers.notations.Notations;
import jmul.math.numbers.notations.UnsupportedNotationException;

import jmul.patterns.PatternHelper;


/**
 * An implementation of a real number on a base 10 numeral system.<br>
 * <br>
 * <i>Note:<br>
 * This implementation ignores irrational numbers and fractions. This may come at a later time or with an
 * additional implementation.</i>
 *
 * @author Kristian Kutin
 */
public final class RealDecimalNumber extends AbstractNumber<DecimalDigits> {

    /**
     * A constant that doesn't contain any digits.
     */
    private static final DecimalDigits[] NO_DIGITS;

    /**
     * A constant number representing positive infinity.
     */
    public static final RealDecimalNumber POSITIVE_INFINITY;

    /**
     * A constant number representing negative infinity.
     */
    public static final RealDecimalNumber NEGATIVE_INFINITY;

    /*
     * The static initializer.
     */
    static {

        NO_DIGITS = new DecimalDigits[] { };

        POSITIVE_INFINITY = new RealDecimalNumber(Signs.POSITIVE);
        NEGATIVE_INFINITY = new RealDecimalNumber(Signs.NEGATIVE);
    }

    /**
     * The default constructor. Creates a real number with a zero value.
     */
    public RealDecimalNumber() {

        this(0);
    }

    /**
     * Creates a real number according to the specified parameters (i.e. signed infinity).
     *
     * @param aSign
     *        a string containing the sign of the number
     */
    private RealDecimalNumber(Sign aSign) {

        super(aSign, NO_DIGITS, null);
    }

    /**
     * Creates a real number according to the specified integer.
     *
     * @param n
     *        an integer
     */
    public RealDecimalNumber(int n) {

        this(String.valueOf(n));
    }

    /**
     * Creates a real number according to the specified long value.
     *
     * @param l
     *        a long value
     */
    public RealDecimalNumber(long l) {

        this(String.valueOf(l));
    }

    /**
     * Creates a real number according to the specified float value.
     *
     * @param f
     *        a float value
     */
    public RealDecimalNumber(float f) {

        this(String.valueOf(f));
    }

    /**
     * Creates a real number according to the specified double value.
     *
     * @param d
     *        a double value
     */
    public RealDecimalNumber(double d) {

        this(String.valueOf(d));
    }

    /**
     * Creates a real number according to the specified big integer.
     *
     * @param i
     *        a big integer
     */
    public RealDecimalNumber(BigInteger i) {

        this(String.valueOf(i));
    }

    /**
     * Creates a real number according to the specified big decimal.
     *
     * @param d
     *        a big decimal
     */
    public RealDecimalNumber(BigDecimal d) {

        this(String.valueOf(d));
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
    public RealDecimalNumber(Sign aSign, DecimalDigits[] allDigits, Integer aRelativeSeparatorIndex) {

        super(aSign, allDigits.clone(), aRelativeSeparatorIndex);
    }

    /**
     * A copy constructor.
     *
     * @param n
     *        a number
     */
    public RealDecimalNumber(RealDecimalNumber n) {

        this(n.sign, n.digits, n.relativeSeparatorIndex);
    }

    /**
     * Creates a real number according to the specified string.
     *
     * @param s
     *        a string
     */
    public RealDecimalNumber(CharSequence s) {

        this(parseInput(s));
    }

    /**
     * Creates a real number according to the specified parameters.
     *
     * @param parsingResult
     *        a parsing result (including notation details and a matcher)
     */
    private RealDecimalNumber(NotationParsingResult parsingResult) {

        this(parsingResult.notation(), parsingResult.matcher());
    }

    /**
     * Creates a real number according to the specified parameters.
     *
     * @param notation
     *        a number notation
     * @param matcher
     *        a matcher for regular expressions
     */
    private RealDecimalNumber(Notations notation, Matcher matcher) {

        super(parseSign(matcher), parseDigits(notation, matcher), determineSeparatorIndex(notation, matcher));
    }

    /**
     * Parses the specified input and returns a matcher.
     *
     * @param s
     *        a string
     *
     * @return a parsing result (including notation details and a matcher)
     */
    private static NotationParsingResult parseInput(CharSequence s) {

        Matcher matcher;
        Notations notation;

        matcher = PatternHelper.match(Constants.DECIMAL_STANDARD_NOTATION_PATTERN_STRING, s);
        notation = Notations.STANDARD_NOTATION;

        if (!matcher.find()) {

            matcher = PatternHelper.match(Constants.DECIMAL_SCIENTIFIC_NOTATION_PATTERN_STRING, s);
            notation = Notations.SCIENTIFIC_NOTATION;

            if (!matcher.find()) {

                String message = String.format("The specified string (\"%s\") is not a valid number!", s);
                throw new IllegalArgumentException(message);
            }
        }

        return new NotationParsingResult(notation, matcher);
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
     * Parse the sign from a number string with the specified matcher.
     *
     * @param matcher
     *        a matcher for regular expressions
     *
     * @return a sign
     */
    private static Sign parseSign(Matcher matcher) {

        String signString = matcher.group(Constants.SIGN_GROUP);

        if ((signString != null) && (signString.length() > 0)) {

            char symbol = signString.charAt(0);
            return Signs.findBySymbol(symbol);
        }

        return Signs.POSITIVE;
    }

    /**
     * Parse the digits from a number with the specified matcher.
     *
     * @param notation
     *        the actual notation
     * @param matcher
     *        a matcher for regular expressions
     *
     * @return all digits
     */
    private static DecimalDigits[] parseDigits(Notations notation, Matcher matcher) {

        switch (notation) {
        case STANDARD_NOTATION:
            return parseDigitsInStandardNotation(matcher);
        case SCIENTIFIC_NOTATION:
            return parseDigitsInScientificNotation(matcher);
        }

        throw new UnsupportedNotationException();
    }

    /**
     * Parse the digits from a string with the specified matcher.
     *
     * @param matcher
     *        a matcher for regular expressions
     *
     * @return all digits as array
     */
    private static DecimalDigits[] parseDigitsInStandardNotation(Matcher matcher) {

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
     * Parse the digits from a string with the specified matcher.
     *
     * @param matcher
     *        a matcher for regular expressions
     *
     * @return all digits as array
     */
    private static DecimalDigits[] parseDigitsInScientificNotation(Matcher matcher) {

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
     * Determines the index of the separator (i.e. point or comma) within a string with the specified matcher.
     *
     * @param notation
     *        the actual notation
     * @param matcher
     *        a matcher for regular expressions
     *
     * @return an index
     */
    private static int determineSeparatorIndex(Notations notation, Matcher matcher) {

        switch (notation) {
        case STANDARD_NOTATION:
            return determineSeparatorIndexInStandardNotation(matcher);
        case SCIENTIFIC_NOTATION:
            return determineSeparatorIndexInScientificNotation(matcher);
        }

        throw new UnsupportedNotationException();
    }

    /**
     * Determines the index of the separator (i.e. point or comma) within a string with the specified matcher.
     *
     * @param matcher
     *        a matcher for regular expressions
     *
     * @return an index
     */
    private static int determineSeparatorIndexInStandardNotation(Matcher matcher) {

        String left = matcher.group(Constants.LEFT_GROUP);

        return left.length() - 1;
    }

    /**
     * Determines the index of the separator (i.e. point or comma) within a string with the specified matcher.
     *
     * @param matcher
     *        a matcher for regular expressions
     *
     * @return an index
     */
    private static int determineSeparatorIndexInScientificNotation(Matcher matcher) {

        String signExponent = matcher.group(Constants.SIGN_EXPONENT_GROUP);
        String exponent = matcher.group(Constants.EXPONENT_GROUP);

        String normalized = "";
        if (signExponent != null) {

            normalized += signExponent;
        }
        normalized += exponent;

        return Integer.parseInt(normalized);
    }

}


