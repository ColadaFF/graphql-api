package co.com.ias.anm.graphqlapi.datafetcher;

import co.com.ias.anm.graphqlapi.services.BookServices;
import graphql.schema.DataFetcher;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookDataFetcher {
    private final BookServices bookServices;

    @Autowired
    public BookDataFetcher(BookServices bookServices) {
        this.bookServices = bookServices;
    }

    public DataFetcher getBookByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String bookId = dataFetchingEnvironment.getArgument("id");
            return bookServices.getBookById(bookId);
        };
    }

    public DataFetcher getAllBooksDataFetcher() {
        return dataFetchingEnvironment -> bookServices.allBooks();
    }

    public DataFetcher getBookPageCount() {
        return environment -> {
            Map<String, String> book = environment.getSource();
            HttpServletRequest request = environment.getContext();
            String pageCountStr = book.get("pageCount");
            return Integer.parseInt(pageCountStr);
        };
    }

    public DataFetcher saveBook() {
        return environment -> {
            System.out.println("Saving ...");
            Map<String, Object> bookInputMap = environment.getArgument("book");
            return bookServices.saveBook(bookInputMap);
        };
    }
 }
