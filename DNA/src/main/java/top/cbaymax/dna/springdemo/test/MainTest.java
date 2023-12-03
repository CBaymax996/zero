package top.cbaymax.dna.springdemo.test;

import top.cbaymax.dna.springdemo.spring.annotation.ComponentScan;
import top.cbaymax.dna.springdemo.spring.annotation.Scope;
import top.cbaymax.dna.springdemo.spring.annotation.ScopeType;

/**
 * @author 褚浩
 */
@ComponentScan("top.cbaymax.dna.springdemo.test.demo")
@Scope(ScopeType.prototype)
public class MainTest {


}