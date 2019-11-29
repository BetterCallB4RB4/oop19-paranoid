package controllers;

import com.google.common.collect.ImmutableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.stream.Collectors;

public final class UIController {
    @FXML
    private Label lbl;

    @FXML
    private Button btn;

    @FXML
    public void btnOnClickHandler() {
        final var elements = ImmutableList.of(
            "Hello, JavaFX version ", System.getProperty("javafx.version"),
            " is running on Java ", System.getProperty("java.version"));
        lbl.setText(elements.stream().collect(Collectors.joining("")));
    }
}
