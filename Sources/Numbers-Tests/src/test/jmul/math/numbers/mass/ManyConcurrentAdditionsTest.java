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


import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import jmul.concurrent.threads.StoppableRunnable;
import jmul.concurrent.threads.ThreadHelper;
import jmul.concurrent.threads.ThreadStartHelper;

import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;

import jmul.resource.ConcurrentMemoryMonitor;
import jmul.resource.MemoryMonitor;

import jmul.test.ConcurrentTestDurationMonitor;
import jmul.test.ConcurrentTestExecutionMonitor;
import jmul.test.TestExecutionMonitor;
import jmul.test.classification.ManualTest;

import jmul.time.TimestampHelper;

import org.junit.Ignore;


/**
 * An implementation of a long running tests with many operations.
 *
 * @author Kristian Kutin
 */
@Ignore
@ManualTest
public class ManyConcurrentAdditionsTest {

    /**
     * The main method.
     *
     * @param args
     *        command line arguments are ignored
     */
    public static void main(String... args) {

        Queue<TestCaseParameters> queue = new ConcurrentLinkedQueue<>();
        ConcurrentMemoryMonitor monitor1 = new ConcurrentMemoryMonitor(30, 90, 1000L, true);
        TestDesigner testDesigner = new TestDesigner(queue, monitor1);
        ConcurrentTestExecutionMonitor monitor2 = new ConcurrentTestExecutionMonitor(testDesigner.totalTestCases());
        TestExecutor testExecutor1 = new TestExecutor(queue, monitor2);
        TestExecutor testExecutor2 = new TestExecutor(queue, monitor2);
        ConcurrentTestDurationMonitor monitor3 = new ConcurrentTestDurationMonitor();
        QueueMonitor monitor4 = new QueueMonitor(queue);

        Thread thread1 = ThreadStartHelper.startThread(monitor1);
        Thread thread2 = ThreadStartHelper.startThread(testDesigner);
        Thread thread3 = ThreadStartHelper.startThread(testExecutor1);
        Thread thread4 = ThreadStartHelper.startThread(testExecutor2);
        Thread thread5 = ThreadStartHelper.startThread(monitor2);
        Thread thread6 = ThreadStartHelper.startThread(monitor3);
        Thread thread7 = ThreadStartHelper.startThread(monitor4);

        boolean running = true;
        while (running) {

            running = !monitor2.done();

            ThreadHelper.sleep(1000L);
        }

        testDesigner.stop();
        testExecutor1.stop();
        testExecutor2.stop();
        monitor1.stop();
        monitor2.stop();
        monitor3.stop();
        monitor4.stop();

        try {
            thread1.join();
        } catch (InterruptedException e) {
        }
        try {
            thread2.join();
        } catch (InterruptedException e) {
        }
        try {
            thread3.join();
        } catch (InterruptedException e) {
        }
        try {
            thread4.join();
        } catch (InterruptedException e) {
        }
        try {
            thread6.join();
        } catch (InterruptedException e) {
        }
        try {
            thread7.join();
        } catch (InterruptedException e) {
        }

        System.out.println(String.format("%d ms", monitor3.duration()));
    }

}


/**
 * A data structure with a test configuration.
 *
 * @author Kristian Kutin
 */
class TestCaseParameters {

    /**
     * A number base.
     */
    public final int base;

    /**
     * A string representation of an operand.
     */
    public final String operand1Representation;

    /**
     * A string representation of an operand.
     */
    public final String operand2Representation;

    /**
     * A string representation of the expected result.
     */
    public final String expectedResultRepresentation;

    /**
     * Creates a new instance accordign to the specified parameters.
     *
     * @param base
     *        a number base
     * @param operand1Representation
     *        a string representation of an operand
     * @param operand2Representation
     *        a string representation of an operand.
     * @param expectedResultRepresentation
     *        a string representation of the expected result
     */
    public TestCaseParameters(int base, String operand1Representation, String operand2Representation,
                              String expectedResultRepresentation) {

        super();

        this.base = base;
        this.operand1Representation = checkParameter(operand1Representation);
        this.operand2Representation = checkParameter(operand2Representation);
        this.expectedResultRepresentation = checkParameter(expectedResultRepresentation);
    }

    /**
     * Checks the specified parameter.
     *
     * @param parameter
     *        a string parameter
     *
     * @return the specified parameter
     */
    private static String checkParameter(String parameter) {

        if (parameter == null) {

            throw new IllegalArgumentException("No parameter (null) was specified!");
        }

        return parameter;
    }

