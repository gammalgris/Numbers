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

package jmul.math.numbers;


import java.util.Comparator;

import jmul.math.Math;
import jmul.math.collections.Sequence;
import jmul.math.collections.Set;
import jmul.math.fractions.Fraction;
import jmul.math.hash.HashHelper;
import static jmul.math.numbers.Constants.DEFAULT_NUMBER_BASE;
import static jmul.math.numbers.creation.CreationParameters.CLONE;
import jmul.math.numbers.nodes.DigitNode;
import jmul.math.numbers.notations.NotationFunction;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.EqualityFunction;
import jmul.math.operations.MixedBinaryOperation;
import jmul.math.operations.MixedComparator;
import jmul.math.operations.MixedEqualityFunction;
import jmul.math.operations.MixedQuaternaryOperation;
import jmul.math.operations.OperationSingletons;
import jmul.math.operations.QuaternaryOperation;
import jmul.math.operations.Result;
import jmul.math.operations.TernaryOperation;
import jmul.math.operations.UnaryOperation;
import jmul.math.operations.implementations.ParameterCheckHelper;
import jmul.math.operations.processing.ProcessingDetails;
import jmul.math.operations.repository.OperationIdentifier;
import jmul.math.operations.repository.OperationIdentifiers;
import jmul.math.signs.Sign;
import jmul.math.signs.Signs;


/**
 * An implementation of a real number. This implementation requires memory depending on the number of digits
 * the number consists of. Even very large or very small numbers can be represented without rounding. Since the
 * underlying data structure is a linked list numbers can exceed the array size limitations in java.<br>
 * Thus it might not be possible to create a string representation for every number (see array size limitations).<br>
 * <br>
 * <i>Note:<br>
 * Consider implementing a stream mechanism to serialize/ deserialize very large or very small numbers.</i><br>
 * <br>
 * <i>Node:<br>
 * Consider an alternative implementation which compresses the linked list in some way in order to save up memory.</i>
 *
 * @author Kristian Kutin
 */
public class NumberImpl implements Number {

    /**
     * The sign of this number.
     */
    private final Sign sign;

    /**
     * The digit base of this number.
     */
    private final int base;

    /**
     * A reference to the center node (i.e. position <code>base^0</code>).
     */
    private final DigitNode centerNode;

    /**
     * Creates a number which represents positive infinity. The default base is <code>10</code>.
     */
    protected NumberImpl() {

        this(DEFAULT_NUMBER_BASE);
    }

    /**
     * Creates a number which represents positive infinity.
     *
     * @param base
     *        the base for this number
     */
    protected NumberImpl(int base) {

        this(base, Signs.POSITIVE);
    }

    /**
     * Creates a number which represents positive or negative infinity. The default base is <code>10</code>.
     *
     * @param sign
     *        a sign for this number
     */
    protected NumberImpl(Sign sign) {

        this(DEFAULT_NUMBER_BASE, sign, null);
    }

    /**
     * Creates a number which represents positive or negative infinity.
     *
     * @param base
     *        the base for this number
     * @param sign
     *        a sign for this number
     */
    protected NumberImpl(int base, Sign sign) {

        this(base, sign, null);
    }

    /**
     * Creates a number with the specified parameters.<br>
     * <br>
     * <i>Note:<br>
     * This constructor is mainly used for internal purposes and to instantiate calculation results.
     * Calculations result don't need to be cloned.</i>
     *
     * @param base
     *        the base for this number
     * @param sign
     *        the sign for this number
     * @param centerNode
     *        a reference to the center node of the linked list
     */
    protected NumberImpl(int base, Sign sign, DigitNode centerNode) {

        super();

        this.sign = sign;
        this.base = base;
        this.centerNode = centerNode;
    }

    /**
     * Returns the sign of this number.
     *
     * @return a sign
     */
    @Override
    public Sign sign() {

        return sign;
    }

    /**
     * Returns the base of the underlying numeral system for this number.
     *
     * @return a base
     */
    @Override
    public int base() {

        return base;
    }

    /**
     * Returns a scientific notation for this number. The default decimal separator is used.<br>
     * <br>
     * <i>Note:<br>
     * If the number has of too many digits it may not be possible to create a string representation.
     * In this case try converting the number to a higher base.</i>
     *
     * @return a scientific notation for this number
     */
    @Override
    public String toScientificNotation() {

        return toScientificNotation(Constants.DECIMAL_SEPARATOR);
    }

    /**
     * Returns a scientific notation for this number. The specified decimal separator is used.<br>
     * <br>
     * <i>Note:<br>
     * If the number has of too many digits it may not be possible to create a string representation.
     * In this case try converting the number to a higher base.</i>
     *
     * @param decimalSeparator
     *        a decimal separator
     *
     * @return a scientific notation for this number
     */
    @Override
    public String toScientificNotation(char decimalSeparator) {

        NotationFunction function =
            (NotationFunction) OperationSingletons.getFunction(OperationIdentifiers.SCIENTIFIC_NOTATION_FUNCTION);

        return function.toString(this, decimalSeparator);
    }

    /**
     * Returns a standard notation for this number. The default decimal separator is used.<br>
     * <br>
     * <i>Note:<br>
     * If the number has of too many digits it may not be possible to create a string representation.
     * In this case try the scientific notation or converting the number to a higher base.</i>
     *
     * @return a standard notation for this number
     */
    @Override
    public String toStandardNotation() {

        return toStandardNotation(Constants.DECIMAL_SEPARATOR);
    }

    /**
     * Returns a standard notation for this number. The specified decimal separator is used.<br>
     * <br>
     * <i>Note:<br>
     * If the number has of too many digits it may not be possible to create a string representation.
     * In this case try the scientific notation or converting the number to a higher base.</i>
     *
     * @param decimalSeparator
     *        a decimal separator
     *
     * @return a standard notation for this number
     */
    @Override
    public String toStandardNotation(char decimalSeparator) {

        NotationFunction function =
            (NotationFunction) OperationSingletons.getFunction(OperationIdentifiers.STANDARD_NOTATION_FUNCTION);

        return function.toString(this, decimalSeparator);
    }

    /**
     * Returns a string representation for this number (i.e. the standard notation for this number).
     *
     * @return a standard notation for this number
     */
    @Override
    public String toString() {

        return toStandardNotation();
    }

    /**
     * Returns a string representation of this number with the specified decimal separator.
     *
     * @param decimalSeparator
     *        a decimal separator
     *
     * @return a string representation
     */
    public String toString(char decimalSeparator) {

        return toStandardNotation(decimalSeparator);
    }

    /**
     * Checks if this number represents infinity.
     *
     * @return <code>true</code> if this number represents infinity, else <code>false</code>
     */
    @Override
    public boolean isInfinity() {

        return centerNode() == null;
    }

