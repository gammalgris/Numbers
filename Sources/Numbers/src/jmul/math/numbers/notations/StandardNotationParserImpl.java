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

package jmul.math.numbers.notations;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static jmul.math.numbers.Constants.ZERO;
import jmul.math.numbers.Sign;
import jmul.math.numbers.Signs;
import jmul.math.numbers.digits.Digit;
import jmul.math.numbers.digits.PositionalNumeralSystems;
import jmul.math.numbers.nodes.DigitNode;
import jmul.math.numbers.nodes.NodesHelper;


/**
 * An implementation for a parser which parses numbers represented by the standard notation.
 *
 * @author Kristian Kutin
 */
public class StandardNotationParserImpl implements NotationParser {

    /**
     * Details about the notation.
     */
    private final Notation notation;

    /**
     * A pattern matcher.
     */
    private final Pattern pattern;

    /**
     * The default constructor.
     */
    public StandardNotationParserImpl() {

        super();

        notation = Notations.STANDARD_NOTATION;
        pattern = Pattern.compile(notation.regex());
    }

    /**
     * Parses the specified string and returns all components of a number.
     *
     * @param base
     *        a base
     * @param string
     *        a string representing a number
     *
     * @return the parsing results
     */
    @Override
    public ParsingResult parseNotation(int base, String string) {

        Matcher matcher = pattern.matcher(string);

        if (!matcher.matches()) {

            String message = String.format("The specified string doesn't match the standard notation (\"%s\")", string);
            throw new IllegalArgumentException(message);
        }

        String signString = matcher.group(notation.namedCapturingGroups().get(0));
        String leftString = matcher.group(notation.namedCapturingGroups().get(1));
        String rightString = matcher.group(notation.namedCapturingGroups().get(2));

        Sign sign;
        if ((signString == null) || signString.isEmpty()) {

            sign = Signs.POSITIVE;

        } else {

            char symbol = signString.charAt(0);
            sign = Signs.findBySymbol(symbol);
        }

        String trimmedLeftString = trimLeftString(leftString);
        DigitNode nodeLeft = parseLeftString(base, trimmedLeftString);

        if (rightString != null) {

            String trimmedRightString = trimRightString(rightString);
            DigitNode nodeRight = parseRightString(base, trimmedRightString);
            NodesHelper.linkNodes(nodeLeft, nodeRight);
        }

        return new ParsingResult(sign, base, nodeLeft);
    }

    /**
     * Returns a copy of the specified string with removed leading zeroes. The string is
     * traversed from left to right.
     *
     * @param string
     *        a string
     *
     * @return a trimmed string
     */
    private String trimLeftString(String string) {

        int lastIndex = string.length() - 1;
        int index = 0;

        for (int i = 0; i <= lastIndex; i++) {

            char c = string.charAt(i);

            if (c != ZERO) {

                index = i;
                break;
            }
        }

        return string.substring(index);
    }


    /**
     * Parses the string from left to right. The reference which is returned points to the utmost right
     * node.
     *
     * @param base
     *        a base
     * @param string
     *        a string
     *
     * @return a reference to a linked list
     */
    private DigitNode parseLeftString(int base, String string) {

        int lastIndex = string.length() - 1;

        DigitNode previousNode = null;
        DigitNode node = null;

        for (int i = 0; i <= lastIndex; i++) {

            char c = string.charAt(i);
            Digit digit = PositionalNumeralSystems.charToDigit(base, c);

            node = NodesHelper.createNode(digit);

            if (previousNode != null) {

                NodesHelper.linkNodes(previousNode, node);
            }

            previousNode = node;
        }

        return node;
    }

    /**
     * Returns a copy of the specified string with removed trailing zeroes. The string is
     * traversed from right to left.
     *
     * @param string
     *        a string
     *
     * @return a trimmed string
     */
    private String trimRightString(String string) {

        int length = string.length();

        if (length == 1) {

            int startIndex = 0;
            char c = string.charAt(startIndex);

            if (c == ZERO) {
                
                return "";
                
            } else {

                return string;
            }
        }


        int lastIndex = string.length() - 1;
        int index = 0;

        for (int i = lastIndex; i >= 0; i--) {

            char c = string.charAt(i);

            if (c != ZERO) {

                index = i;
                break;
            }
        }


        return string.substring(0, index + 1);
    }

    /**
     * Parses the string from left to right. The reference which is returned points to the utmost left
     * node.
     *
     * @param base
     *        a base
     * @param string
     *        a string
     *
     * @return a reference to a linked list
     */
    private DigitNode parseRightString(int base, String string) {

        int lastIndex = string.length() - 1;

        DigitNode firstNode = null;
        DigitNode previousNode = null;
        DigitNode node = null;

        for (int i = 0; i <= lastIndex; i++) {

            char c = string.charAt(i);
            Digit digit = PositionalNumeralSystems.charToDigit(base, c);

            node = NodesHelper.createNode(digit);

            if (firstNode == null) {

                firstNode = node;
            }

            if (previousNode != null) {

                NodesHelper.linkNodes(previousNode, node);
            }

            previousNode = node;
        }

        return firstNode;
    }

}
