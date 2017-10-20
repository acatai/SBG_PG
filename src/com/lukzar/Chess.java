package com.lukzar;

import com.lukzar.config.Templates;
import com.lukzar.fitness.FitnessUtil;
import com.lukzar.model.Piece;
import com.lukzar.model.Point;
import com.lukzar.model.elements.Arc;
import com.lukzar.model.elements.DoubleArc;
import com.lukzar.model.elements.Line;
import com.lukzar.utils.RayCasting;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by lukasz on 08.10.17.
 */
public class Chess {

    private static String template = "<div style=\"display: inline-block\">\n" +
            "\n" +
            "    <div style=\"float: left\">\n" +
            "        <svg viewBox=\"0 0 200 200\"\n" +
            "             class=\"svg\"\n" +
            "             height=\"200px\"\n" +
            "             width=\"200px\">\n" +
            "\n" +
            "            <rect height=\"200\" width=\"200\" stroke-width=\"1\" stroke=\"black\" fill=\"none\"/>\n" +
            "\n" +
            "            %s\n" +
            "        </svg>\n" +
            "    </div>\n" +
            "</div>";

    @Test
    public void test() throws IOException {

        Piece pawn = new Piece(Point.of(140, 200));
        pawn.add(new Arc(Point.of(125, 180), Point.of(155, 195)));
        pawn.add(new Arc(Point.of(117, 140), Point.of(115, 190)));
        pawn.add(new Arc(Point.of(115, 125), Point.of(140, 130)));
        pawn.add(new Arc(Point.of(100, 95), Point.of(140, 100)));

        Piece rook = new Piece(Point.of(145, 200));
        rook.add(new Arc(Point.of(140, 170), Point.of(165, 190)));
        rook.add(new Arc(Point.of(132, 95), Point.of(115, 180)));
        rook.add(new Arc(Point.of(140, 75), Point.of(140, 95)));
        rook.add(new Line(Point.of(120, 75)));
        rook.add(new Line(Point.of(120, 85)));
        rook.add(new Line(Point.of(110, 85)));
        rook.add(new Line(Point.of(110, 75)));
        rook.add(new Line(Point.of(100, 75)));

        Piece knight = new Piece(Point.of(145, 200));
        knight.convertToAsymmetric();
        knight.add(new Arc(Point.of(135, 170), Point.of(165, 190)));
        knight.add(new DoubleArc(Point.of(110, 40),
                Point.of(85, 140), Point.of(185, 40)));
        knight.add(new Line(Point.of(100, 55)));
        knight.add(new DoubleArc(Point.of(40, 65),
                Point.of(60, 55), Point.of(80, 60)));
        knight.add(new Arc(Point.of(35, 85), Point.of(30, 75)));
        knight.add(new DoubleArc(Point.of(80, 90),
                Point.of(60, 100), Point.of(50, 70)));
        knight.add(new Arc(Point.of(65, 170), Point.of(25, 150)));
        knight.add(new Arc(Point.of(55, 200), Point.of(35, 190)));

        Piece bishop = new Piece(Point.of(145, 200));
        bishop.add(new Arc(Point.of(130, 175), Point.of(165, 195)));
        bishop.add(new Arc(Point.of(117, 122), Point.of(115, 185)));
        bishop.add(new Arc(Point.of(115, 110), Point.of(140, 115)));
        bishop.add(new Arc(Point.of(115, 100), Point.of(137, 105)));
        bishop.add(new Arc(Point.of(105, 45), Point.of(140, 70)));
        bishop.add(new Arc(Point.of(100, 35), Point.of(110, 35)));

        Piece queen = new Piece(Point.of(145, 200));
        queen.add(new Arc(Point.of(130, 170), Point.of(165, 190)));
        queen.add(new Arc(Point.of(117, 110), Point.of(115, 170)));
        queen.add(new Arc(Point.of(115, 95), Point.of(140, 102.5)));
        queen.add(new Arc(Point.of(115, 85), Point.of(137, 87.5)));
        queen.add(new Arc(Point.of(140, 30), Point.of(110, 55)));
        queen.add(new Arc(Point.of(105, 30), Point.of(120, 45)));
        queen.add(new Arc(Point.of(100, 20), Point.of(110, 20)));

        Piece king = new Piece(Point.of(145, 200));
        king.add(new Arc(Point.of(130, 170), Point.of(165, 190)));
        king.add(new Arc(Point.of(117, 100), Point.of(115, 170)));
        king.add(new Arc(Point.of(115, 85), Point.of(140, 92.5)));
        king.add(new Arc(Point.of(115, 75), Point.of(137, 77.5)));
        king.add(new Arc(Point.of(135, 20), Point.of(140, 55)));
        king.add(new Arc(Point.of(110, 20), Point.of(120, 15)));
        king.add(new Arc(Point.of(100, 5), Point.of(115, 8)));

        rook.updateStartPoints();
        FitnessUtil.getArcs(rook);

        Main.writeToFile(Arrays.asList(pawn, rook, knight, bishop, queen, king), "out/chess");
    }

