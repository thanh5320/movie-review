package com.hust.movie_review.controllers;

import com.hust.movie_review.data.request.actor.StoreRequest;
import com.hust.movie_review.data.request.actor.UpdateRequest;
import com.hust.movie_review.data.response.DfResponse;
import com.hust.movie_review.models.Actor;
import com.hust.movie_review.service.template.IActorService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/actor")
public class ActorController {
    IActorService actorService;

    public ActorController(IActorService actorService){
        this.actorService = actorService;
    }

    @GetMapping("listing")
    public DfResponse<List<Actor>> listing(){
        return DfResponse.okEntity(actorService.listing());
    }

    @GetMapping("detail/{id}")
    public DfResponse<Actor> detail(@PathVariable int id){
        return DfResponse.okEntity(actorService.detail(id));
    }

    @PostMapping("store")
    public DfResponse<Actor> store(@RequestBody @Valid StoreRequest request){
        return DfResponse.okEntity(actorService.insert(request));
    }


    @PostMapping("update")
    public DfResponse<Actor> update(@RequestBody @Valid UpdateRequest request){
        return DfResponse.okEntity(actorService.update(request));
    }

    @PostMapping("delete/{id}")
    public DfResponse<String> delete(@PathVariable int id){
        boolean result = actorService.delete(id);
        if(!result){
            return DfResponse.badRequest("Có lỗi xảy ra!");
        }

        return DfResponse.okEntity("Xóa thành công");
    }
}
