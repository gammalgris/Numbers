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

package jmul.math.operations.implementations;


import jmul.math.logarithms.Logarithm;
import jmul.math.logarithms.LogarithmHelper;
import jmul.math.numbers.Number;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.Result;


/**
 * An implementation of a function that adds two logarithm expressions.
 *
 * @author Kristian Kutin
 */
public class AddLogarithmsFunction implements BinaryOperation<Logarithm, Result<Logarithm>> {

    /**
     * The default constructor.
     */
    public AddLogarithmsFunction() {

        super();
    }

    /**
     * Adds the specified logarithm expressions.
     *
     * @param logarithmExpression1
     *        a logarithm expression
     * @param logarithmExpression2
     *        a logarithm expression
     *
     * @return a logarithm expression
     */
    @Override
    public Result<Logarithm> calculate(Logarithm logarithmExpression1, Logarithm logarithmExpression2) {

        ParameterCheckHelper.checkParameters(logarithmExpression1, logarithmExpression2);

        if (!logarithmExpression1.logarithmBase().equals(logarithmExpression2.logarithmBase())) {

            throw new IllegalArgumentException("The logarithm bases of the specified logarithm expressions are not equal!");
        }

        Number logarithmBase = logarithmExpression1.logarithmBase();
        Number product = logarithmExpression1.numerus().multiply(logarithmExpression2.numerus());

        Logarithm logarithm = LogarithmHelper.createLogarithm(logarithmBase, product);

        return new Result<Logarithm>(logarithm);
    }

}
