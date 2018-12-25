package dimaarts.ru.sampleapplication.entities;

import java.io.Serializable;

public class AnimalEntity implements Serializable {
    private String name;
    private String imageUrl;

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
