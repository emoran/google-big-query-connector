
package org.mule.modules.googlebigquery.generated.config;

import javax.annotation.Generated;
import org.mule.config.MuleManifest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;


/**
 * Registers bean definitions parsers for handling elements in <code>http://www.mulesoft.org/schema/mule/google-big-query</code>.
 * 
 */
@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.9.0", date = "2020-03-04T09:08:24-05:00", comments = "Build UNNAMED.2793.f49b6c7")
public class GoogleBigQueryNamespaceHandler
    extends NamespaceHandlerSupport
{

    private static Logger logger = LoggerFactory.getLogger(GoogleBigQueryNamespaceHandler.class);

    private void handleException(String beanName, String beanScope, NoClassDefFoundError noClassDefFoundError) {
        String muleVersion = "";
        try {
            muleVersion = MuleManifest.getProductVersion();
        } catch (Exception _x) {
            logger.error("Problem while reading mule version");
        }
        logger.error(((((("Cannot launch the mule app, the  "+ beanScope)+" [")+ beanName)+"] within the connector [google-big-query] is not supported in mule ")+ muleVersion));
        throw new FatalBeanException(((((("Cannot launch the mule app, the  "+ beanScope)+" [")+ beanName)+"] within the connector [google-big-query] is not supported in mule ")+ muleVersion), noClassDefFoundError);
    }

    /**
     * Invoked by the {@link DefaultBeanDefinitionDocumentReader} after construction but before any custom elements are parsed. 
     * @see NamespaceHandlerSupport#registerBeanDefinitionParser(String, BeanDefinitionParser)
     * 
     */
    public void init() {
        try {
            this.registerBeanDefinitionParser("config", new GoogleBigQueryConnectorConnectorConfigConfigDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("config", "@Config", ex);
        }
        try {
            this.registerBeanDefinitionParser("query", new QueryDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("query", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("create-data-set", new CreateDataSetDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("create-data-set", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("create-table", new CreateTableDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("create-table", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("insert-records", new InsertRecordsDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("insert-records", "@Processor", ex);
        }
    }

}
