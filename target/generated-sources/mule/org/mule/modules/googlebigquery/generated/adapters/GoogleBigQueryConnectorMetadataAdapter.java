
package org.mule.modules.googlebigquery.generated.adapters;

import javax.annotation.Generated;
import org.mule.api.MetadataAware;
import org.mule.modules.googlebigquery.GoogleBigQueryConnector;


/**
 * A <code>GoogleBigQueryConnectorMetadataAdapter</code> is a wrapper around {@link GoogleBigQueryConnector } that adds support for querying metadata about the extension.
 * 
 */
@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.9.0", date = "2020-03-20T08:50:41-04:00", comments = "Build UNNAMED.2793.f49b6c7")
public class GoogleBigQueryConnectorMetadataAdapter
    extends GoogleBigQueryConnectorCapabilitiesAdapter
    implements MetadataAware
{

    private final static String MODULE_NAME = "Unofficial Google Big Query";
    private final static String MODULE_VERSION = "2.0.0";
    private final static String DEVKIT_VERSION = "3.9.0";
    private final static String DEVKIT_BUILD = "UNNAMED.2793.f49b6c7";
    private final static String MIN_MULE_VERSION = "3.8";

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
