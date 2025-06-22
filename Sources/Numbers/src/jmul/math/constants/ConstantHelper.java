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


import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;


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
     * @param base
     *        a number base
     * @param string
     *        a sequence of digits
     *
     * @return a constant
     */
    public static Constant createConstant(int base, String string) {

        return createConstant(createNumber(base, string));
    }

    /**
     * Creates a new constant according to the specified parameters.
     *
     * @param value
     *        an initial value
     *
     * @return a constant
     */
    public static Constant createConstant(Number value) {

        return new ConstantImpl(value);
    }

}
