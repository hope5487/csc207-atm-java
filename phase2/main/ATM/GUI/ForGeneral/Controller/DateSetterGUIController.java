package ATM.GUI.ForGeneral.Controller;

import ATM.MainOperation.ATM;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;

public class DateSetterGUIController {

    @FXML
    private DatePicker datePicker;

    public void pushConfirmButton(){
        LocalDate pickedDate = datePicker.getValue();
        if (pickedDate != null){
            ATM.dataStorage.getDateStorage().getTime().setDate(pickedDate.getYear(), pickedDate.getMonthValue(), pickedDate.getDayOfMonth());
            ATM.dataStorage.getDateStorage().writeDateFile();
            ATM.window.setScene(ATM.scenes.logInScene);
        }
    }
}
