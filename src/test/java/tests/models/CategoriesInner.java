package tests.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoriesInner {
    @JsonProperty("Education Loan")
    private Integer educationLoan;
    @JsonProperty("Fee Waiver")
    private Integer feeWaiver;
    @JsonProperty("Fellowship")
    private Integer fellowship;
    @JsonProperty("Internship")
    private Integer internship;
    @JsonProperty("Study Abroad")
    private Integer studyAbroad;
    @JsonProperty("General")
    private Integer general;

    public Integer getEducationLoan() {
        return educationLoan;
    }
    public Integer getFeeWaiver() {
        return feeWaiver;
    }

    public void setEducationLoan(Integer educationLoan) {
        this.educationLoan = educationLoan;
    }
    public Integer getFellowship() {
        return fellowship;
    }
    public Integer getInternship() {
        return internship;
    }
    public Integer getStudyAbroad() {
        return studyAbroad;
    }
    public Integer getGeneral() {
        return general;
    }

    public void setFeeWaiver(Integer feeWaiver) {
        this.feeWaiver = feeWaiver;
    }
    public void setFellowship(Integer fellowship) {
        this.fellowship = fellowship;
    }
    public void setInternship(Integer internship) {
        this.internship = internship;
    }
    public void setStudyAbroad(Integer studyAbroad) {
        this.studyAbroad = studyAbroad;
    }
    public void setGeneral(Integer general) {
        this.general = general;
    }
}
