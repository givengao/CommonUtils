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
        query.setTableName("jbd_stock_take");
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

        String template = "**简要描述：**\n" +
                "\n" +
                "- ***********************示例\n" +
                "\n" +
                "**请求URL：**\n" +
                "- `https://admin.dev.geebento.com/ordergoods/api/sharing/stock/center/findPage`\n" +
                "\n" +
                "**请求方式：**\n" +
                "- post\n" +
                "\n" +
                "**请求示例**\n" +
                "\n" +
                "```\n" +
                "\n" +
                "```\n" +
                "\n" +
                "**请求参数：**\n" +
                "\n" +
                "|参数名|必选|类型|说明|\n" +
                "|:----    |:---|:----- |-----   |\n" +
                "|pageSize |否 |int  |每页的记录数 |\n" +
                "|pageNumber |否 |int  |当前页 |\n" +
                "|isRowCount |否 |boolean  | 是否查询总数 |\n" +
                "|sort |否 |String | 排序方式 |\n" +
                "|orderFields |否 |String[] | 排序字段 |\n";
        for (TableColumnModel tableColumnModel : tableColumnModels) {
            String column = "|" + tableColumnModel.getColumnName() + "|" + tableColumnModel.getIsNull() + "|" + tableColumnModel.getDataType() + "|" + tableColumnModel.getColumnComment()
                    + "|\n";
            template += column;
        }
        template = template +"\n" +
                "\n" +
                " **返回示例**\n" +
                "\n" +
                "```\n" +
                "{\n" +
                "    \"code\": 200,\n" +
                "    \"msg\": \"正常\",\n" +
                "    \"data\": {\n" +
                "        \"data\": [\n" +
                "            {\n" +
                "\t\t\t\t\n";
        for (TableColumnModel tableColumnModel : tableColumnModels) {
            String column = "\t\t\t\t" + "\"" + tableColumnModel.getColumnName() + "\"" + ":" + tableColumnModel.getDataType() + "\n";
            template += column;
        }
        template = template +"\t\t\t}\n" +
                "        ],\n" +
                "        \"startIndex\": 0,\n" +
                "        \"pageSize\": 20,\n" +
                "        \"rowCount\": 1,\n" +
                "        \"pageCount\": 1,\n" +
                "        \"pageNumber\": 1,\n" +
                "    }\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "**返回参数说明**\n" +
                "\n" +
                "|参数名|类型|说明|\n" +
                "|:-----  |:-----|-----|\n" +
                "|pageSize |int  |每页的记录数 |\n" +
                "|pageNumber |int  |当前页 |\n" +
                "|pageCount |long  |总页数 |\n" +
                "|rowCount |long  |总记录数 |\n" +
                "|data |List |查询结果集 |\n" +
                "\n" +
                " **data参数说明**\n" +
                "\n" +
                "|参数名|类型|说明|\n" +
                "|:-----  |:-----|-----|\n";
        for (TableColumnModel tableColumnModel : tableColumnModels) {
            String column = "|" + tableColumnModel.getColumnName() + "|" + tableColumnModel.getDataType() + "|" + tableColumnModel.getColumnComment()
                    + "|\n";
            template += column;
        }
        template = template +"\n" +
                " **备注**\n" +
                "\n" +
                "- 更多返回错误代码请看首页的错误代码描述\n" +
                "\n" +
                "\n";

        System.out.println(template);
    }
}