    /**
     * Returns the number base.
     *
     * @return a number base
     */
    public int base() {

        return base;
    }

    /**
     *
     * @return
     */
    public Number operand1() {

        return createNumber(base, operand1Representation);
    }

    public Number operand2() {

        return createNumber(base, operand2Representation);
    }

    public Number expectedResult() {

        return createNumber(base, expectedResultRepresentation);
    }

}


/**
 * An implementation of a test designer.
 *
 * @author Kristian Kutin
 */
class TestDesigner implements Runnable, StoppableRunnable {

    /**
     * A flag that indicates that this thread is still running or not.
     */
    private volatile boolean running;

    /**
     * This queue contains all tests.
     */
    private Queue<TestCaseParameters> testQueue;

    /**
     * A reference to a memory monitor.
     */
    private MemoryMonitor monitor;

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
     * Creates a new instance according to the specified parameters.
     *
     * @param testQueue
     *        a reference to a test queue
     * @param monitor
     *        a reference to a memory monitor
     */
    public TestDesigner(Queue<TestCaseParameters> testQueue, MemoryMonitor monitor) {

        super();

        this.running = false;
        this.testQueue = testQueue;
        this.monitor = monitor;
        this.minA = -10000;
        this.maxA = 10000;
        this.minB = -10000;
        this.maxB = 10000;
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

            createTestCases();
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
     * Create the test cases and put these into a queue.
     */
    private void createTestCases() {

        for (int a = minA; a <= maxA; a++) {
            for (int b = minB; b <= maxB; b++) {

                sleepUntilAvailableResources();

                if (!running) {

                    return;
                }

                int expectedResult = a + b;
                TestCaseParameters parameters = new TestCaseParameters(10, "" + a, "" + b, "" + expectedResult);
                testQueue.add(parameters);
            }
        }

        stop();
    }

    /**
     * Sleeps until enough free resources are available.
     */
    private void sleepUntilAvailableResources() {

        while (running && monitor.stopMassMemoryAllocation()) {

            ThreadHelper.sleep(1000L);
        }
    }

}


/**
 * An implementation of a test executor.
 *
 * @author Kristian Kutin
 */
class TestExecutor implements Runnable, StoppableRunnable {

    /**
     * A flag that indicates that this thread is still running or not.
     */
    private volatile boolean running;

    /**
     * This queue contains all tests.
     */
    private Queue<TestCaseParameters> testQueue;

    /**
     * An entity that monitors the test execution.
     */
    private TestExecutionMonitor monitor;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param testQueue
     *        a reference to a test queue
     * @param monitor
     *        a reference to a test monitor
     */
    public TestExecutor(Queue<TestCaseParameters> testQueue, TestExecutionMonitor monitor) {

        super();

        this.running = false;
        this.testQueue = testQueue;
        this.monitor = monitor;
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

        TestCaseParameters parameters = testQueue.poll();

        if (parameters == null) {

            return;
        }

        try {

            Number operand1 = parameters.operand1();
            Number operand2 = parameters.operand2();
            Number expectedResult = parameters.expectedResult();
            Number actualResult = operand1.add(operand2);

            String expectedResultString = expectedResult.toString();
            String actualResultString = actualResult.toString();

            if (expectedResultString.equals(actualResultString)) {

                monitor.reportFinishedTest();

            } else {

                monitor.reportFailedTest();
            }

        } catch (Exception e) {

            monitor.reportFailedTest();
        }
    }

}


/**
 * An implmentation of an entity which monitors a queue.
 *
 * @author Kristian Kutin
 */
class QueueMonitor implements Runnable, StoppableRunnable {

    /**
     * A flag that indicates that this thread is still running or not.
     */
    private volatile boolean running;

    /**
     * This queue contains all tests.
     */
    private Queue<TestCaseParameters> testQueue;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param testQueue
     *        a rerefence to a queue
     */
    public QueueMonitor(Queue<TestCaseParameters> testQueue) {

        super();

        this.running = false;
        this.testQueue = testQueue;
    }

    /**
     * The concurrent main loop.
     */
    @Override
    public void run() {

        running = true;

        while (running) {

            String summary =
                String.format("%s The test queue: %d element(s)", TimestampHelper.timestamp(), testQueue.size());

            if (testQueue.isEmpty()) {

                System.gc();
            }

            System.out.println(summary);

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
