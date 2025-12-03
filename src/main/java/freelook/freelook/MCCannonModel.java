package freelook.freelook;

public class MCCannonModel implements Differentiand{

    final int cannonOrientation = 1;
    //final double surfaceDistance = 0.375;
    final double surfaceDistance = 0.375 - 0.01;
    //final double windChargeRange = 1.1;
    final double windChargeRange = 2.4;
    //final double totalWindChargePower = 1;
    final double totalWindChargePower = 1.22 * 2 / 3;
    final double explosionOffsetFromSurface = -0.25;
    final double initialTNTXOffset = 0;
    //final double initialTNTYOffset = 0.25;
    final double initialTNTYOffset = 0.2615;
    final double initialTNTZOffset = 0;
    final double initialArrowXOffset = 0;
    //final double initialArrowYOffset = 0.02472;
    final double initialArrowYOffset = -0.01528;
    final double initialArrowZOffset = 0;
    final double TNTDropoffAfterMoving = 0.04;

    public Vector2D f(final Vector2D in) {

        double relativeYaw = rotateAxis(in.x, cannonOrientation);
        double directionOfCollisionSurface = getDirectionOfCollisionSurface(relativeYaw);

        double XWindChargeImpact = directionOfCollisionSurface * surfaceDistance;
        double YWindChargeImpact = surfaceDistance * Math.abs(1.0/Math.cos(relativeYaw))*Math.tan(-in.y);
        double ZWindChargeImpact = directionOfCollisionSurface * surfaceDistance * Math.tan(relativeYaw);

        double Xf1 = XWindChargeImpact + initialTNTXOffset + directionOfCollisionSurface * explosionOffsetFromSurface;
        double Yf1 = YWindChargeImpact + initialTNTYOffset;
        double Zf1 = ZWindChargeImpact + initialTNTZOffset;

        double windChargeTntDistanceThingy = pythagoreanTheorem(Xf1, Yf1, Zf1);

        double effectiveWindChargePower = (1 - (windChargeTntDistanceThingy / windChargeRange)) * totalWindChargePower;

        double Xf2 = ((effectiveWindChargePower * Xf1) / windChargeTntDistanceThingy) + initialArrowXOffset;
        double Yf2 = ((effectiveWindChargePower * Yf1) / windChargeTntDistanceThingy) + initialArrowYOffset + TNTDropoffAfterMoving;
        double Zf2 = ((effectiveWindChargePower * Zf1) / windChargeTntDistanceThingy) + initialArrowZOffset;

//        System.out.println(Xf2);
//        System.out.println(Yf2);
//        System.out.println(Zf2);

        return new Vector3D(Xf2, Yf2, Zf2).cartesianToBullshit();
    }

    double pythagoreanTheorem(double a, double b, double c){
        return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2) + Math.pow(c, 2));
    }

    double rotateAxis(double val, int rot){
        return val + Math.PI/2 * rot;
    }

    double getDirectionOfCollisionSurface(double yaw){
        if (yaw < Math.PI/2){
            return 1;
        }
        return -1;
    }
}