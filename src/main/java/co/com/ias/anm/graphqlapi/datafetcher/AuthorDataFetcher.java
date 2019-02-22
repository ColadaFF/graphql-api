package co.com.ias.anm.graphqlapi.datafetcher;

import co.com.ias.anm.graphqlapi.services.AuthorServices;
import co.com.ias.anm.graphqlapi.services.BookServices;
import graphql.schema.DataFetcher;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorDataFetcher {
    private final AuthorServices authorServices;

    @Autowired
    public AuthorDataFetcher(AuthorServices authorServices) {
        this.authorServices = authorServices;
    }

    public DataFetcher getAuthorByBookDataFetcher() {
        return dataFetchingEnvironment -> {
            Object t = dataFetchingEnvironment.getContext();
            System.out.println(t);
            Map<String,String> book = dataFetchingEnvironment.getSource();
            String authorId = book.get("authorId");
            return authorServices.getAuthorById(authorId);
        };
    }
}
