package app.service.impl;

import app.model.BoundingBox;
import app.model.Color;
import app.model.Direction;
import app.model.Point2D;
import app.service.InfiniteBlackWhiteGrid;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Since default color for all the cells is white and we need to track only
 * the grid part where black cells reside, let's ignore the white cells
 */
public class OnlyBlackGridImpl implements InfiniteBlackWhiteGrid {

    /**
     * HashSet is injected since lookup-add-remove is faster on average
     * and results only in one traversing for bound calculation
     */
    @Inject
    private Set<Point2D> setOfBlackCells;

    @Inject
    private Color defaultColor;

    @Override
    public Color getColor(Point2D location) {
        return setOfBlackCells.contains(location) ? Color.BLACK : Color.WHITE;
    }

    /**
     * Since only black cells are counted:
     * white -> black - add new cell to the set
     * black -> white - just remove black cell from the set
     *
     * @param location
     */
    @Override
    public void flipColor(Point2D location) {
        boolean exists = setOfBlackCells.contains(location);
        if (exists) {
            setOfBlackCells.remove(location);
        } else {
            setOfBlackCells.add(location);
        }

    }

    @Override
    public Point2D getNextCell(Point2D location, Direction direction) {
        switch (direction) {
            case UP:
                return new Point2D(location.getX(), location.getY() + 1);
            case DOWN:
                return new Point2D(location.getX(), location.getY() - 1);
            case LEFT:
                return new Point2D(location.getX() - 1, location.getY());
            case RIGHT:
                return new Point2D(location.getX() + 1, location.getY());
        }
        throw new IllegalArgumentException("Can't figure next location to " + location + " towards " + direction);
    }

    @Override
    public BoundingBox getBounds(Collection<Point2D> extraCells) {

        if (extraCells == null) {
            extraCells = Collections.emptySet();
        }

        int minX = 0;
        int minY = 0;
        int maxX = 0;
        int maxY = 0;

        // this code duplication can be refactored to streams,
        // but effectively final requirements and stream overhead are not worth it
        for (Point2D location : setOfBlackCells) {
            if (location.getX() < minX) {
                minX = location.getX();
            }
            if (location.getX() > maxX) {
                maxX = location.getX();
            }
            if (location.getY() < minY) {
                minY = location.getY();
            }
            if (location.getY() > maxY) {
                maxY = location.getY();
            }
        }

        for (Point2D location : extraCells) {
            if (location.getX() < minX) {
                minX = location.getX();
            }
            if (location.getX() > maxX) {
                maxX = location.getX();
            }
            if (location.getY() < minY) {
                minY = location.getY();
            }
            if (location.getY() > maxY) {
                maxY = location.getY();
            }
        }
        return new BoundingBox(minX, maxX, minY, maxY);
    }

    /**
     * Allows to perform custom logic in client code
     * without knowing internal specifics of grid
     *
     * @param blackCellsConsumer function that would obtain an information about a grid
     */
    @Override
    public void iterateBlackCells(Consumer<? super Point2D> blackCellsConsumer) {
        for (Point2D blackCellLocation : setOfBlackCells) {
            blackCellsConsumer.accept(blackCellLocation);
        }
    }
}
