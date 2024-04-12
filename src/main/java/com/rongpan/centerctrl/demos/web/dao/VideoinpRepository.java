package com.rongpan.centerctrl.demos.web.dao;

import com.rongpan.centerctrl.demos.web.entity.VideocmdEntity;
import com.rongpan.centerctrl.demos.web.entity.VideoinpEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoinpRepository extends JpaRepository<VideoinpEntity,Long> {

}