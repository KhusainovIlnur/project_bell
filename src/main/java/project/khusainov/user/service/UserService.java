package project.khusainov.user.service;

import project.khusainov.user.view.UserByIdRespView;
import project.khusainov.user.view.UserListReqView;
import project.khusainov.user.view.UserListRespView;
import project.khusainov.user.view.UserSaveReqView;
import project.khusainov.user.view.UserUpdateReqView;

import java.util.List;

public interface UserService {

    /**
     * Получить список организаций по фильтру
     * @param userListReqView
     * @return
     */
    public List<UserListRespView> getUserByFilter(UserListReqView userListReqView);

    /**
     * Получить организацию по id
     * @param id
     * @return
     */
    public UserByIdRespView getUserById(Long id);

    /**
     * Добавить организацию
     * @param userSaveReqView
     */
    public void saveUser(UserSaveReqView userSaveReqView);

    /**
     * Обновить организацию
     * @param userUpdateReqView
     */
    public void updateUser(UserUpdateReqView userUpdateReqView);
}
