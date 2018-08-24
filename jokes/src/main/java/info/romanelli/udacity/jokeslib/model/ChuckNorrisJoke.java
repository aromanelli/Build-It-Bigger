package info.romanelli.udacity.jokeslib.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

@SuppressWarnings("unused")
public class ChuckNorrisJoke implements Joke {

    @SerializedName("id")
    @Expose
    private int id = Integer.MIN_VALUE;

    @SerializedName("joke")
    @Expose
    private String text =
            "Chuck Norris deems you unworthy of hearing a joke about him.";

    @SerializedName("categories")
    @Expose
    private List<String> categories = null;

    /** No args constructor for use in serialization */
    ChuckNorrisJoke() {
        super();
    }

    public int getId() {
        return id;
    }

    public String getJoke() {
        return text;
    }

    public List<String> getCategories() {
        return categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChuckNorrisJoke that = (ChuckNorrisJoke) o;
        return id == that.id &&
                Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, text);
    }

    @Override
    public String toString() {
        return "ChuckNorrisJoke{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", categories=" + categories +
                '}';
    }
}
