package app.service.impl;

import app.model.Point2D;
import app.service.InfiniteBlackWhiteGrid;
import lombok.AllArgsConstructor;
import lombok.experimental.Delegate;

/**
 * Wrapper class to use when needed to avoid grid state mutation
 * As an alternative to defensive copy
 */
@AllArgsConstructor
public class ImmutableGridDecorator implements InfiniteBlackWhiteGrid {

    /**
     * Delegate from Lombok project is aimed to reduce all the boilerplate for decorator pattern
     * use with caution - feature is experimental and may be removed in future releases
     */
    @Delegate(excludes = Exclude.class)
    private InfiniteBlackWhiteGrid original;

    @Override
    public void flipColor(Point2D location) {
        throw new UnsupportedOperationException();
    }
}

interface Exclude {
    void flipColor(Point2D location);
}
