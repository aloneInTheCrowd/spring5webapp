package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher lenizdat = new Publisher("Lenizdat", "Fontanka 59", "Leningrad", "Leningradskaya", "300300");
        publisherRepository.save(lenizdat);

        Author leoTolstoy = new Author("Leo", "Tolstoy");
        Book warAndPeace = new Book("War and Peace", "123123");
        leoTolstoy.getBooks().add(warAndPeace);
        warAndPeace.getAuthors().add(leoTolstoy);
        warAndPeace.setPublisher(lenizdat);
        lenizdat.getBooks().add(warAndPeace);

        authorRepository.save(leoTolstoy);
        bookRepository.save(warAndPeace);
        publisherRepository.save(lenizdat);

        Author fedorDostoyevski = new Author("Fedor", "Dostoyevski");
        Book idiot = new Book("Idiot", "679034");
        fedorDostoyevski.getBooks().add(idiot);
        idiot.getAuthors().add(fedorDostoyevski);
        idiot.setPublisher(lenizdat);
        lenizdat.getBooks().add(idiot);

        authorRepository.save(fedorDostoyevski);
        bookRepository.save(idiot);
        publisherRepository.save(lenizdat);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books in DB: " + bookRepository.count());
        System.out.println("Number of Publishers in DB: " + publisherRepository.count());
        System.out.println(lenizdat.getName() + " number of Books :" + lenizdat.getBooks().size());
    }
}