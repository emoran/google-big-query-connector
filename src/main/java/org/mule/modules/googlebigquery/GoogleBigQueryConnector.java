package org.mule.modules.googlebigquery;

import org.mule.api.annotations.Config;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Processor;

import org.mule.modules.googlebigquery.config.ConnectorConfig;

@Connector(name="google-big-query", friendlyName="GoogleBigQuery")
public class GoogleBigQueryConnector {

    @Config
    ConnectorConfig config;

    /**
     * Custom processor
     *
     * @param friend Name to be used to generate a greeting message.
     * @return A greeting message
     */
    @Processor
    public String Query(String queryString) {
     
        return "test";
    }

    public ConnectorConfig getConfig() {
        return config;
    }

    public void setConfig(ConnectorConfig config) {
        this.config = config;
    }

}