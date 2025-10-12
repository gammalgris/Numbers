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

package jmul.math.functions.conditions;


import jmul.math.numbers.Number;

import java.util.Comparator;


/**
 * A custom comparator for conditions.
 *
 * @author Kristian Kutin
 */
public class ConditionComparator implements Comparator<Condition> {

    /**
     * The default constructor.
     */
    public ConditionComparator() {

        super();
    }

    /**
     * Compares the specified condition.
     *
     * @param c1
     *        a condition
     * @param c2
     *        a condition
     *
     * @return <code>0</code> if both conditions are considered equal, <code>-1</code> if the first condition comes
     *         after the second condition, <code>1</code> comes before the second condition.
     */
    @Override
    public int compare(Condition c1, Condition c2) {

        Number n1 = c1.threshold();
        Number n2 = c2.threshold();

        int result = n1.compareTo(n2);

        if (result == 0) {

            String s1 = String.valueOf(c1);
            String s2 = String.valueOf(c2);

            result = s1.compareTo(s2);
        }

        return result;
    }

}
