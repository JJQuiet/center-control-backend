package com.rongpan.centerctrl.demos.web.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate //更新变化的字段
public class VideocmdEntity implements Serializable {
    private static final long serialVersionUID = -6240549125755016405L;
    @Id
    //@GeneratedValue
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column()
    private String videoname;

    @Column()
    private String aliasname;

    @Column()
    private String outputtype;
    @Column()
    private String cmdname;//full four
    @Column()
    private String cmddata;

}
