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

package jmul.math.logarithms;


import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;


/**
 * A utility class for logarithms.
 *
 * @author Kristian Kutin
 */
public final class LogarithmHelper {

    /**
     * The default constructor.
     */
    private LogarithmHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Creates a new logarithm expression.
     *
     * @param base
     *        a number base
     * @param logarithmBaseString
     *        the logarithm base
     * @param numerusString
     *        the numerus
     *
     * @return a logarithm expression
     */
    public static Logarithm createLogarithm(int base, String logarithmBaseString, String numerusString) {

        return new LogarithmImpl(createNumber(base, logarithmBaseString), createNumber(base, numerusString));
    }

    /**
     * Creates a new logarithm expression.
     *
     * @param logarithmBase
     *        the logarithm base
     * @param numerus
     *        the numers
     *
     * @return a logarithm expression
     */
    public static Logarithm createLogarithm(Number logarithmBase, Number numerus) {

        return new LogarithmImpl(logarithmBase, numerus);
    }

}
