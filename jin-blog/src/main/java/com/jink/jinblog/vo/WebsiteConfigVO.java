package com.jink.jinblog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 网站配置信息
 *
 * @author yezhiqiu
 * @date 2021/08/09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "网站配置")
public class WebsiteConfigVO {

    /**
     * 网站头像
     */
    @Schema(name = "websiteAvatar", defaultValue = "网站头像", required = true, type = "String")
    private String websiteAvatar;

    /**
     * 网站名称
     */
    @Schema(name = "websiteName", defaultValue = "网站名称", required = true, type = "String")
    private String websiteName;

    /**
     * 网站作者
     */
    @Schema(name = "websiteAuthor", defaultValue = "网站作者", required = true, type = "String")
    private String websiteAuthor;

    /**
     * 网站介绍
     */
    @Schema(name = "websiteIntro", defaultValue = "网站介绍", required = true, type = "String")
    private String websiteIntro;

    /**
     * 网站公告
     */
    @Schema(name = "websiteNotice", defaultValue = "网站公告", required = true, type = "String")
    private String websiteNotice;

    /**
     * 网站创建时间
     */
    @Schema(name = "websiteCreateTime", defaultValue = "网站创建时间", required = true, type = "LocalDateTime")
    private String websiteCreateTime;

    /**
     * 网站备案号
     */
    @Schema(name = "websiteRecordNo", defaultValue = "网站备案号", required = true, type = "String")
    private String websiteRecordNo;

    /**
     * 社交登录列表
     */
    @Schema(name = "socialLoginList", defaultValue = "社交登录列表", required = true, type = "List<String>")
    private List<String> socialLoginList;

    /**
     * 社交url列表
     */
    @Schema(name = "socialUrlList", defaultValue = "社交url列表", required = true, type = "List<String>")
    private List<String> socialUrlList;

    /**
     * qq
     */
    @Schema(name = "qq", defaultValue = "qq", required = true, type = "String")
    private String qq;

    /**
     * github
     */
    @Schema(name = "github", defaultValue = "github", required = true, type = "String")
    private String github;

    /**
     * gitee
     */
    @Schema(name = "gitee", defaultValue = "gitee", required = true, type = "String")
    private String gitee;

    /**
     * 游客头像
     */
    @Schema(name = "touristAvatar", defaultValue = "游客头像", required = true, type = "String")
    private String touristAvatar;

    /**
     * 用户头像
     */
    @Schema(name = "userAvatar", defaultValue = "用户头像", required = true, type = "String")
    private String userAvatar;

    /**
     * 是否评论审核
     */
    @Schema(name = "isCommentReview", defaultValue = "是否评论审核", required = true, type = "Integer")
    private Integer isCommentReview;

    /**
     * 是否留言审核
     */
    @Schema(name = "isMessageReview", defaultValue = "是否留言审核", required = true, type = "Integer")
    private Integer isMessageReview;

    /**
     * 是否邮箱通知
     */
    @Schema(name = "isEmailNotice", defaultValue = "是否邮箱通知", required = true, type = "Integer")
    private Integer isEmailNotice;

    /**
     * 是否打赏
     */
    @Schema(name = "isReward", defaultValue = "是否打赏", required = true, type = "Integer")
    private Integer isReward;

    /**
     * 微信二维码
     */
    @Schema(name = "weiXinQRCode", defaultValue = "微信二维码", required = true, type = "String")
    private String weiXinQRCode;

    /**
     * 支付宝二维码
     */
    @Schema(name = "alipayQRCode", defaultValue = "支付宝二维码", required = true, type = "String")
    private String alipayQRCode;

    /**
     * 文章封面
     */
    @Schema(name = "articleCover", defaultValue = "文章封面", required = true, type = "String")
    private String articleCover;

    /**
     * 是否开启聊天室
     */
    @Schema(name = "isReward", defaultValue = "是否打赏", required = true, type = "Integer")
    private Integer isChatRoom;

    /**
     * websocket地址
     */
    @Schema(name = "websocketUrl", defaultValue = "websocket地址", required = true, type = "String")
    private String websocketUrl;

    /**
     * 是否开启音乐
     */
    @Schema(name = "isMusicPlayer", defaultValue = "是否开启音乐", required = true, type = "Integer")
    private Integer isMusicPlayer;

}
