package mysql;

import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.Tuple;

import java.util.ArrayList;
import java.util.List;
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
//        client.query("SELECT * FROM users WHERE fname='raj'", ar -> {
//            if (ar.succeeded()) {
//                RowSet result = ar.result();
//                System.out.println("Got " + result.size() + " rows ");
//                System.out.println("data ");
//
////                ar.result()
//                for (Row row : result) {
//                    System.out.println("User " + ((Integer) ((MySQLRowImpl) row).get(0)).intValue());
//                    System.out.println("User " + ((String) ((MySQLRowImpl) row).get(1)).toString());
//                    // ((Integer) ((MySQLRowImpl) row).get(0)).intValue()
//                }
//
//
//            } else {
//                System.out.println("Failure: " + ar.cause().getMessage());
//            }
//
//            // Now close the pool
//            client.close();
//        });




        // Single
//        client.preparedQuery("INSERT INTO users (id, fname) VALUES (?, ?)", Tuple.of(4323, "Viet"), ar -> {
//            if (ar.succeeded()) {
//                RowSet rows = ar.result();
//                System.out.println(rows.rowCount());
//            } else {
//                System.out.println("Failure: " + ar.cause().getMessage());
//            }
//
//            // Now close the pool
//            client.close();
//        });


        // Multiple

        List<Tuple> batch = new ArrayList<>();
        batch.add(Tuple.of(2324, "Viet32"));
        batch.add(Tuple.of(32341, "Ashi32"));

// Execute the prepared batch
        client.preparedBatch("INSERT INTO USERS (id, fname) VALUES (?, ?)", batch, res -> {
            if (res.succeeded()) {

                // Process rows
                RowSet rows = res.result();
            } else {
                System.out.println("Batch failed " + res.cause());
            }

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