package controller;

import main.ReqLvl;
import main.Skill;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;


class SkillItem {

    public static ReqLvl reqLvl = ReqLvl.JUNIOR;

    private Skill skill;
    private HBox minutesHBox = new HBox();
    private HBox effortHBox = new HBox();


    SkillItem(Skill skill){
        this.skill = skill;

        TextField minutesTextField = new TextField();
        minutesTextField.setPrefColumnCount(2);
        minutesTextField.setFont(new Font(9));

//        minutesTextField.setOnKeyReleased(e -> {
//            addTimeToLabel();
//            System.out.println("bkl");
//        });

        minutesHBox.setUserData(minutesTextField);
        minutesHBox.getChildren().add(minutesTextField);
        minutesHBox.getChildren().add(new Label("min"));


        TextField effortTextField = new TextField();
        effortTextField.setPrefColumnCount(2);
        effortTextField.setFont(new Font(9));

        effortHBox.setUserData(effortTextField);
        effortHBox.getChildren().add(effortTextField);
        effortHBox.getChildren().add(new Label("%"));
    }

    public Skill getSkill() {
        return skill;
    }

    public SimpleStringProperty getNameProperty() {
        return new SimpleStringProperty(skill.getName());
    }

    public SimpleDoubleProperty getPointsProperty() {
        return new SimpleDoubleProperty(Math.round(skill.getPoints() * 100.) / 100.);
    }

    public SimpleDoubleProperty getReqPointsPropertyForLevel(ReqLvl reqLvl) {
        return new SimpleDoubleProperty(skill.getReqPoints()[reqLvl.ordinal()]);
    }

    public double getPointsInPercent(){
        return skill.getPoints() / 100.;
    }

    public double getReqPointsForLevel(ReqLvl reqLvl){
        return skill.getReqPointsForLevel(reqLvl);
    }

    public ReadOnlyObjectWrapper<HBox> getMinutesHBoxWrapper(){
        return new ReadOnlyObjectWrapper<>(minutesHBox);
    }

    public ReadOnlyObjectWrapper<HBox> getEffortHBoxWrapper(){
        return new ReadOnlyObjectWrapper<>(effortHBox);
    }
}