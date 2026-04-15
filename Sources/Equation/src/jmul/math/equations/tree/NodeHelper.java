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
import jmul.math.equations.components.Variable;
import jmul.math.equations.operators.Operator;


/**
 * A utility class for instamtiating nodes and manipulating expression trees.
 *
 * @author Kristian Kutin
 */
public final class NodeHelper {

    /**
     * The default constructor.
     */
    private NodeHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Creates a new node according to the specified parameter.
     *
     * @param operator
     *        an operator
     *
     * @return a new node
     */
    public static OperatorNode createOperatorNode(Operator operator) {

        return new OperatorNodeImpl(operator);
    }

    /**
     * Creates a new node according to the specified parameter.
     *
     * @param constant
     *        a constant
     *
     * @return a new node
     */
    public static OperandNode createOperandNode(Constant constant) {

        return new ConstantNodeImpl(constant);
    }

    /**
     * Creates a new node according to the specified parameter.
     *
     * @param variable
     *         a variable
     *
     * @return a new node
     */
    public static OperandNode createOperandNodde(Variable variable) {

        return new VariableNodeImpl(variable);
    }

    /**
     * Creates a new expression tree.
     *
     * @return a new expression tree
     */
    public static ExpressionTree createExpressionTree() {

        return new ExpressionTreeImpl();
    }

}
