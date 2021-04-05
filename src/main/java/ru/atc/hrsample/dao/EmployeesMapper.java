package ru.atc.hrsample.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;
import ru.atc.hrsample.entity.EmployeesEntity;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface EmployeesMapper {

    @Select("select * from employees where employee_id = #{id}")
    Optional<EmployeesEntity> getEmployeeById(Integer id);


    @Insert("insert into employees(employee_id, first_name,last_name, email, phone_number, " +
            "hire_date, job_id, salary, manager_id, department_id) VALUES " +
            "(#{employeeId}, #{firstName}, #{lastName}, #{email}, #{phoneNumber}, " +
            "#{hireDate}, #{jobId}, #{salary}, #{managerId}, #{departmentId})")
    @SelectKey(keyProperty = "employeeId", before = true, resultType = Integer.class,
            statement = "select nextval('employees_seq')")
    void insert(EmployeesEntity entity);


    @Select("select * from employees")
    List<EmployeesEntity> getAll();
}
