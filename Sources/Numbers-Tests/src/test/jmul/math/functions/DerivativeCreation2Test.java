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
 * This test suite tests various derivative functions.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class DerivativeCreation2Test {

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
                                           createNumber(DEFAULT_NUMBER_BASE, "4")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "8")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "3"),
                                           createNumber(DEFAULT_NUMBER_BASE, "C")));

        Number coefficient = createNumber(DEFAULT_NUMBER_BASE, "2");
        Number exponent = createNumber(DEFAULT_NUMBER_BASE, "2");

        Function f = FunctionHelper.createMonomialFunction(coefficient, exponent);
        f = f.derivativeFunction();

        assertEquals("formula", "4 * x", f.toString());
        assertEquals("formula", "f(x) = 4 * x", f.toFunctionNotation());

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
                                           createNumber(DEFAULT_NUMBER_BASE, "9")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "24")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "3"),
                                           createNumber(DEFAULT_NUMBER_BASE, "51")));

        Number coefficient = createNumber(DEFAULT_NUMBER_BASE, "3");
        Number exponent = createNumber(DEFAULT_NUMBER_BASE, "3");

        Function f = FunctionHelper.createMonomialFunction(coefficient, exponent);
        f = f.derivativeFunction();

        assertEquals("formula", "9 * x^2", f.toString());
        assertEquals("formula", "f(x) = 9 * x^2", f.toFunctionNotation());

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
        f = f.derivativeFunction();

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
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "3"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")));

        Number coefficient = createNumber(DEFAULT_NUMBER_BASE, "2");
        Number exponent = createNumber(DEFAULT_NUMBER_BASE, "0");

        Function f = FunctionHelper.createMonomialFunction(coefficient, exponent);
        f = f.derivativeFunction();

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
    public void testConstantFunctionDerivativeFunction2() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "3"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")));

        Function f = FunctionHelper.createPolynomialFunction(DEFAULT_NUMBER_BASE, "5");
        f = f.derivativeFunction();

        assertEquals("formula", "0", f.toString());
        assertEquals("formula", "f(x) = 0", f.toFunctionNotation());

        for (DataEntry entry : data) {

            Number actualOutput = f.calculate(entry.input);
            assertEquals("function values", entry.expectedOutput, actualOutput);
        }
    }

    /**
     * Tests the derivative function of a linear function.
     */
    @Test
    public void testLinarFunctionDerivativeFunction() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "3"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2")));

        Function f = FunctionHelper.createPolynomialFunction(DEFAULT_NUMBER_BASE, "1", "2");
        f = f.derivativeFunction();

        assertEquals("formula", "2", f.toString());
        assertEquals("formula", "f(x) = 2", f.toFunctionNotation());

        for (DataEntry entry : data) {

            Number actualOutput = f.calculate(entry.input);
            assertEquals("function values", entry.expectedOutput, actualOutput);
        }
    }

    /**
     * Tests the derivative function of a quadratic function.
     */
    @Test
    public void testDerivativeFunctionQuadraticFunction() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "8")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "E")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "3"),
                                           createNumber(DEFAULT_NUMBER_BASE, "14")));

        Function f = FunctionHelper.createPolynomialFunction(DEFAULT_NUMBER_BASE, "1", "2", "3");
        f = f.derivativeFunction();

        assertEquals("formula", "6 * x + 2", f.toString());
        assertEquals("formula", "f(x) = 6 * x + 2", f.toFunctionNotation());

        for (DataEntry entry : data) {

            Number actualOutput = f.calculate(entry.input);
            assertEquals("function values", entry.expectedOutput, actualOutput);
        }
    }

    /**
     * Tests the derivative function of a threshold function.
     */
    @Test
    public void testDerivativeFunctionPartialFunction() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "8")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "3"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2")));

        Function f1 = FunctionHelper.createPolynomialFunction(DEFAULT_NUMBER_BASE, "1", "2", "3");
        Function f2 = FunctionHelper.createPolynomialFunction(DEFAULT_NUMBER_BASE, "1", "2");
        Function f =
            FunctionHelper.createPartialFunction(new ConditionFunctionEntry(new LesserThanCondition(createNumber(DEFAULT_NUMBER_BASE,
                                                                                                                 "2")),
                                                                            f1),
                                                 new ConditionFunctionEntry(new GreaterOrEqualCondition(createNumber(DEFAULT_NUMBER_BASE,
                                                                                                                     "2")),
                                                                            f2));
        f = f.derivativeFunction();

        assertEquals("formula", "{ x < 2 : 6 * x + 2; x >= 2 : 2 }", f.toString());
        assertEquals("formula", "f(x) = { x < 2 : 6 * x + 2; x >= 2 : 2 }", f.toFunctionNotation());

        for (DataEntry entry : data) {

            Number actualOutput = f.calculate(entry.input);
            assertEquals("function values", entry.expectedOutput, actualOutput);
        }
    }

}
