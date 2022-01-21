package dev.willprice.noc.components.walkers;

import lombok.Builder;
import lombok.ToString;
import processing.core.PApplet;

import java.util.Random;

import static java.lang.Math.max;
import static java.lang.Math.min;

@ToString
public class SimpleWalker {
    private int x;
    private int y;
    private final int maxX;
    private final int maxY;
    private final int minStepSize;
    private final int maxStepSize;
    private final Random random;
    private int lastX;
    private int lastY;

    @Builder
    public SimpleWalker(int x, int y, int maxX, int maxY, int minStepSize, int maxStepSize, Random random) {
        this.x = x;
        this.lastX = x;
        this.y = y;
        this.lastY = y;
        this.maxX = maxX;
        this.maxY = maxY;
        this.minStepSize = minStepSize;
        this.maxStepSize = maxStepSize;
        this.random = random;
    }

    public static SimpleWalker centerInScreen(PApplet applet) {
        return centerInScreen(applet, 1, 1);
    }

    public static SimpleWalker centerInScreen(PApplet applet, int minStepSize, int maxStepSize) {
        return SimpleWalker.builder()
                .random(new Random())
                .maxX(applet.width)
                .maxY(applet.height)
                .x(applet.width / 2)
                .y(applet.height / 2)
                .minStepSize(minStepSize)
                .maxStepSize(maxStepSize)
                .build();
    }

    public void step() {
        int choice = random.nextInt(4);
        lastX = x;
        lastY = y;
        int xStep = minStepSize + random.nextInt(maxStepSize - minStepSize + 1) - 1;
        int yStep = minStepSize + random.nextInt(maxStepSize - minStepSize + 1) - 1;
        x = x + xStep * (random.nextInt(3) - 1);
        y = y + yStep * (random.nextInt(3) - 1);
        x = bound(x, maxX);
        y = bound(y, maxY);
    }

    public void draw(PApplet applet) {
        applet.line(lastX, lastY, x, y);
    }

    private int bound(int val, int max) {
        return max(min(val, max), 0);
    }
}
