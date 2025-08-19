/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2024  Kristian Kutin
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


import java.util.SortedMap;
import java.util.TreeMap;

import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.numbers.nodes.DigitNode;

import jmul.test.classification.ManualTest;

import org.junit.Ignore;


/**
 * This test suite tests the occurrence of hash collisions.<br>
 * <br>
 * <i>Note:<br>
 * Currently this test has to be started manually. The results have only informational
 * value. Hash collissions cannot be avoided since the integer range is smaller than
 * the range of numbers that can be created.</i>
 *
 * @author Kristian Kutin
 */
@Ignore
@ManualTest
public class HashCollisionTest {

    /**
     * A hash map for counting hashes and their occurrences.
     */
    private static final SortedMap<Integer, Integer> HASH_OCCURRENCE_MAP;

    /*
     * The static initializer.
     */
    static {

        HASH_OCCURRENCE_MAP = new TreeMap<>();
    }

    /**
     * The main method.
     *
     * @param args
     *        command line arguments are ignored
     */
    public static void main(String... args) {


        int base = 10;
        final Number ZERO = createNumber(base, "0");
        final Number MAX = createNumber(base, "1000");
        Number counter = ZERO;

        while (counter.isLesser(MAX)) {

            showProgress(counter);

            Number positiveNumber = counter;
            addNumber(positiveNumber);

            Number negativeNumber = counter.negate();
            addNumber(negativeNumber);

            counter = counter.inc();
        }

        System.out.println("The map contains " + HASH_OCCURRENCE_MAP.size() + " unique hashes.");
    }

    /**
     * Takes the specified number and calculates the hash value. The occurrence of this
     * hash value is updated in the underlying map.
     *
     * @param number
     *        a number
     */
    private static void addNumber(Number number) {

        Integer hash = number.hashCode();

        Integer occurrence = HASH_OCCURRENCE_MAP.get(hash);
        if (occurrence == null) {

            occurrence = 1;

        } else {

            occurrence++;
        }

        HASH_OCCURRENCE_MAP.put(hash, occurrence);
    }

    /**
     * Shows the progress of the counter
     *
     * @param counter
     *        the current counter
     */
    private static void showProgress(Number counter) {

        DigitNode node = counter.centerNode();
        if (node.digit().isZero()) {

            DigitNode leftNode = node.leftNode();
            if ((leftNode != null) && leftNode.digit().isZero()) {

                System.out.println(counter);
            }
        }
    }

}
