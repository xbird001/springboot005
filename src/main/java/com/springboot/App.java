package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import com.springcore.bean.Dog;
import com.springcore.bean.Person;
import com.springcore.bean.User;


/**
 * 加载第三方Jar中的Bean进行管理，具体可查看器源码
 * @EnableAutoConfiguration 
 *  	作用是从classpath中搜索 所有 META-INF/spring.factories配置文件，然后将其中的
			org.springframework.boot.autoconfigure.EnableAutoConfiguration key对应的配置加载到spring容器中，
			也可参见org.springframework.boot.autoconfigure包下的META-INF/spring.factories,
			**********************这就是spring的自动配置注解的 自动配置的原理**********************************
 * @author Administrator
 * exclude={UserConfig.class,Person.class}当设置了这个时，Dog.class,User.class,Person.class都将报错
 * 对于通过@Configuration方式配置的Bean，此时，如果想排除掉Dog.class或者User.class需要通过传入UserConfig.class
 * 才能排除，而直接通过传入User.class此时无法排除User该实例对象
 * 
 */
@EnableAutoConfiguration(/*exclude={UserConfig.class,Person.class}*/)
@ComponentScan
public class App {
	
	@Bean
	public Runnable runnable() {
		return () -> {};
	}
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
		for(int i=0; i<2; i++) {
			System.out.println();
		}
		
		System.out.println(context.getBean("runnable"));
		/**
		 * 管理第三方Jar中的bean,主要起作用的是@EnableAutoConfiguration，具体参见beanService项目中的spring.factories文件
		 * 
		 */
		System.out.println(context.getBeansOfType(Runnable.class));
		System.out.println(context.getBean(Dog.class));
		System.out.println(context.getBean(User.class));
		System.out.println(context.getBean(Person.class));
		
		for(int i=0; i<2; i++) {
			System.out.println();
		}
		
		context.close();
	}

}
