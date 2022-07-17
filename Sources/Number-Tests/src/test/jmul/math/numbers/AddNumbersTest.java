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

import jmul.math.numbers.Number;
import jmul.math.numbers.NumberImpl;

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests adding two numbers with various combinations of operands.
 * 
 * TODO do the calculations also for numbers with other bases
 * TODO comment test suite
 * 
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class AddNumbersTest {

    private static final int BASE;

    /*
     * The static initializer.
     */
    static {

        BASE = 10;
    }

    private final String firstSummandString;

    private Number firstSummand;

    private final String secondSummandString;

    private Number secondSummand;

    private final String sumString;

    private Number sum;

    public AddNumbersTest(String firstSummandString, String secondSummandString, String sumString) {

        super();

        this.firstSummandString = firstSummandString;

        this.secondSummandString = secondSummandString;

        this.sumString = sumString;
    }

    @Before
    public void setUp() {

        firstSummand = new NumberImpl(BASE, firstSummandString);
        secondSummand = new NumberImpl(BASE, secondSummandString);
        sum = new NumberImpl(BASE, sumString);
    }

    @After
    public void tearDown() {

        firstSummand = null;
        secondSummand = null;
        sum = null;
    }

    @Test
    public void testAddition() {

        assertEquals(firstSummandString, firstSummand.toString());
        assertEquals(secondSummandString, secondSummand.toString());

        Number actualResult = firstSummand.add(secondSummand);

        String message = String.format("%s + %s = %s", firstSummandString, secondSummandString, sumString);
        assertEquals(message, sum, actualResult);
    }

    /**
     * Returns a matrix of test data and expected results.
     *
     * @return a matrix of test data and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { "0", "0", "0" });

        parameters.add(new Object[] { "1", "0", "1" });
        parameters.add(new Object[] { "0", "1", "1" });

        parameters.add(new Object[] { "-1", "0", "-1" });
        parameters.add(new Object[] { "0", "-1", "-1" });

        parameters.add(new Object[] { "100", "0", "100" });
        parameters.add(new Object[] { "0", "100", "100" });

        parameters.add(new Object[] { "-100", "0", "-100" });
        parameters.add(new Object[] { "0", "-100", "-100" });


        parameters.add(new Object[] { "1.2345", "0", "1.2345" });
        parameters.add(new Object[] { "0", "1.2345", "1.2345" });

        parameters.add(new Object[] { "-1.2345", "0", "-1.2345" });
        parameters.add(new Object[] { "0", "-1.2345", "-1.2345" });

        parameters.add(new Object[] { "100.2345", "0", "100.2345" });
        parameters.add(new Object[] { "0", "100.2345", "100.2345" });

        parameters.add(new Object[] { "-100.2345", "0", "-100.2345" });
        parameters.add(new Object[] { "0", "-100.2345", "-100.2345" });


        parameters.add(new Object[] { "1", "0.5", "1.5" });
        parameters.add(new Object[] { "0.5", "1", "1.5" });

        parameters.add(new Object[] { "-1", "0.5", "-0.5" });
        parameters.add(new Object[] { "0.5", "-1", "-0.5" });

        parameters.add(new Object[] { "100", "0.555", "100.555" });
        parameters.add(new Object[] { "0.555", "100", "100.555" });

        parameters.add(new Object[] { "-100", "0.555", "-99.555" });
        parameters.add(new Object[] { "0.555", "-100", "-99.555" });


        parameters.add(new Object[] { "1.2345", "10.98765", "12.22215" });
        parameters.add(new Object[] { "10.98765", "1.2345", "12.22215" });

        parameters.add(new Object[] { "-1.2345", "10.98765", "9,75315" });
        parameters.add(new Object[] { "10.98765", "-1.2345", "9,75315" });

        parameters.add(new Object[] { "123.45", "1.098765", "124.548765" });
        parameters.add(new Object[] { "1.098765", "123.45", "124.548765" });

        parameters.add(new Object[] { "-123.45", "1.098765", "-122.351235" });
        parameters.add(new Object[] { "1.098765", "-123.45", "-122.351235" });


        parameters.add(new Object[] { "0.5", "0.5", "1" });
        parameters.add(new Object[] { "0.12345", "0.98765", "1.1111" });

        parameters.add(new Object[] { "1", "1", "2" });
        parameters.add(new Object[] { "2", "2", "4" });
        parameters.add(new Object[] { "10", "10", "20" });
        parameters.add(new Object[] { "123", "123", "246" });

        parameters.add(new Object[] { "123456789", "987654321", "1111111110" });
        parameters.add(new Object[] { "-123456789", "987654321", "864197532" });
        parameters.add(new Object[] { "123456789", "-987654321", "-864197532" });
        parameters.add(new Object[] { "-123456789", "-987654321", "-1111111110" });

        parameters.add(new Object[] { "11", "1", "12" });
        parameters.add(new Object[] { "1", "11", "12" });
        parameters.add(new Object[] { "-11", "1", "-10" });
        parameters.add(new Object[] { "1", "-11", "-10" });
        parameters.add(new Object[] { "11", "-1", "10" });
        parameters.add(new Object[] { "-1", "11", "10" });
        parameters.add(new Object[] { "-11", "-1", "-12" });
        parameters.add(new Object[] { "-1", "-11", "-12" });

        return parameters;
    }

}
