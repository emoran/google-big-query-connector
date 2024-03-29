
package org.mule.modules.googlebigquery.generated.connectivity;

import javax.annotation.Generated;
import org.mule.devkit.internal.connection.management.ConnectionManagementConnectionKey;

@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.9.0", date = "2020-03-20T08:50:41-04:00", comments = "Build UNNAMED.2793.f49b6c7")
public class ConnectionManagementConfigGoogleBigQueryConnectorConnectionKey implements ConnectionManagementConnectionKey
{

    /**
     * 
     */
    private String serviceAccountId;
    /**
     * 
     */
    private String serviceAccountFile;

    public ConnectionManagementConfigGoogleBigQueryConnectorConnectionKey(String serviceAccountId, String serviceAccountFile) {
        this.serviceAccountId = serviceAccountId;
        this.serviceAccountFile = serviceAccountFile;
    }

    /**
     * Sets serviceAccountId
     * 
     * @param value Value to set
     */
    public void setServiceAccountId(String value) {
        this.serviceAccountId = value;
    }

    /**
     * Retrieves serviceAccountId
     * 
     */
    public String getServiceAccountId() {
        return this.serviceAccountId;
    }

    /**
     * Sets serviceAccountFile
     * 
     * @param value Value to set
     */
    public void setServiceAccountFile(String value) {
        this.serviceAccountFile = value;
    }

    /**
     * Retrieves serviceAccountFile
     * 
     */
    public String getServiceAccountFile() {
        return this.serviceAccountFile;
    }

    @Override
    public int hashCode() {
        int result = ((this.serviceAccountId!= null)?this.serviceAccountId.hashCode(): 0);
        result = ((31 *result)+((this.serviceAccountFile!= null)?this.serviceAccountFile.hashCode(): 0));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ConnectionManagementConfigGoogleBigQueryConnectorConnectionKey)) {
            return false;
        }
        ConnectionManagementConfigGoogleBigQueryConnectorConnectionKey that = ((ConnectionManagementConfigGoogleBigQueryConnectorConnectionKey) o);
        if (((this.serviceAccountId!= null)?(!this.serviceAccountId.equals(that.serviceAccountId)):(that.serviceAccountId!= null))) {
            return false;
        }
        if (((this.serviceAccountFile!= null)?(!this.serviceAccountFile.equals(that.serviceAccountFile)):(that.serviceAccountFile!= null))) {
            return false;
        }
        return true;
    }

}
