package com.katiforis.spring.dao;

import com.katiforis.spring.model.ServiceType;
import com.katiforis.spring.model.Specialite;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("daoservicetype")
public class ServiceTypeDaoImpl extends AbstractDao<Integer, ServiceType>  implements ServiceTypeDao {

    @Override
    public List<ServiceType> findAllServiceType() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<ServiceType> serviceTypes = (List<ServiceType>) criteria.list();
        return serviceTypes;
    }
}
