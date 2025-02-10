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


/**
 * An implementation of a test duration monitor.
 *
 * @author Kristian Kutin
 */
public class ConcurrentTestDurationMonitor implements TestDurationMonitor, Runnable, StoppableRunnable {

    /**
     * A flag that indicates that this thread is still running or not.
     */
    private volatile boolean running;

    /**
     * A start time.
     */
    private long startTime;

    /**
     * A stop time.
     */
    private long stopTime;

    /**
     * The default constructor.
     */
    public ConcurrentTestDurationMonitor() {

        super();

        this.running = false;
        this.startTime = 0L;
        this.stopTime = 0L;
    }

    /**
     * Returns the duration.
     *
     * @return a duration (in milliseconds)
     */
    @Override
    public long duration() {

        if ((startTime == 0L) || (stopTime == 0L)) {

            throw new RuntimeException("Missing the start time or stop time!");
        }

        return stopTime - startTime;
    }

    /**
     * The concurrent main loop.
     */
    @Override
    public void run() {

        running = true;
        startTime = System.currentTimeMillis();

        while (running) {

            ThreadHelper.sleep(1000L);
        }
    }

    /**
     * Stops this monitor.
     */
    @Override
    public void stop() {

        running = false;
        stopTime = System.currentTimeMillis();
    }

}
