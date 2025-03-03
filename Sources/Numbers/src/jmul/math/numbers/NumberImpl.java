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


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import jmul.math.Math;
import jmul.math.fractions.Fraction;
import jmul.math.functions.FunctionSingletons;
import jmul.math.functions.repository.FunctionIdentifier;
import jmul.math.functions.repository.FunctionIdentifiers;
import jmul.math.hash.HashHelper;
import static jmul.math.numbers.Constants.DEFAULT_BASE;
import static jmul.math.numbers.ParameterHelper.checkBase;
import static jmul.math.numbers.ParameterHelper.checkSign;
import jmul.math.numbers.exceptions.NumberParsingException;
import jmul.math.numbers.nodes.DigitNode;
import jmul.math.numbers.nodes.NodesHelper;
import jmul.math.numbers.notations.NotationFunction;
import jmul.math.numbers.notations.NotationParser;
import jmul.math.numbers.notations.ParsingResult;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.EqualityFunction;
import jmul.math.operations.MixedBinaryOperation;
import jmul.math.operations.MixedComparator;
import jmul.math.operations.MixedEqualityFunction;
import jmul.math.operations.Result;
import jmul.math.operations.UnaryOperation;
import jmul.math.signs.Sign;
import jmul.math.signs.Signs;


/**
 * An implementation of a real number. This implementation requires memory depending on the number of digits
 * the number consists of. Even very large or very small numbers can be represented without rounding. Since the
 * underlying data structure is a linked list numbers can exceed the array size limitations in java.<br>
 * It might not be possible to create a string representation for every number (see array size limitations).<br>
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
     * A list of parser functions.
     */
    private static final List<FunctionIdentifier> PARSER_FUNCTION_LIST;

    /*
     * The static initializer.
     */
    static {

        List<FunctionIdentifiers> tmpList = new ArrayList<>();

        /*
         * Because of ambiguities we first test if the input matches the scientific notation, i.e. the letter
         * E or e is used as separator between mantissa and exponent. This letter can also represent a digit.
         * Thus the order of the parser functions is essential.
         */
        tmpList.add(FunctionIdentifiers.SCIENTIFIC_NOTATION_PARSER);
        tmpList.add(FunctionIdentifiers.STANDARD_NOTATION_PARSER);

        PARSER_FUNCTION_LIST = Collections.unmodifiableList(tmpList);
    }

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
    public NumberImpl() {

        this(DEFAULT_BASE);
    }

    /**
     * Creates a number which represents positive infinity.
     *
     * @param base
     *        the base for this number
     */
    public NumberImpl(int base) {

        this(base, Signs.POSITIVE);
    }

    /**
     * Creates a number which represents positive or negative infinity. The default base is <code>10</code>.
     *
     * @param sign
     *        a sign for this number
     */
    public NumberImpl(Sign sign) {

        this(DEFAULT_BASE, sign);
    }

    /**
     * Creates a number which represents positive or negative infinity.
     *
     * @param base
     *        the base for this number
     * @param sign
     *        a sign for this number
     */
    public NumberImpl(int base, Sign sign) {

        super();

        this.base = checkBase(base);
        this.sign = checkSign(sign);
        centerNode = null;
    }

    /**
     * Creates a number according to the specified parameters. The default base is <code>10</code>.
     *
     * @param s
     *        a string containing a number
     */
    public NumberImpl(CharSequence s) {

        this(DEFAULT_BASE, s);
    }

    /**
     * Creates a number according to the specified parameters.
     *
     * @param s
     *        a string containing a number
     */
    public NumberImpl(String s) {

        this((CharSequence) s);
    }

    /**
     * Creates a number according to the specified parameters.
     *
     * @param base
     *        the base for this number
     * @param s
     *        a string containing a number
     */
    public NumberImpl(int base, CharSequence s) {

        this(parseString(base, s));
    }

    /**
     * Creates a number according to the specified parameters.
     *
     * @param base
     *        the base for this number
     * @param s
     *        a string containing a number
     */
    public NumberImpl(int base, String s) {

        this(base, (CharSequence) s);
    }

    /**
     * Creates a number according to the specified parameters.
     *
     * @param parsingResult
     *        the result of parsing a string
     */
    private NumberImpl(ParsingResult parsingResult) {

        this(parsingResult.base, parsingResult.sign, parsingResult.centerNode);
    }

    /**
     * This function wraps parsing a string.
     *
     * @param base
     *        the base for this number
     * @param s
     *        a string which represetns a number
     *
     * @return the parsing result
     */
    private static ParsingResult parseString(int base, CharSequence s) {

        List<Throwable> exceptions = new ArrayList<>();

        for (FunctionIdentifier identifier : PARSER_FUNCTION_LIST) {

            NotationParser parser = (NotationParser) FunctionSingletons.getFunction(identifier);

            try {

                return parser.parseNotation(base, s.toString());

            } catch (IllegalArgumentException e) {

                // Ignore the exception for now because it might not be the matching notation.
                // Remember the exception for later if needed.
                exceptions.add(e);
            }
        }

        throw new NumberParsingException(base, s, exceptions);
    }

    /**
     * A copy constructor.
     *
     * @param number
     *        another number
     */
    public NumberImpl(Number number) {

        this(number.base(), number.sign(), NodesHelper.cloneLinkedList(number.centerNode()));
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
    public NumberImpl(int base, Sign sign, DigitNode centerNode) {

        super();

        this.sign = sign;
        this.base = base;
        this.centerNode = centerNode;
    }

    /**
     * Creates a new number according to the specified parameter. The default base is <code>10</code>.
     *
     * @param b
     *        a byte value
     */
    public NumberImpl(Byte b) {

        this((java.lang.Number) b);
    }

    /**
     * Creates a new number according to the specified parameter. The default base is <code>10</code>.
     *
     * @param s
     *        a short value
     */
    public NumberImpl(Short s) {

        this((java.lang.Number) s);
    }

    /**
     * Creates a new number according to the specified parameter. The default base is <code>10</code>.
     *
     * @param i
     *        an integer value
     */
    public NumberImpl(Integer i) {

        this((java.lang.Number) i);
    }

    /**
     * Creates a new number according to the specified parameter. The default base is <code>10</code>.
     *
     * @param l
     *        a long value
     */
    public NumberImpl(Long l) {

        this((java.lang.Number) l);
    }

    /**
     * Creates a new number according to the specified parameter. The default base is <code>10</code>.
     *
     * @param f
     *        a float value
     */
    public NumberImpl(Float f) {

        this((java.lang.Number) f);
    }

    /**
     * Creates a new number according to the specified parameter. The default base is <code>10</code>.
     *
     * @param d
     *        a double value
     */
    public NumberImpl(Double d) {

        this((java.lang.Number) d);
    }

    /**
     * Creates a new number according to the specified parameter. The default base is <code>10</code>.
     *
     * @param n
     *        a number
     */
    public NumberImpl(java.lang.Number n) {

        this((checkNumberParameter(n)).toString());
    }

    private static java.lang.Number checkNumberParameter(java.lang.Number n) {

        if (n == null) {

            throw new NullPointerException();
        }

        return n;
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
            (NotationFunction) FunctionSingletons.getFunction(FunctionIdentifiers.SCIENTIFIC_NOTATION_FUNCTION);

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
            (NotationFunction) FunctionSingletons.getFunction(FunctionIdentifiers.STANDARD_NOTATION_FUNCTION);

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
     * To the left are the digits which represent the fraction and positions -1 (<code>base^-1</code>),
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
                (MixedComparator<Number, Fraction>) FunctionSingletons.getFunction(FunctionIdentifiers.NUMBER_FRACTION_COMPARATOR_FUNCTION);
            return comparator.compare(this, other);

        } else if (o instanceof Number) {

            Number other = (Number) o;

            Comparator<Number> comparator =
                (Comparator<Number>) FunctionSingletons.getFunction(FunctionIdentifiers.NUMBER_COMPARATOR_FUNCTION);
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
     * @return <code>true</code> if this number is euqal to the specified number, else <code>false</code>
     */
    @Override
    public boolean equals(Object o) {

        if (o instanceof Number) {

            Number other = (Number) o;

            EqualityFunction<Number> function =
                (EqualityFunction<Number>) FunctionSingletons.getFunction(FunctionIdentifiers.NUMBER_EQUALITY_FUNCTION);
            boolean result = function.equals(this, other);

            return result;

        } else if (o instanceof Fraction) {

            Fraction other = (Fraction) o;

            MixedEqualityFunction<Number, Fraction> function =
                (MixedEqualityFunction<Number, Fraction>) FunctionSingletons.getFunction(FunctionIdentifiers.NUMBER_FRACTION_EQUALITY_FUNCTION);
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
            (UnaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.NEGATE_NUMBER_FUNCTION);
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
            (UnaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.NUMBER_COMPLEMENT_FUNCTION);
        Result<Number> result = function.calculate(this);

        return result.result();
    }

    /**
     * Calculates the faculty for this number.
     *
     * @return a number
     */
    @Override
    public Number factorial() {

        // TODO
        throw new UnsupportedOperationException();
    }

    /**
     * Clalculates the suqare root for this number.
     *
     * @return a number
     */
    @Override
    public Number squareRoot() {

        // TODO
        throw new UnsupportedOperationException();
    }

    /**
     * Halves this number.
     *
     * @return a number
     */
    @Override
    public Number halving() {

        UnaryOperation<Number, Result<Number>> function =
            (UnaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.HALVING_NUMBER_FUNCTION);
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
            (UnaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.DOUBLING_NUMBER_FUNCTION);
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
            (BinaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.ADD_NUMBERS_TRIM_RESULT_FUNCTION);
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
            (MixedBinaryOperation<Number, Fraction, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.ADD_NUMBER_AND_FRACTION_FUNCTION);
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
            (BinaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.SUBTRACT_NUMBERS_FUNCTION);
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

        //TODO
        throw new UnsupportedOperationException();
    }

    /**
     * Multiplies this number with the specified number.
     *
     * @param n
     *        a number
     *
     * @return a number
     */
    @Override
    public Number multiply(Number n) {

        BinaryOperation<Number, Result<Number>> function =
            (BinaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.MULTIPLY_NUMBERS_FUNCTION);
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
            (MixedBinaryOperation<Number, Fraction, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.MULTIPLY_NUMBER_AND_FRACTION_FUNCTION);
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
    public Fraction divide(Number n) {

        BinaryOperation<Number, Result<Fraction>> function =
            (BinaryOperation<Number, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.DIVIDE_NUMBERS_FUNCTION);
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

        //TODO
        throw new UnsupportedOperationException();
    }

    /**
     * Divides this number by the specified number and returns the remainder of the division.
     *
     * @param n
     *        a number
     *
     * @return a number
     */
    @Override
    public Number modulo(Number n) {

        // TODO
        throw new UnsupportedOperationException();
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
            (MixedBinaryOperation<Number, Fraction, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.MAX_NUMBER_FRACTION_FUNCTION);
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
            (BinaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.MAX_NUMBER_FUNCTION);
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
            (MixedBinaryOperation<Number, Fraction, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.MIN_NUMBER_FRACTION_FUNCTION);
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
            (BinaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.MIN_NUMBER_FUNCTION);
        Result<Number> result = function.calculate(this, n);

        return result.result();
    }

    /**
     * Takes this number as base and exponentiates it with the specified number.
     *
     * @param n
     *        a number
     *
     * @return a number
     */
    @Override
    public Number exponentiate(Number n) {

        // TODO
        throw new UnsupportedOperationException();
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

        return new NumberImpl(base(), Signs.POSITIVE, NodesHelper.cloneLinkedList(centerNode()));
    }

    /**
     * Returns a truncated number (i.e. where the fractional part is removed).
     *
     * @return a number
     */
    @Override
    public Number truncate() {

        UnaryOperation<Number, Result<Number>> function =
            (UnaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.TRUNCATE_NUMBER_FUNCTION);
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

        final Number ONE = Math.parseNumber(base(), "1");

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
            (BinaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.SHIFT_LEFT_FUNCTION);
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

        final Number ONE = Math.parseNumber(base(), "1");

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
            (BinaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.SHIFT_RIGHT_FUNCTION);
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

        //TODO 2 cases : (1) rebase to smaller base and (2) rebase to greater base
        throw new UnsupportedOperationException();
    }

    /**
     * Increments this number by one.
     *
     * @return a number
     */
    @Override
    public Number inc() {

        UnaryOperation<Number, Result<Number>> function =
            (UnaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.NUMBER_INCREMENT_FUNCTION);
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
            (UnaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.NUMBER_DECREMENT_FUNCTION);
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
            (UnaryOperation<Number, Result<Boolean>>) FunctionSingletons.getFunction(FunctionIdentifiers.EVEN_NUMBER_FUNCTION);
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
            (UnaryOperation<Number, Result<Boolean>>) FunctionSingletons.getFunction(FunctionIdentifiers.ODD_NUMBER_FUNCTION);
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
            (BinaryOperation<Number, Result<Boolean>>) FunctionSingletons.getFunction(FunctionIdentifiers.NUMBER_GREATER_THAN_COMPARISON_FUNCTION);
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
            (MixedBinaryOperation<Number, Fraction, Result<Boolean>>) FunctionSingletons.getFunction(FunctionIdentifiers.NUMBER_GREATER_THAN_FRACTION_COMPARISON_FUNCTION);
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
            (BinaryOperation<Number, Result<Boolean>>) FunctionSingletons.getFunction(FunctionIdentifiers.NUMBER_GREATER_THAN_OR_EQUAL_COMPARISON_FUNCTION);
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
            (MixedBinaryOperation<Number, Fraction, Result<Boolean>>) FunctionSingletons.getFunction(FunctionIdentifiers.NUMBER_GREATER_THAN_OR_EQUAL_FRACTION_COMPARISON_FUNCTION);
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
            (BinaryOperation<Number, Result<Boolean>>) FunctionSingletons.getFunction(FunctionIdentifiers.NUMBER_LESSER_THAN_COMPARISON_FUNCTION);
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
            (MixedBinaryOperation<Number, Fraction, Result<Boolean>>) FunctionSingletons.getFunction(FunctionIdentifiers.NUMBER_LESSER_THAN_FRACTION_COMPARISON_FUNCTION);
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
            (BinaryOperation<Number, Result<Boolean>>) FunctionSingletons.getFunction(FunctionIdentifiers.NUMBER_LESSER_THAN_OR_EQUAL_COMPARISON_FUNCTION);
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
            (MixedBinaryOperation<Number, Fraction, Result<Boolean>>) FunctionSingletons.getFunction(FunctionIdentifiers.NUMBER_LESSER_THAN_OR_EQUAL_FRACTION_COMPARISON_FUNCTION);
        Result<Boolean> result = function.calculate(this, fraction);

        return result.result();
    }

}
