package com.dao;

import com.model.Detective;

public interface IDetectiveDAO extends IDAO<Detective>{
    boolean findById(long id);
    boolean deleteAll();
}
