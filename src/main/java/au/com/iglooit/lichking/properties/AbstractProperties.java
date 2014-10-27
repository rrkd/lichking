package au.com.iglooit.lichking.properties;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * IGUser: nicholas.zhu
 * Date: 25/08/2014
 * Time: 4:44 PM
 */
public abstract class AbstractProperties implements Map<String, String> {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractProperties.class);
    private Map<String, String> cache = new HashMap<String, String>();
    private static final String PROPERTIES_BASE_LOCATION = "au.com.iglooit.lichking.properties";

    /**
     * Method to load properties
     */
    public void loadProperties() {

        String[] propertiesLocationArray = getPropertyFileNames();
        for (String propertiesPath : propertiesLocationArray) {
            ResourceBundle props = getBundleWithEnvironmentFallback(propertiesPath);
            if (props == null) {
                LOG.error("No properties found for [{}] for any suitable environment", propertiesPath);
                continue;
            }
            Set<String> keySet = props.keySet();
            for (String key : keySet) {
                cache.put(key, props.getString(key));
            }
        }
    }

    private ResourceBundle getBundleWithEnvironmentFallback(final String propertiesPath) {
        if (StringUtils.isBlank(propertiesPath)) {
            LOG.warn("Cannot load properties for empty environment or relative property path.");
            return null;
        }
        ResourceBundle bundle = null;

        while (bundle == null) {
            String bundlePath = PROPERTIES_BASE_LOCATION + "." + propertiesPath;
            try {
                LOG.debug("Trying to load [{}]", bundlePath);
                bundle = ResourceBundle.getBundle(bundlePath, Locale.ENGLISH);
                LOG.info("Successfully loaded [{}]", bundlePath);
            } catch (MissingResourceException e) {
                LOG.warn("Unable to retrieve properties bundle [{}]", bundlePath);
            }
        }

        return bundle;
    }

    /**
     * Loads the properties from given locations. Location at higher index in array will get the precedence
     * in case of duplicate properties
     *
     * @return String[] Array of properties locations
     */
    public abstract String[] getPropertyFileNames();

    @Override
    public int size() {
        if (CollectionUtils.isNotEmpty(cache.keySet())) {
            return cache.keySet().size();
        }
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return CollectionUtils.isEmpty(cache.keySet());
    }

    @Override
    public boolean containsKey(Object o) {
        if (o instanceof String) {
            final String key = (String) o;
            return cache.containsKey(key);
        }
        return false;
    }

    @Override
    public boolean containsValue(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String put(String k, String v) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void putAll(Map<? extends String, ? extends String> map) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<String> keySet() {
        return cache.keySet();
    }

    @Override
    public Collection<String> values() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<Entry<String, String>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String get(final Object o) {
        if (o instanceof String) {
            final String key = (String) o;
            if (cache.containsKey(key)) {
                return cache.get(key);
            }
            return "";
        } else {
            return null;
        }
    }
}
