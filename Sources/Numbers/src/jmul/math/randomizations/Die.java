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

package jmul.math.randomizations;


import jmul.math.numbers.Number;


/**
 * This interface describes a die with 2 or more sides (i.e. a die with two sides would rather represent
 * a coin). The die result can be modified by a modifier.
 *
 * @author Kristian Kutin
 */
public interface Die {

    /**
     * The number of sides of this die.
     *
     * @return the number of sides of this die
     */
    Number sides();

    /**
     * Rolls this die.
     *
     * @return the rolling result
     */
    Number roll();

    /**
     * Returns the modifier which is added to the rolling result.
     *
     * @return a modifier
     */
    Number modifier();

    /**
     * Returns the expected minimum value.
     *
     * @return the expected minimum value
     */
    Number expectedMinimumValue();

    /**
     * Returns the expected maximum value.
     *
     * @return the expected maximum value
     */
    Number expectedMaximumValue();

    /**
     * Returns the expected average value.
     *
     * @return the expected average value
     */
    Number expectedAverageValue();

}
