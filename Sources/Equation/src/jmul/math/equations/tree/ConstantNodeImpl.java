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


import jmul.math.equations.components.Constant;
import jmul.math.equations.components.Operand;
import jmul.math.numbers.Number;


/**
 * An implementation of an operand node.
 *
 * @author Kristian Kutin
 */
class ConstantNodeImpl extends NodeBaseImpl implements OperandNode {

    /**
     * A reference to a constant.
     */
    private final Constant constant;

    /**
     * Creates a new node acccording to the specified parameter.
     *
     * @param constant
     *        a reference to a constant
     */
    public ConstantNodeImpl(Constant constant) {

        super();

        if (constant == null) {

            throw new IllegalArgumentException("No constant (null) was specified!");
        }

        this.constant = constant;
    }

    /**
     * Checks if this node contains a constant.
     *
     * @return <code>true</code> if this node contains a constant, else <code>false</code>
     */
    @Override
    public boolean containsConstant() {

        return true;
    }

    /**
     * Checks if this node contains a variable.
     *
     * @return <code>true</code> if this node contains a variable, else <code>false</code>
     */
    @Override
    public boolean containsVariable() {

        return false;
    }

    /**
     * If this node contains a constant then returns the value, else throws an exception.
     *
     * @return a constant value
     */
    @Override
    public Number value() {

        return constant.value();
    }

    /**
     * If this node contains a variable then returns the variable name, else throws an exception.
     *
     * @return a variable name
     */
    @Override
    public String name() {

        throw new UnsupportedOperationException();
    }

    /**
     * Returns the operand.
     *
     * @return an operand
     */
    @Override
    public Operand operand() {

        return constant;
    }

    /**
     * Returns a string representation for this node.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return constant.toString();
    }

}
