
package com.example;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Example {

    @SerializedName("findItemsByKeywordsResponse")
    @Expose
    private List<FindItemsByKeywordsResponse> findItemsByKeywordsResponse = new ArrayList<FindItemsByKeywordsResponse>();

    /**
     * 
     * @return
     *     The findItemsByKeywordsResponse
     */
    public List<FindItemsByKeywordsResponse> getFindItemsByKeywordsResponse() {
        return findItemsByKeywordsResponse;
    }

    /**
     * 
     * @param findItemsByKeywordsResponse
     *     The findItemsByKeywordsResponse
     */
    public void setFindItemsByKeywordsResponse(List<FindItemsByKeywordsResponse> findItemsByKeywordsResponse) {
        this.findItemsByKeywordsResponse = findItemsByKeywordsResponse;
    }

}
