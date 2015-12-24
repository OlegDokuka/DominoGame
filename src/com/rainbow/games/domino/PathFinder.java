package com.rainbow.games.domino;

import java.util.*;

/**
 * Created by olehd on 23.12.2015.
 */
public class PathFinder {

    public Bone[] find(Bone[] bones) {
        final List<BoneGraph> availableCombinations = buildCombinations(bones);
        Collections.sort(availableCombinations);
        List<BoneGraph> bestResult = new ArrayList<>();

        for (BoneGraph graph : availableCombinations) {
            {
                graph.setSideState(Bone.Side.BOTTOM);
                List<BoneGraph> result = moveToNext(graph);

                if (result.size() > bestResult.size()) {
                    bestResult = result;
                }
            }
            {
                graph.setSideState(Bone.Side.TOP);
                List<BoneGraph> result = moveToNext(graph);

                if (result.size() > bestResult.size()) {
                    bestResult = result;
                }
            }
        }

        return bestResult.stream().map(BoneGraph::getBone).toArray(Bone[]::new);
    }

    private List<BoneGraph> moveToNext(BoneGraph graph) {
        List<BoneGraph> result = new ArrayList<>();

        Set<BoneGraph> available = graph.getAvailableLinkedBones();

        graph.setAvailable(false);

        for (BoneGraph boneGraph : available) {
            boneGraph.setSideState(
                    BoneUtils.reverseSide(
                            BoneUtils.getSuitableSide(
                                    boneGraph.getBone(),
                                    BoneUtils.getBoneValue(graph.getBone(), graph.getSideState())
                            )
                    )
            );
            List<BoneGraph> localResult = moveToNext(boneGraph);

            if (localResult.size() > result.size()) {
                result = localResult;
            }
        }

        graph.setAvailable(true);
        graph.setSideState(Bone.Side.BOTH);
        result.add(0, graph);

        return result;
    }

    private List<BoneGraph> buildCombinations(Bone[] bones) {
        BoneGraph[] transformed = transform(bones);

        return Arrays.stream(transformed)
                .collect(ArrayList::new, (list, it) -> {
                    list.add(it);
                    findSuitable(it, transformed);
                }, ArrayList::addAll);
    }

    private BoneGraph[] transform(Bone[] bones) {
        return Arrays.stream(bones).map(BoneGraph::new).toArray(BoneGraph[]::new);
    }

    private void findSuitable(BoneGraph bone, BoneGraph[] allBones) {
        Arrays.stream(allBones)
                .filter(item -> !bone.equals(item))
                .filter(item -> BoneUtils.isSuitable(item.getBone(), bone.getBone(), Bone.Side.BOTH, Bone.Side.BOTH))
                .forEach(bone::add);
    }
}
