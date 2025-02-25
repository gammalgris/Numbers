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

package jmul.math.functions.implementations;


import jmul.math.fractions.Fraction;
import jmul.math.functions.FunctionSingletons;
import static jmul.math.functions.implementations.ParameterCheckHelper.checkParameters;
import jmul.math.functions.repository.FunctionIdentifiers;
import jmul.math.numbers.Number;
import jmul.math.operations.MixedBinaryOperation;
import jmul.math.operations.Result;
import jmul.math.operations.UnaryOperation;


/**
 * An implementation of a max function.
 *
 * @author Kristian Kutin
 */
public class MaxFractionNumber implements MixedBinaryOperation<Fraction, Number, Result<Fraction>> {

    /**
     * The default constructor.
     */
    public MaxFractionNumber() {

        super();
    }

    /**
     * Returns the higher of the two parameters. The specified number is converted into a fraction.
     *
     * @param operand1
     *        a fraction
     * @param operand2
     *        a number
     *
     * @return the higher of the two parameters
     */
    @Override
    public Result<Fraction> calculate(Fraction operand1, Number operand2) {

        checkParameters(operand1, operand2);

        UnaryOperation<Number, Result<Fraction>> conversionFunction =
            (UnaryOperation<Number, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.NUMBER_TO_FRACTION_FUNCTION);
        Result<Fraction> wrappedResult = conversionFunction.calculate(operand2);
        Fraction normalizedNumber = wrappedResult.result();

        Fraction result = operand1.max(normalizedNumber);
        return new Result<Fraction>(result);
    }

}
