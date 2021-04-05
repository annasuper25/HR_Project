package ru.atc.hrsample.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import ru.atc.hrsample.entity.DepartmentsEntity;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface DepartmentsMapper {

    @Select("select * from departments where department_id = #{id} ")
    Optional<DepartmentsEntity> getDepartmentById(Integer id);


    @Insert("insert into departments(department_id, department_name, manager_id, location_id) VALUES " +
            "(#{departmentId}, #{departmentName}, #{managerId}, #{locationId})")
    @SelectKey(keyProperty = "departmentId", before = true, resultType = Integer.class,
            statement = "select nextval('departments_seq')")
//    @Options(useGeneratedKeys = true, keyProperty = "locationsId")
    void insert(DepartmentsEntity entity);



    @Select("select * from departments")
    List<DepartmentsEntity> getAll();

    @Select("select * from departments where department_name = #{departmentName}")
    List<DepartmentsEntity> getAllFilteredByDeparmentName(String departmentName);

    @Select("select * from departments INNER JOIN employees ON departments.manager_id = employees.employee_id\n" +
            "where employees.last_name = #{managerLastName}")
    List<DepartmentsEntity> getAllFilteredByManagerLastName(String managerLastName);


    @Select("select * from departments INNER JOIN employees ON departments.manager_id = employees.employee_id\n" +
            "where department_name = #{arg0} and employees.last_name = #{arg1}")
    List<DepartmentsEntity> getAllFilteredByManagerAndDepName(String departmentName, String managerLastName);


   /* @Update("update departments SET " +
            "department_name = #{departmentName} , manager_id = CASE WHEN #{managerId} = 0 THEN manager_id ELSE #{managerId}, " +
            "location_id =  CASE WHEN #{locationId} = 0 THEN location_id ELSE #{locationId}  WHERE department_id = #{departmentId} ")*/
    @Update("update departments SET department_name = #{departmentName}, manager_id = #{managerId}, location_id = #{locationId} " +
            "WHERE department_id = #{departmentId} RETURNING *")
    void updateDepartment(DepartmentsEntity departmentsEntity);
}
