package ru.job4j.dreamjob.store;

import net.jcip.annotations.ThreadSafe;
import ru.job4j.dreamjob.model.Candidate;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
@Repository
public class CandidateStore {
    private final AtomicInteger idCount = new AtomicInteger();

    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();

    private CandidateStore() {
        candidates.put(idCount.incrementAndGet(), new Candidate(idCount.get(), "kirill", "intern", LocalDateTime.of(2022, Month.AUGUST,24,20,40)));
        candidates.put(idCount.incrementAndGet(), new Candidate(idCount.get(), "stas", "coach java", LocalDateTime.now()));
        candidates.put(idCount.incrementAndGet(), new Candidate(idCount.get(), "petr", "Java senior", LocalDateTime.now()));
    }

    public Collection<Candidate> findAll() {
        return candidates.values();
    }

    public void add(Candidate candidate) {
        candidate.setId(idCount.incrementAndGet());
        candidates.put(candidate.getId(), candidate);
    }

    public Candidate findById(int id) {
        return candidates.get(id);
    }

    public void update(Candidate candidate) {
        candidates.replace(candidate.getId(), candidate);
    }
}