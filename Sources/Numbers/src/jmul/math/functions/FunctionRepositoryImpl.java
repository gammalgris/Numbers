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

package jmul.math.functions;


import java.util.HashMap;
import java.util.Map;


/**
 * A simple implementation for a function repository.
 *
 * @author Kristian Kutin
 */
public class FunctionRepositoryImpl implements FunctionRepository {

    /**
     * This map contains all known identifiers and associated function classes.
     */
    private Map<FunctionIdentifier, Class> identifierClassMap;

    /**
     * This map contains all known identifiers and associated instantiated functions.
     */
    private Map<FunctionIdentifier, Function> identifierFunctionMap;

    /**
     * The default constructor.
     */
    public FunctionRepositoryImpl() {

        super();

        identifierClassMap = new HashMap<>();
        identifierFunctionMap = new HashMap<>();
    }

    /**
     * Checks the specified function identifier.
     *
     * @param identifier
     *        a function identifier
     */
    private static void checkIdentifier(FunctionIdentifier identifier) {

        if (identifier == null) {

            String message = "No valid identifier (null) has been specified!";
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Checks the specified class.
     *
     * @param clazz
     *        a function implementation
     */
    private static void checkClass(Class clazz) {

        if (clazz == null) {

            String message = "No valid class (null) has been specified!";
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Registers a function with the specified identifier and implementation class. The function implementation
     * must have a parameterless constructor.
     *
     * @param identifier
     *        a function identifier
     * @param implementationClass
     *        an implementation class
     */
    @Override
    public void registerFunction(FunctionIdentifier identifier, Class implementationClass) {

        checkIdentifier(identifier);
        checkClass(implementationClass);

        if (identifierClassMap.containsKey(identifier)) {

            throw new FunctionExistsException(identifier.toString());
        }

        identifierClassMap.put(identifier, implementationClass);
    }

    /**
     * Removes the function with the function corresponding to the specified identifier.
     *
     * @param identifier
     *        a function identifier
     */
    @Override
    public void removeFunction(FunctionIdentifier identifier) {

        checkIdentifier(identifier);

        if (!identifierClassMap.containsKey(identifier)) {

            String message =
                String.format("No function with the specified identifier (%s) is registered!", identifier.toString());
            throw new FunctionDoesntExistException(message);
        }

        identifierClassMap.remove(identifier);

        if (identifierFunctionMap.containsKey(identifier)) {

            identifierFunctionMap.remove(identifier);
        }
    }

    /**
     * Returns the fucntion correspondig to the specified identifier.
     *
     * @param identifier
     *        a function identifier
     *
     * @return a function
     */
    @Override
    public Function getFunction(FunctionIdentifier identifier) {

        checkIdentifier(identifier);

        if (!identifierClassMap.containsKey(identifier)) {

            throw new FunctionDoesntExistException(identifier);
        }

        if (identifierFunctionMap.containsKey(identifier)) {

            return identifierFunctionMap.get(identifier);
        }

        Class clazz = identifierClassMap.get(identifier);

        Function function;
        try {

            function = (Function) clazz.newInstance();
            identifierFunctionMap.put(identifier, function);

        } catch (IllegalAccessException | InstantiationException e) {

            String message = String.format("Unable to initialize function (%s)", identifier.toString());
            throw new IllegalArgumentException(message, e);
        }

        return function;
    }

}
