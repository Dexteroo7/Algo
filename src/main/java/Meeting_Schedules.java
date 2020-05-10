import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.BitSet;

public class Meeting_Schedules {

    public static void main(String[] args) throws IOException {

        DateTimeFormatter hh_mm = DateTimeFormatter.ofPattern("HH mm");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader reader = Files.newBufferedReader(Paths.get("input.txt"));
        String[] s = reader.readLine().split(" ");
        int slots = Integer.parseInt(s[0]);
        int requiredMins = Integer.parseInt(s[1]);

        BitSet allDayMins = new BitSet((24 * 60) + 1);

        for (int i = 0; i < slots; i++) {
            s = reader.readLine().split(" ");

            int startingMins = minFromMilitaryTime(s[0], s[1]);
            int endMins = minFromMilitaryTime(s[2], s[3]);
            if (endMins < startingMins) {
                allDayMins.set(startingMins, (24 * 60) + 1);
            } else
                allDayMins.set(startingMins, endMins);
        }

        int lastSlotEnd = 0;
        while (lastSlotEnd < 24 * 60) {
            int startingMins = allDayMins.nextClearBit(lastSlotEnd);
            int endingMins = allDayMins.nextSetBit(startingMins);
            //rest of the day is free
            if (endingMins == -1)
                endingMins = 24 * 60;
            if (endingMins - startingMins >= requiredMins)
                System.out.println(militaryTimeFromMins(startingMins) + " " + militaryTimeFromMins(endingMins));
            lastSlotEnd = endingMins;
        }
    }


    public static String militaryTimeFromMins(int minutes) {

        int hour = minutes / 60;
        int mins = minutes % 60;
        String toReturn = "";

        if (hour < 10)
            toReturn += "0";
        toReturn += hour;
        toReturn += " ";
        if (mins < 10)
            toReturn += "0";
        toReturn += mins;

        return toReturn.equals("24 00") ? "00 00" : toReturn;
    }

    public static int minFromMilitaryTime(String hours, String mins) {

        int parsedHours;
        String cleanedHours = hours.replaceFirst("^0+", "");
        if (cleanedHours.isEmpty())
            parsedHours = 0;
        else
            parsedHours = Integer.parseInt(cleanedHours);

        int parsedMins;
        String cleanedMins = mins.replaceFirst("^0+", "");
        if (cleanedMins.isEmpty())
            parsedMins = 0;
        else
            parsedMins = Integer.parseInt(cleanedMins);

        return (parsedHours * 60) + parsedMins;
    }
}
