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

package jmul.math.operations.repository;


import jmul.math.numbers.notations.ScientificNotationFunctionImpl;
import jmul.math.numbers.notations.ScientificNotationParserImpl;
import jmul.math.numbers.notations.StandardNotationFunctionImpl;
import jmul.math.numbers.notations.StandardNotationParserImpl;
import jmul.math.operations.implementations.AddDigits;
import jmul.math.operations.implementations.AddFractionAndNumber;
import jmul.math.operations.implementations.AddFractions;
import jmul.math.operations.implementations.AddLogarithmsFunction;
import jmul.math.operations.implementations.AddMatrices;
import jmul.math.operations.implementations.AddNumberAndFraction;
import jmul.math.operations.implementations.AddNumbers;
import jmul.math.operations.implementations.AddNumbersTrimResult;
import jmul.math.operations.implementations.AddVectors;
import jmul.math.operations.implementations.ArchimedesPiApproximation;
import jmul.math.operations.implementations.BaseToNumber;
import jmul.math.operations.implementations.CrossProduct;
import jmul.math.operations.implementations.DecrementFraction;
import jmul.math.operations.implementations.DecrementNumber;
import jmul.math.operations.implementations.DetermineCommonDivisors;
import jmul.math.operations.implementations.DetermineCommonPrimeFactors;
import jmul.math.operations.implementations.DetermineDivisors;
import jmul.math.operations.implementations.DeterminePrimeFactors;
import jmul.math.operations.implementations.DigitComplement;
import jmul.math.operations.implementations.DigitToNumberConversion;
import jmul.math.operations.implementations.DivideFractionByNumber;
import jmul.math.operations.implementations.DivideFractions;
import jmul.math.operations.implementations.DivideNumberByFraction;
import jmul.math.operations.implementations.DivisionBySubtraction;
import jmul.math.operations.implementations.DivisionBySubtractionReturnFraction;
import jmul.math.operations.implementations.DivisionOfNumbersBySubtractionReturnResultAndRemainder;
import jmul.math.operations.implementations.DivisoWithIntegers;
import jmul.math.operations.implementations.DoublingFraction;
import jmul.math.operations.implementations.DoublingNumber;
import jmul.math.operations.implementations.DyadicProduct;
import jmul.math.operations.implementations.EulersNumberFunction;
import jmul.math.operations.implementations.EvaluateFraction;
import jmul.math.operations.implementations.EvenNumberCheck;
import jmul.math.operations.implementations.ExponentiateFractionWithNumber;
import jmul.math.operations.implementations.ExponentiateNumberWithFraction;
import jmul.math.operations.implementations.ExponentiateNumberWithNumber;
import jmul.math.operations.implementations.Factorial;
import jmul.math.operations.implementations.FractionToAbsoluteValue;
import jmul.math.operations.implementations.FractionWithinInterval;
import jmul.math.operations.implementations.HalvingDigit;
import jmul.math.operations.implementations.HalvingFraction;
import jmul.math.operations.implementations.HalvingNumber;
import jmul.math.operations.implementations.IncrementFraction;
import jmul.math.operations.implementations.IncrementNumber;
import jmul.math.operations.implementations.IsMultipleCheck;
import jmul.math.operations.implementations.IsPrimeCheck;
import jmul.math.operations.implementations.IsSingleDigitCheck;
import jmul.math.operations.implementations.LeibnizPiApproximation;
import jmul.math.operations.implementations.LongMultiplication;
import jmul.math.operations.implementations.MatrixMultiplication;
import jmul.math.operations.implementations.MaxFraction;
import jmul.math.operations.implementations.MaxFractionNumber;
import jmul.math.operations.implementations.MaxNumber;
import jmul.math.operations.implementations.MaxNumberFraction;
import jmul.math.operations.implementations.MinFraction;
import jmul.math.operations.implementations.MinFractionNumber;
import jmul.math.operations.implementations.MinNumber;
import jmul.math.operations.implementations.MinNumberFraction;
import jmul.math.operations.implementations.ModuloWithIntegers;
import jmul.math.operations.implementations.MultiplicationByAddition;
import jmul.math.operations.implementations.MultiplyDigits;
import jmul.math.operations.implementations.MultiplyFractionAndNumber;
import jmul.math.operations.implementations.MultiplyFractions;
import jmul.math.operations.implementations.MultiplyNumberAndFraction;
import jmul.math.operations.implementations.MultiplyVectorWithNumber;
import jmul.math.operations.implementations.NegateFraction;
import jmul.math.operations.implementations.NegateNumber;
import jmul.math.operations.implementations.NthRoot;
import jmul.math.operations.implementations.NumberComplement;
import jmul.math.operations.implementations.NumberDigitSum;
import jmul.math.operations.implementations.NumberToAbsoluteValue;
import jmul.math.operations.implementations.NumberToDigit;
import jmul.math.operations.implementations.NumberToFraction;
import jmul.math.operations.implementations.NumberToOrdinal;
import jmul.math.operations.implementations.NumberWithinInterval;
import jmul.math.operations.implementations.OddNumberCheck;
import jmul.math.operations.implementations.RandomDigit;
import jmul.math.operations.implementations.RandomNumber;
import jmul.math.operations.implementations.RandomNumberWithinInterval;
import jmul.math.operations.implementations.RebaseFraction;
import jmul.math.operations.implementations.RebaseNumber;
import jmul.math.operations.implementations.ReciprocalOfFraction;
import jmul.math.operations.implementations.ReciprocalOfNumber;
import jmul.math.operations.implementations.ReduceFraction;
import jmul.math.operations.implementations.RemoveFractionPart;
import jmul.math.operations.implementations.RemoveIntegerPart;
import jmul.math.operations.implementations.RoundDigitToEven;
import jmul.math.operations.implementations.RoundDigitToOdd;
import jmul.math.operations.implementations.RoundDown;
import jmul.math.operations.implementations.RoundNumberToEven;
import jmul.math.operations.implementations.RoundNumberToOdd;
import jmul.math.operations.implementations.RoundUp;
import jmul.math.operations.implementations.RussianDivision;
import jmul.math.operations.implementations.RussianPeasantMultiplication;
import jmul.math.operations.implementations.ScalarProduct;
import jmul.math.operations.implementations.ShiftLeft;
import jmul.math.operations.implementations.ShiftRight;
import jmul.math.operations.implementations.SquareFraction;
import jmul.math.operations.implementations.SquareNumber;
import jmul.math.operations.implementations.SquareRoot;
import jmul.math.operations.implementations.SubtractFractionAndNumber;
import jmul.math.operations.implementations.SubtractFractions;
import jmul.math.operations.implementations.SubtractLogarithms;
import jmul.math.operations.implementations.SubtractMatrices;
import jmul.math.operations.implementations.SubtractNumberAndFraction;
import jmul.math.operations.implementations.SubtractNumbers;
import jmul.math.operations.implementations.SubtractVectors;
import jmul.math.operations.implementations.TransposeMatrix;
import jmul.math.operations.implementations.TripleProduct;
import jmul.math.operations.implementations.Vectorization;
import jmul.math.operations.implementations.comparisons.DigitComparator;
import jmul.math.operations.implementations.comparisons.FractionComparator;
import jmul.math.operations.implementations.comparisons.FractionGreaterThanComparison;
import jmul.math.operations.implementations.comparisons.FractionGreaterThanNumberComparison;
import jmul.math.operations.implementations.comparisons.FractionGreaterThanOrEqualComparison;
import jmul.math.operations.implementations.comparisons.FractionGreaterThanOrEqualNumberComparison;
import jmul.math.operations.implementations.comparisons.FractionLesserThanComparison;
import jmul.math.operations.implementations.comparisons.FractionLesserThanNumberComparison;
import jmul.math.operations.implementations.comparisons.FractionLesserThanOrEqualComparison;
import jmul.math.operations.implementations.comparisons.FractionLesserThanOrEqualNumberComparison;
import jmul.math.operations.implementations.comparisons.FractionNumberComparator;
import jmul.math.operations.implementations.comparisons.NumberComparator;
import jmul.math.operations.implementations.comparisons.NumberFractionComparator;
import jmul.math.operations.implementations.comparisons.NumberGreaterThanComparison;
import jmul.math.operations.implementations.comparisons.NumberGreaterThanFractionComparison;
import jmul.math.operations.implementations.comparisons.NumberGreaterThanOrEqualComparison;
import jmul.math.operations.implementations.comparisons.NumberGreaterThanOrEqualFractionComparison;
import jmul.math.operations.implementations.comparisons.NumberLesserThanComparison;
import jmul.math.operations.implementations.comparisons.NumberLesserThanFractionComparison;
import jmul.math.operations.implementations.comparisons.NumberLesserThanOrEqualComparison;
import jmul.math.operations.implementations.comparisons.NumberLesserThanOrEqualFractionComparison;
import jmul.math.operations.implementations.equality.DigitEquality;
import jmul.math.operations.implementations.equality.FractionEquality;
import jmul.math.operations.implementations.equality.FractionNumberEquality;
import jmul.math.operations.implementations.equality.MatrixEquality;
import jmul.math.operations.implementations.equality.NumberEquality;
import jmul.math.operations.implementations.equality.NumberFractionEquality;
import jmul.math.operations.implementations.equality.VectorEquality;


