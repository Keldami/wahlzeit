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

/**
 * Class is an abstract class for generic Coordinate implementation
 * Prototype pattern used for easy conversion between coordinate classes
 */
@DesignPattern(patternName = "Prototype", participants = {"SpericCoordinate", "CartesianCoordinate",
                "NoWhereCoordinate"})
public abstract class AbstractCoordinate implements Coordinate{

    public AbstractCoordinate() {
    }

    @Override
    public abstract boolean isEqual(Coordinate other);

    @Override
    public abstract CartesianCoordinate asCartesianCoordinate() throws Exception;

    @Override
    public abstract double getCartesianDistance(Coordinate other) throws Exception;

    @Override
    public abstract double getSphericDistance(Coordinate other) throws Exception;

    @Override
    public abstract SphericCoordinate asSphericCoordinate() throws Exception;

    public double getDistance(Coordinate other) throws Exception {

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
