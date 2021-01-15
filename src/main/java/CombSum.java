import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class CombSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        Arrays.sort(candidates);
        List<List<Integer>> acc = new ArrayList<>();
        combinationSum(candidates, target, 0, 0, new ArrayList<>(), acc);
        return acc;
    }

    public void combinationSum(int[] candidates, int target, int index, int sum, List<Integer> current, List<List<Integer>> acc) {

        //since its sorted, we can exit
        if (sum > target)
            return;
        if (sum == target) {
            acc.add(new ArrayList<>(current));
            return;
        }

        for (int i = index; i < candidates.length; i++) {

            if (sum + candidates[i] > target)
                break;

            current.add(candidates[i]);
            combinationSum(candidates, target, i, sum + candidates[i], current, acc);
            current.remove(current.size() - 1);
        }
    }
}
