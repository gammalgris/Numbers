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
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import jmul.math.hash.HashHelper;
import jmul.math.numbers.comparators.NaturalOrderingComparator;
import jmul.math.numbers.digits.Digit;
import jmul.math.numbers.notations.ScientificNotation;
import jmul.math.numbers.notations.StandardNotation;

import jmul.singletons.FunctionSingletons;


/**
 * An abstract base class for all implementations of a digit sequence.<br>
 * <br>
 * <i>Note:<br>
 * This implementation uses one array for all relevant digits and the position of the separator (i.e.
 * comma or point) relative to the first digit. This way the index calculations add some complexity but multiplying and
 * dividing by the base is significantly easier (i.e. by simply moving the index to the left or right).</i>
 *
 * @param <T>
 *        the set of digits which comprises the digit sequence
 *
 * @author Kristian Kutin
 */
abstract class AbstractNumber<T extends Digit> extends AbstractSignedNumber<T> {

    /**
     * All relevant digits.
     */
    protected final T[] digits;

    /**
     * The index of a separator relative to the first digit of the array. Thus <code>0</code> means the separator is
     * to the right of the first digit, <code>-1</code> means the separator is to the left of the first digit and
     * <code>1</code> means the separator is between the second and third digit.<br>
     * <br>
     * A value of <code>null</code> indicate that there is no separator. No index and an empty array of digits
     * represent infinity.
     */
    protected final Integer relativeSeparatorIndex;

    /**
     * Creates a new number (i.e. digit sequence) according to the specified parameters. This constructor is
     * intended for instantiating most numbers.
     *
     * @param aSign
     *        the sign for this number (i.e. digit sequence)
     * @param allDigits
     *        all relevant digits of the number without a separator (i.e. point or comma)
     * @param aRelativeSeparatorIndex
     *        the index of a separator (i.e. point or comma) relative to the first digit
     */
    protected AbstractNumber(Sign aSign, T[] allDigits, Integer aRelativeSeparatorIndex) {

        super(aSign);

        if (allDigits.length < 2) {

            digits = allDigits;
            relativeSeparatorIndex = aRelativeSeparatorIndex;

        } else {

            digits = trimArray(allDigits);
            relativeSeparatorIndex = aRelativeSeparatorIndex + offset(allDigits);
        }

        initFunctions();
    }

    /**
     * Trims the specified array, i.e. removes all leading and trailing zeroes.
     *
     * @param allDigits
     *        a digit array
     *
     * @return a trimmed digit array (i.e. a new array)
     */
    private T[] trimArray(T[] allDigits) {

        int left = 0;
        for (int a = 0; a < allDigits.length; a++) {

            T digit = allDigits[a];

            if (zero() == digit) {

                left++;

            } else {

                break;
            }
        }

        int right = allDigits.length;
        for (int a = allDigits.length - 1; a >= 0; a--) {

            T digit = allDigits[a];

            if (zero() == digit) {

                right--;

            } else {

                break;
            }
        }

        List<T> trimmed = new ArrayList<>();
        for (int a = left; a < right; a++) {

            T digit = allDigits[a];
            trimmed.add(digit);
        }

        if (trimmed.isEmpty()) {

            trimmed.add(zero());
        }

        return trimmed.toArray(newArray());
    }

    /**
     * Calculates an offset for the specified digit array. The offset is determined by all leading zeroes.
     *
     * @param allDigits
     *        a digit array
     *
     * @return an offset
     */
    private int offset(T[] allDigits) {

        int left = 0;
        for (int a = 0; a < allDigits.length; a++) {

            T digit = allDigits[a];

            if (zero() == digit) {

                left++;

            } else {

                break;
            }
        }

        return -left;
    }

    /**
     * Here we instantiate singletons (i.e. instances of classes that implement a strategy pattern). To save memory
     * only one instance per class is needed.
     */
    private void initFunctions() {

        if (!FunctionSingletons.existsFunction(FunctionIdentifiers.COMPARATOR)) {

            Comparator<DigitSequence<T>> comparatorFunction = new NaturalOrderingComparator<>();
            FunctionSingletons.putFunction(FunctionIdentifiers.COMPARATOR, comparatorFunction);
        }

        if (!FunctionSingletons.existsFunction(FunctionIdentifiers.SCIENTIFIC_NOTATION)) {

            ScientificNotation<DigitSequence<T>> scientificNotationFunction = new ScientificNotation<>();
            FunctionSingletons.putFunction(FunctionIdentifiers.SCIENTIFIC_NOTATION, scientificNotationFunction);
        }

        if (!FunctionSingletons.existsFunction(FunctionIdentifiers.STANDARD_NOTATION)) {

            StandardNotation<DigitSequence<T>> standardNotationFunction = new StandardNotation<>();
            FunctionSingletons.putFunction(FunctionIdentifiers.STANDARD_NOTATION, standardNotationFunction);
        }
    }

    /**
     * Returns the count of digits left of the comma.
     *
     * @return a partial digit count
     */
    @Override
    public int leftDigits() {

        if (relativeSeparatorIndex == null) {

            return 0;
        }

        return Math.max(1, relativeSeparatorIndex + 1);
    }

    /**
     * Returns the count of digits right of the comma.
     *
     * @return a partial digit count
     */
    @Override
    public int rightDigits() {

        if (relativeSeparatorIndex == null) {

            return 0;
        }

        return Math.max(0, digits.length - (relativeSeparatorIndex + 1));
    }

    /**
     * Returns the total count of digits (i.e. left and right of the comma).
     *
     * @return a digit count
     */
    @Override
    public int digits() {

        return leftDigits() + rightDigits();
    }

