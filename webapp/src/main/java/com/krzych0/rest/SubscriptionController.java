package com.krzych0.rest;

import com.imgtec.creator.pojo.Hateoas;
import com.imgtec.creator.pojo.Notifications;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
public class SubscriptionController {
  final Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());

  @RequestMapping(value = "/notifyClientConnected", method = RequestMethod.POST)
  public ResponseEntity<Void> clientConnected(@RequestBody Notifications<Hateoas> notifications) {
    logger.info("notifyClientConnected: {}", notifications);
    //TODO: implement
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @RequestMapping(value = "/notifyClientUpdated", method = RequestMethod.POST)
  public ResponseEntity<Void> clientUpdated(@RequestBody Notifications<Hateoas> notifications) {
    logger.info("notifyClientUpdated: {}", notifications);
    //TODO: implement
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @RequestMapping(value = "/notifyClientDisconnected", method = RequestMethod.POST)
  public ResponseEntity<Void> clientDisconnected(@RequestBody Notifications<Hateoas> notifications) {
    logger.info("notifyClientDisconnected: {}", notifications);
    //TODO: implement
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @RequestMapping(value = "/notifyObservation", method = RequestMethod.POST)
  public ResponseEntity<Void> observed(@RequestBody Notifications<Hateoas> notifications) {
    logger.info("notifyObservation: {}", notifications);
    //TODO: implement
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
