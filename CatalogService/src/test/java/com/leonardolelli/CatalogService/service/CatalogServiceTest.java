package com.leonardolelli.CatalogService.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.leonardolelli.CatalogService.model.Book;
import com.leonardolelli.CatalogService.model.Book.Genre;
import com.leonardolelli.CatalogService.repository.CatalogRepository;

@ExtendWith(MockitoExtension.class)
class CatalogServiceTest {

	@InjectMocks
	private CatalogService catalogService;
	@Mock
	private CatalogRepository catalogRepository;

	@Test
	void testFindAll() {
		String id1 = UUID.randomUUID().toString();
		String id2 = UUID.randomUUID().toString();
		List<Book> bookList = List.of(new Book(id1, "titolo1", "autore1", 1990, Genre.horror),
				new Book(id2, "titolo2", "autore2", 2000, Genre.thriller));

		Mockito.when(catalogRepository.findAll()).thenReturn(bookList);
		List<Book> result = catalogService.findAll();
		assertEquals(result, bookList);
	}

	@Test
	void testFind() {
		String id1 = UUID.randomUUID().toString();
		Book testBook = new Book(id1, "titolo1", "autore1", 1990, Genre.horror);

		Mockito.when(catalogRepository.findById(id1)).thenReturn(Optional.of(testBook));
		Book result = catalogService.find(id1);
		assertEquals(result, testBook);
	}

	@Test
	void testFindAllByGenre() {
		String id1 = UUID.randomUUID().toString();
		String id2 = UUID.randomUUID().toString();
		List<Book> bookList = List.of(new Book(id1, "titolo1", "autore1", 1990, Genre.horror),
				new Book(id2, "titolo2", "autore2", 2000, Genre.horror));

		Mockito.when(catalogRepository.findAllByGenre(Genre.horror)).thenReturn(bookList);
		List<Book> result = catalogService.findAllBy(Genre.horror, null);
		assertEquals(result, bookList);
	}

	@Test
	void testFindAllByAuthor() {
		String id1 = UUID.randomUUID().toString();
		String id2 = UUID.randomUUID().toString();
		List<Book> bookList = List.of(new Book(id1, "titolo1", "autore2", 1990, Genre.horror),
				new Book(id2, "titolo2", "autore2", 2000, Genre.horror));

		Mockito.when(catalogRepository.findAllByAuthor("autore2")).thenReturn(bookList);
		List<Book> result = catalogService.findAllBy(null, "autore2");
		assertEquals(result, bookList);
	}

	@Test
	void testFindAllByGenreAndAthor() {
		String id1 = UUID.randomUUID().toString();
		String id2 = UUID.randomUUID().toString();
		List<Book> bookList = List.of(new Book(id1, "titolo1", "autore2", 1990, Genre.horror),
				new Book(id2, "titolo2", "autore2", 2000, Genre.horror));

		Mockito.when(catalogRepository.findAllByGenreAndAuthor(Genre.horror, "autore2")).thenReturn(bookList);
		List<Book> result = catalogService.findAllBy(Genre.horror, "autore2");
		assertEquals(result, bookList);
	}

}
