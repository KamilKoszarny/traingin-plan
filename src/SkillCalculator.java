public class SkillCalculator {
    private static final double x = 0.03;

    public static double calculateSkillByExpHours(int expHours, double points){
        for (int i = 0; i < expHours; i++){
            points += (100.0 - points)/100.0*x;
        }
        return points;
    }

    public static double calculateSkillByExpHours(int expHours){
        double points = calculateSkillByExpHours(expHours, 0);
        return points;
    }
}
