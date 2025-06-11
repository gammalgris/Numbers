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
import java.util.Map;

import jmul.math.hash.HashHelper;
import jmul.math.numbers.Number;
import jmul.math.vectors.IndexSingletons;
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

        this.base = base;

        Number ZERO = IndexSingletons.firstIndex().dec();

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

        this.base = base;

        //TODO
        throw new UnsupportedOperationException();
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

        //TODO not implemented yet
        throw new UnsupportedOperationException();
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

        //TODO not implemented yet
        throw new UnsupportedOperationException();
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

        //TODO not implemented yet
        throw new UnsupportedOperationException();
    }

    /**
     * Transposes this matrix.
     *
     * @return a transposed matrix
     */
    @Override
    public Matrix transpose() {

        //TODO not implemented yet
        throw new UnsupportedOperationException();
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

        //TODO not implemented yet
        throw new UnsupportedOperationException();
    }

    /**
     * Performs a vetoriazation of this matrix (i.e. transforms the matrix to a vector).
     *
     * @return a vector
     */
    @Override
    public Vector toVector() {

        //TODO not implemented yet
        throw new UnsupportedOperationException();
    }

    /**
     * Returns a string representation for this matrix.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        //TODO not implemented yet
        throw new UnsupportedOperationException();
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
