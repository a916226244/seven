package com.seven.wx.provider.https;

import org.eclipse.jetty.http.HttpScheme;
import org.eclipse.jetty.http.HttpVersion;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * Created by xiaozhiping on 18/3/18.
 */
@Service
public class HttpsConfiguration {

    @Autowired
    private HttpsProperties properties;

    @Value("${server.port}")
    private int httpPort;

//    @Bean
//    public EmbeddedServletContainerCustomizer servletContainerCustomizer() {
//        return new EmbeddedServletContainerCustomizer() {
//
//            @Override
//            public void customize(ConfigurableEmbeddedServletContainer container) {
//                if (container instanceof JettyEmbeddedServletContainerFactory) {
//                    customizeJetty((JettyEmbeddedServletContainerFactory) container);
//                }
//            }
//
//            private void customizeJetty(JettyEmbeddedServletContainerFactory container) {
//
//                container.addServerCustomizers((Server server) -> {
//                    // HTTP
//                    ServerConnector connector = new ServerConnector(server);
//                    connector.setPort(httpPort);
//
//                    // HTTPS
//                    SslContextFactory sslContextFactory = new SslContextFactory();
//                    sslContextFactory.setKeyStorePath(properties.getKeystoreFile());
//                    sslContextFactory.setKeyStorePassword(properties.getKeystorePassword());
//                    sslContextFactory.setKeyStoreType("JKS");
//                    sslContextFactory.setCipherComparator();
//
//                    HttpConfiguration config = new HttpConfiguration();
//                    config.setSecureScheme(HttpScheme.HTTPS.asString());
//                    config.addCustomizer(new SecureRequestCustomizer());
//
//                    ServerConnector sslConnector = new ServerConnector(
//                            server,
//                            new SslConnectionFactory(sslContextFactory, HttpVersion.HTTP_1_1.asString()),
//                            new HttpConnectionFactory(config));
//                    sslConnector.setPort(properties.getPort());
//
//                    server.setConnectors(new Connector[]{connector, sslConnector});
//                });
//            }
//        };
//    }
}
