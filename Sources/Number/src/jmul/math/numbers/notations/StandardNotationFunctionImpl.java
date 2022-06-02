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


import jmul.math.numbers.Constants;
import jmul.math.numbers.Number;
import jmul.math.numbers.nodes.DigitNode;


/**
 * An implementation of a function that creates a standard notation representation for the specified number.
 *
 * @author Kristian Kutin
 */
public class StandardNotationFunctionImpl implements NotationFunction {

    /**
     * The default constructor.
     */
    public StandardNotationFunctionImpl() {

        super();
    }

    /**
     * Translates the specified number into a string notation.<br>
     * <br>
     * <i>Note:<br>
     * If the number contains too many digits (i.e. more than might fit into an character array) then this method
     * is going to run into an error.</I>
     *
     * @param number
     *        a number
     *
     * @return a string representation
     */
    @Override
    public String toString(Number number) {

        StringBuilder buffer = new StringBuilder();

        if (number.isInfinity()) {

            buffer.append(Constants.INFINITY_REPRESENTATION);

        } else {

            DigitNode node = number.centerNode();

            while (node != null) {

                buffer.insert(0, node.digit());
                node = node.leftNode();
            }

            node = number.centerNode().rightNode();

            if (node != null) {

                buffer.append(Constants.DECIMAL_SEPARATOR);

                while (node != null) {

                    buffer.append(node.digit());
                    node = node.rightNode();
                }
            }
        }

        if (number.isNegative()) {

            buffer.insert(0, number.sign());
        }

        return String.valueOf(buffer);
    }

}
