import com.zxyun.common.db.mysql.factory.SqlActionHelper;
import model.ProductLabel;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @des:
 * @Author: given
 * @Date 2020/4/14 15:51
 */
public class mysqlTest {

    @Test
    public void connectMysql () throws IOException {
        //1.读取配置文件,配置文件是mybatis的全局配置文件，不是单独个配置文件
        InputStream inputStream = Resources.getResourceAsStream("mybatisConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        //3.使用工厂生产SqlSession对象
        SqlSession sqlSession=  sqlSessionFactory.openSession();


        ProductLabel query = new ProductLabel();
        query.setId(0L);
        query.setProductInfoId(0L);
        query.setType(0);
        query.setField("2121");
        query.setValue("");

        String sql = SqlActionHelper
                .select()
                .from(ProductLabel.class)
                .find()
                .column("productId")
                .to();




        sqlSession.select(sql, new ResultHandler() {
            @Override
            public void handleResult(ResultContext resultContext) {
                ResultContext context = resultContext;
                Object object = resultContext.getResultObject();
                Integer count = resultContext.getResultCount();
            }
        });

        //4.使用SqlSession创建Dao接口的代理对象
        inputStream.close();
        sqlSession.close();
    }
}
