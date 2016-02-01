package futures;

import io.vertx.core.AbstractVerticle;

public class ExecBlockingExample extends AbstractVerticle {

  @Override
  public void start() throws Exception {

      System.out.println("Verticle start: " +Thread.currentThread().getId());

    vertx.createHttpServer().requestHandler(request -> {

      // Let's say we have to call a blocking API (e.g. JDBC) to execute a query for each
      // request. We can't do this directly or it will block the event loop
      // But you can do this using executeBlocking:

      vertx.<String>executeBlocking(future -> {

        // Do the blocking operation in here

        // Imagine this was a call to a blocking API to get the result
        try {
	  System.out.println("Blocking: " +Thread.currentThread().getId());
          Thread.sleep(500);
        } catch (Exception ignore) {
        }
        String result = "armadillos!";

        future.complete(result);

      }, res -> {

        if (res.succeeded()) {
	  System.out.println("Verticle action: " +Thread.currentThread().getId());
          request.response().putHeader("content-type", "text/plain").end(res.result());

        } else {
          res.cause().printStackTrace();
        }
      });

    }).listen(8080);

  }
}
