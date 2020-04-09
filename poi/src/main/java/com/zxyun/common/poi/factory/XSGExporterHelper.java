package com.zxyun.common.poi.factory;


import com.zxyun.common.poi.model.XSGCell;
import com.zxyun.common.poi.model.XSGSheet;
import com.zxyun.common.poi.model.XSGWorkBook;
import com.zxyun.common.poi.util.ExcelUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @des: Excel导出
 * @Author: given
 * @Date 2019/9/6 18:33
 */
public class XSGExporterHelper {

    private XSGWorkBook xsgWorkBook;

    private XSGExporterHelper(){
    }

    private XSGExporterHelper(XSGWorkBook xsgWorkBook) {
        this.xsgWorkBook = xsgWorkBook;
    }

    public void export (OutputStream out){
        try {
            ExcelUtil.export(out, xsgWorkBook);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //释放内存
            Builder.bookThreadLocal.remove();
        }
    }

    /**
     * 自定义实现
     * @param consumer
     */
    public void export (Consumer<XSGWorkBook> consumer){
        try {
            consumer.accept(xsgWorkBook);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放内存
            Builder.bookThreadLocal.remove();
        }
    }

    public interface XsgWorkBookConfig {
        XSGExporterHelper build();
    }

    public interface XsgSheetConfig {

        <T> XsgCellConfig<T> and(List<? super T> data);

        XsgWorkBookConfig fileName(String fileName);
    }

    public interface XsgCellConfig<T> {
       XsgCellConfig<T> append(String headerName, Function<? super T,String> bodyFunction);

       XsgCellConfig<T> append(String headerName, Function<? super T,String> bodyFunction, String align);

       XsgSheetConfig sheetTitle(String sheetName);
    }

    public static class Builder implements XsgWorkBookConfig, XsgSheetConfig {

        //多线程问题
        private static ThreadLocal<XSGWorkBook> bookThreadLocal = new ThreadLocal<>();

        public Builder(XSGSheet xsgSheet) {
            XSGWorkBook xsgWorkBook = bookThreadLocal.get();
            if (xsgWorkBook == null) {
                xsgWorkBook = new XSGWorkBook();
                bookThreadLocal.set(xsgWorkBook);
            }
            xsgWorkBook.addXsgSheet(xsgSheet);
        }

        public static <T> XsgCellConfig<? extends T> from (List<? super T> data) {
            return XsgCellConfigImpl.from(data);
        }


        @Override
        public <T> XsgCellConfig<T> and(List<? super T> data) {
            return XsgCellConfigImpl.from(data);
        }

        @Override
        public XsgWorkBookConfig fileName(String bookName) {
            bookThreadLocal.get().setFileName(bookName);
            return this;
        }

        @Override
        public XSGExporterHelper build () {
            return new XSGExporterHelper(bookThreadLocal.get());
        }
    }

    public static class XsgCellConfigImpl<T> implements XsgCellConfig<T> {

        private XSGSheet<T> xsgSheet;

        private List<XSGCell<? super T>> xsgCells;

        List<? super T> data;

        public XsgCellConfigImpl(List<? super T> data) {
            this.data = data;
            this.xsgCells = new ArrayList<>();
        }

        public static <T> XsgCellConfigImpl<T> from (List<? super T> data) {
            return new XsgCellConfigImpl<>(data);
        }

        @Override
        public XsgCellConfig<T> append(String headerName, Function<? super T,String> bodyFunction) {
            XSGCell<T> xsgCell = new XSGCell<T>(headerName, bodyFunction);
            xsgCells.add(xsgCell);
            return this;
        }

        @Override
        public XsgCellConfig<T> append(String headerName, Function<? super T,String> bodyFunction, String align) {
            XSGCell<T> xsgCell = new XSGCell<T>(headerName, bodyFunction, align);
            xsgCells.add(xsgCell);
            return this;
        }

        @Override
        public XsgSheetConfig sheetTitle(String sheetName) {
            xsgSheet = new XSGSheet();
            xsgSheet.setData(data);
            xsgSheet.setXsgCells(xsgCells);
            xsgSheet.setTitle(sheetName);
            return new Builder(xsgSheet);
        }
    }
}
