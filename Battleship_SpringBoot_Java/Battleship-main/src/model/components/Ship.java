package model.components;

import java.util.List;

public class    Ship {
    private List<String> locations;
    private List<String> hits;

    public Ship(List<String> locations, List<String> hits) {
        this.locations = locations;
        this.hits = hits;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public List<String> getHits() {
        return hits;
    }

    public void setHits(List<String> hits) {
        this.hits = hits;
    }

    public void setHits(String value, int index) {
        this.hits.remove(index);
        this.hits.add(index, value);
    }
}
