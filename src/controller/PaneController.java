package controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import skills.Project;
import skills.ReqLvl;
import skills.Skill;
import skills.SkillCalculator;
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

import java.text.SimpleDateFormat;
import java.util.*;

public class PaneController {

    private SkillsManager skillsManager;
    private Set<Skill> skills;

    @FXML
    private TreeTableView<SkillItem> treeTableView;
    @FXML
    private TreeTableColumn<SkillItem, String> skillColumn;
    @FXML
    private TreeTableColumn<SkillItem, Number> pointsColumn;
    @FXML
    private TreeTableColumn<SkillItem, Number> percReqColumn;
    @FXML
    private TreeTableColumn<SkillItem, StackPane> progressColumn;
    @FXML
    private TreeTableColumn<SkillItem, Number> reqPointsColumn;
    @FXML
    private TreeTableColumn<SkillItem, HBox> minutesColumn;
    @FXML
    private TreeTableColumn<SkillItem, HBox> effortColumn;
    @FXML
    private TreeTableColumn<SkillItem, TextField> commentColumn;

    @FXML
    private Label dateLabel;
    @FXML
    private Button activityButton;
    @FXML
    private Text requirementsLabel;
    @FXML
    private RadioButton juniorRadioButton, midRadioButton, seniorRadioButton, architectRadioButton;

    @FXML
    private Text projectText;
    @FXML
    private ComboBox projectComboBox;
    @FXML
    private Text newProjectText;
    @FXML
    private TextField newProjectTextField;
    @FXML
    private Button addProjectButton;
    @FXML
    private Label activityTimeLabel;
    @FXML
    private Button activityCancelButton;



    public PaneController(){
    }

