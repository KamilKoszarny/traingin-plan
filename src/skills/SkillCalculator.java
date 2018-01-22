package skills;

public class SkillCalculator {
    private static final double pointsPerHour = 0.125;


    public static double pointsByMinutes(double minutes){
        double points = 0;
        for (int i = 0; i < minutes; i++){
            points += (100.0-points)/100 * pointsPerHour /60;
        }
        return points;
    }

    public static double pointsPercentLeftByDays(int days){
        double pointsPercentLeft = 100;
        for (int i = 0; i < days; i++) {
            pointsPercentLeft *= 0.999;
//            System.out.println("Perc in day " + i + " : " + pointsPercentLeft);
        }
        return pointsPercentLeft;
    }

    public static double pointsByExpHours(int expHours, double points){
        for (int i = 0; i < expHours; i++){
            points += (100.0 - points)/100.0* pointsPerHour;
        }
        return points;
    }

    public static double pointsByExpHours(int expHours){
        return pointsByExpHours(expHours, 0);
    }
}
