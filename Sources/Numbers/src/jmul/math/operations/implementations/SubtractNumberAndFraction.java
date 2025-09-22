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

package jmul.math.operations.implementations;


import jmul.math.fractions.Fraction;
import jmul.math.operations.OperationSingletons;
import static jmul.math.operations.implementations.ParameterCheckHelper.checkParameters;
import jmul.math.operations.repository.OperationIdentifiers;
import jmul.math.numbers.Number;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.MixedBinaryOperation;
import jmul.math.operations.Result;
import jmul.math.operations.UnaryOperation;


/**
 * This class implements a function for subtracting a number and a fraction.
 *
 * @author Kristian Kutin
 */
public class SubtractNumberAndFraction implements MixedBinaryOperation<Number, Fraction, Result<Fraction>> {

    /**
     * The default constructor.
     */
    public SubtractNumberAndFraction() {

        super();
    }

    /**
     * Subtracts the specified operands.
     *
     * @param operand1
     *        a number
     * @param operand2
     *        a fraction
     *
     * @return a fraction
     */
    @Override
    public Result<Fraction> calculate(Number operand1, Fraction operand2) {

        checkParameters(operand1, operand2);

        UnaryOperation<Number, Result<Fraction>> conversionFunction =
            (UnaryOperation<Number, Result<Fraction>>) OperationSingletons.getFunction(OperationIdentifiers.NUMBER_TO_FRACTION_FUNCTION);
        Result<Fraction> wrappedResult = conversionFunction.calculate(operand1);
        Fraction normalizedNumber = wrappedResult.result();

        BinaryOperation<Fraction, Result<Fraction>> multiplicationFunction =
            (BinaryOperation<Fraction, Result<Fraction>>) OperationSingletons.getFunction(OperationIdentifiers.SUBTRACT_FRACTIONS_FUNCTION);
        Result<Fraction> result = multiplicationFunction.calculate(normalizedNumber, operand2);

        return result;
    }

}
