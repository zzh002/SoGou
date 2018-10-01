package com.spider.demo.controller;

import com.spider.demo.dataobject.LexiconCategory;
import com.spider.demo.dataobject.LexiconInfo;
import com.spider.demo.dataobject.User;
import com.spider.demo.dto.LexiconDTO;
import com.spider.demo.service.CategoryService;
import com.spider.demo.service.LexiconService;
import com.spider.demo.sogou.SoGou;
import com.spider.demo.utils.FileFinderUtil;
import com.spider.demo.utils.FileToStringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Main")
@Slf4j
public class MainController {

    private static Integer count = 20;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private LexiconService lexiconService;

    /**
     * 搜索主界面
     * @param map
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    public ModelAndView main(Map<String, Object> map,
                             HttpSession session) throws Exception {

        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setUsername(" ");
        }

        File baseDir = new File("G:\\cell");
        if (!baseDir.exists() || !baseDir.isDirectory()) {
            System.out.println("文件查找失败：不是一个目录！");
        }
        File[] files = baseDir.listFiles();
        List<File> fileList = new ArrayList(Arrays.asList(files));
        List<LexiconDTO> lexiconDTOList = this.getList(fileList,"G:\\文本");


        map.put("user",user);
        map.put("lexiconDTOList",lexiconDTOList);

        return new ModelAndView("sogou/main",map);
    }

    /**
     * 搜索界面
     * @param keyword
     * @param map
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("/detail")
    public ModelAndView detail(@RequestParam("keyword") String keyword,
                               Map<String, Object> map,
                               HttpSession session) throws Exception {

        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setUsername(" ");
        }

        List<File> fileList = FileFinderUtil.findFiles("G:\\cell",keyword,count);
        List<LexiconDTO> lexiconDTOList = this.getList(fileList,"G:\\文本");

        map.put("user",user);
        map.put("lexiconDTOList",lexiconDTOList);

        return new ModelAndView("sogou/detail",map);
    }

    /**
     * 词库详情
     * @param categoryId
     * @param map
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("/data")
    public ModelAndView data(@RequestParam("categoryId") Integer categoryId,
                               Map<String, Object> map,
                               HttpSession session) throws Exception {

        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setUsername(" ");
        }
        LexiconCategory category = categoryService.findOne(categoryId);
        LexiconDTO lexiconDTO = new LexiconDTO();
        lexiconDTO.setId(categoryId);
        lexiconDTO.setCategoryName(category.getCategoryName());
        List<LexiconInfo> lexiconInfoList = lexiconService.findByCategoryId(categoryId);
        lexiconDTO.setLexiconInfoList(lexiconInfoList);

        map.put("user",user);
        map.put("lexiconDTO",lexiconDTO);

        return new ModelAndView("sogou/data",map);
    }

    /**
     * 获取显示的LexiconDTOList数据
     * @param fileList
     * @param targetpath
     * @return
     * @throws Exception
     */
    private  List<LexiconDTO> getList(List<File> fileList , String targetpath) throws Exception {
        List<LexiconDTO> lexiconDTOList = new ArrayList<>();
        int i = 0;
        File dir = new File(targetpath);
        if (!dir.exists())
            dir.mkdirs();
        for (File file : fileList){
            File file1 = new File(targetpath + File.separator + FileFinderUtil.getFileName(file)+".txt");
            if(!file1.exists()) {
                SoGou sogou = new SoGou();
                // System.out.println(FileFinderUtil.getFileName(file));
                sogou.toTxt(file.getAbsolutePath(),
                        targetpath + File.separator + FileFinderUtil.getFileName(file) + ".txt",
                        false);
            }
            LexiconCategory category = new LexiconCategory();
            category.setCategoryName(FileFinderUtil.getFileName(file));
            category = categoryService.save(category);
            String[] f = new String[100000];
            f = FileToStringUtil.readToString(targetpath+ File.separator + FileFinderUtil.getFileName(file)+".txt");
            int j = 0;
            //category = categoryService.findByCategoryName(FileFinderUtil.getFileName(file));
            LexiconDTO lexiconDTO = new LexiconDTO();
            BeanUtils.copyProperties(category,lexiconDTO);
            List<LexiconInfo> lexiconInfoList = new ArrayList<>();
           // Integer n = lexiconService.count();
            while (f[j]!=null&&category!=null){
                LexiconInfo lexiconInfo = new LexiconInfo();
                lexiconInfo.setCategoryId(category.getId());
                lexiconInfo.setKeyword(f[j++]);

                if (lexiconInfo!=null)
                lexiconInfoList.add(lexiconInfo);
            }
            lexiconInfoList = lexiconService.saveAll(lexiconInfoList);
            if (lexiconInfoList.size()>count) {
                lexiconInfoList = lexiconInfoList.subList(0, count);
            }
            lexiconDTO.setLexiconInfoList(lexiconInfoList);
            lexiconDTOList.add(lexiconDTO);
            i++;
            if (i>=count)
                break;
        }
        return lexiconDTOList;
    }

}
