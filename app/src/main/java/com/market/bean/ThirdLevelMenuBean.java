package com.market.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chenlong on 2017/1/12.
 */

public class ThirdLevelMenuBean implements Serializable
{

    public ThirdLevelMenuBean(List<SecondLevelMenuBean> secondBean)
    {
        this.secondBean = secondBean;
    }

    public ThirdLevelMenuBean()
    {
    }

    private List<SecondLevelMenuBean> secondBean;

    public List<SecondLevelMenuBean> getSecondBean()
    {
        return secondBean;
    }

    public void setSecondBean(List<SecondLevelMenuBean> secondBean)
    {
        this.secondBean = secondBean;
    }

    public static class SecondLevelMenuBean implements  Serializable{
        private String secondLevelName;

        private List<Integer> thirdId;

        public SecondLevelMenuBean()
        {
        }

        public SecondLevelMenuBean(String secondLevelName, List<Integer> thirdId)
        {
            this.secondLevelName = secondLevelName;
            this.thirdId = thirdId;
        }

        public String getSecondLevelName()
        {
            return secondLevelName;
        }

        public void setSecondLevelName(String secondLevelName)
        {
            this.secondLevelName = secondLevelName;
        }

        public List<Integer> getThirdId()
        {
            return thirdId;
        }

        public void setThirdId(List<Integer> thirdId)
        {
            this.thirdId = thirdId;
        }

        @Override
        public String toString()
        {
            return "ThirdLevelMenuBean{" +
                    "secondLevelName='" + secondLevelName + '\'' +
                    ", thirdId=" + thirdId +
                    '}';
        }
    }

    @Override
    public String toString()
    {
        return "ThirdLevelMenuBean{" +
                "secondBean=" + secondBean +
                '}';
    }
}