    @FXML
    void initialize(){
        setLook(true);

        activityButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(activityButton.getText().equals("Add activity")) {
                    setLook(false);
                } else {
                    setLook(true);
                    confirmActivity();
                }
            }
        });
        activityCancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setLook(true);
            }
        });

        newProjectTextField.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                addProjectButton.setVisible(!newProjectTextField.getText().isEmpty());
            }
        });
        addProjectButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Project project = null;
                boolean projectExisits = false;
                for (Project existingProject : Project.projects) {
                    if (newProjectTextField.getText().equalsIgnoreCase(existingProject.getName())){
                        project = existingProject;
                        projectExisits = true;
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("Project exists");
                        alert.setContentText("Project with this name already exists. I am not adding but selecting this");
                        alert.showAndWait();
                    }
                }
                if(!projectExisits){
                    project = new Project(newProjectTextField.getText());
                    Project.projects.add(project);
                    setItemsInProjectComboBox();
                }

                newProjectTextField.setText("");
                projectComboBox.getSelectionModel().select(project.getName());
            }
        });

        initializeTreeTable();

        initializeClock();

        initializeRequirementsToggleGroup();

        initializeProjectsComboBox();
    }

    private void initializeTreeTable(){
        SkillItemsGenerator sIG = new SkillItemsGenerator(skills);
        TreeItem<SkillItem> treeRoot = sIG.generateRootAndItems();
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
        percReqColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<SkillItem, Number>, ObservableValue<Number>>() {
            @Override
            public ObservableValue<Number> call(TreeTableColumn.CellDataFeatures<SkillItem, Number> param) {
                double points =  param.getValue().getValue().getPointsProperty().get();
                double reqPoints =  param.getValue().getValue().getReqPointsPropertyForLevel(SkillItem.reqLvl).get();
                int percent = (int)(points/reqPoints * 100.);
                return new SimpleIntegerProperty(percent);
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

                ProgressBar progressBar = new ProgressBar(param.getValue().getValue().getPointsFactor()*.95 + .025);
                progressBar.prefWidthProperty().bind(widthProperty);

                stackPane.getChildren().add(progressBar);
                for (ReqLvl reqLvl: ReqLvl.values()) {
                    Line line = new Line( 0, 0, 0, stackPane.getPrefHeight());
                    line.setTranslateX((param.getValue().getValue().getReqPointsForLevel(reqLvl)-50)/100. * widthProperty.getValue()*.95 + 0.025);
                    line.setStroke(reqLvl.getColor());
                    if (reqLvl == SkillItem.reqLvl)
                        line.setStrokeWidth(3);
                    stackPane.getChildren().add(line);
                }

                return new ReadOnlyObjectWrapper<>(stackPane);
            }
        });
        reqPointsColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<SkillItem, Number>, ObservableValue<Number>>() {
            @Override
            public ObservableValue<Number> call(TreeTableColumn.CellDataFeatures<SkillItem, Number> param) {
                return param.getValue().getValue().getReqPointsPropertyForLevel(SkillItem.reqLvl);
            }
        });
        minutesColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<SkillItem, HBox>, ObservableValue<HBox>>() {
            @Override
            public ObservableValue<HBox> call(TreeTableColumn.CellDataFeatures<SkillItem, HBox> param) {
                if (param.getValue().getValue().getSkill() == Skill.JAVA)
                    return null;
                else {
                    NumberTextField minutesTextField = (NumberTextField)param.getValue().getValue().getMinutesHBoxWrapper().get().getUserData();
                    minutesTextField.setOnKeyReleased(new EventHandler<KeyEvent>() {
                        @Override
                        public void handle(KeyEvent event) {
                            addTimeToLabel();
                        }
                    });
                    return param.getValue().getValue().getMinutesHBoxWrapper();
                }
            }
        });
        effortColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<SkillItem, HBox>, ObservableValue<HBox>>() {
            @Override
            public ObservableValue<HBox> call(TreeTableColumn.CellDataFeatures<SkillItem, HBox> param) {
                if (param.getValue().getValue().getSkill() == Skill.JAVA)
                    return null;
                else
                    return param.getValue().getValue().getEffortHBoxWrapper();
            }
        });
        commentColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<SkillItem, TextField>, ObservableValue<TextField>>(){
            @Override
            public ObservableValue<TextField> call(TreeTableColumn.CellDataFeatures<SkillItem, TextField> param) {
                return param.getValue().getValue().getCommentTextFieldWrapper();
            }
        });

        skillColumn.setResizable(false);
        pointsColumn.setResizable(false);
        reqPointsColumn.setResizable(false);
        progressColumn.setResizable(false);
        effortColumn.setResizable(false);

        progressColumn.setSortable(false);

        commentColumn.getStyleClass().add("comment-column-style");
    }

    private void setLook(boolean main){
        pointsColumn.setVisible(main);
        percReqColumn.setVisible(main);
        progressColumn.setVisible(main);
        reqPointsColumn.setVisible(main);

        requirementsLabel.setVisible(main);
        juniorRadioButton.setVisible(main);
        midRadioButton.setVisible(main);
        seniorRadioButton.setVisible(main);
        architectRadioButton.setVisible(main);

        if (main) {
            activityButton.setVisible(true);
            activityButton.setText("Add activity");
            activityButton.setTranslateY(0);
        } else {
            activityButton.setVisible(false);
            activityButton.setText("Confirm");
            activityButton.setTranslateY(165);
        }


        if (!main)
            treeTableView.getRoot().setExpanded(true);
        effortColumn.setVisible(!main);
        minutesColumn.setVisible(!main);
        commentColumn.setVisible(!main);

        projectText.setVisible(!main);
        activityTimeLabel.setVisible(!main);
        newProjectText.setVisible(!main);
        newProjectTextField.setVisible(!main);
        addProjectButton.setVisible(false);
        projectComboBox.setVisible(!main);
        if (!main)
            initializeProjectsComboBox();
        activityCancelButton.setVisible(!main);
    }

    private void initializeClock(){
        Task dynamicTimeTask = new Task<Void>() {
            @Override
            protected Void call(){
                SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy \nHH:mm:ss");
                while (true) {
                    updateMessage(sdf.format(new Date()));
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        break;
                    }
                }
                return null;
            }
        };
        dateLabel.textProperty().bind(dynamicTimeTask.messageProperty());
        Thread t2 = new Thread(dynamicTimeTask);
        t2.setName("Task Time Updater");
        t2.setDaemon(true);
        t2.start();
    }

    private void initializeRequirementsToggleGroup(){
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
        percReqColumn.setStyle("-fx-text-fill: #" + fxColor);

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
    }

    private void initializeProjectsComboBox(){
        setItemsInProjectComboBox();
        projectComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                activityButton.setVisible(true);
            }
        });
    }
    private void setItemsInProjectComboBox(){
        projectComboBox.setItems(FXCollections.observableArrayList(
                Project.projects.getNamesList()
        ));
    }

    private void confirmActivity(){
        addActivity();
        skillsManager.save();
    }

    private void addActivity(){
        TextField minutesTextField;
        TreeItem<SkillItem> treeItem = treeTableView.getRoot();
        while (nextTreeItem(treeItem) != null) {
            treeItem = nextTreeItem(treeItem);
            increaseSkill(treeItem);
            minutesTextField = (TextField) treeItem.getValue().getMinutesHBoxWrapper().get().getUserData();
            minutesTextField.setText("");
//            System.out.println(treeItem.getValue().getSkill().getName());
        }
    }

    private TreeItem<SkillItem> nextTreeItem(TreeItem<SkillItem> treeItem){
        return nextTreeItem(treeItem, true);
    }

    private TreeItem<SkillItem> nextTreeItem(TreeItem<SkillItem> treeItem, boolean childSearch){
        if (treeItem.getChildren().size() != 0 && childSearch)
            return treeItem.getChildren().get(0);
        else {
            TreeItem<SkillItem> parentItem = treeItem.getParent();
            if (treeItem == treeTableView.getRoot())
                return null;
            for (int i = 0; i < parentItem.getChildren().size() - 1; i++) {
                if (treeItem == parentItem.getChildren().get(i))
                    return parentItem.getChildren().get(i + 1);
            }
            if (treeItem == parentItem.getChildren().get(parentItem.getChildren().size() - 1))
                return nextTreeItem(parentItem, false);
        }
        return null;
    }

    private void increaseSkill(TreeItem<SkillItem> treeItem){
        Skill skill = treeItem.getValue().getSkill();
        TextField minutesTextField = (TextField)treeItem.getValue().getMinutesHBoxWrapper().get().getUserData();
        TextField effortTextField = (TextField)treeItem.getValue().getEffortHBoxWrapper().get().getUserData();

        if (!minutesTextField.getText().isEmpty()) {
            int minutes = Integer.parseInt(minutesTextField.getText());
            double effectiveMinutes = readEffectiveMinutes(minutes, effortTextField);
            double points = SkillCalculator.pointsByMinutes(effectiveMinutes);
            Project project = Project.getProjectByName((String)projectComboBox.getValue());
            String comment = treeItem.getValue().getCommentTextFieldWrapper().get().getText();

            skillsManager.increaseSkill(skill, points, minutes, project, comment);

            System.out.println(skill.getName());
            System.out.println(points + " points added");
        }
    }

    private double readEffectiveMinutes(int minutes, TextField effortTextField){
        double effort = 100;
        if(!effortTextField.getText().isEmpty())
            effort = Double.parseDouble(effortTextField.getText());

        return minutes * effort / 100.;
    }


    private void addTimeToLabel(){
        int hours = 0, minutes = 0;
        double skillMinutes;
        TextField minutesTextField;
        TreeItem<SkillItem> treeItem = treeTableView.getRoot();
        while (nextTreeItem(treeItem) != null) {
            treeItem = nextTreeItem(treeItem);
            minutesTextField = (TextField)treeItem.getValue().getMinutesHBoxWrapper().get().getUserData();
            if (!minutesTextField.getText().isEmpty()) {
                skillMinutes = Integer.parseInt(minutesTextField.getText());
                minutes += Math.round(skillMinutes);
            }
        }
        hours = (int)(minutes/60.);
        minutes = minutes%60;

        activityTimeLabel.setText("Time on activity:\n" + hours + " h " + minutes + " min");
    }

    public void setSkillsManager(SkillsManager skillsManager) {
        this.skillsManager = skillsManager;
        this.skills = skillsManager.getSkills();
    }

}
