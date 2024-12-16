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

package jmul.math.functions;


import jmul.math.fraction.functions.FractionComparator;
import jmul.math.fraction.functions.FractionEquality;
import jmul.math.fraction.functions.FractionGreaterComparison;
import jmul.math.fraction.functions.FractionGreaterOrEqualComparison;
import jmul.math.fraction.functions.FractionLesserComparison;
import jmul.math.fraction.functions.FractionLesserOrEqualComparison;
import jmul.math.numbers.FunctionIdentifiers;
import jmul.math.numbers.functions.AddDigits;
import jmul.math.numbers.functions.AddNumbers;
import jmul.math.numbers.functions.DecrementNumber;
import jmul.math.numbers.functions.DigitComparator;
import jmul.math.numbers.functions.DigitComplement;
import jmul.math.numbers.functions.DigitEquality;
import jmul.math.numbers.functions.DivisionBySubtraction;
import jmul.math.numbers.functions.DoublingNumber;
import jmul.math.numbers.functions.EvenNumberFunction;
import jmul.math.numbers.functions.HalvingDigit;
import jmul.math.numbers.functions.HalvingNumber;
import jmul.math.numbers.functions.IncrementNumber;
import jmul.math.numbers.functions.MaxFunction;
import jmul.math.numbers.functions.MinFunction;
import jmul.math.numbers.functions.MultiplyNumbers;
import jmul.math.numbers.functions.NegateNumber;
import jmul.math.numbers.functions.NumberComparator;
import jmul.math.numbers.functions.NumberComplement;
import jmul.math.numbers.functions.NumberEquality;
import jmul.math.numbers.functions.NumberGreaterComparison;
import jmul.math.numbers.functions.NumberGreaterOrEqualComparison;
import jmul.math.numbers.functions.NumberLesserComparison;
import jmul.math.numbers.functions.NumberLesserOrEqualComparison;
import jmul.math.numbers.functions.OddNumberFunction;
import jmul.math.numbers.functions.ShiftLeft;
import jmul.math.numbers.functions.ShiftRight;
import jmul.math.numbers.functions.SubtractNumbers;
import jmul.math.numbers.functions.TruncateNumber;
import jmul.math.numbers.notations.ScientificNotationFunctionImpl;
import jmul.math.numbers.notations.ScientificNotationParserImpl;
import jmul.math.numbers.notations.StandardNotationFunctionImpl;
import jmul.math.numbers.notations.StandardNotationParserImpl;


/**
 * An implementation of a function repository initializer.
 *
 *  @author Kristian Kutin
 */
public class FunctionRepositoryInitializerImpl implements FunctionRepositoryInitializer {

    /**
     * The default constructor.
     */
    public FunctionRepositoryInitializerImpl() {

        super();
    }

