package app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Bounds of a grid
 */
@AllArgsConstructor
@Getter
public class BoundingBox {
    private int minX, maxX, minY, maxY;
}
