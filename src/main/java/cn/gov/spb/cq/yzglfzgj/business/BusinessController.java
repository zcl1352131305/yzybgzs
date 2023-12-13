package cn.gov.spb.cq.yzglfzgj.business;

import cn.gov.spb.cq.yzglfzgj.business.entity.JCBBean;
import cn.gov.spb.cq.yzglfzgj.utils.SVNUtil;
import com.grapecity.documents.excel.IWorksheet;
import com.grapecity.documents.excel.Workbook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.tmatesoft.svn.core.SVNException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2017/09/20
 * Time: 15:46
 */
@Controller
@RequestMapping(value = "/business")
@Slf4j
public class BusinessController {

    @GetMapping
    public ModelAndView index() throws SVNException {
        /*Workbook workbook = new Workbook();
        workbook.open(new ByteArrayInputStream(SVNUtil.getSvnFile("行政执法工作台账.xlsx").toByteArray()));
        JCBBean.getJCBDatas(workbook);*/
        return new ModelAndView("business/index");
    }

    @GetMapping("/home")
    public ModelAndView home() throws SVNException {

        return new ModelAndView("business/home_page");
    }


}
