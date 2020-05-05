package com.katiforis.spring.service.interfaces;

import com.katiforis.spring.Respones.JsonRespone;
import com.katiforis.spring.model.*;
import java.util.List;

public interface UserService {

    User findById(int id);

    User findBySSO(String sso);

    User findByEmail(String email);

    void saveUser(User user);

    List<User> findAllUsers();

    boolean isUserSSOUnique(Integer id, String sso);

    boolean isUserEmailUnique(Integer id, String email);

    JsonRespone registerUser(User user);

    List<Disorder> findAllDisorders();

    List<Disorder> getDisordersHave(Integer userId, Integer have);

    void addDisordersToUser(Integer userId, List<Integer> disorderIds);

    void removeDisordersFromUser(Integer userId, List<Integer> disorderIds);

    List<Order> findUserOrders(Integer userId);
}
