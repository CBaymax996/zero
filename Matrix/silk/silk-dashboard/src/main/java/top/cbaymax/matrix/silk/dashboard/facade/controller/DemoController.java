package top.cbaymax.matrix.silk.dashboard.facade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.cbaymax.matrix.silk.dashboard.manager.repository.jpa.UserDO;
import top.cbaymax.matrix.silk.dashboard.infrastructure.error.SilkError;
import top.cbaymax.matrix.silk.dashboard.domain.entity.user.UserError;
import top.cbaymax.matrix.silk.dashboard.manager.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class DemoController {

    @Autowired
    private UserRepository userRepository;


    @RequestMapping("/hi")
    @ResponseBody
    public String sayHi(String name) {
        return String.format("Hi, %s", Optional.ofNullable(name).orElse("Somebody"));
    }

    @RequestMapping("/h2")
    @ResponseBody
    public Map<String, String> sayHi2() {
        Map<String, String> r = new HashMap<>();
        r.put("key", "value");
        return r;

    }


    @GetMapping(path = "/all")
    @ResponseBody
    public Iterable<UserDO> getAllUsers() {
        // This returns a JSON or XML with the users
        throw new SilkError(UserError.user_not_find);
    }
}