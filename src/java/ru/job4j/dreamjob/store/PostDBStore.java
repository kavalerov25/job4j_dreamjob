package ru.job4j.dreamjob.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Post;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostDBStore {

    private static final Logger LOG = LoggerFactory.getLogger(PostDBStore.class.getName());
    private static final String FIND_ALL_SQL = "SELECT * FROM post";
    private static final String INSERT_DATA_QUERY = """
            INSERT INTO post(name, description, created, visible, city_id)
            VALUES (?, ?, ?, ?, ?)
            """;
    private static final String UPDATE_DATA_QUERY = """
            "UPDATE post SET name = (?), description = (?), created = (?)," 
            " visible = (?), city_id = (?) WHERE id = (?)"
             """;
    private static final String FIND_BY_ID_SQL = "SELECT * FROM post WHERE id = ?";
    private final BasicDataSource pool;


    public PostDBStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public List<Post> findAll() {
        List<Post> posts = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(FIND_ALL_SQL)
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    posts.add(
                            new Post(
                                    it.getInt("id"),
                                    it.getString("name"),
                                    it.getString("description"),
                                    it.getDate("created").toLocalDate(),
                                    it.getBoolean("visible"),
                                    it.getInt("city_id")));
                }
            }
        } catch (Exception e) {
            LOG.error("Exception in log", e);
        }
        return posts;
    }


    public Post add(Post post) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(
                     INSERT_DATA_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, post.getName());
            ps.setString(2, post.getDescription());
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            ps.setBoolean(4, post.isVisible());
            ps.setInt(5, post.getCity().getId());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    post.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            LOG.error("Exception in log", e);
        }
        return post;
    }

    public void update(Post post) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(
                     UPDATE_DATA_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, post.getName());
            ps.setString(2, post.getDescription());
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            ps.setBoolean(4, post.isVisible());
            ps.setInt(5, post.getCity().getId());
            ps.setInt(6, post.getId());
            ps.execute();
        } catch (SQLException e) {
            LOG.error("Exception in log", e);
        }
    }

    public Post findById(int id) {
        Post result = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(FIND_BY_ID_SQL)
        ) {
            ps.setInt(1, id);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    result = new Post(it.getInt("id"),
                            it.getString("name"),
                            it.getString("description"),
                            it.getTimestamp("created").toLocalDateTime().toLocalDate(),
                            it.getBoolean("visible"),
                            it.getInt("city_id"));
                }
            }
        } catch (Exception e) {
            LOG.error("Exception in log", e);
        }
        return result;
    }
}
