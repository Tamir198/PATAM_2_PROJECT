package TimeSeries;

import java.util.List;
import java.util.*;

public class Circle {
    //data members
    private Circle circle;
    public Point center;
    public float radius;
    private static final double MULTIPLICATIVE_EPSILON = 1 + 1e-14;
//ctors&functions
    public Circle(List<Point> points){
        this.circle = makeCircle(points);
    }

    public Circle(Point center, float radius) {
        this.center = center;
        this.radius = radius;
    }
    public Circle(float x, float y, float radius) {
        this.center = new Point(x, y);
        this.radius = radius;
    }
    public Circle(Point p1, Point p2) {
        float x = (float)((double)(p1.x + p2.x) * 0.5D);
        float y = (float)((double)(p1.y + p2.y) * 0.5D);
        this.center = new Point(x, y);
        this.radius = this.center.distanceTo(p1);
    }
    public Circle(Point p1, Point p2, Point p3) {
        float baY = p2.y - p1.y;
        float cbY = p3.y - p2.y;
        if ((double)baY != 0.0D && (double)cbY != 0.0D) {
            float baX = -(p2.x - p1.x) / baY;
            float cbX = -(p3.x - p2.x) / cbY;
            float sub = cbX - baX;
            if ((double)sub == 0.0D) {
                this.center = new Point(0.0F, 0.0F);
                this.radius = 0.0F;
            } else {
                float p2X = p2.x * p2.x;
                float p2Y = p2.y * p2.y;
                float f1 = (float)((double)(p2X - p1.x * p1.x + p2Y - p1.y * p1.y) / (2.0D * (double)baY));
                float f2 = (float)((double)(p3.x * p3.x - p2X + p3.y * p3.y - p2Y) / (2.0D * (double)cbY));
                float pointX = (f1 - f2) / sub;
                float pointY = baX * pointX + f1;
                float dX = p1.x - pointX;
                float dY = p1.y - pointY;
                this.center = new Point(pointX, pointY);
                this.radius = (float)Math.sqrt((double)(dX * dX + dY * dY));
            }
        } else {
            this.center = new Point(0.0F, 0.0F);
            this.radius = 0.0F;
        }

    }
    public static Circle makeCircle(List<Point> points) {
        // Clone list to preserve the caller's data, randomize order
        List<Point> shuffled = new ArrayList<>(points);
        Collections.shuffle(shuffled, new Random());

        // Progressively add points to circle or recompute circle
        Circle c = null;
        for (int i = 0; i < shuffled.size(); i++) {
            Point p = shuffled.get(i);
            if (c == null || !c.contains(p))
                c = makeCircleOnePoint(shuffled.subList(0, i + 1), p);
        }
        return c;
    }
    public boolean contains(Point p) {
        return center.distanceTo(p) <= radius * MULTIPLICATIVE_EPSILON;
    }
    public boolean contains(Collection<Point> ps) {
        for (Point p : ps) {
            if (!contains(p))
                return false;
        }
        return true;
    }
    // One boundary point known
    private static Circle makeCircleOnePoint(List<Point> points, Point p) {
        Circle c = new Circle(p, 0);
        for (int i = 0; i < points.size(); i++) {
            Point q = points.get(i);
            if (!c.contains(q)) {
                if (c.radius == 0)
                    c = makeDiameter(p, q);
                else
                    c = makeCircleTwoPoints(points.subList(0, i + 1), p, q);
            }
        }
        return c;
    }
    // Two boundary points known
    private static Circle makeCircleTwoPoints(List<Point> points, Point p, Point q) {
        Circle circ = makeDiameter(p, q);
        Circle left  = null;
        Circle right = null;

        // For each point not in the two-point circle
        Point pq = q.subtract(p);
        for (Point r : points) {
            if (circ.contains(r))
                continue;

            // Form a circumcircle and classify it on left or right side
            double cross = pq.cross(r.subtract(p));
            Circle circle = makeCircumcircle(p, q, r);
            if (circle == null)
                continue;
            else if (cross > 0 && (left == null || pq.cross(circle.center.subtract(p)) > pq.cross(left.center.subtract(p))))
                left = circle;
            else if (cross < 0 && (right == null || pq.cross(circle.center.subtract(p)) < pq.cross(right.center.subtract(p))))
                right = circle;
        }
        // Select which circle to returnS
        if (left == null && right == null)
            return circ;
        else if (left == null)
            return right;
        else if (right == null)
            return left;
        else
            return left.radius <= right.radius ? left : right;
    }
    static Circle makeDiameter(Point a, Point b) {
        Point c = new Point((a.x + b.x) / 2, (a.y + b.y) / 2);
        return new Circle(c, Math.max(c.distanceTo(a), c.distanceTo(b)));
    }
    static Circle makeCircumcircle(Point a, Point b, Point c) {
        double ox = (Math.min(Math.min(a.x, b.x), c.x) + Math.max(Math.max(a.x, b.x), c.x)) / 2;
        double oy = (Math.min(Math.min(a.y, b.y), c.y) + Math.max(Math.max(a.y, b.y), c.y)) / 2;
        double ax = a.x - ox,  ay = a.y - oy;
        double bx = b.x - ox,  by = b.y - oy;
        double cx = c.x - ox,  cy = c.y - oy;
        double d = (ax * (by - cy) + bx * (cy - ay) + cx * (ay - by)) * 2;
        if (d == 0)
            return null;
        double x = ((ax*ax + ay*ay) * (by - cy) + (bx*bx + by*by) * (cy - ay) + (cx*cx + cy*cy) * (ay - by)) / d;
        double y = ((ax*ax + ay*ay) * (cx - bx) + (bx*bx + by*by) * (ax - cx) + (cx*cx + cy*cy) * (bx - ax)) / d;
        Point p = new Point((float)(ox + x), (float)(oy + y));
        double r = Math.max(Math.max(p.distanceTo(a), p.distanceTo(b)), p.distanceTo(c));
        return new Circle(p, (float) r);
    }

}