    /**
     * Returns an initialized function repository.
     *
     * @return a function repository
     */
    public FunctionRepository init() {

        FunctionRepository repository = new FunctionRepositoryImpl();

        repository.registerFunction(FunctionIdentifiers.DIGIT_COMPARATOR_FUNCTION, DigitComparator.class);
        repository.registerFunction(FunctionIdentifiers.DIGIT_EQUALITY_FUNCTION, DigitEquality.class);

        repository.registerFunction(FunctionIdentifiers.STANDARD_NOTATION_PARSER, StandardNotationParserImpl.class);
        repository.registerFunction(FunctionIdentifiers.SCIENTIFIC_NOTATION_PARSER, ScientificNotationParserImpl.class);

        repository.registerFunction(FunctionIdentifiers.SCIENTIFIC_NOTATION_FUNCTION,
                                    ScientificNotationFunctionImpl.class);
        repository.registerFunction(FunctionIdentifiers.STANDARD_NOTATION_FUNCTION, StandardNotationFunctionImpl.class);

        repository.registerFunction(FunctionIdentifiers.NEGATE_NUMBER_FUNCTION, NegateNumber.class);
        repository.registerFunction(FunctionIdentifiers.NUMBER_COMPLEMENT_FUNCTION, NumberComplement.class);

        repository.registerFunction(FunctionIdentifiers.NUMBER_COMPARATOR_FUNCTION, NumberComparator.class);
        repository.registerFunction(FunctionIdentifiers.NUMBER_EQUALITY_FUNCTION, NumberEquality.class);

        repository.registerFunction(FunctionIdentifiers.FRACTION_COMPARATOR_FUNCTION, FractionComparator.class);
        repository.registerFunction(FunctionIdentifiers.FRACTION_EQUALITY_FUNCTION, FractionEquality.class);

        repository.registerFunction(FunctionIdentifiers.ADD_DIGITS_FUNCTION, AddDigits.class);
        repository.registerFunction(FunctionIdentifiers.DIGIT_COMPLEMENT_FUNCTION, DigitComplement.class);
        repository.registerFunction(FunctionIdentifiers.HALVING_DIGIT_FUNCTION, HalvingDigit.class);

        repository.registerFunction(FunctionIdentifiers.ADD_NUMBERS_FUNCTION, AddNumbers.class);
        repository.registerFunction(FunctionIdentifiers.SUBTRACT_NUMBERS_FUNCTION, SubtractNumbers.class);

        repository.registerFunction(FunctionIdentifiers.SHIFT_LEFT_FUNCTION, ShiftLeft.class);
        repository.registerFunction(FunctionIdentifiers.SHIFT_RIGHT_FUNCTION, ShiftRight.class);
        repository.registerFunction(FunctionIdentifiers.TRUNCATE_NUMBER_FUNCTION, TruncateNumber.class);

        repository.registerFunction(FunctionIdentifiers.DOUBLING_NUMBER_FUNCTION, DoublingNumber.class);
        repository.registerFunction(FunctionIdentifiers.HALVING_NUMBER_FUNCTION, HalvingNumber.class);

        repository.registerFunction(FunctionIdentifiers.MAX_NUMBER_FUNCTION, MaxFunction.class);
        repository.registerFunction(FunctionIdentifiers.MIN_NUMBER_FUNCTION, MinFunction.class);

        repository.registerFunction(FunctionIdentifiers.NUMBER_INCREMENT_FUNCTION, IncrementNumber.class);
        repository.registerFunction(FunctionIdentifiers.NUMBER_DECREMENT_FUNCTION, DecrementNumber.class);

        repository.registerFunction(FunctionIdentifiers.EVEN_NUMBER_FUNCTION, EvenNumberFunction.class);
        repository.registerFunction(FunctionIdentifiers.ODD_NUMBER_FUNCTION, OddNumberFunction.class);

        repository.registerFunction(FunctionIdentifiers.NUMBER_GREATER_COMPARISON, NumberGreaterComparison.class);
        repository.registerFunction(FunctionIdentifiers.NUMBER_GREATER_OR_EQUAL_COMPARISON,
                                    NumberGreaterOrEqualComparison.class);
        repository.registerFunction(FunctionIdentifiers.NUMBER_LESSER_COMPARISON, NumberLesserComparison.class);
        repository.registerFunction(FunctionIdentifiers.NUMBER_LESSER_OR_EQUAL_COMPARISON,
                                    NumberLesserOrEqualComparison.class);

        repository.registerFunction(FunctionIdentifiers.FRACTION_GREATER_COMPARISON,
                                    FractionGreaterComparison.class);
        repository.registerFunction(FunctionIdentifiers.FRACTION_GREATER_OR_EQUAL_COMPARISON,
                                    FractionGreaterOrEqualComparison.class);
        repository.registerFunction(FunctionIdentifiers.FRACTION_LESSER_COMPARISON, FractionLesserComparison.class);
        repository.registerFunction(FunctionIdentifiers.FRACTION_LESSER_OR_EQUAL_COMPARISON,
                                    FractionLesserOrEqualComparison.class);

        repository.registerFunction(FunctionIdentifiers.MULTIPLY_NUMBERS_FUNCTION, MultiplyNumbers.class);
        repository.registerFunction(FunctionIdentifiers.DIVIDE_NUMBERS_FUNCTION, DivisionBySubtraction.class);

        return repository;
    }

}
