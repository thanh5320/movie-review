package com.hust.movie_review.service.template;

import com.hust.movie_review.data.request.role.StoreRequest;
import com.hust.movie_review.data.request.role.UpdateRequest;
import com.hust.movie_review.models.Role;

public interface IRoleService extends IService<Role, Integer>{
    Role insert(StoreRequest request);
    Role update(UpdateRequest request);
}
