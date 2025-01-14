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


import java.util.ResourceBundle;

import jmul.math.functions.repository.FunctionIdentifier;
import jmul.math.functions.repository.FunctionRepository;
import jmul.math.functions.repository.FunctionRepositoryInitializer;


/**
 * This class manages function singletons (i.e. instances of classes that implement a strategy pattern) and shouldn't
 * exist multiple times.
 *
 * @author Kristian Kutin
 */
public final class FunctionSingletons {

    /**
     * A property key.
     */
    private static final String INITIALIZER_KEY;

    /**
     * A map containing all singletons.
     */
    private static final FunctionRepository FUNCTION_REPOSITORY;

    /*
     * The static initializer.
     */
    static {

        INITIALIZER_KEY = "initializer";

        ResourceBundle bundle = ResourceBundle.getBundle(FunctionSingletons.class.getName());
        String className = bundle.getString(INITIALIZER_KEY);

        try {

            Class initializerClass = Class.forName(className);
            FunctionRepositoryInitializer initializer = (FunctionRepositoryInitializer) initializerClass.newInstance();
            FUNCTION_REPOSITORY = initializer.init();

        } catch (ClassNotFoundException e) {

            throw new RuntimeException("Unable to initialize the function repository!", e);

        } catch (IllegalAccessException | InstantiationException e) {

            throw new RuntimeException("Unable to initialize the function repository!", e);
        }
    }

    /**
     * The default constructor.
     */
    private FunctionSingletons() {

        throw new UnsupportedOperationException();
    }

    /**
     * Registers a function with the associated identifier.
     *
     * @param identifier
     *        a function identifier
     * @param implementationClass
     *        a function implementation
     */
    public static void registerFunction(FunctionIdentifier identifier, Class implementationClass) {

        FUNCTION_REPOSITORY.registerFunction(identifier, implementationClass);
    }

    /**
     * Returns the function associated with the specified identifier.
     *
     * @param identifier
     *        the name of a function
     *
     * @return a function
     */
    public static Function getFunction(FunctionIdentifier identifier) {

        return FUNCTION_REPOSITORY.getFunction(identifier);
    }

}
