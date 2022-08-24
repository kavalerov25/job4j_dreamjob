package ru.job4j.dreamjob.store;

import ru.job4j.dreamjob.model.Post;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class PostStore {
    private static final PostStore INST = new PostStore();
    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();
    AtomicInteger idCount = new AtomicInteger();

    private PostStore() {
        posts.put(idCount.incrementAndGet(), new Post(idCount.get(), "Junior Java Job", "java developer", LocalDate.now()));
        posts.put(idCount.incrementAndGet(), new Post(idCount.get(), "Middle Java Job", "java developer", LocalDate.now()));
        posts.put(idCount.incrementAndGet(), new Post(idCount.get(), "Senior Java Job", "java developer", LocalDate.now()));
    }

    public static PostStore instOf() {
        return INST;
    }

    public Collection<Post> findAll() {
        return posts.values();
    }

    public void add(Post post) {
        post.setId(idCount.incrementAndGet());
        posts.put(post.getId(), post);
    }
}