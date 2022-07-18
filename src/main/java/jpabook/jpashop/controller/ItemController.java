package jpabook.jpashop.controller;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/new")
    public String createForm(Model model){
        model.addAttribute("form",new BookForm());
        return "items/createItemForm.html";
    }

    @PostMapping("/new")
    public String create(BookForm form) {
        Book book = new Book();
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        itemService.saveItem(book);
        return "redirect:/";
    }

    @GetMapping
    public String list(Model model){
        model.addAttribute("items",itemService.findItems());
        return "items/itemList";
    }

    /**
     * 상품 수정 폼
     * @param itemId 사용자 ID 값
     * @param model 사용자 Item모델
     */
    @GetMapping("/{itemId}/edit")
    public String updateItemForm(@PathVariable Long itemId, Model model){
        Book item = (Book) itemService.findOne(itemId);

        BookForm form = new BookForm();
        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setAuthor(item.getAuthor());
        form.setIsbn(item.getIsbn());


        model.addAttribute("form",form);
        return "items/updateItemForm";
    }
    @PostMapping("/{itemId}/edit")
    public String updateItem(@PathVariable Long itemId,@ModelAttribute("form") BookForm form){

      /*  Book book = new Book();
        book.setId(form.getId());
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());
        */
        itemService.updateItem(itemId,form.toBook());
//        itemService.saveItem(form.toBook());

        return "redirect:/items";
    }
}
