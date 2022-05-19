package com.ssafy.db.repository;

import com.ssafy.db.entity.CCTV;
import com.ssafy.db.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
}
