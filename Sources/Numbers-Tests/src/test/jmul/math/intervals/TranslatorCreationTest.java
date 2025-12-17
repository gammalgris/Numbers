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

package test.jmul.math.intervals;


import jmul.math.intervals.BoundaryTypes;
import jmul.math.intervals.Interval;

import jmul.test.classification.UnitTest;

import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;

import org.junit.Test;

import static jmul.math.intervals.IntervalHelper.createInterval;
import static jmul.math.intervals.IntervalHelper.createTranslator;
import jmul.math.intervals.Translator;

import static org.junit.Assert.assertEquals;


/**
 * This test suite tests creating a translator.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class TranslatorCreationTest {

    /**
     * Tests creating a transaltor with valid parameters.
     */
    @Test
    public void testCreateTranslator() {

        int base = 10;

        Number originLowerBoundary = createNumber(base, "10");
        Number originUpperBoundary = createNumber(base, "20");

        Number destinationLowerBoundary = createNumber(base, "0");
        Number destinationUpperBoundary = createNumber(base, "20");

        Translator translator =
            createTranslator(originLowerBoundary, originUpperBoundary, destinationLowerBoundary,
                             destinationUpperBoundary);

        assertEquals("#base", base, translator.base());

        assertEquals("#bounds", originLowerBoundary, translator.originInterval().lowerBoundary());
        assertEquals("#bounds", originUpperBoundary, translator.originInterval().upperBoundary());
        assertEquals("#bounds", destinationLowerBoundary, translator.destinationInterval().lowerBoundary());
        assertEquals("#bounds", destinationUpperBoundary, translator.destinationInterval().upperBoundary());
    }

    /**
     * Tests creating a transaltor with valid parameters.
     */
    @Test
    public void testCreateTranslatorVariant2() {

        int base = 10;

        Number originLowerBoundary = createNumber(base, "10");
        Number originUpperBoundary = createNumber(base, "20");
        Interval originInterval =
            createInterval(originLowerBoundary, BoundaryTypes.CLOSED_BOUNDARY, originUpperBoundary,
                           BoundaryTypes.CLOSED_BOUNDARY);

        Number destinationLowerBoundary = createNumber(base, "0");
        Number destinationUpperBoundary = createNumber(base, "20");
        Interval destinationInterval =
            createInterval(destinationLowerBoundary, BoundaryTypes.CLOSED_BOUNDARY, destinationUpperBoundary,
                           BoundaryTypes.CLOSED_BOUNDARY);

        Translator translator = createTranslator(originInterval, destinationInterval);

        assertEquals("#base", base, translator.base());

        assertEquals("#bounds", originLowerBoundary, translator.originInterval().lowerBoundary());
        assertEquals("#bounds", originUpperBoundary, translator.originInterval().upperBoundary());
        assertEquals("#bounds", destinationLowerBoundary, translator.destinationInterval().lowerBoundary());
        assertEquals("#bounds", destinationUpperBoundary, translator.destinationInterval().upperBoundary());
    }

}
