package com.cnjava.SpringBootProject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cnjava.SpringBootProject.Model.AppUser;
import com.cnjava.SpringBootProject.Service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users") // Định nghĩa đường dẫn cơ sở cho tất cả các yêu cầu API người dùng
public class UserAPIController {
    @Autowired
    private UserService userService;

    // Định nghĩa phương thức để lấy tất cả người dùng
    @GetMapping
    public List<AppUser> getAllUsers() {
        return userService.getAllUser();
    }

    // Định nghĩa phương thức để lấy người dùng theo ID
    @GetMapping("/{id}")
    public AppUser getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    // Định nghĩa phương thức để lấy người dùng theo địa chỉ email
    @GetMapping("/email/{email}")
    public AppUser getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    // Định nghĩa phương thức để tạo mới người dùng
    @PostMapping
    public void createUser(@RequestBody AppUser user) {
        userService.save(user);
    }

    // Định nghĩa phương thức để cập nhật người dùng
    @PutMapping("/{id}")
    public void updateUser(@PathVariable int id, @RequestBody AppUser user) {
        AppUser existingUser = userService.getUserById(id);
        if (existingUser != null) {
            // Cập nhật thông tin người dùng
            existingUser.setUserName(user.getUserName());
            existingUser.setAddress(user.getAddress());
            existingUser.setPhoneNumber(user.getPhoneNumber());
            // Cập nhật các trường khác tùy ý
            userService.save(existingUser);
        }
    }

    // Định nghĩa phương thức để xóa người dùng theo ID
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteById(id);
    }

    // Định nghĩa phương thức để lấy số lượng người dùng giới hạn trên mỗi trang
    @GetMapping("/limit")
    public List<AppUser> getUsersWithLimit(@RequestParam(name = "page", defaultValue = "0") int page,
                                           @RequestParam(name = "n", defaultValue = "10") int n) {
        return userService.getLimit(page, n);
    }
}