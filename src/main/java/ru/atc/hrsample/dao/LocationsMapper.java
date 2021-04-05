package ru.atc.hrsample.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import ru.atc.hrsample.entity.LocationsEntity;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface LocationsMapper {

    @Select("select * from locations where location_id = #{id} ")
    Optional<LocationsEntity> getLocationById(Integer id);

    @Insert("insert into locations(location_id, street_address, city) VALUES " +
            "(#{locationId}, #{streetAddress}, #{city})")
    @SelectKey(keyProperty = "locationId", before = true, resultType = Integer.class,
            statement = "select nextval('locations_seq')")
//    @Options(useGeneratedKeys = true, keyProperty = "locationsId")
    void insert(LocationsEntity entity);

    @Select("select * from locations")
    List<LocationsEntity> getAll();
}
