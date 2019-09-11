package mysql;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.mysqlclient.MySQLClient;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.mysqlclient.impl.MySQLRowImpl;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.SqlConnection;
import sun.security.provider.certpath.Vertex;
//import io.vertx.ext.asyncsql;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
//        MySQLConnectOptions connectOptions = new MySQLConnectOptions()
//                .setPort(3306)
//                .setHost("localhost")
//                .setDatabase("the-db")
//                .setUser("root")
//                .setPassword("pass@123");
////
//        // Pool options
//        PoolOptions poolOptions = new PoolOptions()
//                .setMaxSize(5);
////
//        // Create the client pool
//        MySQLPool client = MySQLPool.pool(connectOptions, poolOptions);

        Vertx vertx = Vertx.vertx();
        HttpServer httpServer = vertx.createHttpServer();

        Router router = Router.router(vertx);

        // This body handler will be called for all routes
        router.route().handler(BodyHandler.create());

        Route hamdler1 = router
                .route("/hello")
                .handler(routingContext -> {
                    HttpServerResponse httpServerResponse = routingContext.response();
                    httpServerResponse.end("yes temp");
                    JsonObject bodyHandler = routingContext.getBodyAsJson();
                    System.out.println(routingContext.getBody());

                });




        httpServer
                .requestHandler(router::accept)
                .listen(3000);


    }
}