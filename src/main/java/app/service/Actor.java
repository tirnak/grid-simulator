package app.service;

import app.model.Point2D;

public interface Actor {
    /**
     * Perform actor specific logic for one unit of time
     */
    void act();

    InfiniteBlackWhiteGrid getGrid();

    Point2D getCurrentLocation();
}
