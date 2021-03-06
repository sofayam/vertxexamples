package separateglobals;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;

/*
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public class Receiver extends AbstractVerticle {


  @Override
  public void start() throws Exception {

      EventBus eb = vertx.eventBus();

      eb.consumer("ping-address", message -> {
	      int res = Globals.inc(100);
	      System.out.println("Received message: " + message.body() + " " + res);
	      // Now send back reply
	      message.reply("pong!");
	  });

      System.out.println("Receiver ready!");
  }
}
