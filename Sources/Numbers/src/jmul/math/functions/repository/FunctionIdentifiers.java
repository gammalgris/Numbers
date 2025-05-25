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


import jmul.math.operations.OperationClassifier;
import static jmul.math.operations.OperationClassifiers.ADDITION;
import static jmul.math.operations.OperationClassifiers.DIVISION;
import static jmul.math.operations.OperationClassifiers.MULTIPLICATION;
import static jmul.math.operations.OperationClassifiers.NONE;
import static jmul.math.operations.OperationClassifiers.ROUNDING;
import static jmul.math.operations.OperationClassifiers.SUBTRACTION;


/**
 * This enumeration class contains identifiers for various functions.
 *
 * @author Kristian Kutin
 */
public enum FunctionIdentifiers implements FunctionIdentifier {


    NUMBER_TO_ABSOLUTE_VALUE_FUNCTION(NONE),
    FRACTION_TO_ABSOLUTE_VALUE_FUNCTION(NONE),

    BASE_TO_NUMBER_FUNCTION(NONE),
    NUMBER_TO_ORDINAL_FUNCTION(NONE),
    NUMBER_TO_DIGIT_FUNCTION(NONE),

    NUMBER_TO_FRACTION_FUNCTION(NONE),

    STANDARD_NOTATION_FUNCTION(NONE),
    SCIENTIFIC_NOTATION_FUNCTION(NONE),

    STANDARD_NOTATION_PARSER(NONE),
    SCIENTIFIC_NOTATION_PARSER(NONE),

    ADD_DIGITS_FUNCTION(NONE),
    DIGIT_COMPLEMENT_FUNCTION(NONE),
    HALVING_DIGIT_FUNCTION(NONE),

    SHIFT_LEFT_FUNCTION(NONE),
    SHIFT_RIGHT_FUNCTION(NONE),

    MAX_NUMBER_FUNCTION(NONE),
    MAX_NUMBER_FRACTION_FUNCTION(NONE),
    MAX_FRACTION_FUNCTION(NONE),
    MAX_FRACTION_NUMBER_FUNCTION(NONE),
    MIN_NUMBER_FUNCTION(NONE),
    MIN_NUMBER_FRACTION_FUNCTION(NONE),
    MIN_FRACTION_FUNCTION(NONE),
    MIN_FRACTION_NUMBER_FUNCTION(NONE),

    DOUBLING_NUMBER_FUNCTION(NONE),
    HALVING_NUMBER_FUNCTION(NONE),
    DOUBLING_FRACTION_FUNCTION(NONE),
    HALVING_FRACTION_FUNCTION(NONE),

    REMOVE_FRACTION_PART_FUNCTION(NONE),
    REMOVE_INTEGER_PART_FUNCTION(NONE),

    DIGIT_EQUALITY_FUNCTION(NONE),
    DIGIT_COMPARATOR_FUNCTION(NONE),

    NEGATE_NUMBER_FUNCTION(NONE),
    NEGATE_FRACTION_FUNCTION(NONE),
    NUMBER_COMPLEMENT_FUNCTION(NONE),

    NUMBER_COMPARATOR_FUNCTION(NONE),
    NUMBER_FRACTION_COMPARATOR_FUNCTION(NONE),
    NUMBER_EQUALITY_FUNCTION(NONE),
    NUMBER_FRACTION_EQUALITY_FUNCTION(NONE),

    FRACTION_COMPARATOR_FUNCTION(NONE),
    FRACTION_NUMBER_COMPARATOR_FUNCTION(NONE),
    FRACTION_EQUALITY_FUNCTION(NONE),
    FRACTION_NUMBER_EQUALITY_FUNCTION(NONE),

    ADD_NUMBERS_FUNCTION(ADDITION),
    ADD_NUMBERS_TRIM_RESULT_FUNCTION(ADDITION),
    ADD_FRACTIONS_FUNCTION(ADDITION),
    ADD_FRACTION_AND_NUMBER_FUNCTION(ADDITION),
    ADD_NUMBER_AND_FRACTION_FUNCTION(ADDITION),

    SUBTRACT_NUMBERS_FUNCTION(SUBTRACTION),
    SUBTRACT_FRACTIONS_FUNCTION(SUBTRACTION),
    SUBTRACT_FRACTION_AND_NUMBER_FUNCTION(SUBTRACTION),
    SUBTRACT_NUMBER_AND_FRACTION_FUNCTION(SUBTRACTION),

    MULTIPLY_NUMBERS_FUNCTION(MULTIPLICATION),
    MULTIPLY_FRACTIONS_FUNCTION(MULTIPLICATION),
    MULTIPLY_FRACTION_AND_NUMBER_FUNCTION(MULTIPLICATION),
    MULTIPLY_NUMBER_AND_FRACTION_FUNCTION(MULTIPLICATION),

