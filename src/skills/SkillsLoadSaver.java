package skills;

import java.io.*;
import java.util.Set;

public class SkillsLoadSaver {

    public boolean fileExists(){
        File f = new File("skillsHistories.ser");
        if(f.exists() && !f.isDirectory()) {
            return true;
        }
        return false;
    }

    public void saveToFile(Set<Skill> skills){
        SkillsHistories skillsHistories = new SkillsHistories();
        for (Skill skill: skills) {
            skillsHistories.add(skill.skillHistory);
        }
        FileOutputStream fOutS;
        ObjectOutputStream oOutS;
        try {
            fOutS = new FileOutputStream("skillsHistories.ser");
            oOutS = new ObjectOutputStream(fOutS);
            oOutS.writeObject(skillsHistories);
            fOutS.close();
            oOutS.close();
            System.out.println("skillsHistories saved");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public SkillsSet loadFromFile(SkillsSet skills){
        FileInputStream fInS;
        ObjectInputStream oInS;
        SkillsHistories skillsHistories = null;

        try {
            fInS = new FileInputStream("skillsHistories.ser");
            oInS = new ObjectInputStream(fInS);
        } catch (IOException e) {
            e.printStackTrace();
            return skills;
        }

        try {
            skillsHistories = (SkillsHistories)oInS.readObject();
            oInS.close();
            fInS.close();
            System.out.println("skillsHistories readed");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        for (Skill skill: skills) {
            assert skillsHistories != null;
            for (SkillsHistories.SkillHistory skillHistory: skillsHistories) {
                if (skill == skillHistory.skill && skillHistory.size() != 0) {
                    System.out.println(skill);
                    skill.setSkillHistoryAndPoints(skillHistory);
                    for (SkillsHistories.SkillHistory.SkillMoment moment: skillHistory) {
                        Project.projects.add(moment.project);
                    }
                }
            }
        }

        return skills;
    }
}
