package com.bramerlabs.shapes3d;

import com.bramerlabs.shapes2d.Circle;
import com.bramerlabs.shapes2d.Triangle;
import com.bramerlabs.support.Vector3f;

import java.awt.*;
import java.util.ArrayList;

public class Cylinder {

    // position of center of circular faces
    private Vector3f p1, p2;

    private Color color;

    // the radius of the cylinder
    private float radius;

    // the smoothness of the cylinder - default 120
    private int smoothness = 120;

    // the circular faces
    private Circle c1, c2;

    // the faces of the triangles making the cylinder
    private ArrayList<Triangle> faces = new ArrayList<>();

    /**
     * Constructor based on two points in the center of the circular faces, radius, and color
     * @param p1 - the point in the center of the first circular face
     * @param p2 - the point in the center of the second circular face
     * @param radius - the radius of the cylinder
     * @param color - the color of this cylinder
     */
    public Cylinder(Vector3f p1, Vector3f p2, float radius, Color color) {
        this.p1 = p1;
        this.p2 = p2;
        this.radius = radius;
        this.color = color;
        generateTriangles();
    }

    /**
     * Constructor based on two points in the center of the circular faces, radius, color, and smoothness
     * @param p1 - the point in the center of the first circular face
     * @param p2 - the point in the center of the second circular face
     * @param radius - the radius of the cylinder
     * @param color - the color of the cylinder
     * @param smoothness - the smoothness of the cylinder
     */
    public Cylinder(Vector3f p1, Vector3f p2, float radius, Color color, int smoothness) {
        this.p1 = p1;
        this.p2 = p2;
        this.radius = radius;
        this.color = color;
        this.smoothness = smoothness;
        generateTriangles();
    }

    /**
     * generates a list of triangles making up the mesh of this cylinder
     */
    private void generateTriangles() {
        generateCircles();

        faces.addAll(c1.getFaces());
        faces.addAll(c2.getFaces());

        ArrayList<Vector3f> v1 = c1.getVertices();
        ArrayList<Vector3f> v2 = c2.getVertices();

        Vector3f c = new Vector3f(color.getRed(), color.getGreen(), color.getBlue());
        c.scale((float)1/255);

        for (int i = 1; i < c1.getVertices().size() - 1; i++) {
            this.faces.add(new Triangle(v1.get(i), v2.get(i), v1.get(i+1), c));
            this.faces.add(new Triangle(v2.get(i), v1.get(i+1), v2.get(i+1), c));
        }
        this.faces.add(new Triangle(v1.get(smoothness), v2.get(smoothness), v1.get(1), c));
        this.faces.add(new Triangle(v2.get(smoothness), v1.get(1), v2.get(1), c));
    }

    /**
     * generates the two circular faces of the cylinder
     */
    private void generateCircles() {
        Vector3f normal = new Vector3f(p1).subtract(p2);
        c1 = new Circle(p1, radius, normal, color, smoothness);
        c2 = new Circle(p2, radius, normal, color, smoothness);
    }

    /**
     * getter method
     * @return - the ArrayList of triangles
     */
    public ArrayList<Triangle> getFaces() {
        return this.faces;
    }

}
