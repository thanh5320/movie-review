package com.hust.movie_review.service;

import com.hust.movie_review.config.exception.ApiException;
import com.hust.movie_review.data.request.actor.StoreRequest;
import com.hust.movie_review.data.request.actor.UpdateRequest;
import com.hust.movie_review.models.Actor;
import com.hust.movie_review.models.Country;
import com.hust.movie_review.repositories.IActorRepository;
import com.hust.movie_review.repositories.ICountryRepository;
import com.hust.movie_review.service.template.IActorService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActorServiceImpl extends BaseService<Actor> implements IActorService {
    IActorRepository actorRepository;
    ICountryRepository countryRepository;

    public ActorServiceImpl(IActorRepository actorRepository, ICountryRepository countryRepository){
        super(actorRepository);
        this.actorRepository = actorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public Actor insert(StoreRequest request) {
        Actor actor = new Actor();
        actor.setFullName(request.getFullName());
        actor.setGender(request.getGender());
        actor.setYearBirthday(request.getYearBirthday());

        Country country = countryRepository.findByCode(request.getCountryCode());
        actor.setCountry(country);

        return actorRepository.save(actor);
    }

    @SneakyThrows
    @Override
    public Actor update(UpdateRequest request) {
        Optional<Actor> optional = actorRepository.findById(request.getId());
        if(optional.isEmpty()){
            throw new ApiException("Không tìm thấy diễn viên có id tương ứng");
        }

        Actor actor = optional.get();
        actor.setFullName(request.getFullName());
        actor.setGender(request.getGender());
        actor.setYearBirthday(request.getYearBirthday());

        Country country = countryRepository.findByCode(request.getCountryCode());
        actor.setCountry(country);
        return actorRepository.save(actor);
    }
}
