package manager;

import object.Phong;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class QuanLyDatPhong: Quản lý đặt phòng khách sạn.
 */
public class QuanLyDatPhong {
    private final List<Phong> danhSachPhong;

    /**
     * Constructor khởi tạo danh sách phòng.
     */
    public QuanLyDatPhong() {
        danhSachPhong = new ArrayList<>();
    }

    /**
     * Đặt phòng mới vào danh sách.
     * @param phong Phòng cần đặt.
     */
    public void datPhong(Phong phong) {
        danhSachPhong.add(phong);
        System.out.println("Đã đặt phòng: " + phong.getMaPhong());
    }

    /**
     * Hủy đặt phòng theo mã phòng.
     * @param maPhong Mã phòng cần hủy.
     * @return true nếu hủy thành công, false nếu không tìm thấy phòng.
     */
    public boolean huyDatPhong(String maPhong) {
        boolean removed = danhSachPhong.removeIf(phong -> phong.getMaPhong().equals(maPhong));
        if (removed) {
            System.out.println("Đã hủy đặt phòng: " + maPhong);
        } else {
            System.out.println("Không tìm thấy phòng có mã: " + maPhong);
        }
        return removed;
    }

    /**
     * Tìm kiếm phòng theo mã phòng.
     * @param maPhong Mã phòng cần tìm.
     * @return Optional chứa phòng nếu tìm thấy, ngược lại rỗng.
     */
    public Optional<Phong> timPhong(String maPhong) {
        return danhSachPhong.stream()
                .filter(phong -> phong.getMaPhong().equals(maPhong))
                .findFirst();
    }

    /**
     * Hiển thị danh sách các phòng đã đặt.
     */
    public void hienThiDanhSachDatPhong() {
        if (danhSachPhong.isEmpty()) {
            System.out.println("Danh sách đặt phòng trống.");
            return;
        }
        System.out.println("Danh sách các phòng đã đặt:");
        for (Phong phong : danhSachPhong) {
            System.out.println("Mã phòng: " + phong.getMaPhong() +
                    " | Loại: " + phong.getLoaiPhong() +
                    " | Giá: " + phong.getGia() +
                    " | Trạng thái: " + phong.getTrangThai() +
                    " | Số người tối đa: " + phong.getSoNguoiToiDa() +
                    " | Tiện nghi: " + phong.getTienNghi());
        }
    }
}
