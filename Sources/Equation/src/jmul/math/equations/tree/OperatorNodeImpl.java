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


import jmul.math.equations.operators.Operator;


/**
 * An implementation of an oeprator node.
 *
 * @author Kristian Kutin
 */
class OperatorNodeImpl extends NodeBaseImpl implements OperatorNode {

    /**
     * A reference to an operator.
     */
    private final Operator operator;

    /**
     * Creates a new node according to the specified parameter.
     *
     * @param operator
     *        a reference to an operator
     */
    public OperatorNodeImpl(Operator operator) {

        super();

        if (operator == null) {

            throw new IllegalArgumentException("No operator (null) was specified!");
        }

        this.operator = operator;
    }

    /**
     * The symbol for this operand.
     *
     * @return a symbol
     */
    @Override
    public String symbol() {

        return operator.symbol();
    }

    /**
     * The precedence of this operator. The higher the number the higher the precedence.
     *
     * @return an integer value
     */
    @Override
    public int precedence() {

        return operator.precedence();
    }

    /**
     * Returns the operator.
     *
     * @return an operator
     */
    @Override
    public Operator operator() {

        return operator;
    }

    /**
     * Returns a string representation for this node.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return operator.toString();
    }

}
