//package PillSafe.PillSafeweb.repository;
//import PillSafe.PillSafeweb.Entity.User;
//import org.springframework.stereotype.Repository;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import java.util.List;
//
//@Repository
//public class UserRepositoryImpl implements UserRepository {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    public void flush() {
//        entityManager.flush();
//    }
//
//    public <S extends User> S saveAndFlush(S entity) {
//        entityManager.persist(entity);
//        entityManager.flush();
//        return entity;
//    }
//    @Override
//    public User findByGoogleId(String googleId) {
//        return entityManager.createQuery("SELECT u FROM User u WHERE u.googleId = :googleId", User.class)
//                .setParameter("googleId", googleId)
//                .getSingleResult();
//    }
//
//    @Override
//    public User findByEmail(String email) {
//        return entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
//                .setParameter("email", email)
//                .getSingleResult();
//    }
//
//    @Override
//    public List<User> findByAgeGreaterThan(int age) {
//        return entityManager.createQuery("SELECT u FROM User u WHERE u.age > :age", User.class)
//                .setParameter("age", age)
//                .getResultList();
//    }
//
//    @Override
//    public List<User> searchUsersByNameKeyword(String keyword) {
//        return entityManager.createQuery("SELECT u FROM User u WHERE u.name LIKE :keyword", User.class)
//                .setParameter("keyword", "%" + keyword + "%")
//                .getResultList();
//    }
//
////    @Override
////    public <S extends User> S save(S entity) {
////        entityManager.persist(entity);
////        return entity;
////    }
////
////    @Override
////    public <S extends User> List<S> saveAll(Iterable<S> entities) {
////        for (S entity : entities) {
////            entityManager.persist(entity);
////        }
////        return (List<S>) entities;
////    }
//
////    @Override
////    public User findById(Long id) {
////        return entityManager.find(User.class, id);
////    }
////
////    @Override
////    public boolean existsById(Long id) {
////        return entityManager.find(User.class, id) != null;
////    }
//
//    @Override
//    public List<User> findAll() {
//        return entityManager.createQuery("SELECT u FROM User u", User.class)
//                .getResultList();
//    }
//
//    @Override
//    public List<User> findAllById(Iterable<Long> ids) {
//        return entityManager.createQuery("SELECT u FROM User u WHERE u.id IN :ids", User.class)
//                .setParameter("ids", ids)
//                .getResultList();
//    }
//
//    @Override
//    public long count() {
//        return entityManager.createQuery("SELECT COUNT(u) FROM User u", Long.class)
//                .getSingleResult();
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        User user = entityManager.find(User.class, id);
//        if (user != null) {
//            entityManager.remove(user);
//        }
//    }
//
//    @Override
//    public void delete(User entity) {
//        entityManager.remove(entity);
//    }
//
//    @Override
//    public void deleteAll(Iterable<? extends User> entities) {
//        for (User entity : entities) {
//            entityManager.remove(entity);
//        }
//    }
//
//    @Override
//    public void deleteAll() {
//        entityManager.createQuery("DELETE FROM User").executeUpdate();
//    }
//}