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

package test.jmul.math.numbers;


import java.util.ArrayList;
import java.util.Collection;

import jmul.math.numbers.RealDecimalNumber;
import jmul.math.numbers.builders.AdditionOperation;
import jmul.math.numbers.operations.AdditionOperationImpl;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests the addition of two real numbers.
 *
 * @author Kristian Kutin
 */
@RunWith(Parameterized.class)
public class RealNumberAdditionWithValidParametersTest {

    /**
     * A function.
     */
    private AdditionOperation<RealDecimalNumber> additionFunction;

    /**
     * A number.
     */
    private final RealDecimalNumber n;

    /**
     * A number.
     */
    private final RealDecimalNumber m;

    /**
     * The expected result.
     */
    private final RealDecimalNumber expectedResult;

    public RealNumberAdditionWithValidParametersTest(RealDecimalNumber n, RealDecimalNumber m, RealDecimalNumber expectedResult) {

        super();

        this.n = n;
        this.m = m;
        this.expectedResult = expectedResult;
    }

    @Before
    public void setUp() {

        additionFunction = new AdditionOperationImpl<>();
    }

    @After
    public void tearDown() {

        additionFunction = null;
    }

    @Test
    public void testAddition() {

        String message = String.format("Couldn't add the two numbers (n=%s; m=%s)!", n.toString(), m.toString());

        RealDecimalNumber actualResult = additionFunction.add(n, m);

        assertEquals(message, expectedResult, actualResult);
    }

    /**
     * Returns a matrix of test data and expected results.
     *
     * @return a matrix of test data and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { new RealDecimalNumber(), new RealDecimalNumber(0), new RealDecimalNumber(0) });

        parameters.add(new Object[] { RealDecimalNumber.POSITIVE_INFINITY, new RealDecimalNumber(10),
                                      RealDecimalNumber.POSITIVE_INFINITY });
        parameters.add(new Object[] { RealDecimalNumber.NEGATIVE_INFINITY, new RealDecimalNumber(10),
                                      RealDecimalNumber.NEGATIVE_INFINITY });

        parameters.add(new Object[] { new RealDecimalNumber(1), new RealDecimalNumber(10), new RealDecimalNumber(11) });
        parameters.add(new Object[] { new RealDecimalNumber(10), new RealDecimalNumber(1), new RealDecimalNumber(11) });

        parameters.add(new Object[] { new RealDecimalNumber(999), new RealDecimalNumber(1), new RealDecimalNumber(1000) });
        parameters.add(new Object[] { new RealDecimalNumber(123456789), new RealDecimalNumber(987654321), new RealDecimalNumber(1111111110) });
        parameters.add(new Object[] { new RealDecimalNumber(123456789), new RealDecimalNumber(876543211), new RealDecimalNumber(1000000000) });

        return parameters;
    }

}
