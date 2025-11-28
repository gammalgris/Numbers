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

package test.jmul.math.numbers;


import jmul.math.Math;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createInfinity;
import static jmul.math.numbers.NumberHelper.createNegativeInfinity;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.numbers.exceptions.UndefinedOperationException;
import jmul.math.operations.processing.ProcessingDetails;
import jmul.math.operations.repository.OperationIdentifiers;

import jmul.test.classification.UnitTest;

import org.junit.Test;


/**
 * A test suite with invalid input parameters.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class ExponentiateNumberWithIllegalArgumentsTest {

    /**
     * Tests exponentiating infinity with zero.
     */
    @Test(expected = UndefinedOperationException.class)
    public void testExponentiateInfinityWithZero() {

        Number number = createInfinity(10);
        Number exponent = createNumber(10, "0");

        number.exponentiate(exponent);
    }

    /**
     * Tests exponentiating infinity with zero.
     */
    @Test(expected = UndefinedOperationException.class)
    public void testExponentiateInfinityWithZeroVariant2() {

        Number number = createInfinity(10);
        Number exponent = createNumber(10, "0");

        Math.exponentiate(number, exponent);
    }

    /**
     * Tests exponentiating infinity with zero.
     */
    @Test(expected = UndefinedOperationException.class)
    public void testExponentiateInfinityWithZeroVariant3() {

        Number number = createInfinity(10);
        Number exponent = createNumber(10, "0");

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(OperationIdentifiers.EXPONENTIATE_NUMBER_WITH_NUMBER_FUNCTION,
                                                   ProcessingDetails.DEFAULT_PRECISION,
                                                   ProcessingDetails.DEFAULT_ITERATION_DEPTH);

        number.exponentiate(processingDetails, exponent);
    }

    /**
     * Tests exponentiating infinity with zero.
     */
    @Test(expected = UndefinedOperationException.class)
    public void testExponentiateInfinityWithZeroVariant4() {

        Number number = createInfinity(10);
        Number exponent = createNumber(10, "0");

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(OperationIdentifiers.EXPONENTIATE_NUMBER_WITH_NUMBER_FUNCTION,
                                                   ProcessingDetails.DEFAULT_PRECISION,
                                                   ProcessingDetails.DEFAULT_ITERATION_DEPTH);

        Math.exponentiate(processingDetails, number, exponent);
    }

    /**
     * Tests exponentiating negative infinity with zero.
     */
    @Test(expected = UndefinedOperationException.class)
    public void testExponentiateNegativeInfinityWithZero() {

        Number number = createNegativeInfinity(10);
        Number exponent = createNumber(10, "0");

        number.exponentiate(exponent);
    }

    /**
     * Tests exponentiating negative infinity with zero.
     */
    @Test(expected = UndefinedOperationException.class)
    public void testExponentiateNegativeInfinityWithZeroVariant2() {

        Number number = createNegativeInfinity(10);
        Number exponent = createNumber(10, "0");

        Math.exponentiate(number, exponent);
    }

    /**
     * Tests exponentiating negative infinity with zero.
     */
    @Test(expected = UndefinedOperationException.class)
    public void testExponentiateNegativeInfinityWithZeroVariant3() {

        Number number = createNegativeInfinity(10);
        Number exponent = createNumber(10, "0");

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(OperationIdentifiers.EXPONENTIATE_NUMBER_WITH_NUMBER_FUNCTION,
                                                   ProcessingDetails.DEFAULT_PRECISION,
                                                   ProcessingDetails.DEFAULT_ITERATION_DEPTH);

        number.exponentiate(processingDetails, exponent);
    }

    /**
     * Tests exponentiating negative infinity with zero.
     */
    @Test(expected = UndefinedOperationException.class)
    public void testExponentiateNegativeInfinityWithZeroVariant4() {

        Number number = createNegativeInfinity(10);
        Number exponent = createNumber(10, "0");

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(OperationIdentifiers.EXPONENTIATE_NUMBER_WITH_NUMBER_FUNCTION,
                                                   ProcessingDetails.DEFAULT_PRECISION,
                                                   ProcessingDetails.DEFAULT_ITERATION_DEPTH);

        Math.exponentiate(processingDetails, number, exponent);
    }

}
