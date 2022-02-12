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


import java.util.ArrayList;
import java.util.Collection;

import jmul.math.numbers.digits.BinaryDigits;
import jmul.math.numbers.digits.DecimalDigits;
import jmul.math.numbers.digits.Digit;
import jmul.math.numbers.digits.HexadecimalDigits;
import jmul.math.numbers.system.NumeralSystems;
import jmul.math.numbers.digits.OctalDigits;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests identifying digits by their ordinal value.
 *
 * @author Kristian Kutin
 */
@RunWith(Parameterized.class)
public class OrdinalToDigitValidParametersTest {

    private final int base;

    private final int ordinal;

    private final Digit expectedDigit;

    public OrdinalToDigitValidParametersTest(int base, int ordinal, Digit expectedDigit) {

        super();

        this.base = base;
        this.ordinal = ordinal;
        this.expectedDigit = expectedDigit;
    }

    @Test
    public void testOrdinalToDigit() {

        Digit actualDigit = NumeralSystems.ordinalToDigit(base, ordinal);

        String message1 =
            String.format("The base doesn't match (expected=%d; actual=%d)!", expectedDigit.base(), actualDigit.base());
        assertEquals(message1, expectedDigit.base(), actualDigit.base());

        String message2 =
            String.format("The ordinal doesn't match (expected=%d; actual=%d)!", expectedDigit.ordinal(),
                          actualDigit.ordinal());
        assertEquals(message2, expectedDigit.ordinal(), actualDigit.ordinal());

        String message3 = String.format("The digits don't match!");
        assertEquals(message3, expectedDigit, actualDigit);
    }

    /**
     * Returns a matrix of test data and expected results.
     *
     * @return a matrix of test data and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { 2, 0, BinaryDigits.ZERO });
        parameters.add(new Object[] { 2, 1, BinaryDigits.ONE });

        parameters.add(new Object[] { 8, 0, OctalDigits.ZERO });
        parameters.add(new Object[] { 8, 1, OctalDigits.ONE });
        parameters.add(new Object[] { 8, 2, OctalDigits.TWO });
        parameters.add(new Object[] { 8, 3, OctalDigits.THREE });
        parameters.add(new Object[] { 8, 4, OctalDigits.FOUR });
        parameters.add(new Object[] { 8, 5, OctalDigits.FIVE });
        parameters.add(new Object[] { 8, 6, OctalDigits.SIX });
        parameters.add(new Object[] { 8, 7, OctalDigits.SEVEN });

        parameters.add(new Object[] { 10, 0, DecimalDigits.ZERO });
        parameters.add(new Object[] { 10, 1, DecimalDigits.ONE });
        parameters.add(new Object[] { 10, 2, DecimalDigits.TWO });
        parameters.add(new Object[] { 10, 3, DecimalDigits.THREE });
        parameters.add(new Object[] { 10, 4, DecimalDigits.FOUR });
        parameters.add(new Object[] { 10, 5, DecimalDigits.FIVE });
        parameters.add(new Object[] { 10, 6, DecimalDigits.SIX });
        parameters.add(new Object[] { 10, 7, DecimalDigits.SEVEN });
        parameters.add(new Object[] { 10, 8, DecimalDigits.EIGHT });
        parameters.add(new Object[] { 10, 9, DecimalDigits.NINE });

        parameters.add(new Object[] { 16, 0, HexadecimalDigits.ZERO });
        parameters.add(new Object[] { 16, 1, HexadecimalDigits.ONE });
        parameters.add(new Object[] { 16, 2, HexadecimalDigits.TWO });
        parameters.add(new Object[] { 16, 3, HexadecimalDigits.THREE });
        parameters.add(new Object[] { 16, 4, HexadecimalDigits.FOUR });
        parameters.add(new Object[] { 16, 5, HexadecimalDigits.FIVE });
        parameters.add(new Object[] { 16, 6, HexadecimalDigits.SIX });
        parameters.add(new Object[] { 16, 7, HexadecimalDigits.SEVEN });
        parameters.add(new Object[] { 16, 8, HexadecimalDigits.EIGHT });
        parameters.add(new Object[] { 16, 9, HexadecimalDigits.NINE });
        parameters.add(new Object[] { 16, 10, HexadecimalDigits.TEN });
        parameters.add(new Object[] { 16, 11, HexadecimalDigits.ELEVEN });
        parameters.add(new Object[] { 16, 12, HexadecimalDigits.TWELVE });
        parameters.add(new Object[] { 16, 13, HexadecimalDigits.THIRTEEN });
        parameters.add(new Object[] { 16, 14, HexadecimalDigits.FOURTEEN });
        parameters.add(new Object[] { 16, 15, HexadecimalDigits.FIFTEEN });

        return parameters;
    }

}
