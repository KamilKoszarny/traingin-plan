import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class SkillsManager {



    private Set<Skill> skills = new TreeSet<>();
    private SkillsReqImporter skillsReqImporter;




    public SkillsManager(){
        System.out.println("SkillsManager created");
        createSkillList();
        setSubSkills();
        skillsReqImporter = new SkillsReqImporter(skills);
        skills = skillsReqImporter.importSkillsReq();
        addSubSkillReqPoints();
        addSuperSkillReqPoints();
    }

        private void createSkillList(){
            skills.addAll(Arrays.asList(Skill.values()));
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

        private void addSubSkillReqPoints(){
            System.out.println("addSuperSkillReqPoints");
            for (int layer = 1; layer < 5; layer++) {
                for (Skill skill : skills) {
                    if (skill.getLayer() == layer)
                        skill.addReqPointsBySuperSkill();
                }
            }
        }

        private void addSuperSkillReqPoints(){
            System.out.println("addSuperSkillReqPoints");
            for (int layer = 5; layer > -1; layer--) {
                for (Skill skill : skills) {
                    if (skill.getLayer() == layer)
                        skill.addReqPointsBySubSkills();
                }
            }
        }


    public void standardizeSkills(){
        for (Skill skill: skills) {

        }
    }

    public void rateSkills(){

    }

    public void showSkillsReq(){
        String format = "%-30s%6s%15s%n";
        System.out.printf(format, "Skill", "points", "occurencies");
        for (Skill skill: skills) {
            format = "%-30s%6.0f%15s%n";
            String spaces = generateLayerSpaces(skill);
            //System.out.println(skill.getName() + "\t\t" + skill.getReqPoints() + "points,\t" + skill.getOccurencies() + "occurencies");
            System.out.printf(format, spaces + skill.getName(), skill.getReqPoints(), skill.getOccurencies());
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
