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

package jmul.math.numbers.nodes;


/**
 * This interface describes a linked list and grants access to the data structure.
 *
 * @author Kristian Kutin
 */
public interface LinkedDigitList {

    /**
     * Returns the node which represents the zeroth position (i.e. <code>base^0</code>) within a number.
     * To the left are the digits which represent the positions 1 (<code>base^1</code>), 2 (<code>base^2</code>),
     * 3 (<code>base^3</code>), etc..
     * To the right are the digits which represent the fraction and positions -1 (<code>base^-1</code>),
     * -2 (<code>base^-2</code>), 3 (<code>base^-3</code>), etc..
     *
     * @return the center node
     */
    DigitNode centerNode();

}
