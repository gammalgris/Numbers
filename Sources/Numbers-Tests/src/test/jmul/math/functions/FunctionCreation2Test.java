/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2025  Kristian Kutin
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

package test.jmul.math.functions;


import jmul.math.data.DataEntry;
import jmul.math.data.TrainingData;
import jmul.math.functions.Function;
import jmul.math.functions.FunctionHelper;
import jmul.math.functions.conditions.ConditionFunctionEntry;
import jmul.math.functions.conditions.GreaterOrEqualCondition;
import jmul.math.functions.conditions.LesserThanCondition;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This test suite tests various functions.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class FunctionCreation2Test {

    /**
     * A number base.
     */
    private static final int DEFAULT_NUMBER_BASE;

    /*
     * The static initializer.
     */
    static {

        DEFAULT_NUMBER_BASE = 16;
    }

    /**
     * Tests creating a monomial function with valid parameters.
     */
    @Test
    public void testCreateMonomialFunction() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "8")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "3"),
                                           createNumber(DEFAULT_NUMBER_BASE, "12")));

        Function f = FunctionHelper.createMonomialFunction(DEFAULT_NUMBER_BASE, "2", "2");

        assertEquals("formula", "2 * x^2", f.toString());
        assertEquals("formula", "f(x) = 2 * x^2", f.toFunctionNotation());

        for (DataEntry entry : data) {

            Number actualOutput = f.calculate(entry.input);
            assertEquals("function values", entry.expectedOutput, actualOutput);
        }
    }

    /**
     * Tests creating a monomial function with valid parameters.
     */
    @Test
    public void testCreateMonomialFunction2() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "8")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "3"),
                                           createNumber(DEFAULT_NUMBER_BASE, "12")));

        Number coefficient = createNumber(DEFAULT_NUMBER_BASE, "2");
        Number exponent = createNumber(DEFAULT_NUMBER_BASE, "2");

        Function f = FunctionHelper.createMonomialFunction(coefficient, exponent);

        assertEquals("formula", "2 * x^2", f.toString());
        assertEquals("formula", "f(x) = 2 * x^2", f.toFunctionNotation());

        for (DataEntry entry : data) {

            Number actualOutput = f.calculate(entry.input);
            assertEquals("function values", entry.expectedOutput, actualOutput);
        }
    }

    /**
     * Tests creating a monomial function with valid parameters.
     */
    @Test
    public void testCreateMonomialFunction3() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "3"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")));

        Number coefficient = createNumber(DEFAULT_NUMBER_BASE, "0");
        Number exponent = createNumber(DEFAULT_NUMBER_BASE, "2");

        Function f = FunctionHelper.createMonomialFunction(coefficient, exponent);

        assertEquals("formula", "0", f.toString());
        assertEquals("formula", "f(x) = 0", f.toFunctionNotation());

        for (DataEntry entry : data) {

            Number actualOutput = f.calculate(entry.input);
            assertEquals("function values", entry.expectedOutput, actualOutput);
        }
    }

    /**
     * Tests creating a monomial function with valid parameters.
     */
    @Test
    public void testCreateMonomialFunction4() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "3"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2")));

        Number coefficient = createNumber(DEFAULT_NUMBER_BASE, "2");
        Number exponent = createNumber(DEFAULT_NUMBER_BASE, "0");

        Function f = FunctionHelper.createMonomialFunction(coefficient, exponent);

        assertEquals("formula", "2", f.toString());
        assertEquals("formula", "f(x) = 2", f.toFunctionNotation());

        for (DataEntry entry : data) {

            Number actualOutput = f.calculate(entry.input);
            assertEquals("function values", entry.expectedOutput, actualOutput);
        }
    }

    /**
     * Tests creating a monomial function with an invalid parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateMonomialWithInvalidCoefficient() {

        Number coefficient = null;
        Number exponent = createNumber(DEFAULT_NUMBER_BASE, "2");

        FunctionHelper.createMonomialFunction(coefficient, exponent);
    }

    /**
     * Tests creating a monomial function with an invalid parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateMonomialWithInvalidExponent() {

        Number coefficient = createNumber(DEFAULT_NUMBER_BASE, "2");
        Number exponent = null;

        FunctionHelper.createMonomialFunction(coefficient, exponent);
    }

    /**
     * Tests creating a monomial function with an invalid parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateMonomialWithNonIntegerExponent() {

        Number coefficient = createNumber(DEFAULT_NUMBER_BASE, "2");
        Number exponent = createNumber(DEFAULT_NUMBER_BASE, "2.2");

        FunctionHelper.createMonomialFunction(coefficient, exponent);
    }

    /**
     * Tests creating a monomial function with an invalid parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateMonomialWithNegativeExponent() {

        Number coefficient = createNumber(DEFAULT_NUMBER_BASE, "2");
        Number exponent = createNumber(DEFAULT_NUMBER_BASE, "-2");

        FunctionHelper.createMonomialFunction(coefficient, exponent);
    }

    /**
     * Tests a constant function.
     */
    @Test
    public void testConstantFunctionCreation() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "3"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")));

        Number coefficient = createNumber(DEFAULT_NUMBER_BASE, "0");
        Number exponent = createNumber(DEFAULT_NUMBER_BASE, "0");

        Function f = FunctionHelper.createMonomialFunction(coefficient, exponent);

        assertEquals("formula", "0", f.toString());
        assertEquals("formula", "f(x) = 0", f.toFunctionNotation());

        for (DataEntry entry : data) {

            Number actualOutput = f.calculate(entry.input);
            assertEquals("function values", entry.expectedOutput, actualOutput);
        }
    }

    /**
     * Tests a constant function.
     */
    @Test
    public void testConstantFunction2Creation() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "5")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "5")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "5")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "3"),
                                           createNumber(DEFAULT_NUMBER_BASE, "5")));

        Function f = FunctionHelper.createPolynomialFunction(DEFAULT_NUMBER_BASE, "5");

        assertEquals("formula", "5", f.toString());
        assertEquals("formula", "f(x) = 5", f.toFunctionNotation());

        for (DataEntry entry : data) {

            Number actualOutput = f.calculate(entry.input);
            assertEquals("function values", entry.expectedOutput, actualOutput);
        }
    }

    /**
     * Tests a linear function.
     */
    @Test
    public void testLinarFunctionCreation() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "1")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "3")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "5")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "3"),
                                           createNumber(DEFAULT_NUMBER_BASE, "7")));

        Function f = FunctionHelper.createPolynomialFunction(DEFAULT_NUMBER_BASE, "1", "2");

        assertEquals("formula", "2 * x + 1", f.toString());
        assertEquals("formula", "f(x) = 2 * x + 1", f.toFunctionNotation());

        for (DataEntry entry : data) {

            Number actualOutput = f.calculate(entry.input);
            assertEquals("function values", entry.expectedOutput, actualOutput);
        }
    }

    /**
     * Tests a quadratic function.
     */
    @Test
    public void testQuadraticFunctionCreation() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "1")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "6")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "11")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "3"),
                                           createNumber(DEFAULT_NUMBER_BASE, "22")));

        Function f = FunctionHelper.createPolynomialFunction(DEFAULT_NUMBER_BASE, "1", "2", "3");

        assertEquals("formula", "3 * x^2 + 2 * x + 1", f.toString());
        assertEquals("formula", "f(x) = 3 * x^2 + 2 * x + 1", f.toFunctionNotation());

        for (DataEntry entry : data) {

            Number actualOutput = f.calculate(entry.input);
            assertEquals("function values", entry.expectedOutput, actualOutput);
        }
    }

    /**
     * Tests a cubic function.
     */
    @Test
    public void testCubicFunctionCreation() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "1")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "A")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "31")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "3"),
                                           createNumber(DEFAULT_NUMBER_BASE, "8E")));

        Function f = FunctionHelper.createPolynomialFunction(DEFAULT_NUMBER_BASE, "1", "2", "3", "4");

        assertEquals("formula", "4 * x^3 + 3 * x^2 + 2 * x + 1", f.toString());
        assertEquals("formula", "f(x) = 4 * x^3 + 3 * x^2 + 2 * x + 1", f.toFunctionNotation());

        for (DataEntry entry : data) {

            Number actualOutput = f.calculate(entry.input);
            assertEquals("function values", entry.expectedOutput, actualOutput);
        }
    }

    /**
     * Tests creating a üplynomial function with an invalid parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPolynomialFunctionWithInvalidCoefficients() {

        FunctionHelper.createPolynomialFunction(DEFAULT_NUMBER_BASE);
    }

    /**
     * Tests creating a üplynomial function with an invalid parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPolynomialFunctionWithInvalidBase() {

        int base = 0;
        String[] coefficientStrings = new String[] { "1" };

        FunctionHelper.createPolynomialFunction(base, coefficientStrings);
    }

    /**
     * Tests a threshold function.
     */
    @Test
    public void testPartialFunctionCreation() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "1")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "6")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "5")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "3"),
                                           createNumber(DEFAULT_NUMBER_BASE, "7")));

        Function f1 = FunctionHelper.createPolynomialFunction(DEFAULT_NUMBER_BASE, "1", "2", "3");
        Function f2 = FunctionHelper.createPolynomialFunction(DEFAULT_NUMBER_BASE, "1", "2");
        Function f =
            FunctionHelper.createPartialFunction(new ConditionFunctionEntry(new LesserThanCondition(createNumber(DEFAULT_NUMBER_BASE,
                                                                                                                 "2")),
                                                                            f1),
                                                 new ConditionFunctionEntry(new GreaterOrEqualCondition(createNumber(DEFAULT_NUMBER_BASE,
                                                                                                                     "2")),
                                                                            f2));

        assertEquals("formula", "{ x < 2 : 3 * x^2 + 2 * x + 1; x >= 2 : 2 * x + 1 }", f.toString());
        assertEquals("formula", "f(x) = { x < 2 : 3 * x^2 + 2 * x + 1; x >= 2 : 2 * x + 1 }", f.toFunctionNotation());

        for (DataEntry entry : data) {

            Number actualOutput = f.calculate(entry.input);
            assertEquals("function values", entry.expectedOutput, actualOutput);
        }
    }

}
