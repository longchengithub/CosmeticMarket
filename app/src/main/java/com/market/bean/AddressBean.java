package com.market.bean;


import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/1/7.
 */

public class AddressBean {
    /**
     * addressList : [{"addressArea":"赤水市","addressDetail":"文华路文华学院","city":"遵义市","id":134,"isDefault":1,"name":"张瑞丽","phoneNumber":"189","province":"贵州省","zipCode":"563000"},{"addressArea":"密云县","addressDetail":"街道口地铁c口","city":"北京市","id":139,"isDefault":0,"name":"itcast","phoneNumber":"027-81970008","province":"北京市","zipCode":"100000"},{"addressArea":"洪山区","addressDetail":"街道口","city":"武汉","id":146,"isDefault":0,"name":"肖文","phoneNumber":"15801477821","province":"湖北","zipCode":"430070"},{"addressArea":"二七区","addressDetail":"fjghkk","city":"郑州市","id":147,"isDefault":0,"name":"truth tst","phoneNumber":"44554","province":"河南省","zipCode":"450000"},{"addressArea":"二七区","addressDetail":"BBB BBB","city":"郑州市","id":148,"isDefault":0,"name":"zzz","phoneNumber":"254649","province":"河南省","zipCode":"450000"},{"addressArea":"二七区","addressDetail":"Finn","city":"郑州市","id":149,"isDefault":0,"name":"thirty","phoneNumber":"56545","province":"河南省","zipCode":"450000"},{"addressArea":"二七区","addressDetail":"fog","city":"郑州市","id":150,"isDefault":0,"name":"see","phoneNumber":"123","province":"河南省","zipCode":"450000"},{"addressArea":"二七区","addressDetail":"dog","city":"郑州市","id":151,"isDefault":0,"name":"huan","phoneNumber":"1383838383","province":"河南省","zipCode":"450000"},{"addressArea":"枞阳县","addressDetail":"shsc","city":"安庆市","id":154,"isDefault":0,"name":"z","phoneNumber":"23565655","province":"安徽省","zipCode":"246000"},{"addressArea":"潮安县","addressDetail":"JHVH","city":"潮州市","id":156,"isDefault":0,"name":"ah","phoneNumber":"13232313","province":"广东省","zipCode":"515600"},{"addressArea":"积石山保安族东乡族撒拉族自治县","addressDetail":"dsada","city":"临夏回族自治州","id":158,"isDefault":0,"name":"123123","phoneNumber":"312321","province":"甘肃省","zipCode":"731100"},{"addressArea":"安乡县","addressDetail":"TT TT TT","city":"常德市","id":159,"isDefault":0,"name":"ghr","phoneNumber":"18874533538","province":"湖南省","zipCode":"415000"},{"addressArea":"宝安区","addressDetail":"rewqt","city":"深圳市","id":160,"isDefault":0,"name":"uuu","phoneNumber":"12343224456","province":"广东省","zipCode":"518000"},{"addressArea":"","addressDetail":"3123","city":"安庆市","id":162,"isDefault":0,"name":"daq","phoneNumber":"231132312","province":"安徽省","zipCode":""},{"addressArea":"大理市","addressDetail":"xxhg","city":"大理白族自治州","id":164,"isDefault":0,"name":"huan","phoneNumber":"66666666","province":"云南省","zipCode":"671000"},{"addressArea":"大安市","addressDetail":"DMC","city":"白城市","id":165,"isDefault":0,"name":"zzz","phoneNumber":"13333333666","province":"吉林省","zipCode":"137000"},{"addressArea":"浦城县","addressDetail":"dghh","city":"南平市","id":169,"isDefault":0,"name":"doubi","phoneNumber":"322432434234","province":"福建省","zipCode":"353000"},{"addressArea":"西秀区","addressDetail":"ertert","city":"安顺市","id":170,"isDefault":0,"name":"fgh","phoneNumber":"55555555","province":"贵州省","zipCode":"561000"},{"addressArea":"西秀区","addressDetail":"345345","city":"安顺市","id":171,"isDefault":0,"name":"sdf","phoneNumber":"3433423","province":"贵州省","zipCode":"561000"},{"addressArea":"建阳市","addressDetail":"sdfsdf","city":"南平市","id":172,"isDefault":0,"name":"dfcgsdfxdfvzxd","phoneNumber":"34324","province":"福建省","zipCode":"353000"},{"addressArea":"宝安区","addressDetail":"dfgdfg","city":"深圳市","id":173,"isDefault":0,"name":"sdfsdf","phoneNumber":"32425345345345","province":"广东省","zipCode":"518000"},{"addressArea":"洪山区","addressDetail":"街道口","city":"武汉","id":174,"isDefault":1,"name":"肖文","phoneNumber":"15801477821","province":"湖北","zipCode":"430070"},{"addressArea":"洪山区","addressDetail":"街道口","city":"武汉","id":175,"isDefault":1,"name":"肖文","phoneNumber":"15801477821","province":"湖北","zipCode":"430070"},{"addressArea":"洪山区","addressDetail":"街道口","city":"武汉","id":176,"isDefault":1,"name":"肖文","phoneNumber":"15801477821","province":"湖北","zipCode":"430070"},{"addressArea":"洪山区","addressDetail":"街道口","city":"武汉","id":177,"isDefault":1,"name":"肖文","phoneNumber":"15801477821","province":"湖北","zipCode":"430070"},{"addressArea":"洪山区","addressDetail":"街道口","city":"武汉","id":178,"isDefault":1,"name":"肖文","phoneNumber":"15801477821","province":"湖北","zipCode":"430070"}]
     * response : addressList
     */

