# spring-web-auth-demo

spring-security的文档很坑,能找的资料绝大多数是JSP模板引擎(没有springMVC默认的模板取参限制),在模板引擎上花的时间最多,也是尝试了下groovy,确实很爽,比scala模板简单易懂一些.就易用性和参考资料上shiro完爆ss.唯一坚持用ss的理由主要在于它自带了csrf防御机制,可以省点心(实际也只是加个简单的拦截器而已).听说SS有个很蛋疼的设定,不同路径下登陆同一个账户(比如/user/login和/admin/login)会出现,详细见[这里](http://blog.csdn.net/liufeng520/article/details/40615925),而shiro没这个问题.

## 踩的坑

1. spring-security傻逼地把变量放request attribute,而默认groovy模板不允许获取request的值,需要自己指定viewResolver的属性
2. spring-security默认登陆成功的路径是/login?error,NMB的error springMVC会视为null,无法获取,文档上写的在模板用param.error获取,问题是模板TM取不到路径参数,只能从modelMap里取参数
3. idea对groovy模板的支持挺坑的,光是怎么指定模板文件为groovy模板就搞了大半小时
4. spring-security的jdbc模板太简单了,只能解决简单的sql查询用户密码并进行比对,要实现结合Dao层验证用户还是需要自己定义(如demo里的UserDetailService)
5. ldap太复杂了,概念不懂无法尝试
6. spring-boot默认spring-security-core的版本为3.2.8,而这里指定spring-security-web的版本是4.0.3,因为配置顺序优先用了第一个配置,导致4.0.3无法运作,比较愚蠢的错误.如果要用spring-boot,最好检查一下pom的当前版本
7. Optinal居然要先用@RequestParam注解,否则无法使用
8. 虽然spring-boot里说可以自动使用freemarker等模板,但实际上依旧需要开发者在maven里显式地添加依赖,而这一点在文档里根本没写,最后是找到官方demo才知道的
9. 默认`GrantedAuthority`的实现必须有`ROLE_`前缀,否则无法生效!
10. 想要让controller的方法使用`@Secured`,方法必须是`public`!!!
11. 按照之前的代码结构,controller和main入口放在同一个class里,出现了诡异的authBuilder抛错,错误为builder被错误地重复创建了,没查到原因,直接照着别人的demo把controller和main入口拆开就正常了,魔法一般.
12.logout默认`POST`方法,必须校验CSRFToken
13. 默认的未登录的用户角色为`ROLE_ANONYMOUS`
14. csrf默认用httpSession来存,把容器的session指定给memcached等公有cache上即可
15. 默认csrf不会拦截GET,TRACE,HEAD,OPTIONS这四个http method,可额外定义忽略规则指定不需要CSRF校验的请求
16. `exceptionHandling()`错误处理的handing注册方法,未尝试,留坑.
17. 想要用盐加密密码的话,如demo里需要自己new一个`DaoAuthenticationProvider`定义`SaltSource`(官方建议使用bcrypt来加密,看不懂算法,说是自动加盐不需要开发关心密码盐的问题)

## 未解决的坑

1. idea突然无法断点debug,原因不明
4. RememberMe的机制不明,考虑到扩展性同csrf,需要实现一些handler,具体还没看
7. spring-boot的管理,系统参数,jmx之类的都还没看
8. 如何实现多层RememberMe,以及实现服务器之间的通信(是否使用CAS那反人类的玩意儿)
9. spring-boot似乎没有很好的asset-pipeline方案,如果前端使用less,coffee之类的,也不知道要怎么动态调试.