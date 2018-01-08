/*
 * Copyright (c) 2017-2018 by Artur Wasinger
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit.model;


import org.wahlzeit.utils.DesignPattern;

// null coordinate for location
@DesignPattern(patternName = "Null Object")
public class NoWhereCoordinate implements Coordinate{

    public NoWhereCoordinate() {
    }

    @Override
    public double getCartesianDistance(Coordinate other) {
        return Double.NaN;
    }

    @Override
    public double getSphericDistance(Coordinate other) {
        return Double.NaN;
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return null;
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        return null;
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public double getDistance(Coordinate other) {
        return Double.NaN;
    }

    public boolean isEqual(Coordinate other) {
        return false;
    }

    public String toString() {
        return "NoWhereCoordinate in NoWhere";
    }
}
