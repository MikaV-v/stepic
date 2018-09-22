import java.nio.charset.StandardCharsets;
import java.util.*;
import java.math.*;
import java.io.*;

public class Stepic {
    public static void main(String[] args) {
        char[] testChars = new char[]{'a'};
        String in = new String(testChars);
        byte[] bytes = in.getBytes(StandardCharsets.US_ASCII);
        System.out.println(Arrays.toString(bytes));
    }
}
