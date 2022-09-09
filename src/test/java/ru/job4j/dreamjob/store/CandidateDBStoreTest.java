package ru.job4j.dreamjob.store;

import org.junit.jupiter.api.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class CandidateDbStoreTest {
    @Test
    public void whenCreateCandidate() {
        CandidateDBStore store = new CandidateDBStore(new Main().loadPool());
        Candidate candidate = new Candidate(0, "Dima", "Java dev", LocalDate.now());
        store.add(candidate);
        Candidate candidateInDb = store.findById(candidate.getId());
        assertThat(candidateInDb.getCreated(), is(candidate.getCreated()));
    }

    @Test
    public void whenCreateTwoCandidates() {
        CandidateDBStore store = new CandidateDBStore(new Main().loadPool());
        Candidate candidate1 = new Candidate(0, "Stas", "Java dev", LocalDate.now());
        Candidate candidate2 = new Candidate(0, "Kirill", "Java junior", LocalDate.now());
        store.add(candidate1);
        store.add(candidate2);
        Candidate candidateInDb = store.findById(candidate2.getId());
        assertThat(candidateInDb.getName(), is(candidate2.getName()));
    }
}