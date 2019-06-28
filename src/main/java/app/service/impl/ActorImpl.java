package app.service.impl;

import app.model.Color;
import app.model.Direction;
import app.model.Point2D;
import app.service.Actor;
import app.service.InfiniteBlackWhiteGrid;

import javax.inject.Inject;

import static app.model.Color.BLACK;
import static app.model.Color.WHITE;

public class ActorImpl implements Actor
{
	@Inject
	private InfiniteBlackWhiteGrid grid;

	@Inject
	private Point2D location;

	@Inject
	private Direction orientation;

    @Override
    public Point2D getCurrentLocation() {
        return location;
    }

	/**
	 * Main logic for traversing and mutating the grid
	 */
	@Override
	public void act()
	{
		Point2D oldLocation = this.location;

		Color oldColor = grid.getColor(oldLocation);

		if (WHITE.equals(oldColor))
		{
			orientation = orientation.nextClockwise();
		}
		else if (BLACK.equals(oldColor))
		{
			orientation = orientation.nextCounterClockwise();
		}
		else
		{
			throw new IllegalArgumentException(
				"Cell at " + oldLocation + " has invalid color of " + oldColor);
		}

		this.location = grid.getNextCell(location, orientation);

		grid.flipColor(oldLocation);
	}

	/**
	 * Returns immutable in order to avoid state mutation
	 */
	@Override
	public InfiniteBlackWhiteGrid getGrid()
	{
		return new ImmutableGridDecorator(grid);
	}
}

