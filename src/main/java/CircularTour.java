//https://practice.geeksforgeeks.org/problems/circular-tour/1
class CircularTour {
    int tour(int petrol[], int distance[]) {

        int current = 0;
        int startingIndex = 0;

        for (int i = 0; i < petrol.length; i++) {

            current += petrol[i];
            if (current < distance[i]) {
                startingIndex = i + 1;
                current = 0;
            } else
                current -= distance[i];
        }

        if (startingIndex == petrol.length)
            return -1;

        for (int i = 0; i < startingIndex; i++) {
            current += petrol[i];
            if (current < distance[i]) {
                return -1;
            } else
                current -= distance[i];
        }

        return startingIndex;
    }
}
