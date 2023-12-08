module com.example.bankingSystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bankingSystem to javafx.fxml;
    exports com.example.bankingSystem;
    exports com.example.bankingSystem.BankingSystem;
    opens com.example.bankingSystem.BankingSystem to javafx.fxml;
}