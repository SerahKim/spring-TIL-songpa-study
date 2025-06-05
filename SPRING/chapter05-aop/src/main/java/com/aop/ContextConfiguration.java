package com.aop;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


/* aspectj의 autoproxy 사용에 관한 설정을 해주어야 advice가 동작한다. */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ContextConfiguration {
}
