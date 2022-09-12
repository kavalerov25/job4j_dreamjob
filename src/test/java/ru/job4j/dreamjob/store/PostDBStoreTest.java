package ru.job4j.dreamjob.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PostDBStoreTest {

    @Test
    public void whenCreatePost() {
        BasicDataSource pool = new Main().loadPool();
        PostDBStore store = new PostDBStore(pool);
        Post post = new Post(0, "Java Job", "description", true, new City());
        /**     City city = new City();
         city.setId(1);
         post.setCity(city);
         **/
        store.add(post);
        Post postInDb = store.findById(post.getId());
        assertThat(postInDb.getName(), is(post.getName()));
    }

    @Test
    public void whenCreateTwoPosts() {
        PostDBStore store = new PostDBStore(new Main().loadPool());
        Post post = new Post(0, "Java Job", "description", true, new City(1, "Москва"));
        Post postTwo = new Post(1, "Python Job", "description", true, new City(2, "СПБ"));
        store.add(post);
        store.add(postTwo);
        Post postInDb = store.findById(postTwo.getId());
        assertThat(postInDb.getName(), is(postTwo.getName()));
    }

    @Test
    public void whenFindAllPosts() {
        PostDBStore store = new PostDBStore(new Main().loadPool());
        Post post = new Post(0, "Java Job", "description", true, new City(1, "Москва"));
        Post postTwo = new Post(1, "Python Job", "description", true, new City(2, "СПБ"));
        int size = store.findAll().size();
        store.add(post);
        store.add(postTwo);
        List<Post> postInDb = store.findAll();
        assertThat(postInDb.size(), is(size + 2));
    }

    @Test
    public void whenUpdatePost() {
        PostDBStore store = new PostDBStore(new Main().loadPool());
        Post post = new Post(0, "Java Job", "description", true, new City());
        System.out.println(post.toString());
        store.add(post);
        post.setName("New language");
        store.update(post);
        Post postInDb = store.findById(post.getId());
        System.out.println(post.toString());
        assertThat(postInDb.getName(), is(post.getName()));
    }
}