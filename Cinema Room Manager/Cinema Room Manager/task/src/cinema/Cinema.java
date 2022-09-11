package cinema;

public class Cinema {
    public static void main (String[] args) {
        Control control = new Control();

        if (control.buildRoom()) {
            control.mainMenu();
        }
    }
}