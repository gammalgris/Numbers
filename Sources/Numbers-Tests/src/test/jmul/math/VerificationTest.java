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

package test.jmul.math;


import java.util.SortedMap;
import java.util.TreeMap;

import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;

import jmul.test.classification.ManualTest;

import org.junit.Ignore;


/**
 * A test suite for manual verification of calculations.
 *
 * @author Kristian Kutin
 */
@Ignore
@ManualTest
public class VerificationTest {

    /**
     * A main method for performing some calculations.
     *
     * @param args
     *        some command line arguments. Currently these are not evaluated.
     */
    public static void main(String... args) {

        Number multiple = createNumber(5, "0");
        Number sum = createNumber(5, "0");
        Number value = createNumber(5, "3.1");

        Number counter = createNumber(10, "0");
        Number stop = createNumber(10, "10");

        SortedMap<Number, Number> map1 = new TreeMap<Number, Number>();
        SortedMap<Number, Number> map2 = new TreeMap<Number, Number>();

        while (counter.isLesserOrEqual(stop)) {

            map1.put(counter, sum);
            map2.put(counter, multiple);

            counter = counter.inc();
            sum = sum.add(value);
            multiple = multiple.inc();
        }

        System.out.println("map #1: " + map1);
        System.out.println("map #2: " + map2);

        System.out.println("done.");
    }

}
