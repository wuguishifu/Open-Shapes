package com.bramerlabs.support;

public class Color {
    
    // Vector3f value of color
    private Vector3f color;

    // Vector3f value of shaded color
    private Vector3f shadedColor;

    /**
     * default constructor
     * sets both colors to black, and the shadedDifference to 0
     */
    public Color() {
        this.color = new Vector3f();
        this.shadedColor = new Vector3f();
    }

    /**
     * constructor for two bramerlabs Vector3f
     * @param color - a bramerlabs Vector3f describing the color
     * @param shadedColor - a bramerlabs Vector3f describing the shaded color
     */
    public Color(Vector3f color, Vector3f shadedColor) {
        this.color = color;
        this.shadedColor = shadedColor;
    }

    /**
     * constructor for bramerlabs Vector3f 
     * @param color - a bramerlabs Vector3f describing the color
     * @param shadeDifference - the float difference between the color and shaded color
     */
    public Color(Vector3f color, float shadeDifference) {
        this.color = color;
        float r = Math.max(color.x - shadeDifference, 0);
        float g = Math.max(color.y - shadeDifference, 0);
        float b = Math.max(color.z - shadeDifference, 0);
        this.shadedColor = new Vector3f(r, g, b);
    }

    /**
     * constructor for bramerlabs Vector3f
     * @param color - a bramerlabs Vector3f describing the color
     */
    public Color(Vector3f color) {
        this.color = color;
        this.shadedColor = color;
    }

    /**
     * getter method
     * @return - this color
     */
    public Vector3f getColor() {
        return this.color;
    }

    /**
     * getter method
     * @return - this shaded color
     */
    public Vector3f getShadedColor() {
        return this.shadedColor;
    }
}