    private String response;
    private List<AddressListBean> addressList;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<AddressListBean> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<AddressListBean> addressList) {
        this.addressList = addressList;
    }

    public static class AddressListBean implements Serializable{
        /**
         * addressArea : 赤水市
         * addressDetail : 文华路文华学院
         * city : 遵义市
         * id : 134
         * isDefault : 1
         * name : 张瑞丽
         * phoneNumber : 189
         * province : 贵州省
         * zipCode : 56000
         */
        private static final long serialVersionUID = 536871008;
        private String addressArea;
        private String addressDetail;
        private String city;
        private int id;
        private int isDefault;
        private String name;
        private String phoneNumber;
        private String province;
        private String zipCode;


        public AddressListBean(String addressArea, String addressDetail, String city, int id, int isDefault, String name, String phoneNumber, String province, String zipCode) {
            this.addressArea = addressArea;
            this.addressDetail = addressDetail;
            this.city = city;
            this.id = id;
            this.isDefault = isDefault;
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.province = province;
            this.zipCode = zipCode;
        }

        public String getAddressArea() {
            return addressArea;
        }

        public void setAddressArea(String addressArea) {
            this.addressArea = addressArea;
        }

        public String getAddressDetail() {
            return addressDetail;
        }

        public void setAddressDetail(String addressDetail) {
            this.addressDetail = addressDetail;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsDefault() {
            return isDefault;
        }

        public void setIsDefault(int isDefault) {
            this.isDefault = isDefault;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }

        @Override
        public String toString() {
            return "AddressListBean{" +
                    "addressArea='" + addressArea + '\'' +
                    ", addressDetail='" + addressDetail + '\'' +
                    ", city='" + city + '\'' +
                    ", id=" + id +
                    ", isDefault=" + isDefault +
                    ", name='" + name + '\'' +
                    ", phoneNumber='" + phoneNumber + '\'' +
                    ", province='" + province + '\'' +
                    ", zipCode='" + zipCode + '\'' +
                    '}';
        }
    }

    /**
     * addressList : [{"addressArea":"赤水市","addressDetail":"文华路文华学院","city":"遵义市","id":134,"isDefault":1,"name":"张瑞丽","phoneNumber":"189","province":"贵州省","zipCode":"563000"},{"addressArea":"密云县","addressDetail":"街道口地铁c口","city":"北京市","id":139,"isDefault":0,"name":"itcast","phoneNumber":"027-81970008","province":"北京市","zipCode":"100000"},{"addressArea":"洪山区","addressDetail":"街道口","city":"武汉","id":146,"isDefault":0,"name":"肖文","phoneNumber":"15801477821","province":"湖北","zipCode":"430070"},{"addressArea":"二七区","addressDetail":"fjghkk","city":"郑州市","id":147,"isDefault":0,"name":"truth tst","phoneNumber":"44554","province":"河南省","zipCode":"450000"},{"addressArea":"二七区","addressDetail":"BBB BBB","city":"郑州市","id":148,"isDefault":0,"name":"zzz","phoneNumber":"254649","province":"河南省","zipCode":"450000"},{"addressArea":"二七区","addressDetail":"Finn","city":"郑州市","id":149,"isDefault":0,"name":"thirty","phoneNumber":"56545","province":"河南省","zipCode":"450000"},{"addressArea":"二七区","addressDetail":"fog","city":"郑州市","id":150,"isDefault":0,"name":"see","phoneNumber":"123","province":"河南省","zipCode":"450000"},{"addressArea":"二七区","addressDetail":"dog","city":"郑州市","id":151,"isDefault":0,"name":"huan","phoneNumber":"1383838383","province":"河南省","zipCode":"450000"},{"addressArea":"枞阳县","addressDetail":"shsc","city":"安庆市","id":154,"isDefault":0,"name":"z","phoneNumber":"23565655","province":"安徽省","zipCode":"246000"},{"addressArea":"潮安县","addressDetail":"JHVH","city":"潮州市","id":156,"isDefault":0,"name":"ah","phoneNumber":"13232313","province":"广东省","zipCode":"515600"},{"addressArea":"积石山保安族东乡族撒拉族自治县","addressDetail":"dsada","city":"临夏回族自治州","id":158,"isDefault":0,"name":"123123","phoneNumber":"312321","province":"甘肃省","zipCode":"731100"},{"addressArea":"安乡县","addressDetail":"TT TT TT","city":"常德市","id":159,"isDefault":0,"name":"ghr","phoneNumber":"18874533538","province":"湖南省","zipCode":"415000"},{"addressArea":"宝安区","addressDetail":"rewqt","city":"深圳市","id":160,"isDefault":0,"name":"uuu","phoneNumber":"12343224456","province":"广东省","zipCode":"518000"},{"addressArea":"","addressDetail":"3123","city":"安庆市","id":162,"isDefault":0,"name":"daq","phoneNumber":"231132312","province":"安徽省","zipCode":""},{"addressArea":"大理市","addressDetail":"xxhg","city":"大理白族自治州","id":164,"isDefault":0,"name":"huan","phoneNumber":"66666666","province":"云南省","zipCode":"671000"},{"addressArea":"大安市","addressDetail":"DMC","city":"白城市","id":165,"isDefault":0,"name":"zzz","phoneNumber":"13333333666","province":"吉林省","zipCode":"137000"},{"addressArea":"浦城县","addressDetail":"dghh","city":"南平市","id":169,"isDefault":0,"name":"doubi","phoneNumber":"322432434234","province":"福建省","zipCode":"353000"},{"addressArea":"西秀区","addressDetail":"ertert","city":"安顺市","id":170,"isDefault":0,"name":"fgh","phoneNumber":"55555555","province":"贵州省","zipCode":"561000"},{"addressArea":"西秀区","addressDetail":"345345","city":"安顺市","id":171,"isDefault":0,"name":"sdf","phoneNumber":"3433423","province":"贵州省","zipCode":"561000"},{"addressArea":"建阳市","addressDetail":"sdfsdf","city":"南平市","id":172,"isDefault":0,"name":"dfcgsdfxdfvzxd","phoneNumber":"34324","province":"福建省","zipCode":"353000"},{"addressArea":"宝安区","addressDetail":"dfgdfg","city":"深圳市","id":173,"isDefault":0,"name":"sdfsdf","phoneNumber":"32425345345345","province":"广东省","zipCode":"518000"},{"addressArea":"洪山区","addressDetail":"街道口","city":"武汉","id":174,"isDefault":1,"name":"肖文","phoneNumber":"15801477821","province":"湖北","zipCode":"430070"},{"addressArea":"洪山区","addressDetail":"街道口","city":"武汉","id":175,"isDefault":1,"name":"肖文","phoneNumber":"15801477821","province":"湖北","zipCode":"430070"},{"addressArea":"洪山区","addressDetail":"街道口","city":"武汉","id":176,"isDefault":1,"name":"肖文","phoneNumber":"15801477821","province":"湖北","zipCode":"430070"},{"addressArea":"洪山区","addressDetail":"街道口","city":"武汉","id":177,"isDefault":1,"name":"肖文","phoneNumber":"15801477821","province":"湖北","zipCode":"430070"},{"addressArea":"洪山区","addressDetail":"街道口","city":"武汉","id":178,"isDefault":1,"name":"肖文","phoneNumber":"15801477821","province":"湖北","zipCode":"430070"}]
     * response : addressList
     */
    // private static final long serialVersionUID = 536871008;
    @Override
    public String toString() {
        return "AddressBean{" +
                "response='" + response + '\'' +
                ", addressList=" + addressList +
                '}';
    }
}

