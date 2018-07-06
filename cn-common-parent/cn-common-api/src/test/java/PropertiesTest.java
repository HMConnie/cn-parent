import cn.connie.common.utils.GlobalConfigration;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesTest {


    @Test
    public void testProperties() throws Exception {
        InputStream inputStream = PropertiesTest.class.getClassLoader().getResourceAsStream("file.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            GlobalConfigration.reload(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
