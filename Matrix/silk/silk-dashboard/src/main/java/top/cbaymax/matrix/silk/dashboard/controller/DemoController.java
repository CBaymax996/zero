package top.cbaymax.matrix.silk.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.cbaymax.matrix.silk.dashboard.controller.jpa.UserDO;

import java.util.Optional;

@RestController
public class DemoController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/hi")
    public String sayHi(String name) {
        return String.format("Hi, %s", Optional.ofNullable(name).orElse("Somebody"));
    }

    @GetMapping(path="/all")
    @ResponseBody
    public Iterable<UserDO> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }
}