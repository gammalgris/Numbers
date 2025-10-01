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

package jmul.math.operations.processing;


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
     * A constant representing the default algorithm.
     */
    public static final OperationIdentifier DEFAULT_ALGORITHM;

    /**
     * A constant representing a default precision.
     */
    public static final Number DEFAULT_PRECISION;

    /**
     * A constant representing a default iteration depth.
     */
    public static final Number DEFAULT_ITERATION_DEPTH;

    /*
     * The static initializer.
     */
    static {

        DEFAULT_ALGORITHM = null;
        DEFAULT_PRECISION = null;
        DEFAULT_ITERATION_DEPTH = null;
    }

    /**
     * (Optional) The identifier for an algorithm.
     *
     * <i>Note:<br>
     * For some operations there exist several implementations of different algorithm. The difference may be speed,
     * precision/ aproximation, etc..</i>
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
     * @param decimalPlaces
     *        the number of decimal places retained after cutting the fraction part
     * @param iterations
     *        an iteration depth
     */
    private ProcessingDetails(OperationIdentifier algorithm, Number decimalPlaces, Number iterations) {

        super();

        if (decimalPlaces != DEFAULT_PRECISION) {

            ParameterCheckHelper.checkPositiveInteger(decimalPlaces);
        }

        if (iterations != DEFAULT_ITERATION_DEPTH) {

            ParameterCheckHelper.checkPositiveIntegerGreaterZero(iterations);
        }

        this.algorithm = algorithm;
        this.decimalPlaces = decimalPlaces;
        this.iterations = iterations;
    }

    /**
     * Checks if the default algorithm is specified.
     *
     * @return <code>true</code> if the default algorithm was specified, else <code>false</code>
     */
    public boolean isDefaultAlgorithm() {

        return algorithm == DEFAULT_ALGORITHM;
    }

    /**
     * Checks if the default precision is specified.
     *
     * @return <code>true</code> if the default precision was specified, else <code>false</code>
     */
    public boolean isDefaultPrecision() {

        return decimalPlaces == DEFAULT_PRECISION;
    }

    /**
     * Checks if the default iteration depths is specified.
     *
     * @return <code>true</code> if the default iteration depths was specified, else <code>false</code>
     */
    public boolean isDefaultIterationDepth() {

        return iterations == DEFAULT_ITERATION_DEPTH;
    }

    /**
     * Creates a copy of this entity but changes the algorithm.
     *
     * @param algorithm
     *        an identifier for an algorithm
     *
     * @return a modified clone of the processing details
     */
    public ProcessingDetails replaceAlgorithm(OperationIdentifier algorithm) {

        return new ProcessingDetails(algorithm, this.decimalPlaces, this.iterations);
    }

    /**
     * Creates a copy of this entity but changes the specified precision.
     *
     * @param decimalPlaces
     *        a number precision
     *
     * @return a modified clone of the processing details
     */
    public ProcessingDetails replacePrecision(Number decimalPlaces) {

        return new ProcessingDetails(this.algorithm, decimalPlaces, this.iterations);
    }

    /**
     * Creates a copy of this entity but changes the specified iterations.
     *
     * @param iterations
     *        an iteration depth
     *
     * @return a modified clone of the processing details
     */
    public ProcessingDetails replaceIterationDepth(Number iterations) {

        return new ProcessingDetails(this.algorithm, this.decimalPlaces, iterations);
    }

    /**
     * Checks the algorithm.
     *
     * @param allowedAlgorithms
     *        an array of allowed algorithms
     *
     * @return an algorithm
     */
    public OperationIdentifier checkAndReturnAlgorithm(OperationIdentifier[] allowedAlgorithms) {

        return OperationIdentifierHelper.checkAndReturnAlgorithm(allowedAlgorithms, this.algorithm);
    }

    /**
     * Checks the precision.
     *
     * @param defaultDecimalPlaces
     *        a default precision
     *
     * @return a precision
     */
    public Number checkAndReturnPrecision(Number defaultDecimalPlaces) {

        if (defaultDecimalPlaces == null) {

            throw new IllegalArgumentException("No default value (i.e. precision) was specified!");
        }

        if (this.decimalPlaces == DEFAULT_PRECISION) {

            return defaultDecimalPlaces;
        }

        return this.decimalPlaces;
    }

    /**
     * Checks the iteration depth.
     *
     * @param defaultIterations
     *        a default iteration depths
     *
     * @return an iteration depth
     */
    public Number checkAndReturnIterationDepth(Number defaultIterations) {

        if (defaultIterations == null) {

            throw new IllegalArgumentException("No default value (i.e. iteration depth) was specified!");
        }

        if (this.iterations == DEFAULT_ITERATION_DEPTH) {

            return defaultIterations;
        }

        return this.iterations;
    }

    /**
     * Set prcoessing details.
     *
     * @param algorithm
     *        an identifier for an algorithm
     * @param decimalPlaces
     *        a number precision
     * @param iterations
     *        an iteration depth
     *
     * @return processing details
     */
    public static ProcessingDetails setProcessingDetails(OperationIdentifier algorithm, Number decimalPlaces,
                                                         Number iterations) {

        return new ProcessingDetails(algorithm, decimalPlaces, iterations);
    }

    /**
     * Set processing details.
     *
     * @param algorithm
     *        an identifier for an algorithm
     *
     * @return processing details
     */
    public static ProcessingDetails setAlgorithm(OperationIdentifier algorithm) {

        return new ProcessingDetails(algorithm, DEFAULT_PRECISION, DEFAULT_ITERATION_DEPTH);
    }

    /**
     * Set processing details.
     *
     * @param decimalPlaces
     *        a number precision
     *
     * @return processing details
     */
    public static ProcessingDetails setPrecision(Number decimalPlaces) {

        return new ProcessingDetails(DEFAULT_ALGORITHM, decimalPlaces, DEFAULT_ITERATION_DEPTH);
    }

    /**
     * Set processing details.
     *
     * @param iterations
     *        an iteration depth
     *
     * @return processing details
     */
    public static ProcessingDetails setIterationDepth(Number iterations) {

        return new ProcessingDetails(DEFAULT_ALGORITHM, DEFAULT_PRECISION, iterations);
    }

}
