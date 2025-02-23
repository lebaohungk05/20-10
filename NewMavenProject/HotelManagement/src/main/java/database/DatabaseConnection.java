package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import object.DatPhong;
import object.KhachHang;
import object.Phong;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/HotelManagement";
    private static final String USER = "YiJin";
    private static final String PASSWORD = "200500";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Lỗi kết nối database: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static ObservableList<Phong> layDanhSachPhongTrong() {
        ObservableList<Phong> danhSachPhong = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Phong";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Phong phong = new Phong(
                        rs.getString("maPhong"),
                        rs.getString("loaiPhong"),
                        rs.getDouble("gia"),
                        rs.getString("tinhTrang"),
                        rs.getInt("soNguoiToiDa"),
                        rs.getString("tienNghi"),
                        rs.getDouble("danhGia")
                );
                danhSachPhong.add(phong);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách phòng: " + e.getMessage());
            e.printStackTrace();
        }

        return danhSachPhong;
    }

    public static ObservableList<KhachHang> layDanhSachKhachHang() {
        ObservableList<KhachHang> danhSachKhachHang = FXCollections.observableArrayList();
        String query = "SELECT * FROM KhachHang";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                danhSachKhachHang.add(new KhachHang(
                        rs.getString("maKhachHang"),
                        rs.getString("hoTen"),
                        rs.getString("cccd"),
                        rs.getString("soDienThoai"),
                        rs.getString("email"),
                        rs.getString("diaChi"),
                        rs.getDate("ngayDangKy")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách khách hàng: " + e.getMessage());
            e.printStackTrace();
        }
        return danhSachKhachHang;
    }

    public static ObservableList<DatPhong> layDanhSachDatPhong() {
        ObservableList<DatPhong> danhSachDatPhong = FXCollections.observableArrayList();
        String sql = "SELECT dp.maDatPhong, dp.maKhachHang, dp.maPhong, dp.ngayNhanPhong, dp.ngayTraPhong, dp.tongTien " +
                "FROM DatPhong dp";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                DatPhong datPhong = new DatPhong(
                        rs.getString("maDatPhong"),
                        rs.getString("maKhachHang"),
                        rs.getString("maPhong"),
                        rs.getDate("ngayNhanPhong"),
                        rs.getDate("ngayTraPhong"),
                        rs.getDouble("tongTien"),
                        false,
                        ""
                );
                danhSachDatPhong.add(datPhong);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách đặt phòng: " + e.getMessage());
            e.printStackTrace();
        }
        return danhSachDatPhong;
    }

    public static boolean datPhong(String maKhachHang, String maPhong, String ngayNhan, String ngayTra, int tongTien) {
        String sql = "INSERT INTO DatPhong (maKhachHang, maPhong, ngayNhanPhong, ngayTraPhong, tongTien) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maKhachHang);
            stmt.setString(2, maPhong);
            stmt.setString(3, ngayNhan);
            stmt.setString(4, ngayTra);
            stmt.setInt(5, tongTien);
            stmt.executeUpdate();

            String updateSql = "UPDATE Phong SET tinhTrang = 'Đã đặt' WHERE maPhong = ?";
            try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                updateStmt.setString(1, maPhong);
                updateStmt.executeUpdate();
            }

            return true;
        } catch (SQLException e) {
            System.err.println("Lỗi khi đặt phòng: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

     public static ObservableList<KhachHang> timKhachHangTheoTen(String tenKH) {
        ObservableList<KhachHang> danhSachKhachHang = FXCollections.observableArrayList();
        String sql = "SELECT * FROM KhachHang WHERE hoTen LIKE ?";  // Giả sử table KhachHang có cột hoTen

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + tenKH + "%");  // Sử dụng LIKE cho tìm kiếm gần đúng

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    danhSachKhachHang.add(new KhachHang(
                        rs.getString("maKhachHang"),
                        rs.getString("hoTen"),
                        rs.getString("cccd"),
                        rs.getString("soDienThoai"),
                        rs.getString("email"),
                        rs.getString("diaChi"),
                        rs.getDate("ngayDangKy")
                    ));
                }
            }

        } catch (SQLException e) {
            System.err.println("Lỗi tìm kiếm khách hàng theo tên: " + e.getMessage());
            e.printStackTrace();
        }

        return danhSachKhachHang;
    }

    public static ObservableList<KhachHang> timKhachHangTheoMa(String maKH) {
        ObservableList<KhachHang> danhSachKhachHang = FXCollections.observableArrayList();
        String sql = "SELECT * FROM KhachHang WHERE maKhachHang = ?";  // Giả sử table KhachHang có cột maKH

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, maKH);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    danhSachKhachHang.add(new KhachHang(
                        rs.getString("maKhachHang"),
                        rs.getString("hoTen"),
                        rs.getString("cccd"),
                        rs.getString("soDienThoai"),
                        rs.getString("email"),
                        rs.getString("diaChi"),
                        rs.getDate("ngayDangKy")
                    ));
                }
            }

        } catch (SQLException e) {
            System.err.println("Lỗi tìm kiếm khách hàng theo mã: " + e.getMessage());
            e.printStackTrace();
        }

        return danhSachKhachHang;
    }

   public static ObservableList<ThongKeDoanhThu> generateMonthlyRevenueReport() {
        ObservableList<ThongKeDoanhThu> data = FXCollections.observableArrayList();
       // String sql = "SELECT strftime('%Y-%m', ngayNhanPhong) AS Thang, SUM(tongTien) AS TongDoanhThu " +
          //           "FROM DatPhong " +
           //          "GROUP BY Thang " +
            //         "ORDER BY Thang";

        String sql = "SELECT DATE_FORMAT(ngayNhanPhong, '%Y-%m') AS Thang, SUM(tongTien) AS TongDoanhThu FROM DatPhong GROUP BY Thang ORDER BY Thang";
        try (Connection conn = getConnection();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                data.add(new ThongKeDoanhThu(rs.getString("Thang"), rs.getDouble("TongDoanhThu")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }
      public static ObservableList<KhachHang> generateNewCustomerReport() {
          ObservableList<KhachHang> data = FXCollections.observableArrayList();
          String sql = "SELECT * FROM KhachHang ORDER BY ngayDangKy DESC LIMIT 10";
          try (Connection conn = getConnection();
               Statement stmt  = conn.createStatement();
               ResultSet rs    = stmt.executeQuery(sql)) {

              // loop through the result set
              while (rs.next()) {
                  KhachHang kh = new KhachHang(
                          rs.getString("maKhachHang"),
                          rs.getString("hoTen"),
                          rs.getString("cccd"),
                          rs.getString("soDienThoai"),
                          rs.getString("email"),
                          rs.getString("diaChi"),
                          rs.getDate("ngayDangKy")
                  );
                  data.add(kh);
              }
          } catch (SQLException e) {
              System.out.println(e.getMessage());
          }
          return data;
    }
       public static class ThongKeDoanhThu {
        private String thang;
        private double tongDoanhThu;

        public ThongKeDoanhThu(String thang, double tongDoanhThu) {
            this.thang = thang;
            this.tongDoanhThu = tongDoanhThu;
        }

        public String getThang() { return thang; }
        public double getTongDoanhThu() { return tongDoanhThu; }
    }
}