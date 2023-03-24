package helper;

/*
 * @author Marcus Syldon Antino Dirige Gonzaga
 */

import LambdaEx.inputErrorInterface;
import javafx.scene.control.Alert;

/**Messages Class
 * Holds messages to be outputted.
 */
public abstract class Messages {

    /** Warning Popup Method.
     * Takes parameters and outputs customized message.
     * @param warningMessage Messages warningMessage.
     * @param titleBar Messages titleBar.
     */
    public static void warningPopUp (String warningMessage, String titleBar)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titleBar);
        alert.setContentText(warningMessage);
        alert.showAndWait();
    }

    /*
    public static void warningLambdaPopUp (String warningMessage)
    {
        inputErrorInterface message = i -> i;

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(message.inputWarning("Input Error"));
        alert.setContentText(warningMessage);
        alert.showAndWait();
    }

     */
}
