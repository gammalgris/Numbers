/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2024  Kristian Kutin
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

package test.jmul.math.numbers;


import java.util.ArrayList;
import java.util.Collection;

import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createInfinity;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.numbers.nodes.DigitNode;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests the consistency of the linked list which represents a number.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class CheckLinkedListTest {

    /**
     * A number.
     */
    private final Number number;

    /**
     * Creates a test case according to the specified parameter.
     *
     * @param number
     *        a number
     */
    public CheckLinkedListTest(Number number) {

        super();

        this.number = number;
    }

    /**
     * Tests the consistency of the number (i.e. underlying linked list). The test
     * traverses the linked list and checks that the nodes are correctly connected.<br>
     * <br>
     * <i>Note:<br>
     * This test can only check that the nodes are consistently connected in both
     * directions provided these can be reached from the center node.
     * </i>
     */
    @Test
    public void testConsistency() {

        if (number.isInfinity()) {

            checkInfinity(number);

        } else {

            checkNumber(number);
        }
    }

    /**
     * Checks the specified number.
     *
     * @param number
     *        a number which represents infinity
     */
    private static void checkInfinity(Number number) {

        assertEquals(null, number.centerNode());
    }

    /**
     * Checks the specified number.
     *
     * @param number
     *        a number which doesn't represent infinity
     */
    private static void checkNumber(Number number) {

        DigitNode centerNode = number.centerNode();

        assertNotNull(centerNode);
        assertNotNull(centerNode.digit());

        DigitNode node;

        // traverse linked list to the left

        node = centerNode;

        while (node != null) {

            DigitNode leftNode = node.leftNode();

            if (leftNode != null) {

                String message =
                    String.format("inconsistent linking on the left side: %s<->%s", leftNode.digit(), node.digit());
                assertEquals(message, node, leftNode.rightNode());
                assertEquals(message, leftNode, node.leftNode());
            }

            node = node.leftNode();
        }

        // traverse linked list to the right

        node = centerNode;

        while (node != null) {

            DigitNode rightNode = node.rightNode();

            if (rightNode != null) {

                String message =
                    String.format("inconsistent linking on the right side: %s<->%s", node.digit(), rightNode.digit());
                assertEquals(message, node, rightNode.leftNode());
                assertEquals(message, rightNode, node.rightNode());
            }

            node = node.rightNode();
        }
    }

    /**
     * Returns a matrix of test data and expected results.
     *
     * @return a matrix of test data and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { createInfinity() });
        parameters.add(new Object[] { createNumber("0") });
        parameters.add(new Object[] { createNumber("1") });
        parameters.add(new Object[] { createNumber("123456789.87654321") });
        parameters.add(new Object[] { createNumber("123456789.87654321").negate() });
        parameters.add(new Object[] { createNumber("-123456789.87654321").negate() });
        parameters.add(new Object[] { createNumber("0.5").absoluteValue() });
        parameters.add(new Object[] { createNumber("123456789.87654321").absoluteValue() });

        return parameters;
    }

}
