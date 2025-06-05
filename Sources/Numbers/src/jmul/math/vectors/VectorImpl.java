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

import jmul.math.functions.implementations.ParameterCheckHelper;
import jmul.math.matrices.Matrix;
import jmul.math.numbers.Number;


/**
 * An implementation of a vector.
 *
 * @author Kristian Kutin
 */
public class VectorImpl implements Vector {

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

        super();

        this.dimensions = IndexSingletons.firstIndex().dec();
        this.components = new TreeMap<Number, Number>();
    }

    /**
     * Creates a vector according to the specified components.
     *
     * @param components
     *        an array of components
     */
    public VectorImpl(Number... components) {

        super();

        ParameterCheckHelper.checkParameter(components);

        this.components = new TreeMap<Number, Number>();

        Number dimension = IndexSingletons.firstIndex().dec();

        for (Number component : components) {

            ParameterCheckHelper.checkParameter(component);

            dimension = dimension.inc();
            addComponent(dimension, component);
        }

        this.dimensions = dimension;
    }

    /**
     * Creates a vector according to the specified components.
     *
     * @param components
     *        an iterable list or set of components
     */
    public VectorImpl(Iterable<Number> components) {

        super();

        ParameterCheckHelper.checkParameter(components);

        this.components = new TreeMap<Number, Number>();

        Number dimension = IndexSingletons.firstIndex().dec();

        Iterator<Number> iterator = components.iterator();
        while (iterator.hasNext()) {

            dimension = dimension.inc();
            Number component = iterator.next();
            addComponent(dimension, component);
        }

        this.dimensions = dimension;
    }

    /**
     * Creates a vector according to the specified components.
     *
     * @param components
     *        a stream which provides all components
     */
    public VectorImpl(Stream<Number> components) {

        super();

        ParameterCheckHelper.checkParameter(components);

        this.components = new TreeMap<Number, Number>();

        Number dimension = IndexSingletons.firstIndex().dec();

        Iterator<Number> iterator = components.iterator();
        while (iterator.hasNext()) {

            dimension = dimension.inc();
            Number component = iterator.next();
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
     * Returns the component for the specified dimension.
     *
     * @param dimension
     *        a dimension
     *
     * @return the component for the specified dimension
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

        // TODO Implement this method
        throw new UnsupportedOperationException();
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

        // TODO Implement this method
        throw new UnsupportedOperationException();
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

        // TODO Implement this method
        throw new UnsupportedOperationException();
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

        // TODO Implement this method
        throw new UnsupportedOperationException();
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

        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    /**
     * Calculates the triple product of this vector and the two specified vectors.
     *
     * @param vector1
     *        another vector
     * @param vector2
     *        another vector
     *
     * @return the triple product
     */
    @Override
    public Vector tripleProduct(Vector vector1, Vector vector2) {

        // TODO Implement this method
        throw new UnsupportedOperationException();
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

}
