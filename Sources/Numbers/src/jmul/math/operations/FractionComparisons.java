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

package jmul.math.operations;


import jmul.math.fractions.Fraction;


/**
 * This interface describes various fraction comparisons.
 *
 * @author Kristian Kutin
 */
public interface FractionComparisons {

    /**
     * Checks if this object (i.e. operand) is greater than the specified fraction.
     *
     * @param fraction
     *        a fraction
     *
     * @return <code>true</code> if this object (i.e. operand) is greater than the specified fraction,
     *         else <code>false</code>
     */
    boolean isGreater(Fraction fraction);

    /**
     * Checks if this object (i.e. operand) is greater than or equal to the specified expression.
     *
     * @param fraction
     *        a fraction
     *
     * @return <code>true</code> if this object (i.e. operand) is greater than or equal to the specified fraction,
     *         else <code>false</code>
     */
    boolean isGreaterOrEqual(Fraction fraction);

    /**
     * Checks if this object (i.e. operand) is lesser than the specified fraction.
     *
     * @param fraction
     *        a fraction
     *
     * @return <code>true</code> if this object (i.e. operand) is lesser than the specified fraction,
     *         else <code>false</code>
     */
    boolean isLesser(Fraction fraction);

    /**
     * Checks if this object (i.e. operand) is lesser than or equal to the specified fraction.
     *
     * @param fraction
     *        a fraction
     *
     * @return <code>true</code> if this object (i.e. operand) is lesser than or equal to the specified fraction,
     *         else <code>false</code>
     */
    boolean isLesserOrEqual(Fraction fraction);

}
