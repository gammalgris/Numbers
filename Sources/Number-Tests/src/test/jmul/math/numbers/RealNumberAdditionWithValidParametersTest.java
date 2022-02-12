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

import jmul.math.numbers.RealNumber;
import jmul.math.numbers.operations.Addition;
import jmul.math.numbers.operations.AdditionImpl;

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
    private Addition<RealNumber> additionFunction;

    /**
     * A number.
     */
    private final RealNumber n;

    /**
     * A number.
     */
    private final RealNumber m;

    /**
     * The expected result.
     */
    private final RealNumber expectedResult;

    public RealNumberAdditionWithValidParametersTest(RealNumber n, RealNumber m, RealNumber expectedResult) {

        super();

        this.n = n;
        this.m = m;
        this.expectedResult = expectedResult;
    }

    @Before
    public void setUp() {

        additionFunction = new AdditionImpl<>();
    }

    @After
    public void tearDown() {

        additionFunction = null;
    }

    @Test
    public void testAddition() {

        String message = String.format("Couldn't add the two numbers (n=%s; m=%s)!", n.toString(), m.toString());

        RealNumber actualResult = additionFunction.add(n, m);

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

        parameters.add(new Object[] { new RealNumber(), new RealNumber(0), new RealNumber(0) });

        parameters.add(new Object[] { RealNumber.POSITIVE_INFINITY, new RealNumber(10), RealNumber.POSITIVE_INFINITY });
        parameters.add(new Object[] { RealNumber.NEGATIVE_INFINITY, new RealNumber(10), RealNumber.NEGATIVE_INFINITY });

        parameters.add(new Object[] { new RealNumber(1), new RealNumber(10), new RealNumber(11) });
        parameters.add(new Object[] { new RealNumber(10), new RealNumber(1), new RealNumber(11) });

        parameters.add(new Object[] { new RealNumber(999), new RealNumber(1), new RealNumber(1000) });
        parameters.add(new Object[] { new RealNumber(123456789), new RealNumber(987654321), new RealNumber(1111111110) });
        parameters.add(new Object[] { new RealNumber(123456789), new RealNumber(876543211), new RealNumber(1000000000) });

        return parameters;
    }

}
