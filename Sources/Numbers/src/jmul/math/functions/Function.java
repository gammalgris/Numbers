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

package jmul.math.functions;


import jmul.math.numbers.Number;


/**
 * This interface describes a generic mathematical function.
 *
 * @author Kristian Kutin
 */
public interface Function {

    /**
     * Returns the used number base.
     * 
     * @return a number base
     */
    int base();

    /**
     * Calculates the output value according to the specified input value.
     *
     * @param number
     *        a value
     *
     * @return an output value
     */
    Number calculate(Number number);

    /**
     * Returns the derivative function for this function.
     *
     * @return a derivative function
     */
    Function derivativeFunction();

    /**
     * Returns a string in a function notation.
     *
     * @return a string in a function notation
     */
    String toFunctionNotation();

    /**
     * Returns a string representation for this formula.
     *
     * @return a string representation
     */
    String toString();

}
