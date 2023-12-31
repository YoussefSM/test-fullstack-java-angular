package com.finalgo.application.dao;

import com.finalgo.application.entity.Project;
import com.finalgo.application.entity.User;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;

@Service
public class ProjectDao extends AbstractGenericDao<Project>{
    public ProjectDao () {
        super(Project.class);
    }

    public List<Project> getProjectsByOwner(String ownerUsername) {
        String query = "FROM Project p WHERE p.ownerUsername = :ownerUsername";
        try {
            Query<Project> hqlQuery = getCurrentSession().createQuery(query, Project.class);
            hqlQuery.setParameter("ownerUsername", ownerUsername);
            return hqlQuery.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
