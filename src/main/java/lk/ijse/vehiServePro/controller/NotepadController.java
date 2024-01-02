package lk.ijse.vehiServePro.controller;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

    public class NotepadController {

        @FXML
        private TextArea textArea;

        private File currentFile;

        @FXML
        private void handleNew() {
            textArea.clear();
            currentFile = null;
        }

        @FXML
        private void handleOpen() {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open File");
            File selectedFile = fileChooser.showOpenDialog(textArea.getScene().getWindow());
            if (selectedFile != null) {
                try {
                    String content = new String(Files.readAllBytes(Paths.get(selectedFile.getAbsolutePath())));
                    textArea.setText(content);
                    currentFile = selectedFile;
                } catch (IOException e) {
                    showErrorAlert("Error reading file", e.getMessage());
                }
            }
        }

        @FXML
        private void handleSave() {
            if (currentFile == null) {
                handleSaveAs();
            } else {
                saveToFile(currentFile);
            }
        }

        @FXML
        private void handleSaveAs() {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save As");
            File selectedFile = fileChooser.showSaveDialog(textArea.getScene().getWindow());
            if (selectedFile != null) {
                saveToFile(selectedFile);
                currentFile = selectedFile;
            }
        }

        private void saveToFile(File file) {
            try {
                Files.write(Paths.get(file.getAbsolutePath()), textArea.getText().getBytes());
            } catch (IOException e) {
                showErrorAlert("Error saving file", e.getMessage());
            }
        }

        private void showErrorAlert(String title, String message) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }
    }


