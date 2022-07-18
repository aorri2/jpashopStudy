package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemUpdateTest {

    @Autowired
    EntityManager em;
    @Test
    @Transactional
    public void updateTest() throws Exception{
        Book book = new Book();
        book.setName("asdasd");
        book.setIsbn("12312312");

        em.persist(book);
        String firstName = book.getName();
        //given
        Book findBook = em.find(Book.class, 1L);
        //when
        findBook.setName("asdfasdf");

        em.persist(findBook);
        String secondName = findBook.getName();

        //변경 감지 == dirty checking
    //then
        Assertions.assertThat(firstName).isEqualTo(secondName);
    }
}
