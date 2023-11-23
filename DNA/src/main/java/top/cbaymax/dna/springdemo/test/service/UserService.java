package top.cbaymax.dna.springdemo.test.service;

import top.cbaymax.dna.springdemo.spring.annotation.Component;
import top.cbaymax.dna.springdemo.spring.annotation.Scope;
import top.cbaymax.dna.springdemo.spring.annotation.ScopeType;

/**
 * @author 褚浩
 */
@Component("userService")
@Scope(ScopeType.prototype)
public class UserService {
}
