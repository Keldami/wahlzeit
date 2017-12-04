package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;
import org.wahlzeit.utils.CoordinateUtil;

@Subclass
public class SphericCoordinate extends AbstractCoordinate {


    private double latitude;
    private double longitude;
    private double radius;

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


    public void setLatitude(double latitude){
        CoordinateUtil.assertSphericParameter(latitude, 180, 0, "latitude");
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        CoordinateUtil.assertSphericParameter(longitude, 180, -180, "longitude");
        this.longitude = longitude;

    }

    public void setRadius(double radius) {
        CoordinateUtil.assertSphericParameter(radius, Double.POSITIVE_INFINITY, 0, "radius");
        this.radius = radius;

    }


    // \,\zeta =\arccos \left(\sin(\phi _{A})\cdot \sin(\phi _{B})+\cos(\phi _{A})\cdot \cos(\phi _{B})\cdot \cos(\lambda _{B}-\lambda _{A})\right)
    // ( arccos(sin(latitude(a)) * sin(latitude(b) + cos(latitude(a)) * cos (latitude(b)) * cos(longitude(a) -longitude(b)) ) * radius
    @Override
    public double getSphericDistance(Coordinate other) {

        CoordinateUtil.assertAllSphericParameters(
                other.asSphericCoordinate().getLatitude(),
                other.asSphericCoordinate().getLongitude(),
                other.asSphericCoordinate().getRadius());

        double distance;
        double sinlata = Math.toRadians(Math.sin(this.getLatitude()));
        double sinlatb = Math.toRadians(Math.sin(other.asSphericCoordinate().getLatitude()));
        double coslata = Math.toRadians(Math.cos(this.getLatitude()));
        double coslatb = Math.toRadians(Math.cos(other.asSphericCoordinate().getLatitude()));
        double coslongalongb = Math.toRadians(Math.cos(this.getLongitude()) - Math.cos(other.asSphericCoordinate().getLongitude()));
        distance = radius * Math.acos(sinlata * sinlatb + coslata * coslatb * coslongalongb);

        return distance;
    }

    @Override
    public double getCartesianDistance(Coordinate other) {
        return this.asCartesianCoordinate().getCartesianDistance(other.asCartesianCoordinate());
    }


    //to update cartesian coordinates
//    x = radius * cos(latitude) * sin(longitude)
//    y = radius * sin(latitude)
//    z = -radius * cos(latitude) * cos(longitude)
    @Override
    public CartesianCoordinate asCartesianCoordinate() {

        CartesianCoordinate asCartesian = new CartesianCoordinate();

        double x = this.getRadius() * Math.cos(Math.toRadians(this.getLatitude()))
                * Math.sin(Math.toRadians(this.getLongitude()));

        double y = this.getRadius() * Math.sin(Math.toRadians(this.getLatitude()));

        double z = -this.getRadius() * Math.cos(Math.toRadians(this.getLatitude()))
                * Math.cos(Math.toRadians(this.getLongitude()));

        CoordinateUtil.assertAllCartesianParameters(x, y, z);

        asCartesian.setX(x);
        asCartesian.setY(y);
        asCartesian.setZ(z);


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

}
