package scriptures.model;

import java.util.List;

public interface ScriptureDataHandler {
    public List<Scripture> getFavoriteScriptures();
    
    public void addScripture(Scripture scripture);
}