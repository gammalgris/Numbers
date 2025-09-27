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

package jmul.math.operations.implementations;


import java.util.Iterator;
import java.util.stream.Stream;

import jmul.math.digits.Digit;
import jmul.math.fractions.Fraction;
import jmul.math.logarithms.Logarithm;
import jmul.math.matrices.Matrix;
import static jmul.math.numbers.Constants.BASE_MAX_LIMIT;
import static jmul.math.numbers.Constants.BASE_MIN_LIMIT;
import jmul.math.numbers.Number;
import jmul.math.numbers.exceptions.DigitBaseMismatchException;
import jmul.math.operations.ProcessingDetails;
import jmul.math.vectors.Vector;


/**
 * A helper class for checking parameters.
 *
 * @author Kristian Kutin
 */
public final class ParameterCheckHelper {

    /**
     * The default constructor.
     */
    private ParameterCheckHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Checks the specified parameters and throws an exception if invalid.
     *
     * @param d
     *        a parameter
     */
    public static void checkParameter(Digit d) {

        if (d == null) {

            String message = "No digit (null) was specified!";
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Checks the specified parameters and throws an exception if invalid.
     *
     * @param base
     *        a number base
     *
     * @return the specified parameter
     */
    public static Integer checkNumberBase(Integer base) {

        if (base == null) {

            String message = "No number base (null) was specified!";
            throw new IllegalArgumentException(message);
        }

        if ((base < BASE_MIN_LIMIT) || (base > BASE_MAX_LIMIT)) {

            String message = String.format("An invalid number base was specified (%d)!", base);
            throw new IllegalArgumentException(message);
        }

        return base;
    }

    /**
     * This operation only checks if the number bases match. Use other operations to check the individual parameters
     * first.
     *
     * @param base
     *        a number base
     * @param number
     *        a number
     */
    public static void checkNumberBase(Integer base, Number number) {

        if (base != number.base()) {

            String message =
                String.format("The number bases don't match (expected=%d;actual=%d)!", base, number.base());
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Checks if the specified digits have the same base.
     *
     * @param d1
     *        a digit
     * @param d2
     *        a digit
     */
    public static void checkParameterBase(Digit d1, Digit d2) {

        if (d1.base() != d2.base()) {

            throw new DigitBaseMismatchException(d1, d2);
        }
    }

    /**
     * Checks the specifiecd parameters.
     *
     * @param operand1
     *        an operand
     * @param operand2
     *        an operand
     */
    public static void checkParameters(Number operand1, Number operand2) {

        if (operand1 == null) {

            String message = "The first operand is null!";
            throw new IllegalArgumentException(message);
        }

        if (operand2 == null) {

            String message = "The second operand is null!";
            throw new IllegalArgumentException(message);
        }

        if (operand1.base() != operand2.base()) {

            String message = "The specified numbers are of different bases!";
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Checks the specifiecd parameters.
     *
     * @param integer
     *        an integer
     */
    public static void checkIntegerIgnoreNull(Number integer) {

        if (integer.isFraction()) {

            String message = "An integer is requred!";
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Checks the specifiecd parameters.
     *
     * @param operand1
     *        an operand
     * @param operand2
     *        an operand
     * @param operand3
     *        an operand
     */
    public static void checkParameters(Number operand1, Number operand2, Number operand3) {

        if (operand1 == null) {

            String message = "The first operand is null!";
            throw new IllegalArgumentException(message);
        }

        if (operand2 == null) {

            String message = "The second operand is null!";
            throw new IllegalArgumentException(message);
        }

        if (operand3 == null) {

            String message = "The third operand is null!";
            throw new IllegalArgumentException(message);
        }

        if (operand1.base() != operand2.base()) {

            String message = "The specified numbers are of different bases!";
            throw new IllegalArgumentException(message);
        }

        if (operand1.base() != operand3.base()) {

            String message = "The specified numbers are of different bases!";
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Checks the specifiecd parameters.
     *
     * @param operand1
     *        an operand
     * @param operand2
     *        an operand
     * @param operand3
     *        an operand
     * @param operand4
     *        an operand
     */
    public static void checkParameters(Number operand1, Number operand2, Number operand3, Number operand4) {

        if (operand1 == null) {

            String message = "The first operand is null!";
            throw new IllegalArgumentException(message);
        }

        if (operand2 == null) {

            String message = "The second operand is null!";
            throw new IllegalArgumentException(message);
        }

        if (operand3 == null) {

            String message = "The third operand is null!";
            throw new IllegalArgumentException(message);
        }

        if (operand4 == null) {

            String message = "The fourth operand is null!";
            throw new IllegalArgumentException(message);
        }

        if (operand1.base() != operand2.base()) {

            String message = "The specified numbers are of different bases!";
            throw new IllegalArgumentException(message);
        }

        if (operand1.base() != operand3.base()) {

            String message = "The specified numbers are of different bases!";
            throw new IllegalArgumentException(message);
        }

        if (operand1.base() != operand4.base()) {

            String message = "The specified numbers are of different bases!";
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Checks the specifiecd parameters.
     *
     * @param operand1
     *        an operand
     * @param operand2
     *        an operand
     * @param operand3
     *        an operand
     */
    public static void checkParameters(Fraction operand1, Fraction operand2, Fraction operand3) {

        if (operand1 == null) {

            String message = "The first operand is null!";
            throw new IllegalArgumentException(message);
        }

        if (operand2 == null) {

            String message = "The second operand is null!";
            throw new IllegalArgumentException(message);
        }

        if (operand3 == null) {

            String message = "The third operand is null!";
            throw new IllegalArgumentException(message);
        }

        if (operand1.base() != operand2.base()) {

            String message = "The specified fractions are of different bases!";
            throw new IllegalArgumentException(message);
        }

        if (operand1.base() != operand3.base()) {

            String message = "The specified fractions are of different bases!";
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Checks the specified parameter.
     *
     * @param processingDetails
     *        additional processing details
     *
     * @return the specified parameter
     */
    public static ProcessingDetails checkParameter(ProcessingDetails processingDetails) {

        if (processingDetails == null) {

            throw new IllegalArgumentException("No processing details (null) were specified!");
        }

        return processingDetails;
    }

    /**
     * Checks the specified parameter.
     *
     * @param number
     *        a number
     *
     * @return the specified number
     */
    public static Number checkParameter(Number number) {

        if (number == null) {

            String message = "No number (null) was specified!";
            throw new IllegalArgumentException(message);
        }

        return number;
    }

    /**
     * Checks the specified parameter.
     *
     * @param fraction
     *        a fraction
     *
     * @return the specified fraction
     */
    public static Fraction checkParameter(Fraction fraction) {

        if (fraction == null) {

            String message = "No fraction (null) was specified!";
            throw new IllegalArgumentException(message);
        }

        return fraction;
    }

    /**
     * Checks the specified parameter.
     *
     * @param matrix
     *        a matrix
     *
     * @return the specified matrix
     */
    public static Matrix checkParameter(Matrix matrix) {

        if (matrix == null) {

            String message = "No matrix (null) was specified!";
            throw new IllegalArgumentException(message);
        }

        return matrix;
    }

    /**
     * Checks the specified parameter.
     *
     * @param number
     *        a number
     */
    public static void checkInteger(Number number) {

        if (number == null) {

            String message = "No number (null) was specified!";
            throw new IllegalArgumentException(message);
        }

        if (!number.isInteger()) {

            String message = "The specified number is not an integer!";
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Checks the specified parameter.
     *
     * @param number
     *        a number
     */
    public static void checkPositiveInteger(Number number) {

        if (number == null) {

            String message = "No number (null) was specified!";
            throw new IllegalArgumentException(message);
        }

        if (!number.isInteger()) {

            String message = "The specified number is not an integer!";
            throw new IllegalArgumentException(message);
        }

        if (number.isNegative()) {

            String message = "The specified number is negative!";
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Checks the specified parameter.
     *
     * @param number
     *        a number
     */
    public static void checkPositiveIntegerGreaterZero(Number number) {

        if (number == null) {

            String message = "No number (null) was specified!";
            throw new IllegalArgumentException(message);
        }

        if (!number.isInteger()) {

            String message = "The specified number is not an integer!";
            throw new IllegalArgumentException(message);
        }

        if (number.isNegative()) {

            String message = "The specified number is negative!";
            throw new IllegalArgumentException(message);
        }

        if (number.isZero()) {

            String message = "The specified number is zero!";
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Checks the specified parameters.
     *
     * @param operand1
     *        an operand
     * @param operand2
     *        an operand
     */
    public static void checkParameters(Fraction operand1, Fraction operand2) {

        if (operand1 == null) {

            throw new IllegalArgumentException("No expression (null) was specified!");
        }

        if (operand2 == null) {

            throw new IllegalArgumentException("No expression (null) was specified!");
        }

        if (operand1.base() != operand2.base()) {

            throw new DigitBaseMismatchException(operand1, operand2);
        }
    }

    /**
     * Checks the specified parameters.
     *
     * @param operand1
     *        an operand
     * @param operand2
     *        an operand
     */
    public static void checkParameters(Fraction operand1, Number operand2) {

        if (operand1 == null) {

            throw new IllegalArgumentException("No fraction (null) was specified!");
        }

        if (operand2 == null) {

            throw new IllegalArgumentException("No number (null) was specified!");
        }

        if (operand1.base() != operand2.base()) {

            throw new IllegalArgumentException("The fraction and the number don't have the same number base!");
        }
    }

    /**
     * Checks the specified parameters.
     *
     * @param operand1
     *        an operand
     * @param operand2
     *        an operand
     */
    public static void checkParameters(Number operand1, Fraction operand2) {

        checkParameters(operand2, operand1);
    }

    /**
     * Checks the specified parameter.
     *
     * @param numbers
     *        an array of numbers
     *
     * @return the specified array
     */
    public static Number[] checkParameter(Number[] numbers) {

        if (numbers == null) {

            String message = "No numbers (null) were specified!";
            throw new IllegalArgumentException(message);
        }

        return numbers;
    }

    /**
     * Checks the specified parameter.
     *
     * @param numbers
     *        a string array of numbers
     *
     * @return the specified array
     */
    public static String[] checkParameter(String[] numbers) {

        if (numbers == null) {

            String message = "No numbers (null) were specified!";
            throw new IllegalArgumentException(message);
        }

        return numbers;
    }

    /**
     * Checks the specified parameter.
     *
     * @param numbers
     *        an iterable list or set of numbers
     *
     * @return the specified parameter
     */
    public static Iterable<Number> checkParameter(Iterable<Number> numbers) {

        if (numbers == null) {

            String message = "No numbers (null) were specified!";
            throw new IllegalArgumentException(message);
        }

        return numbers;
    }

    /**
     * Checks the specified parameter.
     *
     * @param numbers
     *        a stream of numbers
     *
     * @return the specified parameter
     */
    public static Stream<Number> checkParameter(Stream<Number> numbers) {

        if (numbers == null) {

            String message = "No numbers (null) were specified!";
            throw new IllegalArgumentException(message);
        }

        return numbers;
    }

    /**
     * Checks the specified vectors.
     *
     * @param vector1
     *        a vector
     * @param vector2
     *        a vector
     */
    public static void checkParameters(Vector vector1, Vector vector2) {

        if (vector1 == null) {

            String message = "No vector (null) was specified!";
            throw new IllegalArgumentException(message);
        }

        if (vector2 == null) {

            String message = "No vector (null) was specified!";
            throw new IllegalArgumentException(message);
        }

        if (vector1.base() != vector2.base()) {

            String message =
                String.format("The vectors are of different number bases (%d & %d)!", vector1.base(), vector2.base());
            throw new IllegalArgumentException(message);
        }

        if (!vector1.dimensions().equals(vector2.dimensions())) {

            String message =
                String.format("The vectors are of different dimensions (%d & %d)!", vector1.dimensions(),
                              vector2.dimensions());
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Checks the specified row and column vectors.
     *
     * @param vector1
     *        a vector
     * @param vector2
     *        a vector
     */
    public static void checkRowAndColumnVector(Vector vector1, Vector vector2) {

        if (vector1 == null) {

            String message = "No vector (null) was specified!";
            throw new IllegalArgumentException(message);
        }

        if (vector2 == null) {

            String message = "No vector (null) was specified!";
            throw new IllegalArgumentException(message);
        }

        if (vector1.base() != vector2.base()) {

            String message =
                String.format("The vectors are of different number bases (%d & %d)!", vector1.base(), vector2.base());
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Checks the specified vectors.
     *
     * @param vector
     *        a vector
     * @param number
     *        a number
     */
    public static void checkParameters(Vector vector, Number number) {

        if (vector == null) {

            String message = "No vector (null) was specified!";
            throw new IllegalArgumentException(message);
        }

        if (number == null) {

            String message = "No number (null) was specified!";
            throw new IllegalArgumentException(message);
        }

        if (vector.base() != number.base()) {

            String message =
                String.format("The vector and the number are of different number bases (%d & %d)!", vector.base(),
                              number.base());
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Checks the specified vectors.
     *
     * @param vector1
     *        a vector
     * @param vector2
     *        a vector
     * @param vector3
     *        a vector
     */
    public static void checkParameters(Vector vector1, Vector vector2, Vector vector3) {

        if (vector1 == null) {

            String message = "No vector (null) was specified!";
            throw new IllegalArgumentException(message);
        }

        if (vector2 == null) {

            String message = "No vector (null) was specified!";
            throw new IllegalArgumentException(message);
        }

        if (vector3 == null) {

            String message = "No vector (null) was specified!";
            throw new IllegalArgumentException(message);
        }

        if (!((vector1.base() == vector2.base()) && (vector2.base() == vector3.base()))) {

            String message =
                String.format("The vectors are of different number bases (%d & %d)!", vector1.base(), vector2.base());
            throw new IllegalArgumentException(message);
        }

        if (!(vector1.dimensions().equals(vector2.dimensions()) && vector2.dimensions().equals(vector3.dimensions()))) {

            String message =
                String.format("The vectors are of different dimensions (%d & %d)!", vector1.dimensions(),
                              vector2.dimensions());
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Checks the specified index.
     *
     * @param index
     *        an index
     *
     * @return the specified parameter
     */
    public static Number checkIndex(Number index) {

        if (index == null) {

            String message = "No index (null) was specified!";
            throw new IllegalArgumentException(message);
        }

        if (index.isFraction()) {

            String message = String.format("No valid index (%s) was specified!", index);
            throw new IllegalArgumentException(message);
        }

        if (index.isNegative()) {

            String message = String.format("A negative index (%s) was specified!", index);
            throw new IllegalArgumentException(message);
        }

        return index;
    }

    /**
     * Checks the specified matrix size.
     *
     * @param columns
     *        a number of columnbs
     * @param rows
     *        a number of rows
     */
    public static void checkMatrixSize(Number columns, Number rows) {

        checkIndex(columns);
        checkIndex(rows);

        if (columns.base() != rows.base()) {

            String message =
                String.format("Provide the number of columns and the number of rows in the same number base (coulumns: %d; rows: %d)",
                              columns.base(), rows.base());
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Checks the specified index.
     *
     * @param index
     *        an index
     * @param minIndex
     *        a minimum index
     * @param maxIndex
     *        a maximum index
     */
    public static void checkIndex(Number index, Number minIndex, Number maxIndex) {

        checkIndex(index);
        checkIndex(minIndex);
        checkIndex(maxIndex);

        int base = index.base();

        if (minIndex.base() != base) {

            String message = String.format("Provide the minimum index in number base %d", base);
            throw new IllegalArgumentException(message);
        }

        if (maxIndex.base() != base) {

            String message = String.format("Provide the maximum index in number base %d", base);
            throw new IllegalArgumentException(message);
        }

        if (index.isLesser(minIndex) || index.isGreater(maxIndex)) {

            String message = String.format("Index is out of bounds (%s < %s < %s)", minIndex, index, maxIndex);
            throw new IllegalArgumentException(message);
        }
    }

    /**
     *Chjecks the specified matrices.
     *
     * @param matrix1
     *        a matrix
     * @param matrix2
     *        a matrix
     */
    public static void checkMatrices(Matrix matrix1, Matrix matrix2) {

        if (matrix1 == null) {

            String message = "No matrix (null) was specified!";
            throw new IllegalArgumentException(message);
        }

        if (matrix2 == null) {

            String message = "No matrix (null) was specified!";
            throw new IllegalArgumentException(message);
        }

        if (matrix1.base() != matrix2.base()) {

            String message = "The specified matrixes are of different number bases!";
            throw new IllegalArgumentException(message);
        }

        if (!matrix1.columns().equals(matrix2.columns())) {

            String message = "The specified matrixes are of different columns!";
            throw new IllegalArgumentException(message);
        }

        if (!matrix1.rows().equals(matrix2.rows())) {

            String message = "The specified matrixes are of different rows!";
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Checks the specified parameter.
     *
     * @param iterator
     *        an iterator
     *
     * @return the specified parameter
     */
    public static Iterator<Number> checkParameter(Iterator<Number> iterator) {

        if (iterator == null) {

            String message = "No iterator (null) was specified!";
            throw new IllegalArgumentException(message);
        }

        return iterator;
    }

    /**
     * Checks the specified vectors.
     *
     * @param matrix1
     *        a vector
     * @param matrix2
     *        a vector
     */
    public static void checkMatricesForMultiplication(Matrix matrix1, Matrix matrix2) {

        if (matrix1 == null) {

            String message = "No matrix (null) was specified!";
            throw new IllegalArgumentException(message);
        }

        if (matrix2 == null) {

            String message = "No matrix (null) was specified!";
            throw new IllegalArgumentException(message);
        }

        if (matrix1.base() != matrix2.base()) {

            String message =
                String.format("The matrices are of different number bases (%d & %d)!", matrix1.base(), matrix2.base());
            throw new IllegalArgumentException(message);
        }

        if (!matrix1.columns().equals(matrix2.rows())) {

            String message =
                String.format("The number of columns of the first matrix doesn't match the number of rows of the second matrix (%s & %s)!",
                              matrix1.columns(), matrix2.rows());
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Checks the specified parameters.
     *
     * @param logarithmExpression1
     *        a logarithm expression
     * @param logarithmExpression2
     *        a logarithm expression
     */
    public static void checkParameters(Logarithm logarithmExpression1, Logarithm logarithmExpression2) {

        if (logarithmExpression1 == null) {

            throw new IllegalArgumentException("No logarithm expression (null) was specified!");
        }

        if (logarithmExpression2 == null) {

            throw new IllegalArgumentException("No logarithm expression (null) was specified!");
        }

        if (logarithmExpression1.base() != logarithmExpression2.base()) {

            throw new IllegalArgumentException("The number bases of the specified logarithm expressions are not equal!");
        }
    }

}
