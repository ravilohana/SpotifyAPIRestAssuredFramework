
package com.restAssured.framework.spotify.pojo.error;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)


public class Error {

    @JsonProperty("error")
    private InnerError error;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Error() {
    }

    /**
     * 
     * @param error
     */
    public Error(InnerError error) {
        super();
        this.error = error;
    }

    @JsonProperty("error")
    public InnerError getError() {
        return error;
    }

    @JsonProperty("error")
    public void setError(InnerError error) {
        this.error = error;
    }

}
