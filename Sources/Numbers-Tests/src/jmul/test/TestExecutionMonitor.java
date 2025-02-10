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

package jmul.test;


/**
 * This interface describes an entity that measures test completions.
 *
 * @author Kristian Kutin
 */
public interface TestExecutionMonitor {

    /**
     * Returns the maximum number of tests.
     *
     * @return the maximum number of tests
     */
    int maxTestCases();

    /**
     * Returns the number of reported successful tests.
     *
     * @return the number of reported successful tests
     */
    int finishedTests();

    /**
     * Returns the number of reported test failures.
     *
     * @return the number of reported test failures
     */
    int failures();

    /**
     * Report a successfully finished test for the statistics.
     */
    void reportFinishedTest();

    /**
     * Reports an amount of successfully finished tests for the statistics.
     *
     * @param amount
     *        an amount of successfully finished tests
     */
    void reportFinishedTests(int amount);

    /**
     * Report a failed test for the statistics.
     */
    void reportFailedTest();

    /**
     * Reports the specified amount of failed tests for the statistics.
     *
     * @param amount
     *        an amount of failed finished tests
     */
    void reportFailedTests(int amount);

    /**
     * Checks if all tests have been executed (i.e. failed tests + successful tests = max test cases).
     *
     * @return <code>true</code> if all tests have been executed, else <code>false</code>
     */
    boolean done();

}
