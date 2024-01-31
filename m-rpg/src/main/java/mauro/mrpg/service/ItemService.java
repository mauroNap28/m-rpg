package mauro.mrpg.service;

import mauro.mrpg.dto.ItemDto;
import mauro.mrpg.model.GameCharacter;
import mauro.mrpg.model.Item;
import mauro.mrpg.repository.GameCharacterRepository;
import mauro.mrpg.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    GameCharacterRepository gameCharacterRepository;

    public List<Item> getItemsByCharacterId(Long id) {
        return itemRepository.findByCharacter_Id(id)
            .orElseThrow(() -> new RuntimeException("Error retrieving the inventory."));
    }

    public Item postItem(ItemDto request) {
        GameCharacter gameCharacter = gameCharacterRepository.findById(request.getCharacterId())
            .orElseThrow(() -> new RuntimeException("Game character not found."));
        return Item.builder()
            .name(request.getName())
            .type(request.getType())
            .description(request.getDescription())
            .character(gameCharacter)
            .build();
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    public Item putItem(Long id, ItemDto request) {
        Item item = itemRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Error retrieving the item."));
        item.setName(request.getName());
        item.setType(request.getType());
        item.setDescription(request.getDescription());
        return itemRepository.save(item);
    }
}