/**
 * An implementation of a function repository initializer.<br>
 * <br>
 * <i>Note:<br>
 * The current implementation relies on a static way to initialize the function repository (i.e.
 * to register function identifiers and a corresponding function implementation in the code). Because of
 * the dependencies there is a need to test changes before publishing the code. Thus the initialization
 * is only changeable in the code and there is no mechanism that relies on an external configuration file.
 * For the foreseeable future there is no intention to change this.</i>
 *
 *  @author Kristian Kutin
 */
public class OperationRepositoryInitializerImpl implements OperationRepositoryInitializer {

    /**
     * The default constructor.
     */
    public OperationRepositoryInitializerImpl() {

        super();
    }

    /**
     * Returns an initialized function repository.
     *
     * @return a function repository
     */
    public OperationRepository init() {

        OperationRepository repository = new OperationRepositoryImpl();

        repository.registerFunction(OperationIdentifiers.NUMBER_TO_ABSOLUTE_VALUE_FUNCTION,
                                    NumberToAbsoluteValue.class);
        repository.registerFunction(OperationIdentifiers.FRACTION_TO_ABSOLUTE_VALUE_FUNCTION,
                                    FractionToAbsoluteValue.class);

        repository.registerFunction(OperationIdentifiers.NUMBER_TO_FRACTION_FUNCTION, NumberToFraction.class);

        repository.registerFunction(OperationIdentifiers.BASE_TO_NUMBER_FUNCTION, BaseToNumber.class);
        repository.registerFunction(OperationIdentifiers.NUMBER_TO_ORDINAL_FUNCTION, NumberToOrdinal.class);
        repository.registerFunction(OperationIdentifiers.NUMBER_TO_DIGIT_FUNCTION, NumberToDigit.class);

        repository.registerFunction(OperationIdentifiers.DIGIT_COMPARATOR_FUNCTION, DigitComparator.class);
        repository.registerFunction(OperationIdentifiers.DIGIT_EQUALITY_FUNCTION, DigitEquality.class);

        repository.registerFunction(OperationIdentifiers.STANDARD_NOTATION_PARSER, StandardNotationParserImpl.class);
        repository.registerFunction(OperationIdentifiers.SCIENTIFIC_NOTATION_PARSER,
                                    ScientificNotationParserImpl.class);

        repository.registerFunction(OperationIdentifiers.SCIENTIFIC_NOTATION_FUNCTION,
                                    ScientificNotationFunctionImpl.class);
        repository.registerFunction(OperationIdentifiers.STANDARD_NOTATION_FUNCTION,
                                    StandardNotationFunctionImpl.class);

        repository.registerFunction(OperationIdentifiers.NEGATE_NUMBER_FUNCTION, NegateNumber.class);
        repository.registerFunction(OperationIdentifiers.NEGATE_FRACTION_FUNCTION, NegateFraction.class);
        repository.registerFunction(OperationIdentifiers.NUMBER_COMPLEMENT_FUNCTION, NumberComplement.class);

        repository.registerFunction(OperationIdentifiers.NUMBER_COMPARATOR_FUNCTION, NumberComparator.class);
        repository.registerFunction(OperationIdentifiers.NUMBER_FRACTION_COMPARATOR_FUNCTION,
                                    NumberFractionComparator.class);
        repository.registerFunction(OperationIdentifiers.NUMBER_EQUALITY_FUNCTION, NumberEquality.class);
        repository.registerFunction(OperationIdentifiers.NUMBER_FRACTION_EQUALITY_FUNCTION,
                                    NumberFractionEquality.class);

        repository.registerFunction(OperationIdentifiers.FRACTION_COMPARATOR_FUNCTION, FractionComparator.class);
        repository.registerFunction(OperationIdentifiers.FRACTION_NUMBER_COMPARATOR_FUNCTION,
                                    FractionNumberComparator.class);
        repository.registerFunction(OperationIdentifiers.FRACTION_EQUALITY_FUNCTION, FractionEquality.class);
        repository.registerFunction(OperationIdentifiers.FRACTION_NUMBER_EQUALITY_FUNCTION,
                                    FractionNumberEquality.class);

        repository.registerFunction(OperationIdentifiers.ADD_DIGITS_FUNCTION, AddDigits.class);
        repository.registerFunction(OperationIdentifiers.DIGIT_COMPLEMENT_FUNCTION, DigitComplement.class);
        repository.registerFunction(OperationIdentifiers.HALVING_DIGIT_FUNCTION, HalvingDigit.class);

        repository.registerFunction(OperationIdentifiers.ADD_NUMBERS_FUNCTION, AddNumbers.class);
        repository.registerFunction(OperationIdentifiers.ADD_NUMBERS_TRIM_RESULT_FUNCTION, AddNumbersTrimResult.class);
        repository.registerFunction(OperationIdentifiers.ADD_FRACTIONS_FUNCTION, AddFractions.class);
        repository.registerFunction(OperationIdentifiers.ADD_FRACTION_AND_NUMBER_FUNCTION, AddFractionAndNumber.class);
        repository.registerFunction(OperationIdentifiers.ADD_NUMBER_AND_FRACTION_FUNCTION, AddNumberAndFraction.class);

        repository.registerFunction(OperationIdentifiers.SUBTRACT_NUMBERS_FUNCTION, SubtractNumbers.class);
        repository.registerFunction(OperationIdentifiers.SUBTRACT_FRACTIONS_FUNCTION, SubtractFractions.class);
        repository.registerFunction(OperationIdentifiers.SUBTRACT_NUMBER_AND_FRACTION_FUNCTION,
                                    SubtractNumberAndFraction.class);
        repository.registerFunction(OperationIdentifiers.SUBTRACT_FRACTION_AND_NUMBER_FUNCTION,
                                    SubtractFractionAndNumber.class);

        repository.registerFunction(OperationIdentifiers.SHIFT_LEFT_FUNCTION, ShiftLeft.class);
        repository.registerFunction(OperationIdentifiers.SHIFT_RIGHT_FUNCTION, ShiftRight.class);

        repository.registerFunction(OperationIdentifiers.REMOVE_FRACTION_PART_FUNCTION, RemoveFractionPart.class);
        repository.registerFunction(OperationIdentifiers.REMOVE_INTEGER_PART_FUNCTION, RemoveIntegerPart.class);

        repository.registerFunction(OperationIdentifiers.DOUBLING_NUMBER_FUNCTION, DoublingNumber.class);
        repository.registerFunction(OperationIdentifiers.HALVING_NUMBER_FUNCTION, HalvingNumber.class);
        repository.registerFunction(OperationIdentifiers.DOUBLING_FRACTION_FUNCTION, DoublingFraction.class);
        repository.registerFunction(OperationIdentifiers.HALVING_FRACTION_FUNCTION, HalvingFraction.class);

        repository.registerFunction(OperationIdentifiers.MAX_NUMBER_FUNCTION, MaxNumber.class);
        repository.registerFunction(OperationIdentifiers.MAX_NUMBER_FRACTION_FUNCTION, MaxNumberFraction.class);
        repository.registerFunction(OperationIdentifiers.MAX_FRACTION_FUNCTION, MaxFraction.class);
        repository.registerFunction(OperationIdentifiers.MAX_FRACTION_NUMBER_FUNCTION, MaxFractionNumber.class);
        repository.registerFunction(OperationIdentifiers.MIN_NUMBER_FUNCTION, MinNumber.class);
        repository.registerFunction(OperationIdentifiers.MIN_NUMBER_FRACTION_FUNCTION, MinNumberFraction.class);
        repository.registerFunction(OperationIdentifiers.MIN_FRACTION_FUNCTION, MinFraction.class);
        repository.registerFunction(OperationIdentifiers.MIN_FRACTION_NUMBER_FUNCTION, MinFractionNumber.class);

        repository.registerFunction(OperationIdentifiers.NUMBER_INCREMENT_FUNCTION, IncrementNumber.class);
        repository.registerFunction(OperationIdentifiers.FRACTION_INCREMENT_FUNCTION, IncrementFraction.class);
        repository.registerFunction(OperationIdentifiers.NUMBER_DECREMENT_FUNCTION, DecrementNumber.class);
        repository.registerFunction(OperationIdentifiers.FRACTION_DECREMENT_FUNCTION, DecrementFraction.class);

        repository.registerFunction(OperationIdentifiers.EVEN_NUMBER_FUNCTION, EvenNumberCheck.class);
        repository.registerFunction(OperationIdentifiers.ODD_NUMBER_FUNCTION, OddNumberCheck.class);

        repository.registerFunction(OperationIdentifiers.NUMBER_GREATER_THAN_COMPARISON_FUNCTION,
                                    NumberGreaterThanComparison.class);
        repository.registerFunction(OperationIdentifiers.NUMBER_GREATER_THAN_FRACTION_COMPARISON_FUNCTION,
                                    NumberGreaterThanFractionComparison.class);
        repository.registerFunction(OperationIdentifiers.NUMBER_GREATER_THAN_OR_EQUAL_COMPARISON_FUNCTION,
                                    NumberGreaterThanOrEqualComparison.class);
        repository.registerFunction(OperationIdentifiers.NUMBER_GREATER_THAN_OR_EQUAL_FRACTION_COMPARISON_FUNCTION,
                                    NumberGreaterThanOrEqualFractionComparison.class);
        repository.registerFunction(OperationIdentifiers.NUMBER_LESSER_THAN_COMPARISON_FUNCTION,
                                    NumberLesserThanComparison.class);
        repository.registerFunction(OperationIdentifiers.NUMBER_LESSER_THAN_FRACTION_COMPARISON_FUNCTION,
                                    NumberLesserThanFractionComparison.class);
        repository.registerFunction(OperationIdentifiers.NUMBER_LESSER_THAN_OR_EQUAL_COMPARISON_FUNCTION,
                                    NumberLesserThanOrEqualComparison.class);
        repository.registerFunction(OperationIdentifiers.NUMBER_LESSER_THAN_OR_EQUAL_FRACTION_COMPARISON_FUNCTION,
                                    NumberLesserThanOrEqualFractionComparison.class);

        repository.registerFunction(OperationIdentifiers.FRACTION_GREATER_THAN_COMPARISON_FUNCTION,
                                    FractionGreaterThanComparison.class);
        repository.registerFunction(OperationIdentifiers.FRACTION_GREATER_THAN_NUMBER_COMPARISON_FUNCTION,
                                    FractionGreaterThanNumberComparison.class);
        repository.registerFunction(OperationIdentifiers.FRACTION_GREATER_THAN_OR_EQUAL_COMPARISON_FUNCTION,
                                    FractionGreaterThanOrEqualComparison.class);
        repository.registerFunction(OperationIdentifiers.FRACTION_GREATER_THAN_OR_EQUAL_NUMBER_COMPARISON_FUNCTION,
                                    FractionGreaterThanOrEqualNumberComparison.class);
        repository.registerFunction(OperationIdentifiers.FRACTION_LESSER_THAN_COMPARISON_FUNCTION,
                                    FractionLesserThanComparison.class);
        repository.registerFunction(OperationIdentifiers.FRACTION_LESSER_THAN_NUMBER_COMPARISON_FUNCTION,
                                    FractionLesserThanNumberComparison.class);
        repository.registerFunction(OperationIdentifiers.FRACTION_LESSER_THAN_OR_EQUAL_COMPARISON_FUNCTION,
                                    FractionLesserThanOrEqualComparison.class);
        repository.registerFunction(OperationIdentifiers.FRACTION_LESSER_THAN_OR_EQUAL_NUMBER_COMPARISON_FUNCTION,
                                    FractionLesserThanOrEqualNumberComparison.class);

        repository.registerFunction(OperationIdentifiers.MULTIPLY_DIGITS_FUNCTION, MultiplyDigits.class);
        repository.registerFunction(OperationIdentifiers.MULTIPLY_NUMBERS_BY_ADDITION_FUNCTION,
                                    MultiplicationByAddition.class);
        repository.registerFunction(OperationIdentifiers.RUSSIAN_PEASANT_MULTIPLICATION_FUNCTION,
                                    RussianPeasantMultiplication.class);
        repository.registerFunction(OperationIdentifiers.LONG_MULTIPLICATION_FUNCTION, LongMultiplication.class);
        repository.registerFunction(OperationIdentifiers.MULTIPLY_FRACTIONS_FUNCTION, MultiplyFractions.class);
        repository.registerFunction(OperationIdentifiers.MULTIPLY_NUMBER_AND_FRACTION_FUNCTION,
                                    MultiplyNumberAndFraction.class);
        repository.registerFunction(OperationIdentifiers.MULTIPLY_FRACTION_AND_NUMBER_FUNCTION,
                                    MultiplyFractionAndNumber.class);

        repository.registerFunction(OperationIdentifiers.DIVIDE_NUMBERS_RETURN_RESULT_AND_REMAINDER_FUNCTION,
                                    DivisionOfNumbersBySubtractionReturnResultAndRemainder.class);
        repository.registerFunction(OperationIdentifiers.DIVIDE_NUMBERS_RETURN_FRACTION_FUNCTION,
                                    DivisionBySubtractionReturnFraction.class);
        repository.registerFunction(OperationIdentifiers.DIVIDE_FRACTIONS_FUNCTION, DivideFractions.class);
        repository.registerFunction(OperationIdentifiers.DIVIDE_NUMBER_BY_FRACTION_FUNCTION,
                                    DivideNumberByFraction.class);
        repository.registerFunction(OperationIdentifiers.DIVIDE_FRACTION_BY_NUMBER_FUNCTION,
                                    DivideFractionByNumber.class);
        repository.registerFunction(OperationIdentifiers.RUSSIAN_DIVISION_FUNCTION, RussianDivision.class);
        repository.registerFunction(OperationIdentifiers.DIVIDE_NUMBERS_BY_SUBTRACTION, DivisionBySubtraction.class);

        repository.registerFunction(OperationIdentifiers.MODULO_FUNCTION, ModuloWithIntegers.class);
        repository.registerFunction(OperationIdentifiers.DIVISO_FUNCTION, DivisoWithIntegers.class);

        repository.registerFunction(OperationIdentifiers.RECIPROCAL_OF_NUMBER_FUNCTION, ReciprocalOfNumber.class);
        repository.registerFunction(OperationIdentifiers.RECIPROCAL_OF_FRACTION_FUNCTION, ReciprocalOfFraction.class);

        repository.registerFunction(OperationIdentifiers.REBASE_NUMBER_FUNCTION, RebaseNumber.class);
        repository.registerFunction(OperationIdentifiers.REBASE_FRACTION_FUNCTION, RebaseFraction.class);

        repository.registerFunction(OperationIdentifiers.FACTORIAL_FUNCTION, Factorial.class);

        repository.registerFunction(OperationIdentifiers.ROUND_DIGIT_TO_ODD_FUNCTION, RoundDigitToOdd.class);
        repository.registerFunction(OperationIdentifiers.ROUND_DIGIT_TO_EVEN_FUNCTION, RoundDigitToEven.class);
        repository.registerFunction(OperationIdentifiers.ROUND_NUMBER_TO_ODD_FUNCTION, RoundNumberToOdd.class);
        repository.registerFunction(OperationIdentifiers.ROUND_NUMBER_TO_EVEN_FUNCTION, RoundNumberToEven.class);
        repository.registerFunction(OperationIdentifiers.ROUND_UP_NUMBER_FUNCTION, RoundUp.class);
        repository.registerFunction(OperationIdentifiers.ROUND_DOWN_NUMBER_FUNCTION, RoundDown.class);

        repository.registerFunction(OperationIdentifiers.EVALUATE_FRACTION_FUNCTION, EvaluateFraction.class);
        repository.registerFunction(OperationIdentifiers.IS_MULTIPLE_FUNCTION, IsMultipleCheck.class);

        repository.registerFunction(OperationIdentifiers.SQUARE_NUMBER_FUNCTION, SquareNumber.class);
        repository.registerFunction(OperationIdentifiers.SQUARE_FRACTION_FUNCTION, SquareFraction.class);

        repository.registerFunction(OperationIdentifiers.DETERMINE_DIVISORS_FUNCTION, DetermineDivisors.class);
        repository.registerFunction(OperationIdentifiers.DETERMINE_PRIME_FACTORS_NUMBER, DeterminePrimeFactors.class);
        repository.registerFunction(OperationIdentifiers.DETERMINE_COMMON_DIVISORS_FUNCTION,
                                    DetermineCommonDivisors.class);
        repository.registerFunction(OperationIdentifiers.DETERMINE_COMMON_PRIME_FACTORS_FUNCTION,
                                    DetermineCommonPrimeFactors.class);
        repository.registerFunction(OperationIdentifiers.REDUCE_FRACTION_FUNCTION, ReduceFraction.class);
        repository.registerFunction(OperationIdentifiers.IS_PRIME_FUNCTION, IsPrimeCheck.class);

        repository.registerFunction(OperationIdentifiers.VECTOR_EQUALITY_FUNCTION, VectorEquality.class);
        repository.registerFunction(OperationIdentifiers.MATRIX_EQUALITY_FUNCTION, MatrixEquality.class);

        repository.registerFunction(OperationIdentifiers.ADD_VECTORS_FUNCTION, AddVectors.class);
        repository.registerFunction(OperationIdentifiers.SUBTRACT_VECTORS_FUNCTION, SubtractVectors.class);
        repository.registerFunction(OperationIdentifiers.MULTIPLY_VECTOR_WITH_NUMBER_FUNCTION,
                                    MultiplyVectorWithNumber.class);
        repository.registerFunction(OperationIdentifiers.SCALAR_PRODUCT_FUNCTION, ScalarProduct.class);
        repository.registerFunction(OperationIdentifiers.CROSS_PRODUCT_FUNCTION, CrossProduct.class);
        repository.registerFunction(OperationIdentifiers.TRIPLE_PRODUCT_FUNCTION, TripleProduct.class);

        repository.registerFunction(OperationIdentifiers.ADD_MATRICES_FUNCTION, AddMatrices.class);
        repository.registerFunction(OperationIdentifiers.SUBTRACT_MATRICES_FUNCTION, SubtractMatrices.class);

        repository.registerFunction(OperationIdentifiers.NUMBER_IS_WITHIN_INTERVAL, NumberWithinInterval.class);
        repository.registerFunction(OperationIdentifiers.FRACTION_IS_WITHIN_INTERVAL, FractionWithinInterval.class);

        repository.registerFunction(OperationIdentifiers.DYADIC_PRODUCT_FUNCTION, DyadicProduct.class);

        repository.registerFunction(OperationIdentifiers.VECTORIZATION_FUNCTION, Vectorization.class);
        repository.registerFunction(OperationIdentifiers.TRANSPOSE_MATRIX_FUNCTION, TransposeMatrix.class);
        repository.registerFunction(OperationIdentifiers.MATRIX_MULTIPLCIATION_FUNCTION, MatrixMultiplication.class);

        repository.registerFunction(OperationIdentifiers.DIGIT_SUM_FUNCTION, NumberDigitSum.class);
        repository.registerFunction(OperationIdentifiers.DIGIT_TO_NUMBER_FUNCTION, DigitToNumberConversion.class);
        repository.registerFunction(OperationIdentifiers.IS_SINGLE_DIGIT_FUNCTION, IsSingleDigitCheck.class);

        repository.registerFunction(OperationIdentifiers.RANDOM_DIGIT_FUNCTION, RandomDigit.class);
        repository.registerFunction(OperationIdentifiers.RANDOM_NUMBER_FUNCTION, RandomNumber.class);
        repository.registerFunction(OperationIdentifiers.RANDOM_NUMBER_WITHIN_INTERVAL_FUNCTION,
                                    RandomNumberWithinInterval.class);

        repository.registerFunction(OperationIdentifiers.ADD_LOGARITHMS_FUNCTION, AddLogarithmsFunction.class);
        repository.registerFunction(OperationIdentifiers.SUBTRACT_LOGARITHMS_FUNCTION, SubtractLogarithms.class);

        repository.registerFunction(OperationIdentifiers.SQUARE_ROOT_FUNCTION, SquareRoot.class);
        repository.registerFunction(OperationIdentifiers.NTH_ROOT_FUNCTION, NthRoot.class);

        repository.registerFunction(OperationIdentifiers.EXPONENTIATE_NUMBER_WITH_NUMBER_FUNCTION,
                                    ExponentiateNumberWithNumber.class);
        repository.registerFunction(OperationIdentifiers.EXPONENTIATE_NUMBER_WITH_FRACTION_FUNCTION,
                                    ExponentiateNumberWithFraction.class);
        repository.registerFunction(OperationIdentifiers.EXPONENTIATE_FRACTION_WITH_NUMBER_FUNCTION,
                                    ExponentiateFractionWithNumber.class);

        repository.registerFunction(OperationIdentifiers.EULERS_NUMBER_FUNCTION, EulersNumberFunction.class);
        repository.registerFunction(OperationIdentifiers.LEIBNIZ_PI_APPROXIMATION_FUNCTION,
                                    LeibnizPiApproximation.class);
        repository.registerFunction(OperationIdentifiers.ARCHIMEDES_PI_APPROXIMATION_FUNCTION,
                                    ArchimedesPiApproximation.class);

        return repository;
    }

}
