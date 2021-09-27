package multiteam.multicorelibport;


import com.mojang.math.Vector3f;

public class MathF { //Why MathF? Because in unity, there was a class with Mathematical functions, and i grown quiet fond of this name. In the honor of my C# past, you shall now be named MathF!

    /**
     * Calculates a number in between two scales.
     * Useful for minecraft's float values where it uses a scale between 0-1 instead of 0-16 for blocks and such.
     * To a quicker acces to a solution for the problem mentioned above, see BlockToFloatScale()
    **/
    public static float rescaleValues(float minFrom, float maxFrom, float minTo, float maxTo, float valueToScale){
        float OldRange = (maxFrom - minFrom);
        float NewRange = (maxTo - minTo);

        return (((valueToScale - minFrom) * NewRange) / OldRange) + minTo;
    }

    /**
     * Used to calculate quickly from 0-16 to 0-1 with less values
     **/
    public static float BlockToFloatScale(float value){
        return rescaleValues(0.0f, 16.0f, 0.0f, 1.0f, value);
    }

    /**
     * Does the same as above except with a Vector3f
     **/
    public static Vector3f BlockToFloatScaleVector3f(float x, float y, float z){
        return new Vector3f(BlockToFloatScale(x),BlockToFloatScale(y),BlockToFloatScale(z));
    }

}
