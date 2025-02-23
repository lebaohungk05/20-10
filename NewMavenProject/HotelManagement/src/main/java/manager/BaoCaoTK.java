package manager;

import java.util.*;
import object.HoaDon;
import object.Phong;

public class BaoCaoTK {
    private List<HoaDon> hoaDons;
    private List<Phong> danhSachPhong;

    public BaoCaoTK(List<HoaDon> hoaDons, List<Phong> danhSachPhong) {
        this.hoaDons = hoaDons;
        this.danhSachPhong = danhSachPhong;
    }

    //  1. Thống kê doanh thu từ danh sách hóa đơn
    public double thongKeDoanhThu() {
        double doanhThu = 0;
        for (HoaDon hoaDon : hoaDons) {
            doanhThu += hoaDon.getTongTien();
        }
        return doanhThu;
    }

    //  2. Đếm số phòng đã đặt (chỉ tính phòng có trạng thái "Đã đặt")
    public int soPhongDaDat() {
        int count = 0;
        for (Phong phong : danhSachPhong) {
            if ("Đã đặt".equalsIgnoreCase(phong.getTrangThai())) {
                count++;
            }
        }
        return count;
    }

    //  3. Tìm danh sách khách hàng VIP (top 5 khách chi tiêu nhiều nhất)
    public List<String> timKhachHangVIP() {
        Map<String, Double> tongChiTieu = new HashMap<>();

        for (HoaDon hoaDon : hoaDons) {
            String maKH = hoaDon.getMaKH();
            double tongTien = hoaDon.getTongTien();
            tongChiTieu.put(maKH, tongChiTieu.getOrDefault(maKH, 0.0) + tongTien);
        }

        // Sắp xếp danh sách theo tổng tiền chi tiêu giảm dần
        List<Map.Entry<String, Double>> sortedList = new ArrayList<>(tongChiTieu.entrySet());
        sortedList.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));

        // Lấy top 5 khách hàng VIP
        List<String> danhSachVIP = new ArrayList<>();
        for (int i = 0; i < Math.min(5, sortedList.size()); i++) {
            danhSachVIP.add(sortedList.get(i).getKey() + " (Tổng chi tiêu: " + sortedList.get(i).getValue() + ")");
        }

        return danhSachVIP;
    }

    //  4. Phân tích xu hướng đặt phòng theo loại phòng
    public String phanTichXuHuongDatPhong() {
        Map<String, Integer> loaiPhongCount = new HashMap<>();

        for (Phong phong : danhSachPhong) {
            String loaiPhong = phong.getLoaiPhong();
            loaiPhongCount.put(loaiPhong, loaiPhongCount.getOrDefault(loaiPhong, 0) + 1);
        }

        String xuHuong = "Không có xu hướng rõ ràng.";
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : loaiPhongCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                xuHuong = "Loại phòng được đặt nhiều nhất: " + entry.getKey();
            }
        }

        return xuHuong;
    }

    //  5. Xuất báo cáo chi tiết
    public void hienThiBaoCao() {
        System.out.println(" BÁO CÁO THỐNG KÊ KHÁCH SẠN 📊");
        System.out.println("--------------------------------------------------");
        System.out.println(" Tổng doanh thu: " + thongKeDoanhThu() + " VND");
        System.out.println(" Số phòng đã đặt: " + soPhongDaDat());
        System.out.println(" Danh sách khách hàng VIP:");
        for (String khachVIP : timKhachHangVIP()) {
            System.out.println("   - " + khachVIP);
        }
        System.out.println(" " + phanTichXuHuongDatPhong());
        System.out.println("--------------------------------------------------");
    }
}
