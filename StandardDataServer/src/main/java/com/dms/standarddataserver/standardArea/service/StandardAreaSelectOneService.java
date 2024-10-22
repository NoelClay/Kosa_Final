package com.dms.standarddataserver.standardArea.service;

import com.dms.standarddataserver.standardArea.dto.StandardAreaDTO;

public interface StandardAreaSelectOneService {
    StandardAreaDTO selectOne(String standardAreaName);
}
