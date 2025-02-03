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

package jmul.math.numbers.notations;


import jmul.math.signs.Sign;
import jmul.math.numbers.nodes.DigitNode;


/**
 * This class contains a parsing result.
 *
 * @author Kristian Kutin
 */
public class ParsingResult {

    /**
     * The sign of the number.
     */
    public final Sign sign;

    /**
     * The base of the number.
     */
    public final int base;

    /**
     * A reference to the center node of the linked list which represents the number.
     */
    public final DigitNode centerNode;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param sign
     *        a sign
     * @param base
     *        a base
     * @param centerNode
     *        a reference to the center node
     */
    public ParsingResult(Sign sign, int base, DigitNode centerNode) {

        super();

        this.sign = sign;
        this.base = base;
        this.centerNode = centerNode;
    }

}
