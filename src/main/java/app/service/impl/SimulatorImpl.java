package app.service.impl;

import app.model.Point2D;
import app.service.Actor;
import app.service.InfiniteBlackWhiteGrid;
import app.service.Simulator;

import javax.inject.Inject;
import java.util.function.BiFunction;

public class SimulatorImpl implements Simulator {
    @Inject
    private Actor actor;

    public void run(int ticks) {
        for (int i = 0; i < ticks; i++) {
            actor.act();
        }
    }

    public String serialize(BiFunction<InfiniteBlackWhiteGrid, Point2D, String> serializer) {
        return serializer.apply(actor.getGrid(), actor.getCurrentLocation());
    }

}
