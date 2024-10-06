package com.example.entity;

import javafx.scene.control.Alert;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AccountManager {

    private List<Account> accounts;

    // Constructor đọc danh sách tài khoản từ file
    public AccountManager(String filePath) {
        accounts = new ArrayList<>();
        loadAccountsFromFile(filePath);
    }

    // Phương thức đọc danh sách tài khoản từ file
    private void loadAccountsFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Read from file: " + line);
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    String username = parts[0];
                    String password = parts[1];
                    // System.out.println("UserName: " + username + ", Password: " + password);
                    accounts.add(new Account(username, password));
                }
            }
            for (Account acc : accounts) {
                System.out.println("UserName: " + acc.getUsername() + ";" + "Password: " + acc.getPassword());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Phương thức kiểm tra thông tin đăng nhập
    public boolean checkLogin(String username, String password) {
        for (Account account : accounts) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                // Đăng nhập thành công
                showAlert(Alert.AlertType.INFORMATION, "Đăng Nhập Thành Công", "Bạn đã đăng nhập thành công.");
                return true;
            } else {
                // Đăng nhập thất bại
                showAlert(Alert.AlertType.ERROR, "Đăng Nhập Thất Bại",
                        "Tên đăng nhập hoặc mật khẩu không chính xác!");
                return false;
            }
        }
        return false;
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
