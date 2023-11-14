package Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class UncoverController implements Initializable {

    @FXML
    private TextArea ta_description;

    @FXML
    private TextArea ta_deskripsi;

    @FXML
    private TextField tf_input;

    @FXML
    private TextField tf_kata;

    @FXML
    private TextField tf_word;

    private boolean gimmick = false;
    RBT IETree = new RBT();
    RBT EITree = new RBT();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // imgGim1.setVisible(false);
        // imgGim2.setVisible(false);
        // lblClock.setVisible(false);
        // initClock();

        String[] stringArray = new String[48];

        try {
            String filename = "Kata.txt";
            String absoluteFilePath = "";
            String workingDir = System.getProperty("user.dir") + "\\src";

            absoluteFilePath = workingDir + File.separator + filename;
            FileReader fileReader = new FileReader(absoluteFilePath);
            try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                int i = 0;
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringArray[i] = line.replaceAll("\\s", " ");
                    i++;
                }
            } catch (Exception e) {
                System.out.println(e);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        for (int i = 0; i < stringArray.length; i += 4) {
            if (i + 1 < stringArray.length) {
                IETree.add(stringArray[i], stringArray[i + 1], stringArray[i + 2], stringArray[i + 3]);
            }
        }
        for (int i = 0; i < stringArray.length; i += 4) {
            if (i + 1 < stringArray.length) {
                EITree.add(stringArray[i + 1], stringArray[i], stringArray[i + 2], stringArray[i + 3]);
            }
        }
    }

    void IDEN(String key) {
        if (IETree.search(key) == true) {
            tf_kata.setText(tf_input.getText());
            tf_word.setText(IETree.getValue(key));
            ta_deskripsi.setText(IETree.getDeskripsi(tf_input.getText()));
            ta_description.setText(EITree.getDescription(IETree.getValue(tf_input.getText())));
        } else {
            tf_kata.setText("tidak ditemukan");
            tf_word.setText("not found");
            ta_deskripsi.setText(null);
            ta_description.setText(null);
        }
    }

    void ENID(String value) {
        if (EITree.search(value) == true) {
            tf_kata.setText(EITree.getValue(value));
            tf_word.setText(tf_input.getText());
            ta_deskripsi.setText(IETree.getDeskripsi(EITree.getValue(tf_input.getText())));
            ta_description.setText(EITree.getDescription(tf_input.getText()));
        } else {
            tf_kata.setText("tidak ditemukan");
            tf_word.setText("not found");
            ta_deskripsi.setText(null);
            ta_description.setText(null);
        }
    }

    // public String Gimmick() {
    // String gimmick = "";
    // if (IETree.search(tf_input.getText()) == true) {
    // Node node = IETree.isExist(IETree.getRoot(), tf_input.getText());
    // gimmick = node.getGimmick();
    // return gimmick;
    // } else if (EITree.search(tf_input.getText()) == true) {
    // Node node = EITree.isExist(EITree.getRoot(), tf_input.getText());
    // gimmick = node.getGimmick();
    // return gimmick;
    // }
    // return gimmick;
    // }

    @FXML
    void btn_Search(ActionEvent event) {
        IETree.inorderTraversal(IETree.getRoot());

        if (gimmick == true) {
            // lblDesc.setVisible(true);
            // tfDesc.setVisible(true);
            // tfDeskripsi.setVisible(true);
            // lblClock.setVisible(false);
            // imgGim1.setVisible(false);
            // imgGim2.setVisible(false);

            gimmick = false;
        }

        if (tf_input.getText() != null) {
            if (IETree.search(tf_input.getText())) {
                IDEN(tf_input.getText());
            } else if (EITree.search(tf_input.getText())) {
                ENID(tf_input.getText());
            } else {
                System.out.println();
            }
        }

        // if (Gimmick() != "") {
        // if (Gimmick().compareTo("clock") == 0) {
        // // date Succeed
        // lblClock.setVisible(true);
        // gimmick = true;
        // } else if (Gimmick().compareTo("erase") == 0) {
        // // hapus Succeed
        // lblDesc.setVisible(false);
        // tfDesc.setVisible(false);
        // tfDeskripsi.setVisible(false);
        // gimmick = true;
        // } else if (Gimmick().compareTo("popup") == 0) {
        // // foto Succeed
        // lblDesc.setVisible(false);
        // tfDesc.setVisible(false);
        // tfDeskripsi.setVisible(false);
        // imgGim1.setVisible(true);
        // imgGim2.setVisible(true);
        // gimmick = true;
        // } else if (Gimmick().compareTo("link") == 0) {
        // // map Succeed
        // gimmick = true;
        // try {
        // Runtime.getRuntime().exec(new String[] { "cmd", "/c", "start chrome
        // https://maps.app.goo.gl/pAq67vZxGAdrWQfZA" });
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // } else if (Gimmick().compareTo("spinning") == 0) {
        // RotateTransition rotate = new RotateTransition(Duration.seconds(1), vBox);
        // rotate.setByAngle(360);
        // rotate.play();
        // }
        // }
    }
}