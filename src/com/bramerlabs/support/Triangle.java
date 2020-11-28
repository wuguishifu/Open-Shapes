package com.bramerlabs.support;

public class Triangle {

    //the color of this triangle
    private Vector3f color;

    // the vertices of this triangle
    private Vector3f v1, v2, v3;

    /**
     * default constructor for bramerlabs Vector3f
     * @param v1 - the first vertex
     * @param v2 - the second vertex
     * @param v3 - the third vertex
     * @param color - the color of this triangle
     */
    public Triangle(Vector3f v1, Vector3f v2, Vector3f v3, Vector3f color) {
        this.color = color;
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
    }

    /**
     * default constructor for float[]
     * @param v1 - the first vertex
     * @param v2 - the second vertex
     * @param v3 - the third vertex
     * @param color - the color of this triangle
     */
    public Triangle(float[] v1, float[] v2, float[] v3, float[] color) {
        this.v1 = new Vector3f(v1);
        this.v2 = new Vector3f(v2);
        this.v3 = new Vector3f(v3);
        this.color = new Vector3f(color);
    }

    /**
     * getter method
     * @return - the color of this triangle in the form of a bramerlabs Vector3f
     */
    public Vector3f getColor() {
        return this.color;
    }

    /**
     * getter method
     * @return - the color of this triangle in the form of a float[]
     */
    public float[] getColorF() {
        return new float[]{this.color.x, this.color.y, this.color.z};
    }

    /**
     * getter method
     * @return - the first vertex of this triangle in the form of a bramerlabs Vector3f
     */
    public Vector3f getV1() {
        return this.v1;
    }

    /**
     * getter method
     * @return - the first vertex of this triangle in the form of a float[]
     */
    public float[] getV1F() {
        return new float[]{this.v1.x, this.v1.y, this.v1.z};
    }

    /**
     * getter method
     * @return - the second vertex of this triangle in the form of a bramerlabs Vector3f
     */
    public Vector3f getV2() {
        return this.v1;
    }

    /**
     * getter method
     * @return - the second vertex of this triangle in the form of a float[]
     */
    public float[] getV2F() {
        return new float[]{this.v2.x, this.v2.y, this.v2.z};
    }

    /**
     * getter method
     * @return - the third vertex of this triangle in the form of a bramerlabs Vector3f
     */
    public Vector3f getV3() {
        return this.v3;
    }

    /**
     * getter method
     * @return - the third vertex of this triangle in the form of a float[]
     */
    public float[] getV3F() {
        return new float[]{this.v3.x, this.v3.y, this.v3.z};
    }

}
