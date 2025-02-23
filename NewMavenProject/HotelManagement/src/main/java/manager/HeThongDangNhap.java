package manager;

import java.util.HashMap;
import java.util.Map;

/**
 * Class HeThongDangNhap: Hệ thống đăng nhập, quản lý tài khoản người dùng và quyền truy cập.
 */
public class HeThongDangNhap {
    private Map<String, String> userAccounts;
    private Map<String, String> userRoles;
    private Map<String, String> twoFactorCodes;

    /**
     * Constructor khởi tạo hệ thống đăng nhập.
     */
    public HeThongDangNhap() {
        userAccounts = new HashMap<>();
        userRoles = new HashMap<>();
        twoFactorCodes = new HashMap<>();
    }

    /**
     * Thêm người dùng mới vào hệ thống.
     * @param username Tên đăng nhập.
     * @param password Mật khẩu.
     * @param role Quyền truy cập.
     */
    public void addUser(String username, String password, String role) {
        userAccounts.put(username, password);
        userRoles.put(username, role);
    }

    /**
     * Xác thực người dùng bằng tên đăng nhập và mật khẩu.
     * @param username Tên đăng nhập.
     * @param password Mật khẩu.
     * @return true nếu xác thực thành công, ngược lại false.
     */
    public boolean authenticate(String username, String password) {
        return userAccounts.containsKey(username) && userAccounts.get(username).equals(password);
    }

    /**
     * Kiểm tra quyền truy cập của người dùng.
     * @param username Tên đăng nhập.
     * @param role Quyền cần kiểm tra.
     * @return true nếu người dùng có quyền phù hợp, ngược lại false.
     */
    public boolean authorize(String username, String role) {
        return userRoles.containsKey(username) && userRoles.get(username).equals(role);
    }

    /**
     * Thiết lập mã xác thực hai lớp cho người dùng.
     * @param username Tên đăng nhập.
     * @param code Mã xác thực hai lớp.
     */
    public void setTwoFactorCode(String username, String code) {
        twoFactorCodes.put(username, code);
    }

    /**
     * Xác minh mã xác thực hai lớp.
     * @param username Tên đăng nhập.
     * @param code Mã xác thực cần kiểm tra.
     * @return true nếu mã xác thực đúng, ngược lại false.
     */
    public boolean verifyTwoFactorCode(String username, String code) {
        return twoFactorCodes.containsKey(username) && twoFactorCodes.get(username).equals(code);
    }
}
