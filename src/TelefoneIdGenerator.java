// TelefoneIdGenerator.java
public class TelefoneIdGenerator {
    private static long telefoneId = 0;

    public static long getNextTelefoneId() {
        return telefoneId++;
    }
}