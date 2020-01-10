package com.sebastian.teacherView.dbAccess;

import com.sebastian.teacherView.data.ClassSchool;
import com.sebastian.teacherView.data.Teacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.List;

public class TeacherDbAccess {
    private Connection conn;
    private static final String teacherTable = "teachers";
    private static final String teacherClassesTable = "teacher_classes";
    private static final String classesTable = "classes";
    PreparedStatement pstmnt;
    private static TeacherDbAccess instance;

    static {
        try {
            instance = new TeacherDbAccess();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private TeacherDbAccess()
            throws SQLException, ClassNotFoundException {

        // Class.forName("org.hsqldb.jdbc.JDBCDriver" );

        //STEP 2: Check if JDBC driver is available
        Class.forName("com.mysql.cj.jdbc.Driver");
        //STEP 3: Open a connection
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/CR6" +
                        "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                "root",
                "");

        // we will use this connection to write to a file
        conn.setAutoCommit(true);
        conn.setReadOnly(false);
    }

    public void closeDb() throws SQLException {
        conn.close();
    }

    public List<Teacher> getAllTeacherRows() throws SQLException {

        String sql = "SELECT * FROM " + teacherTable + " ORDER BY teacher_id";
        pstmnt = conn.prepareStatement(sql);
        ResultSet rs = pstmnt.executeQuery();
        List<Teacher> list = FXCollections.observableArrayList();

        while (rs.next()) {
            int i = rs.getInt("teacher_id");
            String name = rs.getString("teacher_name");
            String surname = rs.getString("teacher_surname");
            String email = rs.getString("teacher_email");

            list.add(new Teacher(i, name, surname, email));
        }

        pstmnt.close(); // also closes related result set
        return list;
    }


    public ObservableList<ClassSchool> getAllClassesRows(int teacher_id) throws SQLException {

        String sql = "SELECT classes.class_id, classes.class_name FROM " + teacherTable +
                " INNER JOIN " + teacherClassesTable + " ON teachers.teacher_id = teacher_classes.fk_teacher_id" +
                " INNER JOIN " + classesTable + " ON classes.class_id = teacher_classes.fk_class_id" +
                " WHERE teacher_id = " + teacher_id;

        pstmnt = conn.prepareStatement(sql);
        ResultSet rs = pstmnt.executeQuery();
        ObservableList<ClassSchool> list = FXCollections.observableArrayList();

        while (rs.next()) {
            int i = rs.getInt("class_id");
            String name = rs.getString("class_name");

            list.add(new ClassSchool(i, name));
        }

        pstmnt.close(); // also closes related result set
        return list;
    }


    public static TeacherDbAccess getInstance() {
        return instance;
    }
}
