package org.wahlzeit.model;

public class SphericCoordinate implements Coordinate {


    private double latitude;
    private double longitude;
    private double radius;
    private CartesianCoordinate asCartesian = null;

    public SphericCoordinate(double latitude, double longitude, double radius){
        this.setLatitude(latitude);
        this.setLongitude(longitude);
        this.setRadius(radius);
    }

    public SphericCoordinate() {
        this.setLatitude(0);
        this.setLongitude(0);
        this.setRadius(0);
    }

    public double getLatitude(){
        return latitude;
    }

    public double getLongitude(){
        return longitude;
    }

    public double getRadius() {
        return radius;
    }


    //TODO setter latitude and longitude are degrees, radius should not be less than 0
    public void setLatitude(double latitude){
        if(Double.isNaN(latitude) || Double.isInfinite(latitude)){
            throw new IllegalArgumentException("Please set a value for latitude not Double.NaN or Double.INFINITY");
        }
        this.latitude = latitude;

        if(asCartesian != null) {
            updateCartesianCoordinate(asCartesian);
        }
    }

    public void setLongitude(double longitude) {
        if(Double.isNaN(longitude) || Double.isInfinite(longitude)){
            throw new IllegalArgumentException("Please set a value for longitude not Double.NaN or Double.INFINITY");
        }
        this.longitude = longitude;

        if(asCartesian != null) {
            updateCartesianCoordinate(asCartesian);
        }
    }

    public void setRadius(double radius) {
        if(Double.isNaN(radius) || Double.isInfinite(radius)){
            throw new IllegalArgumentException("Please set a value for radius not Double.NaN or Double.INFINITY");
        }
        this.radius = radius;

        if(asCartesian != null) {
            updateCartesianCoordinate(asCartesian);
        }
    }


    //TODO implement true getSphericDistance
    @Override
    public double getSphericDistance(Coordinate other) {
        return this.asCartesianCoordinate().getCartesianDistance(other.asCartesianCoordinate());
    }

    //TODO
    @Override
    public double getCartesianDistance(Coordinate other) {
        double diffx = this.asCartesianCoordinate().getX() - other.asCartesianCoordinate().getX();
        double diffy = this.asCartesianCoordinate().getY() - other.asCartesianCoordinate().getY();
        double diffz = this.asCartesianCoordinate().getZ() - other.asCartesianCoordinate().getZ();

        double ret = Math.sqrt(this.asCartesianCoordinate().square(diffx) +
                               this.asCartesianCoordinate().square(diffy) +
                               this.asCartesianCoordinate().square(diffz));

        if (Double.isInfinite(ret)) {
            throw new IllegalStateException("The distance is infinity. Illegal state.");
        }
        return ret;
    }

    @Override
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


    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        if(asCartesian == null) {
            asCartesian = new CartesianCoordinate();
            updateCartesianCoordinate(asCartesian);
        }

        return asCartesian;
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        return this;
    }

    @Override
    public boolean isEqual(Coordinate other) {

        if(!(other instanceof SphericCoordinate)) {
            return false;
        }

        if( Double.compare(this.getLatitude(),  other.asSphericCoordinate().getLatitude()) == 0 &&
                Double.compare(this.getLongitude(), other.asSphericCoordinate().getLongitude()) == 0 &&
                Double.compare(this.getRadius(), other.asSphericCoordinate().getRadius()) == 0 ) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof SphericCoordinate && this.isEqual( (Coordinate) obj )){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int ret = 37;
        ret = 41 * ret + Double.valueOf(this.getLatitude()).hashCode();
        ret = 41 * ret + Double.valueOf(this.getLongitude()).hashCode();
        ret = 41 * ret + Double.valueOf(this.getRadius()).hashCode();

        return ret;
    }


    /*
    * Current Coordinate to String
    */
    public String toString() {
        return "SphericCoordinate: {latitude=" + this.getLatitude() + " longitude=" +
                this.getLongitude() + " radius=" + this.getRadius() + " }";
    }


    //to update cartesian coordinates
//    x = radius * cos(latitude) * sin(longitude)
//    y = radius * sin(latitude)
//    z = -radius * cos(latitude) * cos(longitude)
    private void updateCartesianCoordinate(CartesianCoordinate asCartesian) {
        if(asCartesian != null) {

            double x = this.getRadius() * Math.cos(Math.toRadians(this.getLatitude()))
                    * Math.sin(Math.toRadians(this.getLongitude()));

            double y = this.getRadius() * Math.sin(Math.toRadians(this.getLatitude()));

            double z = -this.getRadius() * Math.cos(Math.toRadians(this.getLatitude()))
                    * Math.cos(Math.toRadians(this.getLongitude()));

            asCartesian.setX(x);
            asCartesian.setY(y);
            asCartesian.setZ(z);
        }
    }
}
