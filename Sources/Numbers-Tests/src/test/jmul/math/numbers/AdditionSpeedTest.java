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
public class AdditionSpeedTest {

    /**
     * The main method.
     *
     * @param args
     *        command line argumetns are not evaluated
     */
    public static void main(String... args) {

        int[] numberBases = { 2, 10, 30, 60 };

        for (int base : numberBases) {

            Number a = createNumber(10, "11111");
            System.out.println("a [base=" + a.base() + "] = " + a);
            Number b = createNumber(10, "123456");
            System.out.println("b [base=" + b.base() + "] = " + b);

            Number c = a.rebase(base);
            System.out.println("c [base=" + c.base() + "] = " + c);
            Number d = b.rebase(base);
            System.out.println("d [base=" + d.base() + "] = " + d);

            long startTime = System.currentTimeMillis();

            perform1000000Additions(c, d);

            long stopTime = System.currentTimeMillis();
            long delta = stopTime - startTime;

            String info = String.format("base %s; counting %s ms", base, delta);
            System.out.println(info);
            System.out.println();
        }
    }

    /**
     * Repeats thousand additions with the specified numbers.
     *
     * @param a
     *        a number
     * @param b
     *        a number
     */
    private static void perform1000000Additions(Number a, Number b) {

        System.out.println("# 1000000 additions");
        for (int i = 0; i < 1000000; i++) {

            Number c = a.add(b);
            if (i % 1000 == 0) {

                System.out.print(".");
            }
        }
        System.out.println();
    }

}
