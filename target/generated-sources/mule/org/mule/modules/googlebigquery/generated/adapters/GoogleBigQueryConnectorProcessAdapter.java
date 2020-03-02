
package org.mule.modules.googlebigquery.generated.adapters;

import javax.annotation.Generated;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.devkit.ProcessAdapter;
import org.mule.api.devkit.ProcessTemplate;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.routing.filter.Filter;
import org.mule.modules.googlebigquery.GoogleBigQueryConnector;
import org.mule.security.oauth.callback.ProcessCallback;


/**
 * A <code>GoogleBigQueryConnectorProcessAdapter</code> is a wrapper around {@link GoogleBigQueryConnector } that enables custom processing strategies.
 * 
 */
@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.9.4", date = "2020-03-02T11:45:08-05:00", comments = "Build UNNAMED.2810.4347dd1")
public class GoogleBigQueryConnectorProcessAdapter
    extends GoogleBigQueryConnectorLifecycleInjectionAdapter
    implements ProcessAdapter<GoogleBigQueryConnectorCapabilitiesAdapter>
{


    public<P >ProcessTemplate<P, GoogleBigQueryConnectorCapabilitiesAdapter> getProcessTemplate() {
        final GoogleBigQueryConnectorCapabilitiesAdapter object = this;
        return new ProcessTemplate<P,GoogleBigQueryConnectorCapabilitiesAdapter>() {


            @Override
            public P execute(ProcessCallback<P, GoogleBigQueryConnectorCapabilitiesAdapter> processCallback, MessageProcessor messageProcessor, MuleEvent event)
                throws Exception
            {
                return processCallback.process(object);
            }

            @Override
            public P execute(ProcessCallback<P, GoogleBigQueryConnectorCapabilitiesAdapter> processCallback, Filter filter, MuleMessage message)
                throws Exception
            {
                return processCallback.process(object);
            }

        }
        ;
    }

}
