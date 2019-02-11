package co.com.ias.anm.graphqlapi.repositories;

import com.google.common.collect.ImmutableMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorRepository {
    private static List<Map<String, String>> authors = Arrays.asList(
            ImmutableMap.of("id", "author-1",
                    "firstName", "Joanne",
                    "lastName", "Rowling"),
            ImmutableMap.of("id", "author-2",
                    "firstName", "Herman",
                    "lastName", "Melville"),
            ImmutableMap.of("id", "author-3",
                    "firstName", "Anne",
                    "lastName", "Rice")
    );


    public List<Map<String, String>> allAuthors() {
        return authors;
    }

}
