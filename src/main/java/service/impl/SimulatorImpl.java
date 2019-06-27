package service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import service.Actor;
import service.InfiniteBlackWhiteGrid;
import service.Simulator;
import javax.inject.Inject;
import java.util.function.Function;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class SimulatorImpl implements Simulator
{
	@Inject
	private Actor actor;

	public void run(int ticks)
	{
		for (int i = 0; i < ticks; i++)
		{
			actor.act();
		}
	}

	public String serialize(Function<InfiniteBlackWhiteGrid, String> serializer)
	{
		return serializer.apply(actor.getGrid());
	}

}
