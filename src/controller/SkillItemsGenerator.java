package controller;

import main.Skill;
import javafx.scene.control.TreeItem;

public class SkillItemsGenerator {

    public static TreeItem<SkillItem> generateRootAndItems(){
        SkillItem skillItem;
        TreeItem<SkillItem> treeItem, treeRoot, subTreeItem, subsubTreeItem, subsubsubTreeItem;

        skillItem = new SkillItem(Skill.JAVA);
        treeRoot = new TreeItem<>(skillItem);

        for (Skill skill: Skill.values()) {
            skillItem = new SkillItem(skill);
            treeItem = new TreeItem<>(skillItem);
            for (Skill subSkill: skill.getSubSkills()) {
                skillItem = new SkillItem(subSkill);
                subTreeItem = new TreeItem<>(skillItem);

                for (Skill subsubSkill: subSkill.getSubSkills()) {
                    skillItem = new SkillItem(subsubSkill);
                    subsubTreeItem = new TreeItem<>(skillItem);

                    for (Skill subsubsubSkill: subsubSkill.getSubSkills()) {
                        skillItem = new SkillItem(subsubsubSkill);
                        subsubsubTreeItem = new TreeItem<>(skillItem);
                        subsubTreeItem.getChildren().add(subsubsubTreeItem);
                    }
                    subTreeItem.getChildren().add(subsubTreeItem);
                }
                treeItem.getChildren().add(subTreeItem);
            }
            if (skill.getLayer() == 1)
                treeRoot.getChildren().add(treeItem);
        }
        return treeRoot;
    }
}

//System.out.println(skill.getName() + "   " + subSkill.getName());