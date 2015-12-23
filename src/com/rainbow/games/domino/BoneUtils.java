package com.rainbow.games.domino;

/**
 * Created by olehd on 23.12.2015.
 */
public class BoneUtils {
    public static boolean isSuitable(Bone bone1, Bone bone2) {
        return bone1.getTop() == bone2.getTop() ||
                bone1.getBottom() == bone2.getBottom() ||
                bone1.getTop() == bone2.getBottom() ||
                bone1.getBottom() == bone2.getTop();
    }
}
