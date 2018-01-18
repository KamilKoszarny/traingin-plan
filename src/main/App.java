package main;

import controller.PaneController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class App extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        SkillsManager skillsManager = new SkillsManager();
//        skillsManager.showSkills();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(this.getClass().getResource("/fxml/MainWindow.fxml"));

        StackPane stackPane = fxmlLoader.load();
        Scene scene = new Scene(stackPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Training Plan");
        primaryStage.show();

        PaneController paneController = fxmlLoader.getController();
        paneController.setSkillsManager(skillsManager);
    }

    public static void main(String[] args) {
//        System.out.println(SkillCalculator.pointsByMinutes(480));
        disableWarning();
        launch(args);
    }


    //Hide warning “Illegal reflective access” in java 9
    private static void disableWarning() {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            Unsafe u = (Unsafe) theUnsafe.get(null);

            Class cls = Class.forName("jdk.internal.module.IllegalAccessLogger");
            Field logger = cls.getDeclaredField("logger");
            u.putObjectVolatile(cls, u.staticFieldOffset(logger), null);
        } catch (Exception e) {
            // ignore
        }
    }

}