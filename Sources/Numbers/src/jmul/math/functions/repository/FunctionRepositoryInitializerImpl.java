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

package jmul.math.functions.repository;


import jmul.math.functions.implementations.AddDigits;
import jmul.math.functions.implementations.AddNumbers;
import jmul.math.functions.implementations.AddNumbersTrimResult;
import jmul.math.functions.implementations.DecrementFraction;
import jmul.math.functions.implementations.DecrementNumber;
import jmul.math.functions.implementations.DigitComplement;
import jmul.math.functions.implementations.DivisionBySubtraction;
import jmul.math.functions.implementations.DoublingNumber;
import jmul.math.functions.implementations.EvenNumberFunction;
import jmul.math.functions.implementations.HalvingDigit;
import jmul.math.functions.implementations.HalvingNumber;
import jmul.math.functions.implementations.IncrementFraction;
import jmul.math.functions.implementations.IncrementNumber;
import jmul.math.functions.implementations.MaxFraction;
import jmul.math.functions.implementations.MaxNumber;
import jmul.math.functions.implementations.MinFraction;
import jmul.math.functions.implementations.MinNumber;
import jmul.math.functions.implementations.MultiplyNumbers;
import jmul.math.functions.implementations.NegateFraction;
import jmul.math.functions.implementations.NegateNumber;
import jmul.math.functions.implementations.NumberComplement;
import jmul.math.functions.implementations.NumberToFraction;
import jmul.math.functions.implementations.OddNumberFunction;
import jmul.math.functions.implementations.ShiftLeft;
import jmul.math.functions.implementations.ShiftRight;
import jmul.math.functions.implementations.SubtractNumbers;
import jmul.math.functions.implementations.TruncateNumber;
import jmul.math.functions.implementations.comparisons.DigitComparator;
import jmul.math.functions.implementations.comparisons.FractionComparator;
import jmul.math.functions.implementations.comparisons.FractionGreaterThanComparison;
import jmul.math.functions.implementations.comparisons.FractionGreaterThanNumberComparison;
import jmul.math.functions.implementations.comparisons.FractionGreaterThanOrEqualComparison;
import jmul.math.functions.implementations.comparisons.FractionGreaterThanOrEqualNumberComparison;
import jmul.math.functions.implementations.comparisons.FractionLesserThanComparison;
import jmul.math.functions.implementations.comparisons.FractionLesserThanNumberComparison;
import jmul.math.functions.implementations.comparisons.FractionLesserThanOrEqualComparison;
import jmul.math.functions.implementations.comparisons.FractionLesserThanOrEqualNumberComparison;
import jmul.math.functions.implementations.comparisons.FractionNumberComparator;
import jmul.math.functions.implementations.comparisons.NumberComparator;
import jmul.math.functions.implementations.comparisons.NumberFractionComparator;
import jmul.math.functions.implementations.comparisons.NumberGreaterThanComparison;
import jmul.math.functions.implementations.comparisons.NumberGreaterThanFractionComparison;
import jmul.math.functions.implementations.comparisons.NumberGreaterThanOrEqualComparison;
import jmul.math.functions.implementations.comparisons.NumberGreaterThanOrEqualFractionComparison;
import jmul.math.functions.implementations.comparisons.NumberLesserThanComparison;
import jmul.math.functions.implementations.comparisons.NumberLesserThanFractionComparison;
import jmul.math.functions.implementations.comparisons.NumberLesserThanOrEqualComparison;
import jmul.math.functions.implementations.comparisons.NumberLesserThanOrEqualFractionComparison;
import jmul.math.functions.implementations.equality.DigitEquality;
import jmul.math.functions.implementations.equality.FractionEquality;
import jmul.math.functions.implementations.equality.FractionNumberEquality;
import jmul.math.functions.implementations.equality.NumberEquality;
import jmul.math.functions.implementations.equality.NumberFractionEquality;
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

        repository.registerFunction(FunctionIdentifiers.NUMBER_TO_FRACTION_FUNCTION, NumberToFraction.class);

        repository.registerFunction(FunctionIdentifiers.DIGIT_COMPARATOR_FUNCTION, DigitComparator.class);
        repository.registerFunction(FunctionIdentifiers.DIGIT_EQUALITY_FUNCTION, DigitEquality.class);

        repository.registerFunction(FunctionIdentifiers.STANDARD_NOTATION_PARSER, StandardNotationParserImpl.class);
        repository.registerFunction(FunctionIdentifiers.SCIENTIFIC_NOTATION_PARSER, ScientificNotationParserImpl.class);

        repository.registerFunction(FunctionIdentifiers.SCIENTIFIC_NOTATION_FUNCTION,
                                    ScientificNotationFunctionImpl.class);
        repository.registerFunction(FunctionIdentifiers.STANDARD_NOTATION_FUNCTION, StandardNotationFunctionImpl.class);

        repository.registerFunction(FunctionIdentifiers.NEGATE_NUMBER_FUNCTION, NegateNumber.class);
        repository.registerFunction(FunctionIdentifiers.NEGATE_FRACTION_FUNCTION, NegateFraction.class);
        repository.registerFunction(FunctionIdentifiers.NUMBER_COMPLEMENT_FUNCTION, NumberComplement.class);

        repository.registerFunction(FunctionIdentifiers.NUMBER_COMPARATOR_FUNCTION, NumberComparator.class);
        repository.registerFunction(FunctionIdentifiers.NUMBER_FRACTION_COMPARATOR_FUNCTION,
                                    NumberFractionComparator.class);
        repository.registerFunction(FunctionIdentifiers.NUMBER_EQUALITY_FUNCTION, NumberEquality.class);
        repository.registerFunction(FunctionIdentifiers.NUMBER_FRACTION_EQUALITY_FUNCTION,
                                    NumberFractionEquality.class);

        repository.registerFunction(FunctionIdentifiers.FRACTION_COMPARATOR_FUNCTION, FractionComparator.class);
        repository.registerFunction(FunctionIdentifiers.FRACTION_NUMBER_COMPARATOR_FUNCTION,
                                    FractionNumberComparator.class);
        repository.registerFunction(FunctionIdentifiers.FRACTION_EQUALITY_FUNCTION, FractionEquality.class);
        repository.registerFunction(FunctionIdentifiers.FRACTION_NUMBER_EQUALITY_FUNCTION,
                                    FractionNumberEquality.class);

        repository.registerFunction(FunctionIdentifiers.ADD_DIGITS_FUNCTION, AddDigits.class);
        repository.registerFunction(FunctionIdentifiers.DIGIT_COMPLEMENT_FUNCTION, DigitComplement.class);
        repository.registerFunction(FunctionIdentifiers.HALVING_DIGIT_FUNCTION, HalvingDigit.class);

        repository.registerFunction(FunctionIdentifiers.ADD_NUMBERS_FUNCTION, AddNumbers.class);
        repository.registerFunction(FunctionIdentifiers.ADD_NUMBERS_TRIM_RESULT_FUNCTION, AddNumbersTrimResult.class);
        repository.registerFunction(FunctionIdentifiers.SUBTRACT_NUMBERS_FUNCTION, SubtractNumbers.class);

        repository.registerFunction(FunctionIdentifiers.SHIFT_LEFT_FUNCTION, ShiftLeft.class);
        repository.registerFunction(FunctionIdentifiers.SHIFT_RIGHT_FUNCTION, ShiftRight.class);
        repository.registerFunction(FunctionIdentifiers.TRUNCATE_NUMBER_FUNCTION, TruncateNumber.class);

        repository.registerFunction(FunctionIdentifiers.DOUBLING_NUMBER_FUNCTION, DoublingNumber.class);
        repository.registerFunction(FunctionIdentifiers.HALVING_NUMBER_FUNCTION, HalvingNumber.class);

        repository.registerFunction(FunctionIdentifiers.MAX_NUMBER_FUNCTION, MaxNumber.class);
        repository.registerFunction(FunctionIdentifiers.MAX_FRACTION_FUNCTION, MaxFraction.class);
        repository.registerFunction(FunctionIdentifiers.MIN_NUMBER_FUNCTION, MinNumber.class);
        repository.registerFunction(FunctionIdentifiers.MIN_FRACTION_FUNCTION, MinFraction.class);

        repository.registerFunction(FunctionIdentifiers.NUMBER_INCREMENT_FUNCTION, IncrementNumber.class);
        repository.registerFunction(FunctionIdentifiers.FRACTION_INCREMENT_FUNCTION, IncrementFraction.class);
        repository.registerFunction(FunctionIdentifiers.NUMBER_DECREMENT_FUNCTION, DecrementNumber.class);
        repository.registerFunction(FunctionIdentifiers.FRACTION_DECREMENT_FUNCTION, DecrementFraction.class);

        repository.registerFunction(FunctionIdentifiers.EVEN_NUMBER_FUNCTION, EvenNumberFunction.class);
        repository.registerFunction(FunctionIdentifiers.ODD_NUMBER_FUNCTION, OddNumberFunction.class);

        repository.registerFunction(FunctionIdentifiers.NUMBER_GREATER_THAN_COMPARISON_FUNCTION,
                                    NumberGreaterThanComparison.class);
        repository.registerFunction(FunctionIdentifiers.NUMBER_GREATER_THAN_FRACTION_COMPARISON_FUNCTION,
                                    NumberGreaterThanFractionComparison.class);
        repository.registerFunction(FunctionIdentifiers.NUMBER_GREATER_THAN_OR_EQUAL_COMPARISON_FUNCTION,
                                    NumberGreaterThanOrEqualComparison.class);
        repository.registerFunction(FunctionIdentifiers.NUMBER_GREATER_THAN_OR_EQUAL_FRACTION_COMPARISON_FUNCTION,
                                    NumberGreaterThanOrEqualFractionComparison.class);
        repository.registerFunction(FunctionIdentifiers.NUMBER_LESSER_THAN_COMPARISON_FUNCTION,
                                    NumberLesserThanComparison.class);
        repository.registerFunction(FunctionIdentifiers.NUMBER_LESSER_THAN_FRACTION_COMPARISON_FUNCTION,
                                    NumberLesserThanFractionComparison.class);
        repository.registerFunction(FunctionIdentifiers.NUMBER_LESSER_THAN_OR_EQUAL_COMPARISON_FUNCTION,
                                    NumberLesserThanOrEqualComparison.class);
        repository.registerFunction(FunctionIdentifiers.NUMBER_LESSER_THAN_OR_EQUAL_FRACTION_COMPARISON_FUNCTION,
                                    NumberLesserThanOrEqualFractionComparison.class);

        repository.registerFunction(FunctionIdentifiers.FRACTION_GREATER_THAN_COMPARISON_FUNCTION,
                                    FractionGreaterThanComparison.class);
        repository.registerFunction(FunctionIdentifiers.FRACTION_GREATER_THAN_NUMBER_COMPARISON_FUNCTION,
                                    FractionGreaterThanNumberComparison.class);
        repository.registerFunction(FunctionIdentifiers.FRACTION_GREATER_THAN_OR_EQUAL_COMPARISON_FUNCTION,
                                    FractionGreaterThanOrEqualComparison.class);
        repository.registerFunction(FunctionIdentifiers.FRACTION_GREATER_THAN_OR_EQUAL_NUMBER_COMPARISON_FUNCTION,
                                    FractionGreaterThanOrEqualNumberComparison.class);
        repository.registerFunction(FunctionIdentifiers.FRACTION_LESSER_THAN_COMPARISON_FUNCTION,
                                    FractionLesserThanComparison.class);
        repository.registerFunction(FunctionIdentifiers.FRACTION_LESSER_THAN_NUMBER_COMPARISON_FUNCTION,
                                    FractionLesserThanNumberComparison.class);
        repository.registerFunction(FunctionIdentifiers.FRACTION_LESSER_THAN_OR_EQUAL_COMPARISON_FUNCTION,
                                    FractionLesserThanOrEqualComparison.class);
        repository.registerFunction(FunctionIdentifiers.FRACTION_LESSER_THAN_OR_EQUAL_NUMBER_COMPARISON_FUNCTION,
                                    FractionLesserThanOrEqualNumberComparison.class);

        repository.registerFunction(FunctionIdentifiers.MULTIPLY_NUMBERS_FUNCTION, MultiplyNumbers.class);
        repository.registerFunction(FunctionIdentifiers.DIVIDE_NUMBERS_FUNCTION, DivisionBySubtraction.class);

        return repository;
    }

}
