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

package test.jmul.math.equations.components;


import jmul.math.equations.components.ComponentHelper;
import jmul.math.equations.components.Constant;
import jmul.math.equations.components.Variable;
import jmul.math.numbers.Number;
import jmul.math.numbers.NumberHelper;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This test suite test creating components.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class CreateComponentsTest {

    /**
     * Tests creating a variable.
     */
    @Test
    public void testCreateVariable() {

        String variableName = "x";

        Variable variable = ComponentHelper.createVariable(variableName);

        assertEquals("#name", variableName, variable.name());
        assertEquals("#toString", variableName, variable.toString());
    }

    /**
     * Tests creating a variable with invalid parameters.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateVariableWithNullName() {

        String variableName = null;

        ComponentHelper.createVariable(variableName);
    }

    /**
     * Tests creating a variable with invalid parameters.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateVariableWithEmptyName() {

        String variableName = " ";

        ComponentHelper.createVariable(variableName);
    }

    /**
     * Tests creating a constant.
     */
    @Test
    public void testCreateConstant() {

        String numberString = "10";
        Number number = NumberHelper.createNumber(numberString);

        Constant constant = ComponentHelper.createConstant(number);

        assertEquals("#value", number, constant.value());
        assertEquals("#toString", numberString, constant.toString());
    }

    /**
     * Tests creating a constant with invalid parameters.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateConstantWithNullValue() {

        Number number = null;

        ComponentHelper.createConstant(number);
    }

}
