package com.lin.service.Impl;

//import com.seerbigdata.imms.base.dao.DepartmentDao;
//import com.seerbigdata.imms.base.dto.DepartmentDTO;
//import com.seerbigdata.imms.base.entity.Department;
//import com.seerbigdata.imms.base.service.DepartmentService;
//import com.seerbigdata.util.entity.ListData;
//import com.seerbigdata.util.entity.SearchCriteria;
//import com.seerbigdata.util.entity.TableColumn;
//import com.seerbigdata.util.parm.TableColumnPARM;
//import com.seerbigdata.util.service.TableColumnService;
//import com.seerbigdata.util.support.BaseTableServiceImpl;
//import com.seerbigdata.util.utils.TableUtils;
//import com.seerbigdata.util.utils.ToolUtils;


import com.lin.dao.DepartmentDao;
import com.lin.dto.DepartmentDTO;
import com.lin.entity.Department;
import com.lin.service.DepartmentService;
import com.lin.util.entity.ListData;
import com.lin.util.entity.SearchCriteria;
import com.lin.util.utils.TableUtils;
import com.lin.util.utils.ToolUtils;
import com.yyfly.common.repository.BaseRepository;
import com.yyfly.common.service.impl.BaseServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 部门 service impl
 *

 * @version : 1.0
 */
@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements DepartmentService {

    /**
     * Department dao
     */
    @Autowired
    private DepartmentDao departmentDao;


    /**
     * 表名
     */
    private final String tableName = TableUtils.getTableName(Department.class);








    @Override
    public ListData<Department> findPageList(SearchCriteria searchCriteria) {
        return ListData.of(findAll(searchCriteria.buildSearchParams(), searchCriteria.buildPageRequest()));
    }

    @Override
    public DepartmentDTO toDTO(Department department) {
        if (department == null){
            return null;
        }

        DepartmentDTO dto = new DepartmentDTO();
        BeanUtils.copyProperties(department, dto);
        dto.setEnabled(ToolUtils.statusToBoolean(dto.getStatus()));



        return dto;
    }

    @Override
    public Department toEntity(DepartmentDTO dto) {
        Department department = new Department();
        BeanUtils.copyProperties(dto, department);
        department.setStatus(ToolUtils.booleanToStatus(dto.isEnabled()));
        return department;
    }

    @Override
    public List<DepartmentDTO> toDTOs(List<Department> departments) {
        List<DepartmentDTO> dtos = new ArrayList<>();
        if (departments != null && departments.size() > 0){
            departments.forEach(department -> {
                DepartmentDTO dto = toDTO(department);
                dtos.add(dto);
            });
        }
        return dtos;
    }

    @Override
    public List<Department> toEntitys(List<DepartmentDTO> departmentDTOs) {
        List<Department> departments = new ArrayList<>();
        if (departmentDTOs != null && departmentDTOs.size() > 0){
            departmentDTOs.forEach(departmentDTO -> {
                Department department = toEntity(departmentDTO);
                departments.add(department);
            });
        }
        return departments;
    }

    @Override
    protected BaseRepository<Department> getDao() {
        return departmentDao;
    }
}
