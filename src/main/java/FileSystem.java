import java.util.*;

//https://leetcode.com/problems/design-in-memory-file-system
class FileSystem {

    public static final Map<String, SortedSet<String>> STRUCTURE = new HashMap<>();
    public static final Map<String, String> FILE_CONTENT = new HashMap<>();

    public FileSystem() {
        STRUCTURE.clear();
        FILE_CONTENT.clear();
    }

    public List<String> ls(String path) {

        if (FILE_CONTENT.containsKey(path)) {
            String[] split = path.split("/");
            return Collections.singletonList(split[split.length-1]);
        }

        return new ArrayList<>(STRUCTURE.getOrDefault(path, Collections.emptySortedSet()));
    }

    public void mkdir(String path) {

        String[] split = path.split("/");
        if (split.length > 0) {
            STRUCTURE.computeIfAbsent("/", na -> new TreeSet<>()).add(split[1]);
            StringBuilder prev = new StringBuilder();
            for (int i = 2; i < split.length; i++) {
                prev.append("/").append(split[i-1]);
                STRUCTURE.computeIfAbsent(prev.toString(), na -> new TreeSet<>()).add(split[i]);
            }
        }
    }

    public void addContentToFile(String filePath, String content) {
        mkdir(filePath);
        FILE_CONTENT.merge(filePath, content, (a, b) -> a + b);
    }

    public String readContentFromFile(String filePath) {
        return FILE_CONTENT.get(filePath);
    }
}
