package multiteam.claysoldiers2.main.util;

public class ExceptionUtils {
    public static UnsupportedOperationException utilityClassException() {
        return new UnsupportedOperationException("Instantiation of utility classes are not supported.");
    }
}
