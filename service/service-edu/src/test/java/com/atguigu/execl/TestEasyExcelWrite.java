package com.atguigu.execl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.read.listener.ReadListener;
import com.atguigu.eduservice.entity.dto.EduSubjectDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestEasyExcelWrite {

    public static List<StudentDemo> data(){
        ArrayList<StudentDemo> studentDemos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            studentDemos.add(new StudentDemo(i,"name"+i));
        }
        return studentDemos;
    }

    public static void main(String[] args) {
        String path = "C:\\Users\\root\\Desktop\\springcloud\\student.xlsx";
        path = "C:\\Users\\root\\Desktop\\test.xlsx";

//        EasyExcel.write(path,StudentDemo.class).sheet("学生列表").doWrite(TestEasyExcelWrite.data());

        EasyExcel.read(path, EduSubjectDTO.class, new ReadListener<EduSubjectDTO>() {
            @Override
            public void onException(Exception exception, AnalysisContext context) throws Exception {
                throw exception;
            }

            @Override
            public void invokeHead(Map<Integer, CellData> headMap, AnalysisContext context) {
                System.out.println(headMap);
            }

            @Override
            public void invoke(EduSubjectDTO data, AnalysisContext context) {
                System.out.println(data);
            }


            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                System.out.println("所有内容读取完毕");
            }

            @Override
            public boolean hasNext(AnalysisContext context) {
                return true;
            }
        }).sheet().doRead();
    }
}
