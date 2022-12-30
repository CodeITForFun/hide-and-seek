package cz.ragy.hideandseek.Utilities;

public class Digit {
    public boolean containsDigits(String StringInput) {
        boolean containsDigits = StringInput.matches("[0-9]+");
        if(containsDigits) {
            return true;
        } else {
            return false;
        }

    }
}
