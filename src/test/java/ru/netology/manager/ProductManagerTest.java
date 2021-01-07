package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);

    private Product book1 = new Book(1, "Утро", 99999, "Александра");
    private Product book2 = new Book(13, "Зима", 1990, "Виниамин");
    private Product phone1 = new Smartphone(99, "Poco X3", 1550000, "Xiaomi");
    private Product phone2 = new Smartphone(7, "Galaxy A71", 793000, "Samsung");
    private Product phone3 = new Smartphone(2, "note 5", 1139000, "Xiaomi");
    private Product product1 = new Product(5, "None", 10);

    @BeforeEach
    void setUp() {
        manager.add(book1);
        manager.add(phone1);
        manager.add(product1);
        manager.add(phone2);
        manager.add(book2);
        manager.add(phone3);
    }

    @Test
    void searchByNotFoundTest() {
        Product[] actual = manager.searchBy("Москва");
        Product[] expected = new Product[0];

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByAuthorBookOneFoundTest() {
        Product[] actual = manager.searchBy("александра");
        Product[] expected = new Product[]{book1};

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByNameBookOneFoundTest() {
        Product[] actual = manager.searchBy("ЗИМА");
        Product[] expected = new Product[]{book2};

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByNamePhoneOneFoundTest() {
        Product[] actual = manager.searchBy("gaLaxy a71");
        Product[] expected = new Product[]{phone2};

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByVendorPhoneSeveralFoundTest() {
        Product[] actual = manager.searchBy("xiaomi");
        Product[] expected = new Product[]{phone1, phone3};

        assertArrayEquals(expected, actual);
    }
}
