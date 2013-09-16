/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER       // YOUR DEFINITION HERE
         = new Comparator<Point>() {
         public int compare(Point point1, Point point2) {
             double slope1, slope2;
             slope1 = slopeTo(point1);
             slope2 = slopeTo(point2);
             if (slope1 == slope2) return 0;
             else if (slope1 < slope2) return -1;
             else return 1;
             
         }
    };
    

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        if (this.x == that.x && this.y == that.y)
        {
            return Double.NEGATIVE_INFINITY;
        }
        if (this.y == that.y)
        {
            return 0;
        }
        if (this.x == that.x)
        {
            return Double.POSITIVE_INFINITY;
        }
        
        return ((double) that.y - (double) this.y) 
                / ((double) that.x - (double) this.x);
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        /* The compareTo() method should compare points 
         * by their y-coordinates, breaking ties by their 
         * x-coordinates. Formally, the invoking point (x0, y0) 
         * is less than the argument point (x1, y1) 
         * if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
         */
        if (this.x == that.x && this.y == that.y)
        {
            return 0;           
        }
        else if (this.y < that.y)
        {
            return -1;
        }
        else if (this.y == that.y && this.x < that.x)
        {
            return -1;
        }
        else
        {
            return 1;
        }
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
        Point p1 = new Point(14000, 10000);
        Point p2 = new Point(18000, 10000);
        Point p3 = new Point(19000, 10000);
         
        StdOut.print(p1.SLOPE_ORDER.compare(p2, p3));
    }
}