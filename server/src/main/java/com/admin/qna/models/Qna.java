package com.admin.qna.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Qna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qna_id", updatable = false)
    @Getter
    private Long qnaId;
    @Column(nullable = false)
    @Getter @Setter
    private String title;
    @Column(nullable = false)
    @Getter @Setter
    private String content;
    @Column(name = "reg_date", nullable = false, updatable = false)
    @Getter
    private Timestamp regDate;
    @Column(name = "latest_update_date", nullable = false)
    @Getter
    private Timestamp latestUpdateDate;
    @Column(name = "user_id", nullable = false, updatable = false)
    @Getter @Setter
    private String userId;
    @Column(name = "product_code", nullable = false)
    @Getter @Setter
    private String productCode;
    @Column(name = "is_deleted", nullable = false)
    @ColumnDefault(value = "'N'")
    @Getter @Setter
    private String isDeleted = "N";

    @PrePersist
    public void setDate() {
        this.regDate =  Timestamp.valueOf(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        this.latestUpdateDate = Timestamp.valueOf(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
    }

    @PreUpdate
    public void setLatestUpdateDate() {
        this.latestUpdateDate = Timestamp.valueOf(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
    }

    public boolean isDeleted() {
        return this.isDeleted == "Y" ? true : false;
    }

    public void deleteExcute() {
        this.isDeleted = "Y";
    }

    public void updateService(Qna updateData) {
        this.title = updateData.getTitle();
        this.content = updateData.getContent();
        this.latestUpdateDate = Timestamp.valueOf(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
    }
}
