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

package jmul.math.numbers.conversions;


/**
 * This interface describes various conversion paths.
 *
 * @author Kristian Kutin
 */
public interface Conversions {

    /**
     * Converts this object to a primitive byte value.
     *
     * @return a byte value
     */
    byte toPrimitiveByte();

    /**
     * Converts this object to a primitive short value.
     *
     * @return a short value
     */
    short toPrimitiveShort();

    /**
     * Converts this object to a primitive int value.
     *
     * @return a int value
     */
    int toPrimitiveInt();

    /**
     * Converts this object to a primitive long value.
     *
     * @return a long value
     */
    long toPrimitiveLong();

    /**
     * Converts this object to a primitive float value.
     *
     * @return a float value
     */
    float toPrimitiveFloat();

    /**
     * Converts this object to a primitive double value.
     *
     * @return a double value
     */
    double toPrimitiveDouble();

    /**
     * Converts this object to a byte wrapper.
     *
     * @return a byte wrapper
     */
    Byte toByte();

    /**
     * Converts this object to a short value.
     *
     * @return a short wrapper
     */
    Short toShort();

    /**
     * Converts this object to a integer wrapper.
     *
     * @return a integer wrapper
     */
    Integer toInteger();

    /**
     * Converts this object to a long wrapper.
     *
     * @return a long wrapper
     */
    Long toLong();

    /**
     * Converts this object to a float wrapper.
     *
     * @return a float wrapper
     */
    Float toFloat();

    /**
     * Converts this object to a double wrapper.
     *
     * @return a double wrapper
     */
    Double toDouble();

}
