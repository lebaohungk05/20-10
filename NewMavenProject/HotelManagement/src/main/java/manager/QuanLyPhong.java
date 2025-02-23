package manager;

import database.DatabaseConnection;
import object.Phong;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class QuanLyPhong: Quản lý thông tin phòng trong khách sạn.
 */
public class QuanLyPhong {
    private final List<Phong> danhSachPhong;

    /**
     * Constructor khởi tạo danh sách phòng.
     */
    public QuanLyPhong() {
        danhSachPhong = new ArrayList<>();
    }

    /**
     * Lấy danh sách phòng từ MySQL.
     */
    public void layDanhSachPhongTuMySQL() {
        danhSachPhong.clear(); // Xóa danh sách cũ để tránh dữ liệu trùng lặp
        String sql = "SELECT * FROM Phong";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Phong phong = new Phong(
                        rs.getString("maPhong"),
                        rs.getString("loaiPhong"),
                        rs.getInt("gia"),
                        rs.getString("tinhTrang"),
                        rs.getInt("soNguoiToiDa"),
                        rs.getString("tienNghi"),
                        rs.getFloat("danhGia")
                );
                danhSachPhong.add(phong);
            }
            System.out.println(" Lấy danh sách phòng từ MySQL thành công!");
        } catch (Exception e) {
            System.out.println(" Lỗi khi lấy dữ liệu từ MySQL: " + e.getMessage());
        }
    }

    /**
     * Trả về danh sách phòng.
     * @return Danh sách phòng hiện có.
     */
    public List<Phong> getDanhSachPhong() {
        return danhSachPhong;
    }
}
