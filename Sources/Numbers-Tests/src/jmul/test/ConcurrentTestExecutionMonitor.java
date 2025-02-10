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


import jmul.concurrent.threads.StoppableRunnable;
import jmul.concurrent.threads.ThreadHelper;

import jmul.time.TimestampHelper;


/**
 * An implementation of a test execution monitor.
 *
 * @author Kristian Kutin
 */
public class ConcurrentTestExecutionMonitor implements Runnable, StoppableRunnable, TestExecutionMonitor {

    /**
     * The maximum number of tests.
     */
    private final int maxTests;

    /**
     * The number of reported tests.
     */
    private volatile int tests;

    /**
     * The number of reported failures.
     */
    private volatile int failures;

    /**
     * A flag that indicates that this thread is still running or not.
     */
    private volatile boolean running;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param maxTests
     *        the maximum number of tests
     */
    public ConcurrentTestExecutionMonitor(int maxTests) {

        super();

        this.maxTests = maxTests;
        this.tests = 0;
        this.failures = 0;
        this.running = false;
    }

    /**
     * Returns the maximum number of tests.
     *
     * @return the maximum number of tests
     */
    public int maxTestCases() {

        return maxTests;
    }

    /**
     * Returns the number of reported successful tests.
     *
     * @return the number of reported successful tests
     */
    public int finishedTests() {

        synchronized (this) {

            return tests;
        }
    }

    /**
     * Returns the number of reported test failures.
     *
     * @return the number of reported test failures
     */
    public int failures() {

        synchronized (this) {

            return failures;
        }
    }

    /**
     * Report a successfully finished test for the statistics.
     */
    public void reportFinishedTest() {

        synchronized (this) {

            tests++;
        }
    }

    /**
     * Reports an amount of successfully finished tests for the statistics.
     *
     * @param amount
     *        an amount of successfully finished tests
     */
    public void reportFinishedTests(int amount) {

        synchronized (this) {

            tests = tests + amount;
        }
    }

    /**
     * Report a failed test for the statistics.
     */
    public void reportFailedTest() {

        synchronized (this) {

            failures++;
        }
    }

    /**
     * Reports the specified amount of failed tests for the statistics.
     *
     * @param amount
     *        an amount of failed finished tests
     */
    public void reportFailedTests(int amount) {

        synchronized (this) {

            failures = failures * amount;
        }
    }

    /**
     * Checks if all tests have been executed (i.e. failed tests + successful tests = max test cases).
     *
     * @return <code>true</code> if all tests have been executed, else <code>false</code>
     */
    public boolean done() {

        synchronized (this) {

            int sum = failures + tests;
            return (sum >= maxTests);
        }
    }

    /**
     * Returns a string representation for this monitor (i.e. a summary).
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        String summary =
            String.format("%s max %s / #%s / failed%s", TimestampHelper.timestamp(), maxTestCases(), finishedTests(),
                          failures());
        return summary;
    }

    /**
     * The main concurrent loop.
     */
    @Override
    public void run() {

        running = true;

        while (running) {

            System.out.println(toString());
            ThreadHelper.sleep(1000L);
        }
    }

    /**
     * Stops this monitor.
     */
    @Override
    public void stop() {

        running = false;
    }

}
