package app.controller;

import app.model.Point2D;
import app.model.SimulateRequestDto;
import app.model.SimulateResponseDto;
import app.service.InfiniteBlackWhiteGrid;
import app.service.Simulator;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.UUID;
import java.util.function.BiFunction;

@RestController
public class SimulateController {

    /**
     * Use bean factory wrapper to dinamically obtaion
     * fresh simulator each time
     */
    @Inject
    private ObjectProvider<Simulator> simulatorFactory;

    /**
     * Serializer abstracted as a funcitonal interface,
     * takes grid, actor's location, returns serialized value
     */
    @Inject
    private BiFunction<InfiniteBlackWhiteGrid, Point2D, String> serializer;

    @Value("${simulation.results.directory:/tmp}")
    private Path directoryTowWriteResultsTo;

    @RequestMapping(path = "/simulate", method = RequestMethod.PUT,
	    consumes = "application/json", produces = "application/json")
    public SimulateResponseDto simulate(@RequestBody SimulateRequestDto parameters) throws IOException {

        Simulator simulator = simulatorFactory.getObject();
        simulator.run(parameters.getTicks());
        String result = simulator.serialize(serializer);

	    String filename = writeToFile(result);

	    SimulateResponseDto response = new SimulateResponseDto();
	    response.setFilename(filename);
	    return response;
	}

	/**
	 * TODO abstract file-related logic
	 */
	private String writeToFile(String result) throws IOException {

		UUID uuid = UUID.randomUUID();
		Path pathToSaveTo = directoryTowWriteResultsTo.resolve(uuid.toString() + ".txt");
		Files.createFile(pathToSaveTo);

		Files.write(pathToSaveTo, Collections.singleton(result), StandardOpenOption.WRITE);

		return pathToSaveTo.toString();
	}

	/**
	 * TODO add check that `directoryTowWriteResultsTo` is writable
	 */
	@RequestMapping(path = "/health", method = RequestMethod.GET)
	public void healthCheck() {}
}
