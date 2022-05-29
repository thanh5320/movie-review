package com.hust.movie_review.service.template;

import com.hust.movie_review.data.request.actor.StoreRequest;
import com.hust.movie_review.data.request.actor.UpdateRequest;
import com.hust.movie_review.models.Actor;

public interface IActorService extends IService<Actor> {
    Actor insert(StoreRequest request);

    Actor update(UpdateRequest request);
}
