package freelook.freelook;

public class MCCannonModelDebugMain {
    public static void main(String[] args) {

        // Create an MCCannonModel object
        MCCannonModel cm = new MCCannonModel();

        // mccmRanges(-1.2, 1.2, -1.1, 1.1, .2);  // Works!
        mccmMainFunctionTestRad(-Math.PI/2, 0);
    }

    public static void mccmMainFunctionTestRad(double yawRad, double pitchRad) {

        // Create an MCCannonModel object
        MCCannonModel cm = new MCCannonModel();

        Vector2D inVecRad = new Vector2D(yawRad, pitchRad);
        Vector2D outVecDeg = cm.f(inVecRad);
        System.out.println(inVecRad + ": " + outVecDeg);

    }


    public static void mccmRanges(double min_x, double max_x, double min_y, double max_y, double step_size) {
        MCCannonModel cm = new MCCannonModel();

        for (double x = min_x; x <= max_x; x += step_size) {
            for (double y = min_y; y <= max_y; y += step_size) {
                Vector2D inDeg = new Vector2D(x, y);
                Vector2D outDeg = cm.f(inDeg);
                System.out.println(inDeg + ": " + outDeg);
            }
        }

    }

}
