package com.example.Controller;

import com.example.Common.BaseException;
import com.example.Model.Person;
import com.example.Model.RespEntity;
import com.example.Service.PersonService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("prs")
public class PersonController {
    PersonService personService = new PersonService();

    @RequestMapping(value="create", method = RequestMethod.POST)
    @ResponseBody
    public RespEntity addPerson(@RequestBody Person person){
        if(person.getName().isEmpty() || person.getLabel().isEmpty())
            return new RespEntity(RespEntity.RespCode.BAD_REQUEST, "姓名和标签,缺一不可");
        personService.addPerson(person);
        return new RespEntity(RespEntity.RespCode.SUCCESS, person);
    }

    @RequestMapping(value = "find", method = RequestMethod.GET)
    @ResponseBody
    public RespEntity findPerson(@RequestParam("name") String name){
        Person person;
        try{
            person = personService.findPersonByName(name);
        }catch (BaseException e){
            return new RespEntity(RespEntity.RespCode.BAD_REQUEST, e.getMessage());
        }
        return new RespEntity(RespEntity.RespCode.SUCCESS, person);
    }

}
