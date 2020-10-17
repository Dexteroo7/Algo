package graph;//https://www.hackerrank.com/contests/data-structure-tasks-binary-tree-union-find/challenges/connected-componentpublic


import java.io.*;
import java.util.*;

public class DetectCycleUnionFind {

    private interface UnionFinder {
        int findRoot(int node);

        boolean addEdge(int from, int to);
    }

    private static final class UnionFinderBasic implements UnionFinder {

        final int[] parents;
        int connectedComponents;

        public UnionFinderBasic(int parents) {
            this.parents = new int[parents + 1];
            this.connectedComponents = parents;
        }

        public int findRoot(int node) {
            if (parents[node] == 0)
                return node;
            //return and update
            return parents[node] = findRoot(parents[node]);
        }

        public boolean addEdge(int from, int to) {

            //the node didn't belong to any group yet
            int groupIdFrom = findRoot(from);
            int groupIdTo = findRoot(to);
            if (groupIdFrom != groupIdTo) {
                connectedComponents--;
                parents[groupIdFrom] = groupIdTo;
            }
            return groupIdFrom == groupIdTo;
        }
    }

    private static final class UnionFinderOptimized implements UnionFinder {

        final IdAndRank[] idAndRanks;
        int connectedComponents;

        public UnionFinderOptimized(int nodes) {
            this.idAndRanks = new IdAndRank[nodes + 1];
            for (int i = 0; i < idAndRanks.length; i++) {
                idAndRanks[i] = new IdAndRank(i);
            }
            this.connectedComponents = nodes;
        }

        public int findRoot(int id) {
            if (idAndRanks[id].parent == 0)
                return id;
            return idAndRanks[id].parent = findRoot(idAndRanks[id].parent);
        }

        private void union(int groupIdFrom, int groupIdTo) {
            IdAndRank idAndRankFrom = idAndRanks[groupIdFrom];
            IdAndRank idAndRankTo = idAndRanks[groupIdTo];
            if (idAndRankFrom.rank > idAndRankTo.rank)
                idAndRankTo.parent = idAndRankFrom.id;
            else if (idAndRankTo.rank > idAndRankFrom.rank)
                idAndRankFrom.parent = idAndRankTo.id;
            else {
                idAndRankFrom.parent = idAndRankTo.id;
                idAndRankFrom.rank++;
            }
        }

        public boolean addEdge(int from, int to) {
            int groupIdFrom = findRoot(from);
            int groupIdTo = findRoot(to);
            if (groupIdFrom != groupIdTo) {
                union(groupIdFrom, groupIdTo);
                connectedComponents--;
            }
            return groupIdFrom == groupIdTo;
        }

        private static final class IdAndRank {
            public IdAndRank(int id) {
                this.id = id;
            }

            final int id;
            int parent;
            int rank;
        }
    }

    public static void main(String[] args) throws IOException {

//        InputStream inputStream = Files.newInputStream(Paths.get("input.txt"));
        InputStream inputStream = System.in;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        int nodes = Integer.parseInt(reader.readLine());

        UnionFinderOptimized unionFinder = new UnionFinderOptimized(nodes);


        String line;
        while ((line = reader.readLine()) != null) {

            int[] edge = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
            int from = edge[0];
            int to = edge[1];

            boolean isCycle = unionFinder.addEdge(from, to);
            if (isCycle)
                System.out.println(unionFinder.connectedComponents + " CYCLE FORMED!");
            else
                System.out.println(unionFinder.connectedComponents);
        }
    }
}
