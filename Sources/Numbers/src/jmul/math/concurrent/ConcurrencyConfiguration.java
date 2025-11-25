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

package jmul.math.concurrent;


import java.util.ResourceBundle;


/**
 * A utility class for configurable aspects of concurrency (i.e. to access the configuration file).
 *
 * @author Kristian Kutin
 */
public final class ConcurrencyConfiguration {

    /**
     * The default constructor.
     */
    private ConcurrencyConfiguration() {

        throw new UnsupportedOperationException();
    }

    /**
     * Returns the resource bundle with concurrency configurations.
     *
     * @return a resource bundle
     */
    private static ResourceBundle getBundle() {

        return ResourceBundle.getBundle(ConcurrencyConfiguration.class.getName());
    }

    /**
     * Returns the number of threads configured for concurrent multiplications.
     *
     * @return the number of threads
     */
    public static int getConcurrentMultiplications() {

        ResourceBundle bundle = getBundle();

        String value = bundle.getString("concurrent.multiplications");

        return Integer.parseInt(value);
    }

}
