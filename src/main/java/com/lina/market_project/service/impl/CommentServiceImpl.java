package com.lina.market_project.service.impl;

import com.lina.market_project.pojo.Comment;
import com.lina.market_project.mapper.CommentMapper;
import com.lina.market_project.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 白粥
 * @since 2021-09-06
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

}
