package turtle;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

class My_TurtleSoupTest {
    /**
     * Tests that assertions are enabled.
     */
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false;
    }

    /**
     * Tests calculateRegularPolygonAngle.
     */
    @Test
    public void calculateRegularPolygonAngleTest() {
        assertEquals(60.0, TurtleSoup.calculateRegularPolygonAngle(3), 0.001);
        assertEquals(128.57, TurtleSoup.calculateRegularPolygonAngle(7), 0.01);
        assertEquals(108.0, TurtleSoup.calculateRegularPolygonAngle(5), 0.001);
    }

    /**
     * Tests calculatePolygonSidesFromAngle.
     */
    @Test
    public void calculatePolygonSidesFromAngleTest() {
        assertEquals(3, TurtleSoup.calculatePolygonSidesFromAngle(60.0));
        assertEquals(7, TurtleSoup.calculatePolygonSidesFromAngle(128.57));
        assertEquals(5, TurtleSoup.calculatePolygonSidesFromAngle(108.0));

    }

    /**
     * Tests calculateHeadingToPoint.
     */
    @Test
    public void calculateHeadingToPointTest() {
        assertEquals(0.0, TurtleSoup.calculateHeadingToPoint(0.0, 0, 0, 0, 1), 0.001);
        assertEquals(90.0, TurtleSoup.calculateHeadingToPoint(0.0, 0, 0, 1, 0), 0.001);
        assertEquals(359.0, TurtleSoup.calculateHeadingToPoint(1.0, 4, 5, 4, 6), 0.001);
    }

    /**
     * Tests calculateHeadings.
     */
    @Test
    public void calculateHeadingsTest() {
        List<Integer> xpoints = new ArrayList<>();
        List<Integer> ypoints = new ArrayList<>();
        xpoints.add(0);
        xpoints.add(1);
        xpoints.add(1);
        ypoints.add(0);
        ypoints.add(1);
        ypoints.add(2);

        List<Double> result = TurtleSoup.calculateHeadings(xpoints, ypoints);
        assertEquals(2, result.size());
        assertEquals(45.0, result.get(0), 0.001);
        assertEquals(315.0, result.get(1), 0.001);
    }

    @Test
    public void name() {
    }
}