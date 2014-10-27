package au.com.iglooit.lichking.properties;

/**
 * Created with IntelliJ IDEA.
 * IGUser: nicholas.zhu
 * Date: 25/08/2014
 * Time: 4:50 PM
 */
public final class WebProperties extends AbstractProperties {
    private static WebProperties selfInstance;

    /**
     * Constructor to prevent instantiation.
     */
    private WebProperties() {
    }

    /**
     * Method to return the AFRProperties instance in a singleton way.
     *
     * @return Singleton instance of SearchProperties.
     */
    public static WebProperties getInstance() {
        synchronized (WebProperties.class) {
            if (selfInstance == null) {
                selfInstance = new WebProperties();
                selfInstance.loadProperties();
            }
        }
        return selfInstance;
    }

    @Override
    public String[] getPropertyFileNames() {
        return new String[]{"web"};
    }
}
