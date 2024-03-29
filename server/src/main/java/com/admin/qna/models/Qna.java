package com.admin.qna.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @Column(name = "qna_id")
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
    private Timestamp regDate =  Timestamp.valueOf(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
    @Column(name = "latest_update_date", nullable = false)
    @Getter 
    private Timestamp latestUpdateDate =  Timestamp.valueOf(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
    @Column(name = "user_id", nullable = false, updatable = false)
    @Getter @Setter
    private String userId;
    @Column(name = "product_code", nullable = false)
    @Getter @Setter
    private String productCode;
    @Column(name = "is_deleted", nullable = false)
    @ColumnDefault(value = "'N'")
    private String isDeleted = "N";

    // public Qna(String title, String content, String userId, String productCode) {
    //     this.title = title;
    //     this.content = content;
    //     this.userId = userId;
    //     this.productCode = productCode;
    // }
    // public Qna () {
    //     if (this.regDate == null) {
    //         this.regDate = Timestamp.valueOf(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
    //     }
    //     if (this.latestUpdateDate == null) {
    //         this.latestUpdateDate = Timestamp.valueOf(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
    //     }
    //     if(this.isDeleted == null) {
    //         this.isDeleted = "N";
    //     }
    // }
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
