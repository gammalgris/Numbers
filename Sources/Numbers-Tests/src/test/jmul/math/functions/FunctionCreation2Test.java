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
import jmul.math.fractions.Fraction;
import static jmul.math.fractions.FractionHelper.createFraction;
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
 * This test suite tests various functions (in number base 16).
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
    public void testMonomialFunction() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-0.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0.8")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0.8")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "4.8")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "8")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "C.8")),
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
    public void testMonomialFunction2() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-0.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0.8")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0.8")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "4.8")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "8")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "C.8")),
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
    public void testMonomialFunction3() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-0.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2.8"),
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
    public void testMonomialFunction4() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-0.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2.8"),
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
    public void testMonomialFunctionWithInvalidCoefficient() {

        Number coefficient = null;
        Number exponent = createNumber(DEFAULT_NUMBER_BASE, "2");

        FunctionHelper.createMonomialFunction(coefficient, exponent);
    }

    /**
     * Tests creating a monomial function with an invalid parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testMonomialFunctionWithInvalidExponent() {

        Number coefficient = createNumber(DEFAULT_NUMBER_BASE, "2");
        Number exponent = null;

        FunctionHelper.createMonomialFunction(coefficient, exponent);
    }

    /**
     * Tests creating a monomial function with an invalid parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testMonomialFunctionWithNonIntegerExponent() {

        Number coefficient = createNumber(DEFAULT_NUMBER_BASE, "2");
        Number exponent = createNumber(DEFAULT_NUMBER_BASE, "2.2");

        FunctionHelper.createMonomialFunction(coefficient, exponent);
    }

    /**
     * Tests creating a monomial function with an invalid parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testMonomialFunctionWithNegativeExponent() {

        Number coefficient = createNumber(DEFAULT_NUMBER_BASE, "2");
        Number exponent = createNumber(DEFAULT_NUMBER_BASE, "-2");

        FunctionHelper.createMonomialFunction(coefficient, exponent);
    }

    /**
     * Tests a constant function.
     */
    @Test
    public void testConstantFunction() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-0.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2.8"),
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
    public void testConstantFunction2() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "5")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-0.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "5")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "5")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "5")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "5")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "5")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "5")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2.8"),
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
    public void testLinearFunction() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "-1")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-0.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "1")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "3")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "4")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "5")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "6")),
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
    public void testQuadraticFunction() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-0.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0.C")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "1")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2.C")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "6")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "A.C")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "11")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "18.C")),
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
    public void testCubicFunction() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "-2")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-0.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0.4")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "1")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "3.4")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "A")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "18.4")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "31")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "57.4")),
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
     * Test a polynomial function with a 0 component.
     */
    @Test
    public void testPolynomialFunction() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "-3")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-0.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "-0.C")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2.4")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "9")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "17.4")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "30")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "56.4")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "3"),
                                           createNumber(DEFAULT_NUMBER_BASE, "8D")));

        Function f = FunctionHelper.createPolynomialFunction(DEFAULT_NUMBER_BASE, "0", "2", "3", "4");

        assertEquals("formula", "4 * x^3 + 3 * x^2 + 2 * x", f.toString());
        assertEquals("formula", "f(x) = 4 * x^3 + 3 * x^2 + 2 * x", f.toFunctionNotation());

        for (DataEntry entry : data) {

            Number actualOutput = f.calculate(entry.input);
            assertEquals("function values", entry.expectedOutput, actualOutput);
        }
    }

    /**
     * Test a polynomial function with a 0 component.
     */
    @Test
    public void testPolynomialFunction2() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-0.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "1.4")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "1")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2.4")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "8")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "15.4")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2D")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "52.4")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "3"),
                                           createNumber(DEFAULT_NUMBER_BASE, "88")));

        Function f = FunctionHelper.createPolynomialFunction(DEFAULT_NUMBER_BASE, "1", "0", "3", "4");

        assertEquals("formula", "4 * x^3 + 3 * x^2 + 1", f.toString());
        assertEquals("formula", "f(x) = 4 * x^3 + 3 * x^2 + 1", f.toFunctionNotation());

        for (DataEntry entry : data) {

            Number actualOutput = f.calculate(entry.input);
            assertEquals("function values", entry.expectedOutput, actualOutput);
        }
    }

    /**
     * Test a polynomial function with a 0 component.
     */
    @Test
    public void testPolynomialFunction3() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "-5")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-0.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "-0.8")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "1")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2.8")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "7")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "11.8")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "25")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "44.8")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "3"),
                                           createNumber(DEFAULT_NUMBER_BASE, "73")));

        Function f = FunctionHelper.createPolynomialFunction(DEFAULT_NUMBER_BASE, "1", "2", "0", "4");

        assertEquals("formula", "4 * x^3 + 2 * x + 1", f.toString());
        assertEquals("formula", "f(x) = 4 * x^3 + 2 * x + 1", f.toFunctionNotation());

        for (DataEntry entry : data) {

            Number actualOutput = f.calculate(entry.input);
            assertEquals("function values", entry.expectedOutput, actualOutput);
        }
    }

    /**
     * Test a polynomial function with a 0 component.
     */
    @Test
    public void testPolynomialFunction4() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-0.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0.C")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "1")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2.C")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "6")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "A.C")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "11")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "18.C")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "3"),
                                           createNumber(DEFAULT_NUMBER_BASE, "22")));

        Function f = FunctionHelper.createPolynomialFunction(DEFAULT_NUMBER_BASE, "1", "2", "3", "0");

        assertEquals("formula", "3 * x^2 + 2 * x + 1", f.toString());
        assertEquals("formula", "f(x) = 3 * x^2 + 2 * x + 1", f.toFunctionNotation());

        for (DataEntry entry : data) {

            Number actualOutput = f.calculate(entry.input);
            assertEquals("function values", entry.expectedOutput, actualOutput);
        }
    }

    /**
     * Test a polynomial function with component of 1.
     */
    @Test
    public void testPolynomialFunction5() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-0.5"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0.C13")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "1")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0.5"),
                                           createNumber(DEFAULT_NUMBER_BASE, "1.70D")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "4")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1.5"),
                                           createNumber(DEFAULT_NUMBER_BASE, "6.4BD")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "F")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2.5"),
                                           createNumber(DEFAULT_NUMBER_BASE, "15.06D")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "3"),
                                           createNumber(DEFAULT_NUMBER_BASE, "28")));

        Function f = FunctionHelper.createPolynomialFunction(DEFAULT_NUMBER_BASE, "1", "1", "1", "1");

        assertEquals("formula", "x^3 + x^2 + x + 1", f.toString());
        assertEquals("formula", "f(x) = x^3 + x^2 + x + 1", f.toFunctionNotation());

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
    public void testPartialFunction() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-0.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0.C")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "1")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2.C")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "6")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "A.C")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "5")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "6")),
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

    /**
     * Tests creating a root function with valid parameters (x &gt;= 0).
     */
    @Test
    public void testRootFunctionFunction() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0.B504F333FA")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "3.AC9AA3C1B6")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "5.A827999FD")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "7.E7DB926438")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "3"),
                                           createNumber(DEFAULT_NUMBER_BASE, "A.646E172118")));

        Function f = FunctionHelper.createRootFunction(DEFAULT_NUMBER_BASE, "2", "3", "2");

        assertEquals("formula", "2 * x^(3/2)", f.toString());
        assertEquals("formula", "f(x) = 2 * x^(3/2)", f.toFunctionNotation());

        for (DataEntry entry : data) {

            Number actualOutput = f.calculate(entry.input);
            assertEquals("function values", entry.expectedOutput, actualOutput);
        }
    }

    /**
     * Tests creating a root function with valid parameters.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testRootFunctionWithNegativeInput() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")));

        Function f = FunctionHelper.createRootFunction(DEFAULT_NUMBER_BASE, "2", "3", "2");

        assertEquals("formula", "2 * x^(3/2)", f.toString());
        assertEquals("formula", "f(x) = 2 * x^(3/2)", f.toFunctionNotation());

        for (DataEntry entry : data) {

            Number actualOutput = f.calculate(entry.input);
            assertEquals("function values", entry.expectedOutput, actualOutput);
        }
    }

    /**
     * Tests creating a root function with valid parameters (x &gt;= 0).
     */
    @Test
    public void testRootFunctionFunction2() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "1.6A09E667F6")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2.7311C28124")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2.D413CCCFE8")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "3.298B075B4A")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "3"),
                                           createNumber(DEFAULT_NUMBER_BASE, "3.76CF5D0B08")));

        Number coefficient = createNumber(DEFAULT_NUMBER_BASE, "2");
        Fraction exponent = createFraction(DEFAULT_NUMBER_BASE, "1", "2");

        Function f = FunctionHelper.createRootFunction(coefficient, exponent);

        assertEquals("formula", "2 * x^(1/2)", f.toString());
        assertEquals("formula", "f(x) = 2 * x^(1/2)", f.toFunctionNotation());

        for (DataEntry entry : data) {

            Number actualOutput = f.calculate(entry.input);
            assertEquals("function values", entry.expectedOutput, actualOutput);
        }
    }

    /**
     * Tests creating a root function with valid parameters (x &gt;= 0).
     */
    @Test
    public void testRootFunction3() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0.5"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1.5"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2.5"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "3"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")));

        Number coefficient = createNumber(DEFAULT_NUMBER_BASE, "0");
        Fraction exponent = createFraction(DEFAULT_NUMBER_BASE, "1", "2");

        Function f = FunctionHelper.createRootFunction(coefficient, exponent);

        assertEquals("formula", "0", f.toString());
        assertEquals("formula", "f(x) = 0", f.toFunctionNotation());

        for (DataEntry entry : data) {

            Number actualOutput = f.calculate(entry.input);
            assertEquals("function values", entry.expectedOutput, actualOutput);
        }
    }

    /**
     * Tests creating a sigmoid function with valid parameters.
     */
    @Test
    public void testSigmoidFunction() {

        /*
         * f(-1) = 0.26894142137426583239894576034283910548774689539345516276519799480189396823
         *         0.44d95851579c32..._16
         * f(0) = 0.5
         * f(1) = 0.73105857862573416760105423965716089451225310460654483723480200519810603176
         * f(2) = 0.88079707797332120180976368753356831360214196373758165765427507855654849392
         * f(3) = 0.95257412681948930085765473812182517074459784767332524812262657727600188626
         */

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0.44D958516")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-0.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0.60A681596E")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0.8")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0.9F597EA691")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0.BB26A7AE9F")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0.D14C8F953C")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0.E17BEAD445")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0.EC948EEDA")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "3"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0.F3DBE5E1AF")));

        Function f = FunctionHelper.createSigmoidFunction(DEFAULT_NUMBER_BASE);

        assertEquals("formula", "1 / ( 1 + e^-x )", f.toString());
        assertEquals("formula", "f(x) = 1 / ( 1 + e^-x )", f.toFunctionNotation());

        for (DataEntry entry : data) {

            Number actualOutput = f.calculate(entry.input);
            assertEquals("function values", entry.expectedOutput, actualOutput);
        }
    }

    /**
     * Tests creating a sigmoid function with invalid parameters.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSigmoidFunctionWithInvalidBase() {

        FunctionHelper.createSigmoidFunction(0);
    }

    /**
     * Tests creating a hyperbolic tangent function with valid parameters.
     */
    @Test
    public void testHyperbolicTangentFunction() {

        /*
         * f(-1) = -0.4621171572514683352021084793143217890245062092130896744696040103962120635
         *         -0.764d4f5d50c79b..._16
         * f(0) = 0
         * f(1) = 0.46211715725146833520210847931432178902450620921308967446960401039621206352
         * f(2) = 0.76159415594664240361952737506713662720428392747516331530855015711309698785
         * f(3) = 0.90514825363897860171530947624365034148919569534665049624525315455200377253
         */

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "-0.764D4F5D3E")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-0.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "-0.3EB2FD4D23")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0.3EB2FD4D23")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0.764D4F5D3F")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0.A2991F2A79")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0.C2F7D5A88B")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0.D9291DDB41")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "3"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0.E7B7CBC35E")));

        Function f = FunctionHelper.createHyperbolicTangentFunction(DEFAULT_NUMBER_BASE);

        assertEquals("formula", "( 1 - e^-x ) / ( 1 + e^-x )", f.toString());
        assertEquals("formula", "f(x) = ( 1 - e^-x ) / ( 1 + e^-x )", f.toFunctionNotation());

        for (DataEntry entry : data) {

            Number actualOutput = f.calculate(entry.input);
            assertEquals("function values", entry.expectedOutput, actualOutput);
        }
    }

    /**
     * Tests creating a hyperbolic tangent function with invalid parameters.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testHyperbolicTangentFunctionWithInvalidBase() {

        FunctionHelper.createHyperbolicTangentFunction(0);
    }

    /**
     * Tests creating an exponential function with valid parameters.
     */
    @Test
    public void testExponentialFunction() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "1.8")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-0.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "1.B504F333FB")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2.6A09E667F4")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "3")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "3.D413CCCFE8")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "5")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2.8"),
                                           createNumber(DEFAULT_NUMBER_BASE, "6.A827999FD")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "3"),
                                           createNumber(DEFAULT_NUMBER_BASE, "9")));

        Number coefficient1 = createNumber(DEFAULT_NUMBER_BASE, "2");
        Number coefficient0 = createNumber(DEFAULT_NUMBER_BASE, "1");

        Function f = FunctionHelper.createExponentialFunction(coefficient1, coefficient0);

        assertEquals("formula", "2^x + 1", f.toString());
        assertEquals("formula", "f(x) = 2^x + 1", f.toFunctionNotation());

        for (DataEntry entry : data) {

            Number actualOutput = f.calculate(entry.input);
            assertEquals("function values", entry.expectedOutput, actualOutput);
        }
    }

    /**
     * Tests creating an exponential function with valid parameters.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testExponentialFunctionWithZeroCoefficient1() {

        Number coefficient1 = createNumber(DEFAULT_NUMBER_BASE, "0");
        Number coefficient0 = createNumber(DEFAULT_NUMBER_BASE, "1");

        FunctionHelper.createExponentialFunction(coefficient1, coefficient0);
    }

    /**
     * Tests creating an exponential function with valid parameters.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testExponentialFunctionWithOneCoefficient1() {

        Number coefficient1 = createNumber(DEFAULT_NUMBER_BASE, "1");
        Number coefficient0 = createNumber(DEFAULT_NUMBER_BASE, "1");

        FunctionHelper.createExponentialFunction(coefficient1, coefficient0);
    }

}
