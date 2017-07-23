package com.easyop.service;

import java.util.ArrayList;

import com.easyop.dao.TnewsDAO;
import com.easyop.model.Tnews;

public class EditorSer{
	private TnewsDAO tnewsDao;
	public TnewsDAO getTnewsDao() {
		return tnewsDao;
	}
	public void setTnewsDao(TnewsDAO tnewsDao) {
		this.tnewsDao = tnewsDao;
	}
	
	public ArrayList<Tnews> getDataList(){
		return (ArrayList<Tnews>) tnewsDao.findAll();
	}

}
