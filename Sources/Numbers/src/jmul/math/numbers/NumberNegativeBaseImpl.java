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

package jmul.math.numbers;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;

import jmul.math.fractions.Fraction;
import static jmul.math.numbers.Constants.DEFAULT_NUMBER_BASE;
import static jmul.math.numbers.ParameterHelper.checkBase;
import static jmul.math.numbers.ParameterHelper.checkSign;
import jmul.math.numbers.exceptions.NumberParsingException;
import jmul.math.numbers.nodes.DigitNode;
import jmul.math.numbers.nodes.NodesHelper;
import jmul.math.numbers.notations.NotationParser;
import jmul.math.numbers.notations.ParsingResult;
import jmul.math.operations.OperationSingletons;
import jmul.math.operations.ProcessingDetails;
import jmul.math.operations.repository.OperationIdentifier;
import jmul.math.operations.repository.OperationIdentifiers;
import jmul.math.signs.Sign;
import jmul.math.signs.Signs;


/**
 * An implementation of a real number with a negative number base. This implementation requires memory depending on the
 * number of digits the number consists of. Even very large or very small numbers can be represented without rounding.
 * Since the underlying data structure is a linked list numbers can exceed the array size limitations in java.<br>
 * Thus it might not be possible to create a string representation for every number (see array size limitations).<br>
 * <br>
 * <i>Note:<br>
 * Consider implementing a stream mechanism to serialize/ deserialize very large or very small numbers.</i><br>
 * <br>
 * <i>Node:<br>
 * Consider an alternative implementation which compresses the linked list in some way in order to save up memory.</i>
 *
 * TODO check if a separate implementation is required or not
 *
 * TODO a sign might not be necessary for numbers with a negative base, i.e. a sign might lead to disambiguity with
 *      multiple numbers representing the same number (see number in base -10 compared to a number in base 10):
 *       19 ... -1
 *      -19 ...  1
 *        1 ...  1
 *       -1 ... -1
 *
 * TODO Set the positive sign as default? Experiment with two different implementations?
 *
 * TODO Test how this affects addition, subtraction, etc.). In the default number implementation the operations depend
 *      on addition and/ or subtraction. In this case the dependent operations don't need to change.
 *      Identify the other operations that do low level digit manipulations and need a rework.
 *
 * @author Kristian Kutin
 */
public class NumberNegativeBaseImpl implements Number {

    /**
     * A list of parser functions.
     */
    private static final List<OperationIdentifier> PARSER_FUNCTION_LIST;

