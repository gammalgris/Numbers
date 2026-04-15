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

package test.jmul.math.equations.tree;


import jmul.math.equations.components.ComponentHelper;
import jmul.math.equations.components.Constant;
import jmul.math.equations.components.Variable;
import jmul.math.equations.operators.Operator;
import jmul.math.equations.operators.Operators;
import jmul.math.equations.tree.NodeHelper;
import jmul.math.equations.tree.OperandNode;
import jmul.math.equations.tree.OperatorNode;
import jmul.math.numbers.Number;
import jmul.math.numbers.NumberHelper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;


/**
 * This test suite tests creating nodes.
 *
 * @author Kristian Kutin
 */
public class CreateNodesTest {

    /**
     * Tests creating a constant node.
     */
    @Test
    public void testCreateConstantNode() {

        String numberString = "10";
        Number number = NumberHelper.createNumber(numberString);
        Constant constant = ComponentHelper.createConstant(number);
        OperandNode operandNode = NodeHelper.createOperandNode(constant);

        assertEquals("#status", true, operandNode.containsConstant());
        assertEquals("#status", false, operandNode.containsVariable());
        assertEquals("#value", number, operandNode.value());

        try {

            operandNode.name();
            fail("#name");

        } catch (Exception e) {

            assertTrue("#name", e instanceof UnsupportedOperationException);
        }

        assertNull("#reference", operandNode.parent());
        assertNull("#reference", operandNode.leftChild());
        assertNull("#reference", operandNode.rightChild());

        assertEquals("#toString", numberString, operandNode.toString());
    }

    /**
     * Tests creating a constant node.
     */
    @Test
    public void testCreateVariableNode() {

        String name = "x";
        Variable variable = ComponentHelper.createVariable(name);
        OperandNode operandNode = NodeHelper.createOperandNodde(variable);

        assertEquals("#status", false, operandNode.containsConstant());
        assertEquals("#status", true, operandNode.containsVariable());
        assertEquals("#name", name, operandNode.name());
        assertEquals("#toString", name, operandNode.toString());

        try {

            operandNode.value();
            fail("#value");

        } catch (Exception e) {

            assertTrue("#value", e instanceof UnsupportedOperationException);
        }

        assertNull("#reference", operandNode.parent());
        assertNull("#reference", operandNode.leftChild());
        assertNull("#reference", operandNode.rightChild());
    }

    /**
     * Test creating an operator node.
     */
    @Test
    public void testCreateOperandNode() {

        Operator operator = Operators.ADDITION;
        OperatorNode operatorNode = NodeHelper.createOperatorNode(operator);

        assertEquals("#symbol", operator.symbol(), operatorNode.symbol());
        assertEquals("#precedence", operator.precedence(), operatorNode.precedence());
        assertEquals("#toString", operator.toString(), operatorNode.toString());

        assertNull("#reference", operatorNode.parent());
        assertNull("#reference", operatorNode.leftChild());
        assertNull("#reference", operatorNode.rightChild());
    }

}
