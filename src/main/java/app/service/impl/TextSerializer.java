package app.service.impl;

import app.model.BoundingBox;
import app.model.Point2D;
import app.service.InfiniteBlackWhiteGrid;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.function.BiFunction;
import java.util.function.Consumer;

@Service
public class TextSerializer implements BiFunction<InfiniteBlackWhiteGrid, Point2D, String> {

    public static final char BLACK_CELL = '∎';
    public static final char WHITE_CELL = '∙';
    public static final char ACTOR_CELL = '㋡';

    @Override
    public String apply(InfiniteBlackWhiteGrid infiniteBlackWhiteGrid, Point2D actorLocation) {
        BoundingBox bounds = infiniteBlackWhiteGrid.getBounds(Collections.singleton(actorLocation));
        char[][] matrix = new char[bounds.getMaxX() - bounds.getMinX() + 1][bounds.getMaxY() - bounds.getMinY() + 1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = WHITE_CELL;
            }
        }

        int xOffset = bounds.getMinX();
        int yOffset = bounds.getMinY();

        Consumer<Point2D> matrixPopulator = (cellLocation) -> {
            matrix
                    [cellLocation.getX() - xOffset]
                    [cellLocation.getY() - yOffset] = BLACK_CELL;
        };

        infiniteBlackWhiteGrid.iterateBlackCells(matrixPopulator);
        matrix
                [actorLocation.getX() - xOffset]
                [actorLocation.getY() - yOffset] = ACTOR_CELL;

        // x is in right order to present, however y is inverted.
        // so, bump rows in a reverse and only then concatenate
        // rewrite to good old indices and StringBuffer.append in need of better performance
        Deque<String> mirroredUp = new LinkedList<>();

        for (char[] chars : matrix) {
            mirroredUp.push(new String(chars));
        }

        return String.join("\n", mirroredUp);
    }


}
