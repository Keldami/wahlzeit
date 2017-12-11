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


/**
 * Class is an abstract class for generic Coordinate implementation
 */
public abstract class AbstractCoordinate implements Coordinate{

    public AbstractCoordinate() {
    }

    @Override
    public abstract boolean isEqual(Coordinate other);

    @Override
    public abstract CartesianCoordinate asCartesianCoordinate();

    @Override
    public abstract double getCartesianDistance(Coordinate other);

    @Override
    public abstract double getSphericDistance(Coordinate other);

    @Override
    public abstract SphericCoordinate asSphericCoordinate();

    /*
    * Get's the distance to another coordinate from the current locations coordinate
    * @methodtype get
    */
    public double getDistance(Coordinate other) {

        if(other instanceof CartesianCoordinate){

            return this.getCartesianDistance(other);

        } else if(other instanceof SphericCoordinate){

            return this.getSphericDistance(other);

        } else {
            //for NoWhereCoordinate or undefined coordinate
            return Double.NaN;

        }
    }

}
