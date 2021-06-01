package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Album;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//private Long id;
//
//private String name;
//private int price;
//private int stockQuantity;
//private String author;
//private String isbn;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private EntityManager em;

    @Test
    void saveItem() {
        // given
        Book book = new Book();
        book.setName("TEST BOOK");
        book.setAuthor("digilog");
        book.setIsbn("123456");

        //when
        Long savedId = itemService.saveItem(book);

        //then
        em.flush();
        assertEquals(book, itemService.findOne(savedId));

    }

    @Test
    void findItems() {
        //given
        Book book = new Book();
        book.setName("TEST BOOK");
        book.setAuthor("digilog");
        book.setIsbn("123456");
        Album album = new Album();
        album.setName("TEST ALBUM");
        album.setArtist("digilog");
        album.setPrice(3000);
        List<Item> items = new ArrayList<>();
        items.add(book);
        items.add(album);

        //when
        itemService.saveItem(book);
        itemService.saveItem(album);

        //then
        assertEquals(items, itemService.findItems());
    }
//
//    @Test
//    void findOne() {
//    }
}


