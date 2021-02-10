package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author leoTolstoy = new Author("Leo", "Tolstoy");
        Book warAndPeace = new Book("War and Peace", "123123");
        leoTolstoy.getBooks().add(warAndPeace);
        warAndPeace.getAuthors().add(leoTolstoy);

        authorRepository.save(leoTolstoy);
        bookRepository.save(warAndPeace);

        Author fedorDostoyevski = new Author("Fedor", "Dostoyevski");
        Book idiot = new Book("Idiot", "679034");
        fedorDostoyevski.getBooks().add(idiot);
        idiot.getAuthors().add(fedorDostoyevski);

        authorRepository.save(fedorDostoyevski);
        bookRepository.save(idiot);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books in DB: " + bookRepository.count());



    }
}
