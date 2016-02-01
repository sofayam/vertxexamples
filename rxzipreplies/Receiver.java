package rxzipreplies;

import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.eventbus.EventBus;

import java.util.Random;

/*
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
public class Receiver extends AbstractVerticle {


  @Override
  public void start() throws Exception {

    Random random1 = new Random();
    EventBus eb = vertx.eventBus();

    eb.consumer("heatsensor1").
        toObservable().
        subscribe(message -> {
          message.reply(9 + random1.nextInt(5));
        });

    eb.consumer("heatsensor2").
        toObservable().
        subscribe(message -> {
          message.reply(10 + random1.nextInt(3));
        });
  }
}
