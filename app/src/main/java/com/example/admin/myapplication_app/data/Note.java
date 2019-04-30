package com.example.admin.myapplication_app.data;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Discription:
 *
 * @author guokun
 * @date 2019/4/30
 */


@Entity(indexes = {
        @Index(value = "entityId, chapterId", unique = true)
})
public class Note {


    @NotNull
    private Long entityId;  //实体id,书籍或节目
    @NotNull
    private Long chapterId;  //章节id

    @Generated(hash = 649971240)
    public Note(@NotNull Long entityId, @NotNull Long chapterId) {
        this.entityId = entityId;
        this.chapterId = chapterId;
    }

    @Generated(hash = 1272611929)
    public Note() {
    }

    public Long getEntityId() {
        return this.entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public Long getChapterId() {
        return this.chapterId;
    }

    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
    }


}
