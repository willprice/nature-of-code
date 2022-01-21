package dev.willprice.noc.chapter0;

import com.google.common.base.Preconditions;
import dev.willprice.noc.components.probability.DiscreteDistribution;
import lombok.ToString;
import processing.core.PApplet;

import java.util.Map;
import java.util.Random;

public class Ex1 extends PApplet {
    Walker walker;

    @Override
    public void settings() {
        size(400, 400);
    }

    @Override
    public void setup() {
        background(255);
        Random random = new Random();
        DiscreteDistribution<Walker.Direction> distribution = new DiscreteDistribution<Walker.Direction>(Map.of(
                Walker.Direction.N, 1.0,
                Walker.Direction.NE, 1.0,
                Walker.Direction.E, 1.0,
                Walker.Direction.SE, 2.0,
                Walker.Direction.S, 1.0,
                Walker.Direction.SW, 1.0,
                Walker.Direction.W, 1.0,
                Walker.Direction.NW, 1.0
        ), random);
        walker = new Walker(width/2, height/2, random, distribution);
    }

    @Override
    public void draw() {
        walker.step();
        walker.draw(this);
    }

    public static void main(String[] args) {
        PApplet.main(Ex1.class.getName());
    }

    @ToString
    public class Walker {
        private int x;
        private int y;
        private final Random random;
        private final DiscreteDistribution<Walker.Direction> distribution;

        public Walker(int x, int y, Random random, DiscreteDistribution<Walker.Direction> distribution) {
            this.x = x;
            this.y = y;
            this.random = random;
            this.distribution = distribution;
        }

        public void step() {
            Walker.Direction direction = distribution.sample();
            switch (direction) {
                case W:
                case NW:
                case SW:
                    x -= 1;
                    break;
                case E:
                case NE:
                case SE:
                    x += 1;
                    break;
            }
            switch (direction) {
                case N:
                case NW:
                case NE:
                    y -= 1;
                    break;
                case S:
                case SW:
                case SE:
                    y += 1;
                    break;
            }
            x = bound(x, width);
            y = bound(y, height);
        }

        public void draw(PApplet applet) {
            applet.point(x, y);
        }

        private int bound(int val, int max) {
            return max(min(val, max), 0);
        }

        public enum Direction {
            W, NW, N, NE, E, SE, S, SW;
            private static final Direction[] directions = values();

            public static Direction fromInt(int x) {
                Preconditions.checkArgument(x < directions.length, "x (%s) was out of bounds", x);
                return directions[x];
            }
        }
    }
}
