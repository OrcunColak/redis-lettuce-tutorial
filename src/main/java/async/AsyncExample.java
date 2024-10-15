package async;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;

public class AsyncExample {

    public static void main() throws Exception {
        try (RedisClient redisClient = RedisClient.create("redis://localhost:6379");
            StatefulRedisConnection<String, String> connection = redisClient.connect()) {

            RedisAsyncCommands<String, String> asyncCommands = connection.async();

            asyncCommands.set("key", "Hello, Redis!").thenAccept(System.out::println);
            asyncCommands.get("key").thenAccept(System.out::println);

            // Wait for the async operations to complete
            Thread.sleep(1000);
        }
    }
}
