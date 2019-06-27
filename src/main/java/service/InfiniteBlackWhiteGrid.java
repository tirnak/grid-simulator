package service;

import model.Direction;
import model.Point2D;
import java.awt.*;

public interface InfiniteBlackWhiteGrid
{
	Color getColor(Point2D location);

	void flipColor(Point2D location);

	Point2D getNextCell(Point2D location, Direction direction);
}
