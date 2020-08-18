
package sample.aop.service;

import org.apache.poi.hssf.record.crypto.Biff8DecryptingStream;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import sample.aop.dao.AlumniMapper;
import sample.aop.entity.Alumni;
import sun.awt.image.PixelConverter;

import java.math.BigInteger;
import java.util.List;

@Service
public class AlumniService{

	@Autowired
	protected AlumniMapper alumniMapper;


	public List<Alumni> findAlumniByYear (String year)
	{
		return alumniMapper.findAlumniByYear(year);
	}

	public Alumni findAlumniById(BigInteger id){return alumniMapper.findAlumniById(id);}
	public  Alumni updateAlumni(Alumni alumni)
	{
		Alumni oldAlumni= findAlumniById(alumni.getId());
		alumniMapper.updateAlumni(alumni);
		return oldAlumni;
	}


	public Alumni updateAlumniByDelete(BigInteger id)
	{
		Alumni oldAlumni= findAlumniById(id);
		alumniMapper.deleteAlumniById(id);
		return oldAlumni;
	}



}
