package com.sparksdev.flo.io.file;

/**
 * @author bengill
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.net.URL;
import java.util.Map;

/**
 * @author bengill
 */
public class ResourceLoader {

    final static Logger LOGGER = LoggerFactory.getLogger(ResourceLoader.class);

    public static InputStream loadResource(final String name) {

        final String errorMessage = "Unable to load resource [" + name + "]";

        try {
            LOGGER.info("Loading resource [" + name + "]");
            InputStream is = ResourceLoader.class.getResourceAsStream(name);
            if (is == null) {
                throw new RuntimeException(errorMessage);
            }
            return is;
        } catch (Exception e) {
            LOGGER.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    public static URL getResource(final String name) {

        final String errorMessage = "Unable to load resource [" + name + "]";

        try {
            LOGGER.info("Loading resource [" + name + "]");
            URL url = ResourceLoader.class.getResource(name);
            if (url == null) {
                throw new RuntimeException(errorMessage);
            }
            return url;
        } catch (Exception e) {
            LOGGER.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }


    public static String loadResourceAsString(final String name) {
        InputStream is = loadResource(name);
        return convertStreamToString(is);
    }

    /**
     * Loads the resource and tokenizes any values in the input. For example if the map has an item named "siteName"
     * then any instances of "$@siteName}" in the input will be replaced with the
     * <p/>
     * The performance of this is poor. If you want to pass in more than a few params or do it to large files then it
     * should be improved.
     *
     * @param filename
     *         the name of the file to read
     * @param replaceTokens
     *         a map of tokens to replace
     * @return the de-tokenised string
     */
    public static String loadResourceAsString(final String filename,
                                              final Map<String, String> replaceTokens) {

        String inputData = loadResourceAsString(filename);

        for (Map.Entry<String, String> entry : replaceTokens.entrySet()) {
            inputData = inputData.replace(
                    "${" + entry.getKey() + "}",
                    entry.getValue()
            );
        }

        return inputData;
    }

    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }


}
