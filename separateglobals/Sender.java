package separateglobals;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.DeploymentOptions;
import java.util.function.Consumer;

import java.util.List;
import java.util.Arrays;

/*
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public class Sender extends AbstractVerticle {

    public static void main(String[] args) {

	DeploymentOptions senderOptions = new DeploymentOptions();
	DeploymentOptions receiverOptions = new DeploymentOptions();

	// Does not work if you specify separateglobals.Globals explicitly here
	List<String> classNames = Arrays.asList("separateglobals.*");

	senderOptions.setIsolationGroup("sender");
	senderOptions.setIsolatedClasses(classNames);
       
	Vertx vertx = Vertx.vertx(); 
	// you will get an error that the verticle has already been defined 
	// if you do not use a string here rather than a class
	vertx.deployVerticle("separateglobals.Sender", senderOptions); 
	vertx.deployVerticle(new Receiver(), receiverOptions);		 
    };

  @Override
  public void start() throws Exception {
      EventBus eb = vertx.eventBus();

      // Send a message every second

      vertx.setPeriodic(1000, v -> {

      eb.send("ping-address", "ping!", reply -> {
        if (reply.succeeded()) {
	  int res =  Globals.inc(1);
          System.out.println("Received reply " + reply.result().body() + " " + res);
        } else {
          System.out.println("No reply");
        }
      });

    });
  }
}
