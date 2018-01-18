package main;

public class SkillCalculator {
    private static final double x = 0.15;

    public static double pointsByMinutes(double minutes){
        double points = 0;
        for (int i = 0; i < minutes; i++){
            points += (100.0 - points)/6000.0 * x;
        }
        return points;
    }

    public static double pointsByExpHours(int expHours, double points){
        for (int i = 0; i < expHours; i++){
            points += (100.0 - points)/100.0*x;
        }
        return points;
    }

    public static double pointsByExpHours(int expHours){
        double points = pointsByExpHours(expHours, 0);
        return points;
    }
}
