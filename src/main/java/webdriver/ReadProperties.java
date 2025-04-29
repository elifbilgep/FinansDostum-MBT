package webdriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {
    String appConfigPath = "config.properties";

    private Properties readPropFile() {

        Properties appProps = new Properties();
        InputStream inputStream;
        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(appConfigPath);
            appProps.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return appProps;

    }
    
    public String getProp(String prop) {
        return readPropFile().getProperty(prop);
    }


}
