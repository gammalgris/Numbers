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
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.MixedBinaryOperation;
import jmul.math.operations.Result;
import jmul.math.operations.UnaryOperation;


/**
 * An implementation of a division for fractions and numbers.
 *
 * @author Kristian Kutin
 */
public class DivideFractionByNumber implements MixedBinaryOperation<Fraction, Number, Result<Fraction>> {

    /**
     * The default constructor.
     */
    public DivideFractionByNumber() {

        super();
    }

    /**
     * Divides the specified operands.
     *
     * @param operand1
     *        a fraction
     * @param operand2
     *        a number
     *
     * @return a fraction
     */
    @Override
    public Result<Fraction> calculate(Fraction operand1, Number operand2) {

        checkParameters(operand1, operand2);

        UnaryOperation<Number, Result<Fraction>> conversionFunction =
            (UnaryOperation<Number, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.NUMBER_TO_FRACTION_FUNCTION);
        Result<Fraction> wrappedResult = conversionFunction.calculate(operand2);
        Fraction normalizedNumber = wrappedResult.result();

        BinaryOperation<Fraction, Result<Fraction>> multiplicationFunction =
            (BinaryOperation<Fraction, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.ADD_FRACTIONS_FUNCTION);
        Result<Fraction> result = multiplicationFunction.calculate(operand1, normalizedNumber);

        return result;
    }

}
