package cn.nean.forum.model.es;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(indexName = "blog")
public class BlogIndexDo {

    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 商户id
     */
    @Field(type = FieldType.Long)
    private Long shopId;

    /**
     * 用户id
     */
    @Field(type = FieldType.Long)
    private Long userId;

    /**
     * 标题
     */
    @Field(type = FieldType.Text)
    private String title;

    /**
     * 探店的照片，最多9张，多张以","隔开
     */
    @Field(type = FieldType.Text,index = false)
    private String images;

    /**
     * 探店的文字描述
     */
    @Field(type = FieldType.Text)
    private String content;

    /**
     * 点赞数量
     */
    @Field(type = FieldType.Integer)
    private Integer liked;

    /**
     * 评论数量
     */
    @Field(type = FieldType.Integer)
    private Integer comments;


    /**
     * 创建时间
     */
    @Field(type = FieldType.Date)
    private Date createTime;

    /**
     * 更新时间
     */
    @Field(type = FieldType.Date)
    private Date updateTime;
}
