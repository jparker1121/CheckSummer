package application;

import com.sun.tools.javac.comp.Check;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private GridPane gridBox;

    @FXML
    private Button md5sumCopyButton;

    @FXML
    private CheckBox md5sumCheckBox;

    @FXML
    private CheckBox sha1sumCheckBox;

    @FXML
    private CheckBox sha256sumCheckBox;

    @FXML
    private CheckBox sha512sumCheckBox;

    @FXML
    private Button sha1sumCopyButton;

    @FXML
    private Button sha256sumCopyButton;

    @FXML
    private Button sha512sumCopyButton;

    @FXML
    private Button fileButton;

    @FXML
    private TextField fileTextField;

    @FXML
    private TextField md5sumTextField;
    
    @FXML
    private TextField sha1sumTextField;
    
    @FXML
    private TextField sha256sumTextField;
    
    @FXML
    private TextField sha512sumTextField;
    
    @FXML
    private Button resetButton;

    @FXML
    private Button generateButton;

    @FXML
    private Button exportButton;

    @FXML
    private Button exitButton;

    @FXML
    private RadioButton formatStyleRadioButton;

    Model model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        anchorPane.requestFocus();
        model = new Model();
        setBindings();
    }

    @FXML
    void copySelectedChecksum(ActionEvent event) {
        String buttonClicked = ((Button) event.getSource()).getId();
        String checksum = "";
        
        switch (buttonClicked) {
            case "md5sumCopyButton":
                checksum = md5sumTextField.getText();
                break;
            case "sha1sumCopyButton":
                checksum = sha1sumTextField.getText();
                break;
            case "sha256sumCopyButton":
                checksum = sha256sumTextField.getText();
                break;
            case "sha512sumCopyButton":
                checksum = sha512sumTextField.getText();
                break;
        }
        
        copyToClipboard(model.getFilePath() + "  " + checksum);
    }

    @FXML
    void exitApplication(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void exportChecksumsToFile(ActionEvent event) {
        StringBuilder builder = new StringBuilder();
        TextField[] checksumTextFields = {
                md5sumTextField, sha1sumTextField, sha256sumTextField, sha512sumTextField
        };

        for (TextField tf : checksumTextFields) {
            if (!tf.getText().isEmpty()) {
                if (formatStyleRadioButton.isSelected() && tf.getId().equals("md5sumTextField")) {
                    builder.append("MD5 (" + model.getFile().getName() + ") = " + tf.getText() + "\n");
                } else {
                    builder.append(tf.getText() + "  " + model.getFile().getName() + "\n");
                }
            }
        }

        writeToFile(builder.toString());
    }

    @FXML
    void generateSelectedChecksumValues(ActionEvent event) {
        if (md5sumCheckBox.isSelected()) {
            String checksum = Checksum.getChecksum("MD5", model.getFilePath());
            model.setMd5sum(checksum);
        }

        if (sha1sumCheckBox.isSelected()) {
            String checksum = Checksum.getChecksum("SHA-1", model.getFilePath());
            model.setSha1sum(checksum);
        }

        if (sha256sumCheckBox.isSelected()) {
            String checksum = Checksum.getChecksum("SHA-256", model.getFilePath());
            model.setSha256sum(checksum);
        }

        if (sha512sumCheckBox.isSelected()) {
            String checksum = Checksum.getChecksum("SHA-512", model.getFilePath());
            model.setSha512sum(checksum);
        }
    }

    @FXML
    void getFilePath(ActionEvent event) {
        FileChooser chooser = new FileChooser();

        String downloadDir = System.getProperty("user.home") + System.getProperty("file.separator") + "Downloads";
        chooser.setInitialDirectory(new File(downloadDir));

        File file = chooser.showOpenDialog(getMainWindow(anchorPane));
        if (file != null) {
            model.setFile(file);
        }
    }

    @FXML
    void resetForm(ActionEvent event) {
        model = new Model();
        setBindings();
    }

    private void copyToClipboard(String checksum) {
        Clipboard clipboard = Clipboard.getSystemClipboard();

        ClipboardContent content = new ClipboardContent();
        content.putString(checksum);

        clipboard.setContent(content);
    }

    private void writeToFile(String builderString) {

        FileChooser chooser = new FileChooser();
        chooser.setInitialFileName(model.getFile().getName() + ".md5sum");
        chooser.setTitle("Save");
        File file = chooser.showSaveDialog(getMainWindow(anchorPane));

        if (file != null) {
            try (FileWriter fw = new FileWriter(file.getAbsoluteFile());
                 BufferedWriter bw = new BufferedWriter(fw)) {

                bw.write(builderString);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Window getMainWindow(Pane stage) {
        return stage.getScene().getWindow();
    }

    private void setBindings() {
        fileTextField.textProperty().bind(model.filePathProperty());
        md5sumTextField.textProperty().bind(model.md5sumProperty());
        sha1sumTextField.textProperty().bind(model.sha1sumProperty());
        sha256sumTextField.textProperty().bind(model.sha256sumProperty());
        sha512sumTextField.textProperty().bind(model.sha512sumProperty());
    }
}
