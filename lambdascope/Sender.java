package lambdascope;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;


public class Sender extends AbstractVerticle {
  JsonObject jo = new JsonObject("{\"a\": 1, \"b\": \"foo\"}");
  @Override
  public void start() throws Exception {
    // JsonObject jo = new JsonObject("{\"a\": 1, \"b\": \"foo\"}");
    EventBus eb = vertx.eventBus();

    // Send a message every second


    System.out.println(jo);
    System.out.println(Thread.currentThread().getId());
    vertx.setPeriodic(1000, v -> {
	    System.out.println(Thread.currentThread().getId());
	    eb.send("json-address", jo, reply -> {
		    System.out.println(Thread.currentThread().getId());
		    if (reply.succeeded()) {
			System.out.println("Received reply " + reply.result().body());
			jo = (JsonObject)reply.result().body();
		    } else {
			System.out.println("No reply");
		    }
		});

	});
  }
}
