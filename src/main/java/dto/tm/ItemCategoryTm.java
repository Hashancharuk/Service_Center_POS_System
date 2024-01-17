package dto.tm;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.scene.control.Button;
import jdk.jfr.Threshold;
import lombok.*;

@NoArgsConstructor
@ToString
@Getter
@Setter

public class ItemCategoryTm {
    private String id;
    private String name;
    private String category;
    private Button btn;

    public ItemCategoryTm(String id, String name, String category, Button btn) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.btn = btn;
    }
}
