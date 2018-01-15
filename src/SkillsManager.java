import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class SkillsManager {

    private Set<Skill> skills = new TreeSet<>();
    private SkillsReqImporter skillsReqImporter;


    public SkillsManager(){
        createSkillList();
        skillsReqImporter = new SkillsReqImporter(skills);
        gainRequirements(ReqLvl.JUNIOR);
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

        private void gainRequirements(ReqLvl reqLvl){
            skills = skillsReqImporter.importSkillsReq(reqLvl);
            addSubSkillReqPoints();
            addSuperSkillReqPoints();
        }

            private void addSubSkillReqPoints(){
//                System.out.println("addSubSkillReqPoints");
                for (int layer = 1; layer < 5; layer++) {
                    for (Skill skill : skills) {
                        if (skill.getLayer() == layer)
                            skill.addReqPointsBySuperSkill();
                    }
                }
            }

            private void addSuperSkillReqPoints(){
                for (int layer = 5; layer > -1; layer--) {
                    for (Skill skill : skills) {
                        if (skill.getLayer() == layer)
                            skill.addReqPointsBySubSkills();
                    }
                }
            }


    public void showSkillsReq(){
        String format = "%-30s%10s%10s%n";
        System.out.printf(format, "Skill", "reqPoints", "occur.");
        for (Skill skill: skills) {
            format = "%-30s%10.0f%10s%n";
            String spaces = generateLayerSpaces(skill);
            System.out.printf(format, spaces + skill.getName(), skill.getReqPoints(), skill.getOccurrences());
        }
    }

        private String generateLayerSpaces(Skill skill){
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < skill.getLayer(); i++) {
                builder.append(" ");
            }
            return builder.toString();
        }

}
