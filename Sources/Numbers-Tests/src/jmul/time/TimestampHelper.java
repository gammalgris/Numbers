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

package jmul.time;


import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;


/**
 * A utility class for timestamps.
 *
 * @author Kristian Kutin
 */
public final class TimestampHelper {

    /**
     * A pattern to generate a timestamp.
     */
    private static final String TIMESTAMP_PATTERN;

    /**
     * An entity that generates a time stamp from a date.
     */
    private static final DateFormat DATE_FORMAT;

    /*
     * The static initializer.
     */
    static {

        TIMESTAMP_PATTERN = "yyyy.MM.dd-hh:mm:ss";
        DATE_FORMAT = new SimpleDateFormat(TIMESTAMP_PATTERN);
    }

    /**
     * The default constructor.
     */
    private TimestampHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Returns a timestamp.
     *
     * @return a timestamp
     */
    public static String timestamp() {

        Date now = new Date();

        return DATE_FORMAT.format(now);
    }

}
