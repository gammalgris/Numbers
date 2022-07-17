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

package jmul.math.numbers.exceptions;


import java.util.List;

import jmul.string.Constants;


/**
 * A custom exception for cases when number strings cannot be parsed. When parsing a number string several
 * different parsing algorithms are applied in order to support different number notations.All individual
 * parsing exceptions are added as suppressed exceptions.
 *
 * @author Kristian Kutin
 */
public class NumberParsingException extends RuntimeException {

    /**
     * Creates a new exception according to the specified parameters.
     *
     * @param numberString
     *        the number string which couldn't be parsed
     * @param parsingExceptions
     *        all exceptions from different parsing algorithms
     */
    public NumberParsingException(CharSequence numberString, List<Throwable> parsingExceptions) {

        super(createMessage(numberString), new JoinedParsingExceptions(parsingExceptions));
    }

    /**
     * Creates an individualized error message.
     *
     * @param numberString
     *        the number string which couldn't be parsed
     *
     * @return an error message
     */
    private static String createMessage(CharSequence numberString) {

        return String.format("The specified number string (\"%s\") could not be parsed!", numberString);
    }

}

/**
 * A custom exceptions for merging parsing exceptions with a custom error message.
 *
 * @author Kristian Kutin
 */
class JoinedParsingExceptions extends Throwable {

    /**
     * Creates a new exception according to the specified parameters.
     *
     * @param exceptions
     *        all parsing exceptions
     */
    public JoinedParsingExceptions(List<Throwable> exceptions) {

        super(createMessage(exceptions));

        for (Throwable parsingException : exceptions) {

            addSuppressed(parsingException);
        }
    }

    /**
     * Creates a custom error message.
     *
     * @param exceptions
     *        all parsing exceptions
     *
     * @return an error message
     */
    private static String createMessage(List<Throwable> exceptions) {

        StringBuilder buffer = new StringBuilder();
        buffer.append("All collected parsing exceptions:");

        int count = 1;
        for (Throwable parsingException : exceptions) {

            buffer.append(Constants.NEW_LINE);
            buffer.append(Constants.NEW_LINE);
            buffer.append(Constants.TABULATOR);
            buffer.append(" {");
            buffer.append(count);
            buffer.append("} ");
            buffer.append(parsingException.getMessage());

            count++;
        }

        return buffer.toString();
    }
}
