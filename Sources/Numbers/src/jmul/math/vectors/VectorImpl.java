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

package jmul.math.vectors;


import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Stream;

import jmul.math.functions.FunctionSingletons;
import jmul.math.functions.implementations.ParameterCheckHelper;
import jmul.math.functions.repository.FunctionIdentifiers;
import jmul.math.hash.HashHelper;
import jmul.math.matrices.Matrix;
import jmul.math.numbers.Number;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.EqualityFunction;
import jmul.math.operations.MixedBinaryOperation;
import jmul.math.operations.Result;
import jmul.math.operations.TernaryOperation;


/**
 * An implementation of a vector.
 *
 * @author Kristian Kutin
 */
public class VectorImpl implements Vector {

    /**
     * The number base of this vector.
     */
    private final int base;

    /**
     * The dimensions of this vector.
     */
    private final Number dimensions;

    /**
     * The actual data structure that stores all components of this vector (i.e. index number/ dimension by
     * component value).
     */
    private final SortedMap<Number, Number> components;

    /**
     * The default constructor (i.e. a zero dimension vector).
     */
    public VectorImpl() {

        this(10);
    }

    /**
     * Creates a zero dimension vector.
     *
     * @param base
     *        a number base
     */
    public VectorImpl(int base) {

        super();

        this.base = ParameterCheckHelper.checkNumberBase(base);
        this.dimensions = IndexSingletons.firstIndex().dec();
        this.components = new TreeMap<Number, Number>();
    }

    /**
     * Creates a vector according to the specified components.
     *
     * @param base
     *        a number base
     * @param components
     *        an array of components
     */
    public VectorImpl(int base, Number... components) {

        super();

        ParameterCheckHelper.checkParameter(components);

        this.base = ParameterCheckHelper.checkNumberBase(base);
        this.components = new TreeMap<Number, Number>();

        Number dimension = IndexSingletons.firstIndex().dec();

        for (Number component : components) {

            ParameterCheckHelper.checkParameter(component);
            ParameterCheckHelper.checkNumberBase(base, component);

            dimension = dimension.inc();
            addComponent(dimension, component);
        }

        this.dimensions = dimension;
    }

    /**
     * Creates a vector according to the specified components.
     *
     * @param base
     *        a number base
     * @param components
     *        an iterable list or set of components
     */
    public VectorImpl(int base, Iterable<Number> components) {

        super();

        ParameterCheckHelper.checkParameter(components);

        this.base = ParameterCheckHelper.checkNumberBase(base);
        this.components = new TreeMap<Number, Number>();

        Number dimension = IndexSingletons.firstIndex().dec();

        Iterator<Number> iterator = components.iterator();
        while (iterator.hasNext()) {

            dimension = dimension.inc();
            Number component = iterator.next();

            ParameterCheckHelper.checkParameter(component);
            ParameterCheckHelper.checkNumberBase(base, component);

            addComponent(dimension, component);
        }

        this.dimensions = dimension;
    }

    /**
     * Creates a vector according to the specified components.
     *
     * @param base
     *        a number base
     * @param components
     *        a stream which provides all components
     */
    public VectorImpl(int base, Stream<Number> components) {

        super();

        ParameterCheckHelper.checkParameter(components);

        this.base = ParameterCheckHelper.checkNumberBase(base);
        this.components = new TreeMap<Number, Number>();

        Number dimension = IndexSingletons.firstIndex().dec();

        Iterator<Number> iterator = components.iterator();
        while (iterator.hasNext()) {

            dimension = dimension.inc();
            Number component = iterator.next();

            ParameterCheckHelper.checkParameter(component);
            ParameterCheckHelper.checkNumberBase(base, component);

            addComponent(dimension, component);
        }

        this.dimensions = dimension;
    }

    /**
     * Adds the specified component to this array.
     *
     * @param index
     *        an index number
     * @param component
     *        a component
     */
    private void addComponent(Number index, Number component) {

        components.put(index, component);
    }

    /**
     * The dimensions of this vector.
     *
     * @return the dimensions of this vector.
     */
    @Override
    public Number dimensions() {

        return dimensions;
    }

    /**
     * Returns the component of this vector for the specified dimension.
     *
     * @param dimension
     *        a dimension
     *
     * @return a component (i.e. number)
     */
    @Override
    public Number component(Number dimension) {

        ParameterCheckHelper.checkInteger(dimension);

        Number component = components.get(dimension);

        if (component == null) {

            String message = String.format("An illegal dimension (%s) was specified!", dimension);
            throw new IllegalArgumentException(message);
        }

        return component;
    }

