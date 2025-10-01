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

import jmul.math.operations.repository.OperationIdentifier;


/**
 * A helper class for utility functions regarding operation identifiers.
 *
 * @author Kristian Kutin
 */
public final class OperationIdentifierHelper {

    /**
     * An array index.
     */
    private static final int FIRST_ALGORITHM_INDEX;

    /*
     * The static initializer.
     */
    static {

        FIRST_ALGORITHM_INDEX = 0;
    }

    /**
     * The default constructor.
     */
    private OperationIdentifierHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Checks if the specified algorithm list (i.e. array) contains the specified algorithm.
     *
     * @param allowedAlgorithms
     *        a list of allowed algorithms (i.e. array of allowed algorithms)
     * @param algorithm
     *        an algorithm
     *
     * @return <code>true</code> if the specified algorithm is found in the specified list of allowed algorithms,
     *         else <code>false</code>
     */
    private static boolean containsAlgorithm(OperationIdentifier[] allowedAlgorithms, OperationIdentifier algorithm) {

        for (int index = 0; index < allowedAlgorithms.length; index++) {

            OperationIdentifier actualAlgorithm = allowedAlgorithms[index];

            if (actualAlgorithm == algorithm) {

                return true;
            }
        }

        return false;
    }

    /**
     * Checks if the specified algorithm is one of the allowed algorithms. If no algorithm (i.e. <code>null</code>) is
     * specified then the default algorithm is returned (i.e. the default algorithm is the first algorithm in the list
     * of allowed algorithms).
     *
     * @param allowedAlgorithms
     *        a list of allowed algorithms (i.e. an array of allowed algorithms and the first algorithm is the default
     *        algorithm)
     * @param algorithm
     *        an algorithm or <code>null</code> for the default algorithm
     */
    public static OperationIdentifier checkAndReturnAlgorithm(OperationIdentifier[] allowedAlgorithms,
                                                              OperationIdentifier algorithm) {

        if (allowedAlgorithms == null) {

            throw new IllegalArgumentException("No array of allowed algorithms (null) was specified!");
        }

        if (allowedAlgorithms.length == 0) {

            throw new IllegalArgumentException("No array of allowed algorithms (empty array) was specified!");
        }

        if (algorithm == null) {

            return allowedAlgorithms[FIRST_ALGORITHM_INDEX];
        }

        if (containsAlgorithm(allowedAlgorithms, algorithm)) {

            return algorithm;
        }

        String message = String.format("An invalid algorithm (%s) was specified!", algorithm);
        throw new IllegalArgumentException(message);
    }

}
