
package org.mule.modules.googlebigquery.generated.adapters;

import javax.annotation.Generated;
import org.mule.api.MetadataAware;
import org.mule.modules.googlebigquery.GoogleBigQueryConnector;


/**
 * A <code>GoogleBigQueryConnectorMetadataAdapter</code> is a wrapper around {@link GoogleBigQueryConnector } that adds support for querying metadata about the extension.
 * 
 */
@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.9.4", date = "2020-02-24T11:14:31-05:00", comments = "Build UNNAMED.2810.4347dd1")
public class GoogleBigQueryConnectorMetadataAdapter
    extends GoogleBigQueryConnectorCapabilitiesAdapter
    implements MetadataAware
{

    private final static String MODULE_NAME = "GoogleBigQuery";
    private final static String MODULE_VERSION = "1.0.0";
    private final static String DEVKIT_VERSION = "3.9.4";
    private final static String DEVKIT_BUILD = "UNNAMED.2810.4347dd1";
    private final static String MIN_MULE_VERSION = "3.5.0";

    public String getModuleName() {
        return MODULE_NAME;
    }

    public String getModuleVersion() {
        return MODULE_VERSION;
    }

    public String getDevkitVersion() {
        return DEVKIT_VERSION;
    }

    public String getDevkitBuild() {
        return DEVKIT_BUILD;
    }

    public String getMinMuleVersion() {
        return MIN_MULE_VERSION;
    }

}
