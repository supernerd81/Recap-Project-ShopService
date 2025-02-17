package de.supernerd;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepoTest {

    @org.junit.jupiter.api.Test
    void getProducts() {
        //GIVEN
        ProductRepo repo = new ProductRepo();

        //WHEN
        List<Product> actual = repo.getProducts();

        //THEN
        List<Product> expected = new ArrayList<>();
        expected.add(new Product("1", "Apfel"));
        assertEquals(actual, expected);
    }

    @org.junit.jupiter.api.Test
    void getProductById() {
        //GIVEN
        ProductRepo repo = new ProductRepo();

        //WHEN
        Optional<Product> actual = repo.getProductById("1");

        //THEN
        Optional<Product> expected = Optional.of(new Product("1", "Apfel"));
        assertEquals(actual, expected);
    }

    @org.junit.jupiter.api.Test
    void addProduct() {
        //GIVEN
        ProductRepo repo = new ProductRepo();
        Optional<Product> newProduct = Optional.of(new Product("2", "Banane"));

        //WHEN
        Optional<Product> actual = repo.addProduct(newProduct);

        //THEN
        Optional<Product> expected = Optional.of(new Product("2", "Banane"));
        assertEquals(actual, expected);
        assertEquals(repo.getProductById("2"), expected);
    }

    @org.junit.jupiter.api.Test
    void removeProduct() {
        //GIVEN
        ProductRepo repo = new ProductRepo();

        //WHEN
        repo.removeProduct("1");

        //THEN
        Assertions.assertTrue(repo.getProductById("1").isEmpty());
    }
}
