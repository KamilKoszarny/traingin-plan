package main;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class SkillsManager {

    private Set<Skill> skills = new Skills();
    SkillsLoadSaver skillsLoadSaver = new SkillsLoadSaver();

    public SkillsManager(){


//        createSkillList();
        skills = skillsLoadSaver.loadFromFile();
        setSubSkills();

        SkillsReqImporter skillsReqImporter = new SkillsReqImporter(skills);

        ReqLvl[] reqLvls = ReqLvl.values();
        for (ReqLvl reqLvl: reqLvls) {
            skills = skillsReqImporter.importSkillsReq(reqLvl);
        }

//        increaseSkill(Skill.BASICS, 20);
    }

    private void createSkillList(){
            skills.addAll(Arrays.asList(Skill.values()));
            setSubSkills();
        }

    private void setSubSkills(){
                for (Skill skill: skills) {
                    for (Skill subSkill: skills) {
                        if (subSkill.getSuperSkill() == skill) {
                            skill.addSubSkill(subSkill);
                        }
                    }
                }

            }

    public void increaseSkill(Skill skill, double points){
        skill.addPoints(points);
    }


    public void showSkills(){
        String format = "%-30s%10s%10s%10s%n";
        System.out.printf(format, "Skill", "reqPoints", "occur.", "points");
        for (Skill skill: skills) {
            format = "%-30s%10.0f%10s%10.0f%n";
            String spaces = generateLayerSpaces(skill);
            System.out.printf(format, spaces + skill.getName(), skill.getReqPointsForLevel(ReqLvl.ARCHITECT), skill.getOccurrences(), skill.getPoints());
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
