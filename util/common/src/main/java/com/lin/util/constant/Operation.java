package com.lin.util.constant;

import java.lang.annotation.*;

/**
 * 操作注解
 *
 * @author : fangkuaiIt / fangkuaiIt
 * @version : 1.0
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Operation {

    /**
     * 工单操作
     *
     * @author : fangkuaiIt / fangkuaiIt@seerbigdata.com
     * @version : 1.0
     */
    enum WorkOrderOperate{
        /**
         * NONE
         */
        NONE,
        /**
         * 新增.
         */
        ADD,
        /**
         * 换刀工单
         */
        ADD_TOOL_CHANGE,
        /**
         * 更新.
         */
        UPDATE,
        /**
         * 取消工单.
         */
        CANCEL,
        /**
         * 发布工单.
         */
        PUBLISH,
        /**
         * 指派主维修人.
         */
        ASSIGN,
        /**
         * 拒绝指派.
         */
        DENY,
        /**
         * 接受指派.
         */
        RECEIVE,
        /**
         * 抢单.
         */
        COMPETE,
        /**
         * 开始维修.
         */
        BEGIN_MAINTAIN,
        /**
         * 暂停维修.
         */
        PAUSE_MAINTAIN,
        /**
         * 继续维修.
         */
        CONTINUE_MAINTAIN,
        /**
         * 完成维修.
         */
        FINISH_MAINTAIN,
        /**
         * 交接工单.
         */
        HANDOVER,
        /**
         * 确认完成.
         */
        COMPLETE,
        /**
         * 确认未完成.
         */
        INCOMPLETE,
        /**
         * 退单.
         */
        QUIT
    }

    /**
     * 维修单操作
     *
     * @author : fangkuaiIt / fangkuaiIt@seerbigdata.com
     * @version : 1.0
     */
    enum MaintainOrderOperate{
        /**
         * NONE
         */
        NONE,
        /**
         * 新增换刀维修单.
         */
        TOOL_CHANGE
    }

    /**
     * 评论操作
     * @author : fangkuaiIt / fangkuaiIt@seerbigdata.com
     * @version : 1.0
     */
    enum CommentOperate{
        /**
         * NONE
         */
        NONE,
        /**
         * 文字评论.
         */
        TEXT,
        /**
         * 图片评论.
         */
        IMAGE,
        /**
         * 声音评论.
         */
        AUDIO,
        /**
         * 视频评论.
         */
        VIDEO
    }
}
