package jsondata;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;

/*
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public class Receiver extends AbstractVerticle {


  @Override
  public void start() throws Exception {

      EventBus eb = vertx.eventBus();

      eb.consumer("json-address", message -> {

	      System.out.println("Received message: " + message.body());
	      JsonObject jo = (JsonObject)message.body();

	      Integer i = jo.getInteger("a");
	      jo.put("a", i+1);
	      message.reply(jo);
	  });

      System.out.println("Receiver ready!");
  }
}
