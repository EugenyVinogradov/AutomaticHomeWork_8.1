package ru.netology.dataHelper;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import lombok.Value;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import ru.netology.page.AuthPage;

import java.sql.DriverManager;

public class DataHelper {

    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;

    }

    public static AuthInfo getAuthInfo(AuthPage info) {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    @SneakyThrows
    public static String getVerificationCode() {
        var login = "vasya";
        var verificationCode = "SELECT ac.code from auth_codes ac join users u on u.id = ac.user_id " +
                "where u.login = ? ORDER by created DESC LIMIT 1;";
        var runner = new QueryRunner();
        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/database", "user", "password");
        ) {
            return runner.query(conn, verificationCode, login, new ScalarHandler<>());
        }
    }

    @SneakyThrows
    public static String getStatusUser() {
        var login = "vasya";
        var statusUser = "SELECT u.status from users u where u.login = ?;";
        var runner = new QueryRunner();
        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/database", "user", "password");
        ) {
            return runner.query(conn, statusUser, login, new ScalarHandler<>());
        }
    }

    @SneakyThrows
    public static void CleanDataBase() {
        var runner = new QueryRunner();
        var cleanAuthCode = "DELETE FROM auth_codes;";
        var cleanCards = "DELETE FROM cards;";
        var cleanUsers = "DELETE FROM users;";
        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/database", "user", "password");
        ) {
            runner.update(conn, cleanAuthCode);
            runner.update(conn, cleanCards);
            runner.update(conn, cleanUsers);
        }
    }

    public static String wrongVerificationCode() {
        String wrongVerificationCode = new Faker().internet().password();
        return wrongVerificationCode;
    }

}
