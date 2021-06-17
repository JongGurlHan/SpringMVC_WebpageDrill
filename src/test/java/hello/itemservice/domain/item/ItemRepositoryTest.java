package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

   @AfterEach//테스트 끝날때마다 실행, 테스트끝낼때마다 리포지토리 비워준다
   void afterEach(){
       itemRepository.clearStore();
   }

   @Test
    void save(){
       //given
       Item item = new Item("itemA", 10000, 10);

       //when
       Item savedItem = itemRepository.save(item); //savedItem: 리포지토리에 저장된 아이템

       //then
       Item findItem = itemRepository.findById(item.getId());
       assertThat(findItem.getId()).isEqualTo(savedItem.getId());
   }

    @Test
    void findALl(){ //리스트 사이즈 확인, 아이템 보유 여부 확인
        //given
        Item item1 = new Item("itemA", 10000, 10);
        Item item2 = new Item("itemB", 20000, 20);

        itemRepository.save(item1);
        itemRepository.save(item2);

        //when
        List<Item> result = itemRepository.findALl();

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1,item2);
    }

    @Test
    void updateItem(){
       //given
        Item item = new Item("itemA", 10000, 10);

        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();

        //when
        Item updateParam = new Item("item2", 20000, 30);
        itemRepository.update(itemId, updateParam);

       //then
        Item findItem = itemRepository.findById(itemId);
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }

}