package app;

import model.Direction;
import model.Point2D;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.awt.*;

@Configuration
public class Defaults
{
	@Bean
	public Color getColor()
	{
		return Color.WHITE;
	}

	@Bean
	public Direction getOrientation()
	{
		return Direction.RIGHT;
	}

	@Bean
	public Point2D getStart()
	{
		return new Point2D(0, 0);
	}
}
