package main;

import model.Item;

import java.util.HashMap;
import java.util.Map;

public class Test {
// Khi nó trùng Key rồi thì map nó sẽ so sánh value != null đầu tiên từ trái sang nếu mapping
// nó sẽ lấy Value object sau gán vào object đã có trong map
    public static void main(String[] args) {
        Map<String, Item> map = new HashMap<>();
        Item item = new Item();
//        item.setTexr("1");
        item.setName("abc");
        item.setQuantity(1);
        Item item1 = new Item();
//        item1.setTexr("2");
        item1.setName("abc");
        item1.setPrice(100123.9);
        item1.setQuantity(2);
        map.put(item.getName(), item);
        map.put(item1.getName(), item1);

        if (!map.containsKey(item.getName())) {
            map.put(item.getName(), item);
        } else {
            item1.setQuantity(item1.getQuantity() + 1);
        }

        for (Map.Entry<String, Item> entry : map.entrySet()) {
            System.out.println(entry);
        }

//        for (Item result : map.values()) {
//            System.out.println(result);
//        }
    }
}
