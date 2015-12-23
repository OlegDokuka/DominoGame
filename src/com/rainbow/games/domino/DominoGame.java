package com.rainbow.games.domino;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by olehd on 23.12.2015.
 */
public class DominoGame {

    public static void main(String[] args) {
        List<Bone> bones = Arrays.asList(Bone.COMBINATIONS);

        Collections.shuffle(bones);

        new PathFinder().find(bones.subList(0, 6).toArray(new Bone[6]));

//        new PathFinder().find(new Bone[]{
//                Bone.COMBINATIONS[20], Bone.COMBINATIONS[4], Bone.COMBINATIONS[18],
//                Bone.COMBINATIONS[27], Bone.COMBINATIONS[14], Bone.COMBINATIONS[17]
//        });
    }
}
