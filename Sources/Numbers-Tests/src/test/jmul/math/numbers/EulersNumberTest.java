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


import jmul.math.fractions.Fraction;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;

import jmul.test.classification.ManualTest;

import org.junit.Ignore;


/**
 * A manual test to calculate euler's number.
 *
 */
@Ignore
@ManualTest
public class EulersNumberTest {

    public static void main(String... args) {

        int base = 10;
        Number counter = createNumber(base, "1");

        Fraction result = null;
        for (int a = 0; a < 20; a++) {

            result = g(counter);
            Number resolved = result.evaluate();

            String info = String.format("f(%s) = %s -> %s", counter, result, resolved);
            System.out.println(info);

            counter = counter.inc();
        }
    }

    private static Fraction f(Number x) {

        int base = x.base();
        final Number ONE = createNumber(base, "1");

        return (ONE.add(x.reciprocal())).exponentiate(x);
    }

    private static Fraction g(Number x) {

        if (x.isZero()) {

            return (x.factorial()).reciprocal();
        }

        return ((x.factorial()).reciprocal()).add(g(x.dec()));
    }

}
