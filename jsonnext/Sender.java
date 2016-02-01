package jsonnext;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;

public class Sender extends AbstractVerticle {

  @Override
  public void start() throws Exception {
      EventBus eb = vertx.eventBus();
      eb.consumer("next-address", 
		  message -> {
		      vertx.setTimer(1000, 
				  id -> {
					 eb.send("json-address", message.body());}
				  );
			  }
		  );
  }
}
