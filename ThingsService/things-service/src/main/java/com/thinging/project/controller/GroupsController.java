package com.thinging.project.controller;

import com.thinging.project.service.GroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validator;

@RestController
@RequestMapping("/api/groups")
@Api(value="Groups management")
public class GroupsController extends AbstractController{

    private GroupService groupService;

    public GroupsController(GroupService groupService, Validator validator) {
        super(validator);
        this.groupService = groupService;
    }

    @GetMapping
    @ApiOperation("Return all groups")
    public ResponseEntity<?> getGroups(
            @RequestHeader(value = "Authorization", required = false) String token){
        return respondOK(groupService.getAll());
    }

    @GetMapping("/{group_name}")
    @ApiOperation("Return group by name")
    public ResponseEntity<?> getGroup(
            @RequestHeader(value = "Authorization", required = false) String token,
            @PathVariable("group_name") String groupName){

        return respondOK(groupService.get(groupName));
    }

    @PostMapping
    @ApiOperation("Register new group")
    public ResponseEntity<?> createGroup(
            @RequestHeader(value = "Authorization", required = false) String token,
            @RequestParam(value = "Group-Name") String groupName,
            @RequestParam(value = "Description",required = false) String description){

        return respondCreated(groupService.create(groupName,description));
    }


    @DeleteMapping("/{id}")
    @ApiOperation("Remove group")
    public ResponseEntity<?> deleteThing(
            @RequestHeader(value = "Authorization", required = false) String token,
            @PathVariable("id") String id){
        groupService.delete(id);
        return respondOK("deleted");
    }



}
