package comp127graphics;

import org.junit.jupiter.api.Test;

import static java.lang.Double.NaN;
import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PointTest {
    private final Point p = new Point(1, 2);

    @SuppressWarnings({"SimplifiableAssertion"})
    @Test
    void equality() {
        assertTrue(p.equals(p));
        assertTrue(p.equals(new Point(1,2)));
        assertFalse(p.equals(new Point(1,3)));
        assertFalse(p.equals(new Point(2,2)));
        assertFalse(p.equals(new Point(2,1)));
        //noinspection ConstantConditions
        assertFalse(p.equals(null));
        //noinspection EqualsBetweenInconvertibleTypes
        assertFalse(p.equals((Object) "frotz"));

        Point nanPoint = new Point(NaN, NaN);
        assertTrue(nanPoint.equals(new Point(NaN, NaN)));
        assertFalse(nanPoint.equals(new Point(NaN, 0)));

        assertEquals(p.hashCode(), new Point(1, 2).hashCode());
    }

    @Test
    void coordinateWrangling() {
        assertEquals(1, p.getX());
        assertEquals(2, p.getY());
        assertEquals(new Point(10, 2), p.withX(10));
        assertEquals(new Point(1, 10), p.withY(10));
    }

    @Test
    void angle() {
        assertEquals(0, Point.UNIT_X.angle());
        assertEquals(PI / 2, Point.UNIT_Y.angle());
        assertEquals(PI * -3/4, new Point(-1, -1).angle());
        assertEquals(0, Point.ORIGIN.angle());
        assertEquals(6.12 - PI * 2, Point.atAngle(6.12).angle(), 0.0001);
    }

    @Test
    void magnitude() {
        assertEquals(0, Point.ORIGIN.magnitude());
        assertEquals(Math.sqrt(2), new Point(-1, 1).magnitude());
        assertEquals(5, new Point(3, -4).magnitude());
    }

    @Test
    void distance() {
        assertEquals(5, new Point(-2, 6).distance(p));
        assertEquals(5, p.distance(new Point(-2, 6)));
    }

    @Test
    void arithmetic() {
        assertEquals(
            new Point(18, 42),
            new Point(4, -3)
                .add(new Point(10, -20))
                .subtract(new Point(-4, 5))
                .scale(0.5)
                .scale(2, -3));
    }

    @Test
    void minAndMax() {
        Point q = new Point(-2, 3);
        assertEquals(new Point(-2, 2), Point.min(p, q));
        assertEquals(new Point(-2, 2), Point.min(q, p));
        assertEquals(new Point(1, 3), Point.max(p, q));
        assertEquals(new Point(1, 3), Point.max(q, p));
    }

    @Test
    void rotate() {
        assertClose(new Point(-2, 1), p.rotate(PI / 2), 0.0001);
        assertClose(new Point(2, -1), p.rotate(PI * 7 / 2), 0.0001);
        assertClose(new Point(9, 8), p.rotate(PI, new Point(5, 5)), 0.001);
    }

	@Test
    void interpolate() {
        Point p0 = new Point(5, 6), p1 = new Point(13, 2);
        assertEquals(p0, Point.interpolate(p0, p1, 0));
        assertEquals(p1, Point.interpolate(p0, p1, 1));
        assertClose(new Point(9, 4), Point.interpolate(p0, p1, 0.5), 0.001);
        assertClose(new Point(1, 8), Point.interpolate(p0, p1, -0.5), 0.001);
    }

    private void assertClose(Point expected, Point actual, double maxDistance) {
        assertTrue(expected.distance(actual) <= maxDistance,
            "Expected " + actual + " to be close to " + expected);
	}
}
