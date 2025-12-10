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

package jmul.math.concurrent;


import jmul.concurrent.threads.ThreadHelper;


/**
 * A pool which handles all concurrent calculations.
 *
 * @param <T>
 *        the input type for concurrent calculations
 * @param <S>
 *        the result type for concurrent calculations
 *
 * @author Kristian Kutin
 */
public abstract class CalculationPool<T, S> {

    /**
     * Starts the concurrent calculations and waits for the results.
     *
     * @param inputs
     *        alle required inputs
     *
     * @return all results
     */
    public S[] calculateResultsAndWaitForThreads(T... inputs) {

        ConcurrentCalculation<T, S>[] calculations = createConcurrentCalculations(inputs);
        Thread[] threads = startCalculations(calculations);
        waitForThreads(threads);
        S[] results = rewrapResults(calculations);

        return results;
    }

    /**
     * Creates a new empty array of the result type.
     *
     * @param length
     *        the array size
     *
     * @return a new empty array
     */
    protected abstract S[] newArray(int length);

    /**
     * Creates concurrent calculations (i.e. runnables).
     *
     * @param inputs
     *        the inputs for concurrent calculations
     *
     * @return all concurrent calculations (i.e. runnables)
     */
    protected abstract ConcurrentCalculation<T, S>[] createConcurrentCalculations(T... inputs);

    /**
     * Starts the calculations and returns handles to all threads.
     *
     * @param calculations
     *        all calculations
     *
     * @return threads for all calculations
     */
    protected Thread[] startCalculations(ConcurrentCalculation<T, S>... calculations) {

        int length = calculations.length;
        Thread[] threads = new Thread[length];

        for (int index = 0; index < length; index++) {

            ConcurrentCalculation<T, S> runner = calculations[index];
            Thread thread = new Thread(runner);
            threads[index] = thread;
            thread.start();
        }

        ThreadHelper.sleep(10L);

        return threads;
    }

    /**
     * Waits for all specified threads to finish. If exceptions occur then the first exception is raised.
     *
     * @param threads
     *        all threads
     */
    protected void waitForThreads(Thread... threads) {

        Throwable firtInterruption = null;

        for (Thread thread : threads) {

            try {

                thread.join();

            } catch (InterruptedException e) {

                if (firtInterruption == null) {

                    firtInterruption = e;
                }
            }
        }

        if (firtInterruption != null) {

            throw new ConcurrentComputationException(firtInterruption);
        }
    }

    /**
     * Rewraps the results from the concurrent calculations into a seperate array.
     *
     * @param calculations
     *        all concurrent calculations
     *
     * @return all results
     */
    protected S[] rewrapResults(ConcurrentCalculation<T, S>... calculations) {

        int length = calculations.length;

        S[] results = newArray(length);

        for (int index = 0; index < length; index++) {

            ConcurrentCalculation<T, S> calculation = calculations[index];

            if (calculation.hasFailed()) {

                throw new NoConcurrentResultException(calculation.exception());
            }

            S result = calculation.result();

            results[index] = result;
        }

        return results;
    }

}
