package com.xenecca.api.model.studentopportunity;

public enum OpportunityType {
    SCHOLARSHIP,
    FELLOWSHIP,
    INTERNSHIP,
    EVENT;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }

}
