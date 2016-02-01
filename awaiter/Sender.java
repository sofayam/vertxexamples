package awaiter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.ext.sync.SyncVerticle;

/*
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public class Sender extends SyncVerticle {



  @Override
  public void start() throws Exception {
    EventBus eb = vertx.eventBus();

    // Send a message every second

    vertx.setPeriodic(1000, v -> {

      Message<String> reply = awaitResult
        (h -> eb.send("ping-address", "ping", h));

      System.out.println("Received reply " + reply.body());

//      eb.send("ping-address", "ping!", reply -> {
//        if (reply.succeeded()) {
//          System.out.println("Received reply " + reply.result().body());
//        } else {
//          System.out.println("No reply");
//        }
//      });

    });
  }
}
