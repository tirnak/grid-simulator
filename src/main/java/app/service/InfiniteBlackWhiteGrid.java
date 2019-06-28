package app.service;

import app.model.BoundingBox;
import app.model.Color;
import app.model.Direction;
import app.model.Point2D;

import java.util.Collection;
import java.util.function.Consumer;

public interface InfiniteBlackWhiteGrid {
    /**
     * The only mutating method
     * "Flips color of cell",
     * whatever the underlying logic is
     */
    void flipColor(Point2D location);

    Color getColor(Point2D location);

    /**
     * Defines navigation within grid
     *
     * @param location  to calculate from
     * @param direction where to move
     */
    Point2D getNextCell(Point2D location, Direction direction);

    /**
     * Calculate bounds of grid,
     * Also may be extented by external locations,
     * e.g. actor
     */
    BoundingBox getBounds(Collection<Point2D> extraCells);

    /**
     * Doesn't expose the grid,
     * but rather present an interface for a lambda that will consume it
     * Thus implementation depends on caller rather than on grid
     *
     * @param blackCellsConsumer function that would obtain an information about a grid
     */
    void iterateBlackCells(Consumer<? super Point2D> blackCellsConsumer);
}
