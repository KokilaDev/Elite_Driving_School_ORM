package lk.ijse.elite_driving_school_orm.model;

import lk.ijse.elite_driving_school_orm.db.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseModel {
//    public static List<String> getAllCourseNames() {
//        List<String> list = new ArrayList<>();
//        try (Connection con = DBConnection.getInstance().getConnection();
//             Statement st = con.createStatement();
//             ResultSet rs = st.executeQuery("SELECT name FROM course")) {
//            while (rs.next()) {
//                list.add(rs.getString("name"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//
//    public static List<String> getCoursesForStudent(String studentId) {
//        List<String> list = new ArrayList<>();
//        try (Connection con = DBConnection.getInstance().getConnection();
//             PreparedStatement ps = con.prepareStatement(
//                     "SELECT c.name FROM student_course sc JOIN course c ON sc.course_id=c.id WHERE sc.student_id=?")) {
//            ps.setString(1, studentId);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                list.add(rs.getString(1));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//
//    public static boolean updateStudentCourses(String studentId, List<String> courses) {
//        try (Connection con = DBConnection.getInstance().getConnection()) {
//            con.setAutoCommit(false);
//
//            try (PreparedStatement del = con.prepareStatement("DELETE FROM student_course WHERE student_id=?")) {
//                del.setString(1, studentId);
//                del.executeUpdate();
//            }
//
//            try (PreparedStatement ins = con.prepareStatement(
//                    "INSERT INTO student_course (student_id, course_id) " +
//                            "SELECT ?, id FROM course WHERE name=?")) {
//                for (String courseName : courses) {
//                    ins.setString(1, studentId);
//                    ins.setString(2, courseName);
//                    ins.addBatch();
//                }
//                ins.executeBatch();
//            }
//
//            con.commit();
//            return true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            try {
//                DBConnection.getInstance().getConnection().rollback();
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//            return false;
//        } finally {
//            try {
//                DBConnection.getInstance().getConnection().setAutoCommit(true);
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//        }
//    }

}
