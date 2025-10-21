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

package jmul.math.functions;


import jmul.math.numbers.Number;
import jmul.math.operations.implementations.ParameterCheckHelper;
import jmul.math.operations.processing.ProcessingDetails;


 /**
  * An implementation of an sinus function f(x) = c<sub>1</sub> * sin(x) + c<sub>0</sub>.
  *
  * @author Kristian Kutin
  */
public class SinusDegreeFunctionImpl extends FunctionBaseImpl {

    /**
     * A coefficient.
     */
    private final Number coefficient1;

    /**
     * A coefficient.
     */
    private final Number coefficient0;

    public SinusDegreeFunctionImpl(Number coefficient1, Number coefficient0) {

        super(extractBase(coefficient1, coefficient0));
        
        this.coefficient1 = coefficient1;
        this.coefficient0 = coefficient0;
    }

    /**
     * Tries to extract a number base from the specified parameters.
     *
     * @param coefficient1
     *        a coefficient
     * @param coefficient0
     *        a coefficient
     *
     * @return a number base
     */
    private static int extractBase(Number coefficient1, Number coefficient0) {

        ParameterCheckHelper.checkParameters(coefficient1, coefficient0);

        return coefficient0.base();
    }

    @Override
    public Number calculate(ProcessingDetails processingDetails, Number x) {
        // TODO Implement this method
        return null;
    }

    @Override
    public Function derivativeFunction() {
        // TODO Implement this method
        return null;
    }

    @Override
    public String toString() {

        // TODO Implement this method
        return null;
    }

}
