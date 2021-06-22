package hello.itemservice.domain.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    //BasicItemController가 스프링빈에 등록되면서 생성자 주입으로 itemRepository가 주입된다. , 생성자가 하나라면 @Autowired생략가능
    //혹은 @RequiredArgsConstructor 대신 넣어주면 전체 생략가능
//    @Autowired
//    public BasicItemController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findALl();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping ("/add")
    public String addForm(){
        return "basic/addForm";
    }

   // @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam Integer price,
                       @RequestParam Integer quantity,
                       Model model){
        Item item  = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item", item);

        return "basic/item";
    }

   // @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item){

        //@ModelAttribute의 기능 2가지
        //1. 요청 파라미터 처리: Item 객체 생성하고 요청 파라미터의 값을 프로퍼티 접근법(setXxx)로 입력해준다.
        //2. Model에 @ModelAttribute로 지정한 객체를 자동으로 넣어준다.
        //@ModelAttribute가 item객체 만들고 set호출해서 넣어준다.
        //@ModelAttribute("item") 에서의 "item"은 model.addAttribute()에서의 abbributeName을 지정해준다.
//        Item item  = new Item();
//        item.setItemName(itemName);
//        item.setPrice(price);
//        item.setQuantity(quantity);

        itemRepository.save(item);

        //model.addAttribute("item", item); //자동추가, 생략가능

        return "basic/item";
    }

 //   @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item){

        //Item -> item으로 자동 변환돼서 model.addAttribute된다.
        //Hellodata ->hellodata
        itemRepository.save(item);
        //  model.addAttribute("item", item); //자동추가, 생략가능

        return "basic/item";
    }

    @PostMapping("/add")
    public String addItemV4(Item item){
        //심지어 @ModelAttribute는 생략 가능!
        itemRepository.save(item);

        return "basic/item";
    }

    @GetMapping("/{itemId}/edit") //수정 폼으로 이동
    public String editForm(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit") //수정 vjgl
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item){
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
        //http://localhost:8080/basic/items/2/edit 가 아닌 http://localhost:8080/basic/items/2 로이동
    }


    /**
     * 테스트용 데이터 추가
     * */
    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }


}
