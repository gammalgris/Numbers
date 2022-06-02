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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jmul.math.hash.HashHelper;
import static jmul.math.numbers.ParameterHelper.checkBase;
import static jmul.math.numbers.ParameterHelper.checkSign;
import jmul.math.numbers.nodes.DigitNode;
import jmul.math.numbers.nodes.Nodes;
import jmul.math.numbers.notations.NotationFunction;
import jmul.math.numbers.notations.NotationParser;
import jmul.math.numbers.notations.ParsingResult;
import jmul.math.numbers.notations.ScientificNotationFunctionImpl;
import jmul.math.numbers.notations.ScientificNotationParserImpl;
import jmul.math.numbers.notations.StandardNotationFunctionImpl;
import jmul.math.numbers.notations.StandardNotationParserImpl;
import jmul.math.numbers.operations.EqualityFunction;
import jmul.math.numbers.operations.NegateNumberFunctionImpl;
import jmul.math.numbers.operations.NumberComparatorFunctionImpl;
import jmul.math.numbers.operations.NumberEqualityFunctionImpl;
import jmul.math.operations.Result;
import jmul.math.operations.UnaryOperation;

import jmul.singletons.FunctionIdentifier;
import jmul.singletons.FunctionSingletons;


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
     * The default base if none is specified.
     */
    private static final int DEFAULT_BASE;

    /**
     * A map containing informations to initialize functions.
     */
    private static final Map<FunctionIdentifier, Class> FUNCTION_CLASS_MAP;

    /**
     * A list of parser functions.
     */
    private static final List<FunctionIdentifier> PARSER_FUNCTION_LIST;

    /*
     * The static initializer.
     */
    static {

        DEFAULT_BASE = 10;

        Map<FunctionIdentifier, Class> tmpMap = new HashMap<>();

        tmpMap.put(FunctionIdentifiers.STANDARD_NOTATION_PARSER, StandardNotationParserImpl.class);
        tmpMap.put(FunctionIdentifiers.SCIENTIFIC_NOTATION_PARSER, ScientificNotationParserImpl.class);

        tmpMap.put(FunctionIdentifiers.SCIENTIFIC_NOTATION_FUNCTION, ScientificNotationFunctionImpl.class);
        tmpMap.put(FunctionIdentifiers.STANDARD_NOTATION_FUNCTION, StandardNotationFunctionImpl.class);

        tmpMap.put(FunctionIdentifiers.NEGATE_NUMBER_FUNCTION, NegateNumberFunctionImpl.class);

        tmpMap.put(FunctionIdentifiers.NUMBER_COMPARATOR_FUNCTION, NumberComparatorFunctionImpl.class);
        tmpMap.put(FunctionIdentifiers.NUMBER_EQUALITY_FUNCTION, NumberEqualityFunctionImpl.class);

        FUNCTION_CLASS_MAP = Collections.unmodifiableMap(tmpMap);


        List<FunctionIdentifiers> tmpList = new ArrayList<>();

        tmpList.add(FunctionIdentifiers.STANDARD_NOTATION_PARSER);
        tmpList.add(FunctionIdentifiers.SCIENTIFIC_NOTATION_PARSER);

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

        this(parsingResult.sign, parsingResult.base, parsingResult.centerNode);
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

        List<Exception> exceptions = new ArrayList<>();

        for (FunctionIdentifier identifier : PARSER_FUNCTION_LIST) {

            NotationParser parser = (NotationParser) getFunction(identifier);

            try {

                return parser.parseNotation(base, s.toString());

            } catch (IllegalArgumentException e) {

                // ignore the exception because it might not be the matching notation
                exceptions.add(e);
            }
        }

        String message = String.format("An unknown notation is used (\"%s\")! Errors:%s", s, exceptions.toString());
        throw new IllegalArgumentException(message);
    }

    /**
     * A copy constructor.
     *
     * @param number
     *        another number
     */
    public NumberImpl(Number number) {

        this(number.sign(), number.base(), Nodes.cloneLinkedList(number.centerNode()));
    }

    /**
     * Creates a number with the specified parameters.<br>
     * <br>
     * <i>Note:<br>
     * This constructor is mainly used for internal purposes and to instantiate calculation results.
     * Calculations result don't need to be cloned.</i>
     *
     * @param sign
     *        the sign for this number
     * @param base
     *        the base for this number
     * @param centerNode
     *        a reference to the center node of the linked list
     */
    public NumberImpl(Sign sign, int base, DigitNode centerNode) {

        super();

        this.sign = sign;
        this.base = base;
        this.centerNode = centerNode;
    }

    /**
     * Creaes a new number according to the specified parameter. The default base is <code>10</code>.
     *
     * @param b
     *        a byte value
     */
    public NumberImpl(Byte b) {

        this((java.lang.Number) b);
    }

    /**
     * Creaes a new number according to the specified parameter. The default base is <code>10</code>.
     *
     * @param s
     *        a short value
     */
    public NumberImpl(Short s) {

        this((java.lang.Number) s);
    }

    /**
     * Creaes a new number according to the specified parameter. The default base is <code>10</code>.
     *
     * @param i
     *        an integer value
     */
    public NumberImpl(Integer i) {

        this((java.lang.Number) i);
    }

    /**
     * Creaes a new number according to the specified parameter. The default base is <code>10</code>.
     *
     * @param l
     *        a long value
     */
    public NumberImpl(Long l) {

        this((java.lang.Number) l);
    }

    /**
     * Creaes a new number according to the specified parameter. The default base is <code>10</code>.
     *
     * @param f
     *        a float value
     */
    public NumberImpl(Float f) {

        this((java.lang.Number) f);
    }

    /**
     * Creaes a new number according to the specified parameter. The default base is <code>10</code>.
     *
     * @param d
     *        a double value
     */
    public NumberImpl(Double d) {

        this((java.lang.Number) d);
    }

    /**
     * Creaes a new number according to the specified parameter. The default base is <code>10</code>.
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
     * Initializes a function.<br>
     * <br>
     * <i>Note:<br>
     * Call this method only once.</i>
     *
     * @param identifier
     *        a function identifier
     * @param type
     *        the function class
     *
     * @return a function object
     */
    private static Object initializeFunction(FunctionIdentifier identifier, Class type) throws InstantiationException,
                                                                                               IllegalAccessException {

        Object function = type.newInstance();
        FunctionSingletons.putFunction(identifier, function);

        return function;
    }

    /**
     * Returns the function associated with the specified identifier.
     *
     * @param identifier
     *        a function identifier
     *
     * @return a function
     */
    private static Object getFunction(FunctionIdentifier identifier) {

        if (!FunctionSingletons.existsFunction(identifier)) {

            Class type = FUNCTION_CLASS_MAP.get(identifier);
            try {

                return initializeFunction(identifier, type);

            } catch (IllegalAccessException | InstantiationException e) {

                String message = String.format("Unable to initialize a fucntion (%s)", identifier.toString());
                throw new RuntimeException(message, e);
            }

        } else {

            return FunctionSingletons.getFunction(identifier);
        }
    }

    /**
     * Returns a scientific notation for this number.<br>
     * <br>
     * <i>Note:<br>
     * If the number has of too many digits it may not be possible to create a string representation.
     * In this case try converting the number to a higher base.</i>
     *
     * @return a scientific notation for this number
     */
    @Override
    public String toScientificNotation() {

        NotationFunction function = (NotationFunction) getFunction(FunctionIdentifiers.SCIENTIFIC_NOTATION_FUNCTION);

        return function.toString(this);
    }

    /**
     * Returns a standard notation for this number.<br>
     * <br>
     * <i>Note:<br>
     * If the number has of too many digits it may not be possible to create a string representation.
     * In this case try the scientific notation or converting the number to a higher base.</i>
     *
     * @return a standard notation for this number
     */
    @Override
    public String toStandardNotation() {

        NotationFunction function = (NotationFunction) getFunction(FunctionIdentifiers.STANDARD_NOTATION_FUNCTION);

        return function.toString(this);
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
     * Compares this number with the specified number. Returns <code>-1</code>, <code>-1</code> or <code>-1</code>
     * as this number is lesser than, euqal or greater than the specified number.
     *
     * @param n
     *        a number
     *
     * @return an integer value
     */
    @Override
    public int compareTo(Number n) {

        Comparator<Number> function = (Comparator<Number>) getFunction(FunctionIdentifiers.NUMBER_COMPARATOR_FUNCTION);
        int result = function.compare(this, n);

        return result;
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

        if (o == null) {

            return false;
        }

        EqualityFunction<Number> function =
            (EqualityFunction<Number>) getFunction(FunctionIdentifiers.NUMBER_EQUALITY_FUNCTION);
        boolean result = function.equals(this, (Number) o);

        return result;
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

        UnaryOperation<Number> function =
            (UnaryOperation<Number>) getFunction(FunctionIdentifiers.NEGATE_NUMBER_FUNCTION);
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
     * Adds this number and the specified number.
     *
     * @param n
     *        a number
     *
     * @return a number
     */
    @Override
    public Number add(Number n) {

        // TODO
        throw new UnsupportedOperationException();
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

        // TODO
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

        // TODO
        throw new UnsupportedOperationException();
    }

    /**
     * Divides this number by the specified number.
     *
     * @param n
     *        a number
     *
     * @return a number
     */
    @Override
    public Number divide(Number n) {

        // TODO
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
     * Compares this number and the specified number and returns the greater number.
     *
     * @param n
     *        a number
     *
     * @return a number
     */
    @Override
    public Number max(Number n) {

        // TODO
        throw new UnsupportedOperationException();
    }

    /**
     * Compares this number and the specified number and returns the smaller number.
     *
     * @param n
     *
     * @return
     */
    @Override
    public Number min(Number n) {

        // TODO
        throw new UnsupportedOperationException();
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

}