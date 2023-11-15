package Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;

public class UncoverController implements Initializable {

    @FXML
    private ImageView imgDescription;

    @FXML
    private ImageView imgDeskripsi;

    @FXML
    private ImageView imgMobil;

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

    @FXML
    private Button btn_Play;

    @FXML
    private Button btn_Search;

    private AudioClip audioClip;

    private boolean gimmick = false;
    RBT IETree = new RBT();
    RBT EITree = new RBT();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        imgDeskripsi.setVisible(false);
        imgDescription.setVisible(false);
        imgMobil.setVisible(false);

        URL resourceURL = getClass().getResource("src/Musik.mp3");
        if (resourceURL != null) {
            audioClip = new AudioClip(resourceURL.toString());
        } else {
            System.err.println("Resource not found");
        }

        String[] stringArray = new String[84];

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
        ta_deskripsi.setWrapText(true);
        ta_description.setWrapText(true);
        ta_deskripsi.setStyle("-fx-text-alignment: justify;");
        ta_description.setStyle("-fx-text-alignment: justify;");
    }

    @FXML
    void btn_Play(ActionEvent event) {
        try {
            if (audioClip != null) {
                audioClip.stop();
                audioClip.play();
                System.out.println("Music played successfully!");
            } else {
                System.err.println("AudioClip is not initialized");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void IDEN(String key) {
        if (IETree.search(key) == true) {
            tf_kata.setText(tf_input.getText());
            tf_word.setText(IETree.getValue(key));
            ta_deskripsi.setText(IETree.getDeskripsi(tf_input.getText()));
            ta_description.setText(EITree.getDescription(IETree.getValue(tf_input.getText())));
            ta_deskripsi.setWrapText(true);
            ta_description.setWrapText(true);
            ta_deskripsi.setStyle("-fx-text-alignment: justify;");
            ta_description.setStyle("-fx-text-alignment: justify;");
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
            ta_deskripsi.setWrapText(true);
            ta_description.setWrapText(true);
            ta_deskripsi.setStyle("-fx-text-alignment: justify;");
            ta_description.setStyle("-fx-text-alignment: justify;");
        } else {
            tf_kata.setText("tidak ditemukan");
            tf_word.setText("not found");
            ta_deskripsi.setText(null);
            ta_description.setText(null);
        }
    }

    public String Gimmick() {
        String gimmick = "";
        if (IETree.search(tf_input.getText()) == true) {
            Node node = IETree.isExist(IETree.getRoot(), tf_input.getText());
            gimmick = node.getGimmick();
            return gimmick;
        } else if (EITree.search(tf_input.getText()) == true) {
            Node node = EITree.isExist(EITree.getRoot(), tf_input.getText());
            gimmick = node.getGimmick();
            return gimmick;
        }
        return gimmick;
    }

    @FXML
    void btn_Search(ActionEvent event) {
        IETree.inorderTraversal(IETree.getRoot());

        if (gimmick == true) {
            ta_description.setVisible(true);
            ta_description.setVisible(true);
            imgDeskripsi.setVisible(false);
            imgDescription.setVisible(false);
            imgMobil.setVisible(false);

            gimmick = false;
        }

        if (tf_input.getText() != null) {
            if (IETree.search(tf_input.getText())) {
                IDEN(tf_input.getText());
            } else if (EITree.search(tf_input.getText())) {
                ENID(tf_input.getText());
            } else {
                tf_kata.setText("tidak ditemukan");
                tf_word.setText("not found");
                ta_deskripsi.setText(null);
                ta_description.setText(null);
            }
        }

        if (Gimmick() != "") {
            if (Gimmick().compareTo("gambar") == 0) {
                ta_description.setVisible(true);
                ta_deskripsi.setVisible(true);
                imgDeskripsi.setVisible(true);
                imgDescription.setVisible(true);
                gimmick = true;
            } else if (Gimmick().compareTo("kendaraan") == 0) {
                ta_description.setVisible(true);
                ta_deskripsi.setVisible(true);
                imgMobil.setVisible(true);
                gimmick = true;
            } else if (Gimmick().equals("play")) {
                ta_description.setVisible(true);
                ta_deskripsi.setVisible(true);
                btn_Play.setVisible(true);
                gimmick = true;
                // Automatically play the music
                btn_Play(event);
            } else if (Gimmick().equals("color")) {
                ta_deskripsi.setStyle("-fx-text-fill: blue;");
                ta_description.setStyle("-fx-text-fill: blue;");
            } else if (Gimmick().compareTo("link") == 0) {
                // map Succeed
                gimmick = true;
                try {
                    Runtime.getRuntime().exec(
                            new String[] { "cmd", "/c", "start chrome https://maps.app.goo.gl/S4WL5pS7QaR369fu9" });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}