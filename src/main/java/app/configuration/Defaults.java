package app.configuration;

import app.model.Color;
import app.model.Direction;
import app.model.Point2D;
import app.service.Actor;
import app.service.InfiniteBlackWhiteGrid;
import app.service.Simulator;
import app.service.impl.ActorImpl;
import app.service.impl.OnlyBlackGridImpl;
import app.service.impl.SimulatorImpl;
import org.springframework.context.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashSet;
import java.util.Set;

/**
 * Default beans to be injected
 * Note `WebApplicationContext.SCOPE_REQUEST` - each bean exist only within a request
 */
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
	@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public Point2D getStart()
	{
		return new Point2D(0, 0);
	}

    @Bean
	@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Set getCellStorage() {
	    return new HashSet();
    }

	@Bean
	@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public Actor getActor() {
		return new ActorImpl();
	}

	@Bean
	@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public InfiniteBlackWhiteGrid getGrid() {
		return new OnlyBlackGridImpl();
	}

	@Bean
	@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public Simulator getSimulator() {
		return new SimulatorImpl();
	}

}
