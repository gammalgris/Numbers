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

package jmul.math.constants;


import jmul.math.fractions.Fraction;
import jmul.math.numbers.Constants;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;
import static jmul.math.fractions.FractionHelper.createFraction;


/**
 * A utility class for creating constants.
 *
 * @author Kristian Kutin
 */
public class ConstantHelper {

    /**
     * The default constructor.
     */
    private ConstantHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Creates a new constant according to the specified parameters.
     *
     * @param string
     *        a sequence of digits
     *
     * @return a constant
     */
    public static Constant createConstantNumber(String string) {

        return createConstantNumber(createNumber(Constants.DEFAULT_NUMBER_BASE, string));
    }

    /**
     * Creates a new constant according to the specified parameters.
     *
     * @param base
     *        a number base
     * @param string
     *        a sequence of digits
     *
     * @return a constant
     */
    public static Constant createConstantNumber(int base, String string) {

        return createConstantNumber(createNumber(base, string));
    }

    /**
     * Creates a new constant according to the specified parameters.
     *
     * @param value
     *        an initial value
     *
     * @return a constant
     */
    public static Constant createConstantNumber(Number value) {

        return new ConstantNumberImpl(value);
    }

    /**
     * Creates a new constant according to the specified parameters.
     *
     * @param string
     *        a sequence of digits
     *
     * @return a constant
     */
    public static Constant createConstantFraction(String string) {

        return createConstantFraction(Constants.DEFAULT_NUMBER_BASE, string);
    }

    /**
     * Creates a new constant according to the specified parameters.
     *
     * @param base
     *        a number base
     * @param integerPartString
     *        a sequence of digits of the integer part
     *
     * @return a constant
     */
    public static Constant createConstantFraction(int base, String integerPartString) {

        return createConstantFraction(createFraction(base, integerPartString));
    }

    /**
     * Creates a new constant according to the specified parameters.
     *
     * @param base
     *        a number base
     * @param numeratorString
     *        a sequence of digits of the numerator
     * @param denominatorString
     *        a sequence of digits of the denominator
     *
     * @return a constant
     */
    public static Constant createConstantFraction(int base, String numeratorString, String denominatorString) {

        return createConstantFraction(createFraction(base, numeratorString, denominatorString));
    }

    /**
     * Creates a new constant according to the specified parameters.
     *
     * @param base
     *        a number base
     * @param integerPartString
     *        a sequence of digits of the integer part
     * @param numeratorString
     *        a sequence of digits of the numerator
     * @param denominatorString
     *        a sequence of digits of the denominator
     *
     * @return a constant
     */
    public static Constant createConstantFraction(int base, String integerPartString, String numeratorString,
                                                  String denominatorString) {

        return createConstantFraction(createFraction(base, integerPartString, numeratorString, denominatorString));
    }

    /**
     * Creates a new constant according to the specified parameters.
     *
     * @param value
     *        an initial value
     *
     * @return a constant
     */
    public static Constant createConstantFraction(Fraction value) {

        return new ConstantFractionImpl(value);
    }

}