    /**
     * Checks if this number represents zero.
     *
     * @return <code>true</code> if this number represents zero, else <code>false</code>
     */
    @Override
    public boolean isZero() {

        return (centerNode() != null) && (centerNode().leftNode() == null) && (centerNode().rightNode() == null) &&
               centerNode.digit().isZero();
    }

    /**
     * Checks if this number represents one.
     *
     * @return <code>true</code> if this number represents one, else <code>false</code>
     */
    @Override
    public boolean isOne() {

        return (centerNode() != null) && (centerNode().leftNode() == null) && (centerNode().rightNode() == null) &&
               centerNode.digit().isOrdinal(1);
    }

    /**
     * Returns the node which represents the zeroth position (i.e. <code>base^0</code>) within a number.
     * To the left are the digits which represent the positions 1 (<code>base^1</code>), 2 (<code>base^2</code>),
     * 3 (<code>base^3</code>), etc..
     * To the right are the digits which represent the fraction and positions -1 (<code>base^-1</code>),
     * -2 (<code>base^-2</code>), 3 (<code>base^-3</code>), etc..
     *
     * @return the center node
     */
    @Override
    public DigitNode centerNode() {

        return centerNode;
    }

    /**
     * Compares this number to the specified object.
     *
     * @param o
     *        an object
     *
     * @return <code>1</code> if this number is greater than the specified object, <code>0</code> if this
     *         number is equal to the specified object or <code>-1</code> if this number is lesser than the
     *         specified object
     *
     * @throws NullPointerException
     *         if the specified object is null
     * @throws ClassCastException
     *         if the specified object's type prevents it from being compared to this object.
     */
    @Override
    public int compareTo(Object o) {

        if (o == null) {

            throw new NullPointerException();
        }

        if (o instanceof Fraction) {

            Fraction other = (Fraction) o;

            MixedComparator<Number, Fraction> comparator =
                (MixedComparator<Number, Fraction>) OperationSingletons.getFunction(OperationIdentifiers.NUMBER_FRACTION_COMPARATOR_FUNCTION);
            return comparator.compare(this, other);

        } else if (o instanceof Number) {

            Number other = (Number) o;

            Comparator<Number> comparator =
                (Comparator<Number>) OperationSingletons.getFunction(OperationIdentifiers.NUMBER_COMPARATOR_FUNCTION);
            return comparator.compare(this, other);
        }

        throw new ClassCastException();
    }

    /**
     * Checks the equality of this number and the specified object (i.e. number).
     *
     * @param o
     *        another object (i.e. number)
     *
     * @return <code>true</code> if this number is equal to the specified number, else <code>false</code>
     */
    @Override
    public boolean equals(Object o) {

        if (o instanceof Number) {

            Number other = (Number) o;

            EqualityFunction<Number> function =
                (EqualityFunction<Number>) OperationSingletons.getFunction(OperationIdentifiers.NUMBER_EQUALITY_FUNCTION);
            boolean result = function.equals(this, other);

            return result;

        } else if (o instanceof Fraction) {

            Fraction other = (Fraction) o;

            MixedEqualityFunction<Number, Fraction> function =
                (MixedEqualityFunction<Number, Fraction>) OperationSingletons.getFunction(OperationIdentifiers.NUMBER_FRACTION_EQUALITY_FUNCTION);
            boolean result = function.equals(this, other);

            return result;
        }

        return false;
    }

    /**
     * Calculates a hash code for this number.
     *
     * @return a hash code
     */
    @Override
    public int hashCode() {

        return HashHelper.calculateHashCode(Number.class, sign, base, centerNode);
    }

    /**
     * Checks if this number is a fraction.
     *
     * @return <code>true</code> if this number is a fraction, else <code>false</code>
     */
    @Override
    public boolean isFraction() {

        return (centerNode() != null) && (centerNode().rightNode() != null);
    }

    /**
     * Checks if this number is an integer.
     *
     * @return <code>true</code> if this number is an integer, else <code>false</code>
     */
    @Override
    public boolean isInteger() {

        return (centerNode != null) && (centerNode().rightNode() == null);
    }

    /**
     * Checks if this number is a natural number, including zero.
     *
     * @return <code>true</code> if this number is a natural number, including zero, else <code>false</code>
     */
    @Override
    public boolean isNaturalNumberIncludingZero() {

        return isPositive() && (isInteger() || isZero());
    }

    /**
     * Checks if this number is a natural number.
     *
     * @return <code>true</code> if this number is a natural number, else <code>false</code>
     */
    @Override
    public boolean isNaturalNumber() {

        return isPositive() && isInteger() && !isZero();
    }

    /**
     * Checks if this number is positive.
     *
     * @return <code>true</code> if this number is positive, else <code>false</code>
     */
    @Override
    public boolean isPositive() {

        return Signs.isPositive(sign());
    }

    /**
     * Checks if this number is negative.
     *
     * @return <code>true</code> if this number is negative, else <code>false</code>
     */
    @Override
    public boolean isNegative() {

        return Signs.isNegative(sign());
    }

    /**
     * Negates this number (i.e. changes the sign).
     *
     * @return a number
     */
    @Override
    public Number negate() {

        UnaryOperation<Number, Result<Number>> function =
            (UnaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.NEGATE_NUMBER_FUNCTION);
        Result<Number> result = function.calculate(this);

        return result.result();
    }

    /**
     * Calculates and returns the complement of this number.
     *
     * @return the complement of this number
     */
    @Override
    public Number complement() {

        UnaryOperation<Number, Result<Number>> function =
            (UnaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.NUMBER_COMPLEMENT_FUNCTION);
        Result<Number> result = function.calculate(this);

        return result.result();
    }

    /**
     * Calculates the factorial for this number.
     *
     * @return the factorial for this number
     */
    @Override
    public Number factorial() {

        UnaryOperation<Number, Result<Number>> function =
            (UnaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.FACTORIAL_FUNCTION);
        Result<Number> result = function.calculate(this);

        return result.result();
    }

    /**
     * Calculates the square root for this number.
     *
     * @return the square root for this number
     */
    @Override
    public Number squareRoot() {

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(ProcessingDetails.DEFAULT_ALGORITHM,
                                                   ProcessingDetails.DEFAULT_PRECISION,
                                                   ProcessingDetails.DEFAULT_ITERATION_DEPTH);

        return squareRoot(processingDetails);
    }

