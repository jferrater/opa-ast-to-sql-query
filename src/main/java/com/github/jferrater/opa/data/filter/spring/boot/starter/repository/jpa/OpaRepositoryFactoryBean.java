package com.github.jferrater.opa.data.filter.spring.boot.starter.repository.jpa;

import com.github.jferrater.opa.ast.db.query.service.OpaClientService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

/**
 *
 * @author joffryferrater
 *
 * The repository factory bean
 *
 * @param <R> The JpaRepository implementation
 * @param <T> The managed entity
 * @param <ID> The id of the managed entity
 */
public class OpaRepositoryFactoryBean<R extends JpaRepository<T, ID>, T, ID> extends JpaRepositoryFactoryBean<R, T, ID> {

    @Resource(name = "opaClientService")
    private OpaClientService<T> opaClientService;

    /**
     * Creates a new {@link JpaRepositoryFactoryBean} for the given repository interface.
     *
     * @param repositoryInterface must not be {@literal null}.
     */
    public OpaRepositoryFactoryBean(Class<? extends R> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new OpaRepositoryFactory<>(entityManager, opaClientService);
    }

}
