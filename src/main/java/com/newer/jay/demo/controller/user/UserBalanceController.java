package com.newer.jay.demo.controller.user;

import com.newer.jay.demo.service.user.UserBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user/balance")
@CrossOrigin(origins = "http://localhost:5173")
public class UserBalanceController {
    
    @Autowired
    private UserBalanceService userBalanceService;
    
    /**
     * 查询用户余额
     */
    @GetMapping("/{userId}")
    public Map<String, Object> getBalance(@PathVariable Long userId) {
        try {
            Double balance = userBalanceService.getBalance(userId);
            Map<String, Object> response = new HashMap<>();
            response.put("status", 0);
            response.put("message", "查询成功");
            response.put("balance", balance);
            return response;
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", 1);
            response.put("message", "查询失败: " + e.getMessage());
            return response;
        }
    }
    
    /**
     * 用户充值
     */
    @PostMapping("/recharge")
    public Map<String, Object> recharge(@RequestBody Map<String, Object> request) {
        try {
            Long userId = Long.valueOf(request.get("userId").toString());
            Double amount = Double.valueOf(request.get("amount").toString());
            
            if (amount <= 0) {
                Map<String, Object> response = new HashMap<>();
                response.put("status", 1);
                response.put("message", "充值金额必须大于0");
                return response;
            }
            
            Double newBalance = userBalanceService.addBalance(userId, amount);
            Map<String, Object> response = new HashMap<>();
            response.put("status", 0);
            response.put("message", "充值成功");
            response.put("newBalance", newBalance);
            return response;
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", 1);
            response.put("message", "充值失败: " + e.getMessage());
            return response;
        }
    }
    
    /**
     * 扣减余额（用于支付）
     */
    @PostMapping("/deduct")
    public Map<String, Object> deductBalance(@RequestBody Map<String, Object> request) {
        try {
            Long userId = Long.valueOf(request.get("userId").toString());
            Double amount = Double.valueOf(request.get("amount").toString());
            
            if (amount <= 0) {
                Map<String, Object> response = new HashMap<>();
                response.put("status", 1);
                response.put("message", "扣减金额必须大于0");
                return response;
            }
            
            Double newBalance = userBalanceService.deductBalance(userId, amount);
            Map<String, Object> response = new HashMap<>();
            
            if (newBalance != null) {
                response.put("status", 0);
                response.put("message", "支付成功");
                response.put("newBalance", newBalance);
            } else {
                response.put("status", 1);
                response.put("message", "余额不足，请先充值");
            }
            
            return response;
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", 1);
            response.put("message", "支付失败: " + e.getMessage());
            return response;
        }
    }
}
