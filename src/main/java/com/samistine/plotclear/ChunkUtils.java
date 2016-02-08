/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samistine.plotclear;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Chunk;
import org.bukkit.Location;

/**
 *
 * @author Samuel
 */
public class ChunkUtils {

    public FoundChunks findChunksInArea(List<Location> xandzValues) {
        List<Chunk> possibleChunks = new ArrayList<>();
        for (Location loc : xandzValues) {
            Chunk chunkForLoc = loc.getChunk();
            if (!possibleChunks.contains(chunkForLoc)) {
                possibleChunks.add(chunkForLoc);
            }
        }
        List<Chunk> completeChunks = new ArrayList<>();
        List<Chunk> partialChunks = new ArrayList<>();
        loop:
        for (Chunk chunk : possibleChunks) {
            int X = chunk.getX() * 16;
            int Z = chunk.getZ() * 16;
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    Location loc = chunk.getWorld().getBlockAt(X + x, 0, Z + z).getLocation();
                    if (!xandzValues.contains(loc)) {
                        partialChunks.add(chunk);
                        continue loop;
                    }
                }
            }
            completeChunks.add(chunk);
        }
        return new FoundChunks(completeChunks, partialChunks);
    }

    public static class FoundChunks {

        List<Chunk> completeChunks;
        List<Chunk> partialChunks;

        public FoundChunks(List<Chunk> completeChunks, List<Chunk> partialChunks) {
            this.completeChunks = completeChunks;
            this.partialChunks = partialChunks;
        }

        public List<Chunk> getCompleteChunks() {
            return completeChunks;
        }

        public List<Chunk> getPartialChunks() {
            return partialChunks;
        }

    }

}
