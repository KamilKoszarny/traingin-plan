package controller;

import main.ReqLvl;
import main.Skill;
import main.SkillCalculator;
import main.SkillsManager;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.util.Callback;

public class PaneController {

    private SkillsManager skillsManager;

    @FXML
    private TreeTableView<SkillItem> treeTableView;
    @FXML
    private TreeTableColumn<SkillItem, String> skillColumn;
    @FXML
    private TreeTableColumn<SkillItem, Number> pointsColumn;
    @FXML
    private TreeTableColumn<SkillItem, Number> reqPointsColumn;
    @FXML
    private TreeTableColumn<SkillItem, StackPane> progressColumn;
    @FXML
    private TreeTableColumn<SkillItem, HBox> effortColumn;
    @FXML
    private TreeTableColumn<SkillItem, HBox> minutesColumn;
    @FXML
    private RadioButton juniorRadioButton, midRadioButton, seniorRadioButton, architectRadioButton;
    @FXML
    private Button activityButton;
    @FXML
    private Label hoursLabel;

    public PaneController(){
    }

    @FXML
    void initialize(){
        activityButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(activityButton.getText().equals("Add activity")) {
                    activityButton.setText("Confirm");
                    pointsColumn.setVisible(false);
                    reqPointsColumn.setVisible(false);
                    progressColumn.setVisible(false);
                    effortColumn.setVisible(true);
                    minutesColumn.setVisible(true);
                } else {
                    activityButton.setText("Add activity");
                    pointsColumn.setVisible(true);
                    reqPointsColumn.setVisible(true);
                    progressColumn.setVisible(true);
                    effortColumn.setVisible(false);
                    minutesColumn.setVisible(false);

                    addActivity();
                }
            }
        });



        final ToggleGroup levelToggleGroup = new ToggleGroup();
        juniorRadioButton.setToggleGroup(levelToggleGroup);
        midRadioButton.setToggleGroup(levelToggleGroup);
        seniorRadioButton.setToggleGroup(levelToggleGroup);
        architectRadioButton.setToggleGroup(levelToggleGroup);

        juniorRadioButton.setUserData(ReqLvl.JUNIOR);
        midRadioButton.setUserData(ReqLvl.MID);
        seniorRadioButton.setUserData(ReqLvl.SENIOR);
        architectRadioButton.setUserData(ReqLvl.ARCHITECT);

        juniorRadioButton.setSelected(true);
        String color = ReqLvl.JUNIOR.getColor().toString();
        String fxColor = color.substring(2, color.length());
        reqPointsColumn.setStyle("-fx-text-fill: #" + fxColor);

        levelToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (levelToggleGroup.getSelectedToggle() != null){
                    ReqLvl choosenReqLvl = (ReqLvl)levelToggleGroup.getSelectedToggle().getUserData();
                    SkillItem.reqLvl = choosenReqLvl;
                    String color = choosenReqLvl.getColor().toString();
                    String fxColor = color.substring(2, color.length());
                    reqPointsColumn.setStyle("-fx-text-fill: #" + fxColor);
                    treeTableView.refresh();
                }
            }
        });



        TreeItem<SkillItem> treeRoot = SkillItemsGenerator.generateRootAndItems();
        treeTableView.setRoot(treeRoot);

        skillColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<SkillItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<SkillItem, String> param) {
                return param.getValue().getValue().getNameProperty();
            }
        });
        pointsColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<SkillItem, Number>, ObservableValue<Number>>() {
            @Override
            public ObservableValue<Number> call(TreeTableColumn.CellDataFeatures<SkillItem, Number> param) {
                return param.getValue().getValue().getPointsProperty();
            }
        });
        reqPointsColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<SkillItem, Number>, ObservableValue<Number>>() {
            @Override
            public ObservableValue<Number> call(TreeTableColumn.CellDataFeatures<SkillItem, Number> param) {
                return param.getValue().getValue().getReqPointsPropertyForLevel(SkillItem.reqLvl);
            }
        });
        progressColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<SkillItem, StackPane>, ObservableValue<StackPane>>() {
            @Override
            public ObservableValue<StackPane> call(TreeTableColumn.CellDataFeatures<SkillItem, StackPane> param) {
                SimpleDoubleProperty heightProperty = new SimpleDoubleProperty(15.7);
                ReadOnlyDoubleProperty widthProperty = progressColumn.widthProperty();

                StackPane stackPane = new StackPane();
                stackPane.prefWidthProperty().bind(widthProperty);
                stackPane.prefHeightProperty().bind(heightProperty);

                ProgressBar progressBar = new ProgressBar(param.getValue().getValue().getPointsInPercent());
                progressBar.prefWidthProperty().bind(widthProperty);

                stackPane.getChildren().add(progressBar);
                for (ReqLvl reqLvl: ReqLvl.values()) {
                    Line line = new Line( 0, 0, 0, stackPane.getPrefHeight());
                    line.setTranslateX((param.getValue().getValue().getReqPointsForLevel(reqLvl)-50)/100. * widthProperty.getValue() - 3);
                    line.setStroke(reqLvl.getColor());
                    if (reqLvl == SkillItem.reqLvl)
                        line.setStrokeWidth(3);
                    stackPane.getChildren().add(line);
                }

                return new ReadOnlyObjectWrapper<>(stackPane);
            }
        });
        minutesColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<SkillItem, HBox>, ObservableValue<HBox>>() {
            @Override
            public ObservableValue<HBox> call(TreeTableColumn.CellDataFeatures<SkillItem, HBox> param) {
                return param.getValue().getValue().getMinutesHBoxWrapper();
            }
        });
        effortColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<SkillItem, HBox>, ObservableValue<HBox>>() {
            @Override
            public ObservableValue<HBox> call(TreeTableColumn.CellDataFeatures<SkillItem, HBox> param) {
                return param.getValue().getValue().getEffortHBoxWrapper();
            }
        });

        skillColumn.setResizable(false);
        pointsColumn.setResizable(false);
        reqPointsColumn.setResizable(false);
        progressColumn.setResizable(false);
        effortColumn.setResizable(false);

        progressColumn.setSortable(false);

        effortColumn.setVisible(false);
        minutesColumn.setVisible(false);
    }

    private void addActivity(){
        TextField minutesTextField;
        for (TreeItem<SkillItem> treeItem: treeTableView.getRoot().getChildren()) {
            increaseSkill(treeItem);
            minutesTextField = (TextField)treeItem.getValue().getMinutesHBoxWrapper().get().getUserData();
            minutesTextField.setText("");
            for (TreeItem<SkillItem> subTreeItem : treeItem.getChildren()) {
                increaseSkill(subTreeItem);
                minutesTextField = (TextField)treeItem.getValue().getMinutesHBoxWrapper().get().getUserData();
                minutesTextField.setText("");
                for (TreeItem<SkillItem> subsubTreeItem : subTreeItem.getChildren()) {
                    increaseSkill(subsubTreeItem);
                    minutesTextField = (TextField)treeItem.getValue().getMinutesHBoxWrapper().get().getUserData();
                    minutesTextField.setText("");
                    for (TreeItem<SkillItem> subsubsubTreeItem : subsubTreeItem.getChildren()) {
                        increaseSkill(subsubsubTreeItem);
                        minutesTextField = (TextField)treeItem.getValue().getMinutesHBoxWrapper().get().getUserData();
                        minutesTextField.setText("");
                        for (TreeItem<SkillItem> subsubsubsubTreeItem : subsubsubTreeItem.getChildren()) {
                            increaseSkill(subsubsubsubTreeItem);
                            minutesTextField = (TextField)treeItem.getValue().getMinutesHBoxWrapper().get().getUserData();
                            minutesTextField.setText("");
                        }
                    }
                }
            }
        }
    }


    private void increaseSkill(TreeItem<SkillItem> treeItem){
        Skill skill = treeItem.getValue().getSkill();
        TextField minutesTextField = (TextField)treeItem.getValue().getMinutesHBoxWrapper().get().getUserData();
        TextField effortTextField = (TextField)treeItem.getValue().getEffortHBoxWrapper().get().getUserData();

        if (!minutesTextField.getText().isEmpty()) {
            double minutes = Double.parseDouble(minutesTextField.getText());
            double effort = 100;
            if(!effortTextField.getText().isEmpty())
                effort = Double.parseDouble(effortTextField.getText());

            double effectiveMinutes = minutes * effort / 100.;
            double points = SkillCalculator.pointsByMinutes(effectiveMinutes);
            skillsManager.increaseSkill(skill, points);

            System.out.println(skill.getName());
            System.out.println(points);
        }
    }

    private void addTimeToLabel(){
        double hours = 0;


        hoursLabel.setText("Time: " + Math.round(hours*100.)/100. + " hours");
    }

    public void setSkillsManager(SkillsManager skillsManager) {
        this.skillsManager = skillsManager;
    }
}
