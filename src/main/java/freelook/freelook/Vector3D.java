package freelook.freelook;

public class Vector3D {
    public double x;
    public double y;
    public double z;

    public Vector3D(){
        x = 0;
        y = 0;
        z = 0;
    }
    public Vector3D(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public String toString(){
        return "(" + x + ", " + y + ", " + z + ")";
    }

    public Vector2D cartesianToBullshit(){
        double r1 = Math.sqrt(x*x+z*z);
        double yaw = Math.atan2(-x, z);
        double pitch = Math.atan2(-y, r1);
        return new Vector2D(yaw, pitch);
    }
    public double getMagnitude(){
        return Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2)+Math.pow(z, 2));
    }
    public Vector3D add(Vector3D other){
        return new Vector3D(this.x + other.x, this.y + other.y, this.z + other.z);
    }
    public Vector3D subtract(Vector3D other){
        return new Vector3D(this.x - other.x, this.y - other.y, this.z - other.z);
    }
    public double dotProduct(Vector3D other){
        return this.x * other.x + this.y * other.y + this.z + other.z;
    }
}
