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
 * This interface describes a set of dice.
 *
 * @author Kristian Kutin
 */
public interface Dice extends Iterable<Die> {

    /**
     * Returns the number of dice.
     *
     * @return the number of dice
     */
    Number dice();

    /**
     * Rolls all dice and adds their individual results.
     *
     * @return the sum of all dice rolls (including modifiers)
     */
    Number roll();

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
