package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.Simulator;
import javax.inject.Inject;
import java.util.Map;

@RestController
public class SimulateController
{

	@Autowired
	private Simulator simulator;

	@RequestMapping(path = "/simulate", method = RequestMethod.GET)
	public Map String()
	{

		return Map.of("status", "OK");
	}
}
