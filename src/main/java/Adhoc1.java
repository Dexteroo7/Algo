import java.util.*;

class Adhoc1 {

    private static final class Visit {

        final int timestamp;
        final String website;

        private Visit(int timestamp, String website) {
            this.timestamp = timestamp;
            this.website = website;
        }
    }

    private static final class Triple implements Comparable<Triple> {
        final String a, b, c;

        private Triple(String a, String b, String c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Triple triple = (Triple) o;
            return a.equals(triple.a) &&
                    b.equals(triple.b) &&
                    c.equals(triple.c);
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b, c);
        }

        @Override
        public int compareTo(Triple o) {

            if (a.compareTo(o.a) == 0) {
                if (b.compareTo(o.b) == 0) {
                    return c.compareTo(o.c);
                }
                return b.compareTo(o.b);
            }
            return a.compareTo(o.a);
        }
    }

    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {

        Map<String, List<Visit>> visitsByUser = new HashMap<>();
        for (int i = 0; i < website.length; i++) {
            Visit visit = new Visit(timestamp[i], website[i]);
            visitsByUser.computeIfAbsent(username[i], na -> new ArrayList<>()).add(visit);
        }
        visitsByUser.forEach((s, visits) -> visits.sort(Comparator.<Visit>comparingInt(x -> x.timestamp).thenComparing(x -> x.website)));

        Map<Triple, Set<String>> tripleVisitCounter = new HashMap<>();
        visitsByUser.forEach((s, visits) -> {

            if (visits.size() < 3)
                return;
            for (int i = 0; i < visits.size(); i++) {
                for (int j = i + 1; j < visits.size(); j++) {
                    for (int k = j + 1; k < visits.size(); k++) {

                        Triple triple = new Triple(visits.get(i).website, visits.get(j).website, visits.get(k).website);
                        tripleVisitCounter.computeIfAbsent(triple, na -> new HashSet<>()).add(s);
                    }
                }
            }
        });

        Triple bestTriple = null;
        int maxHits = 0;

        for (Map.Entry<Triple, Set<String>> entry : tripleVisitCounter.entrySet()) {

            Triple key = entry.getKey();
            int hits = entry.getValue().size();
            if (hits > maxHits) {
                bestTriple = key;
                maxHits = hits;
            } else if (hits == maxHits) {
                if (bestTriple.compareTo(key) > 0) {
                    bestTriple = key;
                    maxHits = hits;
                }
            }
        }

        return Arrays.asList(bestTriple.a, bestTriple.b, bestTriple.c);
    }

    public static void main(String[] args) {

        System.out.println(new Adhoc1().mostVisitedPattern(
                new String[]{"h","eiy","cq","h","cq","txldsscx","cq","txldsscx","h","cq","cq"},
                new int[]{527896567,334462937,517687281,134127993,859112386,159548699,51100299,444082139,926837079,317455832,411747930},
                new String[]{"hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","yljmntrclw","hibympufi","yljmntrclw"}
        ));
    }
}
