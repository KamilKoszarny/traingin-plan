package main;

import skills.*;

import java.text.SimpleDateFormat;
import java.util.Set;

public class SkillsManager {

    private SkillsSet skills = new SkillsSet();
    SkillsReqImporter skillsReqImporter = new SkillsReqImporter(skills);
    private SkillsLoadSaver skillsLoadSaver = new SkillsLoadSaver();

    SkillsManager(){
        System.out.println(Project.projects.toString());

        if (skillsLoadSaver.fileExists()) {
            skills = skillsLoadSaver.loadFromFile(skills);
            showSkillsHistories();
        }

        if (skillsReqImporter.fileExists())
            skills = skillsReqImporter.importSkillsReq(ReqLvl.values());

        decreaseSkillsByTime();
    }


    public void increaseSkill(Skill skill, double points, int minutes, Project project, String comment){
        skill.increaseSkill(points, minutes, project, comment);
    }

    public void decreaseSkillsByTime(){
        for (Skill skill: skills) {
            skill.outdatePoints();
        }
    }


    public void showSkills(){
        String format = "%-30s%10s%10s%10s%n";
        System.out.printf(format, "Skill", "reqPoints", "occur.", "points");
        format = "%-30s%10.0f%10s%10.0f%n";
        String spaces;
        for (Skill skill: skills) {
            spaces = generateLayerSpaces(skill);
            System.out.printf(format, spaces + skill.getName(),
                    skill.getReqPointsForLevel(ReqLvl.ARCHITECT), skill.getOccurrences(), skill.getPoints());
        }
    }

    public void showSkillsHistories(){
        System.out.println("\nSkills histories:");
        String format = "%-30s%17s%17s%17s";
        System.out.printf(format, "Skill", "date1", "date2", "...");
        String spaces;
        SimpleDateFormat dateFormat = new SimpleDateFormat(" dd MMM, HH:mm:ss");
        for (Skill skill: skills) {
            if (skill.getSkillHistory().size() > 0) {
                spaces = generateLayerSpaces(skill);
                format = "%n%-30s";
                System.out.printf(format, spaces + skill.getName());
                for (int i = 0; i < skill.getSkillHistory().size(); i++) {
                    System.out.print(dateFormat.format(skill.getSkillHistory().get(i).date));
                }
                format = "%n%-30s";
                System.out.printf(format, "");
                for (int i = 0; i < skill.getSkillHistory().size(); i++) {
                    format = "%12s%5.2f";
                    System.out.printf(format, "points:", skill.getSkillHistory().get(i).points);
                }
                format = "%n%-30s";
                System.out.printf(format, "");
                for (int i = 0; i < skill.getSkillHistory().size(); i++) {
                    format = "%12s%5.2f";
                    System.out.printf(format, "brutto:", skill.getSkillHistory().get(i).bruttoPoints);
                }
                format = "%n%-30s";
                System.out.printf(format, "");
                for (int i = 0; i < skill.getSkillHistory().size(); i++) {
                    format = "%10s%7d";
                    System.out.printf(format, "minutes:", skill.getSkillHistory().get(i).minutes);
                }
                format = "%n%-30s";
                System.out.printf(format, "");
                for (int i = 0; i < skill.getSkillHistory().size(); i++) {
                    format = "%17s";
                    System.out.printf(format, skill.getSkillHistory().get(i).project.getName());
                }
                format = "%n%-30s";
                System.out.printf(format, "");
                for (int i = 0; i < skill.getSkillHistory().size(); i++) {
                    format = "%17s";
                    System.out.printf(format, skill.getSkillHistory().get(i).comment);
                }
                format = "%n%-30s";
                System.out.printf(format, "");
                for (int i = 0; i < skill.getSkillHistory().size(); i++) {
                    format = "%12s%5.1f";
                    System.out.printf(format, "JuniorReq:", skill.getSkillHistory().get(i).reqPoints[ReqLvl.JUNIOR.ordinal()]);
                }
                format = "%n%-30s";
                System.out.printf(format, "");
                for (int i = 0; i < skill.getSkillHistory().size(); i++) {
                    format = "%12s%5d";
                    System.out.printf(format, "occur:", skill.getSkillHistory().get(i).occurrences);
                }
                System.out.println("");
            }
        }
    }


    private String generateLayerSpaces(Skill skill){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < skill.getLayer(); i++) {
            builder.append(" ");
        }
        return builder.toString();
    }


    public Set<Skill> getSkills() {
        return skills;
    }


    public void save(){
        skillsLoadSaver.saveToFile(skills);
    }
}
