package com.xenecca.api.model.studentopportunity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Accessors(prefix = "_")
@Data
@Builder
@Entity
public class StudentOpportunity {
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id", columnDefinition = "VARCHAR(32)")
    @Id
    private final String _id;

    @NotBlank
    @Column(name = "title", unique = true, nullable = false)
    private final String _title;

    // url where opportunity can be found (to apply)
    @NotBlank
    @Column(name = "origin_url", unique = true, nullable = false, columnDefinition = "VARCHAR(360)")
    private final String _originUrl;

    // website where the opportunity is found
    @NotBlank
    @Column(name = "source_website")
    private final String _sourceWebsite;

    @Column(name = "is_scrapped")
    @Builder.Default
    private final boolean _scrapped = false;

    @NotBlank
    @Enumerated(EnumType.STRING)
    @Column(name = "opportunity_type", nullable = false)
    @Builder.Default
    private final OpportunityType _opportunityType = OpportunityType.INTERNSHIP;
}
