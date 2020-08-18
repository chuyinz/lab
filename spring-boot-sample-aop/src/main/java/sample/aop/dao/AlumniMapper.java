package sample.aop.dao;
import org.apache.ibatis.annotations.Mapper;
import sample.aop.entity.Alumni;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface AlumniMapper {
    List<Alumni> findAlumniByYear (String year);
    Alumni findAlumniById (BigInteger id);
    void updateAlumni(Alumni alumni);
    void deleteAlumniById(BigInteger id);
}







