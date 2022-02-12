/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2013  Kristian Kutin
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
 * $Id: cdc67e25845d5321cef6ca7f00d0bcfe6dc55894 $
 */

package jmul.checks;


import java.util.List;

import jmul.checks.exceptions.EmptyStringParameterException;
import jmul.checks.exceptions.NullListParameterException;
import jmul.checks.exceptions.NullParameterException;
import jmul.checks.exceptions.UnexpectedSizeException;


/**
 * A helper class for checking parameters.
 *
 * @author Kristian Kutin
 */
public final class ParameterCheckHelper {

    /**
     * The default constructor.
     */
    private ParameterCheckHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Checks the specified parameter.
     *
     * @param aClass
     *        a class parameter
     *
     * @return the specified class
     *
     * @throws IllegalArgumentException
     *         is thrown if the specified parameter is invalid
     */
    public static Class checkClassParameter(Class aClass) {

        if (aClass == null) {

            throw new NullParameterException();
        }

        return aClass;
    }

    /**
     * Checks the specified parameter.
     *
     * @param aMessage
     *        a string parameter
     *
     * @return the specified message
     *
     * @throws IllegalArgumentException
     *         is thrown if the specified parameter is invalid
     */
    public static String checkExceptionMessage(String aMessage) {

        return checkStringParameter(aMessage);
    }

    /**
     * Checks the specified parameter.
     *
     * @param aString
     *        a string parameter
     *
     * @return the specified string
     *
     * @throws IllegalArgumentException
     *         is thrown if the specified parameter is invalid
     */
    public static String checkStringParameter(String aString) {

        if (aString == null) {

            throw new NullParameterException();
        }

        String aTrimmedString = aString.trim();
        if (aTrimmedString.isEmpty()) {

            throw new EmptyStringParameterException();
        }

        return aString;
    }

    /**
     * Checks the specified parameter.
     *
     * @param aCause
     *        an exception parameter
     *
     * @return the specified exception cause
     *
     * @throws IllegalArgumentException
     *         is thrown if the specified parameter is invalid
     */
    public static Throwable checkExceptionCause(Throwable aCause) {

        if (aCause == null) {

            throw new NullParameterException();
        }

        return aCause;
    }

    /**
     * Checks the specified parameter.
     *
     * @param aList
     *        a list parameter
     *
     * @return the specified list
     */
    public static List checkList(List aList) {

        if (aList == null) {

            throw new NullListParameterException();
        }

        return aList;
    }

    /**
     * Checks the specified paramter.
     *
     * @param aList
     *        a list parameter
     * @param anExpectedSize
     *        the expected list size
     *
     * @return the specified list
     */
    public static List checkList(List aList, int anExpectedSize) {

        checkList(aList);

        if (anExpectedSize < 0) {

            String message = "A negative list size (" + anExpectedSize + ") was specified!";
            throw new IllegalArgumentException(message);
        }

        if (anExpectedSize != aList.size()) {

            throw new UnexpectedSizeException(anExpectedSize, aList.size());
        }

        return aList;
    }

}
