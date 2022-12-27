
package com.restAssured.framework.spotify.pojo.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter @Setter
@Jacksonized
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

    public static ErrorBuilder builder() {
        return new ErrorBuilder();
    }


    public static class ErrorBuilder {
        private InnerError error;

        ErrorBuilder() {
        }

        public ErrorBuilder error(InnerError error) {
            this.error = error;
            return this;
        }

        public Error build() {
            return new Error(error);
        }

        public String toString() {
            return "Error.ErrorBuilder(error=" + this.error + ")";
        }
    }
}
