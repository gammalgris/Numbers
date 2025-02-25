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

package test.jmul.math.numbers.mass;


import jmul.concurrent.threads.StoppableRunnable;
import jmul.concurrent.threads.ThreadStartHelper;

import jmul.math.numbers.NumberHelper;

import jmul.resource.ConcurrentMemoryMonitor;

import jmul.test.ConcurrentTestDurationMonitor;
import jmul.test.ConcurrentTestExecutionMonitor;
import jmul.test.TestExecutionMonitor;
import jmul.test.classification.ManualTest;

import org.junit.Ignore;


/**
 * An implementation of a long running tests with many operations.
 *
 * @author Kristian Kutin
 */
@Ignore
@ManualTest
public class ManySequentialAdditionsTest {

    /**
     * The main method.
     *
     * @param args
     *        command line arguments are currently ignored
     */
    public static void main(String[] args) {

        ConcurrentMemoryMonitor monitor1 = new ConcurrentMemoryMonitor(30, 90, 1000L, true);
        SequentialTestExecutor testExecutor = new SequentialTestExecutor();
        ConcurrentTestExecutionMonitor monitor2 = new ConcurrentTestExecutionMonitor(testExecutor.totalTestCases());
        testExecutor.setTextExecutionMonitor(monitor2);
        ConcurrentTestDurationMonitor monitor3 = new ConcurrentTestDurationMonitor();

        Thread thread1 = ThreadStartHelper.startThread(monitor1);
        Thread thread2 = ThreadStartHelper.startThread(monitor2);
        Thread thread3 = ThreadStartHelper.startThread(testExecutor);
        Thread thread4 = ThreadStartHelper.startThread(monitor3);

        try {
            thread3.join();
        } catch (InterruptedException e) {
        }

        monitor1.stop();
        monitor2.stop();
        monitor3.stop();

        try {
            thread1.join();
        } catch (InterruptedException e) {
        }

        try {
            thread2.join();
        } catch (InterruptedException e) {
        }

        try {
            thread4.join();
        } catch (InterruptedException e) {
        }

        System.out.println(String.format("%d ms", monitor3.duration()));
    }

}


/**
 * A test executor.
 *
 * @author Kristian Kutin
 */
class SequentialTestExecutor implements Runnable, StoppableRunnable {

    /**
     * A flag that indicates that this thread is still running or not.
     */
    private volatile boolean running;

    /**
     * A monitor that tracks test execution.
     */
    private TestExecutionMonitor monitor;

    /**
     * Test parameters.
     */
    private final int minA;

    /**
     * Test parameters.
     */
    private final int minB;

    /**
     * Test parameters.
     */
    private final int maxA;

    /**
     * Test parameters.
     */
    private final int maxB;

    /**
     * The default constructor.
     */
    public SequentialTestExecutor() {

        super();

        this.running = false;
        this.monitor = null;
        this.minA = -10000;
        this.maxA = 10000;
        this.minB = -10000;
        this.maxB = 10000;
    }

    /**
     * Sets a test execution monitor.
     *
     * @param monitor
     * a test execution monitor
     */
    public void setTextExecutionMonitor(TestExecutionMonitor monitor) {

        this.monitor = monitor;
    }

    /**
     * Returns the total number of test cases according to the test parameters.
     *
     * @return total number of test cases
     */
    public int totalTestCases() {

        int sum = (maxA - minA) * (maxB - minB);
        return sum;
    }

    /**
     * The concurrent main loop.
     */
    @Override
    public void run() {

        running = true;

        while (running) {

            executeTests();
        }
    }

    /**
     * Stops this thread.
     */
    @Override
    public void stop() {

        running = false;
    }

    /**
     * The actual test execution.
     */
    private void executeTests() {

        for (int a = minA; a <= maxA; a++) {
            for (int b = minB; b <= maxB; b++) {

                try {

                    int expectedResult = a + b;
                    String expectedResultString = "" + expectedResult;

                    jmul.math.numbers.Number n1 = NumberHelper.createNumber(10, "" + a);
                    jmul.math.numbers.Number n2 = NumberHelper.createNumber(10, "" + b);
                    jmul.math.numbers.Number actualResult = n1.add(n2);
                    String actualResultString = actualResult.toString();

                    if (monitor != null) {

                        if (expectedResultString.equals(actualResultString)) {

                            monitor.reportFinishedTest();

                        } else {

                            monitor.reportFailedTest();
                        }
                    }

                } catch (Exception e) {

                    if (monitor != null) {

                        monitor.reportFailedTest();
                    }
                }
            }
        }

        stop();
    }

}
