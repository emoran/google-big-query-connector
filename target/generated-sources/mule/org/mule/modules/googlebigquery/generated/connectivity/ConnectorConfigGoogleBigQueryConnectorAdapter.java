
package org.mule.modules.googlebigquery.generated.connectivity;

import javax.annotation.Generated;
import org.mule.api.ConnectionException;
import org.mule.devkit.internal.connection.management.ConnectionManagementConnectionAdapter;
import org.mule.devkit.internal.connection.management.TestableConnection;
import org.mule.modules.googlebigquery.config.ConnectorConfig;

@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.9.4", date = "2020-02-24T11:14:31-05:00", comments = "Build UNNAMED.2810.4347dd1")
public class ConnectorConfigGoogleBigQueryConnectorAdapter
    extends ConnectorConfig
    implements ConnectionManagementConnectionAdapter<ConnectorConfig, ConnectionManagementConfigGoogleBigQueryConnectorConnectionKey> , TestableConnection<ConnectionManagementConfigGoogleBigQueryConnectorConnectionKey>
{


    @Override
    public ConnectorConfig getStrategy() {
        return this;
    }

    @Override
    public void test(ConnectionManagementConfigGoogleBigQueryConnectorConnectionKey connectionKey)
        throws ConnectionException
    {
        super.connect(connectionKey.getServiceAccountId(), connectionKey.getServiceAccountFile());
    }

    @Override
    public void connect(ConnectionManagementConfigGoogleBigQueryConnectorConnectionKey connectionKey)
        throws ConnectionException
    {
        super.connect(connectionKey.getServiceAccountId(), connectionKey.getServiceAccountFile());
    }

    @Override
    public void disconnect() {
        super.disconnect();
    }

    @Override
    public String connectionId() {
        return super.connectionId();
    }

    @Override
    public boolean isConnected() {
        return super.isConnected();
    }

}