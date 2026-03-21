package tests.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Metadata {
     private  String source;
     @JsonProperty("scraped_date")
     private String scrapedDate;
     @JsonProperty("total_scholarships")
     private Integer totalScholarships;
     private String method;
     private CategoriesInner categories;
     private StatusesInner statuses;

     public String getSource() {
          return source;
     }
     public String getScrapedDate() {
          return scrapedDate;
     }
     public Integer getTotalScholarships() {
          return totalScholarships;
     }
     public String getMethod() {
          return method;
     }
     public CategoriesInner getCategories() {
          return categories;
     }
     public StatusesInner getStatusesInner() {
          return statuses;
     }

     public void setSource(String source) {
          this.source = source;
     }
     public void setScrapedDate(String scrapedDate) {
          this.scrapedDate = scrapedDate;
     }
     public void setTotalScholarships(Integer totalScholarships) {
          this.totalScholarships = totalScholarships;
     }
     public void setMethod(String method) {
          this.method = method;
     }
     public void setCategories(CategoriesInner categories) {
          this.categories = categories;
     }
     public void setStatusesInner(StatusesInner statusesInner) {
          this.statuses = statuses;
     }
}
