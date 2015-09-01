/*
 * Copyright (C) 2015 Chris Ryan
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
 */
package net.c2technology.roguezombie.world;

import java.util.Random;

/**
 * Utility class for getting random numbers.
 *
 * @author Chris Ryan
 */
public class RandomNumber {

    /**
     * Retrieves an inclusively random number between {@code min} and
     * {@code max}. If the {@code min} or {@code max} values are outside the
     * acceptable range, the closes number within the acceptable range will be
     * used. If {@code min} > {@code max}, {@code max} will be returned.
     *
     * @param min an integer greater than or equal to 0 and less than
     * {@code Integer.MAX_VALUE}.
     * @param max an integer greater than or equal to 0 and less than
     * {@code Integer.MAX_VALUE}.
     * @return
     */
    public static int between(int min, int max) {
        if (min == Integer.MAX_VALUE) {
            min = Integer.MAX_VALUE - 1;
        }
        if (max == Integer.MAX_VALUE) {
            max = Integer.MAX_VALUE - 1;
        }
        if (min > max) {
            return max;
        }
        return new Random().nextInt((max - min) + 1) + min;
    }
}
