package com.MialAndroid.budget_manager;

public class BudgetModel {

    private int id;
    private String description;
    private Double income;
    private Double expense;
    private Double budget;
    private String date;
    private boolean isActive;

    // constructors


    public BudgetModel(int id, String description, Double income, String date) {
        this.id = id;
        this.description = description;
        this.income = income;
        this.date = date;
    }

    public BudgetModel() {
    }

    // toString

    @Override
    public String toString() {
        return "BudgetModel{" +
                "id=" + id +
                ", description=" + description +
                ", income=" + income +
                ", date='" + date + '\'' +
                '}';
    }


    // getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getExpense() {
        return expense;
    }

    public void setExpense(Double expense) {
        this.expense = expense;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
