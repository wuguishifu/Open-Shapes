package com.bramerlabs.shapes2d;

import com.bramerlabs.support.Vector3f;

import java.awt.*;
import java.util.ArrayList;

public class Cone {

    // the vertex of the cone
    private Vector3f vertex;

    // a point in the center of the circular face
    private Vector3f point;

    // the radius of the circular face - default 1
    private float radius = 1f;

    // the number of triangles used to make the circle - default 120
    private int smoothness = 120;

    // the color of this square
    private Color color;

    // the circular face;
    private Circle circle;

    // the triangles that make up the mesh of this cone
    private ArrayList<Triangle> faces = new ArrayList<>();

    // list of vertices making up the mesh of this cone
    private ArrayList<Vector3f> vertices = new ArrayList<>();

    /**
     * constructor for specified point, vertex, and color
     * @param vertex - the vertex of the cone
     * @param point - the focus of the circular face
     * @param color - the color of the cone
     */
    public Cone(Vector3f vertex, Vector3f point, Color color) {
        this.vertex = vertex;
        this.point = point;
        this.color = color;
        generateTriangles();
    }

    /**
     * constructor for specified point, vertex, color, radius, and smoothness
     * @param vertex - the vertex of the cone
     * @param point - the focus of the circular face
     * @param radius - the radius of the circular face
     * @param smoothness - the amount of triangles used to approximate the circular face
     * @param color - the color of the cone
     */
    public Cone(Vector3f vertex, Vector3f point, float radius, int smoothness, Color color) {
        this.vertex = vertex;
        this.point = point;
        this.radius = radius;
        this.smoothness = smoothness;
        this.color = color;
        generateTriangles();
    }

    /**
     * generates a list of triangles making up the mesh of this cone
     */
    private void generateTriangles() {
        // generate vertices
        generateVertices();

        // convert the color to a com.bramerlabs.support.Vector3f
        Vector3f c = new Vector3f(color.getRed(), color.getGreen(), color.getBlue());

        // get the triangular faces
        this.faces.addAll(circle.getFaces());

        // create the other part of the mesh
        for (int i = 1; i < smoothness; i++) {
            this.faces.add(new Triangle(vertices.get(i), vertices.get(smoothness+1), vertices.get(i + 1), c));
        }
        this.faces.add(new Triangle(vertices.get(smoothness), vertices.get(smoothness + 1), vertices.get(1), c));

    }

    /**
     * generates a list of vertices to make up the mesh of this cone
     */
    private void generateVertices() {
        // normal vector to circular face
        Vector3f n = new Vector3f(vertex).subtract(point);

        // the circular face
        circle = new Circle(point, radius, n, color, smoothness);

        this.vertices.addAll(circle.getVertices());
        this.vertices.add(vertex);
    }

    /**
     * getter method
     * @return - an ArrayList of triangles making up the mesh of this square
     */
    public ArrayList<Triangle> getFaces() {
        return this.faces;
    }
}
