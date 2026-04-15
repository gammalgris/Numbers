/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2026  Kristian Kutin
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

package jmul.math.equations.operators;


/**
 * An enumeration of operators.
 *
 * @author Kristian Kutin
 */
public enum Operators implements Operator {


    ADDITION("+", 0),
    SUBTRACTION("-", 0),
    MULTIPLICATION("*", 1),
    DIVISION("/", 1);


    /**
     * A symbol for this operator.
     */
    private final String symbol;

    /**
     * A precedence for this operator.
     */
    private final int precedence;

    /**
     * Creates a new enumeration element according to the specified parameters.
     *
     * @param symbol
     *        a symbol for this operator
     * @param precedence
     *        a precedence for this operator.
     */
    private Operators(String symbol, int precedence) {

        if (symbol == null) {

            throw new IllegalArgumentException("No symbol (null) was specified!");
        }

        if (symbol.trim().isEmpty()) {

            throw new IllegalArgumentException("No symbol (empty string) was specified!");
        }

        this.symbol = symbol;
        this.precedence = precedence;
    }

    /**
     * The symbol for this operator.
     *
     * @return a symbol
     */
    @Override
    public String symbol() {

        return symbol;
    }

    /**
     * The precedence of this operator. The higher the number the higher the precedence.
     *
     * @return an integer value
     */
    @Override
    public int precedence() {

        return precedence;
    }

    /**
     * Returns a string representation for this operator.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return symbol;
    }

}
