package com.spotify.oauth2.pojo;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spotify.*;

public class Error {


    @JsonInclude(JsonInclude.Include.NON_NULL)
    public class PlayList {

        @JsonProperty("error")
        private InnerError error;

        @JsonProperty("error")
        public InnerError getError() {
            return error;
        }

        @JsonProperty("error")
        public void setError(InnerError error) {
            this.error = error;
        }

    }
}
