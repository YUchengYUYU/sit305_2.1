package moutai.yuyucheng.task21;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static boolean isLengthUnit(String unit) {
        return unit.equals("inches") || unit.equals("feet") || unit.equals("yards") || unit.equals("miles");
    }

    public static boolean isWeightUnit(String unit) {
        return unit.equals("tons") || unit.equals("kilograms") || unit.equals("ounces") || unit.equals("pounds");
    }

    public static boolean isTemperatureUnit(String unit) {
        return unit.equals("Celsius") || unit.equals("Fahrenheit") || unit.equals("Kelvin");
    }

    private Spinner sourceUnitSpinner;
    private Spinner targetUnitSpinner;
    private EditText valueEditText;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sourceUnitSpinner = findViewById(R.id.source_unit_spinner);
        targetUnitSpinner = findViewById(R.id.target_unit_spinner);
        valueEditText = findViewById(R.id.value_edit_text);
        resultTextView = findViewById(R.id.result_text_view);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.units,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sourceUnitSpinner.setAdapter(adapter);
        targetUnitSpinner.setAdapter(adapter);

        Button convertButton = findViewById(R.id.convert_button);
        convertButton.setOnClickListener(v -> {
            double value = Double.parseDouble(valueEditText.getText().toString());
            String sourceUnit = sourceUnitSpinner.getSelectedItem().toString();
            String targetUnit = targetUnitSpinner.getSelectedItem().toString();

            double result;


            if ((isLengthUnit(sourceUnit) && isLengthUnit(targetUnit)) ||
                    (isWeightUnit(sourceUnit) && isWeightUnit(targetUnit)) ||
                    (isTemperatureUnit(sourceUnit) && isTemperatureUnit(targetUnit))) {
                if (sourceUnit.equals(targetUnit)) {
                    result = value;
                } else if (isTemperatureUnit(sourceUnit) || isTemperatureUnit(targetUnit)) {
                    result = convertTemperature(value, sourceUnit, targetUnit);
                } else if (isLengthUnit(sourceUnit) && isLengthUnit(targetUnit)) {
                    result = convertLength(value, sourceUnit, targetUnit);
                } else {
                    result = convertWeight(value, sourceUnit, targetUnit);
                }
                resultTextView.setText(String.valueOf(result));
            } else {
                resultTextView.setText(String.valueOf(value));
                Toast.makeText(getApplicationContext(), "Please select correct unit ", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public static double convertLength(double value, String sourceUnit, String targetUnit) {
        double sourceFactor = getLengthConversionFactor(sourceUnit);
        double targetFactor = getLengthConversionFactor(targetUnit);

        return value * sourceFactor / targetFactor;
    }

    public static double convertWeight(double value, String sourceUnit, String targetUnit) {
        double sourceFactor = getWeightConversionFactor(sourceUnit);
        double targetFactor = getWeightConversionFactor(targetUnit);

        return value * sourceFactor / targetFactor;
    }

    public static double convertTemperature(double value, String sourceUnit, String targetUnit) {
        if (sourceUnit.equals("Celsius") && targetUnit.equals("Fahrenheit")) {
            return (value * 1.8) + 32;
        } else if (sourceUnit.equals("Fahrenheit") && targetUnit.equals("Celsius")) {
            return (value - 32) / 1.8;
        } else if (sourceUnit.equals("Celsius") && targetUnit.equals("Kelvin")) {
            return value + 273.15;
        } else if (sourceUnit.equals("Kelvin") && targetUnit.equals("Celsius")) {
            return value - 273.15;
        } else if (sourceUnit.equals("Fahrenheit") && targetUnit.equals("Kelvin")) {
            return (value + 459.67) * 5/9;
        } else {
            return (value * 9/5) - 459.67;
        }
    }

    public static double getLengthConversionFactor(String unit) {
        switch (unit) {
            case "inches":
                return 0.0254;
            case "feet":
                return 0.3048;
            case "yards":
                return 0.9144;
            case "miles":
                return 1609.34;
            default:
                return 1.0;
        }
    }
    public static double getWeightConversionFactor(String unit) {
        switch (unit) {
            case "tons":
                return 907185;
            case "kilograms":
                return 1.0;
            case "ounces":
                return 28.3495;
            case "pounds":
                return 0.453592;
            default:
                return 1.0;
        }
    }
}
