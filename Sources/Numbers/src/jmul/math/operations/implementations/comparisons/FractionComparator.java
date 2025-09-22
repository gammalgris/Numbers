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

package jmul.math.operations.implementations.comparisons;


import java.util.Comparator;

import jmul.math.operations.implementations.ComparatorBase;
import static jmul.math.operations.implementations.ParameterCheckHelper.checkParameters;
import jmul.math.fractions.Fraction;
import jmul.math.operations.Operation;
import jmul.math.numbers.Number;


/**
 * An implementation of an natural ordering comparator for fractions.
 *
 * @author Kristian Kutin
 */
public class FractionComparator extends ComparatorBase implements Operation, Comparator<Fraction> {

    /**
     * The default constructor.
     */
    public FractionComparator() {

        super();
    }

    /**
     * Compares the two specified fractions regarding their natural order.
     *
     * @param fraction1
     *        a fraction
     * @param fraction2
     *        a fraction
     *
     * @return <code>1</code>, <code>0</code> or <code>-1</code> if the first number is greater than,
     *         equals or lesser than the second number.
     */
    @Override
    public int compare(Fraction fraction1, Fraction fraction2) {

        // Check the references
        checkParameters(fraction1, fraction2);

        if (fraction1 == fraction2) {

            return EQUALS;
        }

        // Check the expressions by sign
        if (fraction1.isPositive() && fraction2.isNegative()) {

            return GREATER_THAN;

        } else if (fraction1.isNegative() && fraction2.isPositive()) {

            return LESSER_THAN;
        }

        // Normalize the fractions
        Fraction normalizedFraction1 = fraction1.normalizedFraction();
        Fraction normalizedFraction2 = fraction2.normalizedFraction();

        Number normalizedNumerator1 = normalizedFraction1.numerator().multiply(normalizedFraction2.denominator());
        Number normalizedNumerator2 = normalizedFraction2.numerator().multiply(normalizedFraction1.denominator());

        return normalizedNumerator1.compareTo(normalizedNumerator2);
    }

}