    /**
     * Translates the specified index into an index which is applicable on an array. The calculated array index
     * may be outside the array bounds. This is intended and the invoker has to handle this case.
     *
     * @param anIndex
     *        a positive or negative non zero number
     *
     * @return an array index
     */
    private int translateIndex(int anIndex) {

        int normalizedIndex = anIndex;
        if (normalizedIndex > 0) {

            normalizedIndex -= 1;
        }

        normalizedIndex = -normalizedIndex;
        normalizedIndex += relativeSeparatorIndex;

        return normalizedIndex;
    }

    /**
     * Returns the digit at the specified index positions.<br>
     * <br>
     * <i>Note:<br>
     * The nested if-then-else-block needs some more optimization.</i>
     *
     * @param anIndex
     *        A positive or negative number specifiying the position of a digit. <code>0</code> represents the position
     *        of a separator (i.e. comma or point). A positive number represents a digit left of the separator. A
     *        negative number represents a digit right of the separator.
     *        The higher the absolute value is the farther away it is from the separator (i.e. index 0). If the
     *        specified index exceeds the left or right side of the number then by default <code>0</code> is returned.
     *
     * @return a digit
     */
    @Override
    public T digitAt(int anIndex) {

        if (anIndex == 0) {

            String message =
                "0 is an illegal index value (i.e. represents the position of a separator (i.e. a point or comma) not a digit)!";
            throw new IllegalArgumentException(message);
        }

        if (digits.length == 0) {

            String message = "This number has no digits!";
            throw new IllegalArgumentException(message);
        }

        int normalizedIndex = translateIndex(anIndex);

        if ((normalizedIndex < 0) || (normalizedIndex >= digits.length)) {

            return zero();
        }

        return digits[normalizedIndex];
    }

    /**
     * Returns <code>0</code> for the given digit base.
     *
     * @return <code>0</code>
     */
    protected abstract T zero();

    /**
     * Returns an empty array for the given digit base.
     *
     * @return a new array
     */
    protected abstract T[] newArray();

    /**
     * Returns the base for this number.
     *
     * @return a base
     */
    @Override
    public int base() {

        return zero().base();
    }

    /**
     * Returns the scientific notation for this number (i.e. digit sequence).
     *
     * @return a string containing a number with a specific notation
     */
    @Override
    public String toScientificNotation() {

        ScientificNotation<DigitSequence<T>> scientificNotationFunction =
            (ScientificNotation<DigitSequence<T>>) FunctionSingletons.getFunction(FunctionIdentifiers.SCIENTIFIC_NOTATION);
        return scientificNotationFunction.toString(this);
    }

    /**
     * Returns the standard notation for this number (i.e. digit sequence).
     *
     * @return a string containing a number with a specific notation
     */
    @Override
    public String toStandardNotation() {

        StandardNotation<DigitSequence<T>> standardNotationFunction =
            (StandardNotation<DigitSequence<T>>) FunctionSingletons.getFunction(FunctionIdentifiers.STANDARD_NOTATION);
        return standardNotationFunction.toString(this);
    }

    /**
     * Compares this number with the specified number.
     *
     * @param o
     *        a number
     *
     * @return <code>-1</code> if this number is lesser than the specified number, <code>0</code> if both numbers are
     * equal and <code>1</code> if this number is greater than the specified number
     */
    @Override
    public int compareTo(DigitSequence<T> o) {

        Comparator<DigitSequence<T>> comparatorFunction =
            (Comparator<DigitSequence<T>>) FunctionSingletons.getFunction(FunctionIdentifiers.COMPARATOR);
        return comparatorFunction.compare(this, o);
    }

    /**
     * Checks if this and the specified object are equal.
     *
     * @param o
     *        another object
     *
     * @return <code>true</code> if both objects are equal, else <code>false</code>
     */
    @Override
    public boolean equals(Object o) {

        if (o == null) {

            return false;
        }

        if (this == o) {

            return true;
        }

        Class<?> actualClass = o.getClass();
        Class<?> expectedClass = this.getClass();
        if (!expectedClass.isAssignableFrom(actualClass)) {

            return false;
        }

        AbstractNumber<?> other = (AbstractNumber<?>) o;

        if (!this.sign().equals(other.sign())) {

            return false;
        }

        // this and other have the same sign

        if (this.isInfinity() && other.isInfinity()) {

            return true;

        } else if (this.isInfinity() || other.isInfinity()) {

            return false;
        }

        // neither o1 and o2 are infinity

        Digit thisSampleDigit = this.digitAt(1);
        Digit otherSampleDigit = other.digitAt(1);

        return (thisSampleDigit.base() == otherSampleDigit.base()) && Arrays.equals(this.digits, other.digits) &&
               (this.relativeSeparatorIndex == other.relativeSeparatorIndex);
    }

    /**
     * Calculates and returns a hash code for this object.
     *
     * @return a hash code
     */
    @Override
    public int hashCode() {

        Class<?> clazz = this.getClass();

        // The array itself is not relevant for calculating a hash code. Only the array elements are relevant.
        List<T> l = Arrays.asList(digits);
        return HashHelper.calculateHashCode(clazz, sign(), l, relativeSeparatorIndex);
    }

    /**
     * Returns a string representation for this number (i.e. digit sequence).
     *
     * @return a string representation
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

        return digits() == 0;
    }

    /**
     * Checks if this number is zero.
     *
     * @return <code>true</code> if this number is zero, else <code>false</code>
     */
    @Override
    public boolean isZero() {

        return (relativeSeparatorIndex == 0) && (digits.length == 1) && (digits[0] == zero());
    }

}
