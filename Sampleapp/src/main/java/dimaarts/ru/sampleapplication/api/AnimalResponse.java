package dimaarts.ru.sampleapplication.api;

import java.util.List;

public class AnimalResponse {
    public List<AnimalElementResponse> getData() {
        return data;
    }

    public void setData(List<AnimalElementResponse> data) {
        this.data = data;
    }

    private List<AnimalElementResponse> data;
}
