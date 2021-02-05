package generate;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * dubbo_service
 * @author 
 */
@Data
public class DubboService implements Serializable {
    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 姓名
     */
    private String stuName;

    /**
     * 年龄
     */
    private String stuAge;

    /**
     * 成绩
     */
    private String stuScore;

    private String ecStatus;

    private Date createTime;

    private Date modifyTime;

    private static final long serialVersionUID = 1L;
}