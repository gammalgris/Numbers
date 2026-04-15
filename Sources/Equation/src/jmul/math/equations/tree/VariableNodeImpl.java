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
import jmul.math.equations.components.Variable;
import jmul.math.numbers.Number;


/**
 * An implementation of an operand node.
 *
 * @author Kristian Kutin
 */
class VariableNodeImpl extends NodeBaseImpl implements OperandNode {

    /**
     * A reference to a variable.
     */
    private final Variable variable;

    /**
     * Creates a new node acccording to the specified parameter.
     *
     * @param variable
     *        a reference to a variable
     */
    public VariableNodeImpl(Variable variable) {

        super();

        if (variable == null) {

            throw new IllegalArgumentException("No variable (null) was specified!");
        }

        this.variable = variable;
    }

    /**
     * Checks if this node contains a constant.
     *
     * @return <code>true</code> if this node contains a constant, else <code>false</code>
     */
    @Override
    public boolean containsConstant() {

        return false;
    }

    /**
     * Checks if this node contains a variable.
     *
     * @return <code>true</code> if this node contains a variable, else <code>false</code>
     */
    @Override
    public boolean containsVariable() {

        return true;
    }

    /**
     * If this node contains a constant then returns the value, else throws an exception.
     *
     * @return a constant value
     */
    @Override
    public Number value() {

        throw new UnsupportedOperationException();
    }

    /**
     * If this node contains a variable then returns the variable name, else throws an exception.
     *
     * @return a variable name
     */
    @Override
    public String name() {

        return variable.name();
    }

    /**
     * Returns the operand.
     *
     * @return an operand
     */
    @Override
    public Operand operand() {

        return variable;
    }

    /**
     * Returns a string representation for this node.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return variable.toString();
    }

}
