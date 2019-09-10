package mysql;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.sql.SQLClient;
import io.vertx.mysqlclient.MySQLClient;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.mysqlclient.impl.MySQLRowImpl;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.SqlConnection;
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
        MySQLConnectOptions connectOptions = new MySQLConnectOptions()
                .setPort(3306)
                .setHost("localhost")
                .setDatabase("the-db")
                .setUser("root")
                .setPassword("pass@123");
//
        // Pool options
        PoolOptions poolOptions = new PoolOptions()
                .setMaxSize(5);
//
        // Create the client pool
        MySQLPool client = MySQLPool.pool(connectOptions, poolOptions);

        // A simple query
        client.query("SELECT * FROM users WHERE fname='raj'", ar -> {
            if (ar.succeeded()) {
                RowSet result = ar.result();
                System.out.println("Got " + result.size() + " rows ");
                System.out.println("data ");

//                ar.result()
                for (Row row : result) {
                    System.out.println("User " + ((Integer) ((MySQLRowImpl) row).get(0)).intValue());
                    System.out.println("User " + ((String) ((MySQLRowImpl) row).get(1)).toString());
                    // ((Integer) ((MySQLRowImpl) row).get(0)).intValue()
                }


            } else {
                System.out.println("Failure: " + ar.cause().getMessage());
            }

            // Now close the pool
            client.close();
        });

//        // Get a connection from the pool
//        client.getConnection(ar1 -> {
//
//            if (ar1.succeeded()) {
//
//                System.out.println("Connected");
//
//                // Obtain our connection
//                SqlConnection conn = ar1.result();
//
//                // All operations execute on the same connection
//                conn.query("SELECT * FROM users WHERE id='julien'", ar2 -> {
//                    if (ar2.succeeded()) {
//                        conn.query("SELECT * FROM users WHERE id='emad'", ar3 -> {
//                            // Release the connection to the pool
//                            System.out.println("Yesy");
//                            System.out.println(ar3);
//                            conn.close();
//                        });
//                    } else {
//                        // Release the connection to the pool
//                        conn.close();
//                    }
//                });
//            } else {
//                System.out.println("Could not connect: " + ar1.cause().getMessage());
//            }
//        });

    }
}