package com.nikhil.blog.config;

import com.nikhil.blog.entities.Role;
import com.nikhil.blog.repositories.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RoleInitializer implements CommandLineRunner {

        @Autowired
        private RoleRepo roleRepo;

        @Override
        public void run(String... args) throws Exception {
            // Check and create ADMIN role
            if (roleRepo.findByName("ADMIN").isEmpty()) {
                Role adminRole = new Role();
                adminRole.setRoleId(AppConstants.ADMIN);
                adminRole.setName("ROLE_ADMIN");
                roleRepo.save(adminRole);
            }

            // Check and create NORMAL role
            if (roleRepo.findByName("NORMAL_USER").isEmpty()) {
                Role normalRole = new Role();
                normalRole.setRoleId(AppConstants.NORMAL_USER);
                normalRole.setName("ROLE_NORMAL");
                roleRepo.save(normalRole);
            }

            System.out.println("Roles initialized");
        }
}
