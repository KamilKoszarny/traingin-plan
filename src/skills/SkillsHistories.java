package skills;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class SkillsHistories extends ArrayList<SkillsHistories.SkillHistory> implements Serializable {

    private static final long serialVersionUID = 1L;


    public static class SkillHistory extends ArrayList<SkillHistory.SkillMoment> implements Serializable{

        private static final long serialVersionUID = 1L;

        public Skill skill;

        SkillHistory(Skill skill){
            this.skill = skill;
        }

        public void addMoment(Skill skill, Project project){
            this.add(new SkillMoment(skill.getPoints(), new Date(), project, skill.getReqPoints(), skill.getOccurrences()));
        }

        public double getLastPoints(){
            return this.get(this.size() - 1).points;
        }


        public class SkillMoment implements Serializable{
            private static final long serialVersionUID = 1L;

            public Double points;
            public Date date;
            public Project project;
            public double[] reqPoints;
            public int occurrences;

            SkillMoment(double points, Date date, Project project, double[] reqPoints, int occurrences){
                this.points = points;
                this.date = date;
                this.project = project;
                this.reqPoints = reqPoints;
                this.occurrences = occurrences;
            }
        }

    }
}
