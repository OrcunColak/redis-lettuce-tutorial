package reactive;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.reactive.RedisReactiveCommands;
import reactor.core.publisher.Mono;

public class ReactiveExample {

    public static void main() {
        try (RedisClient redisClient = RedisClient.create("redis://localhost:6379");
             StatefulRedisConnection<String, String> connection = redisClient.connect()) {

            RedisReactiveCommands<String, String> reactiveCommands = connection.reactive();

            Mono<String> set = reactiveCommands.set("key", "Hello, Redis!");
            Mono<String> get = reactiveCommands.get("key");

            set.subscribe(System.out::println);
            get.subscribe(System.out::println);
        }
    }
}
