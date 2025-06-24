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

package jmul.math.matrices;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;

import jmul.math.functions.FunctionSingletons;
import jmul.math.functions.implementations.ParameterCheckHelper;
import jmul.math.functions.repository.FunctionIdentifiers;
import jmul.math.hash.HashHelper;
import jmul.math.indices.IndexSingletons;
import jmul.math.numbers.Number;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.EqualityFunction;
import jmul.math.operations.Result;
import jmul.math.operations.UnaryOperation;
import jmul.math.vectors.Vector;


/**
 * An implementation of a matrix.
 *
 * @author Kristian Kutin
 */
public class MatrixImpl implements Matrix {

    /**
     * The number base of this vector.
     */
    private final int base;

    /**
     * The number of columns in this matrix.
     */
    private final Number columns;

    /**
     * The number of rows in this matrix.
     */
    private final Number rows;

    /**
     * Contains all cells.
     */
    private final Map<Location, Number> cells;

    /**
     * The default constructor (i.e. a zero dimension matrix).
     */
    public MatrixImpl() {

        this(10);
    }

    /**
     * Creates an empty matrix.
     *
     * @param base
     *        the number base
     */
    public MatrixImpl(int base) {

        super();

        this.base = ParameterCheckHelper.checkNumberBase(base);

        Number ZERO = IndexSingletons.firstIndex(base).dec();

        this.columns = ZERO;
        this.rows = ZERO;
        this.cells = new HashMap<>();
    }

    /**
     * Creates a matrix according to the specified parameters.
     *
     * @param base
     *        a number base
     * @param columns
     *        a number of columns
     * @param rows
     *        a number of rows
     * @param numbers
     *        all numbers
     */
    public MatrixImpl(int base, Number columns, Number rows, Number... numbers) {

        super();

        ParameterCheckHelper.checkParameter(numbers);

        this.base = ParameterCheckHelper.checkNumberBase(base);
        ParameterCheckHelper.checkMatrixSize(columns, rows);


        this.columns = ParameterCheckHelper.checkIndex(columns);
        this.rows = ParameterCheckHelper.checkIndex(rows);
        this.cells = new HashMap<>();

        addElements(numbers);
    }

    /**
     * Creates a matrix according to the specified base, size and components.
     *
     * @param base
     *        a number base
     * @param columns
     *        a number of columns
     * @param rows
     *        a number of rows
     * @param iterator
     *        an iterator (i.e. an iterable container)
     */
    private MatrixImpl(int base, Number columns, Number rows, Iterator<Number> iterator) {

        super();

        this.base = ParameterCheckHelper.checkNumberBase(base);
        ParameterCheckHelper.checkMatrixSize(columns, rows);

        this.columns = columns;
        this.rows = rows;
        this.cells = new HashMap<>();

        addElements(iterator);
    }

    /**
     * Creates a matrix according to the specified base, size and components.
     *
     * @param base
     *        a number base
     * @param columns
     *        a number of columns
     * @param rows
     *        a number of rows
     * @param components
     *        an iterable container
     */
    public MatrixImpl(int base, Number columns, Number rows, Iterable<Number> components) {

        this(base, columns, rows, ParameterCheckHelper.checkParameter(components).iterator());
    }

    /**
     * Creates a matrix according to the specified base, size and components.
     *
     * @param base
     *        a number base
     * @param columns
     *        a number of columns
     * @param rows
     *        a number of rows
     * @param components
     *        a stream which provides all components
     */
    public MatrixImpl(int base, Number columns, Number rows, Stream<Number> components) {

        this(base, columns, rows, ParameterCheckHelper.checkParameter(components).iterator());
    }

