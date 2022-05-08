package myspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myspring.dao.MytestDao;

@Service
public class MytestService {

	@Autowired
	private MytestDao dao;
}
