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

import jmul.math.numbers.Sign;
import jmul.math.numbers.Signs;
import jmul.math.numbers.digits.Digit;
import jmul.math.numbers.digits.PositionalNumeralSystems;
import jmul.math.numbers.nodes.DigitNode;
import jmul.math.numbers.nodes.Nodes;


/**
 * An implementation for a parser which parses numbers represented by the standard notation.
 *
 * @author Kristian Kutin
 */
public class ScientificNotationParserImpl implements NotationParser {

    private static final char ZERO;

    /*
     * The static initializer.
     */
    static {

        ZERO = '0';
    }

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
    public ScientificNotationParserImpl() {

        super();

        notation = Notations.SCIENTIFIC_NOATATION;
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

            String message =
                String.format("The specified string doesn't match the scientific notation (\"%s\")", string);
            throw new IllegalArgumentException(message);
        }

        String signString = matcher.group(notation.namedCapturingGroups().get(0));
        String mantissaString = matcher.group(notation.namedCapturingGroups().get(1));
        String exponentSignString = matcher.group(notation.namedCapturingGroups().get(2));
        String exponentString = matcher.group(notation.namedCapturingGroups().get(3));


        Sign sign;
        if ((signString == null) || signString.isEmpty()) {

            sign = Signs.POSITIVE;

        } else {

            char symbol = signString.charAt(0);
            sign = Signs.findBySymbol(symbol);
        }


        Sign exponentSign;
        if ((exponentSignString == null) || exponentSignString.isEmpty()) {

            exponentSign = Signs.POSITIVE;

        } else {

            char symbol = exponentSignString.charAt(0);
            exponentSign = Signs.findBySymbol(symbol);
        }


        int exponent = Integer.parseInt(exponentString);

        mantissaString = mantissaString.replaceFirst("[.]", "");
        DigitNode center = parseMantissaString(base, mantissaString);

        if (Signs.isPositive(exponentSign)) {

            center = moveCenterToRight(base, center, exponent);

        } else {

            center = moveCenterToLeft(base, center, exponent);
        }

        return new ParsingResult(sign, base, center);
    }

    /**
     * Parses the mantissa from left to right. The reference which is returned points to the utmost left
     * node.
     *
     * @param base
     *        a base
     * @param string
     *        the first digit symbol
     *
     * @return a reference to a linked list
     */
    private DigitNode parseMantissaString(int base, String string) {

        int lastIndex = string.length() - 1;

        DigitNode firstNode = null;
        DigitNode previousNode = null;
        DigitNode node = null;

        for (int i = 0; i <= lastIndex; i++) {

            char c = string.charAt(i);
            Digit digit = PositionalNumeralSystems.charToDigit(base, c);

            node = Nodes.createNode(digit);

            if (previousNode != null) {

                Nodes.linkNodes(previousNode, node);
            }

            previousNode = node;

            if (i == 0) {

                firstNode = node;
            }
        }

        return firstNode;
    }

    /**
     * Moves the center node to the right according to the specified exponent. Missing zero digits are
     * added as needed. The reference which is returned points to the center node.
     *
     * @param base
     *        a base
     * @param node
     *        the first digit
     * @param exponent
     *        the unsigned exponent
     *
     * @return a reference to a linked list
     */
    private DigitNode moveCenterToRight(int base, DigitNode node, int exponent) {

        DigitNode centerNode = node;

        for (int i = 1; i <= exponent; i++) {

            DigitNode rightNode = centerNode.rightNode();
            if (rightNode == null) {

                Digit digit = PositionalNumeralSystems.charToDigit(base, ZERO);
                rightNode = Nodes.createNode(digit);

                Nodes.linkNodes(centerNode, rightNode);
            }

            centerNode = centerNode.rightNode();
        }

        return centerNode;
    }

    /**
     * Moves the center node to the left according to the specified exponent. Missing zero digits are
     * added as needed. The reference which is returned points to the utmost left node.
     *
     * @param base
     *        a base
     * @param node
     *        the first digit
     * @param exponent
     *        the unsigned exponent
     *
     * @return a reference to a linked list
     */
    private DigitNode moveCenterToLeft(int base, DigitNode node, int exponent) {

        DigitNode centerNode = node;

        for (int i = 1; i <= exponent; i++) {

            DigitNode leftNode = centerNode.leftNode();
            if (leftNode == null) {

                Digit digit = PositionalNumeralSystems.charToDigit(base, ZERO);
                leftNode = Nodes.createNode(digit);

                Nodes.linkNodes(leftNode, centerNode);
            }

            centerNode = centerNode.leftNode();
        }

        return centerNode;
    }

}
