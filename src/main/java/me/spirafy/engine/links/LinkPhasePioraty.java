package me.spirafy.engine.links;

import me.spirafy.engine.phase.Phase;

public class LinkPhasePioraty {
    private Phase phase;
    private int pioraty;

    public LinkPhasePioraty(Phase phase, int pioraty) {
        this.phase = phase;
        this.pioraty = pioraty;
    }

    public Phase getPhase() {
        return phase;
    }

    public int getPioraty() {
        return pioraty;
    }
}
