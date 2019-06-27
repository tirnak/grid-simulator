package service.impl;

import model.Direction;
import model.Point2D;
import org.springframework.stereotype.Component;
import service.Actor;
import service.InfiniteBlackWhiteGrid;
import javax.inject.Inject;
import java.awt.*;

import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;

@Component
public class ActorImpl implements Actor
{
	@Inject
	private InfiniteBlackWhiteGrid grid;

	@Inject
	private Point2D location;

	@Inject
	private Direction orientation;

	@Override
	public int getX()
	{
		return location.getX();
	}

	@Override
	public int getY()
	{
		return location.getY();
	}

	@Override
	public void act()
	{
		Point2D oldLocation = this.location;

		Color oldColor = grid.getColor(oldLocation);

		if (WHITE.equals(oldColor))
		{
			orientation = orientation.nextClockwise();
		}
		if (BLACK.equals(oldColor))
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

	@Override
	public InfiniteBlackWhiteGrid getGrid()
	{
		return grid;
	}
}

