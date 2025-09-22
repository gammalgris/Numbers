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

package jmul.math.operations.repository;


import jmul.math.operations.Operation;


/**
 * This interface describes a repository for mathematical functions/ operations. The repository allows registering and
 * removing functions. Only registered functions can be accessed. Functions are instantiated on a "as needed basis"
 * (i.e. lazy initialization).
 *
 * @author Kristian Kutin
 */
public interface OperationRepository {

    /**
     * Registers a function with the specified identifier and implementation class. The function implementation
     * must have a parameterless constructor.
     *
     * @param identifier
     *        a function identifier
     * @param implementationClass
     *        an implementation class
     */
    void registerFunction(OperationIdentifier identifier, Class implementationClass);

    /**
     * Removes the function with the function corresponding to the specified identifier.
     *
     * @param identifier
     *        a function identifier
     */
    void removeFunction(OperationIdentifier identifier);

    /**
     * Returns the fucntion correspondig to the specified identifier.
     *
     * @param identifier
     *        a function identifier
     *
     * @return a function
     */
    Operation getFunction(OperationIdentifier identifier);

}
