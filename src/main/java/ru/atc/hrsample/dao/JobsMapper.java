package ru.atc.hrsample.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;
import ru.atc.hrsample.entity.JobsEntity;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface JobsMapper {

    @Select("select * from jobs where job_id = #{id} ")
    Optional<JobsEntity> getJobById(String id);

    @Insert("insert into jobs(job_id, job_title, min_salary, max_salary) VALUES " +
            "(#{jobId}, #{jobTitle}, #{minSalary}, #{maxSalary})")
  //  @SelectKey(keyProperty = "jobId", before = true, resultType = Integer.class)
    void insert(JobsEntity entity);

    @Select("select * from jobs")
    List<JobsEntity> getAll();

}
