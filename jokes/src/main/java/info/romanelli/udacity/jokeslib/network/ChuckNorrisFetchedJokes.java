package info.romanelli.udacity.jokeslib.network;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import info.romanelli.udacity.jokeslib.model.ChuckNorrisJoke;

@SuppressWarnings("unused")
class ChuckNorrisFetchedJokes {

    /* http://www.icndb.com/api/, https://api.icndb.com/jokes/random
    type	"success"
    value
        id	394
        joke	"&quot;Let the Bodies Hit the Floor&quot; was originally written as Chuck Norris' theme song."
        categories	[]
     */

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("value")
    @Expose
    private List<ChuckNorrisJoke> value;

    /** No args constructor for use in serialization */
    ChuckNorrisFetchedJokes() {
        super();
    }

    public String getType() {
        return type;
    }

    public List<ChuckNorrisJoke> getValue() {
        return value;
    }

}
