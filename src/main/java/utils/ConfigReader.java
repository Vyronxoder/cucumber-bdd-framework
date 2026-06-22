package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String getBaseUrl()     { return properties.getProperty("base.url"); }
    public static String getBrowser()     { return System.getProperty("browser", properties.getProperty("browser")); }
    public static boolean isHeadless()    { return Boolean.parseBoolean(System.getProperty("headless", properties.getProperty("headless"))); }
    public static int getImplicitWait()   { return Integer.parseInt(properties.getProperty("implicit.wait")); }
    public static int getExplicitWait()   { return Integer.parseInt(properties.getProperty("explicit.wait")); }
}
