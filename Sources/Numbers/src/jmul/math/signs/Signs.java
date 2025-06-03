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

package jmul.math.signs;


/**
 * This enumeration contains all signs.
 *
 * @author Kristian Kutin
 */
public enum Signs implements Sign {


    POSITIVE('+'),
    NEGATIVE('-'), ;


    /**
     * The symbol associated with the sign.
     */
    private final char symbol;

    /**
     * Creates a new enumeration element with the specified symbol.
     *
     * @param aSymbol
     *        a symbol to be associated with the enumeration element
     */
    private Signs(char aSymbol) {

        symbol = aSymbol;
    }


    /**
     * Returns the symbol associated with this sign.
     *
     * @return a symbol
     */
    @Override
    public char symbol() {

        return symbol;
    }

    /**
     * Returns a string representation of the enumeration element.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return String.valueOf(symbol);
    }

    /**
     * Find a digit by its symbol.
     *
     * @param aSymbol
     *        a string containing a symbol
     *
     * @return a decimal digit
     */
    public static Sign findBySymbol(char aSymbol) {

        for (Signs sign : values()) {

            if (sign.symbol() == aSymbol) {

                return sign;
            }
        }

        String message = String.format("'%s' is not a valid symbol!", aSymbol);
        throw new IllegalArgumentException(message);
    }

    /**
     * Negates the specified sign.
     *
     * @param aSign
     *        a sign
     *
     * @return a negated sign
     */
    public static Sign negate(Sign aSign) {

        if (POSITIVE == aSign) {

            return NEGATIVE;
        }

        return POSITIVE;
    }

    /**
     * Checks if the speficied sign is positive.
     *
     * @param aSign
     *        a sign
     *
     * @return <code>true</code> if the specified sign is positive, else <code>false</code>
     */
    public static boolean isPositive(Sign aSign) {

        return POSITIVE.equals(aSign);
    }

    /**
     * Checks if the speficied sign is positive.
     *
     * @param aSign
     *        a sign
     *
     * @return <code>true</code> if the specified sign is positive, else <code>false</code>
     */
    public static boolean isNegative(Sign aSign) {

        return NEGATIVE.equals(aSign);
    }

    /**
     * Evaluates the specified signs similar to the logical operator or.
     *
     * @param sign1
     *        a sign
     * @param sign2
     *        a sign
     *
     * @return returns a positive sign if one sign is positive or both signs are positive, else returns a negative sign.
     */
    public static Sign or(Sign sign1, Sign sign2) {

        if (isPositive(sign1) || isPositive(sign2)) {

            return POSITIVE;
        }

        return NEGATIVE;
    }

    /**
     * Evaluates the specified signs similar to the logical operator and.
     *
     * @param sign1
     *        a sign
     * @param sign2
     *        a sign
     *
     * @return returns a positive sign if both specified signs are positive, else returns a negative sign.
     */
    public static Sign and(Sign sign1, Sign sign2) {

        if (isPositive(sign1) && isPositive(sign2)) {

            return POSITIVE;
        }

        return NEGATIVE;
    }

    /**
     * Evaluates the specified signs similar to the logical operator xor.
     *
     * @param sign1
     *        a sign
     * @param sign2
     *        a sign
     *
     * @return returns a positive sign if one specified sign is positive and the other specified sign is negative, else returns a negative sign.
     */
    public static Sign xor(Sign sign1, Sign sign2) {

        if (isNegative(sign1) && isPositive(sign2)) {

            return POSITIVE;
        }

        if (isPositive(sign1) && isNegative(sign2)) {

            return POSITIVE;
        }

        return NEGATIVE;
    }

    /**
     * Evaluates the specified signs as if a multiplication is done.
     *
     * @param sign1
     *        a sign
     * @param sign2
     *        a sign
     *
     * @return returns a positive sign if both signs are positive or negative, else returns a negative sign.
     */
    public static Sign multiply(Sign sign1, Sign sign2) {

        if (isPositive(sign1) && isPositive(sign2)) {

            return POSITIVE;
        }

        if (isNegative(sign1) && isNegative(sign2)) {

            return POSITIVE;
        }

        return NEGATIVE;
    }

    /**
     * Evaluates the specified signs as if a division is done and determines the sign of the result.
     *
     * @param sign1
     *        a sign
     * @param sign2
     *        a sign
     *
     * @return returns a positive sign if the both operands are either positive or negative, else returns a negative sign.
     */
    public static Sign divideAndDetermineResultSign(Sign sign1, Sign sign2) {

        if (isNegative(sign1) && isNegative(sign2)) {

            return POSITIVE;
        }

        if (isPositive(sign1) && isPositive(sign2)) {

            return POSITIVE;
        }

        return NEGATIVE;
    }

    /**
     * Evaluates the specified signs as if a division is done and determines the sign of the remainder.
     *
     * @param sign1
     *        a sign
     * @param sign2
     *        a sign
     *
     * @return returns a negative sign if both operands are negative or of the first operand is negative and the second operand is positive, else returns a positive sign.
     */
    public static Sign divideAndDetermineRemainderSign(Sign sign1, Sign sign2) {

        if (isNegative(sign1) && isNegative(sign2)) {

            return NEGATIVE;
        }

        if (isNegative(sign1) && isPositive(sign2)) {

            return NEGATIVE;
        }

        return POSITIVE;
    }

}
