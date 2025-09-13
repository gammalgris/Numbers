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

package jmul.math.logarithms;


import jmul.math.functions.FunctionSingletons;
import jmul.math.functions.implementations.ParameterCheckHelper;
import jmul.math.functions.repository.FunctionIdentifiers;
import jmul.math.numbers.Number;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.Result;


/**
 * An implementation of a logarithm.
 *
 * @author Kristian Kutin
 */
public class LogarithmImpl implements Logarithm {

    /**
     * The loagrithm base.
     */
    private final Number logarithmBase;

    /**
     * The number of the logarithm expression.
     */
    private final Number numerus;

    /**
     * Creates a new logaroithm expression according to the specified numbers.
     *
     * @param logarithmBase
     *        the logarithm base
     * @param numerus
     *        the number
     */
    public LogarithmImpl(Number logarithmBase, Number numerus) {

        super();

        ParameterCheckHelper.checkParameters(logarithmBase, numerus);

        if (numerus.isZero() || numerus.isNegative()) {

            throw new IllegalArgumentException("The numerus of the logarithm must not be zero or negative!");
        }

        if (logarithmBase.isOne()) {

            throw new IllegalArgumentException("The base of the logarithm must not be one!");
        }

        this.logarithmBase = logarithmBase;
        this.numerus = numerus;
    }

    /**
     * Returns the logarithm base.
     *
     * @return the logarithm base (i.e. a number)
     */
    @Override
    public Number logarithmBase() {

        return logarithmBase;
    }

    /**
     * Returns the number of this logarithm expression.
     *
     * @return the number of this logarithm expression
     */
    @Override
    public Number numerus() {

        return numerus;
    }

    /**
     * Adds this logarithm and the specified logarithm. If the logarithm bases are equal then the numbers of the
     * logarithm expressions are multiplied.
     *
     * @param logarithm
     *        a logarithm expression
     *
     * @return a logarithm expression
     */
    @Override
    public Logarithm add(Logarithm logarithm) {

        BinaryOperation<Logarithm, Result<Logarithm>> function =
            (BinaryOperation<Logarithm, Result<Logarithm>>) FunctionSingletons.getFunction(FunctionIdentifiers.ADD_LOGARITHMS_FUNCTION);
        Result<Logarithm> result = function.calculate(this, logarithm);

        return result.result();
    }

    /**
     * Subtracts the specified logarithm expression from this logarithm expression. If the logarithm bases are equal
     * then the number of this logarithm expression is divided by the number of the specified logarithm expression.
     *
     * @param logarithm
     *        a logarithm expression
     *
     * @return a logarithm expression
     */
    @Override
    public Logarithm subtract(Logarithm logarithm) {

        BinaryOperation<Logarithm, Result<Logarithm>> function =
            (BinaryOperation<Logarithm, Result<Logarithm>>) FunctionSingletons.getFunction(FunctionIdentifiers.SUBTRACT_LOGARITHMS_FUNCTION);
        Result<Logarithm> result = function.calculate(this, logarithm);

        return result.result();
    }

    @Override
    public Number evaluate() {
        // TODO Implement this method
        return null;
    }

    /**
     * Returns the base of the underlying numeral system for this number.
     *
     * @return a base
     */
    @Override
    public int base() {

        return logarithmBase.base();
    }

    /**
     * Returns a string representation for this logarithm expression.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        StringBuilder buffer = new StringBuilder();

        buffer.append("log");
        buffer.append(logarithmBase);
        buffer.append("(");
        buffer.append(numerus);
        buffer.append(")");

        return buffer.toString();
    }

}
