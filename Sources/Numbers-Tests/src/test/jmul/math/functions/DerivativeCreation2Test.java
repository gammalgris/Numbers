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
 * This test suite tests various derivative functions (in number base 16).
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
    public void testMonomialFunction() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "-4")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
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
    public void testMonomialFunction2() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "9")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
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
    public void testMonomialFunction3() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
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
    public void testMonomialFunction4() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
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
    public void testConstantFunction2() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
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
    public void testLinearFunction() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
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
    public void testQuadraticFunction() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "-4")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
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
     * Tests a cubic function.
     */
    @Test
    public void testCubicFunction() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "8")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "14")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "3E")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "3"),
                                           createNumber(DEFAULT_NUMBER_BASE, "80")));

        Function f = FunctionHelper.createPolynomialFunction(DEFAULT_NUMBER_BASE, "1", "2", "3", "4");
        f = f.derivativeFunction();

        assertEquals("formula", "C * x^2 + 6 * x + 2", f.toString());
        assertEquals("formula", "f(x) = C * x^2 + 6 * x + 2", f.toFunctionNotation());

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
                                           createNumber(DEFAULT_NUMBER_BASE, "8")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "14")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "3E")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "3"),
                                           createNumber(DEFAULT_NUMBER_BASE, "80")));

        Function f = FunctionHelper.createPolynomialFunction(DEFAULT_NUMBER_BASE, "0", "2", "3", "4");
        f = f.derivativeFunction();

        assertEquals("formula", "C * x^2 + 6 * x + 2", f.toString());
        assertEquals("formula", "f(x) = C * x^2 + 6 * x + 2", f.toFunctionNotation());

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
                                           createNumber(DEFAULT_NUMBER_BASE, "6")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "12")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "3C")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "3"),
                                           createNumber(DEFAULT_NUMBER_BASE, "7E")));

        Function f = FunctionHelper.createPolynomialFunction(DEFAULT_NUMBER_BASE, "1", "0", "3", "4");
        f = f.derivativeFunction();

        assertEquals("formula", "C * x^2 + 6 * x", f.toString());
        assertEquals("formula", "f(x) = C * x^2 + 6 * x", f.toFunctionNotation());

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
                                           createNumber(DEFAULT_NUMBER_BASE, "E")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "E")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "32")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "3"),
                                           createNumber(DEFAULT_NUMBER_BASE, "6E")));

        Function f = FunctionHelper.createPolynomialFunction(DEFAULT_NUMBER_BASE, "1", "2", "0", "4");
        f = f.derivativeFunction();

        assertEquals("formula", "C * x^2 + 2", f.toString());
        assertEquals("formula", "f(x) = C * x^2 + 2", f.toFunctionNotation());

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
                                           createNumber(DEFAULT_NUMBER_BASE, "-4")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "8")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "E")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "3"),
                                           createNumber(DEFAULT_NUMBER_BASE, "14")));

        Function f = FunctionHelper.createPolynomialFunction(DEFAULT_NUMBER_BASE, "1", "2", "3", "0");
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
    public void testPartialFunction() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
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

    /**
     * Tests creating a root function with valid parameters.
     */
    @Test
    public void testRootFunction() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             //TODO 2 * (-1) * i
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "5.6568542498")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "3"),
                                           createNumber(DEFAULT_NUMBER_BASE, "10.3923048458")));

        Function f = FunctionHelper.createRootFunction(DEFAULT_NUMBER_BASE, "2", "3", "2");
        f = f.derivativeFunction();

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
    @Test
    public void testRootFunction2() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             // TODO 2 * i
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2.8284271248")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "3"),
                                           createNumber(DEFAULT_NUMBER_BASE, "3.4641016152")));

        Number coefficient = createNumber(DEFAULT_NUMBER_BASE, "2");
        Fraction exponent = createFraction(DEFAULT_NUMBER_BASE, "1", "2");

        Function f = FunctionHelper.createRootFunction(coefficient, exponent);
        f = f.derivativeFunction();

        assertEquals("formula", "2 * x^(1/2)", f.toString());
        assertEquals("formula", "f(x) = 2 * x^(1/2)", f.toFunctionNotation());

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
         * e = 2.71828182845904523536028747135266249775724709369995957496696762772407663035
         *
         * f(-1) = 0.19661193324345542166463022366450615508493151331200606128718642697872213600
         * f(0) = 0.25
         * f(1) = 0.19661193324345542166463022366450615508493151331200606128718642697872213600
         * f(2) = 0.10499358540698033279007559356054896219252989126985245573537129074881962406
         * f(3) = 0.04517665973357681512442899874934442094507842212096893288333036326699906530
         */

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0.325528DF4C")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0.4")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0.325528DF4C")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0.1AE0DC0FA4")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "3"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0.0B90B2941E")));

        Function f = FunctionHelper.createSigmoidFunction(DEFAULT_NUMBER_BASE);
        f = f.derivativeFunction();

        assertEquals("formula", "e^x / ( e^x + 1 )^2", f.toString());
        assertEquals("formula", "f(x) = e^x / ( e^x + 1 )^2", f.toFunctionNotation());

        for (DataEntry entry : data) {

            Number actualOutput = f.calculate(entry.input);
            assertEquals("function values", entry.expectedOutput, actualOutput);
        }
    }

    /**
     * Tests creating a hyperbolic tangent function with valid parameters.
     */
    @Test
    public void testHyperbolicTangentFunction() {

        /*
         * e = 2.71828182845904523536028747135266249775724709369995957496696762772407663035
         *
         * f(-1) = 0.39322386648691084332926044732901231016986302662401212257437285395744427201
         * f(0) = 0.4
         * f(1) = 0.39322386648691084332926044732901231016986302662401212257437285395744427201
         * f(2) = 0.20998717081396066558015118712109792438505978253970491147074258149763924813
         * f(3) = 0.09035331946715363024885799749868884189015684424193786576666072653399813061
         */

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0.64AA51BE99")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0.8")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0.64AA51BE99")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0.35C1B81F48")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "3"),
                                           createNumber(DEFAULT_NUMBER_BASE, "0.172165283D")));

        Function f = FunctionHelper.createHyperbolicTangentFunction(DEFAULT_NUMBER_BASE);
        f = f.derivativeFunction();

        assertEquals("formula", "( 2 * e^x ) / ( e^x + 1 )^2", f.toString());
        assertEquals("formula", "f(x) = ( 2 * e^x ) / ( e^x + 1 )^2", f.toFunctionNotation());

        for (DataEntry entry : data) {

            Number actualOutput = f.calculate(entry.input);
            assertEquals("function values", entry.expectedOutput, actualOutput);
        }
    }

    /**
     * Tests creating an exponential function with valid parameters.
     */
    @Test
    public void testExponentialFunction() {

        TrainingData data =
            new TrainingData(new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "-1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "1.5")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "0"),
                                           createNumber(DEFAULT_NUMBER_BASE, "2")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "1"),
                                           createNumber(DEFAULT_NUMBER_BASE, "3")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "2"),
                                           createNumber(DEFAULT_NUMBER_BASE, "5")),
                             new DataEntry(createNumber(DEFAULT_NUMBER_BASE, "3"),
                                           createNumber(DEFAULT_NUMBER_BASE, "9")));

        Number coefficient1 = createNumber(DEFAULT_NUMBER_BASE, "2");
        Number coefficient0 = createNumber(DEFAULT_NUMBER_BASE, "1");

        Function f = FunctionHelper.createExponentialFunction(coefficient1, coefficient0);
        f = f.derivativeFunction();

        assertEquals("formula", "2^x + 1", f.toString());
        assertEquals("formula", "f(x) = 2^x + 1", f.toFunctionNotation());

        for (DataEntry entry : data) {

            Number actualOutput = f.calculate(entry.input);
            assertEquals("function values", entry.expectedOutput, actualOutput);
        }
    }

}
