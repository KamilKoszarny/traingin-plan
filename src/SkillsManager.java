import java.util.List;

public class SkillsManager {

    private final int SKILLS_COUNT = 25;
    private final int HEADER_ROW_INDEX = 1;
    private final int MAX_ROWS = 100;
    private final String skillsFilePath = "../Plan.xlsx";
    private final String skillSheetName = "Job requirements";

    private List<Skill> skills;
    private SkillsImporter skillsImporter;




    public SkillsManager(){
        System.out.println("SkillsManager created");
        skillsImporter = new SkillsImporter(skillsFilePath, skillSheetName, HEADER_ROW_INDEX);
        skills = skillsImporter.importSkills(SKILLS_COUNT, MAX_ROWS);
    }


    public void standardizeSkills(){
        for (Skill skill: skills) {

        }
    }

    public void rateSkills(){

    }

    public void showSkills(){
        for (Skill skill: skills) {
            System.out.println(skill.getRank() + ". " + skill.getName());
        }
    }
}
