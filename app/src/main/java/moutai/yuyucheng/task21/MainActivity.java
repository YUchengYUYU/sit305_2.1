    return value * sourceFactor / targetFactor;
}

public static double convertTemperature(double value, String sourceUnit, String targetUnit) {
    if (sourceUnit.equals("Celsius") && targetUnit.equals("Fahrenheit")) {
        return value * 9.0 / 5.0 + 32.0;
    } else if (sourceUnit.equals("Celsius") && targetUnit.equals("Kelvin")) {
        return value + 273.15;
    } else if (sourceUnit.equals("Fahrenheit") && targetUnit.equals("Celsius")) {
        return (value - 32.0) * 5.0 / 9.0;
    } else if (sourceUnit.equals("Fahrenheit") && targetUnit.equals("Kelvin")) {
        return (value + 459.67) * 5.0 / 9.0;
    } else if (sourceUnit.equals("Kelvin") && targetUnit.equals("Celsius")) {
        return value - 273.15;
    } else if (sourceUnit.equals("Kelvin") && targetUnit.equals("Fahrenheit")) {
        return value * 9.0 / 5.0 - 459.67;
    } else {
        return value;
    }
}

public static double getLengthConversionFactor(String unit) {
    switch (unit) {
        case "inches":
            return 1.0;
        case "feet":
            return 12.0;
        case "yards":
            return 36.0;
        case "miles":
            return 63360.0;
        default:
            return 0.0;
    }
}

public static double getWeightConversionFactor(String unit) {
    switch (unit) {
        case "ounces":
            return 0.0625;
        case "pounds":
            return 1.0;
        case "kilograms":
            return 2.20462;
        case "tons":
            return 2000.0;
        default:
            return 0.0;
    }
}
