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

package jmul.resource;


import jmul.concurrent.threads.StoppableRunnable;
import jmul.concurrent.threads.ThreadHelper;

import jmul.misc.ByteConverter;

import jmul.time.TimestampHelper;


/**
 * A component which runs as separate thred and monitors memory usage.
 *
 * @author Kristian Kutin
 */
public class ConcurrentMemoryMonitor implements Runnable, StoppableRunnable, MemoryMonitor {

    /**
     * A flag that indicates that this thread is still running or not.
     */
    private volatile boolean running;

    /**
     * A flag indicating to stop mass allocation of memory.
     */
    private volatile boolean stopAllocatingMemory;

    /**
     * A percentage value (current free memory) that limits mass memory allocation.
     */
    private volatile int limit;

    /**
     * A percentage value (free memory) that limits mass memory allocation.
     */
    private volatile int maxLimit;

    /**
     * The interval duration (milliseconds) for checking the memory allocation.
     */
    private final long sleepTime;

    /**
     * A flag indicating whether or not to show a summary after an interval.
     */
    private final boolean showSummary;

    /**
     * The default constructor.
     *
     * @param limit
     *        a percentage value (current free memory) that limits mass memory allocation
     * @param maxLimit
     *        a percentage value (free memory) that limits mass memory allocation
     * @param sleepTime
     *        a time interval to check memory usage
     * @param showSummary
     *        a flag indicating whether or not to show a summary
     */
    public ConcurrentMemoryMonitor(int limit, int maxLimit, long sleepTime, boolean showSummary) {

        super();

        if ((limit < 0) || (limit > 100)) {

            throw new IllegalArgumentException("The limit has an invalid perecentile value!");
        }

        if (sleepTime < 0) {

            throw new IllegalArgumentException("The sleep time cannot be negative!");
        }

        this.running = false;
        this.stopAllocatingMemory = false;
        this.limit = limit;
        this.maxLimit = maxLimit;
        this.sleepTime = sleepTime;
        this.showSummary = showSummary;
    }

    /**
     * Returns the maximum amount of memory that the JVM can use (see memory settings when
     * starting the java program).
     *
     * @return the maximum amount of memory in bytes
     */
    public long maxMemory() {

        return Runtime.getRuntime().maxMemory();
    }

    /**
     * Returns the current maximum amount of memory available within the JVM (current max memory &lt; max memory).
     *
     * @return the current maximum amount of memory in bytes
     */
    public long currentMaxMemory() {

        return Runtime.getRuntime().totalMemory();
    }

    /**
     * Returns the current amount of free memory available within the JVM (current free memory &lt; free memory).
     *
     * @return the current amount of free memory in bytes
     */
    public long currentFreeMemory() {

        return Runtime.getRuntime().freeMemory();
    }

    /**
     * Returns the amount of free memory available for the JVM.
     *
     * @return the amount of free memory in bytes
     */
    public long freeMemory() {

        return maxMemory() - usedMemory();
    }

    /**
     * Returns the amount of used memory within the JVM.
     *
     * @return the amount of used memory in bytes
     */
    public long usedMemory() {

        return currentMaxMemory() - currentFreeMemory();
    }

    /**
     * Prints a summary of the current memory state.
     */
    public void printMemoryState() {

        String timestamp = TimestampHelper.timestamp();

        long maxMemory = ByteConverter.convertTo(maxMemory(), ByteConverter.BYTES_TO_MEGABYTES);
        long currentMaxMemory = ByteConverter.convertTo(currentMaxMemory(), ByteConverter.BYTES_TO_MEGABYTES);
        long currentFreeMemory = ByteConverter.convertTo(currentFreeMemory(), ByteConverter.BYTES_TO_MEGABYTES);
        long usedMemory = ByteConverter.convertTo(usedMemory(), ByteConverter.BYTES_TO_MEGABYTES);
        long freeMemory = ByteConverter.convertTo(freeMemory(), ByteConverter.BYTES_TO_MEGABYTES);

        double result;

        result = ((double) currentFreeMemory) / ((double) currentMaxMemory) * 100D;
        int currentPercentageFree = (int) result;

        result = ((double) freeMemory) / ((double) maxMemory) * 100D;
        int percentageFree = (int) result;

        String[] summary = new String[8];

        summary[0] = String.format("%s", timestamp);
        summary[1] = String.format("\t         max memory (MB): %s", maxMemory);
        summary[2] = String.format("\t current max memory (MB): %s", currentMaxMemory);
        summary[3] = String.format("\t        free memory (MB): %s", freeMemory);
        summary[4] = String.format("\tcurrent free memory (MB): %s", currentFreeMemory);
        summary[5] = String.format("\t        used memory (MB): %s", usedMemory);
        summary[6] = String.format("\t         free memory (%%): %s (%s)", percentageFree, maxLimit);
        summary[7] = String.format("\t current free memory (%%): %s (%s)", currentPercentageFree, limit);

        for (String s : summary) {

            System.out.println(s);
        }
    }

    /**
     * Checks if the mass memory allocation should be stopped or not. Have memory intense components
     * regularly check this in order to not block too much memory.
     *
     * @return <code>true</code> if the mass memory allocation should be stopped, else <code>false</code>
     */
    public boolean stopMassMemoryAllocation() {

        double result;

        result = ((double) currentFreeMemory()) / ((double) currentMaxMemory()) * 100D;
        int currentPercentageFree = (int) result;

        result = ((double) freeMemory()) / ((double) maxMemory()) * 100D;
        int percentageFree = (int) result;

        return (limit > currentPercentageFree) && (maxLimit > percentageFree);
    }

    /**
     * Stops this monitor.
     */
    public void stop() {

        running = false;
    }

    /**
     * This method continuously checks the memory
     */
    @Override
    public void run() {

        running = true;

        while (running) {

            if (showSummary) {

                printMemoryState();
            }

            ThreadHelper.sleep(sleepTime);
        }
    }

}
