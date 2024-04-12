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
public class ViceoutEntity implements Serializable {
    private static final long serialVersionUID = -6131248115755016405L;
    @Id
    //@GeneratedValue
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    /**
     * 输出
     */
    @Column()
    private String vicename;
    @Column()
    private String aliasname;
    @Column()
    private String outputtype;
}