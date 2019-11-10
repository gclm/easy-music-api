package club.gclmit.chaos.music.cofig;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

/**
 * <p>
 * RestTemplate 模板配置
 * </p>
 *
 * @author: gclm
 * @date: 2019/11/9 16:16
 * @version: V1.0
 */
@Configuration
public class RestTemplateConfig {

    /**
     * <p>
     *  配置 HttpComponentsClientHttpRequestFactory
     *  1. 配置过期时间
     *  2. 配置SSL
     * </p>
     * @author gclm
     * @date 2019/11/9 17:16
     * @return: org.springframework.http.client.HttpComponentsClientHttpRequestFactory
     * @throws
     */
    @Bean
    public HttpComponentsClientHttpRequestFactory requestFactory() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {

        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();

        /**
         * 配置过期时间
         */
        httpRequestFactory.setConnectionRequestTimeout(3000);
        httpRequestFactory.setConnectTimeout(3000);
        httpRequestFactory.setReadTimeout(3000);

        /**
         * 配置 ssl 证书
         */
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                .loadTrustMaterial(null, acceptingTrustStrategy)
                .build();

        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(csf)
                .build();

        httpRequestFactory.setHttpClient(httpClient);

        return httpRequestFactory;
    }

    /**
     * <p>
     *  配置 restTemplate
     * </p>
     *
     * @title restTemplate
     * @author gclm
     * @param: requestFactory
     * @date 2019/11/9 17:17
     * @return: org.springframework.web.client.RestTemplate
     * @throws
     */
    @Bean
    public RestTemplate restTemplate(HttpComponentsClientHttpRequestFactory requestFactory){
        return new RestTemplate(requestFactory);
    }

}
