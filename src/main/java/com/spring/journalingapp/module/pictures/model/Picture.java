package com.spring.journalingapp.module.pictures.model;

import com.spring.journalingapp.core.BaseModel;
import com.spring.journalingapp.module.journal.model.Journal;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "t_pictures")
@Data
public class Picture extends BaseModel<String> {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "picture_id")
    private String id;

    @Column(name = "picture")
    private String picture;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_type")
    private String fileType;
    
    @Column(name = "file")
    @Lob
    private byte[] file;

    @ManyToOne
    @JoinColumn(name = "journal_id")
    private Journal journal;

}
