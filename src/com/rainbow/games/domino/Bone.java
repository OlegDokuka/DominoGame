package com.rainbow.games.domino;

/**
 * Created by olehd on 23.12.2015.
 */
public class Bone {
    public static final Bone[] COMBINATIONS = new Bone[]{
            new Bone(0, 0), new Bone(0, 1), new Bone(0, 2), new Bone(0, 3), new Bone(0, 4), new Bone(0, 5), new Bone(0, 6),
            new Bone(1, 1), new Bone(1, 2), new Bone(1, 3), new Bone(1, 4), new Bone(1, 5), new Bone(1, 6), new Bone(2, 2),
            new Bone(2, 3), new Bone(2, 4), new Bone(2, 5), new Bone(2, 6), new Bone(3, 3), new Bone(3, 4), new Bone(3, 5),
            new Bone(3, 6), new Bone(4, 4), new Bone(4, 5), new Bone(4, 6), new Bone(5, 5), new Bone(5, 6), new Bone(6, 6),
    };


    private int top;
    private int bottom;

    private Bone(int top, int bottom) {
        this.top = top;
        this.bottom = bottom;
    }

    public int getTop() {
        return top;
    }

    public int getBottom() {
        return bottom;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Bone) {
            Bone entered = (Bone) obj;

            return entered.top == top && entered.bottom == bottom;
        }

        return false;
    }

    @Override
    public String toString() {
        return String.format("Bone(%s, %s)", top, bottom);
    }
}
