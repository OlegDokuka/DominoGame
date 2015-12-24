package com.rainbow.games.domino;

import com.sun.istack.internal.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by olehd on 24.12.2015.
 */
public class BoneGraph implements Comparable<BoneGraph> {
    private boolean isAvailable = true;
    private Bone.Side sideState = Bone.Side.BOTH;
    private Bone bone;
    private Set<BoneGraph> linkedBones = new HashSet<>();

    public BoneGraph(Bone bone) {
        this.bone = bone;
    }

    public boolean add(@NotNull BoneGraph bone) {
        return linkedBones.add(bone);
    }

    @Override
    public int compareTo(BoneGraph o) {
        return Integer.compare(linkedBones.size(), o.linkedBones.size());
    }

    public Bone getBone() {
        return bone;
    }

    public Set<BoneGraph> getAvailableLinkedBones() {
        return linkedBones.stream()
                .filter(boneGraph -> boneGraph.isAvailable && BoneUtils.isSuitable(bone, boneGraph.bone, sideState, boneGraph.sideState))
                .collect(Collectors.toSet());
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

    public Bone.Side getSideState() {
        return sideState;
    }

    public void setSideState(Bone.Side sideState) {
        this.sideState = sideState;
    }
}
