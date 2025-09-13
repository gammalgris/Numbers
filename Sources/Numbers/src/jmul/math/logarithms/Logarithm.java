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


import jmul.math.expressions.Expression;
import jmul.math.numbers.Number;


/**
 * This interface describes a logarithm expression (i.e. exponent = logarithm<sub>base</sub>numerus =&gt; base<sup>exponent</sup> = numerus).<br>
 * <br>
 * See <a href="https://en.wikipedia.org/wiki/Logarithm">Logarithm</a><br>
 * <br>
 * <i>Note:<br>
 * This is an intermediate representation to delay evaluation of the expression. Thus
 * you might avoid some rounding errors.</i>
 *
 * @author Kristian Kutin
 */
public interface Logarithm extends Expression {

    /**
     * Returns the base of the underlying numeral system for this expression.
     *
     * @return a base
     */
    int base();

    /**
     * Returns the logarithm base.
     *
     * @return the logarithm base (i.e. a number)
     */
    Number logarithmBase();

    /**
     * Returns the number of this logarithm expression.
     *
     * @return the number of this logarithm expression
     */
    Number numerus();

    /**
     * Adds this logarithm and the specified logarithm. If the logarithm bases are equal then the numbers of the
     * logarithm expressions are multiplied.
     *
     * @param logarithm
     *        a logarithm expression
     *
     * @return a logarithm expression
     */
    Logarithm add(Logarithm logarithm);

    /**
     * Subtracts the specified logarithm expression from this logarithm expression. If the logarithm bases are equal
     * then the number of this logarithm expression is divided by the number of the specified logarithm expression.
     *
     * @param logarithm
     *        a logarithm expression
     *
     * @return a logarithm expression
     */
    Logarithm subtract(Logarithm logarithm);

}
