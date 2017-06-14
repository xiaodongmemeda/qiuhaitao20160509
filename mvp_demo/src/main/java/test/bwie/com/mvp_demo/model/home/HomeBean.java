package test.bwie.com.mvp_demo.model.home;

import java.util.List;

/**
 * Created by ${仇海涛} on 2017/5/9.
 * 类的用途 ：
 */

public class HomeBean {

    /**
     * status : 1
     * info : 获取内容成功
     * data : [{"news_id":"13518","news_title":"2016中国（成都）物联网展览与会议落户成都","news_summary":"亚洲知名展览公司新展展览公司与四川德纳展览有限公司签订了合作备忘录，并正式对外发布将于明年11月共同举办2016中国(成","pic_url":"http://f.expoon.com/sub/news/2015/12/02/458475_230x162_0.jpg"},{"news_id":"13517","news_title":"国际科幻娱乐互动博览会暨幻博会启动仪式","news_summary":"2015年11月27日，国际科幻娱乐互动博览会新闻发布会暨幻博会启动仪式在中环世贸D座时代美术馆如期举办。","pic_url":"http://f.expoon.com/sub/news/2015/12/02/557562_230x162_0.jpg"},{"news_id":"13516","news_title":"湖南餐饮产业博览会3日开幕","news_summary":"今日从第二届湖南餐饮产业博览会组委会获悉，餐博会将于12月3日在长沙红星国际会展中心开幕，届时将举行牙齿咬刀在气球上切黄","pic_url":"http://f.expoon.com/sub/news/2015/12/02/491685_230x162_0.jpg"},{"news_id":"13515","news_title":"2016年亚洲泳池SPA博览会 将在2016年广州保利世贸展览馆举办","news_summary":"据编者从2016亚洲泳池SPA博览会新闻发布会上获悉，亚洲泳池SPA博览会将于2016年5月13日至15日在广州·保利世","pic_url":"http://f.expoon.com/sub/news/2015/12/02/906496_230x162_0.jpg"},{"news_id":"13514","news_title":"2015中国国际互联网+金融博览会在京召开","news_summary":"为积极配合国家相关重要政策的落实，促进我国互联网金融的健康发展，经中国国际贸易促进委员会批准，首届中国(北京)国际互联网","pic_url":"http://f.expoon.com/sub/news/2015/12/02/548994_230x162_0.jpg"},{"news_id":"13513","news_title":"第34届中国上海房地产展交会 开启互联网+模式","news_summary":"由上海市房地产行业协会主办的2015第三十四届中国·上海房地产展示交易会，将于12月4日在上海展览中心开幕，展期为4天。","pic_url":"http://f.expoon.com/sub/news/2015/12/02/590623_230x162_0.jpg"},{"news_id":"13512","news_title":"第74届全国药品交易会将于12月举办 打造覆盖医药全行业盛会","news_summary":"据从国药励展举办的新闻发布会获悉，由国药励展主办的第74届全国药品交易会暨中国健康营养博览会、第15届中国国际保健博览会","pic_url":"http://f.expoon.com/sub/news/2015/12/02/401371_230x162_0.jpg"},{"news_id":"13511","news_title":"敦煌：国际文化博览会开始筹备 将搭建丝路文化交流平台","news_summary":"首届丝绸之路(敦煌)国际文化博览会筹备工作于12月正式启动。作为\u201c一带一路\u201d建设的重大平台，甘肃将为丝绸之路沿线国家及地","pic_url":"http://f.expoon.com/sub/news/2015/12/02/513410_230x162_0.jpg"},{"news_id":"13510","news_title":"节能环保行业客户翘首期盼，2016北京节能减排展前景可期","news_summary":"以低碳、节能、环保为主题的\u201c2016第八届中国(北京)国际节能减排展览会\u201d，将于2016年4月6至8日在北京的国家会议中","pic_url":"http://f.expoon.com/sub/news/2015/12/01/745997_230x162_0.jpg"},{"news_id":"13509","news_title":"\u201c一炉多膛\u201d\u201c拔少\u201d结构 龙标新型锅炉 亮相2016北京锅炉展","news_summary":"由中国低碳产业协会环保锅炉委员会发起;北京市商委特批的2016第六届北京锅炉展将于2016年4月6日到8日集聚国家会议中","pic_url":"http://f.expoon.com/sub/news/2015/12/01/253698_230x162_0.jpg"},{"news_id":"13507","news_title":"成都深港澳嘉年华会 于明年6月举办","news_summary":"2016年6月17-20日，成都深港澳嘉年华会将在成都世纪城国际会展中心举办。这是励展华博展览有限公司继在成都打造礼品家","pic_url":"http://f.expoon.com/sub/news/2015/12/01/101928_230x162_0.jpg"},{"news_id":"13505","news_title":"成都将建购物天堂，礼品展出新招力挺","news_summary":"即将在2016年6月17日迎来八周岁的\u201c中国(成都)礼品及家居用品展览会\u201d，将以\u201c国际购物天堂\u201d为名片的城市，邀请业界大","pic_url":"http://f.expoon.com/sub/news/2015/12/01/751015_230x162_0.jpg"},{"news_id":"13504","news_title":"2015intertex博览会暨深圳原创设计时装周将盛大开幕","news_summary":"2015intertex博览会将会是一个更加优化的生态系统，更加着力于服装产业生态的全方位展现，并变身为一位产业\u201c大匠\u201d","pic_url":"http://f.expoon.com/sub/news/2015/11/30/196388_230x162_0.jpg"},{"news_id":"13503","news_title":"2015沈阳手机博览会 将有超过100家企业参展","news_summary":"近日，小编从沈阳市召开的新闻发布会上了解到，2015中国(沈阳)智慧产业展暨第六届中国(沈阳)国际手机博览会，将于12月","pic_url":"http://f.expoon.com/sub/news/2015/11/30/254271_230x162_0.jpg"},{"news_id":"13502","news_title":"第八届西安食博会 招商工作现已全面展开","news_summary":"第八届中国西安国际食品博览会暨丝绸之路特色食品展将于2016年4月15-18日在西安曲江国际会展中心举行。","pic_url":"http://f.expoon.com/sub/news/2015/11/30/646982_230x162_0.jpg"},{"news_id":"13501","news_title":"湖南艺术博览会 将于2015年底举行","news_summary":"近日，\u201c2015中国·湖南(国际)艺术博览会\u201d新闻发布会在长沙举行。这是湖南首个大型国际艺术博览会，将于12月18日\u20142","pic_url":"http://f.expoon.com/sub/news/2015/11/30/738813_230x162_0.jpg"},{"news_id":"13500","news_title":"丝绸之路(敦煌)国际文化博览会筹备工作启动","news_summary":"11月29日，丝绸之路(敦煌)国际文化博览会组织委员会第一次会议在人民大会堂召开。会议审议通过组委会组成机构及人员名单、","pic_url":"http://f.expoon.com/sub/news/2015/11/30/589384_230x162_0.jpg"},{"news_id":"13499","news_title":"广马博览会12月3日至5日举行 将由奥运冠军领跑","news_summary":"广马博览会将于12月3日至5日举行,爱心跑手到时可去天河体育中心领装备。一年一度的广州马拉松赛将于12月6日开跑。昨日上","pic_url":"http://f.expoon.com/sub/news/2015/11/30/874683_230x162_0.jpg"},{"news_id":"13498","news_title":"北京糖果展人气高涨 展会预登记首次突破3.5万人","news_summary":"北京糖果展自从在线预登记系统开通后，卓有成效，据最新统计，展会预登记已突破3.5万人，目前，每天新增报名人数仍维持在60","pic_url":"http://f.expoon.com/sub/news/2015/11/30/277134_230x162_0.jpg"},{"news_id":"13497","news_title":"东盟精品文化博览会即将开幕 约你一起感知\u201c梦幻的马来西亚\u201d","news_summary":"12月4至8日，\u201c2015中国昆明·东盟精品文化博览会\u201d即将在昆明国际会展中心拉开帷幕。马来西亚作为展会的主题国，将由马","pic_url":"http://f.expoon.com/sub/news/2015/11/30/748949_230x162_0.jpg"}]
     */

    private int status;
    private String info;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * news_id : 13518
         * news_title : 2016中国（成都）物联网展览与会议落户成都
         * news_summary : 亚洲知名展览公司新展展览公司与四川德纳展览有限公司签订了合作备忘录，并正式对外发布将于明年11月共同举办2016中国(成
         * pic_url : http://f.expoon.com/sub/news/2015/12/02/458475_230x162_0.jpg
         */

        private String news_id;
        private String news_title;
        private String news_summary;
        private String pic_url;

        public String getNews_id() {
            return news_id;
        }

        public void setNews_id(String news_id) {
            this.news_id = news_id;
        }

        public String getNews_title() {
            return news_title;
        }

        public void setNews_title(String news_title) {
            this.news_title = news_title;
        }

        public String getNews_summary() {
            return news_summary;
        }

        public void setNews_summary(String news_summary) {
            this.news_summary = news_summary;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }
    }
}
