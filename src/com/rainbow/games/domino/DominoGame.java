package com.rainbow.games.domino;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by olehd on 23.12.2015.
 */
public class DominoGame {

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new RuntimeException("Pleas, insert as program variable count of bones");
        }

        startGame(Integer.valueOf(args[0]));
    }

    private static void startGame(int itemsCount) {
        List<Bone> bones = Arrays.asList(Bone.COMBINATIONS);
        Bone[] generated;
        Bone[] result;

        Collections.shuffle(bones);
        generated = bones.subList(0, itemsCount).toArray(new Bone[itemsCount]);

        System.out.println("--------------Generated randomly bones-------------");
        printBones(generated);
        System.out.println("---------------------------------------------------");

        System.out.println("---------------------------------------------------");

        result = new PathFinder().find(generated);

        System.out.println("------Calculated longest combination of bones------");
        printBones(result);
        System.out.println("---------------------------------------------------");

//        new PathFinder().find(new Bone[]{
//                Bone.COMBINATIONS[20], Bone.COMBINATIONS[4], Bone.COMBINATIONS[18],
//                Bone.COMBINATIONS[27], Bone.COMBINATIONS[14], Bone.COMBINATIONS[17]
//        });
    }

    private static void printBones(Bone... bones) {
        Arrays.stream(bones).forEach(System.out::println);
    }
}
