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

package test.jmul.math.singletons;


import jmul.math.numbers.FunctionIdentifiers;

import jmul.singletons.Function;
import jmul.singletons.FunctionDoesntExistException;
import jmul.singletons.FunctionExistsException;
import jmul.singletons.FunctionRepository;
import jmul.singletons.FunctionRepositoryImpl;

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;


/**
 * This test suite tests a function repository.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class FunctionRepositoryTest {

    /**
     * A function repository.
     */
    private FunctionRepository repository;

    /**
     * Preparatory steps before a test.
     */
    @Before
    public void setuUp() {

        repository = new FunctionRepositoryImpl();
    }

    /**
     * Clean up after a test.
     */
    @After
    public void tearDown() {

        repository = null;
    }

    /**
     * Tests retrieving a function from an empty repository.
     */
    @Test(expected = FunctionDoesntExistException.class)
    public void testQueryEmptyRepository() {

        repository.getFunction(FunctionIdentifiers.ADDITION_FUNCTION);
    }

    /**
     * Tests adding a function. The test might seem superfluous but is needed to check if the
     * register method doesn't throw an exception.
     */
    @Test
    public void testAddFunction() {

        repository.registerFunction(FunctionIdentifiers.ADDITION_FUNCTION, DummyFunction.class);
    }

    /**
     * Tests adding a function and retrieving the function.
     */
    @Test
    public void testAddAndRetrieveFunction() {

        repository.registerFunction(FunctionIdentifiers.ADDITION_FUNCTION, DummyFunction.class);

        Function function = repository.getFunction(FunctionIdentifiers.ADDITION_FUNCTION);
        assertNotNull(function);
    }

    /**
     * Tests overriding a function.
     */
    @Test(expected = FunctionExistsException.class)
    public void testOverrideFunction() {

        repository.registerFunction(FunctionIdentifiers.ADDITION_FUNCTION, DummyFunction.class);
        repository.registerFunction(FunctionIdentifiers.ADDITION_FUNCTION, DummyFunction.class);
    }

    /**
     * Tests removing a function from a an empty repository.
     */
    @Test(expected = FunctionDoesntExistException.class)
    public void testRemoveFunctionFromEmptyRepository() {

        repository.removeFunction(FunctionIdentifiers.ADDITION_FUNCTION);
    }

    /**
     * Tests removing a function. The test might seem superfluous but is needed to check if the
     * register and remove methods don't throw an exception.
     */
    @Test
    public void testRemoveFunction() {

        repository.registerFunction(FunctionIdentifiers.ADDITION_FUNCTION, DummyFunction.class);
        repository.removeFunction(FunctionIdentifiers.ADDITION_FUNCTION);
    }

    /**
     * Tests removing a function and retrieving the function.
     */
    @Test(expected = FunctionDoesntExistException.class)
    public void testRemoveAndRetrieveFunction() {

        repository.registerFunction(FunctionIdentifiers.ADDITION_FUNCTION, DummyFunction.class);
        repository.removeFunction(FunctionIdentifiers.ADDITION_FUNCTION);

        repository.getFunction(FunctionIdentifiers.ADDITION_FUNCTION);
    }

}
