package CRBT.Admin_Service.Service;

import CRBT.Admin_Service.Model.Admin;
import CRBT.Admin_Service.Repository.AdminRepository;
import CRBT.Admin_Service.exception.AdminNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Admin_Service {

    @Autowired
    private AdminRepository adminRepository;

    // Get all admins
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    // Get admin by ID
    public Admin getAdminById(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new AdminNotFoundException("Admin not found with ID: " + id));
    }

    // Create admin
    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    // Update admin
    public Admin updateAdmin(Long id, Admin adminDetails) {
        Admin existingAdmin = getAdminById(id);
        existingAdmin.setUsername(adminDetails.getUsername());
        existingAdmin.setEmail(adminDetails.getEmail());
        existingAdmin.setUsername(adminDetails.getUsername());
        existingAdmin.setPassword(adminDetails.getPassword());
        return adminRepository.save(existingAdmin);
    }

    // Delete admin
    public void deleteAdmin(Long id) {
        Admin admin = getAdminById(id);
        adminRepository.delete(admin);
    }
}
