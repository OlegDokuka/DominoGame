package com.rainbow.games.domino;

/**
 * Created by olehd on 23.12.2015.
 */
public class BoneUtils {

    public static int getBoneValue(Bone src, Bone.Side side) {
        switch (side) {
            case TOP:
                return src.getTop();
            case BOTTOM:
                return src.getBottom();
        }

        return -1;
    }

    public static Bone.Side reverseSide(Bone.Side side) {
        switch (side) {
            case BOTH:
                return Bone.Side.NONE;
            case TOP:
                return Bone.Side.BOTTOM;
            case BOTTOM:
                return Bone.Side.TOP;
            case NONE:
                return Bone.Side.BOTH;
        }

        return Bone.Side.NONE;
    }

    public static Bone.Side getSuitableSide(Bone src, int value) {
        if (src.getTop() == value && src.getBottom() == value) {
            return Bone.Side.BOTH;
        } else if (src.getTop() == value) {
            return Bone.Side.TOP;
        } else if (src.getBottom() == value) {
            return Bone.Side.BOTTOM;
        }

        return Bone.Side.NONE;
    }

    public static boolean isSuitable(Bone bone1, Bone bone2, Bone.Side side1, Bone.Side side2) {
        switch (side1.toString() + side2.toString()) {
            case "TOPTOP":
                return compareByTops(bone1, bone2);
            case "TOPBOTTOM":
                return compareByTopBottom(bone1, bone2);
            case "BOTTOMTOP":
                return compareByBottomTop(bone1, bone2);
            case "BOTTOMBOTTOM":
                return compareByBottoms(bone1, bone2);
            case "BOTHBOTTOM":
                return compareByBottoms(bone1, bone2) || compareByTopBottom(bone1, bone2);
            case "BOTHTOP":
                return compareByTops(bone1, bone2) || compareByBottomTop(bone1, bone2);
            case "BOTTOMBOTH":
                return compareByBottoms(bone1, bone2) || compareByBottomTop(bone1, bone2);
            case "TOPBOTH":
                return compareByTops(bone1, bone2) || compareByTopBottom(bone1, bone2);
            case "BOTHBOTH":
                return compareByTops(bone1, bone2) || compareByBottoms(bone1, bone2) ||
                        compareByBottomTop(bone1, bone2) || compareByTopBottom(bone1, bone2);
        }

        return false;
    }

    private static boolean compareByTops(Bone bone1, Bone bone2) {
        return bone1.getTop() == bone2.getTop();
    }

    private static boolean compareByBottoms(Bone bone1, Bone bone2) {
        return bone1.getBottom() == bone2.getBottom();
    }

    private static boolean compareByTopBottom(Bone bone1, Bone bone2) {
        return bone1.getTop() == bone2.getBottom();
    }

    private static boolean compareByBottomTop(Bone bone1, Bone bone2) {
        return bone1.getBottom() == bone2.getTop();
    }
}
