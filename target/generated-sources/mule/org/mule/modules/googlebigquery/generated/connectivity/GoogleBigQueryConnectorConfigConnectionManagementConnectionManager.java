
package org.mule.modules.googlebigquery.generated.connectivity;

import javax.annotation.Generated;
import org.apache.commons.pool.KeyedObjectPool;
import org.mule.api.MetadataAware;
import org.mule.api.MuleContext;
import org.mule.api.MuleEvent;
import org.mule.api.config.MuleProperties;
import org.mule.api.context.MuleContextAware;
import org.mule.api.devkit.ProcessAdapter;
import org.mule.api.devkit.ProcessTemplate;
import org.mule.api.devkit.capability.Capabilities;
import org.mule.api.devkit.capability.ModuleCapability;
import org.mule.api.lifecycle.Disposable;
import org.mule.api.lifecycle.Initialisable;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.retry.RetryPolicyTemplate;
import org.mule.common.DefaultTestResult;
import org.mule.common.TestResult;
import org.mule.common.Testable;
import org.mule.config.PoolingProfile;
import org.mule.devkit.api.exception.ConfigurationWarning;
import org.mule.devkit.api.lifecycle.LifeCycleManager;
import org.mule.devkit.api.lifecycle.MuleContextAwareManager;
import org.mule.devkit.internal.connection.management.ConnectionManagementConnectionAdapter;
import org.mule.devkit.internal.connection.management.ConnectionManagementConnectionManager;
import org.mule.devkit.internal.connection.management.ConnectionManagementConnectorAdapter;
import org.mule.devkit.internal.connection.management.ConnectionManagementConnectorFactory;
import org.mule.devkit.internal.connection.management.ConnectionManagementProcessTemplate;
import org.mule.devkit.internal.connection.management.UnableToAcquireConnectionException;
import org.mule.devkit.internal.connectivity.ConnectivityTestingErrorHandler;
import org.mule.devkit.processor.ExpressionEvaluatorSupport;
import org.mule.modules.googlebigquery.GoogleBigQueryConnector;
import org.mule.modules.googlebigquery.config.ConnectorConfig;
import org.mule.modules.googlebigquery.generated.adapters.GoogleBigQueryConnectorConnectionManagementAdapter;
import org.mule.modules.googlebigquery.generated.pooling.DevkitGenericKeyedObjectPool;


/**
 * A {@code GoogleBigQueryConnectorConfigConnectionManagementConnectionManager} is a wrapper around {@link GoogleBigQueryConnector } that adds connection management capabilities to the pojo.
 * 
 */
