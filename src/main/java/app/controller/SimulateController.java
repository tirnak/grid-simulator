package app.controller;

import app.model.Point2D;
import app.service.InfiniteBlackWhiteGrid;
import app.service.Simulator;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Map;
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

    @RequestMapping(path = "/simulate", method = RequestMethod.PUT)
    public Map simulate(@RequestBody Integer ticks) {

        Simulator simulator = simulatorFactory.getObject();
        simulator.run(ticks);
        String result = simulator.serialize(serializer);

        return Map.of("status", "OK", "result", result);
    }
}
