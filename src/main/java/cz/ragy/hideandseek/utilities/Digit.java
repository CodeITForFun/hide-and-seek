package cz.ragy.hideandseek.utilities;

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
