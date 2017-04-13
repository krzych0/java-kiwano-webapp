package com.krzych0.service;

import com.imgtec.creator.CreatorClient;
import com.imgtec.creator.pojo.Api;
import com.imgtec.creator.pojo.ResourceCreated;
import com.imgtec.creator.pojo.Subscription;
import com.imgtec.creator.pojo.Subscriptions;
import com.imgtec.creator.subscription.SubscriptionsManager;
import com.krzych0.config.AccessKeysWrapper;
import okhttp3.HttpUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 *
 */

public class CreatorClientProvider {

  final Logger logger = LoggerFactory.getLogger(CreatorClientProvider.class.getSimpleName());

  @Autowired
  AccessKeysWrapper akw;

  @Autowired
  CreatorClient client;


  public void login() {
    try {
      client.getAuthManager().authorize(akw.getKey(), akw.getSecret());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  public void subscribe(String webhookUrl) {
    try {

      HttpUrl url = client.getUrl();
      Api api = client.get(url.toString(), Api.class);

      SubscriptionsManager manager = client.getSubscriptionsManager();
      Subscriptions subscriptions = manager.getSubscriptions(api.getLinkByRel("subscriptions").getHref());

      subscribeClientConnected(manager, subscriptions, webhookUrl+"/notifyClientConnected",
          SubscriptionsManager.SubscriptionTypes.CLIENT_CONNECTED.toString());

      subscribeClientConnected(manager, subscriptions, webhookUrl+"/notifyClientDisconnected",
          SubscriptionsManager.SubscriptionTypes.CLIENT_DISCONNECTED.toString());

      subscribeClientConnected(manager, subscriptions, webhookUrl+"/notifyClientUpdated",
          SubscriptionsManager.SubscriptionTypes.CLIENT_UPDATED.toString());

      subscribeClientConnected(manager, subscriptions, webhookUrl+"/notifyObservation",
          SubscriptionsManager.SubscriptionTypes.OBSERVATION.toString());

    } catch (Exception e) {
      logger.warn("Subscribe failed!", e);
    }
  }

  private void subscribeClientConnected(SubscriptionsManager manager, Subscriptions subscriptions,
                                        String webhook, String subscriptionType) {
    Subscription subscription = new Subscription();
    subscription.setSubscriptionType(subscriptionType);
    subscription.setUrl(webhook);

    try {
      ResourceCreated response = manager.createSubscription(subscriptions, subscription);
    } catch (IOException e) {
      logger.warn("Subscribe {} failed!",  e);
    }
    logger.debug(" => Subscription {} created.", subscriptionType);
  }
}
