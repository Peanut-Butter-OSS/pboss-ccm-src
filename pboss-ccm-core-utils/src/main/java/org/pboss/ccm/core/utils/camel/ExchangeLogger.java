package org.pboss.ccm.core.utils.camel;

import java.util.Collection;
import java.util.Map;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExchangeLogger implements Processor {

  Logger logger = LoggerFactory.getLogger(ExchangeLogger.class);

  public void process(Exchange exchange) throws Exception {

    // if (debug == true) then do all this below
    logExchange(exchange);

    Exception exception = (Exception) exchange.getProperty(Exchange.EXCEPTION_CAUGHT);
    if (exception != null)
      exception.printStackTrace(); // print to server log file
  }

  private void logExchange(Exchange exchange) throws Exception {
    Map<String, ?> headers = exchange.getIn().getHeaders();
    Object body = exchange.getIn().getBody(Object.class);
    Map<String, ?> props = exchange.getProperties();
    CamelContext vContext = exchange.getContext();
    Collection<String> att = exchange.getIn().getAttachmentNames();

    logger.info("=============H=E=A=D=E=R=S======================");
    outMap(headers, "header");
    logger.info("=============P=R=O=P=S==========================");
    outMap(props, "prop");
    logger.info("=============B=O=D=Y============================");
    logger.info(">> " + body.toString());
    logger.info("=============C=O=N=T=E=X=T======================");
    logger.info(">> " + vContext.toString());
    logger.info("=============A=T=T=A=C=H=M=E=N=T=S==============");
    outMap(att, "attachments");
    logger.info("================================================");
  }

  private void outMap(Map<String, ?> m, String mapName) throws Exception {
    Collection<String> vals = m.keySet();
    for (String val : vals) {
      logger.info(mapName + " value: " + val + " :: " + m.get(val));
    }
  }

  private void outMap(Collection<String> vals, String name) throws Exception {
    for (String val : vals) {
      logger.info(name + " values: " + val);
    }
  }
}
