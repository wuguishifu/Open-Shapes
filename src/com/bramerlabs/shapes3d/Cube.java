package com.bramerlabs.shapes3d;

import com.bramerlabs.shapes2d.Square;
import com.bramerlabs.shapes2d.Triangle;
import com.bramerlabs.support.Vector3f;

import java.awt.*;
import java.util.ArrayList;

public class Cube {

    // the center of the cube
    private Vector3f center;

    // two normal vectors defining the cube
    private Vector3f n1, n2;

    // radius of the cube
    private float radius;

    // the color of this cube
    private Color color;

    // the triangles that make up the mesh of this square
    private ArrayList<Triangle> faces = new ArrayList<>();

    /**
     * constructor for specified center, two normal vectors, radius, and color.
     * @param center - the center of the cube
     * @param n1 - one normal vector of the cube
     * @param n2 - a different normal vector of the cube
     * @param radius - the radius of the cube
     * @param color - the color of the cube
     */
    public Cube(Vector3f center, Vector3f n1, Vector3f n2, float radius, Color color) {
        this.center = center;
        this.n1 = n1;
        this.n2 = n2;
        this.radius = radius;
        this.color = color;
        generateTriangles();
    }

    /**
     * generates a list of triangles making the mesh of this cube
     */
    public void generateTriangles() {
        ArrayList<Vector3f> vertices = generateVertices();
        Vector3f c = new Vector3f(color.getRed(), color.getGreen(), color.getBlue());
        c.scale((float)1/255);

        // face 1
        faces.add(new Triangle(vertices.get(0), vertices.get(1), vertices.get(2), c));
        faces.add(new Triangle(vertices.get(0), vertices.get(3), vertices.get(2), c));

        // face 2
        faces.add(new Triangle(vertices.get(0), vertices.get(1), vertices.get(5), c));
        faces.add(new Triangle(vertices.get(0), vertices.get(6), vertices.get(5), c));

        // face 3
        faces.add(new Triangle(vertices.get(0), vertices.get(3), vertices.get(6), c));
        faces.add(new Triangle(vertices.get(6), vertices.get(7), vertices.get(3), c));

        // face 4
        faces.add(new Triangle(vertices.get(3), vertices.get(7), vertices.get(2), c));
        faces.add(new Triangle(vertices.get(2), vertices.get(4), vertices.get(7), c));

        // face 5
        faces.add(new Triangle(vertices.get(2), vertices.get(4), vertices.get(1), c));
        faces.add(new Triangle(vertices.get(1), vertices.get(5), vertices.get(4), c));

        // face 6
        faces.add(new Triangle(vertices.get(4), vertices.get(5), vertices.get(6), c));
        faces.add(new Triangle(vertices.get(4), vertices.get(7), vertices.get(6), c));
    }

    /**
     * generates a list of vertices of this cube
     */
    public ArrayList<Vector3f> generateVertices() {
        n1.normalize(radius);
        n2.normalize(radius);
        Vector3f n3 = Vector3f.cross(n1, n2).normalize(radius);

        Vector3f c1 = (new Vector3f(n1).add(n2)).add(n3);
        Vector3f c2 = (new Vector3f(n1).add(n2)).subtract(n3);

        Square s1 = new Square(n3, c1, n3, color);
        Square s2 = new Square(n3.invert(), c2, n3, color);


        ArrayList<Vector3f> result = new ArrayList<>();

        result.addAll(s1.getVertices());
        result.addAll(s2.getVertices());

        for (Vector3f v : s1.getVertices()) {
            v.add(center);
        }
        for (Vector3f v : s2.getVertices()) {
            v.add(center);
        }

        return result;
    }

    /**
     * getter method
     * @return - a list of triangular faces making up the mesh of this cube
     */
    public ArrayList<Triangle> getFaces() {
        return this.faces;
    }

}
