package org.mule.modules.googlebigquery.config;

import org.mule.api.annotations.components.ConnectionManagement;
import org.mule.api.annotations.TestConnectivity;
import org.mule.api.annotations.Connect;
import org.mule.api.annotations.ValidateConnection;
import org.mule.api.annotations.ConnectionIdentifier;
import org.mule.api.annotations.Disconnect;
import org.mule.api.annotations.param.ConnectionKey;
import org.mule.api.ConnectionException;
import org.mule.api.ConnectionExceptionCode;
import org.mule.api.annotations.display.Password;
import org.mule.api.annotations.display.Placement;
import org.mule.api.annotations.Configurable;
import org.mule.api.annotations.param.Default;
import org.mule.api.annotations.param.Optional;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;

import java.io.File;
import java.io.FileInputStream;

@ConnectionManagement(friendlyName = "Configuration")
public class ConnectorConfig {
    
	GoogleCredentials credentials;
	public BigQuery bigquery;
	

    /**
     * Connect
     *
     * @param username A username
     * @param password A password
     * @throws ConnectionException
     */
    @Connect
    @TestConnectivity
    public void connect(@ConnectionKey String serviceAccountId, @ConnectionKey String serviceAccountFile)
        throws ConnectionException {
        /*
         * CODE FOR ESTABLISHING A CONNECTION GOES IN HERE
         */
    		try {
    			File file = new File(getClass().getClassLoader().getResource(serviceAccountFile).getFile());
    			FileInputStream serviceAccountStream = new FileInputStream(file);
    			credentials = ServiceAccountCredentials.fromStream(serviceAccountStream);    	
    			bigquery = BigQueryOptions.newBuilder().setCredentials(credentials).build().getService();
    		}
    		catch(Exception er) {
    			throw new ConnectionException(ConnectionExceptionCode.UNKNOWN, "", "Can not connect to the service", er);
    		}
    }

    /**
     * Disconnect
     */
    @Disconnect
    public void disconnect() {
    		
        /*
         * CODE FOR CLOSING A CONNECTION GOES IN HERE
         */
    		bigquery = null;
    }

    /**
     * Are we connected
     */
    @ValidateConnection
    public boolean isConnected() {
        //TODO: Change it to reflect that we are connected.
        return false;
    }

    /**
     * Are we connected
     */
    @ConnectionIdentifier
    public String connectionId() {
        return "001";
    }
    
}