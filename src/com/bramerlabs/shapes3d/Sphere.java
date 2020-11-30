package com.bramerlabs.shapes3d;

import com.bramerlabs.support.Triangle;
import com.bramerlabs.support.Vector3f;

import java.awt.*;
import java.util.ArrayList;

public class Sphere {


    // position of the sphere - default 0, 0, 0
    private Vector3f position;

    // radius of the sphere - default 1
    private float radius = 1.0f;

    // color of the sphere - default black
    private Color color;

    // the amount of times to recursively subdivide faces
    private int depth = 4;

    // a list of triangles in this sphere
    private ArrayList<Triangle> faces = new ArrayList<>();

    // the golden ratio
    private static final float phi = 1.6180339f;

    /**
     * constructor for specified position and color with radius 1
     * @param position - the position of the focus of this sphere
     * @param color - the color of this sphere
     */
    public Sphere(Vector3f position, Color color) {
        this.position = position;
        this.color = color;
        generateTriangles();
    }

    /**
     * constructor for all specified values
     * @param position - the position of the focus of this sphere
     * @param radius - the radius of the sphere
     * @param color - the color of this sphere
     * @param depth - the amount of times to recursively subdivide faces for smoothing
     */
    public Sphere(Vector3f position, float radius, Color color, int depth) {
        this.position = position;
        this.radius = radius;
        this.color = color;
        this.depth = depth;
        generateTriangles();
    }

    /**
     * generates a list of
     */
    private void generateTriangles() {
        Vector3f[] vertices = generateVertices();

        // subdivide each triangular face (20 total) recursively
        subdivide(vertices[0],  vertices[2],  vertices[10], depth);
        subdivide(vertices[0],  vertices[10], vertices[5],  depth);
        subdivide(vertices[0],  vertices[5],  vertices[4],  depth);
        subdivide(vertices[0],  vertices[4],  vertices[8],  depth);
        subdivide(vertices[0],  vertices[8],  vertices[2],  depth);
        subdivide(vertices[3],  vertices[1],  vertices[11], depth);
        subdivide(vertices[3],  vertices[11], vertices[7],  depth);
        subdivide(vertices[3],  vertices[7],  vertices[6],  depth);
        subdivide(vertices[3],  vertices[6],  vertices[9],  depth);
        subdivide(vertices[3],  vertices[9],  vertices[1],  depth);
        subdivide(vertices[2],  vertices[6],  vertices[7],  depth);
        subdivide(vertices[2],  vertices[7],  vertices[10], depth);
        subdivide(vertices[10], vertices[7],  vertices[11], depth);
        subdivide(vertices[10], vertices[11], vertices[5],  depth);
        subdivide(vertices[5],  vertices[11], vertices[1],  depth);
        subdivide(vertices[5],  vertices[1],  vertices[4],  depth);
        subdivide(vertices[4],  vertices[1],  vertices[9],  depth);
        subdivide(vertices[4],  vertices[9],  vertices[8],  depth);
        subdivide(vertices[8],  vertices[9],  vertices[6],  depth);
        subdivide(vertices[8],  vertices[6],  vertices[2],  depth);
    }

    /**
     * generates a list of vertices based on the depth - default 4. Recursively subdivides each triangle to
     * form more vertices.
     * @return - a list of vertices of triangles in the sphere.
     */
    private Vector3f[] generateVertices() {

        // define a regular icosahedron using 12 vertices
        Vector3f[] vertices = new Vector3f[12];
        vertices[0]  = new Vector3f( 0.5f * radius, 0,  phi/2 * radius);
        vertices[1]  = new Vector3f( 0.5f * radius, 0, -phi/2 * radius);
        vertices[2]  = new Vector3f(-0.5f * radius, 0,  phi/2 * radius);
        vertices[3]  = new Vector3f(-0.5f * radius, 0, -phi/2 * radius);
        vertices[4]  = new Vector3f( phi/2 * radius,  0.5f * radius, 0);
        vertices[5]  = new Vector3f( phi/2 * radius, -0.5f * radius, 0);
        vertices[6]  = new Vector3f(-phi/2 * radius,  0.5f * radius, 0);
        vertices[7]  = new Vector3f(-phi/2 * radius, -0.5f * radius, 0);
        vertices[8]  = new Vector3f(0,  phi/2 * radius, 0.5f * radius);
        vertices[9]  = new Vector3f(0,  phi/2 * radius,-0.5f * radius);
        vertices[10] = new Vector3f(0, -phi/2 * radius, 0.5f * radius);
        vertices[11] = new Vector3f(0, -phi/2 * radius,-0.5f * radius);
        return vertices;
    }

    /**
     * recursively subdivides a triangle into 4 triangles, and then normalizes each new vertex to a radius of 1
     * @param v1 - the first vertex of the triangle
     * @param v2 - the second vertex of the triangle
     * @param v3 - the third vertex of the triangle
     * @param depth - the current depth of recursion
     */
    private void subdivide(Vector3f v1, Vector3f v2, Vector3f v3, long depth) {

        // default condition
        if (depth == 0) {

            // create new vectors to modify
            Vector3f v1p = new Vector3f(v1);
            Vector3f v2p = new Vector3f(v2);
            Vector3f v3p = new Vector3f(v3);

            faces.add(new Triangle(v1p, v2p, v3p, new Vector3f(color.getRed()/255f, color.getGreen()/255f, color.getBlue()/255f)));

            return;
        }

        // create new vertices for each face
        Vector3f v12 = new Vector3f(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
        Vector3f v23 = new Vector3f(v2.x + v3.x, v2.y + v3.y, v2.z + v3.z);
        Vector3f v31 = new Vector3f(v3.x + v1.x, v3.y + v1.y, v3.z + v1.z);

        // normalize each vertex to retain a certain radius
        v12.normalize(radius);
        v23.normalize(radius);
        v31.normalize(radius);

        // recursive part
        subdivide(v1, v12, v31, depth-1);
        subdivide(v2, v23, v12, depth-1);
        subdivide(v3, v31, v23, depth-1);
        subdivide(v12, v23, v31,depth-1);
    }

    /**
     * getter method
     * @return - the ArrayList of triangles
     */
    public ArrayList<Triangle> getFaces() {
        return this.faces;
    }
}