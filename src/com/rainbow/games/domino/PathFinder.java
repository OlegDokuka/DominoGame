package com.rainbow.games.domino;

import com.sun.istack.internal.NotNull;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by olehd on 23.12.2015.
 */
public class PathFinder {

    public Bone[] find(Bone[] bones) {
        final List<BoneGraph> availableCombinations = buildCombinations(bones);
        Collections.sort(availableCombinations);
        List<BoneGraph> bestResult = new ArrayList<>();

        for (BoneGraph graph : availableCombinations) {
            List<BoneGraph> result = moveToNext(graph);

            if (result.size() > bestResult.size()) {
                bestResult = result;
            }
        }

        return null; //todo fix it
    }

    private List<BoneGraph> moveToNext(BoneGraph graph) {
        List<BoneGraph> result = new ArrayList<>();

        Set<BoneGraph> available = graph.getAvailableLinkedBones();

        graph.setAvailable(false);

        for (BoneGraph boneGraph : available) {
            List<BoneGraph> localResult = moveToNext(boneGraph);

            if (localResult.size() > result.size()) {
                result = localResult;
            }
        }

        graph.setAvailable(true);
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
                .filter(item -> BoneUtils.isSuitable(item.getBone(), bone.getBone()))
                .forEach(bone::add);
    }

    private class BoneGraph implements Comparable<BoneGraph> {
        private int linksCount = 0;
        private boolean isAvailable = true;
        private Bone bone;
        private Set<BoneGraph> linkedBones = new HashSet<>();

        BoneGraph(Bone bone) {
            this.bone = bone;
            this.linksCount = linkedBones.size();
        }

        public boolean add(@NotNull BoneGraph bone) {
            return linkedBones.add(bone);
        }

        @Override
        public int compareTo(BoneGraph o) {
            return Integer.compare(linksCount, o.linksCount);
        }

        public Bone getBone() {
            return bone;
        }

        public Set<BoneGraph> getAvailableLinkedBones() {
            return linkedBones.stream().filter(BoneGraph::isAvailable).collect(Collectors.toSet());
        }

        public boolean isAvailable() {
            return isAvailable;
        }

        public void setAvailable(boolean available) {
            isAvailable = available;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj != null && obj instanceof BoneGraph) {
                BoneGraph graph = (BoneGraph) obj;

                return graph.bone.equals(bone);
            }

            return false;
        }
    }
}
