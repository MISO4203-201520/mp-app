/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.persistence;

import co.edu.uniandes.csw.appmarketplace.entities.IssueEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author af.esguerra10
 */
public class IssuePersistence extends CrudPersistence<IssueEntity> {

    public IssuePersistence() {
        this.entityClass = IssueEntity.class;
    }

    public List<IssueEntity> getIssuesByAppId(String appId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("appId", "%" + appId + "%");
        return executeListNamedQuery("IssueEntity.getIssuesByAppId", params);
    }
}
