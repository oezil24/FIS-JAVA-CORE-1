package com.dao;

import com.dao.impl.CriminalCaseDAO;
import com.model.CriminalCase;

public interface ICriminalCaseDAO extends IDAO<CriminalCase>{
    boolean findById(long id);
    boolean deleteAll();
}
