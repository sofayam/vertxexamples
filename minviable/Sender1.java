package minviable;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.DeploymentOptions;
import java.util.function.Consumer;

public class Sender1 extends AbstractVerticle {

    public static void main(String[] args) {

	VertxOptions options = new VertxOptions().setClustered(true);
	DeploymentOptions doptions = new DeploymentOptions();
       
	Vertx.clusteredVertx(options, res -> {
		if (res.succeeded()) {
		    Vertx vertx = res.result();
		    vertx.deployVerticle(new Sender1(), new DeploymentOptions());
		} else {
		    res.cause().printStackTrace();
		}
	    });
	try {
	    System.in.read();
	} catch (Throwable t) {
		t.printStackTrace();
	}
	    
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
