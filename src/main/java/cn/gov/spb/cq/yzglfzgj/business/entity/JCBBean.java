package cn.gov.spb.cq.yzglfzgj.business.entity;

import com.grapecity.documents.excel.IRange;
import com.grapecity.documents.excel.IWorksheet;
import com.grapecity.documents.excel.Workbook;
import lombok.Data;
import org.apache.log4j.Logger;

import java.util.List;
@Data
public class JCBBean {
    private static Logger logger = Logger.getLogger(String.valueOf(JCBBean.class));

    String peoples;
    String checkTime;


    /**
     * 读取excel表，标准化为检查表实体列表
     * @param workbook
     * @return
     */
    public static List<JCBBean> getJCBDatas(Workbook workbook ){
        IWorksheet JCBSheet = workbook.getWorksheets().get(0);
        //获取所有可用区域的数据
        IRange range = JCBSheet.getUsedRange();
        Object[][] datas = (Object[][])range.getValue();
        //遍历确定字段顺序
        int timeIdx=0, peopleNumIdx=0, addressIdx=0, peopleIdx=0;
        for (int i = 0; i < datas[1].length; i++) {
            if(datas[1][i].toString().equals("检查时间")) timeIdx =i;
            if(datas[1][i].toString().equals("检查人数")) peopleNumIdx =i;
            if(datas[1][i].toString().equals("检查地点")) addressIdx =i;
            if(datas[1][i].toString().equals("检查人")) peopleIdx =i;
        }
        for (int i = 0; i < datas.length; i++) {
            if(i < 2) continue;
            logger.info("检查时间："+datas[i][timeIdx]+"，检查人："+datas[i][peopleIdx]+"，检查地点："+datas[i][addressIdx]);

        }

        return null;
    }

}
