package com.zxyun.common.util.pipeline;

/**
 * @des: 管道处理器节点
 * @Author: given
 * @Date 2020/2/28 16:44
 */
public interface PipelineHandler<I,O> {
    O process(I input);
}
