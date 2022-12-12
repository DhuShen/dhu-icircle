package TestDao;

import com.dhu.domain.Request;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RequestDao {
    //增加请求
    int insertRequest(Request request);

    //查询未审核请求
    List<Request> selectAllOrder();
    //查询已审核请求
    List<Request> selectAllOrderChecked();
    //设置已经审批
    boolean setRequestChecked(@Param("requestId") int requestId);

    boolean setRequestBacked(@Param("requestId") int requestId);

    Request getRequestInfo(@Param("requestId")int requestId);
}
