package com.example.Controller;

import com.example.Common.BaseException;
import com.example.Model.Person;
import com.example.Model.RespEntity;
import com.example.Service.PersonService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("prs")
public class PersonController {
//    @RequestMapping(value="create", method = RequestMethod.POST)
//    @ResponseBody
//    public RespEntity addPerson(@RequestBody Person person){
//        if(person.getName())
//        return new RespEntity(RespEntity.RespCode.SUCCESS, person);
//    }

    @RequestMapping(value = "find", method = RequestMethod.GET)
    @ResponseBody
    public RespEntity findPerson(@RequestParam("name") String name){
        Person person;
        PersonService personService = new PersonService();
        try{
            person = personService.findPersonByName(name);
        }catch (BaseException e){
            return new RespEntity(RespEntity.RespCode.BAD_REQUEST, e.getMessage());
        }
        return new RespEntity(RespEntity.RespCode.SUCCESS, person);
    }

}
