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

        public void addMoment(Skill skill, Project project, String comment){
            this.add(new SkillMoment(skill, project, comment));
        }

        public double getLastPoints(){
            return this.get(this.size() - 1).points;
        }

        public double getLastBruttoPoints(){
            return this.get(this.size() - 1).bruttoPoints;
        }

        public int getLastMinutes(){
            return this.get(this.size() - 1).minutes;
        }


        public class SkillMoment implements Serializable{
            private static final long serialVersionUID = 1L;

            public Double points;
            public Double bruttoPoints;
            public Date date;
            public Project project;
            public String comment;
            public double[] reqPoints;
            public int minutes;
            public int occurrences;

            SkillMoment(Skill skill, Project project, String comment){
                this.points = skill.getPoints();
                this.bruttoPoints = skill.getBruttoPoints();
                this.date = new Date();
                this.project = project;
                this.comment = comment;
                this.reqPoints =  skill.getReqPoints();
                this.minutes = skill.getMinutes();
                this.occurrences = skill.getOccurrences();
            }
        }

    }
}
