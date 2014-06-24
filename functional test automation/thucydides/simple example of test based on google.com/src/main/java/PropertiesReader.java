import java.io.*;
import java.util.Properties;


public class PropertiesReader
{

    public static Properties readProperties(String name)
    {
        Properties properties = new Properties();
        InputStream stream = null;
        try
        {
            stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
            properties.load(stream);
        }
        catch (IOException e)
        {

            e.printStackTrace();

        } finally
        {
            if (stream != null)
            {
                try
                {
                    stream.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();

                }
            }
        }
        return properties;
    }
}
