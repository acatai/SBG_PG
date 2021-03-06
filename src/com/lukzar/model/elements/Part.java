package com.lukzar.model.elements;

import com.lukzar.model.Point;

import java.util.List;

/**
 * Created by lukasz on 04.06.17.
 */
public abstract class Part {

    Point endPos;
    Point startPos;

    public Part(Point endPos) {
        this.endPos = endPos;
    }

    public Part(Point startPos, Point endPos) {
        this.endPos = endPos;
        this.startPos = startPos;
    }

    public abstract String toSvg();

    public abstract Part reverse();

    public abstract List<Line> convertToLines();

    public Point getEndPos() {
        return this.endPos;
    }

    public void setEndPos(Point endPos) {
        this.endPos = endPos;
    }

    public Point getStartPos() {
        return this.startPos;
    }

    public void setStartPos(Point startPos) {
        this.startPos = startPos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Part part = (Part) o;

        return endPos.equals(part.endPos);
    }

    @Override
    public int hashCode() {
        return endPos.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s - %s", startPos.toSvg(), endPos.toString());
    }
}
