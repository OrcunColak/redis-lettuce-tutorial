package sync;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

public class SynExample {

    public static void main() {
        // Create a Redis client
        try (RedisClient redisClient = RedisClient.create("redis://localhost:6379");
             StatefulRedisConnection<String, String> connection = redisClient.connect()) {

            // Obtain synchronous commands
            RedisCommands<String, String> syncCommands = connection.sync();

            // Use the commands
            syncCommands.set("key", "Hello, Redis!");
            String value = syncCommands.get("key");

            System.out.println(value);  // Output: Hello, Redis!
        }
    }
}
