package freelook.freelook;

public class Derivative{

    final static double delta = 0.0001;

    public static Vector2D takePartialDerivativeA(Differentiand d, Vector2D in){
        double compA = (d.f(new Vector2D(in.x+delta, in.y)).x - d.f(in).x);
        double compB = (d.f(new Vector2D(in.x+delta, in.y)).y - d.f(in).y);
        if(compA != 0.0){
            compA /= delta;
        }
        if(compB != 0.0){
            compB /= delta;
        }

        return new Vector2D(compA, compB);
    }
    public static Vector2D takePartialDerivativeB(Differentiand d, Vector2D in){
        double compA = (d.f(new Vector2D(in.x, in.y+delta)).x - d.f(in).x);
        double compB = (d.f(new Vector2D(in.x, in.y+delta)).y - d.f(in).y);
        if(compA != 0){
            compA /= delta;
        }
        if(compB != 0){
            compB /= delta;
        }

        return new Vector2D(compA, compB);
    }
}
