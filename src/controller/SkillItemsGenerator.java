package controller;

import main.Skill;
import javafx.scene.control.TreeItem;

import java.util.Iterator;
import java.util.Set;

public class SkillItemsGenerator {

    Set<Skill> skills;

    SkillItemsGenerator(Set<Skill> skills){
        this.skills = skills;
    }

    public TreeItem<SkillItem> generateRootAndItems(){
        Skill mainSkill;
        SkillItem mainSkillItem, skillItem;
        TreeItem<SkillItem> treeItem = null, treeRoot = null, subTreeItem = null, subsubTreeItem = null, subsubsubTreeItem = null;

        Iterator<Skill> it = skills.iterator();
        mainSkill = it.next();
        mainSkillItem = new SkillItem(mainSkill);
        treeRoot = new TreeItem<>(mainSkillItem);

        for (Skill skill = mainSkill; it.hasNext();){
            skill = it.next();
            if (skill.getLayer() == 1) {
                mainSkillItem = new SkillItem(skill);
                treeItem = new TreeItem<>(mainSkillItem);
                treeRoot.getChildren().add(treeItem);
            } else if (skill.getLayer() == 2){
                skillItem = new SkillItem(skill);
                subTreeItem = new TreeItem<>(skillItem);
                treeItem.getChildren().add(subTreeItem);
            } else if (skill.getLayer() == 3){
                skillItem = new SkillItem(skill);
                subsubTreeItem = new TreeItem<>(skillItem);
                subTreeItem.getChildren().add(subsubTreeItem);
            } else if (skill.getLayer() == 4){
                skillItem = new SkillItem(skill);
                subsubsubTreeItem = new TreeItem<>(skillItem);
                subsubTreeItem.getChildren().add(subsubsubTreeItem);
            }

        }



/*
        for (Skill skill: mainSkill.getSubSkills()) {
            mainSkillItem = new SkillItem(skill);
            treeItem = new TreeItem<>(mainSkillItem);
            for (Skill subSkill: skill.getSubSkills()) {
                mainSkillItem = new SkillItem(subSkill);
                subTreeItem = new TreeItem<>(mainSkillItem);

                for (Skill subsubSkill: subSkill.getSubSkills()) {
                    mainSkillItem = new SkillItem(subsubSkill);
                    subsubTreeItem = new TreeItem<>(mainSkillItem);

                    for (Skill subsubsubSkill: subsubSkill.getSubSkills()) {
                        mainSkillItem = new SkillItem(subsubsubSkill);
                        subsubsubTreeItem = new TreeItem<>(mainSkillItem);
                        subsubTreeItem.getChildren().add(subsubsubTreeItem);
                    }
                    subTreeItem.getChildren().add(subsubTreeItem);
                }
                treeItem.getChildren().add(subTreeItem);
            }
            if (skill.getLayer() == 1)
                treeRoot.getChildren().add(treeItem);
        }
        */
        return treeRoot;
    }
}

//System.out.println(skill.getName() + "   " + subSkill.getName());