    /*
     * The static initializer.
     */
    static {

        List<OperationIdentifiers> tmpList = new ArrayList<>();

        //TODO initialization missing

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
    public NumberNegativeBaseImpl() {

        this(DEFAULT_NUMBER_BASE);
    }

    /**
     * Creates a number which represents positive infinity.
     *
     * @param base
     *        the base for this number
     */
    public NumberNegativeBaseImpl(int base) {

        this(base, Signs.POSITIVE);
    }

    /**
     * Creates a number which represents positive or negative infinity. The default base is <code>10</code>.
     *
     * @param sign
     *        a sign for this number
     */
    public NumberNegativeBaseImpl(Sign sign) {

        this(DEFAULT_NUMBER_BASE, sign);
    }

    /**
     * Creates a number which represents positive or negative infinity.
     *
     * @param base
     *        the base for this number
     * @param sign
     *        a sign for this number
     */
    public NumberNegativeBaseImpl(int base, Sign sign) {

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
    public NumberNegativeBaseImpl(CharSequence s) {

        this(DEFAULT_NUMBER_BASE, s);
    }

    /**
     * Creates a number according to the specified parameters.
     *
     * @param s
     *        a string containing a number
     */
    public NumberNegativeBaseImpl(String s) {

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
    public NumberNegativeBaseImpl(int base, CharSequence s) {

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
    public NumberNegativeBaseImpl(int base, String s) {

        this(base, (CharSequence) s);
    }

    /**
     * Creates a number according to the specified parameters.
     *
     * @param parsingResult
     *        the result of parsing a string
     */
    private NumberNegativeBaseImpl(ParsingResult parsingResult) {

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

        for (OperationIdentifier identifier : PARSER_FUNCTION_LIST) {

            NotationParser parser = (NotationParser) OperationSingletons.getFunction(identifier);

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
    public NumberNegativeBaseImpl(Number number) {

        //TODO check number base -> negative number base: clone -> positive number base: rebase
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
    public NumberNegativeBaseImpl(int base, Sign sign, DigitNode centerNode) {

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
    public NumberNegativeBaseImpl(Byte b) {

        this((java.lang.Number) b);
    }

    /**
     * Creates a new number according to the specified parameter. The default base is <code>10</code>.
     *
     * @param s
     *        a short value
     */
    public NumberNegativeBaseImpl(Short s) {

        this((java.lang.Number) s);
    }

    /**
     * Creates a new number according to the specified parameter. The default base is <code>10</code>.
     *
     * @param i
     *        an integer value
     */
    public NumberNegativeBaseImpl(Integer i) {

        this((java.lang.Number) i);
    }

    /**
     * Creates a new number according to the specified parameter. The default base is <code>10</code>.
     *
     * @param l
     *        a long value
     */
    public NumberNegativeBaseImpl(Long l) {

        this((java.lang.Number) l);
    }

    /**
     * Creates a new number according to the specified parameter. The default base is <code>10</code>.
     *
     * @param f
     *        a float value
     */
    public NumberNegativeBaseImpl(Float f) {

        this((java.lang.Number) f);
    }

    /**
     * Creates a new number according to the specified parameter. The default base is <code>10</code>.
     *
     * @param d
     *        a double value
     */
    public NumberNegativeBaseImpl(Double d) {

        this((java.lang.Number) d);
    }

    /**
     * Creates a new number according to the specified parameter. The default base is <code>10</code>.
     *
     * @param n
     *        a number
     */
    public NumberNegativeBaseImpl(java.lang.Number n) {

        this((checkNumberParameter(n)).toString());
    }

    private static java.lang.Number checkNumberParameter(java.lang.Number n) {

        if (n == null) {

            throw new NullPointerException();
        }

        return n;
    }

    @Override
    public String toScientificNotation() {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public String toScientificNotation(char decimalSeparator) {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public String toStandardNotation() {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public String toStandardNotation(char decimalSeparator) {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString(char decimalSeparator) {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isInfinity() {
        // TODO Implement this method
        return false;
    }

    @Override
    public boolean isZero() {
        // TODO Implement this method
        return false;
    }

    @Override
    public boolean isOne() {
        // TODO Implement this method
        return false;
    }

    @Override
    public boolean isFraction() {
        // TODO Implement this method
        return false;
    }

    @Override
    public boolean isInteger() {
        // TODO Implement this method
        return false;
    }

    @Override
    public boolean isEven() {
        // TODO Implement this method
        return false;
    }

    @Override
    public boolean isOdd() {
        // TODO Implement this method
        return false;
    }

    @Override
    public boolean isNaturalNumberIncludingZero() {
        // TODO Implement this method
        return false;
    }

    @Override
    public boolean isNaturalNumber() {
        // TODO Implement this method
        return false;
    }

    @Override
    public Number absoluteValue() {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Number removeFractionPart() {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Number removeIntegerPart() {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Number shiftLeft() {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Number shiftLeft(Number shifts) {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Number shiftRight() {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Number shiftRight(Number shifts) {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Number rebase(int base) {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isMultipleOf(Number number) {
        // TODO Implement this method
        return false;
    }

    @Override
    public boolean isPrime() {
        // TODO Implement this method
        return false;
    }

    @Override
    public SortedSet<Number> divisorSet() {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public SortedSet<Number> primeFactors() {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Fraction toFraction() {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isWithinInterval(Number min, Number max) {
        // TODO Implement this method
        return false;
    }

    @Override
    public DigitNode centerNode() {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Number negate() {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Number complement() {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Number factorial() {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Number square() {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Number squareRoot() {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Number halving() {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Number doubling() {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Number inc() {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Number dec() {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Number add(Number n) {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Fraction add(Fraction f) {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Number subtract(Number n) {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Fraction subtract(Fraction f) {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Number multiply(Number n) {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Fraction multiply(Fraction f) {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Number divide(Number n) {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    /**
     * Divides this number by the specified number.
     *
     * @param n
     *        a number
     * @param decimalPlaces
     *        the number of decimal places retained after cutting the fraction part
     *
     * @return the quotient
     */
    @Override
    public Number divide(Number n, Number decimalPlaces) {

        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Number divide(OperationIdentifier algorithm, Number n) {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Number divide(OperationIdentifier algorithm, Number n, Number decimalPlaces) {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    /**
     * Divides this number by the specified number.
     *
     * @param n
     *        a number
     *
     * @return a fraction (e.g. a quotient, a mixed fraction or integer)
     */
    @Override
    public Fraction divideReturnFraction(Number n) {

        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Fraction divide(Fraction f) {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Number modulo(Number n) {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Number diviso(Number n) {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Fraction max(Fraction f) {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Number max(Number n) {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Fraction min(Fraction f) {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Number min(Number n) {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Number exponentiate(Number n) {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Number log(Number n) {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Fraction reciprocal() {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Number roundDown() {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Number roundUp() {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Number round(Number decimalPlaces) {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Number round(int decimalPlaces) {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Number round(OperationIdentifier algorithm, int decimalPlaces) {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Number round(OperationIdentifier algorithm, Number decimalPlaces) {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isGreater(Number number) {
        // TODO Implement this method
        return false;
    }

    @Override
    public boolean isGreater(Fraction fraction) {
        // TODO Implement this method
        return false;
    }

    @Override
    public boolean isGreaterOrEqual(Number number) {
        // TODO Implement this method
        return false;
    }

    @Override
    public boolean isGreaterOrEqual(Fraction fraction) {
        // TODO Implement this method
        return false;
    }

    @Override
    public boolean isLesser(Number number) {
        // TODO Implement this method
        return false;
    }

    @Override
    public boolean isLesser(Fraction fraction) {
        // TODO Implement this method
        return false;
    }

    @Override
    public boolean isLesserOrEqual(Number number) {
        // TODO Implement this method
        return false;
    }

    @Override
    public boolean isLesserOrEqual(Fraction fraction) {
        // TODO Implement this method
        return false;
    }

    @Override
    public int compareTo(Object o) {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Sign sign() {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public int base() {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isPositive() {
        // TODO Implement this method
        return false;
    }

    @Override
    public boolean isNegative() {
        // TODO Implement this method
        return false;
    }

    @Override
    public byte toPrimitiveByte() {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public short toPrimitiveShort() {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public int toPrimitiveInt() {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public long toPrimitiveLong() {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public float toPrimitiveFloat() {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public double toPrimitiveDouble() {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Byte toByte() {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Short toShort() {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Integer toInteger() {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Long toLong() {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Float toFloat() {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public Double toDouble() {
        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    /**
     * Calculates the digit sum for this number.
     *
     * @return the digit sum
     */
    @Override
    public Number digitSum() {

        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isSingleDigit() {
        // TODO Implement this method
        return false;
    }

    /**
     * Returns the total number of digits.
     *
     * @return the total number of digits
     */
    @Override
    public Number digits() {

        //TODO
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the total number of digits left of the decimal separator.
     *
     * @return the total number of digits left of the decimal separator
     */
    @Override
    public Number digitsLeft() {

        //TODO
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the total number of digits right of the decimal separator.
     *
     * @return the total number of digits right of the decimal separator
     */
    @Override
    public Number digitsRight() {

        //TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public Number root(Number n) {
        // TODO Implement this method
        return null;
    }

    @Override
    public Number squareRoot(ProcessingDetails processingDetails) {
        // TODO Implement this method
        return null;
    }

    @Override
    public Number root(ProcessingDetails processingDetails, Number n) {
        // TODO Implement this method
        return null;
    }

    @Override
    public Number multiply(ProcessingDetails processingDetails, Number n) {
        // TODO Implement this method
        return null;
    }

}
