package ru.job4j.dreamjob.store;

import ru.job4j.dreamjob.model.Candidate;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CandidateStore {
    private static final CandidateStore INST = new CandidateStore();
    private final AtomicInteger idCount = new AtomicInteger();

    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();

    private CandidateStore() {
        candidates.put(idCount.incrementAndGet(), new Candidate(idCount.get(), "kirill", "intern", LocalDateTime.of(2022, Month.AUGUST,24,20,40)));
        candidates.put(idCount.incrementAndGet(), new Candidate(idCount.get(), "stas", "coach java", LocalDateTime.now()));
        candidates.put(idCount.incrementAndGet(), new Candidate(idCount.get(), "petr", "Java senior", LocalDateTime.now()));
    }

    public static CandidateStore instOf() {
        return INST;
    }

    public Collection<Candidate> findAll() {
        return candidates.values();
    }

    public void add(Candidate candidate) {
        candidate.setId(idCount.incrementAndGet());
        candidates.put(candidate.getId(), candidate);
    }

    public Object findById(int id) {
        return candidates.get(id);
    }

    public void update(Candidate candidate) {
        candidates.put(candidate.getId(), candidate);
    }
}