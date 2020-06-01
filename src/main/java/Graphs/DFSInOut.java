package Graphs;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

//https://www.hackerearth.com/practice/algorithms/graphs/topological-sort/practice-problems/algorithm/oliver-and-the-game-3
class DFSInOut {

    static int[][] getInOut(int houses, List<Integer>[] adjacencyList) {

        int[][] inout = new int[houses + 1][2];

        dfs(adjacencyList, inout, new int[1], 1);

        return inout;
    }

    static void dfs(List<Integer>[] adjacencyList, int[][] inout, int[] counter, int current) {

        //already visited
        if (inout[current][0] > 0)
            return;

        counter[0]++;
        inout[current][0] = counter[0];

        for (int id : adjacencyList[current]) {
            dfs(adjacencyList, inout, counter, id);
        }

        counter[0]++;
        inout[current][1] = counter[0];
    }

    public static void main(String[] args) throws Exception {
        //BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader input = Files.newBufferedReader(Paths.get("input2.txt"));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));

        int houses = Integer.parseInt(input.readLine());

        if (houses == 1) {
            output.write("YES\n");
            return;
        }

        List<Integer>[] adjacencyList = new List[houses + 1];
        for (int i = 0; i < adjacencyList.length; i++)
            adjacencyList[i] = new ArrayList<>(2);
        for (int i = 1; i < houses; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int from = Integer.parseInt(tokenizer.nextToken());
            int to = Integer.parseInt(tokenizer.nextToken());
            adjacencyList[from].add(to);
            adjacencyList[to].add(from);
        }

        int[][] inOut = getInOut(houses, adjacencyList);

        int queries = Integer.parseInt(input.readLine());
        for (int i = 0; i < queries; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            boolean towards1 = tokenizer.nextToken().equals("0");
            int oliver = Integer.parseInt(tokenizer.nextToken());
            int bob = Integer.parseInt(tokenizer.nextToken());
            int[] oliverLevel = inOut[oliver];
            int[] bobLevel = inOut[bob];
            output.write(towards1 ? oliverLevel[0] < bobLevel[0] && oliverLevel[1] > bobLevel[1] ? "YES" : "NO" : oliverLevel[1] < bobLevel[1] && oliverLevel[0] > bobLevel[0] ? "YES" : "NO");
            output.write('\n');
        }
        output.flush();
    }
}
