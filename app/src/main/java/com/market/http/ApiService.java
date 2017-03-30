package com.market.http;

import com.market.bean.AddressBean;
import com.market.bean.BannerResp;
import com.market.bean.CategoryResp;
import com.market.bean.Hotproduct;
import com.market.bean.LoginResp;
import com.market.bean.MianMoResp;
import com.market.bean.OrderCancelResponseResp;
import com.market.bean.OrderDetailResp;
import com.market.bean.OrderListResp;
import com.market.bean.OrderSumbitResp;
import com.market.bean.OrdercheckoutResp;
import com.market.bean.ProductListResp;
import com.market.bean.ProductResp;
import com.market.bean.Qiangou;
import com.market.bean.RegisterResp;
import com.market.bean.SearchRecommend;
import com.market.bean.SearchResult;
import com.market.bean.Tijian;
import com.market.bean.Topic;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by chenlong on 2017/1/5.
 */

public interface ApiService
{
    /**
     * 默认的api主接口
     */
    String BASE_URL = "http://192.168.31.102:8080/market/";
    String IMAGE_URL = "http://192.168.31.102:8080/market";
//    String IMAGE_URL = "http://10.0.2.2:8080/market";
//    String BASE_URL = "http://10.0.2.2:8080/market/";

    @GET("home")
    Observable<BannerResp> getBannerApi();

    @GET("login")
    Observable<LoginResp> getLoginApi(@Query("username") String username, @Query("password") String password);

    @POST("register")
    Observable<RegisterResp> postRegisterApi(@Query("username") String username, @Query("password") String password);

    @GET("category")
    Observable<CategoryResp> getCategoryRespApi();

    @GET("mianmo.json")
    Observable<MianMoResp> getProductListApi();

    @GET("productlist")
        //page=1&&pageNum=10&&cId=125&&orderby="saleDown"
    Observable<ProductListResp> getProductList(@Query("page") int page
            , @Query("pageNum") int pageNum
            , @Query("cId") int cid
            , @Query("orderBy") String orderBy);

    @GET("product")
    Observable<ProductResp> getProductDetailsApi(@Query("pId") int pid);

    @GET("addresslist")
    Observable<AddressBean> getAddressListApi(@Header("userid") int userid);

    @GET("search/recommend")
        //热搜
    Observable<SearchRecommend> getSearchRecommendApi();

    @GET("search")
        //商品详情
    Observable<SearchResult> getSearchResultApi(@Query("keyword") String keyword, @Query("page") int page, @Query("pageNum") int pageNum, @Query("orderby") String orderby);

    /**
     * 黄涛主页的请求
     *
     * @return
     */
    @GET("recommendpics")
    Observable<Hotproduct> getHotproduct();

    @GET("brand")
    Observable<Tijian> getTijian();

    @GET("topic")
    Observable<Topic> getTopics(@Query("page") int page, @Query("pageNum") int pageNum);

    @GET("limitbuy")
    Observable<Qiangou> getQianggouimges(@Query("page") int page, @Query("pageNum") int pageNum);

    @GET("orderlist")
    //请求获取订单列表
    Observable<OrderListResp> getOrderListApi(@Query("type") int type, @Query("page") int page, @Query("pageNum") int pageNum,@Header("userid") int userid);

    @GET("orderdetail")
        //通过订单编号请求获取订单详情
    Observable<OrderDetailResp> getOrderDetailRespApi(@Query("orderId") String orderId,@Header("userid") int userid);

    @FormUrlEncoded
    @POST("ordercancel")
        //请求取消订单
    Observable<OrderCancelResponseResp> getOrncelResponseApi(@Field("orderId") String orderId,@Header("userid") int userid);

    @FormUrlEncoded
    @POST("checkout")
    Observable<OrdercheckoutResp> getOrdercheckoutRespApi(@Field("sku") String sku,@Header("userid") int userid);

    @FormUrlEncoded
    @POST("ordersumbit")
    Observable<OrderSumbitResp> getOrderSumbitRespApi(@Field("sku") String sku,
                                                      @Field("addressId") int addressId,
                                                      @Field("paymentType") int paymentType,
                                                      @Field("deliveryType") int deliveryType,
                                                      @Field("invoiceType") int invoiceType,
                                                      @Field("invoiceTitle") String invoiceTitle,
                                                      @Field("invoiceContent") String invoiceContent,
                                                      @Header("userid") int userid);
    //**************************************

    @GET("addressdelete")
    Observable<AddressBean> getAddressDeleteApi(@Query("id") int id,@Header("userid") int userid);

    @GET("addressdefault")
    Observable<AddressBean> getAddressDefaultApi(@Query("id") int id,@Header("userid") int userid);

    @FormUrlEncoded
    @POST("addresssave")
    Observable<AddressBean> addAddress(@Field("id") int id,@Field("name") String name,@Field("phoneNumber") String phoneNumber,
                                       @Field("province") String province,@Field("city") String city,@Field("addressArea") String addressArea,
                                       @Field("addressDetail") String addressDetail,@Field("zipCode") String zipCode,@Field("isDefault") int isDefault,@Header("userid") int userid);
}
