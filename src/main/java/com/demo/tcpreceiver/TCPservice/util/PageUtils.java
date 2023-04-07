package com.demo.tcpreceiver.TCPservice.util;//package com.demo.tcpreceiver.core.util;
//import com.github.pagehelper.PageInfo;
//import com.demo.tcpreceiver.dto.PageResultDTO;
//import com.demo.tcpreceiver.request.PageRequest;
//
//public class PageUtils {
//
//    /**
//     * 将分页信息封装到统一的接口
//     * @param pageRequest
//     * @param pageInfo
//     * @return
//     */
//    public static PageResultDTO getPageResult(PageRequest pageRequest, PageInfo<?> pageInfo) {
//        PageResultDTO pageResult = new PageResultDTO();
//        pageResult.setPageNum(pageInfo.getPageNum());
//        pageResult.setPageSize(pageInfo.getPageSize());
//        pageResult.setTotalSize(pageInfo.getTotal());
//        pageResult.setTotalPages(pageInfo.getPages());
//        pageResult.setContent(pageInfo.getList());
//        return pageResult;
//    }
//}