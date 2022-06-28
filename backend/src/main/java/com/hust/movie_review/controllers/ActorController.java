package com.hust.movie_review.controllers;

import com.hust.movie_review.data.mapper.ActorMapper;
import com.hust.movie_review.data.request.actor.StoreRequest;
import com.hust.movie_review.data.request.actor.UpdateRequest;
import com.hust.movie_review.data.response.ActorResponse;
import com.hust.movie_review.data.response.DfResponse;
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
    public DfResponse<List<ActorResponse>> listing(){
        return DfResponse.okEntity(ActorMapper.toActorResponses(actorService.listing()));
    }

    @GetMapping("detail/{id}")
    public DfResponse<ActorResponse> detail(@PathVariable int id){
        return DfResponse.okEntity(ActorMapper.toActorResponse(actorService.detail(id)));
    }

    @PostMapping("store")
    public DfResponse<ActorResponse> store(@RequestBody @Valid StoreRequest request){
        return DfResponse.okEntity(ActorMapper.toActorResponse(actorService.insert(request)));
    }


    @PostMapping("update")
    public DfResponse<ActorResponse> update(@RequestBody @Valid UpdateRequest request){
        return DfResponse.okEntity(ActorMapper.toActorResponse(actorService.update(request)));
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
