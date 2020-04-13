import com.zxyun.common.db.mysql.condition.GroupByCondition;
import com.zxyun.common.db.mysql.condition.OnCondition;
import com.zxyun.common.db.mysql.condition.OrderByCondition;
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
        innerJoinSql();
    }

    private static void innerJoinSql () {
        ProductLabel query = new ProductLabel();
        query.setId(0L);
        query.setProductInfoId(0L);
        query.setType(0);
        query.setField("2121");
        query.setValue("");

        SqlActionHelper
                .select()
                .from(ProductLabel.class)
                .find()
                .innerJoin(ProductReason.class)
                .on(OnCondition.template()
                        .append("productInfoId", "productInfoId")
                )
                .where(QueryCondition.template(query)
                        .lt("type", ProductLabel::getType)
                        .lt("field", ProductLabel::getField)
                )
                .groupBy(GroupByCondition.template()
                        .group("productInfoId")
                )
                .orderBy(OrderByCondition.template()
                        .Asc("productInfoId")
                )
                .to();
    }
}
