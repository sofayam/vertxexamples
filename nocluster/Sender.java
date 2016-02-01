package nocluster;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.DeploymentOptions;
import java.util.function.Consumer;

/*
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public class Sender extends AbstractVerticle {

    public static void main(String[] args) {

	DeploymentOptions doptions = new DeploymentOptions();
       
	Vertx vertx = Vertx.vertx(); 

	vertx.deployVerticle(new Sender(), doptions);
	vertx.deployVerticle(new Receiver(), doptions);
		 
    };

  @Override
  public void start() throws Exception {
      EventBus eb = vertx.eventBus();

      // Send a message every second

      vertx.setPeriodic(1000, v -> {

      eb.send("ping-address", "ping!", reply -> {
        if (reply.succeeded()) {
          System.out.println("Received reply " + reply.result().body());
        } else {
          System.out.println("No reply");
        }
      });

    });
  }
}
