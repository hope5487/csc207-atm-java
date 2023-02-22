package ATM.GUI.ForUser.Controller;

import ATM.BankUsers.BankWorker;
import ATM.FrequentlyAskedQuestions.Answers;
import ATM.MainOperation.ATM;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

public class FrequentlyAskedQuestionsMenuGUIController {

    @FXML
    private TextArea textArea;

    @FXML
    private ChoiceBox<String> selectQuestion;

    private static final Answers answers = new Answers();

    //Go back to main menu
     public void pressBack(){
        textArea.clear();
        selectQuestion.getItems().clear();
         if (ATM.currentUser instanceof BankWorker){
             ATM.window.setScene(ATM.scenes.bankWorkerMenuScene);
         } else {
             ATM.window.setScene(ATM.scenes.userMainMenuScene);
         }
    }

    public void pressASK() {
        textArea.clear();
        displayAnswers();
    }

    public void setSelectQuestion() {
         selectQuestion.getItems().clear();
         selectQuestion.getItems().add("How do I pay bills?");
         selectQuestion.getItems().add("How can I deposit funds into my account?");
         selectQuestion.getItems().add("How can I withdraw funds out of my account?");
         selectQuestion.getItems().add("How can I check my balances?");
         selectQuestion.getItems().add("How can I transfer money?");
         selectQuestion.getItems().add("What if I do not have a login?");
         selectQuestion.getItems().add("How can I create or add a new bank account?");
         selectQuestion.getItems().add("What types of accounts can I create?");
         selectQuestion.getItems().add("What is the difference between an Account and a User?");
         selectQuestion.getItems().add("Can I change my password?");
         selectQuestion.getItems().add("How can I undo a transaction?");
         selectQuestion.getItems().add("Do I have to pay tax on my withdrawals?");
         selectQuestion.getItems().add("How can I exchange funds into foreign currency?");
         selectQuestion.getItems().add("How can I buy and sell stocks?");
    }

    // helper method for pressASK
    private void displayAnswers() { questionAndAnswers(); }

    // helper method for displayAnswers
    private void questionAndAnswers() {
        switch (selectQuestion.getValue()) {
            case "How do I pay bills?":
                textArea.setText(answers.payBillsAnswer());
                break;
            case "How can I deposit funds into my account?":
                textArea.setText(answers.depositAnswer());
                break;
            case "How can I withdraw funds out of my account?":
                textArea.setText(answers.withdrawAnswer());
                break;
            case "How can I check my balances?":
                textArea.setText(answers.checkBalanceAnswer());
                break;
            case "How can I transfer money?":
                textArea.setText(answers.transferAnswer());
                break;
            case "What if I do not have a login?":
                textArea.setText(answers.loginAnswer());
                break;
            case "How can I create or add a new bank account?":
                textArea.setText(answers.newAccountAnswer());
                break;
            case "What types of accounts can I create?":
                textArea.setText(answers.createAccountAnswer());
                break;
            case "What is the difference between an Account and a User?":
                textArea.setText(answers.createUserAnswer());
                break;
            case "Can I change my password?":
                textArea.setText(answers.changePasswordAnswer());
                break;
            case "How can I undo a transaction?":
                textArea.setText(answers.undoAnswer());
                break;
            case "Do I have to pay tax on my withdrawals?":
                textArea.setText(answers.withdrawTaxAnswer());
                break;
            case "How can I exchange funds into foreign currency?":
                textArea.setText(answers.exchangeFundsAnswer());
                break;
            case "How can I buy and sell stocks?":
                textArea.setText(answers.stocksAnswer());
                break;
            default:
                textArea.setText("Please select a question");
                break;
        }
    }

}
