package com.lukzar.model.elements;

import com.lukzar.Main;
import com.lukzar.model.Point;
import com.lukzar.utils.BezierUtil;

import java.util.List;

import lombok.Getter;

/**
 * Created by lukasz on 04.06.17.
 */
@Getter
public class Arc extends Part {

    private final Point q;

    public Arc(Point endPos, Point q) {
        super(endPos);
        this.q = q;
    }

    @Override
    public String toSvg() {
        return String.format("Q%f,%f %f,%f ", q.getX(), q.getY(),
                endPos.getX(), endPos.getY());
    }

    @Override
    public String toSvgReversed() {
        return String.format("Q%f,%f %f,%f ",
                Main.CONFIG.getPiece().getWidth() - q.getX(), q.getY(),
                Main.CONFIG.getPiece().getWidth() - startPos.getX(), startPos.getY());
    }

    @Override
    public List<Line> convertToLines() {
        return BezierUtil.convertToLine(this);
    }

}
