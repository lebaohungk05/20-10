package manager;

import java.util.List;
import object.HoaDon;

/**
 * Class ThanhToan: Quản lý thanh toán hóa đơn khách sạn.
 */
public class ThanhToan {
    private final List<HoaDon> hoaDons;

    /**
     * Constructor khởi tạo danh sách hóa đơn.
     * @param hoaDons Danh sách hóa đơn cần quản lý.
     */
    public ThanhToan(List<HoaDon> hoaDons) {
        this.hoaDons = hoaDons;
    }

    /**
     * Xuất hóa đơn chi tiết.
     */
    public void xuatHoaDon() {
        if (hoaDons.isEmpty()) {
            System.out.println("Không có hóa đơn nào để hiển thị.");
            return;
        }
        System.out.println("Danh sách hóa đơn:");
        for (HoaDon hoaDon : hoaDons) {
            System.out.println(hoaDon);
        }
    }

    /**
     * Tính tổng tiền của tất cả hóa đơn.
     * @return Tổng tiền của tất cả hóa đơn.
     */
    public double tinhTongTien() {
        double tongTien = 0;
        for (HoaDon hoaDon : hoaDons) {
            tongTien += hoaDon.getTongTien();
        }
        return tongTien;
    }

    /**
     * Hỗ trợ thanh toán online (mô phỏng).
     */
    public void hoTroThanhToanOnline() {
        System.out.println("Thanh toán online thành công!");
    }

    /**
     * Kiểm tra trạng thái thanh toán của hóa đơn.
     * @return "Chưa thanh toán" nếu có ít nhất một hóa đơn chưa thanh toán, ngược lại "Đã thanh toán".
     */
    public String trangThaiThanhToan() {
        for (HoaDon hoaDon : hoaDons) {
            if (hoaDon.getTrangThaiThanhToan().equalsIgnoreCase("Chưa thanh toán")) {
                return "Chưa thanh toán";
            }
        }
        return "Đã thanh toán";
    }
}
