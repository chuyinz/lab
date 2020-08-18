package sample.aop.aspect;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.aspectj.lang.JoinPoint;

import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sample.aop.entity.Admin;
import sample.aop.entity.Alumni;
import sample.aop.service.AlumniService;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Aspect
@Component
public class LoggingAspect {

    public String account="";//用户名
    public static Admin a =new Admin();//记录登录的管理员
    private long startTimeMillis = 0; // 开始时间
    private long endTimeMillis = 0; // 结束时间
    @Autowired
    private AlumniService alumniService;

   // private HttpServletRequest request = null;

    /**
     *
     * @Description: 查询方法调用前触发   记录开始时间
     * @author zyy
     * @date 2019.4.30
     * @param joinPoint
     */
    @Before("execution(* sample.aop.service.AlumniService.findAlumniByYear(..))")
    public void beforeSearch(JoinPoint joinPoint){
        System.out.println("before");
       // request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
       // admin = (Admin)request.getSession().getAttribute("current_user");
        startTimeMillis = System.currentTimeMillis(); //记录方法开始执行的时间
        String startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTimeMillis);  //格式化开始时间
        Logger.getLogger("ReadLog").info("\noperator: admin "+a.getAccount());
        Logger.getLogger("ReadLog").info("start time: "+startTime );
    }

    /**
     *
     * @Description: 查询方法调用后触发   记录结束时间
     * @author zyy
     * @param joinPoint
     */
    @After("execution(* sample.aop.service.AlumniService.findAlumniByYear(..))")
    public  void afterSearch(JoinPoint joinPoint) {
        System.out.println("after");
        endTimeMillis = System.currentTimeMillis();

        //格式化结束时间
        String endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endTimeMillis);


        //PropertyConfigurator.configure("src/log4j.properties");

        Logger.getLogger("ReadLog").info(/*" admin: "+admin.getAccount()+*/"end time: "+endTime);

    }


    @AfterReturning(pointcut="execution(* sample.aop.service.AlumniService.findAlumniByYear(..))", returning="retVal")
    public void logAfterReturningGetAlumni(Object retVal) throws Throwable
    {
        Logger.getLogger("ReadLog").info("Method: sample.aop.service.AlumniService.findAlumniByYear(..) called:\nsearch result:");
        List<Alumni>alumniList=(List<Alumni>)retVal;
        for(int i=0;i<alumniList.size();i++)
        {
            Logger.getLogger("ReadLog").info(alumniList.get(i).getName());
        }
    }





    /**
     *
     * @Description: 更新方法调用前触发   记录开始时间
     * @author zyy
     * @date 2019.4.30
     * @param joinPoint
     */
    @Before("execution(* sample.aop.service.AlumniService.updateAlumni*(..))")
    public void beforeUpdate(JoinPoint joinPoint){
        System.out.println("before");
        // request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // admin = (Admin)request.getSession().getAttribute("current_user");
        startTimeMillis = System.currentTimeMillis(); //记录方法开始执行的时间
        String startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTimeMillis);  //格式化开始时间

        Logger.getLogger("UpdateLog").info("\noperator: admin "+a.getAccount());
        Logger.getLogger("UpdateLog").info("start time: "+startTime );
    }

    /**
     *
     * @Description: 更新方法调用后触发   记录结束时间
     * @author zyy
     * @param joinPoint
     */
    @After("execution(* sample.aop.service.AlumniService.updateAlumni*(..))")
    public  void afterUpdate(JoinPoint joinPoint) {
        System.out.println("after");
        endTimeMillis = System.currentTimeMillis();

        //格式化结束时间
        String endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endTimeMillis);


        //PropertyConfigurator.configure("src/log4j.properties");

        Logger.getLogger("UpdateLog").info(/*" admin: "+admin.getAccount()+*/"end time: "+endTime);

    }


    /**
     * 记录更新的旧值和新值
     * @param joinPoint
     * @param retVal  返回值
     * @throws Throwable
     */
    @AfterReturning(pointcut="execution(* sample.aop.service.AlumniService.updateAlumni(..))", returning="retVal")
    public void logAfterReturningUpdateAlumni(JoinPoint joinPoint,Object retVal) throws Throwable
    {
        Alumni oldAlumni=(Alumni)retVal;


        Logger.getLogger("UpdateLog").info("Method: sample.aop.service.AlumniService.updateAlumni(..) called:\nold value:"+oldAlumni.getName()+" "+oldAlumni.getBirthday() +" "+oldAlumni.getPhone());
        Object[] object=joinPoint.getArgs();
        Alumni newAlumni=new Alumni();
       for(int i=0;i<object.length;i++)
       {
           newAlumni=(Alumni)object[i];
       }
        Logger.getLogger("UpdateLog").info("new value:"+ newAlumni.getName()+" "+newAlumni.getBirthday()+" "+newAlumni.getPhone());


    }



    /**
     * 记录删除的旧值和新值
     * @param joinPoint
     * @param retVal  返回值
     * @throws Throwable
     */
    @AfterReturning(pointcut="execution(* sample.aop.service.AlumniService.updateAlumniByDelete(..))", returning="retVal")
    public void logAfterReturningDeleteAlumni(JoinPoint joinPoint,Object retVal) throws Throwable
    {
        Alumni oldAlumni=(Alumni)retVal;


        Logger.getLogger("UpdateLog").info("Method: sample.aop.service.AlumniService.updateAlumniByDelete(..) called:\nold value:"+oldAlumni.getName()+" "+oldAlumni.getBirthday() +" "+oldAlumni.getPhone());

        Logger.getLogger("UpdateLog").info("new value: null");


    }
    /**
     * 获取用户登录成功传过来的user值
     * @param admin
     * @return
     */
    public static Admin getAdmin(Admin admin){
        a=admin;
        return a;
    }




    /**
     * 管理员登录方法的切入点
     */
    @AfterReturning(pointcut="execution(* sample.aop.service.AdminService.loginValid(..))", argNames = "joinPoint,object", returning = "object")
    public void loginLog(JoinPoint joinPoint, Object object) throws Throwable {
        @SuppressWarnings("unchecked")
        //获取service登录执行后的参数  这里返回map
        Map<String, Object> map=(Map<String, Object>) object;
        System.out.println(map);
        if (map.get("status")=="error") {
            Logger.getLogger("LoginLog").info("Login error!");
        }
        if (joinPoint.getArgs() == null) {// 没有参数
            Logger.getLogger("LoginLog").info("Login error!");
        }
        account=a.getAccount();
        startTimeMillis = System.currentTimeMillis(); //记录方法开始执行的时间
        String startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTimeMillis);  //格式化开始时间

        Logger.getLogger("LoginLog").info("\nLogin admin:"+account+" success!");
        Logger.getLogger("LoginLog").info("Login time: "+startTime );
    }





}