    /**
     * Calculates the square root for this number.
     *
     * @param processingDetails
     *        additonal processing details
     *
     * @return the square root for this number
     */
    @Override
    public Number squareRoot(ProcessingDetails processingDetails) {

        ParameterCheckHelper.checkParameter(processingDetails);

        final OperationIdentifier[] ALLOWED_ALGORITHMS = new OperationIdentifier[] {
            OperationIdentifiers.SQUARE_ROOT_FUNCTION };

        OperationIdentifier algorithm = processingDetails.checkAndReturnAlgorithm(ALLOWED_ALGORITHMS);
        Number decimalPlaces =
            processingDetails.checkAndReturnPrecision(Math.DEFAULT_MAXIMUM_FRACTION_LENGTH.value(base));
        Number iterations =
            processingDetails.checkAndReturnIterationDepth(Math.DEFAULT_HERON_METHOD_ITERATIONS.value(base));

        TernaryOperation<Number, Result<Number>> function =
            (TernaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(algorithm);
        Result<Number> result = function.calculate(this, iterations, decimalPlaces);

        return result.result();
    }

    /**
     * Calculates the nth root for this number.
     *
     * @return the nth root for this number
     */
    @Override
    public Number root(Number n) {

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(ProcessingDetails.DEFAULT_ALGORITHM,
                                                   ProcessingDetails.DEFAULT_PRECISION,
                                                   ProcessingDetails.DEFAULT_ITERATION_DEPTH);

        return root(processingDetails, n);
    }

    /**
     * Calculates the nth root for this number.
     *
     * @param processingDetails
     *        additonal processing details
     * @param n
     *        the root
     *
     * @return the nth root for this number
     */
    @Override
    public Number root(ProcessingDetails processingDetails, Number n) {

        ParameterCheckHelper.checkParameter(processingDetails);
        ParameterCheckHelper.checkParameter(n);

        final OperationIdentifier[] ALLOWED_ALGORITHMS = new OperationIdentifier[] {
            OperationIdentifiers.NTH_ROOT_FUNCTION };

        OperationIdentifier algorithm = processingDetails.checkAndReturnAlgorithm(ALLOWED_ALGORITHMS);
        Number decimalPlaces =
            processingDetails.checkAndReturnPrecision(Math.DEFAULT_MAXIMUM_FRACTION_LENGTH.value(base));
        Number iterations =
            processingDetails.checkAndReturnIterationDepth(Math.DEFAULT_NTH_ROOT_ITERATIONS.value(base));

        QuaternaryOperation<Number, Result<Number>> function =
            (QuaternaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(algorithm);
        Result<Number> result = function.calculate(this, n, iterations, decimalPlaces);

        return result.result();
    }

    /**
     * Halves this number.
     *
     * @return a number
     */
    @Override
    public Number halving() {

        UnaryOperation<Number, Result<Number>> function =
            (UnaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.HALVING_NUMBER_FUNCTION);
        Result<Number> result = function.calculate(this);

        return result.result();
    }

    /**
     * Doubles this number.
     *
     * @return a number
     */
    @Override
    public Number doubling() {

        UnaryOperation<Number, Result<Number>> function =
            (UnaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.DOUBLING_NUMBER_FUNCTION);
        Result<Number> result = function.calculate(this);

        return result.result();
    }

    /**
     * Adds this number and the specified number.
     *
     * @param n
     *        a number
     *
     * @return a number
     */
    @Override
    public Number add(Number n) {

        BinaryOperation<Number, Result<Number>> function =
            (BinaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.ADD_NUMBERS_TRIM_RESULT_FUNCTION);
        Result<Number> result = function.calculate(this, n);

        return result.result();
    }

    /**
     * Adds this number and the specified fraction.
     *
     * @param f
     *        a fraction
     *
     * @return a fraction
     */
    @Override
    public Fraction add(Fraction f) {

        MixedBinaryOperation<Number, Fraction, Result<Fraction>> function =
            (MixedBinaryOperation<Number, Fraction, Result<Fraction>>) OperationSingletons.getFunction(OperationIdentifiers.ADD_NUMBER_AND_FRACTION_FUNCTION);
        Result<Fraction> result = function.calculate(this, f);

        return result.result();
    }

    /**
     * Substracts the specified number from this number.
     *
     * @param n
     *        a number
     *
     * @return a number
     */
    @Override
    public Number subtract(Number n) {

        BinaryOperation<Number, Result<Number>> function =
            (BinaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.SUBTRACT_NUMBERS_FUNCTION);
        Result<Number> result = function.calculate(this, n);

        return result.result();
    }

    /**
     * Substracts the specified fraction from this number.
     *
     * @param f
     *        a fraction
     *
     * @return a fraction
     */
    @Override
    public Fraction subtract(Fraction f) {

        MixedBinaryOperation<Number, Fraction, Result<Fraction>> function =
            (MixedBinaryOperation<Number, Fraction, Result<Fraction>>) OperationSingletons.getFunction(OperationIdentifiers.SUBTRACT_NUMBER_AND_FRACTION_FUNCTION);
        Result<Fraction> result = function.calculate(this, f);

        return result.result();
    }

    /**
     * Multiplies this number with the specified number.
     *
     * @param n
     *        a number
     *
     * @return the product of this number and the specified number
     */
    @Override
    public Number multiply(Number n) {

        ProcessingDetails processingDetails = ProcessingDetails.setAlgorithm(ProcessingDetails.DEFAULT_ALGORITHM);

        return multiply(processingDetails, n);
    }

    /**
     * Multiplies this number with the specified number.
     *
     * @param processingDetails
     *        additonal processing details
     * @param n
     *        a number
     *
     * @return the product of this number and the specified number
     */
    public Number multiply(ProcessingDetails processingDetails, Number n) {

        ParameterCheckHelper.checkParameter(processingDetails);

        final OperationIdentifier[] ALLOWED_ALGORITHMS = new OperationIdentifier[] {
            OperationIdentifiers.LONG_MULTIPLICATION_FUNCTION,
            OperationIdentifiers.MULTIPLY_NUMBERS_BY_ADDITION_FUNCTION,
            OperationIdentifiers.RUSSIAN_PEASANT_MULTIPLICATION_FUNCTION
        };

        OperationIdentifier algorithm = processingDetails.checkAndReturnAlgorithm(ALLOWED_ALGORITHMS);

        BinaryOperation<Number, Result<Number>> function =
            (BinaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(algorithm);
        Result<Number> result = function.calculate(this, n);

        return result.result();
    }

    /**
     * Multiplies this number with the specified fraction.
     *
     * @param f
     *        a fraction
     *
     * @return a fraction
     */
    @Override
    public Fraction multiply(Fraction f) {

        MixedBinaryOperation<Number, Fraction, Result<Fraction>> function =
            (MixedBinaryOperation<Number, Fraction, Result<Fraction>>) OperationSingletons.getFunction(OperationIdentifiers.MULTIPLY_NUMBER_AND_FRACTION_FUNCTION);
        Result<Fraction> result = function.calculate(this, f);

        return result.result();
    }

    /**
     * Divides this number by the specified number.
     *
     * @param n
     *        a number
     *
     * @return an expression (e.g. a quotient, a mixed fraction or integer)
     */
    @Override
    public Fraction divideReturnFraction(Number n) {

        BinaryOperation<Number, Result<Fraction>> function =
            (BinaryOperation<Number, Result<Fraction>>) OperationSingletons.getFunction(OperationIdentifiers.DIVIDE_NUMBERS_RETURN_FRACTION_FUNCTION);
        Result<Fraction> result = function.calculate(this, n);

        return result.result();
    }

    /**
     * Divides this number by the specified fraction.
     *
     * @param f
     *        a fraction
     *
     * @return a fraction
     */
    @Override
    public Fraction divide(Fraction f) {

        MixedBinaryOperation<Number, Fraction, Result<Fraction>> function =
            (MixedBinaryOperation<Number, Fraction, Result<Fraction>>) OperationSingletons.getFunction(OperationIdentifiers.DIVIDE_NUMBER_BY_FRACTION_FUNCTION);
        Result<Fraction> result = function.calculate(this, f);

        return result.result();
    }

    /**
     * Divides this number by the specified number and returns the remainder of the division.
     *
     * @param n
     *        an integer
     *
     * @return the remainder of the division
     */
    @Override
    public Number modulo(Number n) {

        BinaryOperation<Number, Result<Number>> function =
            (BinaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.MODULO_FUNCTION);
        Result<Number> result = function.calculate(this, n);

        return result.result();
    }

    /**
     * Divides this number by the specified number and returns the result of the division.
     *
     * @param n
     *        an integer
     *
     * @return the result of the division
     */
    @Override
    public Number diviso(Number n) {

        BinaryOperation<Number, Result<Number>> function =
            (BinaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.DIVISO_FUNCTION);
        Result<Number> result = function.calculate(this, n);

        return result.result();
    }

    /**
     * Compares this number and the specified fraction and returns the greater number (as fraction).
     *
     * @param f
     *        a fraction
     *
     * @return a fraction
     */
    @Override
    public Fraction max(Fraction f) {

        MixedBinaryOperation<Number, Fraction, Result<Fraction>> function =
            (MixedBinaryOperation<Number, Fraction, Result<Fraction>>) OperationSingletons.getFunction(OperationIdentifiers.MAX_NUMBER_FRACTION_FUNCTION);
        Result<Fraction> result = function.calculate(this, f);

        return result.result();
    }

    /**
     * Compares this number and the specified number and returns the greater number.
     *
     * @param n
     *        a number
     *
     * @return a number
     */
    @Override
    public Number max(Number n) {

        BinaryOperation<Number, Result<Number>> function =
            (BinaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.MAX_NUMBER_FUNCTION);
        Result<Number> result = function.calculate(this, n);

        return result.result();
    }

    /**
     * Compares this number and the specified fraction and returns the smaller number (as fraction).
     *
     * @param f
     *        a fraction
     *
     * @return a fraction
     */
    @Override
    public Fraction min(Fraction f) {

        MixedBinaryOperation<Number, Fraction, Result<Fraction>> function =
            (MixedBinaryOperation<Number, Fraction, Result<Fraction>>) OperationSingletons.getFunction(OperationIdentifiers.MIN_NUMBER_FRACTION_FUNCTION);
        Result<Fraction> result = function.calculate(this, f);

        return result.result();
    }

    /**
     * Compares this number and the specified number and returns the smaller number.
     *
     * @param n
     *        a number
     *
     * @return a number
     */
    @Override
    public Number min(Number n) {

        BinaryOperation<Number, Result<Number>> function =
            (BinaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.MIN_NUMBER_FUNCTION);
        Result<Number> result = function.calculate(this, n);

        return result.result();
    }

    /**
     * Exponentiates this number by the specified exponent.
     *
     * @param exponent
     *        an exponent (must be an integer)
     *
     * @return the result
     */
    @Override
    public Number exponentiate(Number exponent) {

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(ProcessingDetails.DEFAULT_ALGORITHM,
                                                   ProcessingDetails.DEFAULT_PRECISION,
                                                   ProcessingDetails.DEFAULT_ITERATION_DEPTH);

        return exponentiate(processingDetails, exponent);
    }

    /**
     * Exponentiates this number by the specified exponent.
     *
     * @param processingDetails
     *        additonal processing details
     * @param exponent
     *        an exponent (must be an integer)
     *
     * @return the result
     */
    @Override
    public Number exponentiate(ProcessingDetails processingDetails, Number exponent) {

        ParameterCheckHelper.checkParameter(processingDetails);

        final OperationIdentifier[] ALLOWED_ALGORITHMS = new OperationIdentifier[] {
            OperationIdentifiers.EXPONENTIATE_NUMBER_WITH_NUMBER_BY_SQUARING_FUNCTION,
            OperationIdentifiers.EXPONENTIATE_NUMBER_WITH_NUMBER_FUNCTION
        };

        OperationIdentifier algorithm = processingDetails.checkAndReturnAlgorithm(ALLOWED_ALGORITHMS);
        Number decimalPlaces =
            processingDetails.checkAndReturnPrecision(Math.DEFAULT_MAXIMUM_FRACTION_LENGTH.value(base));

        TernaryOperation<Number, Result<Number>> function =
            (TernaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(algorithm);
        Result<Number> result = function.calculate(this, exponent, decimalPlaces);

        return result.result();
    }

    /**
     * Exponentiates this number by the specified exponent.
     *
     * @param exponent
     *        an exponent
     *
     * @return the result
     */
    @Override
    public Number exponentiate(Fraction exponent) {

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(ProcessingDetails.DEFAULT_ALGORITHM,
                                                   ProcessingDetails.DEFAULT_PRECISION,
                                                   ProcessingDetails.DEFAULT_ITERATION_DEPTH);

        return exponentiate(processingDetails, exponent);
    }

    /**
     * Exponentiates this number by the specified exponent.
     *
     * @param processingDetails
     *        additonal processing details
     * @param exponent
     *        an exponent
     *
     * @return the result
     */
    @Override
    public Number exponentiate(ProcessingDetails processingDetails, Fraction exponent) {

        ParameterCheckHelper.checkParameter(processingDetails);

        final OperationIdentifier[] ALLOWED_ALGORITHMS = new OperationIdentifier[] {
            OperationIdentifiers.EXPONENTIATE_NUMBER_WITH_FRACTION_FUNCTION };

        OperationIdentifier algorithm = processingDetails.checkAndReturnAlgorithm(ALLOWED_ALGORITHMS);
        Number decimalPlaces =
            processingDetails.checkAndReturnPrecision(Math.DEFAULT_MAXIMUM_FRACTION_LENGTH.value(base));
        Number iterations =
            processingDetails.checkAndReturnIterationDepth(Math.DEFAULT_NTH_ROOT_ITERATIONS.value(base));

        MixedQuaternaryOperation<Number, Fraction, Result<Number>> function =
            (MixedQuaternaryOperation<Number, Fraction, Result<Number>>) OperationSingletons.getFunction(algorithm);
        Result<Number> result = function.calculate(this, exponent, iterations, decimalPlaces);

        return result.result();
    }

    /**
     * Takes the specified number as base and claculates the logarithm for this number.
     *
     * @param n
     *        a number
     *
     * @return a Number
     */
    @Override
    public Number log(Number n) {

        // TODO
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the absolute value of this number.
     *
     * @return the absolute value
     */
    @Override
    public Number absoluteValue() {

        UnaryOperation<Number, Result<Number>> function =
            (UnaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.NUMBER_TO_ABSOLUTE_VALUE_FUNCTION);
        Result<Number> result = function.calculate(this);

        return result.result();
    }

    /**
     * Returns a truncated number (i.e. where the fractional part is removed).
     *
     * @return a truncated number
     */
    @Override
    public Number removeFractionPart() {

        UnaryOperation<Number, Result<Number>> function =
            (UnaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.REMOVE_FRACTION_PART_FUNCTION);
        Result<Number> result = function.calculate(this);

        return result.result();
    }

    /**
     * Returns a truncated number (i.e. where the integer part is removed).
     *
     * @return a truncated number
     */
    @Override
    public Number removeIntegerPart() {

        UnaryOperation<Number, Result<Number>> function =
            (UnaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.REMOVE_INTEGER_PART_FUNCTION);
        Result<Number> result = function.calculate(this);

        return result.result();
    }

    /**
     * Returns a number where the decimal point is shifted to the left by one digit.
     *
     * @return a number
     */
    @Override
    public Number shiftLeft() {

        final Number ONE = Math.ONE.value(base());

        return shiftLeft(ONE);
    }

    /**
     * Returns a number where the decimal point is shifted to the left by the number of specified
     * shifts.
     *
     * @param shifts
     *        the nuber of shifts to be performed. Zero will perform no shift. A positive number
     *        will perform shifts to the left. A negative number will perform shifts to the right.
     *
     * @return a number
     */
    @Override
    public Number shiftLeft(Number shifts) {

        BinaryOperation<Number, Result<Number>> function =
            (BinaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.SHIFT_LEFT_FUNCTION);
        Result<Number> result = function.calculate(this, shifts);

        return result.result();
    }

    /**
     * Returns a number where the decimal point is shifted to the right by one digit.
     *
     * @return a number
     */
    @Override
    public Number shiftRight() {

        final Number ONE = Math.ONE.value(base());

        return shiftRight(ONE);
    }

    /**
     * Returns a number where the decimal point is shifted to the right by the number of specified
     * shifts.
     *
     * @param shifts
     *        the nuber of shifts to be performed. Zero will perform no shift. A positive number
     *        will perform shifts to the right. A negative number will perform shifts to the left.
     *
     * @return a number
     */
    @Override
    public Number shiftRight(Number shifts) {

        BinaryOperation<Number, Result<Number>> function =
            (BinaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.SHIFT_RIGHT_FUNCTION);
        Result<Number> result = function.calculate(this, shifts);

        return result.result();
    }

    /**
     * Translates this number into a number of the specified base.
     *
     * @param base
     *        the new base
     *
     * @return a number
     */
    @Override
    public Number rebase(int base) {

        MixedBinaryOperation<Number, Integer, Result<Number>> function =
            (MixedBinaryOperation<Number, Integer, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.REBASE_NUMBER_FUNCTION);
        Result<Number> result = function.calculate(this, base);

        return result.result();
    }

    /**
     * Increments this number by one.
     *
     * @return a number
     */
    @Override
    public Number inc() {

        UnaryOperation<Number, Result<Number>> function =
            (UnaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.NUMBER_INCREMENT_FUNCTION);
        Result<Number> result = function.calculate(this);

        return result.result();
    }

    /**
     * Decrements this number by one.
     *
     * @return a number
     */
    @Override
    public Number dec() {

        UnaryOperation<Number, Result<Number>> function =
            (UnaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.NUMBER_DECREMENT_FUNCTION);
        Result<Number> result = function.calculate(this);

        return result.result();
    }

    /**
     * Checks if this number is an even integer.
     *
     * @return <code>true</code> if this number is an even integer, else <code>false</code>
     */
    @Override
    public boolean isEven() {

        UnaryOperation<Number, Result<Boolean>> function =
            (UnaryOperation<Number, Result<Boolean>>) OperationSingletons.getFunction(OperationIdentifiers.EVEN_NUMBER_FUNCTION);
        Result<Boolean> result = function.calculate(this);

        return result.result();
    }

    /**
     * Checks if this number is an odd integer.
     *
     * @return <code>true</code> if this number is an odd integer, else <code>false</code>
     */
    @Override
    public boolean isOdd() {

        UnaryOperation<Number, Result<Boolean>> function =
            (UnaryOperation<Number, Result<Boolean>>) OperationSingletons.getFunction(OperationIdentifiers.ODD_NUMBER_FUNCTION);
        Result<Boolean> result = function.calculate(this);

        return result.result();
    }

    /**
     * Checks if this number is greater than the specified number.
     *
     * @param number
     *        a number
     *
     * @return <code>true</code> if this number is greater than the specified number, else <code>false</code>
     */
    @Override
    public boolean isGreater(Number number) {

        BinaryOperation<Number, Result<Boolean>> function =
            (BinaryOperation<Number, Result<Boolean>>) OperationSingletons.getFunction(OperationIdentifiers.NUMBER_GREATER_THAN_COMPARISON_FUNCTION);
        Result<Boolean> result = function.calculate(this, number);

        return result.result();
    }

    /**
     * Checks if this number is greater than the specified fraction.
     *
     * @param fraction
     *        a fraction
     *
     * @return <code>true</code> if this number is greater than the specified fraction, else <code>false</code>
     */
    @Override
    public boolean isGreater(Fraction fraction) {

        MixedBinaryOperation<Number, Fraction, Result<Boolean>> function =
            (MixedBinaryOperation<Number, Fraction, Result<Boolean>>) OperationSingletons.getFunction(OperationIdentifiers.NUMBER_GREATER_THAN_FRACTION_COMPARISON_FUNCTION);
        Result<Boolean> result = function.calculate(this, fraction);

        return result.result();
    }

    /**
     * Checks if this number is greater than or equal to the specified number.
     *
     * @param number
     *        a number
     *
     * @return <code>true</code> if this number is greater than or equal to the specified number, else <code>false</code>
     */
    @Override
    public boolean isGreaterOrEqual(Number number) {

        BinaryOperation<Number, Result<Boolean>> function =
            (BinaryOperation<Number, Result<Boolean>>) OperationSingletons.getFunction(OperationIdentifiers.NUMBER_GREATER_THAN_OR_EQUAL_COMPARISON_FUNCTION);
        Result<Boolean> result = function.calculate(this, number);

        return result.result();
    }

    /**
     * Checks if this number is greater than or equal to the specified fraction.
     *
     * @param fraction
     *        a fraction
     *
     * @return <code>true</code> if this number is greater than or equal to the specified fraction, else <code>false</code>
     */
    @Override
    public boolean isGreaterOrEqual(Fraction fraction) {

        MixedBinaryOperation<Number, Fraction, Result<Boolean>> function =
            (MixedBinaryOperation<Number, Fraction, Result<Boolean>>) OperationSingletons.getFunction(OperationIdentifiers.NUMBER_GREATER_THAN_OR_EQUAL_FRACTION_COMPARISON_FUNCTION);
        Result<Boolean> result = function.calculate(this, fraction);

        return result.result();
    }

    /**
     * Checks if this number is lesser than the specified number.
     *
     * @param number
     *        a number
     *
     * @return <code>true</code> if this number is lesser than the specified number, else <code>false</code>
     */
    @Override
    public boolean isLesser(Number number) {

        BinaryOperation<Number, Result<Boolean>> function =
            (BinaryOperation<Number, Result<Boolean>>) OperationSingletons.getFunction(OperationIdentifiers.NUMBER_LESSER_THAN_COMPARISON_FUNCTION);
        Result<Boolean> result = function.calculate(this, number);

        return result.result();
    }

    /**
     * Checks if this number is lesser than the specified fraction.
     *
     * @param fraction
     *        a fraction
     *
     * @return <code>true</code> if this number is lesser than the specified fraction, else <code>false</code>
     */
    @Override
    public boolean isLesser(Fraction fraction) {

        MixedBinaryOperation<Number, Fraction, Result<Boolean>> function =
            (MixedBinaryOperation<Number, Fraction, Result<Boolean>>) OperationSingletons.getFunction(OperationIdentifiers.NUMBER_LESSER_THAN_FRACTION_COMPARISON_FUNCTION);
        Result<Boolean> result = function.calculate(this, fraction);

        return result.result();
    }

    /**
     * Checks if this number is lesser than or equal to the specified number.
     *
     * @param number
     *        a number
     *
     * @return <code>true</code> if this number is lesser than or equal to the specified number, else <code>false</code>
     */
    @Override
    public boolean isLesserOrEqual(Number number) {

        BinaryOperation<Number, Result<Boolean>> function =
            (BinaryOperation<Number, Result<Boolean>>) OperationSingletons.getFunction(OperationIdentifiers.NUMBER_LESSER_THAN_OR_EQUAL_COMPARISON_FUNCTION);
        Result<Boolean> result = function.calculate(this, number);

        return result.result();
    }

    /**
     * Checks if this number is lesser than or equal to the specified fraction.
     *
     * @param fraction
     *        a fraction
     *
     * @return <code>true</code> if this number is lesser than or equal to the specified fraction, else <code>false</code>
     */
    @Override
    public boolean isLesserOrEqual(Fraction fraction) {

        MixedBinaryOperation<Number, Fraction, Result<Boolean>> function =
            (MixedBinaryOperation<Number, Fraction, Result<Boolean>>) OperationSingletons.getFunction(OperationIdentifiers.NUMBER_LESSER_THAN_OR_EQUAL_FRACTION_COMPARISON_FUNCTION);
        Result<Boolean> result = function.calculate(this, fraction);

        return result.result();
    }

    /**
     * Returns the reciprocal of this number.
     *
     * @return a fraction
     */
    @Override
    public Fraction reciprocal() {

        UnaryOperation<Number, Result<Fraction>> function =
            (UnaryOperation<Number, Result<Fraction>>) OperationSingletons.getFunction(OperationIdentifiers.RECIPROCAL_OF_NUMBER_FUNCTION);
        Result<Fraction> result = function.calculate(this);

        return result.result();
    }

    /**
     * Round this number down to the nearest integer that doesn't exceed this number.
     *
     * @return the nearest integer that doesn't exceed this number
     */
    @Override
    public Number roundDown() {

        UnaryOperation<Number, Result<Number>> function =
            (UnaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.ROUND_DOWN_NUMBER_FUNCTION);
        Result<Number> result = function.calculate(this);

        return result.result();
    }

    /**
     * Round this number up to the nearest integer that is not less than this number.
     *
     * @return the nearest integer that is not less than this number
     */
    @Override
    public Number roundUp() {

        UnaryOperation<Number, Result<Number>> function =
            (UnaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.ROUND_UP_NUMBER_FUNCTION);
        Result<Number> result = function.calculate(this);

        return result.result();
    }

    /**
     * Shorten the precision of this number according to the specified decimal places.
     *
     * @return a rounded number
     */
    @Override
    public Number round() {

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(ProcessingDetails.DEFAULT_ALGORITHM,
                                                   ProcessingDetails.DEFAULT_PRECISION,
                                                   ProcessingDetails.DEFAULT_ITERATION_DEPTH);

        return round(processingDetails);
    }

    /**
     * Shorten the precision of this number according to the specified decimal places.
     *
     * @param processingDetails
     *        additonal processing details
     *
     * @return a shortened number according to the specified precision
     */
    @Override
    public Number round(ProcessingDetails processingDetails) {

        ParameterCheckHelper.checkParameter(processingDetails);

        final OperationIdentifier[] ALLOWED_ALGORITHMS = new OperationIdentifier[] {
            OperationIdentifiers.ROUND_NUMBER_TO_ODD_FUNCTION, OperationIdentifiers.ROUND_NUMBER_TO_EVEN_FUNCTION
        };

        OperationIdentifier algorithm = processingDetails.checkAndReturnAlgorithm(ALLOWED_ALGORITHMS);
        Number decimalPlaces =
            processingDetails.checkAndReturnPrecision(Math.DEFAULT_MAXIMUM_FRACTION_LENGTH.value(base));

        BinaryOperation<Number, Result<Number>> function =
            (BinaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(algorithm);
        Result<Number> result = function.calculate(this, decimalPlaces);

        return result.result();
    }

    /**
     * Checks if this number is a multiple of the specified number.
     *
     * @param number
     *        a number
     *
     * @return <code>true</code> if this number is a multiple of the specified number, else <code>false</code>
     */
    @Override
    public boolean isMultipleOf(Number number) {

        BinaryOperation<Number, Result<Boolean>> function =
            (BinaryOperation<Number, Result<Boolean>>) OperationSingletons.getFunction(OperationIdentifiers.IS_MULTIPLE_FUNCTION);
        Result<Boolean> result = function.calculate(this, number);

        return result.result();
    }

    /**
     * Divides this number by the specified number.
     *
     * @param n
     *        a number
     *
     * @return the quotient
     */
    @Override
    public Number divide(Number n) {

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(ProcessingDetails.DEFAULT_ALGORITHM,
                                                   ProcessingDetails.DEFAULT_PRECISION,
                                                   ProcessingDetails.DEFAULT_ITERATION_DEPTH);

        return divide(processingDetails, n);
    }

    /**
     * Divides this number by the specified number.
     *
     * @param processingDetails
     *        additonal processing details
     * @param n
     *        a number
     *
     * @return the quotient
     */
    @Override
    public Number divide(ProcessingDetails processingDetails, Number n) {

        ParameterCheckHelper.checkParameter(processingDetails);

        final OperationIdentifier[] ALLOWED_ALGORITHMS = new OperationIdentifier[] {
            OperationIdentifiers.DIVIDE_NUMBERS_BY_SUBTRACTION, OperationIdentifiers.RUSSIAN_DIVISION_FUNCTION,
            OperationIdentifiers.LONG_DIVISION
        };

        OperationIdentifier algorithm = processingDetails.checkAndReturnAlgorithm(ALLOWED_ALGORITHMS);
        Number decimalPlaces =
            processingDetails.checkAndReturnPrecision(Math.DEFAULT_MAXIMUM_FRACTION_LENGTH.value(base));

        TernaryOperation<Number, Result<Number>> function =
            (TernaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(algorithm);
        Result<Number> result = function.calculate(this, n, decimalPlaces);

        return result.result();
    }

    /**
     * Checks if this number is a prime number.
     *
     * @return <code>true</code> if this number is a prime number, else <code>false</code>
     */
    @Override
    public boolean isPrime() {

        UnaryOperation<Number, Result<Boolean>> function =
            (UnaryOperation<Number, Result<Boolean>>) OperationSingletons.getFunction(OperationIdentifiers.IS_PRIME_FUNCTION);
        Result<Boolean> result = function.calculate(this);

        return result.result();
    }

    /**
     * Calculates the square for this number.
     *
     * @return a number
     */
    @Override
    public Number square() {

        UnaryOperation<Number, Result<Number>> function =
            (UnaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.SQUARE_NUMBER_FUNCTION);
        Result<Number> result = function.calculate(this);

        return result.result();
    }

    /**
     * Determines all divisors of this number.
     *
     * @return a set containing all divisors or an empty set if there are no divisors
     */
    @Override
    public Set<Number> divisors() {

        UnaryOperation<Number, Result<Set<Number>>> function =
            (UnaryOperation<Number, Result<Set<Number>>>) OperationSingletons.getFunction(OperationIdentifiers.DETERMINE_DIVISORS_FUNCTION);
        Result<Set<Number>> result = function.calculate(this);

        return result.result();
    }

    /**
     * Determines all common divisors of this number and the specified number.
     *
     * @param number
     *        a number
     *
     * @return a set containing all common divisors or an empty set if there are no common divisors
     */
    @Override
    public Set<Number> commonDivisors(Number number) {

        BinaryOperation<Number, Result<Set<Number>>> function =
            (BinaryOperation<Number, Result<Set<Number>>>) OperationSingletons.getFunction(OperationIdentifiers.DETERMINE_COMMON_DIVISORS_OF_NUMBERS);
        Result<Set<Number>> result = function.calculate(this, number);

        return result.result();
    }

    /**
     * Determines the prime factors for this number. The result sequence contains all prime factors.
     *
     * @return a sequnce of prime factors or an empty sequence if there are no prime factors
     */
    @Override
    public Sequence<Number> primeFactors() {

        UnaryOperation<Number, Result<Sequence<Number>>> function =
            (UnaryOperation<Number, Result<Sequence<Number>>>) OperationSingletons.getFunction(OperationIdentifiers.DETERMINE_PRIME_FACTORS_OF_NUMBER);
        Result<Sequence<Number>> result = function.calculate(this);

        return result.result();
    }

    /**
     * Translates this number into a fraction.
     *
     * @return a fraction
     */
    @Override
    public Fraction toFraction() {

        UnaryOperation<Number, Result<Fraction>> function =
            (UnaryOperation<Number, Result<Fraction>>) OperationSingletons.getFunction(OperationIdentifiers.NUMBER_TO_FRACTION_FUNCTION);
        Result<Fraction> result = function.calculate(this);

        return result.result();
    }

    /**
     * Converts this object to a primitive byte value.
     *
     * @return a byte value
     */
    @Override
    public byte toPrimitiveByte() {

        Byte b = toByte();

        return b.byteValue();
    }

    /**
     * Converts this object to a primitive short value.
     *
     * @return a short value
     */
    @Override
    public short toPrimitiveShort() {

        Short s = toShort();

        return s.shortValue();
    }

    /**
     * Converts this object to a primitive int value.
     *
     * @return a int value
     */
    @Override
    public int toPrimitiveInt() {

        Integer i = toInteger();

        return i.intValue();
    }

    /**
     * Converts this object to a primitive long value.
     *
     * @return a long value
     */
    @Override
    public long toPrimitiveLong() {

        Long l = toLong();

        return l.longValue();
    }

    /**
     * Converts this object to a primitive float value.
     *
     * @return a float value
     */
    @Override
    public float toPrimitiveFloat() {

        Float f = toFloat();

        return f.floatValue();
    }

    /**
     * Converts this object to a primitive double value.
     *
     * @return a double value
     */
    @Override
    public double toPrimitiveDouble() {

        Double d = toDouble();

        return d.doubleValue();
    }

    /**
     * Converts this object to a byte wrapper.
     *
     * @return a byte wrapper
     */
    @Override
    public Byte toByte() {

        Number n = rebase(10);
        n = n.removeFractionPart();
        return Byte.parseByte(n.toString());
    }

    /**
     * Converts this object to a short value.
     *
     * @return a short wrapper
     */
    @Override
    public Short toShort() {

        Number n = rebase(10);
        n = n.removeFractionPart();
        return Short.parseShort(n.toString());
    }

    /**
     * Converts this object to a integer wrapper.
     *
     * @return a integer wrapper
     */
    @Override
    public Integer toInteger() {

        Number n = rebase(10);
        n = n.removeFractionPart();
        return Integer.parseInt(n.toString());
    }

    /**
     * Converts this object to a long wrapper.
     *
     * @return a long wrapper
     */
    @Override
    public Long toLong() {

        Number n = rebase(10);
        n = n.removeFractionPart();
        return Long.parseLong(n.toString());
    }

    /**
     * Converts this object to a float wrapper.
     *
     * @return a float wrapper
     */
    @Override
    public Float toFloat() {

        Number n = rebase(10);
        return Float.parseFloat(n.toString());
    }

    /**
     * Converts this object to a double wrapper.
     *
     * @return a double wrapper
     */
    @Override
    public Double toDouble() {

        Number n = rebase(10);
        return Double.parseDouble(n.toString());
    }

    /**
     * Calculates the digit sum for this number.
     *
     * @return the digit sum
     */
    @Override
    public Number digitSum() {

        UnaryOperation<Number, Result<Number>> function =
            (UnaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.DIGIT_SUM_FUNCTION);
        Result<Number> result = function.calculate(this);

        return result.result();
    }

    /**
     * Checks if this number consists of a single digit.
     *
     * @return <code>true</code> if this number consists of a single digit, else <code>false</code>
     */
    @Override
    public boolean isSingleDigit() {

        UnaryOperation<Number, Result<Boolean>> function =
            (UnaryOperation<Number, Result<Boolean>>) OperationSingletons.getFunction(OperationIdentifiers.IS_SINGLE_DIGIT_FUNCTION);
        Result<Boolean> result = function.calculate(this);

        return result.result();
    }

    /**
     * Returns the total number of digits.
     *
     * @return the total number of digits
     */
    @Override
    public Number digits() {

        Number digitsLeft = digitsLeft();
        Number digitsRight = digitsRight();

        Number sum = digitsLeft.add(digitsRight);

        return sum;
    }

    /**
     * Returns the total number of digits left of the decimal separator.
     *
     * @return the total number of digits left of the decimal separator
     */
    @Override
    public Number digitsLeft() {

        Number absoluteNumber = absoluteValue();

        if (absoluteNumber.isInfinity()) {

            return NumberHelper.createNumber(CLONE, absoluteNumber);
        }

        Number digits = Math.ZERO.value(base);
        DigitNode node = absoluteNumber.centerNode();

        while (node != null) {

            digits = digits.inc();
            node = node.leftNode();
        }

        return digits;
    }

    /**
     * Returns the total number of digits right of the decimal separator.
     *
     * @return the total number of digits right of the decimal separator
     */
    @Override
    public Number digitsRight() {

        Number absoluteNumber = absoluteValue();

        if (absoluteNumber.isInfinity()) {

            return NumberHelper.createNumber(CLONE, absoluteNumber);
        }

        Number digits = Math.ZERO.value(base);
        DigitNode node = absoluteNumber.centerNode();
        node = node.rightNode();

        while (node != null) {

            digits = digits.inc();
            node = node.rightNode();
        }

        return digits;
    }

    /**
     * Calculates the sine of this number (in radian).
     *
     * @return the sine of this number
     */
    @Override
    public Number sine() {

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(ProcessingDetails.DEFAULT_ALGORITHM,
                                                   ProcessingDetails.DEFAULT_PRECISION,
                                                   ProcessingDetails.DEFAULT_ITERATION_DEPTH);

        return sine(processingDetails);
    }

    /**
     * Calculates the sine of this number (in radian) according to the specified proessing parameters.
     *
     * @param processingDetails
     *        procewssing parameters
     *
     * @return the sine of this number
     */
    @Override
    public Number sine(ProcessingDetails processingDetails) {

        ParameterCheckHelper.checkParameter(processingDetails);

        final OperationIdentifier[] ALLOWED_ALGORITHMS = new OperationIdentifier[] {
            OperationIdentifiers.SINE_APPROXIMATION_FUNCTION, OperationIdentifiers.SINE_APPROXIMATION_2_FUNCTION
        };

        OperationIdentifier algorithm = processingDetails.checkAndReturnAlgorithm(ALLOWED_ALGORITHMS);

        Number iterations =
            processingDetails.checkAndReturnIterationDepth(Math.DEFAULT_SINE_APPROXIMATION_ITERATIONS.value(base));
        Number decimalPlaces =
            processingDetails.checkAndReturnPrecision(Math.DEFAULT_MAXIMUM_FRACTION_LENGTH.value(base));

        TernaryOperation<Number, Result<Number>> function =
            (TernaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(algorithm);
        Result<Number> result = function.calculate(this, iterations, decimalPlaces);

        return result.result();
    }

    /**
     * Calculates the cosine of this number (in radian).
     *
     * @return the cosine of this number
     */
    @Override
    public Number cosine() {

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(ProcessingDetails.DEFAULT_ALGORITHM,
                                                   ProcessingDetails.DEFAULT_PRECISION,
                                                   ProcessingDetails.DEFAULT_ITERATION_DEPTH);

        return cosine(processingDetails);
    }

    /**
     * Calculates the cosine of this number (in radian) according to the specified proessing parameters.
     *
     * @param processingDetails
     *        procewssing parameters
     *
     * @return the cosine of this number
     */
    @Override
    public Number cosine(ProcessingDetails processingDetails) {

        ParameterCheckHelper.checkParameter(processingDetails);

        Number iterations =
            processingDetails.checkAndReturnIterationDepth(Math.DEFAULT_COSINE_APPROXIMATION_ITERATIONS.value(base));
        Number decimalPlaces =
            processingDetails.checkAndReturnPrecision(Math.DEFAULT_MAXIMUM_FRACTION_LENGTH.value(base));

        TernaryOperation<Number, Result<Number>> function =
            (TernaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.COSINE_APPROXIMATION_FUNCTION);
        Result<Number> result = function.calculate(this, iterations, decimalPlaces);

        return result.result();
    }

    /**
     * Determines the common prime factors of this number and the specified number.
     *
     * @param number
     *        a number
     *
     * @return a sequence of common prime factors or an empty sequence if there are no common prime factors
     */
    @Override
    public Sequence<Number> commonPrimeFactors(Number number) {

        BinaryOperation<Number, Result<Sequence<Number>>> function =
            (BinaryOperation<Number, Result<Sequence<Number>>>) OperationSingletons.getFunction(OperationIdentifiers.DETERMINE_COMMON_PRIME_FACTORS_IN_NUMBERS);
        Result<Sequence<Number>> result = function.calculate(this, number);

        return result.result();
    }

}