    /**
     * Calculates the length of this vector.
     *
     * @return the length of this vector
     */
    @Override
    public Number length() {

        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    /**
     * Adds this vector and the specified vector.
     *
     * @param vector
     *        another vector
     *
     * @return the result of the addition
     */
    @Override
    public Vector add(Vector vector) {

        BinaryOperation<Vector, Result<Vector>> function =
            (BinaryOperation<Vector, Result<Vector>>) FunctionSingletons.getFunction(FunctionIdentifiers.ADD_VECTORS_FUNCTION);
        Result<Vector> result = function.calculate(this, vector);

        return result.result();
    }

    /**
     * Subtracts the specified vector form this vector.
     *
     * @param vector
     *        another vector
     *
     * @return the result of the subtraction
     */
    @Override
    public Vector subtract(Vector vector) {

        BinaryOperation<Vector, Result<Vector>> function =
            (BinaryOperation<Vector, Result<Vector>>) FunctionSingletons.getFunction(FunctionIdentifiers.SUBTRACT_VECTORS_FUNCTION);
        Result<Vector> result = function.calculate(this, vector);

        return result.result();
    }

    /**
     * Multiply this vector with the specified number (i.e. perform a scalar multiplication).
     *
     * @param number
     *        a number
     *
     * @return the result of the multiplication
     */
    @Override
    public Vector multiply(Number number) {

        MixedBinaryOperation<Vector, Number, Result<Vector>> function =
            (MixedBinaryOperation<Vector, Number, Result<Vector>>) FunctionSingletons.getFunction(FunctionIdentifiers.MULTIPLY_VECTOR_WITH_NUMBER_FUNCTION);
        Result<Vector> result = function.calculate(this, number);

        return result.result();
    }

    /**
     * Multiplies this vector with the specified vector (i.e. calculates the inner product of two vectors).
     *
     * @param vector
     *        another vector
     *
     * @return the result of the multiplication
     */
    @Override
    public Number scalarProduct(Vector vector) {

        BinaryOperation<Vector, Result<Number>> function =
            (BinaryOperation<Vector, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.SCALAR_PRODUCT_FUNCTION);
        Result<Number> result = function.calculate(this, vector);

        return result.result();
    }

    /**
     * Calculates the cross product of this vector and the specified vector.
     *
     * @param vector
     *        another vector
     *
     * @return the cross product
     */
    @Override
    public Vector crossProduct(Vector vector) {

        BinaryOperation<Vector, Result<Vector>> function =
            (BinaryOperation<Vector, Result<Vector>>) FunctionSingletons.getFunction(FunctionIdentifiers.CROSS_PRODUCT_FUNCTION);
        Result<Vector> result = function.calculate(this, vector);

        return result.result();
    }

    /**
     * Calculates the triple product of this vector and the two specified vectors (i.e. <code>(vector1 x vector2) * vector3</code>)
     *
     * @param vector1
     *        a vector
     * @param vector2
     *        a vector
     *
     * @return the triple product
     */
    @Override
    public Number tripleProduct(Vector vector1, Vector vector2) {

        TernaryOperation<Vector, Result<Number>> function =
            (TernaryOperation<Vector, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.TRIPLE_PRODUCT_FUNCTION);
        Result<Number> result = function.calculate(this, vector1, vector2);

        return result.result();
    }

    /**
     * Calculates the dyadic product of this vector and the specified vector. The underlying assumption ist that this
     * first vector is interpreted as a column vector and the specified vector is interpreted as a row vector.
     *
     * @param vector
     *        another vector
     *
     * @return the dyadic product
     */
    @Override
    public Matrix dyadicProduct(Vector vector) {

        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the number base for this vector.
     *
     * @return a base
     */
    @Override
    public int base() {

        return base;
    }

    /**
     * Returns an iterator for this vector.
     *
     * @return an iterator
     */
    @Override
    public Iterator<Number> iterator() {

        return this.components
                   .values()
                   .iterator();
    }

    /**
     * Calculates a hash code for this vector.
     *
     * @return a hash code
     */
    @Override
    public int hashCode() {

        return HashHelper.calculateHashCode(Vector.class, base, dimensions, components);
    }

    /**
     * Checks the equality of this vector and the specified object (i.e. vector).
     *
     * @param o
     *        another object (i.e. vector)
     *
     * @return <code>true</code> if this vector is equal to the specified vector, else <code>false</code>
     */
    @Override
    public boolean equals(Object o) {

        if (o instanceof Vector) {

            Vector other = (Vector) o;

            EqualityFunction<Vector> function =
                (EqualityFunction<Vector>) FunctionSingletons.getFunction(FunctionIdentifiers.VECTOR_EQUALITY_FUNCTION);
            boolean result = function.equals(this, other);

            return result;
        }

        return false;
    }

    /**
     * Returns a string representation for this vector.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        StringBuffer buffer = new StringBuffer();

        buffer.append("(");

        Iterator<Number> iterator = iterator();
        boolean first = true;

        while (iterator.hasNext()) {

            if (first) {

                first = false;

            } else {

                buffer.append(",");
            }

            Number component = iterator.next();

            buffer.append(component);
        }

        buffer.append(")");

        return buffer.toString();
    }

}
