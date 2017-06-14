package qiuhaitao.bwie.com.loginregist_demo;

/**
 * Created by ASUS on 2017/6/9.
 */

public class RegisterBean {

    /**
     * code : 200
     * datas : {"username":"a111111","userid":"5","key":"bff9e30869f346899dc36dcc1d2145bd"}
     */

    private int code;
    private DatasBean datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * username : a111111
         * userid : 5
         * key : bff9e30869f346899dc36dcc1d2145bd
         */

        private String username;
        private String userid;
        private String key;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
}
