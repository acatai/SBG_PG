package com.lukzar.model.elements;

import com.lukzar.config.Configuration;
import com.lukzar.model.Point;
import com.lukzar.utils.BezierUtil;

import java.util.List;
import java.util.Locale;

/**
 * NOT USED for now
 * <p>
 * Created by lukasz on 04.06.17.
 */
public class DoubleArc extends Part {

    private final Point q1;
    private final Point q2;

    public DoubleArc(Point endPos, Point q1, Point q2) {
        super(endPos);
        this.q1 = q1;
        this.q2 = q2;
    }

    @Override
    public String toSvg() {
        return String.format(Locale.US, "C%s %s %s ", q1.toSvg(), q2.toSvg(), endPos.toSvg());
    }

    @Override
    public DoubleArc reverse() {
        double ax = Configuration.Piece.WIDTH - q2.getX();
        double ay = q2.getY();
        double bx = Configuration.Piece.WIDTH - q1.getX();
        double by = q1.getY();
        double cx = Configuration.Piece.WIDTH - startPos.getX();
        double cy = startPos.getY();
        return new DoubleArc(
                Point.of(cx, cy),
                Point.of(ax, ay),
                Point.of(bx, by)
        );
    }

    @Override
    public List<Line> convertToLines() {
        return BezierUtil.convertToLine(this);
    }

    public Point getQ1() {
        return this.q1;
    }

    public Point getQ2() {
        return this.q2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        DoubleArc doubleArc = (DoubleArc) o;

        return q1.equals(doubleArc.q1) && q2.equals(doubleArc.q2);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + q1.hashCode();
        result = 31 * result + q2.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s - [%s] - [%s] - %s", startPos.toSvg(), q1.toString(), q2.toString(), endPos.toString());
    }
}
