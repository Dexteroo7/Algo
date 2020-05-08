package Graphs;

import java.io.*;
import java.math.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RoadsandLibraries {

    // Complete the roadsAndLibraries function below.
    static long roadsAndLibraries(int n, int c_lib, int c_road, int[][] connections) {

        final LibsAndRoads libsAndRoads;
        if (c_lib <= c_road || connections.length == 0) {
            libsAndRoads = new LibsAndRoads(n, 0);
        } else {

            List<List<Integer>> adjacencyList = IntStream.range(0, n)
                    .mapToObj(na -> new ArrayList<Integer>())
                    .collect(Collectors.toList());
            for (int[] connection : connections) {
                adjacencyList.get(connection[0] - 1).add(connection[1] - 1);
                adjacencyList.get(connection[1] - 1).add(connection[0] - 1);
            }

//        adjacencyList.forEach(System.out::println);
            libsAndRoads = roadsAndLibraries(adjacencyList, new BitSet(n), new LibsAndRoads(), n);
        }
//        System.out.println(result);
        return (libsAndRoads.libs * c_lib) + (libsAndRoads.roads * c_road);
    }

    static LibsAndRoads roadsAndLibraries(List<List<Integer>> adjacencyList,
                                          BitSet visited,
                                          LibsAndRoads libsAndRoads,
                                          int numberOfCities) {

        //we get the id of any city without a library, build one there and connect all cities using a dfs
        int cityId = visited.nextClearBit(0);
        while (cityId < numberOfCities) {
//        System.out.println("Unconnected" + cityId);
            //build a library
            libsAndRoads.libs++;

            //connect all children
            Deque<Integer> bfsQueue = new LinkedList<>();
            bfsQueue.addFirst(cityId);
            visited.set(cityId);
            while (!bfsQueue.isEmpty()) {

                Integer currentCityId = bfsQueue.removeLast();
                //build a road to children and add to bfsQueue if not already seen
                for (Integer connectedCity : adjacencyList.get(currentCityId)) {
                    if (!visited.get(connectedCity)) {
                        libsAndRoads.roads++;
                        bfsQueue.addFirst(connectedCity);
                        visited.set(connectedCity);
                    }
                }
            }
            cityId = visited.nextClearBit(0);
        }

        return libsAndRoads;
    }

    private static final class LibsAndRoads {

        static LibsAndRoads none() {
            return new LibsAndRoads();
        }

        long libs = 0, roads = 0;

        public LibsAndRoads(long libs, long roads) {
            this.libs = libs;
            this.roads = roads;
        }

        public LibsAndRoads() {
        }

        public LibsAndRoads merge(LibsAndRoads other) {
            return new LibsAndRoads(this.libs + other.libs, this.roads + other.roads);
        }

        @Override
        public String toString() {
            return "LibsAndRoads{" +
                    "libs=" + libs +
                    ", roads=" + roads +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {


        Scanner scanner = new Scanner(new FileInputStream(new File("input03.txt")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nmC_libC_road = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nmC_libC_road[0]);

            int m = Integer.parseInt(nmC_libC_road[1]);

            int c_lib = Integer.parseInt(nmC_libC_road[2]);

            int c_road = Integer.parseInt(nmC_libC_road[3]);

            int[][] cities = new int[m][2];

            for (int i = 0; i < m; i++) {
                String[] citiesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int citiesItem = Integer.parseInt(citiesRowItems[j]);
                    cities[i][j] = citiesItem;
                }
            }

            long result = roadsAndLibraries(n, c_lib, c_road, cities);
            System.out.println(result);
        }

        scanner.close();
    }
}
