package com.xenecca.api.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.xenecca.api.model.studentopportunity.OpportunityType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Accessors(prefix = "_")
@Data
public class StudentOpportunityReqDto {

    @NotBlank(message = "Title must not be null/empty")
    private final String _title;

    @URL(message = "URL format is not valid")
    @NotBlank(message = "URL must not be null/empty")
    private final String _originUrl;

    @NotNull(message = "Opportunity type is a required field")
    private final OpportunityType _opportunityType;

    @JsonCreator
    public StudentOpportunityReqDto(@JsonProperty("title") String title,
                                    @JsonProperty("originUrl") String originUrl,
                                    @JsonProperty("opportunityType") OpportunityType opportunityType
    ) {
        _title = title;
        _originUrl = originUrl;
        _opportunityType = opportunityType;
    }
}
