package jsondata;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;


/*
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public class Sender extends AbstractVerticle {



  @Override
  public void start() throws Exception {
    EventBus eb = vertx.eventBus();

    // Send a message every second

    JsonObject jo = new JsonObject("{\"a\": 1, \"b\": \"foo\"}");
    System.out.println(jo);

    vertx.setPeriodic(1000, v -> {

	    eb.send("json-address", jo, reply -> {
		    if (reply.succeeded()) {
			System.out.println("Received reply " + reply.result().body());
		    } else {
			System.out.println("No reply");
		    }
		});

	});
  }
}
