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


import jmul.math.digits.Digit;


/**
 * This interface describes a special node type for a linked list of digits.
 *
 * @author Kristian Kutin
 */
public interface DigitNode {

    /**
     * Returns the digit stored in this node.
     *
     * @return a digit
     */
    Digit digit();

    /**
     * Returns the node to the left of this node or <code>null</code> if there is no node.
     *
     * @return a node or <code>null</code> if there is no node to the left
     */
    DigitNode leftNode();

    /**
     * Returns the node to the right of this node or <code>null</code> if there is no node.
     *
     * @return a node or <code>null</code> if there is no node to the right
     */
    DigitNode rightNode();

    /**
     * Returns a string representation for this node.
     *
     * @return a string representation
     */
    String toString();

}
