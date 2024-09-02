import java.util.ArrayList;

public class Player {
    private String get;
    Parameters ParametersObject;
    ArrayList< Object > errors = new ArrayList < Object > ();
    private float results;
    ArrayList < Object > response = new ArrayList < Object > ();


    // Getter Methods

    public String getGet() {
        return get;
    }

    public Parameters getParameters() {
        return ParametersObject;
    }

    public float getResults() {
        return results;
    }

    // Setter Methods

    public void setGet(String get) {
        this.get = get;
    }

    public void setParameters(Parameters parametersObject) {
        this.ParametersObject = parametersObject;
    }

    public void setResults(float results) {
        this.results = results;
    }
}
class Parameters {
    private String id;


    // Getter Methods

    public String getId() {
        return id;
    }

    // Setter Methods

    public void setId(String id) {
        this.id = id;
    }
}
