package dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class ItemCategoryDto {
    private String id;
    private String name;
    private String category;
    public String getId() {
        return id;
    }
}
