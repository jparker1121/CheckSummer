package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class ViewController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private GridPane gridBox;

    @FXML
    private Button md5sumCopyButton;

    @FXML
    private CheckBox md5sumCheckBox;

    @FXML
    private CheckBox sha2sumCheckBox;

    @FXML
    private CheckBox sha256sumCheckBox;

    @FXML
    private CheckBox sha512sumCheckBox;

    @FXML
    private Button sh1sumCopyButton;

    @FXML
    private Button sha256sumCopyButton;

    @FXML
    private Button sha512sumCopyButton;

    @FXML
    private RadioButton exportMd5sumRadioButton;

    @FXML
    private RadioButton exportSha1sumRadioButton;

    @FXML
    private RadioButton exportSha256sumRadioButton;

    @FXML
    private RadioButton exportSha512sumRadioButton;

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
    void copySelectedChecksum(ActionEvent event) {
        String buttonClicked = ((Button) event.getSource()).getId();
        String checksum = "";
        
        switch (buttonClicked) {
            case "md5sumCopybutton":
                checksum = md5sumTextField.getText();
                break;
            case "sha1sumCopybutton":
                checksum = sha1sumTextField.getText();
                break;
            case "sha256sumCopybutton":
                checksum = sha256sumTextField.getText();
                break;
            case "sha512sumCopybutton":
                checksum = sha512sumTextField.getText();
                break;
        }
        
        copyToClipboard(fileTextField.getText() + "  " + checksum);
    }

    @FXML
    void exitApplication(ActionEvent event) {

    }

    @FXML
    void exportChecksumsToFile(ActionEvent event) {

    }

    @FXML
    void generateSelectedChecksumValues(ActionEvent event) {

    }

    @FXML
    void getFilePath(ActionEvent event) {

    }

    @FXML
    void resetForm(ActionEvent event) {

    }

    private void copyToClipboard(String checksum) {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        
        ClipboardContent content = new ClipboardContent();
        content.putString(checksum);
        
        clipboard.setContent(content);
    }

}
