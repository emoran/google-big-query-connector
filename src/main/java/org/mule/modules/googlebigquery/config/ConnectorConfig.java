package org.mule.modules.googlebigquery.config;

import org.mule.api.annotations.components.ConnectionManagement;
import org.mule.api.annotations.display.FriendlyName;
import org.mule.api.annotations.display.Password;
import org.mule.api.annotations.display.Placement;
import org.mule.api.annotations.TestConnectivity;
import org.mule.api.annotations.Configurable;
import org.mule.api.annotations.Connect;
import org.mule.api.annotations.ValidateConnection;
import org.mule.api.annotations.ConnectionIdentifier;
import org.mule.api.annotations.Disconnect;
import org.mule.api.annotations.param.ConnectionKey;
import org.mule.api.annotations.param.Default;
import org.mule.api.annotations.param.Optional;
import org.mule.modules.googlebigquery.GoogleBigQueryConnector;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mule.api.ConnectionException;
import org.mule.api.ConnectionExceptionCode;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;

@ConnectionManagement(friendlyName = "Configuration")
public class ConnectorConfig {
    

	private static final Log logger = LogFactory.getLog(ConnectorConfig.class);
	GoogleCredentials credentials;
	public BigQuery bigquery;
	
	
	
	public String getProxyHost() {
		return proxyHost;
	}

	public void setProxyHost(String proxyHost) {
		this.proxyHost = proxyHost;
	}

	public Integer getProxyPort() {
		return proxyPort;
	}

	public void setProxyPort(Integer proxyPort) {
		this.proxyPort = proxyPort;
	}

	/**
	  * Hostname of the proxy. If this property is not set then no proxy is used, otherwise proxy is going to be used but host can not be empty.
	  */
	  @Configurable
	  @Optional
	  @Default(value="http://httpproxy.smfc.twitter.com")
	  @Placement(group = "Proxy Settings")
	  @FriendlyName(value = "Host")
	  private String proxyHost;

	  /**
	  * Port of the proxy. If host is set then this property has to be set and can not be a negative number.
	  */
	  @Configurable
	  @Optional
	  @Default("3128")
	  @Placement(group = "Proxy Settings")
	  @FriendlyName(value = "Port")
	  private Integer proxyPort;
	  	
	    public String getProxyUserName() {
		return proxyUserName;
	}

	public void setProxyUserName(String proxyUserName) {
		this.proxyUserName = proxyUserName;
	}

	public String getProxyPassword() {
		return proxyPassword;
	}

	public void setProxyPassword(String proxyPassword) {
		this.proxyPassword = proxyPassword;
	}

		/**
		  * Port of the proxy. If host is set then this property has to be set and can not be a negative number.
		  */
		  @Configurable
		  @Optional
		  @Placement(group = "Proxy Settings")
		  @FriendlyName(value = "User Name")
		  private String proxyUserName;
		  
  		/**
	     * Password used to authenticate against the proxy.
	     */
	    @Configurable
	    @Optional
	    @Password
	    @Placement(group = "Proxy Settings")
	    @FriendlyName(value = "Password")
	    private String proxyPassword;
	    

	

    /**
     * Connect
     *
     * @param username A username
     * @param password A password
     * @throws ConnectionException
     * @throws IOException 
     */
    @Connect
    @TestConnectivity
    public void connect(@ConnectionKey String serviceAccountId, @ConnectionKey String serviceAccountFile)
        throws ConnectionException {
       
    		try {
    			configureProxy();
    			File file = new File(getClass().getClassLoader().getResource(serviceAccountFile).getFile());
    			FileInputStream serviceAccountStream = new FileInputStream(file);    			
    			credentials = ServiceAccountCredentials.fromStream(serviceAccountStream);    	    			
    			bigquery = BigQueryOptions.newBuilder().setCredentials(credentials).build().getService();	    			
    		}
    		catch(Exception er) {
    			logger.info("Error connecting to Big Query: "+er.getMessage());
    			er.printStackTrace();
    		}
    		
    }
    
    public void configureProxy() throws IOException {
    		
    		logger.info("Attempt to setup Proxy");
        final String user = this.getProxyUserName();
        final String password = this.getProxyPassword();
        Authenticator.setDefault(new Authenticator() {
          protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(user, password.toCharArray());
          }
        });
        
        System.setProperty("https.proxyHost",this.getProxyHost());
        System.setProperty("https.proxyPort", Integer.toString(this.getProxyPort()));
        System.setProperty("jdk.http.auth.tunneling.disabledSchemes", "");
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