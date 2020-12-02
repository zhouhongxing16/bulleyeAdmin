package com.chris.bulleyeadmin.wechat.pojo;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "wx_template")
public class WxTemplate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    private String id;

    @Column(name = "source_id")
    private String sourceId;

    @Column(name = "templateId")
    private String templateId;

    @Column(name = "title")
    private String title;

    @Column(name = "primary_industry")
    private String primaryIndustry;

    @Column(name = "deputy_industry")
    private String deputyIndustry;

    @Column(name = "content")
    private String content;

    @Column(name = "example")
    private String example;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrimaryIndustry() {
        return primaryIndustry;
    }

    public void setPrimaryIndustry(String primaryIndustry) {
        this.primaryIndustry = primaryIndustry;
    }

    public String getDeputyIndustry() {
        return deputyIndustry;
    }

    public void setDeputyIndustry(String deputyIndustry) {
        this.deputyIndustry = deputyIndustry;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }
}
