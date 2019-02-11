package co.com.ias.anm.graphqlapi.repositories;

import com.google.common.collect.ImmutableMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {
    private static List<Map<String, String>> books = Arrays.asList(
            ImmutableMap.of("id", "book-1",
                    "name", "Harry Potter and the Philosopher's Stone",
                    "pageCount", "223",
                    "authorId", "author-1"),
            ImmutableMap.of("id", "book-2",
                    "name", "Moby Dick",
                    "pageCount", "635",
                    "authorId", "author-2"),
            ImmutableMap.of("id", "book-3",
                    "name", "Interview with the vampire",
                    "pageCount", "371",
                    "authorId", "author-3")
    );



    public List<Map<String, String>> allBooks() {
        return books;
    }
}
