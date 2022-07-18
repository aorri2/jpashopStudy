package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
@Getter
@Setter
@NoArgsConstructor
public class Book extends Item{

    private String author;
    private String isbn;


    public Book(String name, int price, int stockQuantity) {
        this.setName(name);
        this.setPrice(price);
        this.setStockQuantity(stockQuantity);
    }
}
