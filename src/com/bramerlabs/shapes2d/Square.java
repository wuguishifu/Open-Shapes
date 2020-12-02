package com.bramerlabs.shapes2d;

import com.bramerlabs.support.Vector3f;

import java.awt.*;
import java.util.ArrayList;

public class Square {

    // the position of the center of the square
    private Vector3f center;

    // the position of a corner of the square
    private Vector3f c1;

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
        this.c1 = corner;
        this.normal = normal;
        this.color = color;
        generateTriangles();
    }

    /**
     * generates a list of triangles making the mesh of this square
     */
    public void generateTriangles() {
        generateVertices();
        this.faces.add(new Triangle(vertices.get(0), vertices.get(1), vertices.get(2), new Vector3f(color.getRed(), color.getGreen(), color.getBlue())));
        this.faces.add(new Triangle(vertices.get(2), vertices.get(3), vertices.get(0), new Vector3f(color.getRed(), color.getGreen(), color.getBlue())));
    }

    /**
     * generates a list of vertices - the 4 corners of the square
     */
    private void generateVertices() {
        float size = Vector3f.distance(c1, center);
        Vector3f c1p = new Vector3f(c1).subtract(center);
        Vector3f c2p = Vector3f.cross(c1p, normal);
        Vector3f c3p = Vector3f.cross(c2p, normal);
        Vector3f c4p = Vector3f.cross(c3p, normal);
        vertices.add(c1p.add(center).scale(size));
        vertices.add(c2p.add(center).scale(size));
        vertices.add(c3p.add(center).scale(size));
        vertices.add(c4p.add(center).scale(size));
        System.out.println(c1p);
        System.out.println(c2p);
        System.out.println(c3p);
        System.out.println(c4p);
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