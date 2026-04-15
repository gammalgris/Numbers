/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2026  Kristian Kutin
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

package jmul.math.equations.tree;


import jmul.math.equations.components.Operand;
import jmul.math.numbers.Number;


/**
 * An node in an expression tree.
 *
 * @author Kristian Kutin
 */
public interface OperandNode extends ExpressionNode {

    /**
     * Checks if this node contains a constant.
     *
     * @return <code>true</code> if this node contains a constant, else <code>false</code>
     */
    boolean containsConstant();

    /**
     * Checks if this node contains a variable.
     *
     * @return <code>true</code> if this node contains a variable, else <code>false</code>
     */
    boolean containsVariable();

    /**
     * If this node contains a constant then returns the value, else throws an exception.
     *
     * @return a constant value
     */
    Number value();

    /**
     * If this node contains a variable then returns the variable name, else throws an exception.
     *
     * @return a variable name
     */
    String name();

    /**
     * Returns the operand.
     *
     * @return an operand
     */
    Operand operand();

}
