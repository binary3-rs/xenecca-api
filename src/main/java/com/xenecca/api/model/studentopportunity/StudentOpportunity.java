package com.xenecca.api.model.studentopportunity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Accessors(prefix = "_")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StudentOpportunity {
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    @Id
    private String _id;

    @NotBlank
    @Column(name = "title", unique = true, nullable = false, columnDefinition = "VARCHAR(128)")
    private String _title;

    // url where opportunity can be found (to apply)
    @NotBlank
    @Column(name = "origin_url", unique = true, nullable = false, columnDefinition = "VARCHAR(360)")
    private String _originUrl;

    // website where the opportunity is found
    @Column(name = "source_website", columnDefinition = "VARCHAR(128)")
    private String _sourceWebsite;

    @Enumerated(EnumType.STRING)
    @Column(name = "opportunity_type", nullable = false)
    @Builder.Default
    private OpportunityType _opportunityType = OpportunityType.INTERNSHIP;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp _createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp _updatedAt;
}