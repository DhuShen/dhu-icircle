package com.dhu.service.impl;

import com.dhu.dao.CircleDao;
import com.dhu.dao.MessageDao;
import com.dhu.dao.RequestDao;
import com.dhu.domain.Circle;
import com.dhu.domain.Message;
import com.dhu.domain.Request;
import com.dhu.domain.User;
import com.dhu.domain.view.CircleView;
import com.dhu.service.CircleService;
import com.dhu.tools.MyTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CircleServiceImpl implements CircleService {
    @Autowired
    CircleDao circleDao;
    MessageDao messageDao;
    RequestDao requestDao;

    @Override
    public String getCircleNameById(int circleId) {
        return circleDao.getCircleNameById(circleId);
    }

    //根据circleId查圈子
    @Override
    public Circle selectById(int circleId) {
        return circleDao.selectByCircleId(circleId);
    }

    //判断是不是圈主
    @Override
    public boolean isCircleMaster(String userId, int circleId) {
        Circle circle = circleDao.selectByCircleId(circleId);
        return Objects.equals(userId, circle.getCircle_UserId());
    }

    @Override
    public User GetCircleMaster(String userId, int circleId) {
        return circleDao.GetCircleMaster(userId, circleId);
    }

    //判断是否在圈子中(圈主也属于圈内成员）
    @Override
    public boolean isInCircle(String userId, int circleId) {
        return circleDao.selectConnection(userId, circleId) != null;
    }

    //    c)	查找圈子、贴子【在Circle,Post中】--查询圈子，模糊查询
    public List<Circle> searchCircle(String circleName) {
        return circleDao.selectByName(circleName);
    }

    //    d)	浏览圈子、帖子、评论【在Circle,Post,Discuss中】--获取热度圈子
    @Override
    public List<CircleView> getHotCircle() {
        return circleDao.selectTen();
    }

    //   i3)查看自己管理的圈子【在circle中】
    @Override
    public List<Circle> getMyCircle(String userId) {
        return circleDao.selectById(userId);
    }

    //   i4)查看自己加入的圈子【在circle中】
    @Override
    public List<Circle> getInCircle(String userId) {
        return circleDao.selectConnectionById(userId);
    }

    //    j)	加入圈子【在circle中】--事先不在圈子里
    @Override
    public boolean enterCircle(String userId, int circleId) {
        if (!isInCircle(userId, circleId)) {
            return circleDao.insertConnection(userId, circleId) > 0;
        } else
            return false;
    }

    //    n)	在圈子中查看圈内成员信息【在circle中】
    @Override
    public List<User> selectUserInCircle(int circleId) {
        return circleDao.selectUserInCircle(circleId);
    }

    //    u)	踢掉圈内成员【在circle中】--在圈子内（退出圈子）, 圈主用（踢人）
    @Override
    public boolean quitCircle(String userId, int circleId) {
        return circleDao.deleteConnection(userId, circleId) > 0;
    }

    //    o)	解散圈子【在circle中】--AOP
    @Override
    public boolean dissolveCircle(String userId, int circleId) {
        Circle circle = circleDao.selectByCircleId(circleId);
        if (Objects.equals(circle.getCircle_UserId(), userId)) {
            return (circleDao.deleteCircle(circleId) > 0 && circleDao.deleteCircleConnection(circleId) > 0);
        } else
            return false;
    }

    //    k)	建立圈子（需要向管理员申请）【在request、circle中】--管理员用
    @Override
    public boolean setUpCircle(String circleName, String circleContent, String userId) {
        //先处理内容中的换行和空格
        circleContent = circleContent.replace("&nbsp", " ");
        circleContent = circleContent.replace("<br>", "\n");
        Circle circle = new Circle();
        circle.setCircleName(circleName);
        circle.setCircleContent(circleContent);
        circle.setCircleLife(0);//判断圈子是否被封
        circle.setCircleTime(MyTime.getNowTime());
        circle.setCircle_UserId(userId);
        return circleDao.insertCircle(circle) > 0;
    }

    //批准建立圈子--管理员用
    public boolean allowSetUpCircle(int RequestId) {
        Request request = requestDao.getRequestInfo(RequestId);
        //建立圈子
        if (!setUpCircle(request.getRequestCircleName(), request.getRequestCircleContent(), request.getRequest_UserId()))
            return false;
        //发信息
        Message message = new Message();
        message.setMessageUserIdSet(null);
        message.setMessageContent("您的圈子 " + request.getRequestCircleName() + " 已建立成功！");
        message.setMessageUserIdGet(request.getRequest_UserId());
        message.setMessageSetTime(MyTime.getNowTime());
        if (messageDao.insertMessage(message) < 1) return false;
        //设置请求已审核
        return requestDao.setRequestChecked(RequestId);
    }

    //退回建立圈子--管理员用
    public boolean refuseSetUpCircle(int RequestId) {
        Request request = requestDao.getRequestInfo(RequestId);
        //发信息
        Message message = new Message();
        message.setMessageUserIdSet(null);
        message.setMessageContent("您的圈子 " + request.getRequestCircleName() + " 建立识别，请您仔细检查申请圈子信息是否有违规哦！");
        message.setMessageUserIdGet(request.getRequest_UserId());
        message.setMessageSetTime(MyTime.getNowTime());
        if (messageDao.insertMessage(message) < 1) return false;
        //设置请求已审核
        return requestDao.setRequestChecked(RequestId);
    }

    //    p)	任命新圈主（需要向管理员申请）【在request、circle中】--管理员用
    @Override
    public boolean setNewCircleUser(int circleId, String userId) {
        Circle circle = new Circle();
        circle.setCircleId(circleId);
        circle.setCircle_UserId(userId);
        return circleDao.updateCircleMaster(circle) > 0;
    }

    //批准任命新圈主--管理员用
    public boolean allowSetNewCircleUser(int RequestId) {
        Request request = requestDao.getRequestInfo(RequestId);
        //建立圈子
        if (!setNewCircleUser(request.getRequestCircleId(), request.getRequestUserId()))
            return false;
        //发信息给申请人
        Message message = new Message();
        message.setMessageUserIdSet(null);
        message.setMessageContent("您的圈子 " + request.getRequestCircleName() + " 已任命给新圈主！");
        message.setMessageUserIdGet(request.getRequest_UserId());
        message.setMessageSetTime(MyTime.getNowTime());
        if (messageDao.insertMessage(message) < 1) return false;
        //发信息给新圈主
        Message message1 = new Message();
        message1.setMessageUserIdSet(null);
        message1.setMessageContent("您已经被任命为圈子 " + request.getRequestCircleName() + " 的圈主！");
        message1.setMessageUserIdGet(request.getRequestUserId());
        message1.setMessageSetTime(MyTime.getNowTime());
        if (messageDao.insertMessage(message1) < 1) return false;
        //设置请求已审核
        return requestDao.setRequestChecked(RequestId);
    }

    //退回任命新圈主--管理员用
    public boolean refuseSetNewCircleUser(int RequestId) {
        Request request = requestDao.getRequestInfo(RequestId);
        //发信息
        Message message = new Message();
        message.setMessageUserIdSet(null);
        message.setMessageContent("您的 " + request.getRequestCircleName() + " 圈子任命新圈主被退回了，请检查请求是否有违规！");
        message.setMessageUserIdGet(request.getRequest_UserId());
        message.setMessageSetTime(MyTime.getNowTime());
        if (messageDao.insertMessage(message) < 1) return false;
        //设置请求已审核
        return requestDao.setRequestChecked(RequestId);
    }


    //    q)	修改圈子相关信息（需要向管理员申请）【在request、circle中】--管理员用
    @Override
    public boolean updateCircleContent(int circleId, String circleName, String circleContent) {
        //先处理内容中的换行和空格
        circleContent = circleContent.replace("&nbsp", " ");
        circleContent = circleContent.replace("<br>", "\n");
        Circle circle = new Circle();
        circle.setCircleId(circleId);
        circle.setCircleName(circleName);
        circle.setCircleContent(circleContent);
        return circleDao.updateCircleContent(circle) > 0;
    }

    //批准修改圈子相关信息--管理员用
    public boolean allowUpdateCircleContent(int RequestId) {
        Request request = requestDao.getRequestInfo(RequestId);
        //建立圈子
        if (!updateCircleContent(request.getRequestCircleId(), request.getRequestCircleName(), request.getRequestCircleContent()))
            return false;
        //发信息给申请人
        Message message = new Message();
        message.setMessageUserIdSet(null);
        message.setMessageContent("您的圈子 " + request.getRequestCircleName() + " 已修改信息成功！");
        message.setMessageUserIdGet(request.getRequest_UserId());
        message.setMessageSetTime(MyTime.getNowTime());
        if (messageDao.insertMessage(message) < 1) return false;
        //设置请求已审核
        return requestDao.setRequestChecked(RequestId);
    }

    //退回修改圈子相关信息--管理员用
    public boolean refuseUpdateCircleContent(int RequestId) {
        Request request = requestDao.getRequestInfo(RequestId);
        //发信息
        Message message = new Message();
        message.setMessageUserIdSet(null);
        message.setMessageContent("您的圈子 " + request.getRequestCircleName() + " 修改信息请求被退回了，请检查请求是否有违规！");
        message.setMessageUserIdGet(request.getRequest_UserId());
        message.setMessageSetTime(MyTime.getNowTime());
        if (messageDao.insertMessage(message) < 1) return false;
        //设置请求已审核
        return requestDao.setRequestChecked(RequestId);
    }

}
