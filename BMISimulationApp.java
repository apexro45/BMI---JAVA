import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class BMISimulationApp extends Application {

    private TextField weightField;
    private TextField heightField;
    private TextArea resultArea;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("BMI Simulation App");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        Label weightLabel = new Label("Weight (kg):");
        GridPane.setConstraints(weightLabel, 0, 0);
        weightField = new TextField();
        weightField.setPromptText("Enter weight");
        GridPane.setConstraints(weightField, 1, 0);

        Label heightLabel = new Label("Height (m):");
        GridPane.setConstraints(heightLabel, 0, 1);
        heightField = new TextField();
        heightField.setPromptText("Enter height");
        GridPane.setConstraints(heightField, 1, 1);

        Button calculateButton = new Button("Calculate BMI");
        GridPane.setConstraints(calculateButton, 1, 2);
        calculateButton.setOnAction(e -> calculateBMI());

        resultArea = new TextArea();
        resultArea.setEditable(false);
        GridPane.setConstraints(resultArea, 0, 3, 2, 1);

        grid.getChildren().addAll(weightLabel, weightField, heightLabel, heightField, calculateButton, resultArea);

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void calculateBMI() {
        try {
            double weight = Double.parseDouble(weightField.getText());
            double height = Double.parseDouble(heightField.getText());

            double bmi = weight / (height * height);

            String bmiCategory;
            if (bmi < 18.5) {
                bmiCategory = "Underweight";
            } else if (bmi < 25) {
                bmiCategory = "Normal weight";
            } else if (bmi < 30) {
                bmiCategory = "Overweight";
            } else {
                bmiCategory = "Obese";
            }

            String result = String.format("Your BMI: %.2f\nBMI Category: %s", bmi, bmiCategory);
            resultArea.setText(result);
        } catch (NumberFormatException e) {
            resultArea.setText("Please enter valid weight and height.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
