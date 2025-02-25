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

package jmul.math.functions.implementations.comparisons;


import jmul.math.fractions.Fraction;
import jmul.math.functions.Function;
import jmul.math.functions.FunctionSingletons;
import jmul.math.functions.implementations.ComparatorBase;
import static jmul.math.functions.implementations.ParameterCheckHelper.checkParameters;
import jmul.math.functions.repository.FunctionIdentifiers;
import jmul.math.numbers.Number;
import jmul.math.operations.MixedComparator;
import jmul.math.operations.Result;
import jmul.math.operations.UnaryOperation;


/**
 * A custom implementation of a comparator that compares a fraction with a number.
 *
 * @author Kristian Kutin
 */
public class FractionNumberComparator extends ComparatorBase implements Function, MixedComparator<Fraction, Number> {

    /**
     * The default constructor.
     */
    public FractionNumberComparator() {

        super();
    }

    /**
     * Compares the specified objects.
     *
     * @param f fraction
     *        a fraction
     * @param n number
     *        a number
     *
     * @return <code>1</code> if the specified fraction is greater than the specified number, <code>0</code> if the
     *         specified fraction and the specified number are considered equal or <code>-1</code> if the specified
     *         fraction is lesser than the specified number
     */
    @Override
    public int compare(Fraction f, Number n) {

        checkParameters(f, n);

        UnaryOperation<Number, Result<Fraction>> function =
            (UnaryOperation<Number, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.NUMBER_TO_FRACTION_FUNCTION);
        Result<Fraction> wrappedResult = function.calculate(n);
        Fraction normalizedNumber = wrappedResult.result();

        return f.compareTo(normalizedNumber);
    }

}
