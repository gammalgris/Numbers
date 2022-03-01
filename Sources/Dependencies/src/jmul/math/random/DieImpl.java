/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2015  Kristian Kutin
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

package jmul.math.random;


/**
 * An implementation of a standard die where each side is represented by a number.
 *
 * @author Kristian Kutin
 */
public class DieImpl implements Die {

    /**
     * The serial UID which is required by java's serialization mechanism.
     */
    private static final long serialVersionUID = 1L;

    /**
     * A minimum value.
     */
    private static final int ALLOWED_MINIMUM_VALUE;

    /*
     * The static initializer.
     */
    static {

        ALLOWED_MINIMUM_VALUE = 1;
    }

    /**
     * The number of sides of this die.
     */
    private final int sides;

    /**
     * Creates a new die according to the specified parameters.
     *
     * @param sides
     *        the sides of the die
     */
    public DieImpl(int sides) {

        checkParameter(sides);

        this.sides = sides;
    }

    /**
     * A getter method.
     *
     * @return the sides of this die
     */
    @Override
    public int getSides() {

        return sides;
    }

    /**
     * Performs a die roll.
     *
     * @return the result of a die roll
     */
    @Override
    public int roll() {

        return (int) (Math.random() * getSides() + 1);
    }

    /**
     * Returns a string representation of this die.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        StringBuilder buffer = new StringBuilder();

        buffer.append("d");
        buffer.append(getSides());

        return String.valueOf(buffer);
    }

    /**
     * Checks if the specified numeric value is withing the allowed value range.
     *
     * @param aNumericValue
     */
    private static void checkParameter(int aNumericValue) {

        if (aNumericValue < ALLOWED_MINIMUM_VALUE) {

            String message =
                String.format("An invalid value was specified (value=%d; allowed minimum value=%d)!", aNumericValue,
                              ALLOWED_MINIMUM_VALUE);
            throw new IllegalArgumentException(message);
        }
    }

}
