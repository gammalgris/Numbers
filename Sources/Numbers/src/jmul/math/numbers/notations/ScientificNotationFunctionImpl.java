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


import jmul.math.numbers.Constants;
import jmul.math.numbers.Number;
import jmul.math.numbers.nodes.DigitNode;
import static jmul.math.numbers.Constants.ZERO;


/**
 * An implementation of a function that creates a scientific notation representation for the specified number.
 *
 * @author Kristian Kutin
 */
public class ScientificNotationFunctionImpl implements NotationFunction {

    /**
     * The default constructor.
     */
    public ScientificNotationFunctionImpl() {

        super();
    }

    /**
     * Translates the specified number into a string notation.
     *
     * @param number
     *        a number
     * @param decimalSeparator
     *        a decimal separator
     *
     * @return a string representation
     */
    @Override
    public String toString(Number number, char decimalSeparator) {

        StringBuilder buffer = new StringBuilder();

        if (number.isInfinity()) {

            buffer.append(Constants.INFINITY_REPRESENTATION);

        } else if (number.isZero()) {

            buffer.append(number.centerNode().digit());

        } else {

            TraversalResult result = traverseNumber(number.centerNode());

            DigitNode relativeCenter = result.relativeCenter();

            buffer.append(relativeCenter.digit());

            if (relativeCenter.rightNode() != null) {

                buffer.append(decimalSeparator);
            }

            DigitNode nextNode = relativeCenter.rightNode();
            while (nextNode != null) {

                buffer.append(nextNode.digit());
                nextNode = nextNode.rightNode();
            }

            CharSequence trimmedRight = trimRightSide(buffer);
            buffer = new StringBuilder(trimmedRight);

            buffer.append(Constants.EXPONENT_ABBREVIATION);
            buffer.append(result.exponent());
        }

        if (number.isNegative()) {

            buffer.insert(0, number.sign());
        }

        return String.valueOf(buffer);
    }

    private TraversalResult traverseNumber(DigitNode center) {

        if (center.digit().isZero() && (center.rightNode() != null)) {

            return goRight(center);

        } else if (center.leftNode() != null) {

            return goLeft(center);
        }

        return new TraversalResult(0, center);
    }

    private TraversalResult goRight(DigitNode center) {

        int exponent = 0;
        DigitNode relativeCenter = center;

        while (true) {

            if (!relativeCenter.digit().isZero()) {

                break;
            }

            exponent--;
            relativeCenter = relativeCenter.rightNode();
        }

        return new TraversalResult(exponent, relativeCenter);
    }

    private TraversalResult goLeft(DigitNode center) {

        int exponent = 0;
        DigitNode relativeCenter = center;

        while (true) {

            if (relativeCenter.leftNode() == null) {

                break;
            }

            exponent++;
            relativeCenter = relativeCenter.leftNode();
        }

        return new TraversalResult(exponent, relativeCenter);
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
    private CharSequence trimRightSide(CharSequence string) {

        int lastIndex = string.length() - 1;
        int index = 0;

        for (int i = lastIndex; i >= 0; i--) {

            char c = string.charAt(i);

            if (c != ZERO) {

                index = i;
                break;
            }
        }

        return string.subSequence(0, index + 1);
    }

}

class TraversalResult {

    private final int exponent;

    private final DigitNode relatvieCenter;

    public TraversalResult(int exponent, DigitNode relativeCenter) {

        this.exponent = exponent;
        this.relatvieCenter = relativeCenter;
    }

    public int exponent() {

        return exponent;
    }

    public DigitNode relativeCenter() {

        return relatvieCenter;
    }

}
