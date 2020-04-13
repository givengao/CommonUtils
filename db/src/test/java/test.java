import com.zxyun.common.db.mysql.condition.GroupByCondition;
import com.zxyun.common.db.mysql.condition.OnCondition;
import com.zxyun.common.db.mysql.condition.QueryCondition;
import com.zxyun.common.db.mysql.factory.SqlActionHelper;
import model.ProductLabel;
import model.ProductReason;

/**
 * @des:
 * @Author: given
 * @Date 2020/4/10 15:23
 */
public class test {

    public static void main (String args[]) {
        SqlActionHelper
                .select()
                .from(ProductLabel.class)
                .find()
                .column("id")
                .column("productInfoId")
                .innerJoin(ProductReason.class)
                .on(new OnCondition<>())
                .where(new QueryCondition<>())
                .groupBy(new GroupByCondition<>())
                .to();

    }
}
