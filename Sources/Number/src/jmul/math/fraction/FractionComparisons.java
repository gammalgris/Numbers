/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2024  Kristian Kutin
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

package jmul.math.fraction;


/**
 * This interface describes various expression comparisons.
 *
 * @author Kristian Kutin
 */
public interface FractionComparisons {

    /**
     * Checks if this expression is greater than the specified expression.
     *
     * @param expression
     *        an expression
     *
     * @return <code>true</code> if this expression is greater than the specified expression, else <code>false</code>
     */
    boolean isGreater(Fraction expression);

    /**
     * Checks if this expression is greater than or equal to the specified expression.
     *
     * @param expression
     *        an expression
     *
     * @return <code>true</code> if this expression is greater than or equal to the specified expression, else <code>false</code>
     */
    boolean isGreaterOrEqual(Fraction expression);

    /**
     * Checks if this expression is lesser than the specified expression.
     *
     * @param expression
     *        an expression
     *
     * @return <code>true</code> if this expression is lesser than the specified expression, else <code>false</code>
     */
    boolean isLesser(Fraction expression);

    /**
     * Checks if this expression is lesser than or equal to the specified expression.
     *
     * @param expression
     *        an expression
     *
     * @return <code>true</code> if this expression is lesser than or equal to the specified expression, else <code>false</code>
     */
    boolean isLesserOrEqual(Fraction expression);

}
