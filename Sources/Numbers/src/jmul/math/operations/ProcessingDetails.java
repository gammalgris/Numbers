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

package jmul.math.operations;


import jmul.math.numbers.Number;
import jmul.math.operations.implementations.ParameterCheckHelper;
import jmul.math.operations.repository.OperationIdentifier;


/**
 * A parameter class which combines various aspects of processing details (e.g. precision details regarding decimal
 * places for rounding), performance details (e.g. a choice between available alternative algorithms for an
 * operation), etc..<br>
 * <br>
 * <i>Note:<br>
 * This class should help clean up method signatures and avoid ambiguities.</i>
 *
 * @author Kristian Kutin
 */
public final class ProcessingDetails {

    /**
     * (Mandatory) The identifier for an algorithm.
     *
     * <i>Note:<br>
     * For some operations there exist several implementations of different algorithm. The difference may be performance
     * or precision/ aproximation.</i>
     */
    public final OperationIdentifier algorithm;

    /**
     * (Optional) The number of decimal places retained after cutting the fraction part. This may be <code>null</code>.
     */
    public final Number decimalPlaces;

    /**
     * (Optional) Some algorithms (e.g. recursive functions) need an iteration depth. This may be <code>null</code>.
     */
    public final Number iterations;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param algorithm
     *        the identifier for an algorithm
     */
    public ProcessingDetails(OperationIdentifier algorithm) {

        this(algorithm, null, null);
    }

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param algorithm
     *        the identifier for an algorithm
     * @param decimalPlaces
     *        the number of decimal places retained after cutting the fraction part
     */
    public ProcessingDetails(OperationIdentifier algorithm, Number decimalPlaces) {

        this(algorithm, decimalPlaces, null);
    }

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param algorithm
     *        the identifier for an algorithm
     * @param decimalPlaces
     *        the number of decimal places retained after cutting the fraction part
     * @param iterations
     *        an iteration depth
     */
    public ProcessingDetails(OperationIdentifier algorithm, Number decimalPlaces, Number iterations) {

        super();

        if (algorithm == null) {

            throw new IllegalArgumentException("No algorithm identifier was specified!");
        }

        if (decimalPlaces != null) {

            ParameterCheckHelper.checkPositiveInteger(decimalPlaces);
        }

        if (iterations != null) {

            ParameterCheckHelper.checkPositiveIntegerGreaterZero(iterations);
        }

        this.algorithm = algorithm;
        this.decimalPlaces = decimalPlaces;
        this.iterations = iterations;
    }

    /**
     * Checks if a precision (i.e. decimal places) is provided.
     *
     * @return <code>true</code> if a precision is provided, else <code>false</code>
     */
    public boolean hasDecimalPlaces() {

        return decimalPlaces != null;
    }

    /**
     * Checks if a recursion depth is provided.
     *
     * @return <code>true</code> if a recursion depth is provided, else <code>false</code>
     */
    public boolean hasIterations() {

        return iterations != null;
    }

    /**
     * Creates a copy of this entity but changes the algorithm.
     *
     * @param algorithm
     *        an identifiery for an algorithm
     *
     * @return a modified clone
     */
    public ProcessingDetails replaceAlgorithm(OperationIdentifier algorithm) {

        return new ProcessingDetails(algorithm);
    }

    /**
     * Creates a copy of this entity but changes the specified iterations.
     *
     * @param iterations
     *        an iteration depths
     *
     * @return a modified clone
     */
    public ProcessingDetails replaceIterations(Number iterations) {

        return new ProcessingDetails(this.algorithm, this.decimalPlaces, iterations);
    }

    /**
     * Creates a copy of this entity but changes the specified precision.
     *
     * @param decimalPlaces
     *        a number precision
     *
     * @return a modified clone
     */
    public ProcessingDetails replacePrecision(Number decimalPlaces) {

        return new ProcessingDetails(this.algorithm, decimalPlaces, this.iterations);
    }

}
