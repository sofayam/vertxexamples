package jsonnext;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;

public class Starter extends AbstractVerticle {

  @Override
  public void start() throws Exception {
    EventBus eb = vertx.eventBus();

    // Prime the pump with the first message

    JsonObject jo = new JsonObject("{\"a\": 1, \"b\": \"foo\"}");
    System.out.println(jo);

    eb.send("next-address", jo);

  }
}
