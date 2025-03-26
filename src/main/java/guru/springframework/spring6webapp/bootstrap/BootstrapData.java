package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repository.AuthorRepository;
import guru.springframework.spring6webapp.repository.BookRepository;
import guru.springframework.spring6webapp.repository.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("123456");

        eric = authorRepository.save(eric);
        ddd = bookRepository.save(ddd);

        eric.getBooks().add(ddd);
        authorRepository.save(eric);

        Publisher publisher = new Publisher();
        publisher.setPublisherName("Alpina Non Fiction");
        publisher.setCity("Moscow");
        publisher.setState("Moscow");
        publisher.setZip("123123");

        publisherRepository.save(publisher);

        System.out.println("Inside Bootstrap");
        System.out.printf("Book count: %s%n", bookRepository.count());
        System.out.printf("Author count: %s%n", authorRepository.count());
        System.out.printf("Publisher count: %s%n", publisherRepository.count());
    }
}
