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
import static jmul.math.operations.OperationClassifiers.SUBTRACTION;


/**
 * This enumeration class contains identifiers for various functions.
 *
 * @author Kristian Kutin
 */
public enum FunctionIdentifiers implements FunctionIdentifier {


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
    MIN_NUMBER_FUNCTION(NONE),

    DOUBLING_NUMBER_FUNCTION(NONE),
    HALVING_NUMBER_FUNCTION(NONE),
    TRUNCATE_NUMBER_FUNCTION(NONE),

    DIGIT_EQUALITY_FUNCTION(NONE),
    DIGIT_COMPARATOR_FUNCTION(NONE),

    NEGATE_NUMBER_FUNCTION(NONE),
    NUMBER_COMPLEMENT_FUNCTION(NONE),

    NUMBER_COMPARATOR_FUNCTION(NONE),
    NUMBER_FRACTION_COMPARATOR_FUNCTION(NONE),
    NUMBER_EQUALITY_FUNCTION(NONE),

    FRACTION_COMPARATOR_FUNCTION(NONE),
    FRACTION_NUMBER_COMPARATOR_FUNCTION(NONE),
    FRACTION_EQUALITY_FUNCTION(NONE),

    ADD_NUMBERS_FUNCTION(ADDITION),
    SUBTRACT_NUMBERS_FUNCTION(SUBTRACTION),
    MULTIPLY_NUMBERS_FUNCTION(MULTIPLICATION),
    DIVIDE_NUMBERS_FUNCTION(DIVISION),
    MODULO_FUNCTION(NONE),
    DIVISO_FUNCTION(NONE),

    NUMBER_INCREMENT_FUNCTION(ADDITION),
    NUMBER_DECREMENT_FUNCTION(SUBTRACTION),

    EVEN_NUMBER_FUNCTION(NONE),
    ODD_NUMBER_FUNCTION(NONE),

    NUMBER_GREATER_COMPARISON(NONE),
    NUMBER_GREATER_OR_EQUAL_COMPARISON(NONE),
    NUMBER_LESSER_COMPARISON(NONE),
    NUMBER_LESSER_OR_EQUAL_COMPARISON(NONE),

    FRACTION_GREATER_COMPARISON(NONE),
    FRACTION_GREATER_OR_EQUAL_COMPARISON(NONE),
    FRACTION_LESSER_COMPARISON(NONE),
    FRACTION_LESSER_OR_EQUAL_COMPARISON(NONE),

    REBASE_FUNCTION(NONE);


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
