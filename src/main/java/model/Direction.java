package model;

public enum Direction
{
	UP,
	DOWN,
	LEFT,
	RIGHT;

	public Direction nextClockwise()
	{
		switch (this)
		{
			case UP:
				return RIGHT;
			case RIGHT:
				return DOWN;
			case DOWN:
				return LEFT;
			case LEFT:
				return UP;
		}
		throw new IllegalArgumentException(
			"Unknonw direction of " + this + " was provided");
	}

	public Direction nextCounterClockwise()
	{
		switch (this)
		{
			case UP:
				return LEFT;
			case LEFT:
				return DOWN;
			case DOWN:
				return RIGHT;
			case RIGHT:
				return UP;
			default:
		}
		throw new IllegalArgumentException(
			"Unknonw direction of " + this + " was provided");
	}
}
