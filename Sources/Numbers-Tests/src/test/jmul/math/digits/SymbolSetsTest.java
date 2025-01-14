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

package test.jmul.math.digits;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jmul.math.digits.SymbolSets;
import jmul.math.numbers.Constants;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite checks all available symbols sets.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class SymbolSetsTest {

    /**
     * The base for a positional numeral system.
     */
    private final int base;

    /**
     * The excpected exception.
     */
    private final Class expectedException;

    /**
     * A set containing all digit symbols.
     */
    private final SymbolSets symbolSets;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param base
     *        the base for a positional numeral system.
     * @param expectedException
     *        the expected exception if the base is not supported
     */
    public SymbolSetsTest(int base, Class expectedException) {

        super();

        this.base = base;
        this.expectedException = expectedException;

        this.symbolSets = new SymbolSets();
    }

    @Test
    public void checkSubset() {

        char[] characters = null;
        try {

            characters = symbolSets.subset(base);

        } catch (Exception e) {

            if (expectedException == null) {

                throw e;
            }

            Class actualException = e.getClass();
            if (actualException == expectedException) {

                return;

            } else {

                String message =
                    String.format("%s is expected but %s was thrown!", expectedException.getName(),
                                  actualException.getName());
                fail(message);
            }
        }

        List<Character> uniqueSymbols = new ArrayList<>();
        List<Character> duplicateSymbols = new ArrayList<>();

        for (char character : characters) {

            if (uniqueSymbols.contains(character)) {

                duplicateSymbols.add(character);

            } else {

                uniqueSymbols.add(character);
            }
        }

        {
            String message = String.format("The base %d has no unique symbols (%s)!", base, uniqueSymbols.toString());
            assertFalse(message, uniqueSymbols.isEmpty());
        }

        {
            String message =
                String.format("The base %d has duplicate symbols (%s)!", base, duplicateSymbols.toString());
            assertTrue(message, duplicateSymbols.isEmpty());
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

        for (int base = -1; base < 100; base++) {

            if (base < Constants.BASE_MIN_LIMIT) {

                parameters.add(new Object[] { base, IllegalArgumentException.class });

            } else if (base >= Constants.BASE_MAX_LIMIT) {

                parameters.add(new Object[] { base, IllegalArgumentException.class });

            } else {

                parameters.add(new Object[] { base, null });
            }
        }

        return parameters;
    }

}
