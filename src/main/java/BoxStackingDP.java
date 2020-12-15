import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class BoxStackingDP {

    static class Box {
        final int height, width, length;

        public Box(int height, int width, int length) {
            this.height = height;
            this.width = width;
            this.length = length;
        }
    }

    public static int maxHeight(int[] height, int[] width, int[] length, int n) {

        List<Box> boxes = new ArrayList<>(height.length * 3 + 1);
        for (int i = 0; i < height.length; i++) {
            boxes.add(new Box(height[i], Integer.max(width[i], length[i]), Integer.min(width[i], length[i])));
            boxes.add(new Box(width[i], Integer.max(height[i], length[i]), Integer.min(height[i], length[i])));
            boxes.add(new Box(length[i], Integer.max(width[i], height[i]), Integer.min(width[i], height[i])));
        }

        Collections.sort(boxes, new Comparator<Box>() {
            @Override
            public int compare(Box o1, Box o2) {
                return o2.width*o2.length - o1.width*o1.length;
            }
        });
//        boxes.sort(Comparator.<Box>comparingInt(x -> x.length).thenComparing(x -> x.width).reversed());

        int toReturn = 0;
        int[] max = new int[boxes.size()];
        for (int i = 0; i < boxes.size(); i++) {
            max[i] = boxes.get(i).height;
        }

        for (int i = 0; i < boxes.size(); i++) {

            Box current = boxes.get(i);
            //max height with box[i] on top
            int currentMax = 0;
            for (int j = 0; j < i; j++) {

                Box check = boxes.get(j);
                if (current.length < check.length && current.width < check.width) {
                    currentMax = Integer.max(currentMax, max[j]);
                }
            }
            max[i] = current.height + currentMax;
            toReturn = Integer.max(toReturn, max[i]);
        }

        return toReturn;
    }
}
