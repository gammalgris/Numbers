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


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import jmul.math.functions.conditions.Condition;
import jmul.math.functions.conditions.ConditionFunctionEntry;
import jmul.math.numbers.Number;


/**
 * Implements a function with a threshold (i.e. function1 &lt; threshold &lt;= function2).
 *
 * @author Kristian Kutin
 */
public class PartialFunctionImpl extends FunctionBaseImpl {

    /**
     * A map containing all functions and conditions.
     */
    private Map<Condition<Number>, Function> functionMap;

    /**
     * Creates a new threshold function.
     *
     * @param entries
     *        all condition-&gt;function entries
     */
    PartialFunctionImpl(ConditionFunctionEntry... entries) {

        super(extractBase(entries));

        this.functionMap = toUndmodifiableMap(entries);
    }

    /**
     * Checks the specified parameter and tries to extract a number base.
     *
     * @param entries
     *        all partial functions
     *
     * @return a number base
     */
    private static int extractBase(ConditionFunctionEntry[] entries) {

        if (entries == null) {

            throw new IllegalArgumentException("No partial functions (null) were specified!");
        }

        if (entries.length == 0) {

            throw new IllegalArgumentException("No partial functions (empty array) were specified!");
        }

        return entries[0].base;
    }

    /**
     * Transforms the specified arra to a map.
     *
     * @param entries
     *        partial function entries
     *
     * @return a map
     */
    private static Map<Condition<Number>, Function> toUndmodifiableMap(ConditionFunctionEntry[] entries) {

        Map<Condition<Number>, Function> tmpMap = new HashMap<>();
        for (ConditionFunctionEntry entry : entries) {

            tmpMap.put(entry.condition, entry.function);
        }

        return Collections.unmodifiableMap(tmpMap);
    }

    /**
     * Calculates the output value.
     *
     * @param number
     *        a value
     *
     * @return an output value
     */
    @Override
    public Number calculate(Number number) {

        for (Map.Entry<Condition<Number>, Function> entry : functionMap.entrySet()) {

            Condition<Number> condition = entry.getKey();
            Function function = entry.getValue();

            if (condition.meetsCondition(number)) {

                return function.calculate(number);
            }
        }

        throw new MissingConditionCaseException();
    }

    /**
     * Returns the derivative function for this function.
     *
     * @return a derivative function
     */
    @Override
    public Function derivativeFunction() {

        int length = functionMap.size();
        ConditionFunctionEntry[] entries = new ConditionFunctionEntry[length];

        int index = 0;
        for (Map.Entry<Condition<Number>, Function> entry : functionMap.entrySet()) {

            Condition<Number> condition = entry.getKey();
            Function function = entry.getValue();

            entries[index] = new ConditionFunctionEntry(condition, function.derivativeFunction());
            index++;
        }

        return new PartialFunctionImpl(entries);
    }

    /**
     * Returns a string representation for this function.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        StringBuilder buffer = new StringBuilder();

        buffer.append("{ ");

        boolean first = true;
        for (Map.Entry<Condition<Number>, Function> entry : functionMap.entrySet()) {

            if (first) {

                first = false;

            } else {

                buffer.append("; ");
            }

            Condition<Number> condition = entry.getKey();
            Function function = entry.getValue();

            buffer.append(condition);
            buffer.append(" : ");
            buffer.append(function);
        }

        buffer.append(" }");

        return buffer.toString();
    }

}
