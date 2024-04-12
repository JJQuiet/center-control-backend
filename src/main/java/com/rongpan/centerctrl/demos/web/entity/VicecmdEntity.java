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
public class VicecmdEntity implements Serializable {
    private static final long serialVersionUID = -6131248115755016405L;
    @Id
    //@GeneratedValue
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    /**
     * 输入
     */
    @Column()
    private String vicename;
    @Column()
    private String aliasname;
    @Column()
    private String inputtype;
    @Column()
    private String cmddata;
}
