package com.spring.journalingapp.module.journal.model;

import com.spring.journalingapp.core.BaseModel;
import com.spring.journalingapp.module.pictures.model.Picture;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Table(name = "t_journals")
@Data
public class Journal extends BaseModel<String> {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "journal_id")
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @OneToMany(mappedBy = "journal", cascade = CascadeType.ALL)
    private List<Picture> pictures;


}
