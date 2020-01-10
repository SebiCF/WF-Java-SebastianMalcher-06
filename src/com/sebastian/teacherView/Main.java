package com.sebastian.teacherView;

import com.sebastian.teacherView.dbAccess.TeacherDbAccess;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("teacherView.fxml"));
        primaryStage.setTitle("Hello Teacher");
        primaryStage.setScene(new Scene(root, 900, 350));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {

        try {
            TeacherDbAccess.getInstance().getAllTeacherRows();
        } catch (Exception e) {
            displayException(e);
        }
    }

    @Override
    public void stop() {

        try {
            TeacherDbAccess.getInstance().closeDb();
        } catch (Exception e) {
            displayException(e);
        }
    }

    static void displayException(Exception e) {

        System.out.println("###### Exception ######");
        e.printStackTrace();
        System.exit(0);
    }
}
