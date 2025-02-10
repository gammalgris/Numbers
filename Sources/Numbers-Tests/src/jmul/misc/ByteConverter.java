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

package jmul.misc;


/**
 * A utlity class for converting bytes into other units.
 *
 * @author Kristian Kutin
 */
public final class ByteConverter {

    /**
     * A factor to convert bytes into kilobytes.
     */
    public static final long BYTES_TO_KILOBYTES;

    /**
     * A factor to convert bytes into megabytes.
     */
    public static final long BYTES_TO_MEGABYTES;

    /**
     * A factor to convert bytes into gigabytes.
     */
    public static final long BYTES_TO_GIGABYTES;

    /*
     * The static initializer.
     */
    static {

        BYTES_TO_KILOBYTES = 1024L;
        BYTES_TO_MEGABYTES = BYTES_TO_KILOBYTES * 1024L;
        BYTES_TO_GIGABYTES = BYTES_TO_MEGABYTES * 1024L;
    }

    /**
     * The default constructor.
     */
    private ByteConverter() {

        throw new UnsupportedOperationException();
    }

    /**
     * Converts the specified amount of memory (bytes) to the designated amount of
     * memory (factor).
     *
     * @param amountMemory
     *        an amount of memory in bytes
     * @param factor
     *        a factor to convert the bytes to
     *
     * @return a converted amount of memory
     */
    public static long convertTo(long amountMemory, long factor) {

        return amountMemory / factor;
    }

}
