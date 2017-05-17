import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

/**
 * Created by Administrator on 2017/5/13/013.
 * 线程安全
 *
 */
public class SqlConnection {
    //ThreadLocal 保存Connection变量
    private static ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<Connection>();
    private static Connection getConnection(){
        //如果connectionThread 没有本线程对应的Connection ，创建一个connection对象
        if (connectionThreadLocal.get() == null){
            Connection con = getConnection();
            connectionThreadLocal.set(con);
            return con;
        }else {
            return connectionThreadLocal.get();
        }
    }

    public void addTopic (){
        try {
            Statement st  = getConnection().createStatement();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
