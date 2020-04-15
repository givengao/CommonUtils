import com.zxyun.common.db.mysql.condition.QueryCondition;
import com.zxyun.common.db.mysql.factory.SqlActionHelper;
import model.TableColumnModel;
import org.junit.Test;

import java.util.List;

/**
 * @des:
 * @Author: given
 * @Date 2020/4/14 16:11
 */
public class jdbcTest {

    @Test
    public void test () {
        TableColumnModel query = new TableColumnModel();
        query.setTableName("jbd_stock_sharing_center");
        List<TableColumnModel> tableColumnModels = SqlActionHelper
                .select()
                .from(TableColumnModel.class)
                .find()
                .leftJoin(null)
                .on(null)
                .where(QueryCondition.template(query).eq("TABLE_NAME", TableColumnModel::getTableName))
                .groupBy(null)
                .orderBy(null)
                .toList();

        int i = 0;

    }
}
