package com.xenecca.api.dto.response;

import com.xenecca.api.model.studentopportunity.OpportunityType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentOpportunityRespDto {

    private String _id;
    private String _title;
    private String _originUrl;
    private OpportunityType _opportunityType;

}
