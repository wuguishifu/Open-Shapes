package com.bramerlabs.support;

public class Vector3f {

    // x, y, z position
    public float x, y, z;

    /**
     * default constructor
     * @param x - x position
     * @param y - y position
     * @param z - z position
     */
    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * constructor from a float array
     * @param position - a float array containing position in form [x, y, z].
     * method will automatically set the position based off of available variables.
     * no minimum or maximum supplied variables, if more than 3 position will be first 3.
     */
    public Vector3f(float[] position) {
        x = position.length > 0 ? position[0] : 0;
        y = position.length > 1 ? position[1] : 0;
        z = position.length > 2 ? position[2] : 0;
    }

    /**
     * constructor for copying a vector
     * @param v - the vector to be copied
     */
    public Vector3f(Vector3f v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
    }

    /**
     * adds two vectors
     * @param v - the vector to be added
     * @return - this vector
     */
    public Vector3f add(Vector3f v) {
        this.x += v.x;
        this.y += v.y;
        this.z += v.z;
        return this;
    }

    /**
     * adds a float array to a vector
     * @param val - a float array containing values to be added
     * method will automatically add based off of the amount of values in the array
     * @return - this vector
     */
    public Vector3f add(float[] val) {
        this.x += val.length > 0 ? val[0] : 0;
        this.y += val.length > 1 ? val[1] : 0;
        this.z += val.length > 2 ? val[2] : 0;
        return this;
    }

    /**
     * normalizes a vector to a length of 1
     * @return - this vector
     */
    public Vector3f normalize() {
        float length = (float)Math.sqrt(x * x + y * y + z * z);
        this.x /= length;
        this.y /= length;
        this.z /= length;
        return this;
    }

    /**
     * normalizes a vector to a certain length
     * @param r - the new length of the vector after normalization
     * @return - this vector
     */
    public Vector3f normalize(float r) {
        float length = (float)Math.sqrt(x * x + y * y + z * z);
        float v = length/r;
        this.x /= v;
        this.y /= v;
        this.z /= z;
        return this;
    }
}