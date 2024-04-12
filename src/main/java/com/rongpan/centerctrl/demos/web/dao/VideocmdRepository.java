package com.rongpan.centerctrl.demos.web.dao;

import com.rongpan.centerctrl.demos.web.entity.VideocmdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VideocmdRepository extends JpaRepository<VideocmdEntity,Long> {

}