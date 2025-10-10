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


import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;

import jmul.test.classification.ManualTest;

import org.junit.Ignore;


/**
 * This test suite counts numbers in a specific interval for different number bases.
 *
 * @author Kristian Kutin
 */
@Ignore
@ManualTest
public class CountNumbersTest {

    /**
     * The main method.
     *
     * @param args
     *        command line argumetns are not evaluated
     */
    public static void main(String... args) {

        int[] numberBases = { 2, 10, 30 };

        for (int base : numberBases) {

            long startTime = System.currentTimeMillis();

            Number counter = countNumbersBase(base);

            long stopTime = System.currentTimeMillis();
            long delta = stopTime - startTime;

            String info = String.format("base %s; counting %s ms; counted %s numbers", base, delta, counter);
            System.out.println(info);
        }
    }

    /**
     * Count the distinct number within the interval [0, 1].
     *
     * @param base
     *        a number base
     *
     * @return the total count
     */
    private static Number countNumbersBase(int base) {

        Number iterationStep = createNumber(base, "0.00001");
        Number number = createNumber(base, "1");
        Number counter = createNumber(10, "1");

        while (!number.isZero()) {

            number = number.subtract(iterationStep);
            counter = counter.inc();
        }

        return counter;
    }

}
