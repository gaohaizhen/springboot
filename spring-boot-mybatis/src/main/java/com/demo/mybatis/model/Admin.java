package com.demo.mybatis.model;

import java.util.Date;

public class Admin {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column admin.adminId
     *
     * @mbg.generated Tue Sep 12 10:04:54 CST 2017
     */
    private Long adminid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column admin.name
     *
     * @mbg.generated Tue Sep 12 10:04:54 CST 2017
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column admin.password
     *
     * @mbg.generated Tue Sep 12 10:04:54 CST 2017
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column admin.createTime
     *
     * @mbg.generated Tue Sep 12 10:04:54 CST 2017
     */
    private Date createtime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column admin.adminId
     *
     * @return the value of admin.adminId
     *
     * @mbg.generated Tue Sep 12 10:04:54 CST 2017
     */
    public Long getAdminid() {
        return adminid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column admin.adminId
     *
     * @param adminid the value for admin.adminId
     *
     * @mbg.generated Tue Sep 12 10:04:54 CST 2017
     */
    public void setAdminid(Long adminid) {
        this.adminid = adminid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column admin.name
     *
     * @return the value of admin.name
     *
     * @mbg.generated Tue Sep 12 10:04:54 CST 2017
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column admin.name
     *
     * @param name the value for admin.name
     *
     * @mbg.generated Tue Sep 12 10:04:54 CST 2017
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column admin.password
     *
     * @return the value of admin.password
     *
     * @mbg.generated Tue Sep 12 10:04:54 CST 2017
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column admin.password
     *
     * @param password the value for admin.password
     *
     * @mbg.generated Tue Sep 12 10:04:54 CST 2017
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column admin.createTime
     *
     * @return the value of admin.createTime
     *
     * @mbg.generated Tue Sep 12 10:04:54 CST 2017
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column admin.createTime
     *
     * @param createtime the value for admin.createTime
     *
     * @mbg.generated Tue Sep 12 10:04:54 CST 2017
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}