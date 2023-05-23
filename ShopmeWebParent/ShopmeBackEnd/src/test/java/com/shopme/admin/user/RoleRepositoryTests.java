package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Role;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(true)
public class RoleRepositoryTests {
	@Autowired
	private RoleRepository repo;

	@Test
	public void testCreateNewRole() {
		Role roleAdmin = new Role("Admin", "Manage everything");
		Role savedRole = repo.save(roleAdmin);
		assertThat(savedRole.getId()).isGreaterThan(0);
	}

	@Test
	public void testCreateRestRoles() {
		Role salesPerson = new Role("Salesperson", "Manage product price, customers, shipping, orders, sales report");
		Role editorPerson = new Role("Editor", "Manage categories, brands, products, articles and menus");
		Role shipperPerson = new Role("Shipper", "view products, view orders, update order status");
		Role assistant = new Role("Assistant", "manage questions and reviews");

		Iterable<Role> savedRoles = repo.saveAll(List.of(salesPerson, editorPerson, shipperPerson, assistant));
		savedRoles.forEach(r -> assertThat(r.getId()).isGreaterThan(0));

	}
}
