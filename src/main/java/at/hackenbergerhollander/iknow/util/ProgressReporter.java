package at.hackenbergerhollander.iknow.util;

import java.text.DecimalFormat;

public class ProgressReporter {

    private static final DecimalFormat FOUR_DECIMAL_PLACES = new DecimalFormat("#.####");

    private String text;
    private int interval;

    private int counter = 0;
    private long start = 0;
    private long starttime = 0;

    public ProgressReporter(String text, int interval) {
        this.text = text;
        this.interval = interval;
    }

    public ProgressReporter(int interval) {
        this("", interval);
    }

    public void update() {
        synchronized (this) {
            if (starttime == 0) {
                starttime = System.currentTimeMillis();
            }
            if (counter % interval == 0) {
                if (start != 0) {
                    double diff = (System.nanoTime() - start) / 1000000D;
                    System.out.println(text + (counter - interval) + "-" + counter + " in " + FOUR_DECIMAL_PLACES.format(diff) + "ms, " +
                            "per item " + FOUR_DECIMAL_PLACES.format(diff / (double) interval) + "ms, " +
                            "Runtime: " + asHours((int) ((System.currentTimeMillis() - starttime) / 1000)));
                }
                start = System.nanoTime();
            }
            counter++;
        }
    }

    private static String asHours(int seconds) {
        int s = seconds % 60;
        int m = seconds / 60 % 60;
        int h = seconds / 60 / 60 % 60;
        return String.format("%02d", h) + ":" + String.format("%02d", m) + ":" + String.format("%02d", s);
    }

}
