package miu.edu.cs489;

import java.time.LocalDate;

public class PensionPlan {
    private String refNo;
    private LocalDate enrollmentDate;
    private Double monthlyContribution;
    private Employee employees;

    public PensionPlan(String refNo, LocalDate enrollmentDate, Double monthlyContribution, Employee employees) {
        if (employees == null ){
            throw new RuntimeException();
        }
        this.refNo = refNo;
        this.enrollmentDate = enrollmentDate;
        this.monthlyContribution = monthlyContribution;
        this.employees = employees;
        this.employees.setPensionPlan(this);
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public Double getMonthlyContribution() {
        return monthlyContribution;
    }

    public void setMonthlyContribution(Double monthlyContribution) {
        this.monthlyContribution = monthlyContribution;
    }

    public Employee getEmployees() {
        return employees;
    }

    public void setEmployees(Employee employees) {
        this.employees = employees;
    }
}