    DIVIDE_NUMBERS_RETURN_RESULT_AND_REMAINDER_FUNCTION(DIVISION),
    DIVIDE_FRACTIONS_RETURN_RESULT_AND_REMAINDER_FUNCTION(DIVISION),
    DIVIDE_NUMBERS_RETURN_FRACTION_FUNCTION(DIVISION),
    DIVIDE_FRACTIONS_FUNCTION(DIVISION),
    DIVIDE_NUMBER_BY_FRACTION_FUNCTION(DIVISION),
    DIVIDE_FRACTION_BY_NUMBER_FUNCTION(DIVISION),

    MODULO_FUNCTION(DIVISION),
    DIVISO_FUNCTION(DIVISION),

    NUMBER_INCREMENT_FUNCTION(ADDITION),
    NUMBER_DECREMENT_FUNCTION(SUBTRACTION),

    FRACTION_INCREMENT_FUNCTION(ADDITION),
    FRACTION_DECREMENT_FUNCTION(SUBTRACTION),

    EVEN_NUMBER_FUNCTION(NONE),
    ODD_NUMBER_FUNCTION(NONE),

    NUMBER_GREATER_THAN_COMPARISON_FUNCTION(NONE),
    NUMBER_GREATER_THAN_FRACTION_COMPARISON_FUNCTION(NONE),
    NUMBER_GREATER_THAN_OR_EQUAL_COMPARISON_FUNCTION(NONE),
    NUMBER_GREATER_THAN_OR_EQUAL_FRACTION_COMPARISON_FUNCTION(NONE),
    NUMBER_LESSER_THAN_COMPARISON_FUNCTION(NONE),
    NUMBER_LESSER_THAN_FRACTION_COMPARISON_FUNCTION(NONE),
    NUMBER_LESSER_THAN_OR_EQUAL_COMPARISON_FUNCTION(NONE),
    NUMBER_LESSER_THAN_OR_EQUAL_FRACTION_COMPARISON_FUNCTION(NONE),

    FRACTION_GREATER_THAN_COMPARISON_FUNCTION(NONE),
    FRACTION_GREATER_THAN_NUMBER_COMPARISON_FUNCTION(NONE),
    FRACTION_GREATER_THAN_OR_EQUAL_COMPARISON_FUNCTION(NONE),
    FRACTION_GREATER_THAN_OR_EQUAL_NUMBER_COMPARISON_FUNCTION(NONE),
    FRACTION_LESSER_THAN_COMPARISON_FUNCTION(NONE),
    FRACTION_LESSER_THAN_NUMBER_COMPARISON_FUNCTION(NONE),
    FRACTION_LESSER_THAN_OR_EQUAL_COMPARISON_FUNCTION(NONE),
    FRACTION_LESSER_THAN_OR_EQUAL_NUMBER_COMPARISON_FUNCTION(NONE),

    RECIPROCAL_OF_NUMBER_FUNCTION(NONE),
    RECIPROCAL_OF_FRACTION_FUNCTION(NONE),

    REBASE_NUMBER_FUNCTION(NONE),
    REBASE_FRACTION_FUNCTION(NONE),

    FACTORIAL_FUNCTION(NONE),

    ROUND_DIGIT_TO_ODD_FUNCTION(ROUNDING),
    ROUND_DIGIT_TO_EVEN_FUNCTION(ROUNDING),
    ROUND_NUMBER_TO_ODD_FUNCTION(ROUNDING),
    ROUND_NUMBER_TO_EVEN_FUNCTION(ROUNDING),
    ROUND_UP_NUMBER_FUNCTION(ROUNDING),
    ROUND_DOWN_NUMBER_FUNCTION(ROUNDING);


    /**
     * An operation classifier which is associated with a function identifier.
     */
    private final OperationClassifier operationClassifier;

    /**
     * The default constructor.
     *
     * @param operationClassifier
     */
    private FunctionIdentifiers(OperationClassifier operationClassifier) {

        this.operationClassifier = operationClassifier;
    }

    /**
     * Returns the operation classifier associated with this function identifier.
     *
     * @return an operation classifier
     */
    @Override
    public OperationClassifier operationClassifier() {

        return operationClassifier;
    }

    /**
     * Returns the length this char sequence.
     *
     * @return a length
     */
    @Override
    public int length() {

        return toString().length();
    }

    /**
     * Returns the character at the specified position.
     *
     * @param index
     *        an index (i.e. zero or positive number up to the length of the string)
     *
     * @return a character
     */
    @Override
    public char charAt(int index) {

        return toString().charAt(index);
    }

    /**
     * Returns a subsequence at the specified position.
     *
     * @param start
     *        a start index
     * @param end
     *        an end index
     *
     * @return a substring
     */
    @Override
    public CharSequence subSequence(int start, int end) {

        return toString().subSequence(start, end);
    }

}
