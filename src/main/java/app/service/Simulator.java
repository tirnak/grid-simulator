package app.service;

import app.model.Point2D;

import java.util.function.BiFunction;

public interface Simulator {

    /**
     * Perform the actual logic for running the simulation
     * during `ticks` amount of time
     */
    void run(int ticks);

    /**
     * Allows to delegate the serialization's specific to
     * functional interfaced class
     */
    String serialize(BiFunction<InfiniteBlackWhiteGrid, Point2D, String> serializer);
}
