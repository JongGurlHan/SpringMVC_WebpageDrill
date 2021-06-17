package hello.itemservice.domain.item;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Data //  @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode.포함, 위험하므로 평소엔 @Getter @Setter하고 필요에 따라 추가
@Getter
@Setter
public class Item {

    private Long id;
    private String itemName;
    private Integer price; //가격, 수량 모두 null로 들어갈 수 있음을 가정해서
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }


}
