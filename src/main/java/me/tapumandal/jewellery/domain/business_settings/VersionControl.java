package me.tapumandal.jewellery.domain.business_settings;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by TapuMandal on 1/8/2021.
 * For any query ask online.tapu@gmail.com
 */
@Entity
@Table(name = "version_control")
@Component
public class VersionControl implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;

    @Column(name = "force_update")
    protected boolean force;

    @Column(name = "app_version")
    protected int appVersion;

    @Column(name = "forceable_version")
    protected int forceableVersion;

    @Column(name = "message")
    protected String message;

    @Column(name = "title")
    protected String title;

    @Column(name = "display_version")
    protected String displayVersion;

    @Column(name = "change_log")
    protected String changeLog;

    @Column(name = "created_at")
    @CreationTimestamp
    protected Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    protected Date updatedAt;

//    @OneToOne(mappedBy="versionControl")
//    @PrimaryKeyJoinColumn
//    public BusinessSettings businessSettings;

    public VersionControl() {
    }

    public VersionControl(VersionControlDto versionControlDto) {
        this.force = versionControlDto.isForce();
        this.appVersion = versionControlDto.getAppVersion();
        this.forceableVersion = versionControlDto.getForceableVersion();
        this.message = versionControlDto.getMessage();
        this.title = versionControlDto.getTitle();
        this.displayVersion = versionControlDto.getDisplayVersion();
        this.changeLog = versionControlDto.getChangeLog();
    }

    public boolean isForce() {
        return force;
    }

    public void setForce(boolean force) {
        this.force = force;
    }

    public int getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(int appVersion) {
        this.appVersion = appVersion;
    }

    public int getForceableVersion() {
        return forceableVersion;
    }

    public void setForceableVersion(int forceableVersion) {
        this.forceableVersion = forceableVersion;
    }

    public String getMessage() {
        return message == null ? "" : message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDisplayVersion() {
        return displayVersion == null ? "" : displayVersion;
    }

    public void setDisplayVersion(String displayVersion) {
        this.displayVersion = displayVersion;
    }

    public String getChangeLog() {
        return changeLog == null ? "" : changeLog;
    }

    public void setChangeLog(String changeLog) {
        this.changeLog = changeLog;
    }
}