    @Test
    public void ray() throws IOException {

        Piece knight = new Piece(Point.of(145, 200));
        knight.convertToAsymmetric();
        knight.add(new Arc(Point.of(135, 170), Point.of(165, 190)));
        knight.add(new DoubleArc(Point.of(110, 40),
                Point.of(85, 140), Point.of(185, 40)));
        knight.add(new Line(Point.of(100, 55)));
        knight.add(new DoubleArc(Point.of(40, 65),
                Point.of(60, 55), Point.of(80, 60)));
        knight.add(new Arc(Point.of(35, 85), Point.of(30, 75)));
        knight.add(new DoubleArc(Point.of(80, 90),
                Point.of(60, 100), Point.of(50, 70)));
        knight.add(new Arc(Point.of(65, 170), Point.of(25, 150)));
        knight.add(new Arc(Point.of(55, 200), Point.of(35, 190)));


        Piece rook = new Piece(Point.of(145, 200));
        rook.add(new Arc(Point.of(140, 170), Point.of(165, 190)));
        rook.add(new Arc(Point.of(132, 95), Point.of(115, 180)));
        rook.add(new Arc(Point.of(140, 75), Point.of(140, 95)));
        rook.add(new Line(Point.of(120, 75)));
        rook.add(new Line(Point.of(121, 85)));
        rook.add(new Line(Point.of(110, 85)));
        rook.add(new Line(Point.of(111, 75)));
        rook.add(new Line(Point.of(100, 75)));


        Piece queen = new Piece(Point.of(145, 200));
        queen.add(new Arc(Point.of(130, 170), Point.of(165, 190)));
        queen.add(new Arc(Point.of(117, 110), Point.of(115, 170)));
        queen.add(new Arc(Point.of(115, 95), Point.of(140, 102.5)));
        queen.add(new Arc(Point.of(115, 85), Point.of(137, 87.5)));
        queen.add(new Arc(Point.of(140, 30), Point.of(110, 55)));
        queen.add(new Arc(Point.of(105, 30), Point.of(120, 45)));
        queen.add(new Arc(Point.of(100, 20), Point.of(110, 20)));

        final File file = new File("out/ray.html");

        try (FileOutputStream os = new FileOutputStream(file)) {
            os.write(String.format(Templates.getListTemplate(),
                    getString(knight)
                            + getString(rook)
                            + getString(queen)
            ).getBytes());
        }
    }

    private String getString(Piece rook) {
        return rook.toSvg() + "\n" + getRay(rook);
    }

    private String getRay(Piece knight) {
        knight.convertToAsymmetric();
        List<Line> converted = knight.getConverted();

        LinkedList<Line> ll = new LinkedList<>(converted);

        ll.addLast(new Line(ll.getLast().getEndPos(), ll.getFirst().getStartPos()));

        boolean[][] cast = RayCasting.cast(ll);

        StringBuilder builder = new StringBuilder();

        for (int row = 0; row < cast.length; row++) {
            for (int col = 0; col < cast.length; col++) {
                if (cast[row][col]) {
                    builder.append(
                            String.format(
                                    "<circle cx=\"%s\" cy=\"%s\" r=\"0.5\" fill=\"red\" stroke=\"red\"/>\n",
                                    col, row
                            )
                    );
                }
            }
        }


        return String.format(template, builder.toString());
    }
}