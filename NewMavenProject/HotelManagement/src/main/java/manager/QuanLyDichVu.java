package manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import object.DichVu;

/**
 * Class QuanLyDichVu: Quản lý các dịch vụ khách sạn.
 */
public class QuanLyDichVu {
    private Map<String, List<DichVu>> danhSachDichVuKhachHang;

    /**
     * Constructor khởi tạo hệ thống quản lý dịch vụ.
     */
    public QuanLyDichVu() {
        this.danhSachDichVuKhachHang = new HashMap<>();
    }

    /**
     * Đặt dịch vụ cho khách hàng.
     * @param tenKhachHang Tên khách hàng.
     * @param dichVu Dịch vụ được đặt.
     */
    public void datDichVu(String tenKhachHang, DichVu dichVu) {
        danhSachDichVuKhachHang.putIfAbsent(tenKhachHang, new ArrayList<>());
        danhSachDichVuKhachHang.get(tenKhachHang).add(dichVu);
        System.out.println("Đã đặt dịch vụ " + dichVu.getTenDichVu() + " cho khách hàng " + tenKhachHang);
    }

    /**
     * Hủy dịch vụ đã đặt của khách hàng.
     * @param tenKhachHang Tên khách hàng.
     * @param tenDichVu Tên dịch vụ cần hủy.
     */
    public void huyDichVu(String tenKhachHang, String tenDichVu) {
        if (danhSachDichVuKhachHang.containsKey(tenKhachHang)) {
            List<DichVu> dichVus = danhSachDichVuKhachHang.get(tenKhachHang);
            boolean removed = dichVus.removeIf(dv -> dv.getTenDichVu().equals(tenDichVu));
            if (removed) {
                System.out.println("Đã hủy dịch vụ " + tenDichVu + " cho khách hàng " + tenKhachHang);
            } else {
                System.out.println("Khách hàng " + tenKhachHang + " không có dịch vụ " + tenDichVu + " để hủy.");
            }
        } else {
            System.out.println("Khách hàng " + tenKhachHang + " chưa đặt dịch vụ nào.");
        }
    }

    /**
     * Thống kê số lần sử dụng của từng dịch vụ.
     */
    public void thongKeSuDungDichVu() {
        Map<String, Integer> thongKe = new HashMap<>();
        for (List<DichVu> dichVus : danhSachDichVuKhachHang.values()) {
            for (DichVu dv : dichVus) {
                thongKe.put(dv.getTenDichVu(), thongKe.getOrDefault(dv.getTenDichVu(), 0) + 1);
            }
        }

        System.out.println("Thống kê sử dụng dịch vụ:");
        for (Map.Entry<String, Integer> entry : thongKe.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " lượt sử dụng");
        }
    }
}
