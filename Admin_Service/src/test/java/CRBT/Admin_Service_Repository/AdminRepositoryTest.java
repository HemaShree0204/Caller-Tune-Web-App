package CRBT.Admin_Service_Repository;


import CRBT.Admin_Service.Model.Admin;
import CRBT.Admin_Service.Repository.AdminRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AdminRepositoryTest {

    @Autowired
    private AdminRepository adminRepository;

    @Test
    void testSaveAndFindById() {
        Admin admin = new Admin();
        admin.setUsername("testUser");
        admin.setEmail("test@example.com");
        admin.setPassword("pass");

        admin = adminRepository.save(admin);

        Admin found = adminRepository.findById(admin.getId()).orElse(null);
        assertNotNull(found);
        assertEquals("testUser", found.getUsername());
    }

    @Test
    void testExistsByUsernameAndEmail() {
        Admin admin = new Admin();
        admin.setUsername("checkUser");
        admin.setEmail("check@example.com");
        adminRepository.save(admin);

        assertTrue(adminRepository.existsByUsername("checkUser"));
        assertTrue(adminRepository.existsByEmail("check@example.com"));
    }
}
