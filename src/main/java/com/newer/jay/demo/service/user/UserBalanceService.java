package com.newer.jay.demo.service.user;

import com.newer.jay.demo.entity.User;
import com.newer.jay.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserBalanceService {
    
    @Autowired
    private UserMapper userMapper;
    
    /**
     * 增加用户余额
     * @param userId 用户ID
     * @param amount 增加金额
     * @return 更新后的余额
     */
    @Transactional
    public Double addBalance(Long userId, Double amount) {
        User user = userMapper.selectById(userId);
        if (user != null) {
            Double currentBalance = user.getBalance() != null ? user.getBalance() : 0.0;
            Double newBalance = currentBalance + amount;
            user.setBalance(newBalance);
            userMapper.updateById(user);
            return newBalance;
        }
        return 0.0;
    }
    
    /**
     * 扣减用户余额
     * @param userId 用户ID
     * @param amount 扣减金额
     * @return 更新后的余额，余额不足时返回null
     */
    @Transactional
    public Double deductBalance(Long userId, Double amount) {
        User user = userMapper.selectById(userId);
        if (user != null) {
            Double currentBalance = user.getBalance() != null ? user.getBalance() : 0.0;
            if (currentBalance >= amount) {
                Double newBalance = currentBalance - amount;
                user.setBalance(newBalance);
                userMapper.updateById(user);
                return newBalance;
            }
        }
        return null; // 余额不足
    }
    
    /**
     * 查询用户余额
     * @param userId 用户ID
     * @return 用户余额
     */
    public Double getBalance(Long userId) {
        User user = userMapper.selectById(userId);
        return user != null ? (user.getBalance() != null ? user.getBalance() : 0.0) : 0.0;
    }
    
    /**
     * 增加用户订单数
     * @param userId 用户ID
     */
    @Transactional
    public void incrementTotalOrders(Long userId) {
        User user = userMapper.selectById(userId);
        if (user != null) {
            Integer currentOrders = user.getTotalOrders() != null ? user.getTotalOrders() : 0;
            user.setTotalOrders(currentOrders + 1);
            userMapper.updateById(user);
        }
    }
}
