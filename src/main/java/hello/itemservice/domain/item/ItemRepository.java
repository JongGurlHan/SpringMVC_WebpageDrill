package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;
import org.thymeleaf.templateresolver.FileTemplateResolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository  //Component Scan의 대상이 된다.
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>();   //static
    private static long sequence = 0L; //static

    //저장
    public Item save(Item item){
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    //아이디로 개별조회
    public Item findById(Long id){
        return store.get(id);
    }

    //전체조회
    public List<Item> findALl(){
        return new ArrayList<>(store.values());
    }

    //수정
    public void update(Long itemId, Item updateParam){
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());

    }
    //store날리기, hashmap 데이터 날라간다
    public void clearStore(){
        store.clear();
    }







 }
