package com.he.ssm.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author itaem
 * date:2021-03-09 2021/3/9:0:26
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Menu {

    /**
     * path : /user
     * name : user
     * meta : {"title":"用户管理"}
     */

    private String  path;
    private String  name;
    private MetaDTO meta;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class MetaDTO {
        /**
         * title : 用户管理
         */

        private String title;
    }
}
