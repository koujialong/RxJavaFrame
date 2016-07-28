package com.example.administrator.rxjavaframe.model;

import java.util.List;

/**
 * Created by Administrator on 2016/7/28.
 */
public class CityModel {

    /**
     * code : 0
     * msg : 操作成功
     * startIndex : 0
     * total : 0
     * pageSize : 30
     * curPage : 0
     * items : []
     * item : {"provinceList":[{"codeid":"340","codename":"安徽省"},{"codeid":"820","codename":"澳门特别行政区"},{"codeid":"110","codename":"北京市"},{"codeid":"350","codename":"福建省"},{"codeid":"620","codename":"甘肃省"},{"codeid":"440","codename":"广东省"},{"codeid":"450","codename":"广西壮族自治区"},{"codeid":"520","codename":"贵州省"},{"codeid":"460","codename":"海南省"},{"codeid":"130","codename":"河北省"},{"codeid":"410","codename":"河南省"},{"codeid":"230","codename":"黑龙江省"},{"codeid":"420","codename":"湖北省"},{"codeid":"430","codename":"湖南省"},{"codeid":"220","codename":"吉林省"},{"codeid":"320","codename":"江苏省"},{"codeid":"360","codename":"江西省"},{"codeid":"210","codename":"辽宁省"},{"codeid":"150","codename":"内蒙古自治区"},{"codeid":"640","codename":"宁夏回族自治区"},{"codeid":"630","codename":"青海省"},{"codeid":"370","codename":"山东省"},{"codeid":"140","codename":"山西省"},{"codeid":"610","codename":"陕西省"},{"codeid":"310","codename":"上海市"},{"codeid":"510","codename":"四川省"},{"codeid":"710","codename":"台湾省"},{"codeid":"120","codename":"天津市"},{"codeid":"540","codename":"西藏自治区"},{"codeid":"810","codename":"香港特别行政区"},{"codeid":"650","codename":"新疆维吾尔自治区"},{"codeid":"530","codename":"云南省"},{"codeid":"330","codename":"浙江省"},{"codeid":"500","codename":"重庆市"}],"hotProvince":[{"codeid":"110","codename":"北京市"},{"codeid":"310","codename":"上海市"},{"codeid":"120","codename":"天津市"},{"codeid":"440","codename":"广东省"}]}
     * totalPage : 0
     */

    private int code;
    private String msg;
    private int startIndex;
    private int total;
    private int pageSize;
    private int curPage;
    private ItemBean item;
    private int totalPage;
    private List<?> items;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public ItemBean getItem() {
        return item;
    }

    public void setItem(ItemBean item) {
        this.item = item;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<?> getItems() {
        return items;
    }

    public void setItems(List<?> items) {
        this.items = items;
    }

    public static class ItemBean {
        /**
         * codeid : 340
         * codename : 安徽省
         */

        private List<ProvinceListBean> provinceList;
        /**
         * codeid : 110
         * codename : 北京市
         */

        private List<HotProvinceBean> hotProvince;

        public List<ProvinceListBean> getProvinceList() {
            return provinceList;
        }

        public void setProvinceList(List<ProvinceListBean> provinceList) {
            this.provinceList = provinceList;
        }

        public List<HotProvinceBean> getHotProvince() {
            return hotProvince;
        }

        public void setHotProvince(List<HotProvinceBean> hotProvince) {
            this.hotProvince = hotProvince;
        }

        public static class ProvinceListBean {
            private String codeid;
            private String codename;

            public String getCodeid() {
                return codeid;
            }

            public void setCodeid(String codeid) {
                this.codeid = codeid;
            }

            public String getCodename() {
                return codename;
            }

            public void setCodename(String codename) {
                this.codename = codename;
            }
        }

        public static class HotProvinceBean {
            private String codeid;
            private String codename;

            public String getCodeid() {
                return codeid;
            }

            public void setCodeid(String codeid) {
                this.codeid = codeid;
            }

            public String getCodename() {
                return codename;
            }

            public void setCodename(String codename) {
                this.codename = codename;
            }
        }
    }
}
