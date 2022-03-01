/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2022  Kristian Kutin
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


import java.math.BigDecimal;

import java.util.Arrays;

import jmul.math.numbers.RealDecimalNumber;

import jmul.math.numbers.digits.HexadecimalDigits;

import jmul.math.numbers.digits.OctalDigits;

import jmul.math.numbers.builders.AdditionOperation;

import jmul.math.numbers.operations.AdditionOperationImpl;

import memutils.ObjectSizeFetcher;

public class MiscTest {

    public static void main(String... args) {

        /*int i = Integer.MIN_VALUE;
        System.out.println(i);
        int j = i * -1;
        System.out.println(j);
        System.out.println("");

        float f = 3.1234123467821356678789900111111111111111111111456F;
        System.out.println(f);
        System.out.println(Math.round(f));
        System.out.println(Math.round(f+0.5F));

        float left = Math.round(f);
        float right = f - left;
        System.out.println(left);
        System.out.println(right);

        System.out.println("Float.MAX_VALUE=" + Float.MAX_VALUE);
        System.out.println("Double.MAX_VALUE=" + Double.MAX_VALUE);
        System.out.println("Double.MAX_EXPONENT=" + Double.MAX_EXPONENT);
        System.out.println("Double.MIN_EXPONENT=" + Double.MIN_EXPONENT);

        //int[] a = new int[] {};

        //System.out.println(a[0]);
        */

        /*long beforeUsedMem;
        long afterUsedMem;

        beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        RealNumber n1 = new RealNumber("1.2304563211E-20");
        afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println(afterUsedMem - beforeUsedMem);

        beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        BigDecimal n2 = new BigDecimal("0.00000000000000000012304563211");
        afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println(afterUsedMem - beforeUsedMem);

        beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        RealNumber n3 = new RealNumber("1.230456321100000001E-80");
        afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println(afterUsedMem - beforeUsedMem);

        beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        BigDecimal n4 =
            new BigDecimal("0.00000000000000000000000000000000000000000000000000000000000000000000000000000012304563211");
        afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println(afterUsedMem - beforeUsedMem);

        System.out.println("-- n1 --");
        System.out.println(n1);
        System.out.println(ObjectSizeFetcher.getObjectSize(n1));

        System.out.println("-- n2 --");
        System.out.println(n2);
        System.out.println(ObjectSizeFetcher.getObjectSize(n2));

        System.out.println("-- n3 --");
        System.out.println(n3);
        System.out.println(ObjectSizeFetcher.getObjectSize(n3));

        System.out.println("-- n4 --");
        System.out.println(n4);
        System.out.println(ObjectSizeFetcher.getObjectSize(n4));

        System.out.println();

        System.out.println(HexadecimalDigits.ZERO.equals(OctalDigits.ZERO));*/
        
        int[] a = new int[] { 1, 2, 3 };
        int[] b = a.clone();

        int c = b[0];
        b[0] = b[2];
        b[2] = c;

        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));

        AdditionOperation<RealDecimalNumber> additionFunction = new AdditionOperationImpl<>();
        RealDecimalNumber n = new RealDecimalNumber(1);
        RealDecimalNumber m = new RealDecimalNumber(2);
        RealDecimalNumber r = additionFunction.add(n, m);
        System.out.println("n:="+ n);
        System.out.println("m:="+ m);
        System.out.println("r:="+ r);

        m = RealDecimalNumber.POSITIVE_INFINITY;
        r = additionFunction.add(n, m);
        System.out.println("n:="+ n);
        System.out.println("m:="+ m);
        System.out.println("r:="+ r);
    }

}
