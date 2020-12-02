package com.bramerlabs.support;

import java.util.Objects;

public class Vector3f {

    // x, y, z position
    public float x, y, z;

    /**
     * default constructor
     * position will be set to (0, 0, 0)
     */
    public Vector3f() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    /**
     * constructor for 3 position values
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
     * subtracts two vectors
     * @param v - the vector to be subtracted
     * @return - this vector
     */
    public Vector3f subtract(Vector3f v) {
        this.x -= v.x;
        this.y -= v.y;
        this.z -= v.z;
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
     * takes the cross product of two vectors
     * @param v - vector 1
     * @param u - vector 2
     * @return - the cross product of v and u (v x u)
     */
    public static Vector3f cross(Vector3f v, Vector3f u) {
        float x = v.y * u.z - v.z * u.y;
        float y = v.z * u.x - v.x * u.z;
        float z = v.x * u.y - v.y * u.x;
        return new Vector3f(x, y, z);
    }

    /**
     * takes the scalar product of two vectors
     * @param v - vector 1
     * @param u - vector 2
     * @return - the dot product of v and u (v dot u)
     */
    public static float dot(Vector3f v, Vector3f u) {
        return v.x * u.x + v.y * u.y + v.z * u.z;
    }

    /**
     * scales a vector
     * @param n - the scale factor
     * @return - this vector
     */
    public Vector3f scale(float n) {
        this.x *= n;
        this.y *= n;
        this.z *= n;
        return this;
    }

    /**
     * gets the distance between two vectors
     * @param v - vector 1
     * @param u - vector 2
     * @return - the distance between the two
     */
    public static float distance(Vector3f v, Vector3f u) {
        float x = v.x - u.x;
        float y = v.y - u.y;
        float z = v.z - u.z;
        return (float)Math.sqrt(x * x + y * y + z * z);
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
        this.z /= v;
        return this;
    }

    /**
     * inverts the x value
     */
    public Vector3f invertX() {
        this.x = -this.x;
        return this;
    }

    /**
     * inverts the y value
     */
    public Vector3f invertY() {
        this.y = -this.y;
        return this;
    }

    /**
     * inverts the z value
     */
    public Vector3f invertZ() {
        this.z = -this.z;
        return this;
    }

    /**
     * inverts the vector
     */
    public Vector3f invert() {
        x = -x;
        y = -y;
        z = -z;
        return this;
    }

    /**
     * checks if two vectors are approximately equal
     * @param o - other vector
     * @param e - epsilon value
     * @return - true if the vectors are approximately equal
     */
    public boolean equals(Object o, float e) {
        if (this == o) return true;
        if (!(o instanceof Vector3f)) return false;
        Vector3f u = (Vector3f) o;
        return
                this.x - u.x < e &&
                this.y - u.y < e &&
                this.z - u.z < e;
    }

    /**
     * checks if two vectors are exactly equal
     * @param o - the other object
     * @return - true if the vectors are exactly equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector3f)) return false;
        Vector3f vector3f = (Vector3f) o;
        return Float.compare(vector3f.x, x) == 0 &&
                Float.compare(vector3f.y, y) == 0 &&
                Float.compare(vector3f.z, z) == 0;
    }

    /**
     * generates a hashcode for this vector
     * @return the hashcode integer
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    /**
     * converts this Vector3f into an array of floats
     * @return - float array
     */
    public float[] toFloats() {
        return new float[]{this.x, this.y, this.z};
    }

    /**
     * generates a string based off of the x, y, z position of this vector
     * @return - the string
     */
    public String toString() {
        return "<" + x + ", " + y + ", " + z + ">";
    }
}