@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.9.0", date = "2020-03-04T05:10:19-05:00", comments = "Build UNNAMED.2793.f49b6c7")
public class GoogleBigQueryConnectorConfigConnectionManagementConnectionManager
    extends ExpressionEvaluatorSupport
    implements MetadataAware, MuleContextAware, ProcessAdapter<GoogleBigQueryConnectorConnectionManagementAdapter> , Capabilities, Disposable, Initialisable, Testable, ConnectionManagementConnectionManager<ConnectionManagementConfigGoogleBigQueryConnectorConnectionKey, GoogleBigQueryConnectorConnectionManagementAdapter, ConnectorConfig>
{

    /**
     * 
     */
    private String serviceAccountId;
    /**
     * 
     */
    private String serviceAccountFile;
    /**
     * Mule Context
     * 
     */
    protected MuleContext muleContext;
    /**
     * Connector Pool
     * 
     */
    private KeyedObjectPool connectionPool;
    protected PoolingProfile poolingProfile;
    protected RetryPolicyTemplate retryPolicyTemplate;
    private final static String MODULE_NAME = "Unofficial Google Big Query";
    private final static String MODULE_VERSION = "1.0.20";
    private final static String DEVKIT_VERSION = "3.9.0";
    private final static String DEVKIT_BUILD = "UNNAMED.2793.f49b6c7";
    private final static String MIN_MULE_VERSION = "3.8";

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

    /**
     * Sets muleContext
     * 
     * @param value Value to set
     */
    public void setMuleContext(MuleContext value) {
        this.muleContext = value;
    }

    /**
     * Retrieves muleContext
     * 
     */
    public MuleContext getMuleContext() {
        return this.muleContext;
    }

    /**
     * Sets poolingProfile
     * 
     * @param value Value to set
     */
    public void setPoolingProfile(PoolingProfile value) {
        this.poolingProfile = value;
    }

    /**
     * Retrieves poolingProfile
     * 
     */
    public PoolingProfile getPoolingProfile() {
        return this.poolingProfile;
    }

    /**
     * Sets retryPolicyTemplate
     * 
     * @param value Value to set
     */
    public void setRetryPolicyTemplate(RetryPolicyTemplate value) {
        this.retryPolicyTemplate = value;
    }

    /**
     * Retrieves retryPolicyTemplate
     * 
     */
    public RetryPolicyTemplate getRetryPolicyTemplate() {
        return this.retryPolicyTemplate;
    }

    public void initialise() {
        connectionPool = new DevkitGenericKeyedObjectPool(new ConnectionManagementConnectorFactory(this), poolingProfile);
        if (retryPolicyTemplate == null) {
            retryPolicyTemplate = muleContext.getRegistry().lookupObject(MuleProperties.OBJECT_DEFAULT_RETRY_POLICY_TEMPLATE);
        }
    }

    @Override
    public void dispose() {
        try {
            connectionPool.close();
        } catch (Exception e) {
        }
    }

    public GoogleBigQueryConnectorConnectionManagementAdapter acquireConnection(ConnectionManagementConfigGoogleBigQueryConnectorConnectionKey key)
        throws Exception
    {
        return ((GoogleBigQueryConnectorConnectionManagementAdapter) connectionPool.borrowObject(key));
    }

    public void releaseConnection(ConnectionManagementConfigGoogleBigQueryConnectorConnectionKey key, GoogleBigQueryConnectorConnectionManagementAdapter connection)
        throws Exception
    {
        connectionPool.returnObject(key, connection);
    }

    public void destroyConnection(ConnectionManagementConfigGoogleBigQueryConnectorConnectionKey key, GoogleBigQueryConnectorConnectionManagementAdapter connection)
        throws Exception
    {
        connectionPool.invalidateObject(key, connection);
    }

    /**
     * Returns true if this module implements such capability
     * 
     */
    public boolean isCapableOf(ModuleCapability capability) {
        if (capability == ModuleCapability.LIFECYCLE_CAPABLE) {
            return true;
        }
        if (capability == ModuleCapability.CONNECTION_MANAGEMENT_CAPABLE) {
            return true;
        }
        return false;
    }

    @Override
    public<P >ProcessTemplate<P, GoogleBigQueryConnectorConnectionManagementAdapter> getProcessTemplate() {
        return new ConnectionManagementProcessTemplate(this, muleContext);
    }

    @Override
    public ConnectionManagementConfigGoogleBigQueryConnectorConnectionKey getDefaultConnectionKey() {
        return new ConnectionManagementConfigGoogleBigQueryConnectorConnectionKey(getServiceAccountId(), getServiceAccountFile());
    }

    @Override
    public ConnectionManagementConfigGoogleBigQueryConnectorConnectionKey getEvaluatedConnectionKey(MuleEvent event)
        throws Exception
    {
        if (event!= null) {
            final String _transformedServiceAccountId = ((String) evaluateAndTransform(muleContext, event, this.getClass().getDeclaredField("serviceAccountId").getGenericType(), null, getServiceAccountId()));
            if (_transformedServiceAccountId == null) {
                throw new UnableToAcquireConnectionException("Parameter serviceAccountId in method connect can't be null because is not @Optional");
            }
            final String _transformedServiceAccountFile = ((String) evaluateAndTransform(muleContext, event, this.getClass().getDeclaredField("serviceAccountFile").getGenericType(), null, getServiceAccountFile()));
            if (_transformedServiceAccountFile == null) {
                throw new UnableToAcquireConnectionException("Parameter serviceAccountFile in method connect can't be null because is not @Optional");
            }
            return new ConnectionManagementConfigGoogleBigQueryConnectorConnectionKey(_transformedServiceAccountId, _transformedServiceAccountFile);
        }
        return getDefaultConnectionKey();
    }

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

    @Override
    public ConnectionManagementConfigGoogleBigQueryConnectorConnectionKey getConnectionKey(MessageProcessor messageProcessor, MuleEvent event)
        throws Exception
    {
        return getEvaluatedConnectionKey(event);
    }

    @Override
    public ConnectionManagementConnectionAdapter newConnection() {
        ConnectorConfigGoogleBigQueryConnectorAdapter connection = new ConnectorConfigGoogleBigQueryConnectorAdapter();
        return connection;
    }

    @Override
    public ConnectionManagementConnectorAdapter newConnector(ConnectionManagementConnectionAdapter<ConnectorConfig, ConnectionManagementConfigGoogleBigQueryConnectorConnectionKey> connection) {
        GoogleBigQueryConnectorConnectionManagementAdapter connector = new GoogleBigQueryConnectorConnectionManagementAdapter();
        connector.setConfig(connection.getStrategy());
        return connector;
    }

    public ConnectionManagementConnectionAdapter getConnectionAdapter(ConnectionManagementConnectorAdapter adapter) {
        GoogleBigQueryConnectorConnectionManagementAdapter connector = ((GoogleBigQueryConnectorConnectionManagementAdapter) adapter);
        ConnectionManagementConnectionAdapter strategy = ((ConnectionManagementConnectionAdapter) connector.getConfig());
        return strategy;
    }

    public TestResult test() {
        try {
            ConnectorConfigGoogleBigQueryConnectorAdapter strategy = ((ConnectorConfigGoogleBigQueryConnectorAdapter) newConnection());
            MuleContextAwareManager.setMuleContext(strategy, this.muleContext);
            LifeCycleManager.executeInitialiseAndStart(strategy);
            ConnectionManagementConnectorAdapter connectorAdapter = newConnector(strategy);
            MuleContextAwareManager.setMuleContext(connectorAdapter, this.muleContext);
            LifeCycleManager.executeInitialiseAndStart(connectorAdapter);
            strategy.test(getDefaultConnectionKey());
            return new DefaultTestResult(org.mule.common.Result.Status.SUCCESS);
        } catch (ConfigurationWarning warning) {
            return ((DefaultTestResult) ConnectivityTestingErrorHandler.buildWarningTestResult(warning));
        } catch (Exception e) {
            return ((DefaultTestResult) ConnectivityTestingErrorHandler.buildFailureTestResult(e));
        }
    }

}
