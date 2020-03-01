package com.lin.service;

//import com.seerbigdata.imms.base.dto.DepartmentDTO;
//import com.seerbigdata.imms.base.entity.Department;
//import com.seerbigdata.util.entity.DTOConvert;
//import com.seerbigdata.util.entity.ListData;
//import com.seerbigdata.util.entity.SearchCriteria;
//import com.seerbigdata.util.support.BaseTableService;



import com.lin.dto.DepartmentDTO;
import com.lin.entity.Department;
import com.lin.util.entity.DTOConvert;
import com.lin.util.entity.ListData;
import com.lin.util.entity.SearchCriteria;
import com.yyfly.common.service.BaseService;
import com.yyfly.common.service.impl.BaseServiceImpl;

/**
 * 部门 service
 *

 * @version : 1.0
 */
public interface DepartmentService extends BaseService<Department> , DTOConvert<Department, DepartmentDTO> {

      /**
       * Find page list list data.
       *
       * @param searchCriteria the search criteria
       * @return the list data
       * @author : fang / 2019-02-15
       */
      ListData<Department> findPageList(SearchCriteria searchCriteria);
  }
