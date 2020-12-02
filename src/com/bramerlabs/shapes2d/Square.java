package com.bramerlabs.shapes2d;

import com.bramerlabs.support.Vector3f;

import java.awt.*;
import java.util.ArrayList;

public class Square {

    // the position of the center of the square
    private Vector3f center;

    // the position of a corner of the square
    private Vector3f corner;

    // the vector normal to the square
    private Vector3f normal;

    // the color of this square
    private Color color;

    // the triangles that make up the mesh of this square
    private ArrayList<Triangle> faces = new ArrayList<>();

    // list of vertices making up the mesh
    private ArrayList<Vector3f> vertices = new ArrayList<>();

    /**
     * constructor for specified position and color
     * @param center - the position of the center of the square
     * @param normal - a vector normal to the square
     * @param color - the color of the square
     */
    public Square(Vector3f center, Vector3f corner, Vector3f normal, Color color) {
        this.center = center;
        this.corner = corner;
        this.normal = normal;
        this.color = color;
        generateTriangles();
    }

    /**
     * generates a list of triangles making the mesh of this square
     */
    public void generateTriangles() {
        generateVertices();

        Vector3f c = new Vector3f(color.getRed(), color.getGreen(), color.getBlue());
        c.scale((float)1/255);

        this.faces.add(new Triangle(vertices.get(0), vertices.get(1), vertices.get(2), c));
        this.faces.add(new Triangle(vertices.get(2), vertices.get(3), vertices.get(0), c));
    }

    /**
     * generates a list of vertices - the 4 corners of the square
     */
    private void generateVertices() {
        float size = Vector3f.distance(corner, center);
        Vector3f c1 = Vector3f.cross(normal, corner);
        Vector3f c2 = Vector3f.cross(normal, c1);
        Vector3f c3 = Vector3f.cross(normal, c2);
        Vector3f c4 = Vector3f.cross(normal, c3);
        c1.normalize(size).add(center);
        c2.normalize(size).add(center);
        c3.normalize(size).add(center);
        c4.normalize(size).add(center);
        vertices.add(c1);
        vertices.add(c2);
        vertices.add(c3);
        vertices.add(c4);
    }

    public ArrayList<Vector3f> getVertices() {
        return this.vertices;
    }

    /**
     * getter method
     * @return - an ArrayList of triangles making up the mesh of this square
     */
    public ArrayList<Triangle> getFaces() {
        return this.faces;
    }
}