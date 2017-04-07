package com.krzych0.service;

import com.imgtec.creator.CreatorClient;
import com.imgtec.creator.auth.AuthTokenProvider;
import com.imgtec.creator.response.GsonResponseHandler;
import com.imgtec.creator.response.ResponseHandler;
import com.imgtec.creator.utils.GsonDeserializer;
import com.krzych0.config.AccessKeysWrapper;
import okhttp3.HttpUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 *
 */
@Configuration
public class ServiceConfiguration {

  @Bean
  public AccessKeysWrapper getAccessKeysWrapper() {
    return new AccessKeysWrapper();
  }

  @Bean
  HttpUrl getUrl() {
    return HttpUrl.parse("https://deviceserver.creatordev.io");
  }

  @Bean
  public CreatorClient getCreatorClient(HttpUrl url) {
    AuthTokenProvider authTokenProvider = new AuthTokenProvider();
    ResponseHandler responseHandler = new GsonResponseHandler(new GsonDeserializer());

    CreatorClient client = new CreatorClient
        .Builder()
        .setUrl(url)
        .setTokenProvider(authTokenProvider)
        .setResponseHandler(responseHandler)
        .setSslSocketFactory(
            LenientSSLSocketFactory.getSocketFactory(),
            new NaiveTrustManager())
        .build();

    return client;
  }

  @Bean
  public CreatorClientProvider getClientProvider(AccessKeysWrapper keys) {
    return new CreatorClientProvider();
  }


  static class NaiveTrustManager implements X509TrustManager {

    @Override
    public void checkClientTrusted(X509Certificate[] cert, String authType)
        throws CertificateException {
      // do nothing
    }

    @Override
    public void checkServerTrusted(X509Certificate[] cert, String authType)
        throws CertificateException {
      // do nothing
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
      return new X509Certificate[0];
    }
  }


  static class LenientSSLSocketFactory {

    private static final Logger LOGGER =
        LoggerFactory.getLogger(LenientSSLSocketFactory.class.getSimpleName());

    private LenientSSLSocketFactory() {
      // created to hide default constructor
    }

    public static SSLSocketFactory getSocketFactory() {
      try {
        TrustManager[] tm = new TrustManager[]{new NaiveTrustManager()};
        SSLContext context = SSLContext.getInstance("SSL");
        context.init(new KeyManager[0], tm, new SecureRandom());
        return context.getSocketFactory();
      } catch (NoSuchAlgorithmException e) {
        LOGGER.warn("Couldn't create SSLSocketFactory", e);
      } catch (KeyManagementException e) {
        LOGGER.warn("Couldn't create SSLSocketFactory", e);
      }
      return null;
    }
  }


}
