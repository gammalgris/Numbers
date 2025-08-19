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


import jmul.math.digits.PositionalNumeralSystems;
import static jmul.math.numbers.Constants.DEFAULT_NUMBER_BASE;
import jmul.math.numbers.nodes.DigitNode;
import jmul.math.numbers.nodes.NodesHelper;
import jmul.math.numbers.notations.ParserHelper;
import jmul.math.numbers.notations.ParsingResult;
import jmul.math.signs.Sign;
import jmul.math.signs.Signs;


/**
 * A class with utility functions for numbers.
 *
 * @author Kristian Kutin
 */
public final class NumberHelper {

    /**
     * The default constructor.
     */
    private NumberHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Creates a new number according to the specified parameters.
     *
     * @param numberString
     *        a sequence of digits
     *
     * @return a number
     */
    public static Number createNumber(String numberString) {

        return createNumber(DEFAULT_NUMBER_BASE, numberString);
    }

    /**
     * Creates a new number according to the specified parameters.
     *
     * @param base
     *        the number base
     * @param numberString
     *        a sequence of digits
     *
     * @return a number
     */
    public static Number createNumber(int base, String numberString) {

        if (numberString == null) {

            return new NumberImpl(base);
        }

        ParsingResult parsingResult = ParserHelper.parseString(base, numberString);
        return new NumberImpl(parsingResult.base, parsingResult.sign, parsingResult.centerNode);
    }

    /**
     * Creates a new number according to the specified parameters.
     *
     * @param number
     *        a number
     *
     * @return a clone of the specified number
     */
    public static Number createNumber(Number number) {

        return new NumberImpl(number.base(), number.sign(), NodesHelper.cloneLinkedList(number.centerNode()));
    }

    /**
     * Creates a new number according to the specified parameters.
     *
     * @param parsingResult
     *        a parsing result
     *
     * @return a number
     */
    protected static Number createNumber(ParsingResult parsingResult) {

        return new NumberImpl(parsingResult.base, parsingResult.sign, parsingResult.centerNode);
    }

    /**
     * Creates a new number according to the specified parameters. The number consists of a single digit.
     *
     * @param base
     *        the number base
     * @param sign
     *        the sign of the number
     * @param ordinal
     *        the ordinal value of the number (i.e. digit)
     *
     * @return a number
     */
    public static Number createNumber(int base, Sign sign, int ordinal) {

        String symbol = PositionalNumeralSystems.toString(base, ordinal);

        Number number;
        if (Signs.isNegative(sign)) {

            number = createNumber(base, Signs.NEGATIVE.toString() + symbol);

        } else {

            number = createNumber(base, symbol);
        }

        return number;
    }

    /**
     * Creates a new number according to the specified parameters.
     *
     * @param base
     *        the number base
     * @param sign
     *        the sign of the number
     * @param centerNode
     *        the center node of a linked list
     *
     * @return a number
     */
    public static Number createNumber(int base, Sign sign, DigitNode centerNode) {

        return new NumberImpl(base, sign, centerNode);
    }

    /**
     * Creates a number which represents infinity.
     *
     * @return a number
     */
    public static Number createInfinity() {

        return createInfinity(Constants.DEFAULT_NUMBER_BASE);
    }

    /**
     * Creates a number which represents infinity.
     *
     * @param base
     *        the number base
     *
     * @return a number
     */
    public static Number createInfinity(int base) {

        return createNumber(base, Signs.POSITIVE, null);
    }

    /**
     * Creates a number which represents infinity.
     *
     * @param base
     *        the number base
     * @param sign
     *        a sign
     *
     * @return a number
     */
    public static Number createInfinity(int base, Sign sign) {

        return createNumber(base, sign, null);
    }

    /**
     * Creates a number which represents negative infinity.
     *
     * @return
     */
    public static Number createNegativeInfinity() {

        return createNegativeInfinity(Constants.DEFAULT_NUMBER_BASE);
    }

    /**
     * Creates a number which represents negative infinity.
     *
     * @param base
     *        the number base
     *
     * @return a number
     */
    public static Number createNegativeInfinity(int base) {

        return createNumber(base, Signs.NEGATIVE, null);
    }

    /**
     * Parses the specified byte value.
     *
     * @param b
     *        a byte value
     *
     * @return a number
     */
    public static Number parseByte(byte b) {

        ParsingResult parsingResult = ParserHelper.parseByte(b);

        return createNumber(parsingResult);
    }

    /**
     * Parses the specified short value.
     *
     * @param s
     *        a short value
     *
     * @return a number
     */
    public static Number parseShort(short s) {

        ParsingResult parsingResult = ParserHelper.parseShort(s);

        return createNumber(parsingResult);
    }

    /**
     * Parses the specified integer value.
     *
     * @param i
     *        a integer value
     *
     * @return a number
     */
    public static Number parseInteger(int i) {

        ParsingResult parsingResult = ParserHelper.parseInteger(i);

        return createNumber(parsingResult);
    }

    /**
     * Parses the specified long value.
     *
     * @param l
     *        a long value
     *
     * @return a number
     */
    public static Number parseLong(long l) {

        ParsingResult parsingResult = ParserHelper.parseLong(l);

        return createNumber(parsingResult);
    }

    /**
     * Parses the specified float value.
     *
     * @param f
     *        a float value
     *
     * @return a number
     */
    public static Number parseFloat(float f) {

        ParsingResult parsingResult = ParserHelper.parseFloat(f);

        return createNumber(parsingResult);
    }

    /**
     * Parses the specified double value.
     *
     * @param d
     *        a double value
     *
     * @return a number
     */
    public static Number parseDouble(double d) {

        ParsingResult parsingResult = ParserHelper.parseDouble(d);

        return createNumber(parsingResult);
    }

    /**
     * Parses the specified number value.
     *
     * @param n
     *        a double value
     *
     * @return a number
     */
    public static Number parseNumber(java.lang.Number n) {

        ParsingResult parsingResult = ParserHelper.parseNumber(n);

        return createNumber(parsingResult);
    }

    /**
     * Counts digits left of a decimal separator.<br>
     * <br>
     * <i>Note:<br>
     * The function may not work on too large numbers</i>
     *
     * @param number
     *        a number
     *
     * @return a digit count
     *
     * @deprecated integrate into number implementation and replace counter type.
     */
    @Deprecated
    public static int countLeftDigits(Number number) {

        int count = 0;

        if (number.isInfinity()) {

            return count;
        }

        DigitNode node = number.centerNode();

        while (node != null) {

            int previousCount = count;
            count++;

            if (count < previousCount) {

                throw new ArithmeticOverflowException();
            }

            node = node.leftNode();
        }

        return count;
    }

    /**
     * Counts digits right of the decimal separator.<br>
     * <br>
     * <i>Note:<br>
     * The function may not work on too large numbers</i>
     *
     * @param number
     *        a number
     *
     * @return a digit count
     *
     * @deprecated integrate into number implementation and replace counter type.
     */
    @Deprecated
    public static int countRightDigit(Number number) {

        int count = -1;

        if (number.isInfinity()) {

            return count;
        }

        DigitNode node = number.centerNode();

        while (node != null) {

            int previousCount = count;
            count++;

            if (count < previousCount) {

                throw new ArithmeticOverflowException();
            }

            node = node.rightNode();
        }

        return count;
    }

}


/**
 * A custom exception class.
 *
 * @deprecated is obsolete
 *
 * @author Kristian Kutin
 */
@Deprecated
class ArithmeticOverflowException extends RuntimeException {

    /**
     * The default constructor.
     */
    public ArithmeticOverflowException() {

        super();
    }

}
