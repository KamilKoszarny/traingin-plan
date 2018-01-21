package skills;

import java.util.Arrays;
import java.util.TreeSet;

public class SkillsSet extends TreeSet<Skill>{

    public SkillsSet(){
        createSkillsSet();
    }

    private void createSkillsSet(){
        this.addAll(Arrays.asList(Skill.values()));
        setSubSkills();
    }

    private void setSubSkills(){
        for (Skill skill: this) {
            for (Skill subSkill: this) {
                if (subSkill.getSuperSkill() == skill) {
                    skill.addSubSkill(subSkill);
                }
            }
        }

    }
}