    /**
     * Adds the specified elements to this matrix.<br>
     * <br>
     * <i>Example:<br>
     * <br>
     * The array:<br>
     * <code>{1, 2, 3, 4}</code><br>
     * <br>
     * The corresponding matrix:<br>
     * <code>|1, 3|<br>
     * |2, 4|</code>
     * </i>
     *
     * @param numbers
     *        all matrix elements
     */
    private void addElements(Number... numbers) {

        final Number firstIndex = IndexSingletons.firstIndex(base);
        int index = 0;

        for (Number columnIndex = firstIndex; columnIndex.isLesserOrEqual(columns);
             columnIndex = IndexSingletons.nextIndex(columnIndex)) {

            for (Number rowIndex = firstIndex; rowIndex.isLesserOrEqual(rows);
                 rowIndex = IndexSingletons.nextIndex(rowIndex)) {

                if (index >= numbers.length) {

                    String message = "The specified number array has fewer elements than expected!";
                    throw new IllegalArgumentException(message);
                }

                Number number = numbers[index];

                Location loacation = new Location(columnIndex, rowIndex);
                cells.put(loacation, number);

                index++;
            }
        }

        if (index < numbers.length) {

            String message = "The specified number array has more elements than expected!";
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Adds the specified elements to this matrix.<br>
     * <br>
     * <i>Example:<br>
     * <br>
     * The array:<br>
     * <code>{1, 2, 3, 4}</code><br>
     * <br>
     * The corresponding matrix:<br>
     * <code>|1, 3|<br>
     * |2, 4|</code>
     * </i>
     *
     * @param iterator
     *        an iterator (i.e. an iterable container)
     */
    private void addElements(Iterator<Number> iterator) {

        final Number firstIndex = IndexSingletons.firstIndex(base);

        for (Number rowIndex = firstIndex; rowIndex.isLesserOrEqual(rows);
             rowIndex = IndexSingletons.nextIndex(rowIndex)) {

            for (Number columnIndex = firstIndex; columnIndex.isLesserOrEqual(columns);
                 columnIndex = IndexSingletons.nextIndex(columnIndex)) {

                if (!iterator.hasNext()) {

                    String message = "The specified container has fewer elements than expected!";
                    throw new IllegalArgumentException(message);
                }

                Number number = iterator.next();

                Location loacation = new Location(columnIndex, rowIndex);
                cells.put(loacation, number);
            }
        }

        if (iterator.hasNext()) {

            String message = "The specified container has more elements than expected!";
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Returns the component at the specified index positions.
     *
     * @param columnIndex
     *        a column index
     * @param rowIndex
     *        a row index
     *
     * @return a number
     */
    @Override
    public Number component(Number columnIndex, Number rowIndex) {

        Number firstIndex = IndexSingletons.firstIndex(base);
        ParameterCheckHelper.checkIndex(columnIndex, firstIndex, columns);
        ParameterCheckHelper.checkIndex(rowIndex, firstIndex, rows);

        Location location = new Location(columnIndex, rowIndex);
        return cells.get(location);
    }

    /**
     * Returns the number base of this matrix.
     *
     * @return a number base
     */
    @Override
    public int base() {

        return base;
    }

    /**
     * Returns the number of columns of this matrix.
     *
     * @return the number of columns
     */
    @Override
    public Number columns() {

        return columns;
    }

    /**
     * Returns the number of rows of this matrix.
     *
     * @return the number of rows
     */
    @Override
    public Number rows() {

        return rows;
    }

    /**
     * Performs a matrix addition with this matrix and the specified matrix.
     *
     * @param matrix
     *        a matrix
     *
     * @return the result
     */
    @Override
    public Matrix add(Matrix matrix) {

        BinaryOperation<Matrix, Result<Matrix>> function =
            (BinaryOperation<Matrix, Result<Matrix>>) FunctionSingletons.getFunction(FunctionIdentifiers.ADD_MATRICES_FUNCTION);
        Result<Matrix> result = function.calculate(this, matrix);

        return result.result();
    }

    /**
     * Performs a matrix subtraction with this matrix and the specified matrix.
     *
     * @param matrix
     *        a matrix
     *
     * @return the result
     */
    @Override
    public Matrix subtract(Matrix matrix) {

        BinaryOperation<Matrix, Result<Matrix>> function =
            (BinaryOperation<Matrix, Result<Matrix>>) FunctionSingletons.getFunction(FunctionIdentifiers.SUBTRACT_MATRICES_FUNCTION);
        Result<Matrix> result = function.calculate(this, matrix);

        return result.result();
    }

    /**
     * Transposes this matrix.
     *
     * @return a transposed matrix
     */
    @Override
    public Matrix transpose() {

        UnaryOperation<Matrix, Result<Matrix>> function =
            (UnaryOperation<Matrix, Result<Matrix>>) FunctionSingletons.getFunction(FunctionIdentifiers.TRANSPOSE_MATRIX_FUNCTION);
        Result<Matrix> result = function.calculate(this);

        return result.result();
    }

    /**
     * Performs a matrix multiplication with this matrix and the specified matrix.
     *
     * @param matrix
     *        a matrix
     *
     * @return the result
     */
    @Override
    public Matrix multiply(Matrix matrix) {

        BinaryOperation<Matrix, Result<Matrix>> function =
            (BinaryOperation<Matrix, Result<Matrix>>) FunctionSingletons.getFunction(FunctionIdentifiers.MATRIX_MULTIPLCIATION_FUNCTION);
        Result<Matrix> result = function.calculate(this, matrix);

        return result.result();
    }

    /**
     * Performs a vetorization of this matrix (i.e. transforms the matrix to a vector).
     *
     * @return a vector
     */
    @Override
    public Vector toVector() {

        UnaryOperation<Matrix, Result<Vector>> function =
            (UnaryOperation<Matrix, Result<Vector>>) FunctionSingletons.getFunction(FunctionIdentifiers.VECTORIZATION_FUNCTION);
        Result<Vector> result = function.calculate(this);

        return result.result();
    }

    /**
     * Returns a string representation for this matrix.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        StringBuffer buffer = new StringBuffer();

        final Number firstIndex = IndexSingletons.firstIndex(base);

        buffer.append("{");

        for (Number rowIndex = firstIndex; rowIndex.isLesserOrEqual(rows);
             rowIndex = IndexSingletons.nextIndex(rowIndex)) {

            buffer.append("{");

            for (Number columnIndex = firstIndex; columnIndex.isLesserOrEqual(columns);
                 columnIndex = IndexSingletons.nextIndex(columnIndex)) {

                Number component = component(columnIndex, rowIndex);

                if (!columnIndex.equals(firstIndex)) {

                    buffer.append(", ");
                }

                buffer.append(component);
            }

            buffer.append("}");
        }

        buffer.append("}");

        return buffer.toString();
    }

    /**
     * Calculates a hash code for this vector.
     *
     * @return a hash code
     */
    @Override
    public int hashCode() {

        return HashHelper.calculateHashCode(Matrix.class, base, rows, columns, null);
    }

    /**
     * Compares this matrix with the specified object.
     *
     * @param o
     *        another object
     *
     * @return <code>true</code> if this matrix is considered equals to the specified object, esle <code>false</code>
     */
    @Override
    public boolean equals(Object o) {

        if (o instanceof Matrix) {

            Matrix other = (Matrix) o;

            EqualityFunction<Matrix> function =
                (EqualityFunction<Matrix>) FunctionSingletons.getFunction(FunctionIdentifiers.MATRIX_EQUALITY_FUNCTION);
            boolean result = function.equals(this, other);

            return result;
        }

        return false;
    }

}


/**
 * This class reresents a cell location within a matrix.
 *
 * @author Kristian Kutin
 */
final class Location {

    /**
     * The column of this cell.
     */
    public final Number column;

    /**
     * The row of this cell.
     */
    public final Number row;

    /**
     * Creates a new cell location according with the specified parameters.
     *
     * @param column
     *        a column
     * @param row
     *        a row
     */
    public Location(Number column, Number row) {

        super();

        this.column = column;
        this.row = row;
    }

    /**
     * Calculates the hash code for this cell location.
     *
     * @return a hash code
     */
    @Override
    public int hashCode() {

        return HashHelper.calculateHashCode(Location.class, column, row);
    }

    /**
     * Checks ifthis location is equal to the specified object.
     *
     * @param o
     *        an object
     *
     * @return <code>true</code> if this location is equal to the specified object, else <code>false</code>
     */
    @Override
    public boolean equals(Object o) {

        if (o == null) {

            return false;
        }

        if (this == o) {

            return true;
        }

        if (o instanceof Location) {

            Location other = (Location) o;

            return this.column == other.column && this.row == other.row;
        }

        return false;
    }

    /**
     * Returns a string representation for this location.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return String.format("(%s, %s)", column, row);
    }

}
